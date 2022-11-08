package be.ugent.oplossing;

import be.ugent.oplossing.model.IFace;
import be.ugent.oplossing.model.IRubikCube;
import be.ugent.oplossing.model.RubiksKubus;
import be.ugent.oplossing.show.FaceView;
import be.ugent.oplossing.show.RubiksReader;
import be.ugent.oplossing.show.Shape3DRectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.List;

// drag the mouse over the rectangle to rotate it.
public class RubiksViewer extends Application {
    private static final Font TITLE_FONT = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40);
    private static final Color BACKGROUND_COLOR = Color.web("0xe5e5e5");

    private final Rotate rotateX = new Rotate(20, 0, 0, 0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(-40, 0, 0, 0, Rotate.Y_AXIS);
    private final int updateDegreeIncrement = 5;
    private final int animationDuration = 250;

    private double anchorX, anchorY;
    private final Group meshGroup = new Group();
    private IRubikCube rubikCube;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // TODO: replace the dummy cube with your own implementation
        // -- weg: rubikCube = RubiksReader.ReadFromFile("fullCube.csv");
        rubikCube = new RubiksKubus();
        Scene scene = initGui();
        scene.setFill(BACKGROUND_COLOR);
        primaryStage.setTitle("Rubik's Cube Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene initGui() {
        // init BorderPane layout
        final BorderPane layout = new BorderPane();

        // init 3D Viewer for rubic's cube
        final Group viewer3D = new Group();
        final SubScene subScene = new SubScene(viewer3D, 500, 500, true, SceneAntialiasing.DISABLED);
        addCamera(subScene);
        addMouseHandlers(subScene);
        layout.setCenter(subScene);

        // Draw initial Rubic's cube
        List<IFace> faces = rubikCube.getAllFaces();
        List<Shape3DRectangle> shapes = faces.stream().map(Shape3DRectangle::new).toList();
        meshGroup.getChildren().addAll(shapes);
        meshGroup.getTransforms().addAll(rotateX, rotateY);
        viewer3D.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));

        // Add Title
        Label title = new Label("Rubik's Cube");
        title.setAlignment(Pos.CENTER);
        title.setFont(TITLE_FONT);
        layout.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        // Init Controls
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(25));

        int row = 0;
        for (String colorStr : FaceView.COLORS) {
            grid.add(new Rectangle(10, 10, Color.web(colorStr)), 0, row);
            grid.add(new Label(colorStr), 1, row);
            // Rotate ClockWise
            Button rotateCW = new Button("\u2B6E");
            rotateCW.setOnAction(e -> rotate(colorStr, true));
            grid.add(rotateCW, 2, row);
            // Rotate CounterClockWise
            Button rotateCCW = new Button("\u2B6F");
            rotateCCW.setOnAction(e -> rotate(colorStr, false));
            grid.add(rotateCCW, 3, row);
            row++;
        }
        layout.setRight(grid);
        return new Scene(layout);
    }

    private void rotate(String colorStr, boolean clockwise) {
        System.out.println("Rotate " + (clockwise ? "CW:" : "CCW:") + colorStr);
        Color color = Color.web(colorStr);

        Timeline timeline = new Timeline();
        for (int i = 0; i <= 90; i += updateDegreeIncrement) {
            int degree = i * (clockwise ? -1 : 1);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1.0 * animationDuration * i / 90), e -> {
                List<IFace> faces = rubikCube.getRotation(color, degree);
                List<Shape3DRectangle> shapes = faces.stream().map(Shape3DRectangle::new).toList();
                meshGroup.getChildren().clear();
                meshGroup.getChildren().addAll(shapes);
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        // final keyframe to update the cube model in the correct position
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(animationDuration), e -> {
            rubikCube.rotate(color, clockwise);
        }));
        timeline.play();
    }

    private void addCamera(SubScene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(true);
        perspectiveCamera.setNearClip(0.1);
        perspectiveCamera.setFarClip(10000.0);
        perspectiveCamera.setTranslateZ(-20);
        scene.setCamera(perspectiveCamera);
    }

    private void addMouseHandlers(SubScene scene) {
        scene.setOnMousePressed(me -> {
            anchorX = me.getSceneX();
            anchorY = me.getSceneY();
        });

        scene.setOnMouseDragged(me -> {
            rotateX.setAngle(rotateX.getAngle() + (me.getSceneY() - anchorY) / 2);
            rotateY.setAngle(rotateY.getAngle() - (me.getSceneX() - anchorX) / 2);
            anchorX = me.getSceneX();
            anchorY = me.getSceneY();
        });
    }
} 

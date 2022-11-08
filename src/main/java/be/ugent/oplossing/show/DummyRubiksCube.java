package be.ugent.oplossing.show;

import be.ugent.oplossing.model.IFace;
import be.ugent.oplossing.model.IRubikCube;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyRubiksCube implements IRubikCube {
    private List<IFace> faces;

    public DummyRubiksCube(List<IFace> faces) {
        this.faces = faces;
    }

    @Override
    public List<IFace> getAllFaces() {
        return faces;
    }

    @Override
    public List<IFace> getRotation(Color color, int degree) {
        Rotate rotate = new Rotate(degree, getRotationAxis(color));
        List<IFace> rotatedFaces = new ArrayList<>();
        for (IFace face : faces) {
            Point3D[] corners = Arrays.stream(face.getFaceCorners())
                    .map(rotate::transform)
                    .toArray(Point3D[]::new);
            rotatedFaces.add(new FaceView(face.getFaceColor(), corners));
        }
        return rotatedFaces;
    }

    @Override
    public void rotate(Color color, boolean clockwise) {
        faces = getRotation(color, clockwise ? 90 : -90);
    }

    private Point3D getRotationAxis(Color color) {
        if (color == Color.RED || color == Color.ORANGE) {
            return Rotate.X_AXIS;
        } else if (color == Color.GREEN || color == Color.BLUE) {
            return Rotate.Y_AXIS;
        } else {
            return Rotate.Z_AXIS;
        }
    }
}

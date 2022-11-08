package be.ugent.oplossing.model;

import javafx.scene.paint.Color;

import java.util.List;

public interface IRubikCube {
    List<IFace> getAllFaces();
    List<IFace> getRotation(Color color, int degree);
    void rotate(Color color, boolean clockwise);
}

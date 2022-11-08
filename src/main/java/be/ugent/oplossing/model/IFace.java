package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public interface IFace {
    Color getFaceColor();
    // Returns co(x,y,z) of the corners in (counter) clock-wise
    Point3D[] getFaceCorners();
}

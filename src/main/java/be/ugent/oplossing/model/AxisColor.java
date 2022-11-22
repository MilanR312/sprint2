package be.ugent.oplossing.model;

import javafx.scene.paint.Color;

import java.util.Map;

public enum AxisColor {
    wit("y", -2),
    geel("y", 2),
    rood("x", 2),
    blauw("z", -2),
    oranje("x", -2),
    groen("z", 2);

    final String axis;
    final int number;
    static final Map<Color, AxisColor> map = Map.of(
            Color.RED, rood,
            Color.WHITE, wit,
            Color.GREEN, groen,
            Color.YELLOW, geel,
            Color.BLUE, blauw,
            Color.ORANGE, oranje
    );

    AxisColor(String axis, int number) {
        this.axis = axis;
        this.number = number;
    }

    static AxisColor getAxisColorFromColor(Color color) {
        return map.get(color);
    }
}

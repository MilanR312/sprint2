package be.ugent.oplossing.model;

import javafx.scene.paint.Color;

import java.util.Map;

public enum AxisColor {
  wit(Color.WHITE, "y", -2 ),
    geel(Color.WHITE, "y", 2 ),
    rood(Color.WHITE, "x", 2 ),
    blauw(Color.WHITE, "z", -2 ),
    oranje(Color.WHITE, "x", -2 ),
    groen(Color.GREEN, "z", 2);

  Color color;
  String axis;
  int number;
  static Map<Color, AxisColor> map = Map.of(
          Color.RED, rood,
          Color.WHITE, wit,
          Color.GREEN, groen,
          Color.YELLOW, geel,
          Color.BLUE, blauw,
          Color.ORANGE, oranje
  );

    AxisColor(Color color, String axis, int number) {
        this.color = color;
        this.axis = axis;
        this.number = number;
    }

    static AxisColor getAxisColorFromColor(Color color) {
        return map.get(color);
    }
}

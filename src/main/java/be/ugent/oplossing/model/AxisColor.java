package be.ugent.oplossing.model;

import javafx.scene.paint.Color;

import java.util.Map;

public enum AxisColor {
  wit(Color.WHITE, "X", 2 ),
    geel(Color.WHITE, "X", 2 ),
    rood(Color.WHITE, "X", 2 ),
    blauw(Color.WHITE, "X", 2 ),
    oranje(Color.WHITE, "X", 2 ),
    groen(Color.GREEN, "X", 2);

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

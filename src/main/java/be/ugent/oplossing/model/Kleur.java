package be.ugent.oplossing.model;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class Kleur {

    private static Map<String,Color> map = new HashMap<>() {{
        put("rood",Color.RED);
        put("oranje",Color.ORANGE);
        put("geel",Color.YELLOW);
        put("groen",Color.GREEN);
        put("blauw",Color.BLUE);
        put("wit",Color.WHITE);
        put("zwart",Color.BLACK);
    }};;

    public static Color getKleur(String naam){
        return map.get(naam);
    }


}

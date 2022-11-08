package be.ugent.oplossing.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Vlakje implements IFace {
    private Color kleur;
    private List<Hoekpunt> hoekpunten;

    // enkel nodig in toString()-methode
    private Map<Color,String> kleurenNamen = new HashMap<Color,String>(Map.ofEntries(
            entry(Color.RED, "rood"),
            entry(Color.GREEN, "groen"),
            entry(Color.YELLOW, "geel"),
            entry(Color.WHITE, "wit"),
            entry(Color.BLACK, "zwart"),
            entry(Color.ORANGE, "oranje"),
            entry(Color.BLUE, "blauw")
    ));

    // De constructor krijgt alleen de kleur mee.
    // Om hoekpunten toe te voegen, schrijf je een methode.
    public Vlakje(String kleur){
        this.kleur = Kleur.getKleur(kleur);
        this.hoekpunten = new ArrayList<>();
    }
    public Vlakje(Color kleur){
        this.kleur = kleur;
        this.hoekpunten = new ArrayList<>();
    }

    // Hoekpunten moeten in wijzerzin of tegenwijzerzin in de list staan
    // om het tekenen juist te laten verlopen.
    // Daar werd al voor gezorgd in de klasse Kubusje
    // (zie commentaar bij static Hoekpunt[] ptnTovCentrum).
    // Wees hier dus pragmatisch in: ga niet zitten rekenen of controleren;
    // als je bij constructie van de kubus gestructureerd te werk gaat,
    // is het al in orde.
    public void voegPuntToe(Hoekpunt p){
        hoekpunten.add(p);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("");
        builder.append(kleurenNamen.get(kleur));
        for(Hoekpunt p : hoekpunten){
            builder.append("\t"+p);
        }
        return builder.toString();
    }


    @Override
    public Color getFaceColor() {
        return kleur;
    }

    @Override
    public Point3D[] getFaceCorners() {
        Point3D[] ptn = new Point3D[hoekpunten.size()];
        for(int i = 0; i<ptn.length; i++){
            ptn[i] = hoekpunten.get(i).getLocation();
        }
        return ptn;
    }

}

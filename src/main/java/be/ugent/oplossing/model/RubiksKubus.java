package be.ugent.oplossing.model;

import be.ugent.oplossing.show.RubiksReader;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RubiksKubus implements IRubikCube{

    private List<Kubusje> kubusjes;

    // Alle gegevens voor de 27 kubusjes werden in een excel-bestand ingevuld en bewaard.
    // Excel biedt dankzij z'n kolommen een duidelijk overzicht, er kan heel gestructureerd gewerkt
    // (en gecontroleerd) worden.
    // Inlezen van het bestand werd afgekeken van de gegeven code in de show-map.
    // (Moet niet gekend zijn voor op het examen.)
    public RubiksKubus() throws FileNotFoundException {
        kubusjes = new ArrayList<>();

        Scanner sc = new Scanner(new File(Objects.requireNonNull(RubiksReader.class.getResource("kubussen_xyz.csv")).getFile()));
        while(sc.hasNext()){
            Scanner sc_ = new Scanner(sc.nextLine());
            sc_.useDelimiter(";");
            int x = sc_.nextInt();
            int y = sc_.nextInt();
            int z = sc_.nextInt();
            String[] kleuren = new String[6];
            for(int i=0; i<6; i++){
                kleuren[i] = sc_.next();
            }
            kubusjes.add(new Kubusje(x,y,z,kleuren));
        }

    }


    // Dit kan je gebruiken om zelf te testen, zolang de view er nog niet is.
    // Layout niet handig? Pas zelf aan.
    public String toString(){
        // kan je later met streams doen
        String[] strings = new String[kubusjes.size()];
        int i=0;
        for(Kubusje kubus : kubusjes){
            strings[i++] = kubus.toString();
        }
        return String.join("\n",strings);
    }



    // Deze methode wordt gebruikt door het showteam om de View te maken.
    // Meer is er niet nodig (in tegenstelling tot wat in sprint0 aangekondigd werd,
    // dus geen onderscheid tussen zichtbare en onzichtbare vlakjes).
    @Override
    public List<IFace> getAllFaces() {
        List<IFace> list = new ArrayList<>();
        for(Kubusje kubus : kubusjes){
            for(Vlakje vlak : kubus.getVlakjes()){
                list.add(vlak);
            }
        }
        return list;
    }

    @Override
    public List<IFace> getRotation(Color color, int degree) {
        System.out.println("RubiksKubus::getRotation nog niet geimplementeerd");
        return null; // vervang
    }

    @Override
    public void rotate(Color color, boolean clockwise) {
        System.out.println("RubiksKubus::rotate nog niet geimplementeerd");
        // vervang
    }

    public List<Kubusje> getKubusjes(){
        return kubusjes;
    }

}


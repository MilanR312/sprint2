package be.ugent.oplossing.model;

import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        test_KLASSE_ZijvlakKubusje();
        test_KLASSE_Kubusje();
        test_KLASSE_RubiksKubus();

        test_ROTEREN();
    }

    public static void test_ROTEREN() {
        try {
            RubiksKubus rubik = new RubiksKubus();
            System.out.println(rubik);
            for (int i = 1; i <= 4; i++) {
                System.out.println("\n**********\n************\nNA " + i + "x 30° ROTEREN VAN ROOD CLOCKWISE");
                List<IFace> list = rubik.getRotation(Color.RED, 30);
                for(int k=0; k<5; k++){
                    System.out.println(list.get(k));
                }
                System.out.println("\n**********\n************\nNA " + i + "x ROTEREN VAN ROOD CLOCKWISE");
                rubik.rotate(Color.RED, true);
                System.out.println(rubik);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test_KLASSE_ZijvlakKubusje() {
        System.out.println("test voor de klasse Vlakje.");
        Vlakje vlak = new Vlakje("groen");
        vlak.voegPuntToe(new Hoekpunt(5, 6, 7));
        vlak.voegPuntToe(new Hoekpunt(0, 1, 2));
        vlak.voegPuntToe(new Hoekpunt(2, 3, 4));
        vlak.voegPuntToe(new Hoekpunt(4, 5, 6));
        vlak.voegPuntToe(new Hoekpunt(10, 11, 12));
        System.out.println(vlak);
    }

    public static void test_KLASSE_Kubusje() {
        System.out.println("test voor de klasse Kubusje.");
        Kubusje kubus = new Kubusje(2, 2, 2, List.of("groen", "rood", "geel", "zwart", "zwart", "zwart").toArray(new String[0]));
        System.out.println(kubus);
    }

    public static void test_KLASSE_RubiksKubus() {
        System.out.println("test voor de klasse RubiksKubus.");
        RubiksKubus kubus = null;
        try {
            kubus = new RubiksKubus();
            System.out.println(kubus);
        } catch (FileNotFoundException e) {
            System.out.println("foutje, bestand niet gevonden");
        }
    }


}

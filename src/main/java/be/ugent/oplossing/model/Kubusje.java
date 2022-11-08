package be.ugent.oplossing.model;

import javafx.geometry.Point3D;

//       ^ Y
//       |
//       |
//       ---------->  X
//     /
//    /
//   Z

public class Kubusje {

    private Hoekpunt centrum;
    private Hoekpunt[] hoekpunten;
    private Vlakje[] vlakjes;

    // Onderstaande constanten zorgen ervoor dat code leesbaar en snel controleerbaar is.
    // Ze bepalen de volgorde waarin de vlakken opgeslagen worden in de array 'vlakjes'.
    // Eerst het vlakje langs de positieve X-as,
    // dan het vlakje langs de positieve Y-as, dan positieve Z-as,
    // dan negatieve X-as, negatieve Y-as, negatieve Z-as.
    // Deze richtingen hebben ook te maken met de kleuren (zie sprint 2:
    // het middelste gekleurde vlakje van een draaivlak (set van 9 kubusjes)
    // blijft altijd op dezelfde as zitten, heeft altijd dezelfde kleur).
    // Wil je al deze informatie (kleur, richting/as, volgorde,...)
    // in een aparte klasse bundelen? Dat is een mogelijkheid.
    private static int XPOS = 0;
    private static int YPOS = 1;
    private static int ZPOS = 2;
    private static int XNEG = 3;
    private static int YNEG = 4;
    private static int ZNEG = 5;

    private static Hoekpunt[] ptnTovCentrum = new Hoekpunt[]{
            new Hoekpunt(-1, -1, 1),
            new Hoekpunt(-1, 1, 1),
            new Hoekpunt(1, 1, 1),
            new Hoekpunt(1, -1, 1),
            new Hoekpunt(1, -1, -1),
            new Hoekpunt(1, 1, -1),
            new Hoekpunt(-1, 1, -1),
            new Hoekpunt(-1, -1, -1)};

    // Merk op: de bovenstaande hoekpunten werden in een specifieke volgorde opgesomd.
    // Als de hoekpunten in die volgorde overlopen worden en dus aan vlakken toegekend worden,
    // dan zullen ze altijd in wijzer- of tegenwijzerzin toegevoegd worden - maar nooit 'kriskras'.
    //                      $**********
    //                    /         / *
    //                   ***********  *
    //                   *         *  *
    //                   *         *  *
    //                   *         * *
    //                   @---------*
    // Start bij @, volg de sterretjes, en voeg elk hoekpunt dat je tegenkomt in die volgorde toe.
    // Als je bij $ uitkomt voeg je nog het achterste, onzichtbare punt toe.


    // De constructor krijgt het centrum mee,
    // gevolgd door de kleuren in volgorde volgens X Y Z X' Y' Z' as.
    // (met X' de negatieve X-as, Y' de negatieve Y-as, ...)
    // De constructor vult dan alle andere informatie juist in.
    // GELDT ALLEEN VOOR KUBUSJES DIE NOG 'RECHT' STAAN,
    // een Kubusje wordt alleen geconstrueerd bij het begin van het programma.
    public Kubusje(double centrum_x, double centrum_y, double centrum_z, String[] kleuren) {
        this.centrum = new Hoekpunt(centrum_x, centrum_y, centrum_z); // int zal automatisch naar double omgezet worden
        hoekpunten = new Hoekpunt[8];
        vlakjes = new Vlakje[6];
        initialiseerPuntenEnVlakken(kleuren);
    }



    private void initialiseerPuntenEnVlakken(String[] kleuren) {
        // maak eerst 6 vlakken aan, met de juiste kleuren
        for(int i=0; i<kleuren.length; i++){
            vlakjes[i] = new Vlakje(kleuren[i]);
        }

        // maak daarna 8 hoekpunten aan;
        // bij elk hoekpunt kijk je na of het toegevoegd moet worden aan
        //     rechter- dan wel linkervlak (XPOS-as of XNEG-as)
        //     boven- dan wel ondervlak (YPOS-as of YNEG-as)
        //     voor- dan wel achtervlak (ZPOS-as of ZNEG-as)
        // (en doe dat dan ook)
        // (Met wat wiskunde en een extra klasse VergelijkingVanVlak kan het ook
        // zonder if/else-structuur, maar neem niet teveel hooi ineens op je vork.)
        for(int i=0; i<ptnTovCentrum.length; i++){
            hoekpunten[i] = centrum.plus(ptnTovCentrum[i]);
            if(ptnTovCentrum[i].getX() == 1){
                vlakjes[XPOS].voegPuntToe(hoekpunten[i]);
            }
            else {
                vlakjes[XNEG].voegPuntToe(hoekpunten[i]);
            }
            if(ptnTovCentrum[i].getY()==1){
                vlakjes[YPOS].voegPuntToe(hoekpunten[i]);
            }
            else{
                vlakjes[YNEG].voegPuntToe(hoekpunten[i]);
            }
            if(ptnTovCentrum[i].getZ()==1){
                vlakjes[ZPOS].voegPuntToe(hoekpunten[i]);
            }
            else{
                vlakjes[ZNEG].voegPuntToe(hoekpunten[i]);
            }
        }

    }


    // Deze methode is handig bij uittesten in Main.java:
    //    (1) Aankondiging dat hier de info van een Kubusje komt
    //    (2) coordinaten van het centrum
    //    (3) alle zijvlakjes (waarbij een zijvlakje zelf weet hoe 't zich omzet in een String)
    public String toString() {

        StringBuilder builder = new StringBuilder("Kubusje:\n");
        builder.append(centrum.toString());
        for(Vlakje vlak : vlakjes){
            builder.append("\n\t"+vlak);
        }
        return builder.toString();
    }

    public Vlakje[] getVlakjes(){
        return vlakjes;
    }


    public Point3D getCentrum() {return centrum.getLocation();}


}

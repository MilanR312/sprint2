package be.ugent.oplossing.model;

import javafx.geometry.Point3D;

import java.util.Locale;

public class Hoekpunt {
    Point3D loc; // 'loc' van locatie

    public Hoekpunt(double x, double y, double z) {
        loc = new Point3D(x, y, z);
    }

    // Gebruik deze methode om twee punten in 3D 'op te tellen' (vectorrekenen).
    public Hoekpunt plus(Hoekpunt ander) {
        return new Hoekpunt(loc.getX() + ander.loc.getX(), loc.getY() + ander.loc.getY(), loc.getZ() + ander.loc.getZ());
    }

    public double getX() {
        return loc.getX();
    }

    public double getY() {
        return loc.getY();
    }

    public double getZ() {
        return loc.getZ();
    }

    // Deze getter zou een kopie kunnen geven
    // om memory-violations te voorkomen (zie later in theorie),
    // maar veel zal dat niet helpen:
    // een Point3D-object is sowieso niet aanpasbaar.
    public Point3D getLocation() {
        return loc;
    }

    // We willen hoekpunten een nieuwe locatie kunnen geven.
    // Dat zal gebeuren bij het roteren van kubusjes.
    public void setLocation(Point3D loc) {
        this.loc = loc;
    }


    // Deze methode is handig voor de testen in Main.java.
    // Nut van de if-test:
    // als de coördinaten allemaal geheel zijn, wordt ".0" weggelaten;
    // anders niet.
    public String toString() {
        if ((int) loc.getX() == loc.getX() && (int) loc.getY() == loc.getY() && (int) loc.getZ() == loc.getZ()) {
            return "(" + (int) loc.getX() + "," + (int) loc.getY() + "," + (int) loc.getZ() + ")";
        }
        Locale.setDefault(new Locale("en", "GB"));
        return String.format("(%.2f,%.2f,%.2f)", loc.getX(), loc.getY(), loc.getZ());
    }


    private double[][] calculateRotation(double degrees, String axis) {
        var rad = Math.toRadians(degrees);
        var AxisRotation = new Matrix(axis, rad);
        var hoekpunt = new Matrix(new double[][]{{this.getX()}, {this.getY()}, {this.getZ()}});
        return AxisRotation.multiply(hoekpunt).matrix;
    }

    public Hoekpunt copyAndRotate(double degrees, String axis) {
        var result = calculateRotation(degrees, axis);
        return new Hoekpunt(result[0][0], result[1][0], result[2][0]);
    }

    public void rotate(double degrees, String axis) {
        var result = calculateRotation(degrees, axis);
        setLocation(new Point3D(Math.round(result[0][0]), Math.round(result[1][0]), Math.round(result[2][0])));
    }

    public double getAxis(String axis) {
        return switch (axis) {
            case "x" -> this.getX();
            case "y" -> this.getY();
            case "z" -> this.getZ();
            default -> throw new IllegalArgumentException("Unknown axis: " + axis);
        };
    }
}
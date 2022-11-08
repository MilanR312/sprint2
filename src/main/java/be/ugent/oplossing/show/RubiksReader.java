package be.ugent.oplossing.show;

import be.ugent.oplossing.model.IFace;
import be.ugent.oplossing.model.IRubikCube;
import javafx.geometry.Point3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RubiksReader {
    public static IRubikCube ReadFromFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(Objects.requireNonNull(RubiksReader.class.getResource(filename)).getFile()));
        List<IFace> faces = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            faces.add(parseLine(line));
        }
        return new DummyRubiksCube(faces);
    }


    private static IFace parseLine(String line) {
        String[] splitted = line.split(";");
        Point3D[] corners = Arrays.stream(splitted)
                .skip(1) // skip the color
                .map(corner -> corner.substring(1, corner.length()-1)) // remove leading and trailing brackets
                .map(corner -> corner.split(","))
                .map(corner -> new Point3D(
                        Integer.parseInt(corner[0]),
                        Integer.parseInt(corner[1]),
                        Integer.parseInt(corner[2])))
                .toArray(Point3D[]::new);
        return new FaceView(splitted[0], corners);
    }
}

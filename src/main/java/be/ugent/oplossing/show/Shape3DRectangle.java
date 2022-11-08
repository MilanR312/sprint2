package be.ugent.oplossing.show;

import be.ugent.oplossing.model.IFace;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import java.util.Arrays;
import java.util.stream.DoubleStream;


/**
 * points:
 * 0      3
 *  -------   texture:
 *  |\    |  0,0    1,0
 *  | \   |    -------
 *  |  \  |    |     |
 *  |   \ |    |     |
 *  |    \|    -------
 *  -------  0,1    1,1
 * 1      2
 *
 * texture[0] 0,0 maps to vertex 0
 * texture[1] 0,1 maps to vertex 1
 * texture[2] 1,1 maps to vertex 2
 * texture[3] 1,0 maps to vertex 3
 *
 * Two triangles define rectangular faces:
 * p0, t0, p1, t1, p2, t2 // First triangle of a textured rectangle
 * p0, t0, p2, t2, p3, t3 // Second triangle of a textured rectangle
 **/

public class Shape3DRectangle extends MeshView {
    private static float SIZE = 2;

    // Points need to be supplied in (counter) clockwise order
    public Shape3DRectangle(IFace face) {
        super();
        float[] points = doubleArrayToFloat(Arrays.stream(face.getFaceCorners())
                .flatMapToDouble(corner -> DoubleStream.of(corner.getX(), corner.getY(), corner.getZ()))
                .toArray());

        float[] texCoords = {
                1, 1, // idx t0
                1, 0, // idx t1
                0, 1, // idx t2
                0, 0  // idx t3
        };
        int[] faces = {
                0, 0, 1, 1, 2, 2, // p0, t0, p1, t1, p2, t2
                0, 0, 2, 2, 3, 3  // p0, t0, p2, t2, p3, t3
        };
        int[] smooth = {
                0, 0 // both triangles on 1 surface
        };

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().setAll(points);
        mesh.getTexCoords().setAll(texCoords);
        mesh.getFaces().setAll(faces);
        mesh.getFaceSmoothingGroups().addAll(smooth);

        setMesh(mesh);
        setMaterial(new PhongMaterial(face.getFaceColor()));
        setCullFace(CullFace.NONE);
    }

    private float[] doubleArrayToFloat(double [] d) {
        float[] f = new float[d.length];
        for (int i=0; i<d.length; i++) {
            f[i] = (float) d[i];
        }
        return f;
    }
}

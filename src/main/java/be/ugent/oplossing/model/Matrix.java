package be.ugent.oplossing.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.lang.Iterable;

public class Matrix {
    double[][] matrix;
    int width;
    int height;
    private static final Map<String, Function<Double, double[][]>> rotationMatrices = Map.of(
            "x", rad -> new double[][]{
                    {1.0, 0.0, 0.0}
                    , {0.0, Math.cos(rad), -Math.sin(rad)}
                    , {0.0, Math.sin(rad), Math.cos(rad)}
            },
            "y", rad -> new double[][]{
                    {Math.cos(rad), 0.0, Math.sin(rad)}
                    , {0.0, 1.0, 0.0}
                    , {-Math.sin(rad), 0.0, Math.cos(rad)}
            },
            "z", rad -> new double[][]{
                    {Math.cos(rad), -Math.sin(rad), 0.0}
                    , {Math.sin(rad), Math.cos(rad), 0.0}
                    , {0.0, 0.0, 1.0}
            }
    );

    public Matrix(int width, int height) {
        this.matrix = new double[width][height];
        this.width = width;
        this.height = height;
    }

    public Matrix(double[][] arr) {
        this.matrix = arr;
        this.width = arr.length;
        this.height = arr[0].length;
    }

    public Matrix(String axis, double rad) {
        this.matrix = rotationMatrices.get(axis).apply(rad);
        this.width = matrix.length;
        this.height = matrix[0].length;
    }

    public double multiplyMatricesCell(Matrix other, int row, int col) {
        double cell = 0;
        for (int i = 0; i < other.width; i++) {
            cell += this.matrix[row][i] * other.matrix[i][col];
        }
        return cell;
    }

    public Matrix multiply(Matrix other) {
        Matrix output = new Matrix(this.width, other.height);
        for (int row = 0; row < output.width; row++) {
            for (int col = 0; col < output.height; col++) {
                output.matrix[row][col] = multiplyMatricesCell(other, row, col);
            }
        }
        return output;
    }

}

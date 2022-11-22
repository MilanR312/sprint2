package be.ugent.oplossing.model;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import java.lang.Iterable;

public class Matrix implements Iterable<Double[]>{
    Double [][] matrix;
    int width, height;
    double value = 0;
    static Map<String, Function<Double, Double[][]>> predefs = Map.of(
        "x", c -> new Double[][]{
            {1.0, 0.0, 0.0}
            ,{0.0, Math.cos(c), -Math.sin(c)}
            ,{0.0, Math.sin(c), Math.cos(c)}
        },
        "y", c -> new Double[][]{
            {Math.cos(c), 0.0, Math.sin(c)}
               ,{0.0            , 1.0, 0.0}
               ,{-Math.sin(c), 0.0, Math.cos(c)}
        },
        "z", c -> new Double[][]{
            {Math.cos(c), -Math.sin(c), 0.0}
               ,{Math.sin(c),  Math.cos(c), 0.0}
               ,{0.0,             0.0,              1.0}
        }
    );
    Matrix(int width, int height){
        this.width = width;
        this.height = height;
        matrix = new Double[width][height];
    }
    Matrix(Double [][] arr){
        width = arr.length;
        height = arr[0].length;
        matrix = arr;
    }
    Matrix(String predef, double val){
        matrix = predefs.get(predef).apply(val);
        width = matrix.length;
        height = matrix[0].length;
        value = val;
    }
    double multiplyMatricesCell(Matrix other, int row, int col){
        double cell = 0;
        for(int i = 0; i < other.width; i++){
            cell += this.matrix[row][i] * other.matrix[i][col];
        }
        return cell;
    }
    Matrix multiply(Matrix other){
        Matrix output = new Matrix(this.width, other.height);
        for (int row = 0; row < output.width; row++) {
            for (int col = 0; col < output.height; col++) {
                output.matrix[row][col] = multiplyMatricesCell(other, row, col);
            }
        }
        return output;
    }
    @Override
    public Iterator<Double[]> iterator(){
        return new Iterator<Double[]>() {
            private int pos;
            @Override
            public boolean hasNext(){
                return pos < width;
            }
            @Override
            public Double[] next(){
                return matrix[pos++];
            }
        };
    }
    
}

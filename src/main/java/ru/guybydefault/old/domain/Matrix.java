package ru.guybydefault.old.domain;

import ru.guybydefault.old.domain.operations.Minus;
import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

import java.util.ArrayList;

public class Matrix implements MatrixExpression {

    private final int height;
    private final int width;
    private final OrdinaryExpression[][] matrix;

    public Matrix(int height, int width, ArrayList<ArrayList<OrdinaryExpression>> m) {
        this.height = height;
        this.width = width;
        this.matrix = transformToArray(m);
    }

    public static Matrix getZeroMatrix(int height, int width) {
        ArrayList<ArrayList<OrdinaryExpression>> rows = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ArrayList<OrdinaryExpression> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new Value(ValueType.CONST, "0"));
            }
            rows.add(row);
        }
        return new Matrix(height, width, rows);
    }

    public Matrix inverse() {
        ArrayList<ArrayList<OrdinaryExpression>> rows = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ArrayList<OrdinaryExpression> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new Minus());
            }
            rows.add(row);
        }
    }

    public OrdinaryExpression[][] getMatrix() {
        return matrix;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public MatrixExpression evaluate() {
        return this;
    }

    @Override
    public OrdinaryExpression getEvaluatedCell(int row, int column) {
        return getMatrix()[row][column].evaluate();
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }

    private OrdinaryExpression[][] transformToArray(ArrayList<ArrayList<OrdinaryExpression>> list) {
        OrdinaryExpression[][] array = new OrdinaryExpression[list.size()][list.get(0).size()];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = list.get(i).get(j);
            }
        }
        return array;
    }
}

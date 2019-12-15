package ru.guybydefault.domain;

import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

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
    public MatrixExpression evaluate(Evaluator visitor) {
      return visitor.evaluate(this);
    }

    @Override
    public OrdinaryExpression getEvaluatedCell(int row, int column) {
        return getMatrix()[row][column];
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

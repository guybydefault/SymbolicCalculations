package ru.guybydefault.domain;

import java.util.ArrayList;

public class Matrix extends Expression {

    private final int height;
    private final int width;
    private final ArrayList<ArrayList<Expression>> matrix;

    public Matrix(int h, int w, ArrayList<ArrayList<Expression>> m) {
        this.height = h;
        this.width = w;
        this.matrix = m;
    }

    public ArrayList<ArrayList<Expression>> getMatrix() {
        return matrix;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        String result = "";

        return result;
    }
}

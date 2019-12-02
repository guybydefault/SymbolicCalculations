package ru.guybydefault.domain;

import java.util.ArrayList;

public class Matrix implements Expression {

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
        StringBuilder result = new StringBuilder("<mo>[</mo>\n"
                + "<mtable>\n");
        for (int i = 0; i < height; i++) {
            result.append("<mtr>\n");
            for (int j = 0; j < width; j++) {
                result.append("<mtd>\n").append(matrix.get(i).get(j).toString()).append("</mtd>\n");
            }
            result.append("</mtr>\n");
        }
        return result.append("</mtable>\n<mo>]</mo>\n").toString();
    }

    @Override
    public Expression simplify() {
        return null;
    }
}

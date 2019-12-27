package ru.guybydefault.old.services;

import ru.guybydefault.old.domain.operations.Divide;
import ru.guybydefault.old.domain.operations.Multiplicate;
import ru.guybydefault.old.domain.operations.Subtract;
import ru.guybydefault.old.domain.operations.Sum;
import ru.guybydefault.old.domain.operations.matrix.MatrixMultiplicate;
import ru.guybydefault.old.domain.operations.matrix.MatrixSubtract;
import ru.guybydefault.old.domain.operations.matrix.MatrixSum;

public class MathMLSerializer implements Serializer {

    @Override
    public String serialize(MatrixSum sum) {
       return serializeDivOperation(sum.getArg1(), sum.getArg2());
    }

    @Override
    public String serialize(Sum sum) {
        return serializeOperation(sum.getArg1(), sum.getArg2(), "+");

    }

    @Override
    public String serialize(Divide divide) {
        return serializeDivOperation(divide.getArg1(), divide.getArg2());
    }

    @Override
    public String serialize(MatrixSubtract matrixSubtract) {
        return serializeOperation(matrixSubtract.getArg1(), matrixSubtract.getArg2(), "-");
    }

    @Override
    public String serialize(MatrixMultiplicate matrixMultiplicate) {
        return serializeOperation(matrixMultiplicate.getArg1(), matrixMultiplicate.getArg2(), "*");
    }

    @Override
    public String serialize(Multiplicate multiplicate) {
        return serializeOperation(multiplicate.getArg1(), multiplicate.getArg2(), "*");
    }

    @Override
    public String serialize(Value value) {
        return null;
    }

    @Override
    public String serialize(Matrix matrix) {
        StringBuilder result = new StringBuilder("<mfenced separators=\"\" open=\"[\" close=\"]\">\n"
                + "<mtable>\n");
        for (int i = 0; i < matrix.getHeight(); i++) {
            result.append("<mtr>\n");
            for (int j = 0; j < matrix.getWidth(); j++) {
                result.append("<mtd>\n").append(matrix.getMatrix()[i][j].toString()).append("</mtd>\n");
            }
            result.append("</mtr>\n");
        }
        return result.append("</mtable>\n</mfenced>\n").toString();
    }

    @Override
    public String serialize(Function function) {
        return function.getType().toString(function.getArg1(), function.getArg2());;
    }

    @Override
    public String serialize(UnaryOp unaryOp) {
        return null;
    }

    @Override
    public String serialize(Subtract subtract) {
        return null;
    }

    private String serializeDivOperation(Expression arg1, Expression arg2) {
        return "<mfrac>\n"
                + "<mrow>\n"
                + arg1.toString()
                + "</mrow>\n"
                + "<mrow>\n"
                + arg2.toString()
                + "</mrow>\n"
                + "</mfrac>\n";
    }
    private String serializeOperation(Expression arg1, Expression arg2, String sign) {
        return "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                + arg1.serialize(this)
                + "<mo>" + sign + "</mo>\n"
                + arg2.serialize(this)
                + "</mfenced>\n";
    }
}

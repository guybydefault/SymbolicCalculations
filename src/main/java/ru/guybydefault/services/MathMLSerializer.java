package ru.guybydefault.services;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.operations.Divide;
import ru.guybydefault.domain.operations.Multiplicate;
import ru.guybydefault.domain.operations.Sum;
import ru.guybydefault.domain.operations.matrix.MatrixMultiplicate;
import ru.guybydefault.domain.operations.matrix.MatrixSubtract;
import ru.guybydefault.domain.operations.matrix.MatrixSum;

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

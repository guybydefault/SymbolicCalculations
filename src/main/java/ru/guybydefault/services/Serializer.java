package ru.guybydefault.services;

import ru.guybydefault.domain.Function;
import ru.guybydefault.domain.Matrix;
import ru.guybydefault.domain.UnaryOp;
import ru.guybydefault.domain.Value;
import ru.guybydefault.domain.operations.Divide;
import ru.guybydefault.domain.operations.Multiplicate;
import ru.guybydefault.domain.operations.Subtract;
import ru.guybydefault.domain.operations.Sum;
import ru.guybydefault.domain.operations.matrix.MatrixMultiplicate;
import ru.guybydefault.domain.operations.matrix.MatrixSubtract;
import ru.guybydefault.domain.operations.matrix.MatrixSum;

public interface Serializer {
    String serialize(MatrixSum sum);
    String serialize(Sum sum);
    String serialize(Divide divide);

    String serialize(MatrixSubtract matrixSubtract);

    String serialize(MatrixMultiplicate matrixMultiplicate);

    String serialize(Multiplicate multiplicate);

    String serialize(Value value);

    String serialize(Matrix matrix);

    String serialize(Function function);

    String serialize(UnaryOp unaryOp);

    String serialize(Subtract subtract);
}

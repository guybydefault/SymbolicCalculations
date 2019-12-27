package ru.guybydefault.old.services;

import ru.guybydefault.old.domain.Function;
import ru.guybydefault.old.domain.Matrix;
import ru.guybydefault.old.domain.UnaryOp;
import ru.guybydefault.old.domain.Value;
import ru.guybydefault.old.domain.operations.Divide;
import ru.guybydefault.old.domain.operations.Multiplicate;
import ru.guybydefault.old.domain.operations.Subtract;
import ru.guybydefault.old.domain.operations.Sum;
import ru.guybydefault.old.domain.operations.matrix.MatrixMultiplicate;
import ru.guybydefault.old.domain.operations.matrix.MatrixSubtract;
import ru.guybydefault.old.domain.operations.matrix.MatrixSum;

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

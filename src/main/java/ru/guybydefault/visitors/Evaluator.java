package ru.guybydefault.visitors;

import ru.guybydefault.domain.*;
import ru.guybydefault.domain.operations.BinaryOp;
import ru.guybydefault.domain.operations.Multiplicate;
import ru.guybydefault.domain.operations.matrix.MatrixMultiplicate;
import ru.guybydefault.domain.operations.matrix.MatrixSubtract;
import ru.guybydefault.domain.operations.matrix.MatrixSum;

public interface Evaluator {

     default Expression evaluate(Matrix matrix) {
          return matrix;
     }

     default Expression evaluate(UnaryOp unaryOp) {
          return unaryOp;
     }

     default Expression evaluate(BinaryOp binaryOp) {
          return binaryOp;
     }

     default Expression evaluate(Value value) {
          return value;
     }

     default Expression evaluate(Function function) {
          return function;
     }

     default Expression evaluate(MatrixMultiplicate matrixMultiplicate) {
          return matrixMultiplicate;
     }

     default Expression evaluate(MatrixSubtract matrixSubtract) {
          return matrixSubtract;
     };

     default Expression evaluate(MatrixSum matrixSum) {
          return matrixSum;
     };

     default Expression evaluate(Multiplicate multiplicate) { return multiplicate; }
}

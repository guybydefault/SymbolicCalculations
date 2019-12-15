package ru.guybydefault.visitors;

import ru.guybydefault.domain.*;
import ru.guybydefault.domain.operations.BinaryOp;
import ru.guybydefault.domain.operations.Multiplicate;
import ru.guybydefault.domain.operations.matrix.MatrixMultiplicate;
import ru.guybydefault.domain.operations.matrix.MatrixSubtract;
import ru.guybydefault.domain.operations.matrix.MatrixSum;

public interface Evaluator {

     default MatrixExpression evaluate(Matrix matrix) {
          return matrix;
     }

     default OrdinaryExpression evaluate(UnaryOp unaryOp) {
          return unaryOp;
     }

     default OrdinaryExpression evaluate(BinaryOp binaryOp) {
          return binaryOp;
     }

     default OrdinaryExpression evaluate(Value value) {
          return value;
     }

     default OrdinaryExpression evaluate(Function function) {
          return function;
     }

     default MatrixExpression evaluate(MatrixMultiplicate matrixMultiplicate) {
          return matrixMultiplicate;
     }

     default MatrixExpression evaluate(MatrixSubtract matrixSubtract) {
          return matrixSubtract;
     };

     default MatrixExpression evaluate(MatrixSum matrixSum) {
          return matrixSum;
     };

     default OrdinaryExpression evaluate(Multiplicate multiplicate) { return multiplicate; }
}

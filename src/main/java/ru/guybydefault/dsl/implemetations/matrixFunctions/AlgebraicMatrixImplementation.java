package ru.guybydefault.dsl.implemetations.matrixFunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;
import ru.guybydefault.dsl.implemetations.listfunctions.AbstractListFunctionImplementation;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AlgebraicMatrixImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[]{MatrixFunctions.AlgrebraicMatrix};

    public AlgebraicMatrixImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> matrix) {
        //inp = expr + items is matrix (list of expressions with Head List)
        boolean isSquareMatrix = matrix.stream()
                .map(x -> x.visit(new AsExpressionVisitor()))
                .filter(Objects::nonNull)
                .filter(x -> x.getHead() == ListFunctions.List)
                .filter(x -> x.getArguments().size() == matrix.size())
                .count() == matrix.size();
        if (!isSquareMatrix) {
            throw new IllegalArgumentException("Matrix of algebraic components can be computed only to a square matrix");
        }

        if (matrix.size() == 1) throw new IllegalArgumentException();

        List<Symbol> newMatrix = new LinkedList<>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Symbol> matrixRow = new LinkedList<>();
            for (int k = 0; k < matrix.get(i).visit(AsExpressionVisitor.getInstance()).getArguments().size(); k++) {
                Symbol minor = DeterminerImplementation.getDeterminer(getMatrixExcludingRowAndColumn(matrix, i, k));
                if ((i + k) % 2 == 0) {
                    matrixRow.add(minor);
                } else {
                    matrixRow.add(new Expression(ArithmeticFunctions.Minus, minor));
                }
            }
            newMatrix.add(new Expression(ListFunctions.List, matrixRow));
        }
        return new Expression(ListFunctions.List, newMatrix);
    }

    private List<Symbol> getMatrixExcludingRowAndColumn(List<Symbol> matrix, int row, int column) {
        List<Symbol> newMatrixRows = new LinkedList<>();
        for (int i = 0; i < matrix.size(); i++) {
            if (i != row) {
                LinkedList<Symbol> rowSymbolList = new LinkedList<>();
                Expression rowExpression = new Expression(ListFunctions.List, rowSymbolList);
                for (int k = 0; k < matrix.get(i).visit(AsExpressionVisitor.getInstance()).getArguments().size(); k++) {
                    if (k != column) {
                        rowSymbolList.add(matrix.get(i).visit(AsExpressionVisitor.getInstance()).getArguments().get(k));
                    }
                }
                newMatrixRows.add(rowExpression);
            }
        }
        return newMatrixRows;
    }
}

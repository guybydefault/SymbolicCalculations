package ru.guybydefault.dsl.implemetations.matrixFunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;
import ru.guybydefault.dsl.implemetations.listfunctions.AbstractListFunctionImplementation;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeterminerImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {MatrixFunctions.Determiner};

    public DeterminerImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> items) {
        //inp = expr + items is matrix (list of expressions with Head List)
        return getDeterminer(items);
    }

    static Symbol getDeterminer(List<Symbol> matrix) {
        boolean isSquareMatrix = matrix.stream()
                .map(x -> x.visit(new AsExpressionVisitor()))
                .filter(Objects::nonNull)
                .filter(x -> x.getHead() == ListFunctions.List)
                .filter(x -> x.getArguments().size() == matrix.size())
                .count() == matrix.size();
        if (!isSquareMatrix) {
            throw new IllegalArgumentException("Determiner can be computed only to a matrix");
        }

        if (matrix.size() == 1) return ((Expression) matrix.get(0)).getArguments().get(0);

        if (matrix.size() == 2) {
            Symbol first = new Expression(ArithmeticFunctions.Times,
                    ((Expression) matrix.get(0)).getArguments().get(0),
                    ((Expression) matrix.get(1)).getArguments().get(1));

            Symbol second = new Expression(ArithmeticFunctions.Minus,
                    new Expression(ArithmeticFunctions.Times,
                            ((Expression) matrix.get(0)).getArguments().get(1),
                            ((Expression) matrix.get(1)).getArguments().get(0)));

            return new Expression(ArithmeticFunctions.Plus, first, second);
        }

        List<List<Symbol>> minors = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Symbol> minor_rows = new ArrayList<>();
            for (int j = 0; j < matrix.size(); j++) {
                List<Symbol> row_elems = new ArrayList<>();
                for (int k = 0; k < matrix.size(); k++) {
                    if (k != i && j != 0) {
                        row_elems.add(((Expression) matrix.get(j)).getArguments().get(k));
                    }
                }
                if (!row_elems.isEmpty()) minor_rows.add(new Expression(ListFunctions.List, row_elems));
            }
            minors.add(minor_rows);
        }

        List<Symbol> terms = new ArrayList<>();

        for (int i = 0; i < matrix.size(); i++) {
            if (i % 2 == 0) {
                terms.add(new Expression(ArithmeticFunctions.Minus,
                        new Expression(ArithmeticFunctions.Times,
                                ((Expression) matrix.get(0)).getArguments().get(i),
                                getDeterminer(minors.get(i)))));
            } else {
                terms.add(new Expression(ArithmeticFunctions.Times,
                        ((Expression) matrix.get(0)).getArguments().get(i),
                        getDeterminer(minors.get(i))));
            }
        }

        return new Expression(ArithmeticFunctions.Plus, terms);
    }
}

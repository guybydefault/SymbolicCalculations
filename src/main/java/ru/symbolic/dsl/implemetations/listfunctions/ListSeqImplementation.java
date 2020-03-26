package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;
import ru.symbolic.dsl.library.Functions;
import ru.symbolic.visitors.cast.AsExpressionVisitor;
import ru.symbolic.visitors.cast.AsStringSymbolVisitor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListSeqImplementation extends AbstractFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[]{ListFunctions.List, ArithmeticFunctions.Plus, ArithmeticFunctions.Times};

    public ListSeqImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        List<Symbol> newList = new LinkedList<>();
        for (Symbol symbol : expression.getArguments()) {
            Expression expr = symbol.visit(AsExpressionVisitor.getInstance());
            if (expr != null) {
                StringSymbol head = expr.getHead().visit(AsStringSymbolVisitor.getInstance());
                if (head != null && head.equals(Functions.Seq)) {
                    if (expr.getArguments().size() > 0) {
                        // empty seq -> nothing
                        expr.getArguments().forEach(arg -> newList.add(arg));
                    } else {
                        System.out.println("de");
                    }
                    continue;
                }
            }
            newList.add(symbol);
        }
        return new Expression(expression.getHead(), Collections.unmodifiableList(newList));
    }

}

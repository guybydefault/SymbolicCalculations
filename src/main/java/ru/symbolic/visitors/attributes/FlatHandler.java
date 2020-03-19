package ru.symbolic.visitors.attributes;

import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatHandler implements ISymbolVisitor {
    private static final HasAttributeChecker FlatChecker =
            new HasAttributeChecker(Attributes.Flat);

    @Override
    public Object visitExpression(Expression expression) {
        if (!(boolean)expression.getHead().visit(FlatChecker)) {
            return expression;
        }

        return new Expression(expression.getHead(),
                expression.getArguments().stream().flatMap(a ->
                        a instanceof Expression && ((Expression) a).getHead().equals(expression.getHead())
                                ? ((Expression) a).getArguments().stream()
                                : Stream.of(a)).collect(Collectors.toList()));
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        return symbol;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return constant;
    }
}

package ru.symbolic.visitors.attributes;

import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;

public class OneIdentityHandler implements ISymbolVisitor {

    private static final HasAttributeChecker IsOneIdentityVisitor =
            new HasAttributeChecker(Attributes.OneIdentity);

    @Override
    public Object visitExpression(Expression expression) {
        return expression.getHead().visit(IsOneIdentityVisitor)
                && expression.getArguments().size() == 1
                ? expression.getArguments().get(0)
                : expression;
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

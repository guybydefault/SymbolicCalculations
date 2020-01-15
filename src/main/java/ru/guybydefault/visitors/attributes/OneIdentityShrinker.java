package ru.guybydefault.visitors.attributes;

import ru.guybydefault.dsl.library.Attributes;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class OneIdentityShrinker implements ISymbolVisitor {

    private static final HasAttributeChecker IsOneIdentityVisitor =
            new HasAttributeChecker(Attributes.OneIdentity);

    @Override
    public Object visitExpression(Expression expression) {
        return (boolean)expression.getHead().visit(IsOneIdentityVisitor)
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

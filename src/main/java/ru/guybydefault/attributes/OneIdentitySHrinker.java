package ru.guybydefault.attributes;

import ru.guybydefault.Attributes;
import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class OneIdentitySHrinker implements ISymbolVisitor {

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

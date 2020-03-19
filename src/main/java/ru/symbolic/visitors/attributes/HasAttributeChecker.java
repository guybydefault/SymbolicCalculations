package ru.symbolic.visitors.attributes;

import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;

import java.util.Arrays;

public class HasAttributeChecker implements ISymbolVisitor<Boolean> {

    public HasAttributeChecker(StringSymbol attribute) {
        this.attribute = attribute;
    }

    private StringSymbol attribute;

    @Override
    public Boolean visitExpression(Expression expression) {
        return false;
    }

    @Override
    public Boolean visitSymbol(StringSymbol symbol) {
        return Arrays.asList(symbol.getAttributes()).contains(attribute);
    }

    @Override
    public Boolean visitConstant(Constant constant) {
        return false;
    }

    public StringSymbol getAttribute() {
        return attribute;
    }
}

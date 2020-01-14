package ru.guybydefault.attributes;

import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

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

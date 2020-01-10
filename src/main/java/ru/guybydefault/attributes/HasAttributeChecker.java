package ru.guybydefault.attributes;

import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

import java.util.Arrays;

public class HasAttributeChecker implements ISymbolVisitor {

    public HasAttributeChecker(StringSymbol attribute){
        this.attribute = attribute;
    }

    private StringSymbol attribute;

    @Override
    public Object visitExpression(Expression expression) {
        return false;
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        return Arrays.asList(symbol.getAttributes()).contains(attribute);
    }

    @Override
    public Object visitConstant(Constant constant) {
        return false;
    }

    public StringSymbol getAttribute() {
        return attribute;
    }
}

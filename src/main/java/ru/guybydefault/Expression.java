package ru.guybydefault;

import java.util.List;

public final  class Expression extends Symbol {

    private Symbol head;
    private List<Symbol> arguments;

    @Override
    protected Object visitImplementation(ISymbolVisitor visitor) {
        return visitor.visitExpression(this);
    }
}

package ru.guybydefault.visitors.cast;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.visitors.ISymbolVisitor;

public class AsStringSymbolVisitor implements ISymbolVisitor<StringSymbol> {

    private static final AsStringSymbolVisitor instance = new AsStringSymbolVisitor();

    @Override
    public StringSymbol visitExpression(Expression expression) {
        return null;
    }

    @Override
    public StringSymbol visitSymbol(StringSymbol symbol) {
        return symbol;
    }

    @Override
    public StringSymbol visitConstant(Constant constant) {
        return null;
    }

    public static AsStringSymbolVisitor getInstance() {
        return instance;
    }
}

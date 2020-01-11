package ru.guybydefault.cast;

import ru.guybydefault.*;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

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

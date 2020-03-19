package ru.symbolic.visitors.cast;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.visitors.ISymbolVisitor;

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

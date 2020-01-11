package ru.guybydefault.cast;

import ru.guybydefault.*;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class AsConstantVisitor implements ISymbolVisitor<Constant> {

    private static final AsConstantVisitor instance = new AsConstantVisitor();

    @Override
    public Constant visitExpression(Expression expression) {
        return null;
    }

    @Override
    public Constant visitSymbol(StringSymbol symbol) {
        return null;
    }

    @Override
    public Constant visitConstant(Constant constant) {
        return constant;
    }

    public static AsConstantVisitor getInstance() {
        return instance;
    }
}

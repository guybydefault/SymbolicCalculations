package ru.symbolic.visitors.cast;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.visitors.ISymbolVisitor;

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

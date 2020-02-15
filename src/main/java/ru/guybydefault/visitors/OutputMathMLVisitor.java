package ru.guybydefault.visitors;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class OutputMathMLVisitor implements ISymbolVisitor{

    private static final OutputMathMLVisitor instance = new OutputMathMLVisitor();

    @Override
    public Object visitExpression(Expression expression) {
//        return expression.getHead().visit(new OutputMathMLVisitor()).toString()
//                + expression.getArguments().stream()
//                .map(x -> x.visit(new OutputMathMLVisitor()).toString())
//                .collect(Collectors.joining( " " ));
        return null;
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        String result = "";
//        switch (symbol.getName()) {
//
//        }
        return null;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return constant.getValue();
    }

    public static OutputMathMLVisitor getInstance() {
        return instance;
    }

}

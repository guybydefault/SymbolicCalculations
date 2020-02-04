package ru.guybydefault.visitors;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

import java.util.stream.Collectors;

public class OutputMathML implements ISymbolVisitor{



    @Override
    public Object visitExpression(Expression expression) {
//        return expression.getHead().visit(new OutputMathML()).toString()
//                + expression.getArguments().stream()
//                .map(x -> x.visit(new OutputMathML()).toString())
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
}

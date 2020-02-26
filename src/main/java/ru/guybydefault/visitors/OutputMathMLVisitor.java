package ru.guybydefault.visitors;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.*;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Attributes;
import ru.guybydefault.dsl.library.Functions;

public class OutputMathMLVisitor implements ISymbolVisitor{

    private static final OutputMathMLVisitor instance = new OutputMathMLVisitor();

    @Override
    public Object visitExpression(Expression expression) {
        StringBuilder result = new StringBuilder();
        if (expression.getHead() instanceof Constant) {
            if (expression.getArguments().isEmpty())
                throw new IllegalArgumentException("Constant can not have arguments!");
            result.append(expression.getHead().visit(getInstance()));
        } else if (expression.getHead() instanceof StringSymbol) {
            StringSymbol head = (StringSymbol) expression.getHead();
            if (Alphabet.isFromAlphabet(head)) result.append(head.visit(getInstance()));
            if (Attributes.isFromAttributes(head)) result.append(head.visit(getInstance()));
            if (Functions.isFromFunctions(head)) {
                result.append(head.visit(getInstance()));
                result.append(simpleFunctionArgs(result, expression, ","));
            }
            if (ArithmeticFunctions.isFromArithmeticFunctions(head)) {
                if (head == ArithmeticFunctions.Minus) {
                    if (expression.getArguments().size() != 1)
                        throw new IllegalArgumentException("Minus mustbe applied to one arg, not several or zero!");
                    result.append("<mo>-</mo>").append(expression.getArguments().get(0).visit(getInstance()));
                } else if (head == ArithmeticFunctions.ListPlus) {
                    //hz che tut delat'
                } else {
                    result.append(simpleFunctionArgs(result, expression,
                            head.visit(getInstance()).toString()));
                }
            }
            if (BooleanFunctions.isFromBooleanFunctions(head)) {
                if (head == BooleanFunctions.True || head == BooleanFunctions.False) {
                    result.append(head.visit(getInstance()));
                } else if (head == BooleanFunctions.More || head == BooleanFunctions.Less
                        || head == BooleanFunctions.Eq) {
                    if (expression.getArguments().size() != 2)
                        throw new IllegalArgumentException("More or Less must have 2 args!");
                    result.append(expression.getArguments().get(0).visit(getInstance()))
                            .append(head.visit(getInstance()))
                            .append(expression.getArguments().get(1).visit(getInstance()));
                } else {
                    result.append(head.visit(getInstance()))
                            .append(simpleFunctionArgs(result, expression, ","));
                }
            }
            if (CastingFunctions.isFromCastingFunctions(head)) {
                if (head == CastingFunctions.Null) {
                    if (expression.getArguments().size() != 0)
                        throw new IllegalArgumentException("Null can not have args!");
                    result.append(head.visit(getInstance()));
                } else if (head == CastingFunctions.AsExpressionArgs) {
                    result.append(head.visit(getInstance()));
                    result.append(simpleFunctionArgs(result, expression, ","));
                } else {
                    if (expression.getArguments().size() != 1)
                        throw new IllegalArgumentException("Most Casting functions must have only and minimum 1 arg!");
                    result.append(head.visit(getInstance()));
                    result.append(simpleFunctionArgs(result, expression, ","));
                }
            }
            if (ListFunctions.isFromListFunctions(head)) {

            }
            if (MatrixFunctions.isFromMatrixFunctions(head)) {

            }
        } else {
            result.append(expression.getHead().visit(getInstance()));
            result.append(simpleFunctionArgs(result, expression, ","));
        }
        return result.toString();
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        String result = "";
        if (Alphabet.isFromAlphabet(symbol))
            result = "<mi>" + symbol.getName().substring(0, symbol.getName().length() - 1) + "</mi>";
        if (Attributes.isFromAttributes(symbol)) result = "<mi>" + symbol.getName() + "</mi>";
        if (Functions.isFromFunctions(symbol)) result = "<mi>" + symbol.getName() + "</mi>";
        if (ArithmeticFunctions.isFromArithmeticFunctions(symbol)) result = arithmeticFunctions(symbol);
        if (BooleanFunctions.isFromBooleanFunctions(symbol)) {
            if (symbol == BooleanFunctions.More) {
                result = "<mi>></mi>";
            } else if (symbol == BooleanFunctions.Less) {
                result = "<mi><</mi>";
            } else if (symbol == BooleanFunctions.Eq) {
                result = "<mi>=</mi>";
            }
            result = "<mi>" + symbol.getName() + "</mi>";
        }
        if (CastingFunctions.isFromCastingFunctions(symbol)) result = "<mi>" + symbol.getName() + "</mi>";
        if (ListFunctions.isFromListFunctions(symbol)) result = "<mi>" + symbol.getName() + "</mi>";
        if (MatrixFunctions.isFromMatrixFunctions(symbol))
            result = (symbol == MatrixFunctions.MatrixPlus) ? "<mo>+</mo>" : "<mo>*</mo>";
        return result;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return "<mn>" + constant.getValue() + "</mn>";
    }

    public static OutputMathMLVisitor getInstance() {
        return instance;
    }

    private String arithmeticFunctions(StringSymbol symbol) {
        if (symbol == ArithmeticFunctions.Plus || symbol == ArithmeticFunctions.BinaryPlus
                || symbol == ArithmeticFunctions.ListPlus || symbol == ArithmeticFunctions.ListPlusList) {
            return "<mo>+</mo>";
        } else if (symbol == ArithmeticFunctions.Times || symbol == ArithmeticFunctions.BinaryTimes
                || symbol == ArithmeticFunctions.ListTimes) {
            return "<mo>*</mo>";
        } else {
            return "<mo>-</mo>";
        }
    }

    private StringBuilder simpleFunctionArgs(StringBuilder result, Expression expression, String spliterator) {
        if (!expression.getArguments().isEmpty()) {
            result.append("<mfenced separators=\"" + spliterator + "\">");
            for (Symbol s: expression.getArguments()) {
                result.append(s.visit(getInstance()));
            }
            result.append("</mfenced>");
        } else {
            result.append("<mfenced></mfenced>");
        }
        return result;
    }
}

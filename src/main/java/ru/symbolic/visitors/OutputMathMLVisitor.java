package ru.symbolic.visitors;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.*;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.dsl.library.Functions;

import java.util.List;

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
            List<Symbol> args = expression.getArguments();
            if (Alphabet.isFromAlphabet(head)) result.append(head.visit(getInstance()));
            if (Attributes.isFromAttributes(head)) result.append(head.visit(getInstance()));
            if (Functions.isFromFunctions(head)) result.append(head.visit(getInstance()))
                    .append(simpleFunctionArgs(result, expression, ","));
            if (ArithmeticFunctions.isFromArithmeticFunctions(head)) {
                if (head == ArithmeticFunctions.Minus) {
                    if (args.size() != 1)
                        throw new IllegalArgumentException("Minus mustbe applied to one arg, not several or zero!");
                    result.append("<mo>-</mo>").append(args.get(0).visit(getInstance()));
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
//                } else if (head == BooleanFunctions.More || head == BooleanFunctions.Less
//                        || head == BooleanFunctions.Eq) {
//                    if (args.size() != 2)
//                        throw new IllegalArgumentException("More or Less must have 2 args!");
//                    result.append(args.get(0).visit(getInstance()))
//                            .append(head.visit(getInstance()))
//                            .append(args.get(1).visit(getInstance()));
                } else {
                    result.append(head.visit(getInstance()))
                            .append(simpleFunctionArgs(result, expression, ","));
                }
            }
            if (CastingFunctions.isFromCastingFunctions(head)) {
                if (head == CastingFunctions.Null) {
                    if (args.size() != 0)
                        throw new IllegalArgumentException("Null can not have args!");
                    result.append(head.visit(getInstance()));
                } else if (head == CastingFunctions.AsExpressionArgs) {
                    result.append(head.visit(getInstance()))
                            .append(simpleFunctionArgs(result, expression, ","));
                } else {
                    if (args.size() != 1)
                        throw new IllegalArgumentException("Most Casting functions must have only and minimum 1 arg!");
                    result.append(head.visit(getInstance()))
                            .append(simpleFunctionArgs(result, expression, ","));
                }
            }
            if (ListFunctions.isFromListFunctions(head)) {
                if (head == ListFunctions.List) {
                    if (!args.isEmpty()) {
                        Expression arg0 = null;
                        if (args.get(0) instanceof Expression) {
                            arg0 = (Expression) args.get(0);
                        }
                        if (arg0 != null && arg0.getHead() == ListFunctions.List) {
                            result.append("<mfenced open=\"[\" close=\"]\"><mtable>");
                            for (int i = 0; i < args.size(); i++) {
                                result.append("<mtr>");
                                Expression argi = (Expression) args.get(i);
                                for (int j = 0; j < argi.getArguments().size(); j++) {
                                    result.append("<mtd>").append(argi.getArguments().get(j).visit(getInstance()))
                                            .append("</mtd>");
                                }
                                result.append("</mtr>");
                            }
                            result.append("</mtable></mfenced>");
                            //<math xmlns="http://www.w3.org/1998/Math/MathML"><mfenced open="[" close="]"><mtable><mtr><mtd><mn>1</mn></mtd><mtd><mn>2</mn></mtd></mtr><mtr><mtd><mn>3</mn></mtd><mtd><mn>4</mn></mtd></mtr></mtable></mfenced></math>
                        } else {
                            result.append(simpleFunctionArgs(result, expression, ","));
                        }
                    } else {
                        result.append("<mfenced></mfenced>");
                    }
                } else {
                    if (args.size() <= 0)
                        throw new IllegalArgumentException("Most List functions must have minimum 1 arg!");
                    result.append(head.visit(getInstance()))
                            .append(simpleFunctionArgs(result, expression, ","));
                }
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
            } else {
                result = "<mi>" + symbol.getName() + "</mi>";
            }
        }
        if (CastingFunctions.isFromCastingFunctions(symbol)) result = "<mi>" + symbol.getName() + "</mi>";
        if (ListFunctions.isFromListFunctions(symbol)) result = "<mi>" + symbol.getName() + "</mi>";

        return result;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return "<mo>" + constant.getValue() + "</mo>";
    }

    public static OutputMathMLVisitor getInstance() {
        return instance;
    }

    private String arithmeticFunctions(StringSymbol symbol) {
        if (symbol == ArithmeticFunctions.Plus || symbol == ArithmeticFunctions.BinaryPlus
                || symbol == ArithmeticFunctions.ListPlus) {
            return "<mo>+</mo>";
        } else if (symbol == ArithmeticFunctions.Times || symbol == ArithmeticFunctions.BinaryTimes) {
            return "<mo>*</mo>";
        } else {
            return "<mo>-</mo>";
        }
    }

    private StringBuilder simpleFunctionArgs(StringBuilder result, Expression expression, String spliterator) {
        if (!expression.getArguments().isEmpty()) {
            result.append("<mfenced><mrow>").append(expression.getArguments().get(0).visit(getInstance()));
            for (int i = 1; i < expression.getArguments().size(); i++) {
                result.append(spliterator).append(expression.getArguments().get(i).visit(getInstance()));
            }
            result.append("</mrow></mfenced>");
        } else {
            result.append("<mfenced></mfenced>");
        }
        return result;
    }

    public String addMathMl(String inner) {
        return "<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><mrow>" + inner + "</mrow></math>";
    }
}

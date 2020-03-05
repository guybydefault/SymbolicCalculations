package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.visitors.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.visitors.cast.AsStringSymbolVisitor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlusImplementation extends AbstractFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ArithmeticFunctions.BinaryPlus, ArithmeticFunctions.Plus};

    public PlusImplementation(){
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        if (expression.getArguments().contains(new StringSymbol("x"))) {
            System.out.println("hey");
        }

        List<Constant> constants = expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .collect(Collectors.toList());

        Constant constant = new Constant(constants.stream()
                .filter(Objects::nonNull)
                .map(Constant::getValue)
                .reduce(0.0, Double::sum));

        if (constants.contains(null)) {

            List<Symbol> args = expression.getArguments().stream()
                    .filter(x -> x.visit(new AsConstantVisitor()) == null)
                    .collect(Collectors.toList());

//            HashMap<Symbol, Constant> singleArgsNumbers = getHashMapSingleEntriesNumbers(expression);
//
//            //but if it is a lonely StringSymbol we'll put it back
//            List<Symbol> args = getTimesSingleEntries(expression).stream()
//                    .filter(x -> singleArgsNumbers.get(x).getValue() == 1)
//                    .collect(Collectors.toList());
//
//            //adding other times with constant more than 1 and single arg
//            args.addAll(getTimesSingleEntries(expression).stream()
//                    .filter(x -> singleArgsNumbers.get(x).getValue() > 1) //0 and 1 won't be in Times format
//                    .map(x -> new Expression(ArithmeticFunctions.Times, x, singleArgsNumbers.get(x)))
//                    .collect(Collectors.toList()));
//
//            HashMap<List<Symbol>, Constant> multipleArgsNumbers = getHashMapMultipleEntriesNumbers(expression);
//
//            //


            if (!constants.stream().allMatch(Objects::isNull) && constant.getValue() != 0) {
                args.add(constant);
            }
            return new Expression(ArithmeticFunctions.Plus, args);
        }
        return constant;
    }

    private List<Symbol> getTimesSingleEntries(Expression expression) {
        List<Symbol> res;
        List<Symbol> stringSymbols = expression.getArguments().stream()
                .filter(x -> x.visit(new AsStringSymbolVisitor()) != null)
                .collect(Collectors.toList());
        List<Symbol> timesSingleArgs = expression.getArguments().stream()
                .filter(x -> x.visit(new AsExpressionVisitor()) != null)
                .filter(x -> //1 arg without constants
                        (((Expression) x).getHead() == ArithmeticFunctions.Times
                        || ((Expression) x).getHead() == ArithmeticFunctions.BinaryTimes))
                .filter(x -> //1 arg without constants
                        (((Expression) x).getArguments().stream()
                                .filter(o -> o.visit(new AsConstantVisitor()) == null).count() == 1))
                .collect(Collectors.toList());
        res = stringSymbols;
        res.addAll(timesSingleArgs);
        res = res.stream()
                .distinct()
                .collect(Collectors.toList());
        return res;
    }

    private List<List<Symbol>> getTimesMultipleEntries(Expression expression) {
        return expression.getArguments().stream()
                .filter(x -> x.visit(new AsExpressionVisitor()) != null)
                .filter(x -> //1 arg without constants
                        (((Expression) x).getHead() == ArithmeticFunctions.Times
                                || ((Expression) x).getHead() == ArithmeticFunctions.BinaryTimes))
                .filter(x -> //1 arg without constants
                        (((Expression) x).getArguments().stream()
                                .filter(o -> o.visit(new AsConstantVisitor()) == null).count() > 1))
                .map(x -> (((Expression) x).getArguments().stream()
                        .filter(o -> o.visit(new AsConstantVisitor()) == null)
                        .collect(Collectors.toList())))
                .distinct()
                .collect(Collectors.toList());
    }

    private HashMap<Symbol, Constant> getHashMapSingleEntriesNumbers(Expression expression) {
        HashMap<Symbol, Constant> result = new HashMap<>();
        List<Symbol> alphabet = getTimesSingleEntries(expression);
        for (Symbol s : alphabet) {
            double n = expression.getArguments().stream()
                    .filter(x -> x.equals(s))
                    .count();
            n += expression.getArguments().stream()
                    .map(x -> x.visit(new AsExpressionVisitor()))
                    .filter(Objects::nonNull)
                    .filter(this::isTimesWithOneConstantAndOneArg)
                    .filter(x -> x.getArguments().contains(s))
                    .map(this::getOneConstant)
                    .reduce(0.0, Double::sum);
            result.put(s, new Constant(n));
        }
        return result;
    }

//    private HashMap<List<Symbol>, Constant> getHashMapMultipleEntriesNumbers(Expression expression) {
//        HashMap<List<Symbol>, Constant> result = new HashMap<>();
//        List<List<Symbol>> alphabet = getTimesMultipleEntries(expression);
//        for (List<Symbol> s : alphabet) {
//            double n = expression.getArguments().stream()
//                    .map(x -> x.visit(new AsExpressionVisitor()))
//                    .filter(Objects::nonNull)
//                    .filter(x -> x.getArguments().stream())
//        }
//        return result;
//    }

    //one of args is constant
    private boolean hasOneConstant(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .filter(Objects::nonNull)
                .count() == 1;
    }

    //get first (and only) constant argument
    private double getOneConstant(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .get(0).getValue();
    }

    //not an Expression of type Times or Binary times with 2 args
    // and one of them is from Alphabet and another is Constant
    private boolean isTimesWithOneConstantAndOneArg(Expression expression) {
        return Stream.of(expression)
                .filter(x -> x.getHead() == ArithmeticFunctions.Times
                        || x.getHead() == ArithmeticFunctions.BinaryTimes)
                .filter(x -> x.getArguments().size() == 2)
                .filter(this::hasOneConstant)
                .count() == 1;
    }
}

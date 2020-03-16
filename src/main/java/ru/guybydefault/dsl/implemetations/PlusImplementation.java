package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.visitors.cast.AsConstantVisitor;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.visitors.cast.AsStringSymbolVisitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlusImplementation extends AbstractFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[]{ArithmeticFunctions.BinaryPlus, ArithmeticFunctions.Plus};

    public PlusImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Constant constant = new Constant(expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .filter(Objects::nonNull)
                .map(Constant::getValue)
                .reduce(0.0, Double::sum));

        List<Symbol> args = new LinkedList<>();

        for (Symbol arg : expression.getArguments()) {
            if (arg.visit(AsConstantVisitor.getInstance()) == null) {
                args.add(arg);
            }
        }

        if (constant.getValue() != 0 || args.size() == 0) {
            args.add(constant);
        }

        return new Expression(ArithmeticFunctions.Plus, args);
    }

    protected Symbol evaluateDana(Expression expression) {
        List<Constant> constants = expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .collect(Collectors.toList());

        Constant constant = new Constant(constants.stream()
                .filter(Objects::nonNull)
                .map(Constant::getValue)
                .reduce(0.0, Double::sum));

        if (constants.contains(null)) {

            //without simplification
//            List<Symbol> args = expression.getArguments().stream()
//                    .filter(x -> x.visit(new AsConstantVisitor()) == null)
//                    .collect(Collectors.toList());

            HashMap<Symbol, Constant> singleArgsNumbers = getHashMapSingleEntriesNumbers(expression);

            //lonely StringSymbol or Expression we'll put it in args
            List<Symbol> args = getTimesSingleEntries(expression).stream()
                    .filter(x -> singleArgsNumbers.get(x).getValue() == 1)
                    .collect(Collectors.toList());

            //adding other Times with constant more than 1 and single arg
            args.addAll(getTimesSingleEntries(expression).stream()
                    .filter(x -> singleArgsNumbers.get(x).getValue() > 1) //0 and 1 won't be in Times format
                    .map(x -> new Expression(ArithmeticFunctions.Times, x, singleArgsNumbers.get(x)))
                    .collect(Collectors.toList()));

            HashMap<List<Symbol>, Constant> multipleArgsNumbers = getHashMapMultipleEntriesNumbers(expression);

            //adding Times Expressions without Constant but with several other args
            args.addAll(getTimesMultipleEntries(expression).stream()
                    .filter(x -> multipleArgsNumbers.get(x).getValue() == 1)
                    .map(x -> new Expression(ArithmeticFunctions.Times, x))
                    .collect(Collectors.toList()));

            //adding Times Expressions with 1 constant and several other args
            args.addAll(getTimesMultipleEntries(expression).stream()
                    .filter(x -> multipleArgsNumbers.get(x).getValue() > 1) //0 and 1 won't be in Times format
                    .map(x -> addInList(x, multipleArgsNumbers.get(x)))
                    .map(x -> new Expression(ArithmeticFunctions.Times, x))
                    .collect(Collectors.toList()));

            if (!constants.stream().allMatch(Objects::isNull) && constant.getValue() != 0) {
                args.add(constant);
            }

            if (args.size() == 1) {
                return args.get(0);
            }

            return new Expression(ArithmeticFunctions.Plus, args);
        }

        return constant;
    }

    private List<Symbol> addInList(List<Symbol> list, Symbol symbol) {
        list.add(symbol);
        return list;
    }

    private List<Symbol> getTimesSingleEntries(Expression expression) {
        List<Symbol> res;
        List<Symbol> singleNotConstants = expression.getArguments().stream()
                .filter(x -> x.visit(new AsStringSymbolVisitor()) != null)
                .collect(Collectors.toList());
        singleNotConstants.addAll(expression.getArguments().stream()
                .map(x -> x.visit(new AsExpressionVisitor()))
                .filter(Objects::nonNull)
                .filter(x -> !isTimes(x))
                .collect(Collectors.toList()));

        List<Symbol> timesSingleArgs = expression.getArguments().stream()
                .map(x -> x.visit(new AsExpressionVisitor()))
                .filter(Objects::nonNull)
                .filter(this::isTimesWithOneConstantAndOneArg)
                .filter(x -> !(singleNotConstants.contains(x.getArguments().get(0))))
                .collect(Collectors.toList());
        res = singleNotConstants;
        res.addAll(timesSingleArgs);
        res = res.stream()
                .distinct()
                .collect(Collectors.toList());
        return res;
    }

    private List<List<Symbol>> getTimesMultipleEntries(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsExpressionVisitor()))
                .filter(Objects::nonNull)
                .filter(this::isTimesWithOneConstantAndSeveralArgs)
                .map(x -> (x.getArguments().stream()
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

    private HashMap<List<Symbol>, Constant> getHashMapMultipleEntriesNumbers(Expression expression) {
        HashMap<List<Symbol>, Constant> result = new HashMap<>();
        List<List<Symbol>> alphabet = getTimesMultipleEntries(expression);
        for (List<Symbol> l : alphabet) {
            double n = expression.getArguments().stream()
                    .map(x -> x.visit(new AsExpressionVisitor()))
                    .filter(Objects::nonNull)
                    .filter(this::isTimesWithoutConstantsAndWithSeveralArgs)
                    .filter(x -> x.getArguments().equals(l))
                    .count();
            n += expression.getArguments().stream()
                    .map(x -> x.visit(new AsExpressionVisitor()))
                    .filter(Objects::nonNull)
                    .filter(this::isTimesWithOneConstantAndSeveralArgs)
                    .filter(x -> hasAllArgsExceptConstantEquals(x, l))
                    .map(this::getOneConstant)
                    .reduce(0.0, Double::sum);
            result.put(l, new Constant(n));
        }
        return result;
    }

    private boolean isTimes(Expression expression) {
        return Stream.of(expression)
                .filter(x -> x.getHead() == ArithmeticFunctions.Times
                        || x.getHead() == ArithmeticFunctions.BinaryTimes)
                .count() == 1;
    }

    private boolean hasAllArgsExceptConstantEquals(Expression expression, List<Symbol> list) {
        List<Symbol> args = expression.getArguments().stream()
                .filter(x -> x.visit(new AsConstantVisitor()) == null)
                .collect(Collectors.toList());
        args.removeAll(list);
        return args.isEmpty();
    }

    //one of args is constant
    private boolean hasOneConstant(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .filter(Objects::nonNull)
                .count() == 1;
    }

    //one of args is constant
    private boolean hasNoConstants(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor())).noneMatch(Objects::nonNull);
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
    // and only one of them is Constant
    private boolean isTimesWithOneConstantAndOneArg(Expression expression) {
        return Stream.of(expression)
                .filter(this::isTimes)
                .filter(x -> x.getArguments().size() == 2)
                .filter(this::hasOneConstant)
                .count() == 1;
    }

    //not an Expression of type Times or Binary times with more than 2 args
    // and only one of them is Constant
    private boolean isTimesWithOneConstantAndSeveralArgs(Expression expression) {
        return Stream.of(expression)
                .filter(this::isTimes)
                .filter(x -> x.getArguments().size() > 2)
                .filter(this::hasOneConstant)
                .count() == 1;
    }

    //not an Expression of type Times or Binary times with more than 2 args
    // and only one of them is Constant
    private boolean isTimesWithoutConstantsAndWithSeveralArgs(Expression expression) {
        return Stream.of(expression)
                .filter(this::isTimes)
                .filter(x -> x.getArguments().size() > 2)
                .filter(this::hasNoConstants)
                .count() == 1;
    }
}

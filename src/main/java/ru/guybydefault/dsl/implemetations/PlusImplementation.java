package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.dsl.library.Alphabet;
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

public class PlusImplementation extends AbstractFunctionImplementation  {
    private static final StringSymbol[] names = new StringSymbol[] {ArithmeticFunctions.BinaryPlus, ArithmeticFunctions.Plus};

    public PlusImplementation(){
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        List<Constant> constants = expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .collect(Collectors.toList());

        Constant constant = new Constant(constants.stream()
                .filter(Objects::nonNull)
                .map(Constant::getValue)
                .reduce(0.0, Double::sum));

        if (constants.contains(null)) {
            if (!constants.stream().allMatch(Objects::isNull)) {
                List<Symbol> args = expression.getArguments().stream()
                        .filter(x -> x.visit(new AsConstantVisitor()) == null) //not a Constant
                        .filter(x -> x.visit(new AsStringSymbolVisitor()) == null) //not a StringSymbol
                        .filter(x -> isTimesWithAlphabetAndConstant((Expression) x)) //not an Expression of type Times or Binary times with 2 args
                        .filter(x ->
                                ((Expression) x).getArguments().stream()
                                        .filter(a -> a.visit(new AsStringSymbolVisitor()) != null)
                                        .filter(a -> Alphabet.isFromAlphabet((StringSymbol) a))
                                        .count() == 1)// and one of them is from Alphabet and another is Constant
                        .collect(Collectors.toList());

                HashMap<StringSymbol, Constant> hma = getHashMapAlphabet(expression);

                //but if it is a lonely StringSymbol we'll put it back
                args.addAll(Alphabet.getAlphabet().stream()
                        .filter(x -> hma.get(x).getValue() == 1)
                        .collect(Collectors.toList()));

                args.addAll(Alphabet.getAlphabet().stream()
                        .filter(x -> hma.get(x).getValue() > 1) //0 and 1 won't be in Times format
                        .map(x -> new Expression(ArithmeticFunctions.Times, x, hma.get(x)))
                        .collect(Collectors.toList()));

                if (constant.getValue() != 0) {
                    args.add(constant);
                }
                return new Expression(ArithmeticFunctions.Plus, args);
            }
            return expression;
        }

        return constant;
    }

    private HashMap<StringSymbol, Constant> getHashMapAlphabet(Expression expression) {
        HashMap<StringSymbol, Constant> result = new HashMap<>();
        for (StringSymbol s : Alphabet.getAlphabet()) {
            double n = expression.getArguments().stream()
                    .filter(x -> x.equals(s))
                    .count();
            n += expression.getArguments().stream()
                    .map(x -> x.visit(new AsExpressionVisitor()))
                    .filter(Objects::nonNull)
                    .filter(this::isTimesWithAlphabetAndConstant)
                    .filter(x -> x.getArguments().contains(s))
                    .map(this::getOneConstant)
                    .reduce(0.0, Double::sum);
            result.put(s, new Constant(n));
        }
        return result;
    }

    private boolean hasOneConstant(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .filter(Objects::nonNull)
                .count() == 1;
    }

    private double getOneConstant(Expression expression) {
        return expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .get(0).getValue();
    }

    //not an Expression of type Times or Binary times with 2 args
    // and one of them is from Alphabet and another is Constant
    private boolean isTimesWithAlphabetAndConstant(Expression expression) {
        return Stream.of(expression)
                .filter(x -> x.getHead() == ArithmeticFunctions.Times
                || x.getHead() == ArithmeticFunctions.BinaryTimes)
                .filter(x -> x.getArguments().size() == 2)
                .filter(this::hasOneConstant)
                .count() == 1;
    }
}

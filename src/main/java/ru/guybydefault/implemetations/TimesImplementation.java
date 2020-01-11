package ru.guybydefault.implemetations;

import ru.guybydefault.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TimesImplementation extends AbstractFunctionImplementation{

    private final StringSymbol[] names = new StringSymbol[] {ArithmeticFunctions.Times, ArithmeticFunctions.BinaryTimes};

    protected TimesImplementation(StringSymbol[] names) {
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
        List<Constant> constants = expression.getArguments().stream()
                .map(x -> (Constant) x.visit(new AsConstantVisitor()))
                .collect(Collectors.toList());

        if (constants.contains(null)) {
            return expression;
        }

        return new Constant(constants.stream().filter(Objects::nonNull)
                .map(Constant::getValue).reduce(1.0, (a, b) -> a * b));
    }
}
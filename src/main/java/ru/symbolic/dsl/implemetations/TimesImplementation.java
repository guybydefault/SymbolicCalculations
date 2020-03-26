package ru.symbolic.dsl.implemetations;

import ru.symbolic.visitors.cast.AsConstantVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TimesImplementation extends AbstractFunctionImplementation{

    private static final StringSymbol[] names = new StringSymbol[] {ArithmeticFunctions.Times, ArithmeticFunctions.BinaryTimes};

    public TimesImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        List<Constant> constants = expression.getArguments().stream()
                .map(x -> x.visit(new AsConstantVisitor()))
                .collect(Collectors.toList());

        Constant constant = new Constant(constants.stream().filter(Objects::nonNull)
                .map(Constant::getValue).reduce(1.0, (a, b) -> a * b));

        if (constants.contains(null)) {
            if (constants.stream().allMatch(Objects::isNull)) {
                return expression;
            } else {
                List<Symbol> s = expression.getArguments().stream()
                        .filter(x -> x.visit(new AsConstantVisitor()) == null)
                        .collect(Collectors.toList());
                s.add(constant);
                if (constant.getValue() == 0d) {
                    return constant;
                }
                return new Expression(ArithmeticFunctions.Times, s);
            }
        }


        return constant;
    }
}

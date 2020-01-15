package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.visitors.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlusImplementation extends AbstractFunctionImplementation  {
    private static final StringSymbol[] names = new StringSymbol[] {ArithmeticFunctions.BinaryPlus, ArithmeticFunctions.Plus};

    public PlusImplementation(){
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        List<Constant> constants = expression.getArguments().stream()
                .map(x -> (Constant) x.visit(new AsConstantVisitor()))
                .collect(Collectors.toList());

        if (constants.contains(null)) {
            return expression;
        }

        return new Constant(constants.stream().filter(Objects::nonNull)
                .map(Constant::getValue).reduce(0.0, Double::sum));
    }
}

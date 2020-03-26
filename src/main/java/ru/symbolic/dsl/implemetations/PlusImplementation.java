package ru.symbolic.dsl.implemetations;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.visitors.cast.AsConstantVisitor;
import ru.symbolic.visitors.cast.AsExpressionVisitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

}

package ru.symbolic.dsl.implemetations;

import ru.symbolic.visitors.cast.AsConstantVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeImplementation extends AbstractFunctionImplementation  {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.Range};

    public RangeImplementation(){
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Constant from = (Constant) expression.getArguments().get(0).visit(new AsConstantVisitor());
        Constant to = (Constant) expression.getArguments().get(1).visit(new AsConstantVisitor());
        Constant amount = (Constant) expression.getArguments().get(2).visit(new AsConstantVisitor());

        if (from == null || to == null || amount == null) {
            return expression;
        }

        Double step = (to.getValue() - from.getValue()) / amount.getValue();

        return new Expression(ListFunctions.List,
                IntStream.rangeClosed(0, Math.abs((int)amount.getValue()))
                        .boxed().collect(Collectors.toList())
                        .stream().map(i -> from.getValue() + i * step)
                        .map(x -> new Constant(x))
                        .collect(Collectors.toList()));
    }
}

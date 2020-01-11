package ru.guybydefault.implemetations;

import ru.guybydefault.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeImplementation extends AbstractFunctionImplementation  {
    private final StringSymbol[] names = new StringSymbol[] {ListFunctions.Range};

    public RangeImplementation(StringSymbol[] names){
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
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

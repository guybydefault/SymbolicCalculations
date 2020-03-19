package ru.symbolic.dsl.implemetations;

import ru.symbolic.visitors.cast.AsConstantVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateListImplementation extends AbstractFunctionImplementation  {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.GenerateList};

    public GenerateListImplementation(){
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Constant count = expression.getArguments().get(0).visit(new AsConstantVisitor());
        if (count == null) {
            return expression;
        }
        return new Expression(ListFunctions.List,
                IntStream.range(0, (int)count.getValue())
                .boxed().collect(Collectors.toList())
                .stream().map(x -> new Constant(x))
                .collect(Collectors.toList()));
    }
}

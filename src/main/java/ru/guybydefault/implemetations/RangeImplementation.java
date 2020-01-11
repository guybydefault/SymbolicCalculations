package ru.guybydefault.implemetations;

import ru.guybydefault.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

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

        var step = (to.Value - @from.Value) / amount.Value;

        return List[
                Enumerable.Range(0, Math.Abs((int) amount.Value))
                        .Select(i => from.Value + i * step)
                    .Select(x => new Constant(x))
                    .OfType<Symbol>()
                .ToArray()
            ];
    }
}

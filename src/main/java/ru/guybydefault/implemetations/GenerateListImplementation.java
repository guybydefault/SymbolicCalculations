package ru.guybydefault.implemetations;

import ru.guybydefault.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

public class GenerateListImplementation extends AbstractFunctionImplementation  {
    private final StringSymbol[] names = new StringSymbol[] {ListFunctions.GenerateList};

    public GenerateListImplementation(StringSymbol[] names){
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
        Constant count = (Constant) expression.getArguments().get(0).visit(new AsConstantVisitor());

        return List[
                Enumerable.Range(0, (int) count.Value)
                        .Select(x => new Constant(x))
                    .OfType<Symbol>()
                .ToArray()
            ];
    }
}

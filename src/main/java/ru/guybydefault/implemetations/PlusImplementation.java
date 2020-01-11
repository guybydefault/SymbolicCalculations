package ru.guybydefault.implemetations;

import ru.guybydefault.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;

public class PlusImplementation extends AbstractFunctionImplementation  {
    private final StringSymbol[] names = new StringSymbol[] {ArithmeticFunctions.BinaryPlus, ArithmeticFunctions.Plus};

    public PlusImplementation(StringSymbol[] names){
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
        Constant[] constants = expression.getArguments()
                .Select(x => x.visit(new AsConstantVisitor()))
                .ToList();

        if (constants.Any(x => x == null)) {
            return expression;
        }

        return constants
                .Where(x => x != null)
                .Select(x => x.Value)
                .Aggregate(0m, (acc, x) => acc + x);
    }
}

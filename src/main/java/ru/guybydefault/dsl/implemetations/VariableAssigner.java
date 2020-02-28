package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.library.Functions;

import java.util.HashMap;
import java.util.Map;

public class VariableAssigner extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[]{Functions.SetDelayed, Functions.Set};

    public Map<Symbol, Symbol> variables = new HashMap<>();

    public VariableAssigner() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol variable = expression.getArguments().get(0);
        Symbol body = expression.getArguments().get(1);

        if (variable instanceof StringSymbol) {
            if (!variables.containsKey(variable)) {
                variables.put(variable, body);
            }
        }


        return expression;
    }
}

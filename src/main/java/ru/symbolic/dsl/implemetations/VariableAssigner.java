package ru.symbolic.dsl.implemetations;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.library.Functions;

import java.util.HashMap;
import java.util.Map;

public class VariableAssigner extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[]{Functions.SetDelayed, Functions.Set};

    public Map<Symbol, Symbol> variables = new HashMap<>();

    public Map<Expression, Symbol> patterns = new HashMap<>();

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
        } else if (variable instanceof Expression) {
            if (!patterns.containsKey(variable)) {
                patterns.put((Expression) variable, body);
            }
        }

        return expression;
    }
}

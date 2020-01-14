package ru.guybydefault;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.evaluation.VariableReplacer;

import java.util.Map;
import java.util.Objects;

public class GlobalVariablesReplacer implements ISymbolVisitor<Symbol> {
    private final VariableAssigner variableAssigner;

    public GlobalVariablesReplacer(VariableAssigner variableAssigner) {
        this.variableAssigner = variableAssigner;
    }

    @Override
    public Symbol visitExpression(Expression expression) {
        Symbol prev;
        Symbol newExpr = expression;

        do {
            prev = newExpr;
            for (Map.Entry<Symbol, Symbol> entry : variableAssigner.variables.entrySet()) {
                newExpr = newExpr.visit(new VariableReplacer(entry.getKey(), entry.getValue()));
            }
        } while (!Objects.equals(newExpr, prev));
        return newExpr;
    }

    @Override
    public Symbol visitSymbol(StringSymbol symbol) {
        return symbol;
    }

    @Override
    public Symbol visitConstant(Constant constant) {
        return constant;
    }
}

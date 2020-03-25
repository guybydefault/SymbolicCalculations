package ru.symbolic.visitors.evaluation;

import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.dsl.implemetations.VariableAssigner;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;

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
            for (Map.Entry<Expression, Symbol> entry : variableAssigner.patterns.entrySet()) {
                newExpr = newExpr.visit(new SymbolMatcher(entry.getKey(), entry.getValue()));
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

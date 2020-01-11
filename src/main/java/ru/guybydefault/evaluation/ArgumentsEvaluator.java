package ru.guybydefault.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class ArgumentsEvaluator implements ISymbolVisitor<CalculationResult> {

    private final FullEvaluator fullEvaluator;

    ArgumentsEvaluator(FullEvaluator fullEvaluator) {
        this.fullEvaluator = fullEvaluator;
    }

    @Override
    public CalculationResult visitExpression(Expression expression) {
        return null;
    }

    @Override
    public CalculationResult visitSymbol(StringSymbol symbol) {
        return null;
    }

    @Override
    public CalculationResult visitConstant(Constant constant) {
        return null;
    }
}

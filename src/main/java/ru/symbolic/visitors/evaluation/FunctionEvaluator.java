package ru.symbolic.visitors.evaluation;

import ru.symbolic.CalculationResult;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.visitors.cast.AsExpressionVisitor;
import ru.symbolic.visitors.cast.AsStringSymbolVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.library.Functions;

import java.util.LinkedList;
import java.util.Objects;

import static ru.symbolic.dsl.functions.ListFunctions.List;

public class FunctionEvaluator implements ISymbolVisitor<CalculationResult> {

    private final FullEvaluator fullEvaluator;

    FunctionEvaluator(FullEvaluator fullEvaluator) {
        this.fullEvaluator = fullEvaluator;
    }

    @Override
    public CalculationResult visitExpression(Expression expression) {
        Expression funcHead = expression.getHead().visit(AsExpressionVisitor.getInstance());

        if (funcHead == null) {
            return new CalculationResult(new LinkedList<>(), expression);
        }

        if (!Objects.equals(funcHead.getHead(), Functions.Fun)) {
            return new CalculationResult(new LinkedList<>(), expression);
        }

        if (funcHead.getArguments().size() != 2) {
            throw new IllegalArgumentException("Function declaration should contain only 2 arguments");
        }

        Symbol funParameter = funcHead.getArguments().get(0);
        Symbol funBody = funcHead.getArguments().get(1);

        Expression listParameters = funParameter.visit(AsExpressionVisitor.getInstance());
        if (listParameters != null && Objects.equals(listParameters.getHead(), List)) {
            for (int i = 0; i < listParameters.getArguments().size() && i < expression.getArguments().size(); i++) {
                funBody = funBody.visit(
                        new VariableReplacer(
                                listParameters.getArguments().get(i),
                                expression.getArguments().get(i),
                                true));
            }
            return funBody.visit(fullEvaluator);
        }

        StringSymbol variable = funParameter.visit(AsStringSymbolVisitor.getInstance());

        if (variable == null) {
            throw new IllegalArgumentException("Fun parameter can be only StringSymbol or List. Something gone wrong");
        }

        Symbol functionArgument = expression.getArguments().get(0);
        Symbol substituted = funBody.visit(new VariableReplacer(variable, functionArgument, true));

        return substituted.visit(fullEvaluator);
    }

    @Override
    public CalculationResult visitSymbol(StringSymbol symbol) {
        return new CalculationResult(new LinkedList<>(), symbol);
    }

    @Override
    public CalculationResult visitConstant(Constant constant) {
        return new CalculationResult(new LinkedList<>(), constant);
    }
}

package ru.guybydefault.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.cast.AsExpressionVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;
import ru.guybydefault.old.domain.Function;

import java.util.LinkedList;
import java.util.Objects;

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
        if (listParameters != null && Objects.equals(listParameters.getHead(), ListFunctions.List)) {
            // Replace list
            return listParameters.getArguments()
                    .Zip(expression.Arguments)22
                    .Aggregate(
                            funBody,
                            (acc, x) => acc.Visit(new VariableReplacer(x.First, x.Second, true))
                    ).Visit(fullEvaluator);
        }

        var variable = funParameter.Visit(AsStringSymbolVisitor.Instance);

        if (variable == null) {
            throw new ArgumentException("Fun parameter can be only StringSymbol or List. Something gone wrong");
        }

        var functionArgument = expression.getArguments().get(0);
        var substituted = funBody.Visit(new VariableReplacer(variable, functionArgument, true));

        return substituted.Visit(fullEvaluator);
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

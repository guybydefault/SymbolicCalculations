package ru.guybydefault.visitors.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.visitors.cast.AsStringSymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.library.Functions;

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
//        TODO list params
//        if (listParameters != null && Objects.equals(listParameters.getHead(), ListFunctions.List)) {
//            // Replace list
//            return listParameters.getArguments()
//                    .Zip(expression.getArguments())
//                    .Aggregate(
//                            funBody,
//                            (acc, x) => acc.Visit(new VariableReplacer(x.First, x.Second, true))
//                    ).Visit(fullEvaluator);
//        }

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

package ru.guybydefault.visitors.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.attributes.HasAttributeChecker;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.library.Functions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.guybydefault.dsl.library.Attributes.*;

public class ArgumentsEvaluator implements ISymbolVisitor<CalculationResult> {

    private static final HasAttributeChecker HoldAllCompleteChecker = new HasAttributeChecker(HoldAllComplete);
    private static final HasAttributeChecker HoldAllChecker = new HasAttributeChecker(HoldAll);
    private static final HasAttributeChecker HoldRestChecker = new HasAttributeChecker(HoldRest);
    private static final HasAttributeChecker HoldFirstChecker = new HasAttributeChecker(HoldFirst);

    private final FullEvaluator fullEvaluator;

    ArgumentsEvaluator(FullEvaluator fullEvaluator) {
        this.fullEvaluator = fullEvaluator;
    }

    @Override
    public CalculationResult visitExpression(Expression expression) {
        CalculationResult headCalculationResult = expression.getHead().visit(fullEvaluator);
        List<CalculationResult> argsCalculationResult = evaluateArguments(headCalculationResult.getSymbol(), expression.getArguments());

        List<Symbol> argCalculationSteps = new LinkedList<>(headCalculationResult.getSteps());
        List<Symbol> args = new LinkedList<>();
        for (CalculationResult argCalculationResult : argsCalculationResult) {
            argCalculationSteps.addAll(argCalculationResult.getSteps());
            args.add(argCalculationResult.getSymbol());
        }

        return new CalculationResult(argCalculationSteps, new Expression(headCalculationResult.getSymbol(), args));
    }

    @Override
    public CalculationResult visitSymbol(StringSymbol symbol) {
        return new CalculationResult(new LinkedList<>(), symbol);
    }

    @Override
    public CalculationResult visitConstant(Constant constant) {
        return new CalculationResult(new LinkedList<>(), constant);
    }

    private List<CalculationResult> evaluateArguments(
            Symbol head,
            List<Symbol> arguments
    ) {
        if (head.visit(HoldAllCompleteChecker)) {
            return arguments.stream().map(
                    arg -> new CalculationResult(new LinkedList<>(), arg)
            ).collect(Collectors.toList());
        }

        if (head.visit(HoldAllChecker)) {
            return evaluateEagerly(arguments);
        }

        List<CalculationResult> calculationResults = new LinkedList<>();

        if (head.visit(HoldRestChecker)) {
            calculationResults.add(arguments.get(0).visit(fullEvaluator));
            calculationResults.addAll(evaluateEagerly(
                    arguments
                            .stream()
                            .skip(1)
                            .collect(Collectors.toList()))
            );
            return calculationResults;
        }

        if (head.visit(HoldFirstChecker)) {
            calculationResults.addAll(evaluateEagerly(arguments.stream()
                    .limit(1)
                    .collect(Collectors.toList())
            ));
            for (int i = 1; i < arguments.size(); i++) {
                calculationResults.add(arguments.get(i).visit(fullEvaluator));
            }
            return calculationResults;
        }

        return arguments
                .stream()
                .map(arg -> arg.visit(fullEvaluator))
                .collect(Collectors.toList());
    }

    private List<CalculationResult> evaluateEagerly(List<Symbol> symbols) {
        List<CalculationResult> calculationResults = new LinkedList<>();
        for (Symbol symbol : symbols) {
            Expression expression = symbol.visit(AsExpressionVisitor.getInstance());
            if (expression != null && Objects.equals(expression.getHead(), Functions.Evaluate)) {
                for (Symbol expressionArgument : expression.getArguments()) {
                    calculationResults.add(expressionArgument.visit(fullEvaluator));
                }
            } else {
                calculationResults.add(new CalculationResult(new LinkedList<>(), symbol));
            }
        }
        return calculationResults;
    }

}

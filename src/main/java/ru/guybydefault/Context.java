package ru.guybydefault;

import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.implemetations.VariableAssigner;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.evaluation.FullEvaluator;
import ru.guybydefault.visitors.evaluation.GlobalVariablesReplacer;

import java.util.LinkedList;
import java.util.List;

public class Context {

    private int iterations = 0;

    public CalculationResult run(Symbol symbol) {
        VariableAssigner variableAssigner = new VariableAssigner();
        GlobalVariablesReplacer globalVariablesReplacer = new GlobalVariablesReplacer(variableAssigner);

        List<ISymbolVisitor<Symbol>> visitors = new LinkedList<>();
        visitors.add(variableAssigner);
        FullEvaluator fullEvaluator = new FullEvaluator(visitors);

        Symbol currResult = symbol;
        List<Symbol> resultHistory = new LinkedList<>();
        while (iterations <= 1000) {
            resultHistory.add(currResult);

            /**
             * Here we make evaluations, transformations,
             * apply definitions, etc...
             */
            Symbol newResult = currResult
                    .visit(globalVariablesReplacer)
                    .visit(fullEvaluator)
                    .getSymbol();


            /**
             * NOTE
             * 1 Evaluate the head of the expression.
             * 2 Evaluate each element in turn.
             * 3 Apply transformations associated with the attributes Orderless, Listable, and Flat.
             * 4 Apply any definitions that you have given.
             * 5 Apply any built‐in definitions.
             * 6 Evaluate the result.
             */


            if (currResult.equals(newResult)) {
                return new CalculationResult(resultHistory, currResult);
            }
            resultHistory.add(newResult);
            iterations += 1;
        }

        return null;
    }

}

package ru.guybydefault;

import ru.guybydefault.domain.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Context {

    public CalculationResult run(Symbol symbol) {
        Symbol currResult = symbol;
        List<Symbol> resultHistory = new LinkedList<>();
        while (true) {
            resultHistory.add(currResult);

            /**
             * Here we make evaluations, transformations,
             * apply definitions, etc...
             */

            Symbol newResult = null;

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
        }
    }

}

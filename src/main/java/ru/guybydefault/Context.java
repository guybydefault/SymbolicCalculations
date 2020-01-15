package ru.guybydefault;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.implemetations.VariableAssigner;
import ru.guybydefault.dsl.library.Functions;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.evaluation.FullEvaluator;
import ru.guybydefault.visitors.evaluation.GlobalVariablesReplacer;

import java.util.LinkedList;
import java.util.List;
import static ru.guybydefault.dsl.functions.CastingFunctions.*;
import static ru.guybydefault.dsl.functions.ListFunctions.*;
import static ru.guybydefault.dsl.functions.BooleanFunctions.*;
import static ru.guybydefault.dsl.library.Functions.*;
import static ru.guybydefault.dsl.functions.ArithmeticFunctions.*;

public class Context {

    private static Expression DefaultContext = new Expression(Functions.Seq,
            new Expression(SetDelayed, IsConstant, IsConstantImplementation()),
            new Expression(SetDelayed, IsStringSymbol, IsStringSymbolImplementation()),
            new Expression(SetDelayed, IsExpressionWithName, IsExpressionWithNameImplementation()),
            new Expression(SetDelayed, DefaultValue, DefaultValueImplementation()),

            new Expression(SetDelayed, Contains, ContainsImplementation()),
            new Expression(SetDelayed, Concat, ConcatImplementation()),
            new Expression(SetDelayed, CountItem, CountItemImplementation()),
            new Expression(SetDelayed, Filter, FilterImplementation()),
            new Expression(SetDelayed, Map, MapImplementation()),
            new Expression(SetDelayed, Fold, FoldImplementation()),

            new Expression(SetDelayed, ListTimes, ListTimesImplementation()),
            new Expression(SetDelayed, ListPlus, ListPlusImplementation()),
            new Expression(SetDelayed, ListPlusList, ListPlusListImplementation()),

            new Expression(SetDelayed, Minus, MinusImplementation()),
            new Expression(SetDelayed, Or, OrImplementation()),
            new Expression(SetDelayed, And, AndImplementation()),
            new Expression(SetDelayed, More, MoreImplementation()),
            new Expression(SetDelayed, Less, LessImplementation()),
            new Expression(SetDelayed, Not, NotImplementation()),
            new Expression(SetDelayed, While, WhileImplementation())
            );


    public CalculationResult run(Symbol symbol) {
        VariableAssigner variableAssigner = new VariableAssigner();
        GlobalVariablesReplacer globalVariablesReplacer = new GlobalVariablesReplacer(variableAssigner);

        List<ISymbolVisitor<Symbol>> visitors = new LinkedList<>();
        visitors.add(variableAssigner);
        FullEvaluator fullEvaluator = new FullEvaluator(visitors);

        Symbol context = DefaultContext.visit(fullEvaluator).getSymbol();

        Symbol currResult = symbol;
        List<Symbol> resultHistory = new LinkedList<>();
        while (true) {
            resultHistory.add(currResult);

            /**
             * Here we make evaluations, transformations,
             * apply definitions, etc...
             */
            Symbol newResult = currResult
                    .visit(globalVariablesReplacer)
                    .visit(globalVariablesReplacer)
                    .visit(globalVariablesReplacer)
                    .visit(globalVariablesReplacer)
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
        }
    }

}

package ru.guybydefault;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.library.Functions;
import ru.guybydefault.evaluation.FullEvaluator;

import java.util.LinkedList;
import java.util.List;
import static ru.guybydefault.dsl.functions.CastingFunctions.*;
import static ru.guybydefault.dsl.functions.ListFunctions.*;
import static ru.guybydefault.dsl.functions.BooleanFunctions.*;
import static ru.guybydefault.dsl.library.Functions.*;
import static ru.guybydefault.dsl.functions.ArithmeticFunctions.*;

public class Context {

    private static Expression DefaultContext = new Expression(Functions.Seq,
            new Expression(Functions.SetDelayed, IsConstant, IsConstantImplementation()),
            new Expression(Functions.SetDelayed, IsStringSymbol, IsStringSymbolImplementation()),
            new Expression(Functions.SetDelayed, IsExpressionWithName, IsExpressionWithNameImplementation()),
            new Expression(Functions.SetDelayed, DefaultValue, DefaultValueImplementation()),

            new Expression(Functions.SetDelayed, Contains, ContainsImplementation()),
            new Expression(Functions.SetDelayed, Concat, ConcatImplementation()),
            new Expression(Functions.SetDelayed, Count, IsExpressionWithNameImplementation()),




            new Expression(Functions.SetDelayed, IsExpressionWithName, IsExpressionWithNameImplementation()),
            new Expression(Functions.SetDelayed, IsExpressionWithName, IsExpressionWithNameImplementation()),



            );
    Seq[
    SetDelayed[IsConstant, IsConstantImplementation],
    SetDelayed[IsStringSymbol, IsStringSymbolImplementation],
    SetDelayed[IsExpressionWithName, IsExpressionWithNameImplementation],
    SetDelayed[DefaultValue, DefaultValueImplementation],
    //
    SetDelayed[Contains, ContainsImplementation],
    SetDelayed[Concat, ConcatImplementation],
    SetDelayed[CountItem, CountItemImplementation],
    SetDelayed[Filter, FilterImplementation],
    SetDelayed[Map, MapImplementation],
    SetDelayed[Fold, FoldImplementation],
    //
    SetDelayed[Factorial, FactorialImplementation],
    SetDelayed[TaylorSin, TaylorSinImplementation],
    SetDelayed[ListTimes, ListTimesImplementation],
    SetDelayed[ListPlus, ListPlusImplementation],
    //
    SetDelayed[Minus, MinusImplementation],
    SetDelayed[Or, OrImplementation],
    SetDelayed[And, AndImplementation],
    SetDelayed[More, MoreImplementation],
    SetDelayed[Less, LessImplementation],
    SetDelayed[Not, NotImplementation],
    SetDelayed[While, WhileImplementation]
            ];


    public CalculationResult run(Symbol symbol) {
        VariableAssigner variableAssigner = new VariableAssigner();
        GlobalVariablesReplacer globalVariablesReplacer = new GlobalVariablesReplacer(variableAssigner);

        List<ISymbolVisitor<Symbol>> visitors = new LinkedList<>();
        visitors.add(variableAssigner);
        FullEvaluator fullEvaluator = new FullEvaluator(visitors);

        Symbol currResult = symbol;
        List<Symbol> resultHistory = new LinkedList<>();
        while (true) {
            resultHistory.add(currResult);

            /**
             * Here we make evaluations, transformations,
             * apply definitions, etc...
             */
            currResult.visit()
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

package ru.guybydefault;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.implemetations.VariableAssigner;
import ru.guybydefault.dsl.library.Functions;
import ru.guybydefault.io.xml.XMLParser;
import ru.guybydefault.io.xml.XMLPrinterVisitor;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.evaluation.FullEvaluator;
import ru.guybydefault.visitors.evaluation.GlobalVariablesReplacer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static ru.guybydefault.dsl.functions.ArithmeticFunctions.*;
import static ru.guybydefault.dsl.functions.BooleanFunctions.*;
import static ru.guybydefault.dsl.functions.CastingFunctions.*;
import static ru.guybydefault.dsl.functions.ListFunctions.*;
import static ru.guybydefault.dsl.functions.MatrixFunctions.MatrixPlus;
import static ru.guybydefault.dsl.library.Functions.Seq;
import static ru.guybydefault.dsl.library.Functions.SetDelayed;

public class Context {

    private static final String DEBUG_FOLDER_NAME = "t_debug";
    private static XMLParser xmlParser;
    private static Expression DefaultContext;

    static {
        try {
            xmlParser = new XMLParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            DefaultContext = new Expression(Functions.Seq,
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
                    new Expression(SetDelayed, While, WhileImplementation()),

                    //adding matrix functions in context
                    new Expression(SetDelayed, MatrixPlus, xmlParser.parse("src/main/resources/matrix_add.xml"))
//                    ,
//                        new Expression(SetDelayed, MatrixMult, xmlParser.parse("src/main/resources/matrix_muls.xml"))
            );
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private int iterations = 0;

    public CalculationResult run(Symbol symbol) {
        VariableAssigner variableAssigner = new VariableAssigner();
        GlobalVariablesReplacer globalVariablesReplacer = new GlobalVariablesReplacer(variableAssigner);

        List<ISymbolVisitor<Symbol>> visitors = new LinkedList<>();
        visitors.add(variableAssigner);
        FullEvaluator fullEvaluator = new FullEvaluator(visitors);

        Symbol context = DefaultContext.visit(fullEvaluator).getSymbol();
        symbol = new Expression(Seq, context, symbol);

        try {
            FileUtils.deleteDirectory(new File(DEBUG_FOLDER_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Symbol currResult = symbol;
        List<Symbol> resultHistory = new LinkedList<>();
        resultHistory.add(currResult);
        while (iterations <= 1000) {
            /**
             * Here we make evaluations, transformations,
             * apply definitions, etc...
             */
            Symbol newResult = currResult
                    .visit(globalVariablesReplacer);
            debugIteration("iteration_variable_replacer_" + (iterations + 1) + ".xml", newResult);

            CalculationResult calculationResult = newResult.visit(fullEvaluator);
            resultHistory.addAll(calculationResult.getSteps());
            newResult = calculationResult.getSymbol();
            debugIteration("iteration_full_" + (iterations + 1) + ".xml", newResult);

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
            currResult = newResult;
            resultHistory.add(newResult);
            iterations += 1;
        }

        return null;
    }

    private void debugIteration(String fileName, Symbol currResult) {
        try {
            new File(DEBUG_FOLDER_NAME).mkdir();
            XMLPrinterVisitor xmlPrinterVisitor = new XMLPrinterVisitor(FileUtils.getFile(DEBUG_FOLDER_NAME, fileName));
            xmlPrinterVisitor.print(currResult);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

}

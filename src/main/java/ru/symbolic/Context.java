package ru.symbolic;

import org.apache.commons.io.FileUtils;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.implemetations.VariableAssigner;
import ru.symbolic.dsl.library.Functions;
import ru.symbolic.io.xml.XMLParser;
import ru.symbolic.io.xml.XMLPrinterVisitor;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.visitors.cast.AsExpressionVisitor;
import ru.symbolic.visitors.evaluation.FullEvaluator;
import ru.symbolic.visitors.evaluation.GlobalVariablesReplacer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static ru.symbolic.dsl.functions.ArithmeticFunctions.Minus;
import static ru.symbolic.dsl.functions.ArithmeticFunctions.MinusImplementation;
import static ru.symbolic.dsl.functions.BooleanFunctions.*;
import static ru.symbolic.dsl.functions.CastingFunctions.*;
import static ru.symbolic.dsl.library.Functions.Seq;
import static ru.symbolic.dsl.library.Functions.SetDelayed;

public class Context {

    private static final String DEBUG_FOLDER_NAME = "t_debug";
    private static XMLParser xmlParser;
    private static Expression DefaultContext;

    static {
        try {
            xmlParser = new XMLParser();
        } catch (ParserConfigurationException | IllegalAccessException e) {
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

                    new Expression(SetDelayed, Minus, MinusImplementation()),
                    new Expression(SetDelayed, Or, OrImplementation()),
                    new Expression(SetDelayed, And, AndImplementation()),
                    new Expression(SetDelayed, Less, LessImplementation()),
                    new Expression(SetDelayed, More, MoreImplementation()),
                    new Expression(SetDelayed, Not, NotImplementation()),
                    new Expression(SetDelayed, While, WhileImplementation()),

                    xmlParser.parse("src/main/resources/first.xml"),
                    xmlParser.parse("src/main/resources/rest.xml"),
                    xmlParser.parse("src/main/resources/simplify.xml"),
                    xmlParser.parse("src/main/resources/simplify1.xml"),
                    xmlParser.parse("src/main/resources/simplify2.xml"),
                    xmlParser.parse("src/main/resources/const_compact.xml"),
                    xmlParser.parse("src/main/resources/const_compact1.xml"),
                    xmlParser.parse("src/main/resources/const_compact2.xml"),
                    xmlParser.parse("src/main/resources/flatten_brackets.xml")
                    ,

//                    xmlParser.parse("src/main/resources/mul1.xml"),
                    xmlParser.parse("src/main/resources/mul2.xml"),
                    xmlParser.parse("src/main/resources/mul3.xml"),
                    xmlParser.parse("src/main/resources/mul4.xml"),
                    xmlParser.parse("src/main/resources/mul5.xml"),
                    xmlParser.parse("src/main/resources/pow.xml")


            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        debugIteration("before.xml", currResult);

        int iterations = 0;
        while (iterations <= 100000) {
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
                // skipping DefaultContext SetDelayed stuff when returning result
                currResult = currResult.visit(AsExpressionVisitor.getInstance()).getArguments().get(DefaultContext.getArguments().size());
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

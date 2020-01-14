package ru.guybydefault.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.attributes.ArgumentsSorter;
import ru.guybydefault.attributes.FlatFlattener;
import ru.guybydefault.attributes.OneIdentityShrinker;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.implemetations.*;
import ru.guybydefault.implemetations.booleanFunctions.CompareImplementation;
import ru.guybydefault.implemetations.booleanFunctions.EqImplementation;
import ru.guybydefault.implemetations.booleanFunctions.IfImplementation;
import ru.guybydefault.implemetations.casting.AsConstantImplementation;
import ru.guybydefault.implemetations.casting.AsExpressionArgsImplementation;
import ru.guybydefault.implemetations.casting.AsStringSymbolImplementation;
import ru.guybydefault.implemetations.listfunctions.AppendImplementation;
import ru.guybydefault.implemetations.listfunctions.DistinctImplementation;
import ru.guybydefault.implemetations.listfunctions.FastMapImplementation;
import ru.guybydefault.implemetations.listfunctions.LengthImplementation;

import java.util.LinkedList;
import java.util.List;

public class FullEvaluator implements ISymbolVisitor<CalculationResult> {
    public static final FullEvaluator Default = new FullEvaluator();

    private static final OneIdentityShrinker OneIdentityShrinker = new OneIdentityShrinker();
    private static final FlatFlattener FlatFlattener = new FlatFlattener();

    private static final ArgumentsSorter ArgumentsSorter = new ArgumentsSorter();
    private static final PlusImplementation PlusImplementation = new PlusImplementation();
    private static final TimesImplementation TimesImplementation = new TimesImplementation();

    private static final IfImplementation IfImplementation = new IfImplementation();
    //    private static final PartImplementation PartImplementation = new PartImplementation();
    private static final AppendImplementation AppendImplementation = new AppendImplementation();
    private static final EqImplementation EqImplementation = new EqImplementation();
    private static final CompareImplementation CompareImplementation = new CompareImplementation();

    private static final AsConstantImplementation AsConstant = new AsConstantImplementation();
    private static final AsStringSymbolImplementation AsStringSymbol = new AsStringSymbolImplementation();
    private static final AsExpressionArgsImplementation AsExpressionArgs = new AsExpressionArgsImplementation();
    private static final ApplyListImplementation ApplyListImplementation = new ApplyListImplementation();
    private static final GenerateListImplementation GenerateList = new GenerateListImplementation();
    private static final LengthImplementation LengthImplementation = new LengthImplementation();
    private static final DistinctImplementation DistinctImplementation = new DistinctImplementation();
    private static final RangeImplementation RangeImplementation = new RangeImplementation();
    private static final FastMapImplementation FastMapImplementation = new FastMapImplementation();

    private final ArgumentsEvaluator argumentsEvaluator;
    private final FunctionEvaluator functionEvaluator;
    private final List<ISymbolVisitor<Symbol>> visitors;
    private List<ISymbolVisitor<Symbol>> flow;

    public FullEvaluator() {
        this(null);
    }

    public FullEvaluator(List<ISymbolVisitor<Symbol>> visitors) {
        if (visitors == null) {
            this.visitors = new LinkedList<>();
        } else {
            this.visitors = visitors;
        }
        argumentsEvaluator = new ArgumentsEvaluator(this);
        functionEvaluator = new FunctionEvaluator(this);
    }


    @Override
    public CalculationResult visitExpression(Expression expression) {
        List<ISymbolVisitor<Symbol>> visitors = getFlow();
        CalculationResult argCalculationResult = expression.visit(argumentsEvaluator);
        CalculationResult funcCalculationResult = argCalculationResult.getResult().visit(functionEvaluator);

        List<Symbol> steps = new LinkedList<>();
        steps.addAll(argCalculationResult.getSteps());
        steps.add(argCalculationResult.getResult());
        steps.addAll(funcCalculationResult.getSteps());
        steps.add(funcCalculationResult.getResult());

        Symbol currSymbol = funcCalculationResult.getResult();
        for (ISymbolVisitor<Symbol> visitor : visitors) {
            currSymbol = currSymbol.visit(visitor);
            steps.add(currSymbol);
        }
        return new CalculationResult(steps, currSymbol);
    }

    @Override
    public CalculationResult visitSymbol(StringSymbol symbol) {
        return new CalculationResult(new LinkedList<>(), symbol);
    }

    @Override
    public CalculationResult visitConstant(Constant constant) {
        return new CalculationResult(new LinkedList<>(), constant);
    }

    private List<ISymbolVisitor<Symbol>> getFlow() {
        if (flow != null) {
            return flow;
        }

        flow = new LinkedList<ISymbolVisitor<Symbol>>(visitors);

        flow.add(FlatFlattener);
        flow.add(ArgumentsSorter);
        flow.add(OneIdentityShrinker);
        // Implementations
        flow.add(PlusImplementation);
        flow.add(TimesImplementation);
        flow.add(IfImplementation);
        flow.add(EqImplementation);
        flow.add(CompareImplementation);
//                PartImplementation,
        flow.add(AppendImplementation);
        flow.add(AsConstant);
        flow.add(AsStringSymbol);
        flow.add(AsExpressionArgs);
        flow.add(ApplyListImplementation);
        flow.add(LengthImplementation);
        flow.add(DistinctImplementation);
        flow.add(RangeImplementation);
//                GroupImplementation,
        flow.add(FastMapImplementation);
        flow.add(GenerateList);

        return flow;
    }
}

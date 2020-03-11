package ru.guybydefault.visitors.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.implemetations.*;
import ru.guybydefault.dsl.implemetations.booleanFunctions.CompareImplementation;
import ru.guybydefault.dsl.implemetations.booleanFunctions.EqImplementation;
import ru.guybydefault.dsl.implemetations.booleanFunctions.IfImplementation;
import ru.guybydefault.dsl.implemetations.casting.AsConstantImplementation;
import ru.guybydefault.dsl.implemetations.casting.AsExpressionArgsImplementation;
import ru.guybydefault.dsl.implemetations.casting.AsStringSymbolImplementation;
import ru.guybydefault.dsl.implemetations.listfunctions.*;
import ru.guybydefault.dsl.implemetations.matrixFunctions.DeterminerImplementation;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.attributes.FlatHandler;
import ru.guybydefault.visitors.attributes.OneIdentityHandler;
import ru.guybydefault.visitors.attributes.OrderlessHandler;

import java.util.LinkedList;
import java.util.List;

public class FullEvaluator implements ISymbolVisitor<CalculationResult> {
    public static final FullEvaluator Default = new FullEvaluator();

    private static final OneIdentityHandler OneIdentityShrinker = new OneIdentityHandler();
    private static final FlatHandler FlatFlattener = new FlatHandler();

    private static final OrderlessHandler ArgumentsSorter = new OrderlessHandler();
    private static final PlusImplementation PlusImplementation = new PlusImplementation();
    private static final TimesImplementation TimesImplementation = new TimesImplementation();

    private static final IfImplementation IfImplementation = new IfImplementation();
    private static final PartImplementation PartImplementation = new PartImplementation();
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
    private static final FMapImplementation FastMapImplementation = new FMapImplementation();

    private static final DeterminerImplementation DeterminerImplementation = new DeterminerImplementation();

    private final ArgumentsEvaluator argumentsEvaluator;
    private final FunctionEvaluator functionEvaluator;
    private final List<ISymbolVisitor<Symbol>> visitors;
    private List<ISymbolVisitor<Symbol>> flow;
    private final RangeImplementation rangeImplementation = new RangeImplementation();

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
        CalculationResult funcCalculationResult = argCalculationResult.getSymbol().visit(functionEvaluator);

        List<Symbol> steps = new LinkedList<>();
        steps.addAll(argCalculationResult.getSteps());
        steps.add(argCalculationResult.getSymbol());
        steps.addAll(funcCalculationResult.getSteps());
        steps.add(funcCalculationResult.getSymbol());

        Symbol currSymbol = funcCalculationResult.getSymbol();
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
        flow.add(PartImplementation);
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
        //matrix impl
        flow.add(DeterminerImplementation);

        return flow;
    }
}

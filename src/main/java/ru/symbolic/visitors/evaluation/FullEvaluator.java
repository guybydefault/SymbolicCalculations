package ru.symbolic.visitors.evaluation;

import ru.symbolic.CalculationResult;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.implemetations.*;
import ru.symbolic.dsl.implemetations.booleanFunctions.CompareImplementation;
import ru.symbolic.dsl.implemetations.booleanFunctions.EqImplementation;
import ru.symbolic.dsl.implemetations.booleanFunctions.IfImplementation;
import ru.symbolic.dsl.implemetations.casting.AsConstantImplementation;
import ru.symbolic.dsl.implemetations.casting.AsExpressionArgsImplementation;
import ru.symbolic.dsl.implemetations.casting.AsStringSymbolImplementation;
import ru.symbolic.dsl.implemetations.listfunctions.*;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.visitors.attributes.FlatHandler;
import ru.symbolic.visitors.attributes.OneIdentityHandler;
import ru.symbolic.visitors.attributes.OrderlessHandler;

import java.util.LinkedList;
import java.util.List;

public class FullEvaluator implements ISymbolVisitor<CalculationResult> {
    public static final FullEvaluator Default = new FullEvaluator();

    private static final OneIdentityHandler OneIdentityShrinker = new OneIdentityHandler();
    private static final FlatHandler FlatFlattener = new FlatHandler();

    private static final OrderlessHandler ArgumentsSorter = new OrderlessHandler();
    private static final PlusImplementation PlusImplementation = new PlusImplementation();
    private static final TimesImplementation TimesImplementation = new TimesImplementation();
    private static final DivideImplementation DivideImplementation = new DivideImplementation();
    private static final PowerImplementation PowerImplementation = new PowerImplementation();

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
    private static final MapImplementation FastMapImplementation = new MapImplementation();
    private static final ListSumImplementation ListSumImplementation = new ListSumImplementation();
    private static final ListSeqImplementation ListSeqImplementation = new ListSeqImplementation();

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

    public static ListSeqImplementation getListSeqImplementation() {
        return ListSeqImplementation;
    }

    private List<ISymbolVisitor<Symbol>> getFlow() {
        if (flow != null) {
            return flow;
        }

        flow = new LinkedList<ISymbolVisitor<Symbol>>(visitors);




        flow.add(FlatFlattener);
        /**
         * it is important that ListSeqImplementation comes before OneIdentityShrinker, cuz it opens Seq
         * and oneidentity can delete plus because of the one seq arg inside
         */
        flow.add(ListSeqImplementation);

        flow.add(ArgumentsSorter);
        flow.add(OneIdentityShrinker);
        // Implementations

        flow.add(PlusImplementation);
        flow.add(TimesImplementation);
        flow.add(PowerImplementation);
        flow.add(DivideImplementation);
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
        flow.add(FastMapImplementation);
        flow.add(GenerateList);
        flow.add(ListSumImplementation);
        return flow;
    }
}

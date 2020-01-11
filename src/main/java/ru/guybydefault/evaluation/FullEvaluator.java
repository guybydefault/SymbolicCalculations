package ru.guybydefault.evaluation;

import ru.guybydefault.CalculationResult;
import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;

import java.util.LinkedList;
import java.util.List;
import static ru.guybydefault.dsl.functions.ListFunctions.*;

public class FullEvaluator implements ISymbolVisitor {
    public static final FullEvaluator Default = new FullEvaluator();

    private static final OneIdentityShrinker OneIdentityShrinker = new OneIdentityShrinker();
    private static final FlatFlattener FlatFlattener = new FlatFlattener();

    private static final ArgumentsSorter ArgumentsSorter = new ArgumentsSorter();
    private static final PlusImplementation PlusImplementation = new PlusImplementation();
    private static final TimesImplementation TimesImplementation = new TimesImplementation();

    private static final SinFunctionImplementation SinFunctionImplementation = new SinFunctionImplementation();
    private static final IfImplementation IfImplementation = new IfImplementation();
    private static final PartImplementation PartImplementation = new PartImplementation();
    private static final AppendImplementation AppendImplementation = new AppendImplementation();
    private static final EqImplementation EqImplementation = new EqImplementation();
    private static final CompareImplementation CompareImplementation = new CompareImplementation();
    private static final PowerImplementation PowerImplementation = new PowerImplementation();

    private static final AsConstantImplementation AsConstant = new AsConstantImplementation();
    private static final AsStringSymbolImplementation AsStringSymbol = new AsStringSymbolImplementation();
    private static final AsExpressionArgsImplementation AsExpressionArgs = new AsExpressionArgsImplementation();
    private static final ApplyListImplementation ApplyListImplementation = new ApplyListImplementation();
    private static final GenerateListImplementation GenerateList = new GenerateListImplementation();
    private static final DivideImplementation DivideImplementation = new DivideImplementation();
    private static final LengthImplementation LengthImplementation = new LengthImplementation();
    private static final DistinctImplementation DistinctImplementation = new DistinctImplementation();
    private static final GroupImplementation GroupImplementation = new GroupImplementation();
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
        this.visitors = visitors;
        argumentsEvaluator = new ArgumentsEvaluator(this);
        functionEvaluator = new FunctionEvaluator(this);
    }


    @Override
    public CalculationResult visitExpression(Expression expression) {
        List<ISymbolVisitor<Symbol>> visitors = getFlow();
        CalculationResult argCalculationResult = expression.visit(argumentsEvaluator);
        CalculationResult funcCalculationResult = argCalculationResult.getResult().visit(functionEvaluator);


//        var steps = ImmutableList<Symbol>.Empty
//                .AddRange(argSteps)
//                .Add(argSymbol)
//                .AddRange(funcSteps);
//
//        return visitors.Aggregate(
//                (steps, funcSymbol),
//        (state, visitor) => {
//            var (steps, symbol) = state;
//            var visited = symbol.Visit(visitor);
//
//            return (steps.Add(visited), visited);
//        });
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

        flow = visitors.add(new ISymbolVisitor<Symbol>[] {
                FlatFlattener,
                ArgumentsSorter,
                OneIdentityShrinker,
                // Implementations
                PlusImplementation,
                TimesImplementation,
                DivideImplementation,
                SinFunctionImplementation,
                IfImplementation,
                EqImplementation,
                CompareImplementation,
                PartImplementation,
                AppendImplementation,
                AsConstant,
                AsStringSymbol,
                AsExpressionArgs,
                ApplyListImplementation,
                PowerImplementation,
                LengthImplementation,
                DistinctImplementation,
                RangeImplementation,
                GroupImplementation,
                FastMapImplementation,
                GenerateList
                // Last
        });

        return flow;
    }
}

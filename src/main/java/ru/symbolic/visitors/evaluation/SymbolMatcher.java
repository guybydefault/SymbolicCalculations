package ru.symbolic.visitors.evaluation;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Functions;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.visitors.attributes.HasAttributeChecker;
import ru.symbolic.visitors.cast.AsExpressionVisitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.symbolic.dsl.library.Attributes.*;

public class SymbolMatcher implements ISymbolVisitor<Symbol> {

    private static final HasAttributeChecker HoldAllChecker = new HasAttributeChecker(HoldAll);
    private static final HasAttributeChecker HoldRestChecker = new HasAttributeChecker(HoldRest);
    private static final HasAttributeChecker HoldFirstChecker = new HasAttributeChecker(HoldFirst);

    private final Expression pattern;
    private final Symbol replace;
    private final boolean eager;


    public SymbolMatcher(Expression pattern, Symbol replace) {
        this(pattern, replace, false);
    }

    public SymbolMatcher(Expression pattern, Symbol replace, boolean eager) {
        this.pattern = pattern;
        this.replace = replace;
        this.eager = eager;
    }

    @Override
    public Symbol visitExpression(Expression expression) {
        MatchState matchState = new MatchState(pattern, expression).match();
        if (matchState.isMatched()) {
            return getReplace(matchState);
        }

        Symbol head = expression.getHead().visit(this);

        if (!eager) {
            if (head.visit(HoldAllChecker)) {
                return new Expression(head, expression.getArguments());
            }

            if (head.visit(HoldFirstChecker)) {
                List<Symbol> arguments = new LinkedList<>();
                for (int i = 0; i < expression.getArguments().size(); i++) {
                    Symbol symbol = expression.getArguments().get(i);
                    if (i != 0) {
                        arguments.add(symbol.visit(this));
                    } else {
                        arguments.add(symbol);
                    }
                }
                return new Expression(head, arguments);
            }


            if (head.visit(HoldRestChecker)) {
                List<Symbol> arguments = new LinkedList<>();
                for (int i = 0; i < expression.getArguments().size(); i++) {
                    Symbol symbol = expression.getArguments().get(i);
                    if (i == 0) {
                        arguments.add(symbol.visit(this));
                    } else {
                        arguments.add(symbol);
                    }
                }
                return new Expression(head, arguments);
            }
        }

        return new Expression(head,
                expression.getArguments()
                        .stream()
                        .map(x -> x.visit(this))
                        .collect(Collectors.toList()));
    }

    @Override
    public Symbol visitSymbol(StringSymbol symbol) {
        MatchState matchState = new MatchState(pattern, symbol);
        if (matchState.match().isMatched()) {
            return getReplace(matchState);
        }
        return symbol;
    }

    @Override
    public Symbol visitConstant(Constant constant) {
        MatchState matchState = new MatchState(pattern, constant);
        if (matchState.match().isMatched()) {
            return getReplace(matchState);
        }
        return constant;
    }


    private Symbol getReplace(MatchState matchState) {
        Symbol expr = replace;
        for (Map.Entry<String, Symbol> entry : matchState.getLocalPatternVariables().entrySet()) {
            expr = expr.visit(new VariableReplacer(new StringSymbol(entry.getKey()), entry.getValue(), true));
        }
        expr = expr.visit(FullEvaluator.getListSeqImplementation());
        return expr;
    }
}

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
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.symbolic.dsl.library.Attributes.*;


public final class VariableReplacer implements ISymbolVisitor<Symbol> {
    private static final HasAttributeChecker HoldAllChecker = new HasAttributeChecker(HoldAll);
    private static final HasAttributeChecker HoldRestChecker = new HasAttributeChecker(HoldRest);
    private static final HasAttributeChecker HoldFirstChecker = new HasAttributeChecker(HoldFirst);

    private final Symbol funcArgument;
    private final Symbol variable;
    private final boolean eager;

    public VariableReplacer(Symbol variable, Symbol funcArgument) {
        this(variable, funcArgument, false);
    }

    public VariableReplacer(Symbol variable, Symbol funcArgument, boolean eager) {
        this.variable = variable;
        this.funcArgument = funcArgument;
        this.eager = eager;
    }

    public Symbol visitExpression(Expression expression) {
        Symbol head = expression.getHead().visit(this);

        if (Objects.equals(head, Functions.Fun)) {
            if (Objects.equals(expression.getArguments().get(0), variable)) {
                return expression;
            }
            Expression arg = expression.getArguments().get(0).visit(AsExpressionVisitor.getInstance());
            if (arg != null && Objects.equals(arg.getHead(), ListFunctions.List)) {
                for (Symbol listArg : arg.getArguments()) {
                    if (Objects.equals(listArg, variable)) {
                        return expression;
                    }
                }
            }
        }

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
        return Objects.equals(symbol, variable) ? funcArgument : symbol;
    }

    @Override
    public Symbol visitConstant(Constant constant) {
        return constant;
    }

}

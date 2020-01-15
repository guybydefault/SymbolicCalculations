package ru.guybydefault.visitors.evaluation;

import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.visitors.attributes.HasAttributeChecker;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.library.Functions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.guybydefault.dsl.library.Attributes.*;


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

            if (Objects.equals(head, Functions.Fun) && Objects.equals(expression.getArguments().get(0), variable)) {
                return expression;
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

package ru.guybydefault.evaluation;

import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.attributes.HasAttributeChecker;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;

import java.util.Objects;

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

            if (Equals(head, Fun) && Equals(expression.getArguments().get(0), variable)) {
                return expression;
            }

            if (!eager) {
                if (head.Visit(HoldAllChecker)) {
                    return head[expression.Arguments.ToArray()];
                }

                if (head.Visit(HoldFirstChecker)) {
                    return head[
                            expression.Arguments
                                    .Select((x, i) => i != 0 ? x.Visit(this) : x)
                            .ToArray()
                    ];
                }


                if (head.Visit(HoldRestChecker)) {
                    return head[
                            expression.Arguments
                                    .Select((x, i) => i == 0 ? x.Visit(this) : x)
                            .ToArray()
                    ];
                }
            }

            var arguments = expression.Arguments
                    .Select(x => x.Visit(this))
                .ToArray();

            return head[arguments];
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

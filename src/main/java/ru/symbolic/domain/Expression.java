package ru.symbolic.domain;

import ru.symbolic.visitors.ISymbolVisitor;

import java.util.*;

import static ru.symbolic.dsl.library.Functions.SetDelayed;

public final class Expression extends Symbol {

    private final Symbol head;
    private final List<Symbol> arguments;

    public Expression(Symbol head, Symbol ... arguments) {
        this(head, List.of(arguments));
    }

    public Expression(Symbol head, List<Symbol> arguments) {
        this.head = head;
        this.arguments = Collections.unmodifiableList(arguments);
    }

    @Override
    protected <T> T visitImplementation(ISymbolVisitor<T> visitor) {
        return visitor.visitExpression(this);
    }

    public List<Symbol> getArguments() {
        return arguments;
    }

    public Symbol getHead() {
        return head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return head.equals(that.head)
               && this.arguments.containsAll(that.getArguments()) && that.getArguments().containsAll(this.getArguments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, arguments);
    }

    @Override
    public String toString() {
        if (head.equals(SetDelayed)) {
            return "";
        }
        return "Expression{" +
                "head=" + head +
                ", arguments=" + arguments +
                '}';
    }
}

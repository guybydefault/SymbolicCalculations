package ru.guybydefault.domain;

import ru.guybydefault.visitors.ISymbolVisitor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final  class Expression extends Symbol {

    private Symbol head;
    private List<Symbol> arguments;

    public Expression(Symbol head, Symbol ... arguments) {
        this(head, Arrays.asList(arguments));
    }

    public Expression(Symbol head, List<Symbol> arguments) {
        this.head = head;
        this.arguments = arguments;
    }

    public static Expression newInstance(Symbol head, Symbol arg) {
        List<Symbol> args = new LinkedList<>();
        args.add(arg);
        return new Expression(head, args);
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
}

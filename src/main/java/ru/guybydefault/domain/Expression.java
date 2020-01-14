package ru.guybydefault.domain;

import ru.guybydefault.ISymbolVisitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    protected Object visitImplementation(ISymbolVisitor visitor) {
        return visitor.visitExpression(this);
    }

    public List<Symbol> getArguments() {
        return arguments;
    }

    public Symbol getHead() {
        return head;
    }

    public boolean equals(Expression other) {
        return this.head.equals(other.head) && this.arguments.equals(other.arguments);
    }
}

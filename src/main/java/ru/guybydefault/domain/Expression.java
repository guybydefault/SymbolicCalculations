package ru.guybydefault.domain;

import ru.guybydefault.ISymbolVisitor;

import java.util.List;

public final  class Expression extends Symbol {

    private Symbol head;
    private List<Symbol> arguments;

    public Expression(Symbol hesd, List<Symbol> arguments) {
        this.head = hesd;
        this.arguments = arguments;
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

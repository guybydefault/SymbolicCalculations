package ru.symbolic.domain;

import ru.symbolic.visitors.ISymbolVisitor;

public abstract class Symbol {

    public <T> T visit(ISymbolVisitor<T> visitor) {
        return visitImplementation(visitor);
    }

    protected abstract <T> T visitImplementation(ISymbolVisitor<T> visitor);

    @Override
    public abstract String toString();
}

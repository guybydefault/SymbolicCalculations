package ru.guybydefault.domain;

import ru.guybydefault.ISymbolVisitor;

public abstract class Symbol {

    public <T> T visit(ISymbolVisitor<T> visitor) {
        return visitImplementation(visitor);
    }

    protected abstract <T> T visitImplementation(ISymbolVisitor<T> visitor);

}

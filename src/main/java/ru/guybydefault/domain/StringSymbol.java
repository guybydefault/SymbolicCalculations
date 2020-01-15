package ru.guybydefault.domain;

import ru.guybydefault.visitors.ISymbolVisitor;

public final class StringSymbol extends Symbol {

    private final String name;

    private final StringSymbol[] attributes;

    public StringSymbol(String name) {
        this.name = name;
        this.attributes = new StringSymbol[]{};
    }

    public StringSymbol(String name, StringSymbol[] attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public StringSymbol[] getAttributes() {
        return attributes;
    }

    @Override
    protected <T> T visitImplementation(ISymbolVisitor<T> visitor) {
        return visitor.visitSymbol(this);
    }

    public boolean equals(StringSymbol other) {
        return this.name.equals(other.name);
    }
}

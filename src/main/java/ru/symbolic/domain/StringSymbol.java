package ru.symbolic.domain;

import ru.symbolic.visitors.ISymbolVisitor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSymbol that = (StringSymbol) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "StringSymbol{" +
                "name='" + name + '\'' +
                '}';
    }
}

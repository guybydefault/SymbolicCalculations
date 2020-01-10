package ru.guybydefault;

public class Constant extends Symbol {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    protected Object visitImplementation(ISymbolVisitor visitor) {
        return visitor.visitConstant(this);
    }

    public boolean equals(Constant other) {
        return this.value == other.value;
    }
}

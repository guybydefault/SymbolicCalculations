package ru.symbolic;

import ru.symbolic.domain.Symbol;

import java.util.List;
import java.util.Objects;

public class CalculationResult {

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    private final List<Symbol> steps;
    private final Symbol symbol;

    public CalculationResult(List<Symbol> steps, Symbol symbol) {
        this.steps = steps;
        this.symbol = symbol;
    }

    public List<Symbol> getSteps() {
        return steps;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationResult that = (CalculationResult) o;
        return Objects.equals(symbol, that.symbol);
    }
}

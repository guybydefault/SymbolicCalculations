package ru.guybydefault;

import ru.guybydefault.domain.Symbol;

import java.util.List;

public class CalculationResult {

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
}

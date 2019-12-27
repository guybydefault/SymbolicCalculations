package ru.guybydefault;

import java.util.List;

public class CalculationResult {

    private final List<Symbol> steps;
    private final Symbol result;

    public CalculationResult(List<Symbol> steps, Symbol result) {
        this.steps = steps;
        this.result = result;
    }

    public List<Symbol> getSteps() {
        return steps;
    }

    public Symbol getResult() {
        return result;
    }
}

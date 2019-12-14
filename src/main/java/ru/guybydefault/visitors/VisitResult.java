package ru.guybydefault.visitors;

public class VisitResult {

    private boolean hasChangedExpression;

    public VisitResult(boolean hasChangedExpression) {
        this.hasChangedExpression = hasChangedExpression;
    }

    public boolean isHasChangedExpression() {
        return hasChangedExpression;
    }

    public void setHasChangedExpression(boolean hasChangedExpression) {
        this.hasChangedExpression = hasChangedExpression;
    }
}

package ru.guybydefault.domain;

public enum BinaryOpType {
    PLUS ("plus"),
    MINUS ("minus"),
    MULTIPLY ("mult"),
    DIVIDE ("div");

    private final String title;

    BinaryOpType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toString(Expression arg1, Expression arg2) {
        switch (this) {
            case PLUS: return "<mo>(</mo>\n"
                    + arg1.toString()
                    + "<mo>+</mo>\n"
                    + arg2.toString()
                    + "<mo>)</mo>\n";
            case MINUS: return "<mo>(</mo>\n"
                    + arg1.toString()
                    + "<mo>-</mo>\n"
                    + arg2.toString()
                    + "<mo>)</mo>\n";
            case MULTIPLY: return "<mo>(</mo>\n"
                    + arg1.toString()
                    + "<mo>*</mo>\n"
                    + arg2.toString()
                    + "<mo>)</mo>\n";
            case DIVIDE: return "<mfrac>\n"
                    + "<mrow>\n"
                    + arg1.toString()
                    + "</mrow>\n"
                    + "<mrow>\n"
                    + arg2.toString()
                    + "</mrow>\n"
                    + "</mfrac>\n";
            default: throw new IllegalArgumentException("There's no other types in this enum");
        }
    }
}

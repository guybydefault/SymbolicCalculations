package ru.guybydefault.domain;

public enum UnaryOpType {
    PLUS ("plus"),
    MINUS ("minus"),
    TRANSPOSE("transpose"),
    MODULE ("module");

    private String title;

    UnaryOpType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toString(Expression arg) {
        switch (this) {
            case PLUS: return arg.toString();
            case MINUS: return "<mo>-</mo>\n"
                    + arg.toString();
            case MODULE: return "<mo>|</mo>\n"
                    + arg.toString()
                    + "<mo>|</mo>\n";
            case TRANSPOSE: return "<msup>\n"
                    + "<mrow>\n"
                    + "<mo>(</mo>\n"
                    + arg.toString()
                    + "<mo>)</mo>\n"
                    + "</mrow>\n"
                    + "<mi>T</mi>\n"
                    + "</msup>\n";
            default: throw new IllegalArgumentException("There's no other types in this enum");
        }
    }
}

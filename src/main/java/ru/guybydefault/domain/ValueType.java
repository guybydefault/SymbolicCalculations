package ru.guybydefault.domain;

public enum ValueType {
    CONST ("const"),
    VARIABLE ("var");

    private String title;

    ValueType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toString(String value) {
        switch (this) {
            case CONST: return "<mn>" + value + "</mn>\n";
            case VARIABLE: return "<mo>" + value + "</mo>\n";
            default: throw new IllegalArgumentException("There's no other types in this enum");
        }
    }
}

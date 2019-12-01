package ru.guybydefault.domain;

public enum  BinaryOpType {
    PLUS ("plus"),
    MINUS ("minus"),
    MULTIPLY ("mult"),
    DIVIDE ("div");

    private String title;

    BinaryOpType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "";
    }
}

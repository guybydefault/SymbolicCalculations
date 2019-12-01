package ru.guybydefault.domain;

public enum UnaryOpType {
    PLUS ("plus"),
    MINUS ("minus");

    private String title;

    UnaryOpType(String title) {
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

package ru.guybydefault.domain;

public enum FunctionType {
    LOG ("log"),
    EXP ("exp"),
    POW ("pow"),
    SIN ("sin"),
    COS ("cos"),
    TG ("tg"),
    CTG ("ctg"),
    ARCSIN ("arcsin"),
    ARCCOS ("arccos"),
    ARCTG ("arctg"),
    ARCCTG ("arcctg");

    private String title;

    FunctionType(String title) {
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

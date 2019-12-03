package ru.guybydefault.domain;

public enum FunctionType {
    LOG("log"),
    EXP("exp"),
    POW("pow"),
    SIN("sin"),
    COS("cos"),
    TG("tg"),
    CTG("ctg"),
    ARCSIN("arcsin"),
    ARCCOS("arccos"),
    ARCTG("arctg"),
    ARCCTG("arcctg"),
    MODULE("module");

    private String name;

    FunctionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static FunctionType findByName(String name) {
        for (FunctionType functionType : values()) {
            if (functionType.getName().equals(name)) {
                return functionType;
            }
        }
        return null;
    }

    public String toString(Expression arg1, Expression arg2) {
        switch (this) {
            case LOG:
                if (arg2 == null) {
                    return "<mi>ln</mi>\n"
                            + "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                            + arg1.toString()
                            + "</mfenced>\n";
                } else {
                    return "<msub>\n"
                            + "<mi>log</mi>\n"
                            + arg1.toString()
                            + "</msub>\n"
                            + "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                            + arg2.toString()
                            + "</mfenced>\n";
                }
            case EXP:
                return "<msup>\n"
                        + "<mi>e</mi>"
                        + arg1.toString()
                        + "</msup>\n";
            case POW:
                return "<msup>\n"
                        + "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                        + arg1.toString()
                        + "</mfenced>\n"
                        + "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                        + arg2.toString()
                        + "</mfenced>\n"
                        + "</msup>\n";
            case MODULE:
                return "<mfenced separators=\"\" open=\"|\" close=\"|\">\n"
                        + arg1.toString()
                        + "</mfenced>\n";
            //all trigonometric functions looks kinda same
            default:
                return "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                        + "<mi>"
                        + this.getName()
                        + "</mi>\n"
                        + "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                        + arg1.toString()
                        + "</mfenced>\n"
                        + "</mfenced>\n";
        }
    }
}

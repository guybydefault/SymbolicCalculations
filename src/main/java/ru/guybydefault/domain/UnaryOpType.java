package ru.guybydefault.domain;

public enum UnaryOpType {
    PLUS ("Plus"),
    MINUS ("Minus"),
    TRANSPOSE("Transpose");

    private String name;

    UnaryOpType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UnaryOpType findByName(String name) {
        for (UnaryOpType unaryOpType : values()) {
            if (unaryOpType.name.equals(name)) {
                return unaryOpType;
            }
        }
        return null;
    }

    public String toString(Expression arg) {
        switch (this) {
            case PLUS: return arg.toString();
            case MINUS: return "<mo>-</mo>\n"
                    + arg.toString();
            case TRANSPOSE: return "<msup>\n"
                    + "<mrow>\n"
                    + "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                    + arg.toString()
                    + "</mfenced>\n"
                    + "</mrow>\n"
                    + "<mi>T</mi>\n"
                    + "</msup>\n";
            default: throw new IllegalArgumentException("There's no other types in this enum");
        }
    }
}

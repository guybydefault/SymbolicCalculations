package ru.guybydefault.domain;

public enum BinaryOpType {
    SUM("Sum"),
    SUB("Sub"),
    MUL("Mul"),
    DIV("Div");

    private final String name;

    BinaryOpType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BinaryOpType findByName(String name) {
        for (BinaryOpType binaryOpType : BinaryOpType.values()) {
            if (binaryOpType.name.equals(name)) {
                return binaryOpType;
            }
        }
        return null;
    }

    public String toString(Expression arg1, Expression arg2) {
        switch (this) {
            case SUM: return "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                    + arg1.toString()
                    + "<mo>+</mo>\n"
                    + arg2.toString()
                    + "</mfenced>\n";
            case SUB: return "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                    + arg1.toString()
                    + "<mo>-</mo>\n"
                    + arg2.toString()
                    + "</mfenced>\n";
            case MUL: return "<mfenced separators=\"\" open=\"(\" close=\")\">\n"
                    + arg1.toString()
                    + "<mo>*</mo>\n"
                    + arg2.toString()
                    + "</mfenced>\n";
            case DIV: return "<mfrac>\n"
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

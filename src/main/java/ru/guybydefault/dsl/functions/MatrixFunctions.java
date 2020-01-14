package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Functions;

public class MatrixFunctions {
    public static final StringSymbol Matrix = new StringSymbol("Matrix");
    public static final StringSymbol MatrixPlus = new StringSymbol("MatrixPlus");

    public static Expression MatrixPlusImplementation() {
//        return new Expression(Functions.Fun, new Expression(ArithmeticFunctions.ListPlus, ));
        return null;
    }
}

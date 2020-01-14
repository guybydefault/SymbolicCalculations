package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;

public class MatrixFunctions {
    public static final StringSymbol Matrix = new StringSymbol("Matrix");
    public static final StringSymbol MatrixPlus = new StringSymbol("MatrixPlus");

    public static Expression MatrixPlusImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(
                new Expression(ListFunctions.List, Arrays.asList(Alphabet.list, Alphabet.list2)),
                new Expression(ListFunctions.FastMap, Arrays.asList(
                        new Expression(ListFunctions.GenerateList, Collections.singletonList(
                                new Expression(ListFunctions.Length, Collections.singletonList(Alphabet.list))
                        )),
                        new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                new Expression(ArithmeticFunctions.ListPlusList, Arrays.asList(
                                        new Expression(ListFunctions.Part, Arrays.asList(Alphabet.list, Alphabet.x)),
                                        new Expression(ListFunctions.Part, Arrays.asList(Alphabet.list2, Alphabet.x))))))))));
    }


}

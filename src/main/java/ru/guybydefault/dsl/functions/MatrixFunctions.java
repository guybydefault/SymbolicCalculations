package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.StringSymbol;

import java.util.Arrays;
import java.util.List;

public class MatrixFunctions {
    public static final StringSymbol MatrixPlus = new StringSymbol("MatrixPlus");
    public static final StringSymbol MatrixMult = new StringSymbol("MatrixMult");
    public static final StringSymbol Transpose = new StringSymbol("Transpose");
    public static final StringSymbol Determiner = new StringSymbol("Determiner");
    public static final StringSymbol MatrixTimes = new StringSymbol("MatrixTimes");

    private static final List<StringSymbol> symbols = Arrays.asList(MatrixPlus, MatrixMult, Transpose, Determiner, MatrixTimes);

    public static boolean isFromMatrixFunctions(StringSymbol symbol) {
        return symbols.contains(symbol);
    }

//
//    public static Expression MatrixPlusImplementation() {
//        return new Expression(Functions.Fun, Arrays.asList(
//                new Expression(ListFunctions.List, Arrays.asList(Alphabet.list, Alphabet.list2)),
//                new Expression(ListFunctions.FMap, Arrays.asList(
//                        new Expression(ListFunctions.GenerateList, Collections.singletonList(
//                                new Expression(ListFunctions.Length, Collections.singletonList(Alphabet.list))
//                        )),
//                        new Expression(Functions.Fun, Arrays.asList(
//                                new Expression(ListFunctions.List, Alphabet.x),
//                                new Expression(ArithmeticFunctions.ListPlusList, Arrays.asList(
//                                        new Expression(ListFunctions.Part, Arrays.asList(Alphabet.list, Alphabet.x)),
//                                        new Expression(ListFunctions.Part, Arrays.asList(Alphabet.list2, Alphabet.x))))
//                        ))))));
//    }
//
//    public static Expression MatrixMultImplementation() {
//        return new Expression(Functions.Fun, Arrays.asList(
//                new Expression(ListFunctions.List, Arrays.asList(Alphabet.x, Alphabet.y)),
//                new Expression(ListFunctions.Map, Arrays.asList(
//                        new Expression(ListFunctions.Range, Collections.singletonList(
//                                new Expression(ListFunctions.Length, Alphabet.x))),
//                        new Expression(Functions.Fun, Arrays.asList(Alphabet.n,
//                                new Expression(ListFunctions.Map, Arrays.asList(
//                                        new Expression(ListFunctions.Range, Collections.singletonList(
//                                                new Expression(ListFunctions.Length, Collections.singletonList(Alphabet.x))
//                                        )),
//                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.f,
//                                                new Expression(ArithmeticFunctions.ListPlusList, Collections.singletonList(
//                                                        new Expression(ListFunctions.Map, Arrays.asList(
//                                                                new Expression(ListFunctions.Range, Collections.singletonList(
//                                                                        new Expression(ListFunctions.Length, Collections.singletonList(
//                                                                                new Expression(ListFunctions.Part, Arrays.asList(Alphabet.x, new Constant(0)))
//                                                                        ))
//                                                                )),
//                                                                new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
//                                                                        new Expression(ArithmeticFunctions.Times, Arrays.asList(
//                                                                                new Expression(ListFunctions.Part, Arrays.asList(
//                                                                                        new Expression(ListFunctions.Part, Arrays.asList(Alphabet.x, Alphabet.n)), Alphabet.acc
//                                                                                )),
//                                                                                new Expression(ListFunctions.Part, Arrays.asList(
//                                                                                        new Expression(ListFunctions.Part, Arrays.asList(Alphabet.y, Alphabet.acc)), Alphabet.f))))))))))))))))))));
//    }
}

package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Attributes;
import ru.guybydefault.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArithmeticFunctions {
    public static final StringSymbol Plus = new StringSymbol("Plus",
            new StringSymbol[]{Attributes.Flat,
                    Attributes.OneIdentity,
                    Attributes.Orderless});

    public static final StringSymbol BinaryPlus = new StringSymbol("BinaryPlus");
    public static final StringSymbol BinaryTimes = new StringSymbol("BinaryTimes");

    public static final StringSymbol Times = new StringSymbol("Times",
            new StringSymbol[]{Attributes.Flat,
                    Attributes.OneIdentity,
                    Attributes.Orderless});

    public static final StringSymbol Divide = new StringSymbol("Divide");

    public static final StringSymbol Minus = new StringSymbol("Minus");
    public static final StringSymbol ListPlus = new StringSymbol("ListPlus");
    public static final StringSymbol ListTimes = new StringSymbol("ListTimes");
    public static final StringSymbol ListPlusList = new StringSymbol("ListPlusList");

    private static final List<StringSymbol> symbols = Arrays.asList(Plus, BinaryPlus, BinaryTimes, Times, Minus,
            ListPlus, ListTimes, ListPlusList, Divide);

    public static boolean isFromArithmeticFunctions(StringSymbol symbol) {
        return symbols.contains(symbol);
    }

    public static Expression MinusImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Times, new Constant(-1), Alphabet.x)));
    }

    public static Expression Abs() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(BooleanFunctions.If, Arrays.asList(
                        new Expression(
                                new Expression(BooleanFunctions.Less, Collections.singletonList(Alphabet.x)),
                                Collections.singletonList(new Constant(0))
                        ),
                        new Expression(Minus, Collections.singletonList(Alphabet.x)), Alphabet.x))));
    }

    public static Expression ListPlusImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.list,
                new Expression(
                        new Expression(
                                new Expression(ListFunctions.Fold, Collections.singletonList(Alphabet.list)),
                                Collections.singletonList(new Constant(0))
                        ),
                        Collections.singletonList(
                                new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                new Expression(BinaryPlus, Arrays.asList(Alphabet.acc, Alphabet.x))))))))));
    }

    public static Expression ListPlusListImplementation() {
        return new Expression(Functions.Fun,
                new Expression(ListFunctions.List, Alphabet.list, Alphabet.list2),
                new Expression(ListFunctions.FMap,
                        new Expression(ListFunctions.GenerateList,
                                new Expression(ListFunctions.Length, Alphabet.list)
                        ),
                        new Expression(Functions.Fun, Alphabet.x,
                                new Expression(Plus,
                                        new Expression(ListFunctions.Part, Alphabet.list, Alphabet.x),
                                        new Expression(ListFunctions.Part, Alphabet.list2, Alphabet.x)
                                )
                        )
                )
        );
    }

    public static Expression ListTimesImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.list,
                new Expression(
                        new Expression(
                                new Expression(ListFunctions.Fold, Collections.singletonList(Alphabet.list)),
                                Collections.singletonList(new Constant(1))),
                        Collections.singletonList(
                                new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                new Expression(BinaryTimes, Arrays.asList(Alphabet.acc, Alphabet.x))))))))));
    }
}

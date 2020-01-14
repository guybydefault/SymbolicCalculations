package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.dsl.library.Attributes;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

import java.util.Arrays;
import java.util.Collections;

import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

public class ArithmeticFunctions {
    public static final StringSymbol Plus = new StringSymbol("Plus",
            new StringSymbol[] { Attributes.Flat,
                    Attributes.OneIdentity,
                    Attributes.Orderless });

    public static final StringSymbol BinaryPlus = new StringSymbol("BinaryPlus");
    public static final StringSymbol BinaryTimes = new StringSymbol("BinaryTimes");

    public static final StringSymbol Times = new StringSymbol("Times",
            new StringSymbol[] { Attributes.Flat,
                    Attributes.OneIdentity,
                    Attributes.Orderless });

    public static final StringSymbol Minus = new StringSymbol("Minus");
    public static final StringSymbol ListPlus = new StringSymbol("ListPlus");
    public static final StringSymbol ListTimes = new StringSymbol("ListTimes");

    public static Expression MinusImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Times, Collections.singletonList(new Constant(-1)))));
    }

    public static Expression Abs() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(BooleanFunctions.If, Arrays.asList(
                        new Expression(
                            new Expression(BooleanFunctions.Less, Collections.singletonList(Alphabet.x)),
                                Collections.singletonList(new Constant(0))
                        ),
                        new Expression(Minus, Collections.singletonList(Alphabet.x)), Alphabet.x
                ))));
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

    //TODO: ListPlusListImplementation

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

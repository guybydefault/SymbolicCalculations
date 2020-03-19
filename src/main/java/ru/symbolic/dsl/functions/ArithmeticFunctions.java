package ru.symbolic.dsl.functions;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.dsl.library.Functions;

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

    private static final List<StringSymbol> symbols = Arrays.asList(Plus, BinaryPlus, BinaryTimes, Times, Minus,
            ListPlus, Divide);

    public static boolean isFromArithmeticFunctions(StringSymbol symbol) {
        return symbols.contains(symbol);
    }

    public static Expression MinusImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Times, new Constant(-1), Alphabet.x)));
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

}

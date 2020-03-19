package ru.symbolic.dsl.library;

import ru.symbolic.domain.StringSymbol;

import java.util.Arrays;
import java.util.List;

public class Functions {
    public static final StringSymbol Evaluate = new StringSymbol("Evaluate");

    public static final StringSymbol Hold = new StringSymbol("Hold",
            new StringSymbol[]{Attributes.HoldAll});

    public static final StringSymbol HoldComplete = new StringSymbol("HoldComplete",
            new StringSymbol[]{Attributes.HoldAllComplete});

    public static final StringSymbol Fun = new StringSymbol("Fun",
            new StringSymbol[]{Attributes.HoldAll});

    public static final StringSymbol Seq = new StringSymbol("Seq",
            new StringSymbol[]{Attributes.Flat, Attributes.OneIdentity});

    public static final StringSymbol SetDelayed = new StringSymbol("SetDelayed",
            new StringSymbol[] {Attributes.HoldAll});

    public static final StringSymbol Set = new StringSymbol("Set",
            new StringSymbol[] {Attributes.HoldFirst});

    public static final StringSymbol ApplyList = new StringSymbol("ApplyList");

    private static final List<StringSymbol> symbols = Arrays.asList(Evaluate, Hold, HoldComplete, Fun, Seq, SetDelayed, Set, ApplyList);

    public static boolean isFromFunctions(StringSymbol symbol) {
        return symbols.contains(symbol);
    }
}
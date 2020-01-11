package ru.guybydefault.dsl.library;

import ru.guybydefault.domain.StringSymbol;

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
}
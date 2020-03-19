package ru.symbolic.dsl.library;

import ru.symbolic.domain.StringSymbol;

import java.util.Arrays;
import java.util.List;

public class Attributes {
    public static final StringSymbol HoldAll = new StringSymbol("HoldAll");
    public static final StringSymbol HoldRest = new StringSymbol("HoldRest");
    public static final StringSymbol HoldFirst = new StringSymbol("HoldFirst");
    public static final StringSymbol HoldAllComplete = new StringSymbol("HoldAllComplete");

    public static final StringSymbol Flat = new StringSymbol("Flat");
    public static final StringSymbol OneIdentity = new StringSymbol("OneIdentity");
    public static final StringSymbol Orderless = new StringSymbol("Orderless");

    private static final List<StringSymbol> symbols = Arrays.asList(HoldAll, HoldRest, HoldFirst, HoldAllComplete, Flat, OneIdentity, Orderless);

    public static boolean isFromAttributes(StringSymbol symbol) {
        return symbols.contains(symbol);
    }
}

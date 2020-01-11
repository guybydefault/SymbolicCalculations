package ru.guybydefault.dsl.library;

import ru.guybydefault.domain.StringSymbol;

public class Attributes {
    public static final StringSymbol HoldAll = new StringSymbol("HoldAll");
    public static final StringSymbol HoldRest = new StringSymbol("HoldRest");
    public static final StringSymbol HoldFirst = new StringSymbol("HoldFirst");
    public static final StringSymbol HoldAllComplete = new StringSymbol("HoldAllComplete");

    public static final StringSymbol Flat = new StringSymbol("Flat");
    public static final StringSymbol OneIdentity = new StringSymbol("OneIdentity");
    public static final StringSymbol Orderless = new StringSymbol("Orderless");
}

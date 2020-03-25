package ru.symbolic.dsl.functions;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;

import static ru.symbolic.dsl.library.Alphabet.*;

public class ListFunctions {
    public static final StringSymbol List = new StringSymbol("List");
    public static final StringSymbol GenerateList = new StringSymbol("GenerateList");

    public static final StringSymbol Part = new StringSymbol("Part");
    public static final StringSymbol Fold = new StringSymbol("Fold");
    public static final StringSymbol Append = new StringSymbol("Append");

    public static final Expression EmptyList = new Expression(List, Collections.singletonList(new Constant(0)));

    public static final StringSymbol Map = new StringSymbol("Map");
    public static final StringSymbol Filter = new StringSymbol("Filter");
    public static final StringSymbol Length = new StringSymbol("Length");
    public static final StringSymbol Concat = new StringSymbol("Concat");
    public static final StringSymbol CountItem = new StringSymbol("CountItem");
    public static final StringSymbol Contains = new StringSymbol("Contains");
    public static final StringSymbol Distinct = new StringSymbol("Distinct");
    public static final StringSymbol Group = new StringSymbol("Group");

    public static final StringSymbol Range = new StringSymbol("Range");

    public static final StringSymbol ListSum = new StringSymbol("ListSum");

}

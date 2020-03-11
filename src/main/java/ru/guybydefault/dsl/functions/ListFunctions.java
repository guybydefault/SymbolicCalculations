package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;

import static ru.guybydefault.dsl.library.Alphabet.*;

public class ListFunctions {
    public static final StringSymbol List = new StringSymbol("List");
    public static final StringSymbol GenerateList = new StringSymbol("GenerateList");

    public static final StringSymbol Part = new StringSymbol("Part");
    public static final StringSymbol Fold = new StringSymbol("Fold");
    public static final StringSymbol Append = new StringSymbol("Append");

    public static final Expression EmptyList = new Expression(List, Collections.singletonList(new Constant(0)));

    public static final StringSymbol Map = new StringSymbol("Map");
    public static final StringSymbol FMap = new StringSymbol("FMap");
    public static final StringSymbol Filter = new StringSymbol("Filter");
    public static final StringSymbol Length = new StringSymbol("Length");
    public static final StringSymbol Concat = new StringSymbol("Concat");
    public static final StringSymbol CountItem = new StringSymbol("CountItem");
    public static final StringSymbol Contains = new StringSymbol("Contains");
    public static final StringSymbol Distinct = new StringSymbol("Distinct");
    public static final StringSymbol Group = new StringSymbol("Group");

    public static final StringSymbol Range = new StringSymbol("Range");

    public static final StringSymbol ListSum = new StringSymbol("ListSum");

    private static final java.util.List<StringSymbol> symbols = Arrays.asList(List, GenerateList, Part, Fold, Append,
            Map, FMap, Filter, Length, Concat, CountItem, Contains, Distinct, Group, ListSum);

    public static boolean isFromListFunctions(StringSymbol symbol) {
        return symbols.contains(symbol);
    }

//    public static Expression MapImplementation() {
//        return new Expression(Functions.Fun, Arrays.asList(list,
//                new Expression(Functions.Fun, Arrays.asList(f,
//                        new Expression(
//                                new Expression(
//                                        new Expression(Fold, list),
//                                        Collections.singletonList(EmptyList)),
//                                Collections.singletonList(
//                                        new Expression(Functions.Fun, Arrays.asList(acc,
//                                                new Expression(Functions.Fun, Arrays.asList(x,
//                                                        new Expression(Append, Arrays.asList(acc,
//                                                                new Expression(f, Collections.singletonList(x))))))))))))));
//    }
//
//    public static Expression FilterImplementation() {
//        return new Expression(Functions.Fun, Arrays.asList(list,
//                new Expression(Functions.Fun, Arrays.asList(f,
//                        new Expression(
//                                new Expression(
//                                        new Expression(Fold, Collections.singletonList(list)),
//                                        Collections.singletonList(EmptyList)),
//                                Collections.singletonList(
//                                        new Expression(Functions.Fun, Arrays.asList(acc,
//                                                new Expression(Functions.Fun, Arrays.asList(x,
//                                                        new Expression(BooleanFunctions.If, Arrays.asList(
//                                                                new Expression(f, Collections.singletonList(x)),
//                                                                new Expression(Append, Arrays.asList(acc, x)),
//                                                                acc
//                                                        ))))))))))));
//    }

//    public static Expression ConcatImplementation() {
//        return new Expression(list, Collections.singletonList(
//                new Expression(Functions.Fun, Arrays.asList(list2,
//                        new Expression(
//                                new Expression(
//                                        new Expression(Fold, Collections.singletonList(list2)),
//                                        Collections.singletonList(list)),
//                                Collections.singletonList(
//                                        new Expression(Functions.Fun, Arrays.asList(acc,
//                                                new Expression(Functions.Fun, Arrays.asList(x,
//                                                        new Expression(Append, Arrays.asList(acc, x))))))))))));
//    }

//    public static Expression CountItemImplementation() {
//        return new Expression(Functions.Fun, Arrays.asList(list,
//                new Expression(Functions.Fun, Arrays.asList(x,
//                        new Expression(
//                                new Expression(
//                                        new Expression(Fold, Collections.singletonList(list)),
//                                        Collections.singletonList(new Constant(0))),
//                                Collections.singletonList(
//                                        new Expression(Functions.Fun, Arrays.asList(y,
//                                                new Expression(BooleanFunctions.If, Arrays.asList(
//                                                        new Expression(BooleanFunctions.Eq, Arrays.asList(x, y)),
//                                                        new Expression(ArithmeticFunctions.Plus, Arrays.asList(acc, new Constant(1))),
//                                                        acc))))))))));
//    }

//    public static Expression ContainsImplementation() {
//        return new Expression(Functions.Fun, Collections.singletonList(
//                new Expression(Functions.Fun, Arrays.asList(x,
//                        new Expression(
//                                new Expression(
//                                        new Expression(Fold, Collections.singletonList(list)),
//                                        Collections.singletonList(BooleanFunctions.False)),
//                                Collections.singletonList(
//                                        new Expression(Functions.Fun, Arrays.asList(acc,
//                                                new Expression(Functions.Fun, Arrays.asList(y,
//                                                        new Expression(BooleanFunctions.If, Arrays.asList(acc, acc,
//                                                                new Expression(BooleanFunctions.Eq, Arrays.asList(x, y))))))))))))));
//    }

    public static Expression FoldImplementation() {
        return new Expression(Functions.Fun, new Expression(ListFunctions.List, list, new StringSymbol("initialState'"), f),
                new Expression(
                        new Expression(Functions.Fun, n,
                                new Expression(Part,
                                        new Expression(
                                                new Expression(
                                                        new Expression(BooleanFunctions.While,
                                                                new Expression(List, new Constant(0), new StringSymbol("initialState'"))),
                                                        new Expression(Functions.Fun, x,
                                                                new Expression(BooleanFunctions.Less,
                                                                        new Expression(Part, x, new Constant(0)))), n),
                                                new Expression(Functions.Fun, x,
                                                        new Expression(List,
                                                                new Expression(ArithmeticFunctions.Plus,
                                                                        new Expression(Part, x, new Constant(0)),
                                                                        new Constant(1)),
                                                                f,
                                                                new Expression(
                                                                        new Expression(Part, x, new Constant(1)),
                                                                        new Expression(Part, list, new Expression(Part, x, new Constant(0))))))
                                        ),
                                        new Constant(1))),
                        new Expression(Length, list)));
    }
}

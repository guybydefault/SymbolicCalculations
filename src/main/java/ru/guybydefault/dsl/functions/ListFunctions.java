package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;

public class ListFunctions {
    public static final StringSymbol List = new StringSymbol("List");
    public static final StringSymbol KindaList = new StringSymbol("");
    public static final StringSymbol GenerateList = new StringSymbol("GenerateList");

    public static final StringSymbol Part = new StringSymbol("Part");
    public static final StringSymbol Fold = new StringSymbol("Fold");
    public static final StringSymbol Append = new StringSymbol("Append");

    public static final Expression EmptyList = new Expression(List, Collections.singletonList(new Constant(0)));

    public static final StringSymbol Map = new StringSymbol("Map");
    public static final StringSymbol FastMap = new StringSymbol("FastMap");
    public static final StringSymbol Filter = new StringSymbol("Filter");
    public static final StringSymbol Length = new StringSymbol("Length");
    public static final StringSymbol Concat = new StringSymbol("Concat");
    public static final StringSymbol CountItem = new StringSymbol("CountItem");
    public static final StringSymbol Contains = new StringSymbol("Contains");
    public static final StringSymbol Distinct = new StringSymbol("Distinct");
    public static final StringSymbol Group = new StringSymbol("Group");

    public static final StringSymbol Range = new StringSymbol("Range");

    public static Expression MapImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.list,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.f,
                        new Expression(
                            new Expression(
                                    new Expression(Fold, Collections.singletonList(Alphabet.list)),
                                    Collections.singletonList(EmptyList)),
                                Collections.singletonList(
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
                                                new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                        new Expression(Append, Arrays.asList(Alphabet.acc,
                                                                new Expression(Alphabet.f, Collections.singletonList(Alphabet.x))))))))))))));
    }
//    Fun[list, Fun[f,
//    Fold[list][EmptyList][Fun[acc, Fun[x,
//    Append[acc, f[x]]]]]]];

    public static Expression FilterImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.list,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.f,
                        new Expression(
                            new Expression(
                                new Expression(Fold, Collections.singletonList(Alphabet.list)),
                                        Collections.singletonList(EmptyList)),
                                Collections.singletonList(
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
                                                new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                        new Expression(BooleanFunctions.If, Arrays.asList(
                                                                new Expression(Alphabet.f, Collections.singletonList(Alphabet.x)),
                                                                new Expression(Append, Arrays.asList(Alphabet.acc, Alphabet.x)),
                                                                Alphabet.acc
                                                        ))))))))))));
    }
//    Fun[list, Fun[f,
//    Fold[list][EmptyList][Fun[acc, Fun[x,
//    If[f[x],
//    Append[acc, x],
//    acc]]]]]];

    public static Expression ConcatImplementation(){
        return new Expression(Alphabet.list, Collections.singletonList(
                new Expression(Functions.Fun, Arrays.asList(Alphabet.list2,
                        new Expression(
                            new Expression(
                                new Expression(Fold, Collections.singletonList(Alphabet.list2)),
                                        Collections.singletonList(Alphabet.list)),
                                Collections.singletonList(
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
                                                new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                        new Expression(Append, Arrays.asList(Alphabet.acc, Alphabet.x))))))))))));
    }
//    Fun[list, Fun[list2,
//    Fold[list2][list][Fun[acc, Fun[x,
//    Append[acc, x]]]]]];

    public static Expression CountItemImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.list,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                        new Expression(
                        new Expression(
                        new Expression(Fold, Collections.singletonList(Alphabet.list)),
                                Collections.singletonList(new Constant(0))),
                                Collections.singletonList(
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                                                new Expression(BooleanFunctions.If, Arrays.asList(
                                                        new Expression(BooleanFunctions.Eq, Arrays.asList(Alphabet.x, Alphabet.y)),
                                                        new Expression(ArithmeticFunctions.Plus, Arrays.asList(Alphabet.acc, new Constant(1))),
                                                        Alphabet.acc))))))))));
    }
//    Fun[list, Fun[x,
//    Fold[list][0][Fun[acc, Fun[y,
//    If[Eq[x, y], Plus[acc, 1], acc]]]]]];

    public static Expression ContainsImplementation(){
        return new Expression(Functions.Fun, Collections.singletonList(
                new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                        new Expression(
                        new Expression(
                        new Expression(Fold, Collections.singletonList(Alphabet.list)),
                                Collections.singletonList(BooleanFunctions.False)),
                                Collections.singletonList(
                                        new Expression(Functions.Fun, Arrays.asList(Alphabet.acc,
                                                new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                                                        new Expression(BooleanFunctions.If, Arrays.asList(Alphabet.acc, Alphabet.acc,
                                                                new Expression(BooleanFunctions.Eq, Arrays.asList(Alphabet.x, Alphabet.y))))))))))))));
    }
//    Fun[list, Fun[x,
//    Fold[list][False][Fun[acc, Fun[y,
//    If[acc, acc, Eq[x, y]]]]]]];

    public static Expression FoldImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.list,
                new Expression(Functions.Fun, Arrays.asList(new StringSymbol("initialState'"),
                        new Expression(Functions.Fun, Arrays.asList(Alphabet.f,
                                new Expression(Functions.Fun, Arrays.asList(Alphabet.n,
                                        new Expression(
                                            new Expression(Part, Arrays.asList(
                                                    new Expression(
                                                            new Expression(
                                                                    new Expression(BooleanFunctions.While, Collections.singletonList(
                                                                            new Expression(List, Arrays.asList(new Constant(0), new StringSymbol("initialState'"))))), Collections.singletonList(
                                                                            new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                                                    new Expression(
                                                                                            new Expression(BooleanFunctions.Less, Collections.singletonList(
                                                                                                    new Expression(Part, Arrays.asList(Alphabet.x, new Constant(0))))), Collections.singletonList(Alphabet.n)))))),
                                                            Collections.singletonList(new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                                                                    new Expression(List, Arrays.asList(
                                                                            new Expression(ArithmeticFunctions.Plus, Arrays.asList(
                                                                                    new Expression(Part, Arrays.asList(Alphabet.x, new Constant(0))),
                                                                                    new Constant(1))),
                                                                            new Expression(
                                                                                new Expression(Alphabet.f, Collections.singletonList(
                                                                                        new Expression(Part, Arrays.asList(Alphabet.x, new Constant(1)))
                                                                                )),
                                                                                    Collections.singletonList(
                                                                                            new Expression(Part, Arrays.asList(Alphabet.list,
                                                                                                    new Expression(Part, Arrays.asList(Alphabet.x, new Constant(0))))))))))))),
                                                    new Constant(1))),
                                                Collections.singletonList(
                                                        new Expression(Length, Collections.singletonList(Alphabet.list))))))))))));
    }
//    Fun[list,
//      Fun["initialState'",
//          Fun[f,
//              Fun[n,
//                  Part[
//                      While[
//                          List[0, "initialState'"]
//                          ]
//                          [Fun[x,
//                              Less[
//                                  Part[x, 0]
//                                  ]
//                                  [n]
//                              ]
//                           ]
//                          ?
//                          [Fun[x,
//                              List[
//                                  Plus[
//                                      Part[x, 0],
//                                      1],
//                                  f[
//                                      Part[x, 1]
//                                   ]
    //                                  [
//                                      Part[list,
//                                          Part[x, 0]
//                                          ]
    //                                  ]
//                                  ] (list ends)
//                              ] (args end)
//                           ], (Fun end)
//                           1]
//                      ] (Part end)
//                      [Length[list]]]]];
}

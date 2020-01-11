package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;

public class CastingFunctions {
    public static final StringSymbol AsConstant = new StringSymbol("AsConstant");
    public static final StringSymbol AsStringSymbol = new StringSymbol("AsStringSymbol");
    public static final StringSymbol AsExpressionArgs = new StringSymbol("AsExpressionArgs");

    public static final StringSymbol Null = new StringSymbol("Null");

    public static final StringSymbol IsConstant = new StringSymbol("IsConstant");
    public static final StringSymbol IsStringSymbol = new StringSymbol("IsStringSymbol");
    public static final StringSymbol IsExpressionWithName = new StringSymbol("IsExpressionWithName");
    public static final StringSymbol DefaultValue = new StringSymbol("DefaultValue");

    public static Expression IsConstantImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(BooleanFunctions.Not, Collections.singletonList(
                        new Expression(BooleanFunctions.Eq, Arrays.asList(
                                new Expression(AsConstant, Collections.singletonList(Alphabet.x)),
                                Null))))));
    }
//    Fun[x,
//    Not[Eq[AsConstant[x], Null]]
//            ];

    public static Expression IsStringSymbolImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(BooleanFunctions.Not, Arrays.asList(
                        new Expression(AsStringSymbol, Collections.singletonList(Alphabet.x)),
                        Null))));
    }
//    Fun[x,
//    Not[Eq[AsStringSymbol[x], Null]]
//            ];

    public static Expression IsExpressionWithNameImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(new StringSymbol("name'"),
                new Expression(Functions.Fun, Arrays.asList(new StringSymbol("expression'"),
                        new Expression(BooleanFunctions.Not, Collections.singletonList(
                                new Expression(BooleanFunctions.Eq, Arrays.asList(
                                        new Expression(AsExpressionArgs, Arrays.asList(
                                                new StringSymbol("name'"),
                                                new StringSymbol("expression'"))),
                                        Null))))))));
    }
//    Fun["name'", Fun["expression'",
//    Not[Eq[AsExpressionArgs["name'", "expression'"], Null]]
//            ]];

    public static Expression DefaultValueImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                        new Expression(BooleanFunctions.If, Arrays.asList(
                                new Expression(BooleanFunctions.Eq, Arrays.asList(Alphabet.x, Null)),
                                Alphabet.y, Alphabet.x))))));
    }
//    Fun[x, Fun[y,
//    If[Eq[x, Null], y, x]
//            ]];
}

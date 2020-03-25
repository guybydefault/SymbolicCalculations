package ru.symbolic.dsl.functions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static Expression IsStringSymbolImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(BooleanFunctions.Not, Arrays.asList(
                        new Expression(AsStringSymbol, Collections.singletonList(Alphabet.x)),
                        Null))));
    }

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

    public static Expression DefaultValueImplementation() {
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                        new Expression(BooleanFunctions.If, Arrays.asList(
                                new Expression(BooleanFunctions.Eq, Arrays.asList(Alphabet.x, Null)),
                                Alphabet.y, Alphabet.x))))));
    }
}

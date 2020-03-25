package ru.symbolic.dsl.functions;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.dsl.library.Functions;

import java.util.Arrays;
import java.util.List;

public class BooleanFunctions {
    public static final StringSymbol True = new StringSymbol("True");
    public static final StringSymbol False = new StringSymbol("False");

    public static final StringSymbol If = new StringSymbol("If", new StringSymbol[]{Attributes.HoldRest});

    public static final StringSymbol Eq = new StringSymbol("Eq");
    public static final StringSymbol Compare = new StringSymbol("Compare");

    public static final StringSymbol Not = new StringSymbol("Not");
    public static final StringSymbol Less = new StringSymbol("Less");
    public static final StringSymbol More = new StringSymbol("More");
    public static final StringSymbol And = new StringSymbol("And");
    public static final StringSymbol Or = new StringSymbol("Or");
    public static final StringSymbol While = new StringSymbol("While");


    public static Expression LessImplementation() {
        return new Expression(
                Functions.Fun,
                new Expression(ListFunctions.List, Alphabet.x, Alphabet.y),
                new Expression(
                        Eq,
                        new Expression(Compare, Alphabet.x, Alphabet.y),
                        new Constant(-1)));
    }

    public static Expression MoreImplementation() {
        return new Expression(
                Functions.Fun,
                new Expression(ListFunctions.List, Alphabet.x, Alphabet.y),
                new Expression(
                        Eq,
                        new Expression(Compare, Alphabet.x, Alphabet.y),
                        new Constant(1)));
    }

    public static Expression NotImplementation() {
        return new Expression(Functions.Fun, Alphabet.x, new Expression(If, Alphabet.x, False, True));
    }

    public static Expression AndImplementation() {
        return new Expression(
                Functions.Fun,
                new Expression(ListFunctions.List, Alphabet.x, Alphabet.y),
                new Expression(If, Alphabet.x, Alphabet.y, Alphabet.x));
    }

    public static Expression OrImplementation() {
        return new Expression(
                Functions.Fun,
                new Expression(ListFunctions.List, Alphabet.x, Alphabet.y),
                new Expression(If, Alphabet.x, True, Alphabet.y));
    }

    public static Expression WhileImplementation() {
        return new Expression(Functions.Fun, new Expression(ListFunctions.List, Alphabet.x, Alphabet.pred, Alphabet.body),
                new Expression(
                        If,
                        new Expression(Alphabet.pred, Alphabet.x),
                        new Expression(While, new Expression(Alphabet.body, Alphabet.x), Alphabet.pred, Alphabet.body),
                        Alphabet.x)
        );
    }
}
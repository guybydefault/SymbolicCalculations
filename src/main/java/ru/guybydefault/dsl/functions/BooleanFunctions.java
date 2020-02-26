package ru.guybydefault.dsl.functions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Attributes;
import ru.guybydefault.dsl.library.Functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BooleanFunctions {
    public static final StringSymbol True = new StringSymbol("True");
    public static final StringSymbol False = new StringSymbol("False");

    public static final StringSymbol If = new StringSymbol("If", new StringSymbol[] {Attributes.HoldRest});

    public static final StringSymbol Eq = new StringSymbol("Eq");
    public static final StringSymbol Compare = new StringSymbol("Compare");

    public static final StringSymbol Not = new StringSymbol("Not");
    public static final StringSymbol Less = new StringSymbol("Less");
    public static final StringSymbol More = new StringSymbol("More");
    public static final StringSymbol And = new StringSymbol("And");
    public static final StringSymbol Or = new StringSymbol("Or");
    public static final StringSymbol While = new StringSymbol("While");

    private static final List<StringSymbol> symbols = Arrays.asList(True, False, If, Eq, Compare, Not, Less, More, And, Or, While);

    public static boolean isFromBooleanFunctions(StringSymbol symbol) {
        return symbols.contains(symbol);
    }

    public static Expression NotImplementation() {
        return new Expression(Alphabet.x, Collections.singletonList(
                new Expression(If, Arrays.asList(True, False))));
    }

    public static Expression LessImplementation() {
        return new Expression(Functions.Fun, Collections.singletonList(
                new Expression(Alphabet.y, Collections.singletonList(
                        new Expression(Eq, Arrays.asList(
                                new Expression(Compare, Arrays.asList(Alphabet.x, Alphabet.y)),
                                new Constant(-1)))))));
    }

    public static Expression MoreImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                        new Expression(Eq, Arrays.asList(Alphabet.x, Alphabet.y)),
                        new Constant(1)))));
    }

    public static Expression AndImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                        new Expression(If, Arrays.asList(Alphabet.x, Alphabet.y, False))))));
    }

    public static Expression OrImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.y,
                        new Expression(If, Arrays.asList(Alphabet.x, True, Alphabet.y,
                                new StringSymbol("Error")))))));
    }

    public static Expression WhileImplementation(){
        return new Expression(Functions.Fun, Arrays.asList(Alphabet.x,
                new Expression(Functions.Fun, Arrays.asList(Alphabet.pred,
                        new Expression(Functions.Fun, Arrays.asList(Alphabet.body,
                                new Expression(If, Arrays.asList(
                                        new Expression(Alphabet.pred, Collections.singletonList(Alphabet.x)),
                                        new Expression(
                                            new Expression(
                                                new Expression(While, Collections.singletonList(
                                                        new Expression(Alphabet.body, Collections.singletonList(Alphabet.x))
                                                )), Collections.singletonList(Alphabet.pred)
                                            ), Collections.singletonList(Alphabet.body)
                                        ),
                                        Alphabet.x
                                ))))))));
    }
}

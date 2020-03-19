package src.main.java.listFunctions;

import org.junit.Ignore;
import org.junit.Test;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class FoldTest {

    @Test
    @Ignore
    public void foldTest_1() {
        evaluateAndAssert(
                new Expression(
                        new Expression(
                                new Expression(
                                        ListFunctions.Fold,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2), new Constant(3))
                                ),
                                new Constant(0))
                        ,
                        new Expression(
                                Functions.Fun,
                                Alphabet.acc,
                                new Expression(
                                        Functions.Fun,
                                        Alphabet.x,
                                        new Expression(
                                                ArithmeticFunctions.Plus,
                                                Alphabet.acc,
                                                Alphabet.x
                                        )
                                )
                        ))
                ,
                new Constant(6)
        );
    }
}

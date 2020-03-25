package src.main.java.arithmeticOperations;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class SumTest {

    @org.junit.Test
    public void testSum_1() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Constant(1),
                        new Constant(2)),
                new Constant(3)
        );
    }

    @org.junit.Test
    public void testSum_2() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Plus,
                                new Constant(5),
                                new Constant(1)),
                        new Constant(2)),
                new Constant(8)
        );
    }

    @org.junit.Test
    public void testSum_3() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Plus,
                                new Constant(5),
                                new Constant(1)),
                        new Expression(ArithmeticFunctions.Plus,
                                new Expression(ArithmeticFunctions.Plus,
                                        new Constant(3),
                                        new Constant(2)),
                                new Constant(2))),
                new Constant(13)
        );
    }

    @org.junit.Test
    public void testSum_4() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Plus,
                                new Constant(5),
                                new StringSymbol("x")),
                        new Expression(ArithmeticFunctions.Plus,
                                new Expression(ArithmeticFunctions.Plus,
                                        new Constant(3),
                                        new Constant(2)),
                                new Constant(2))),
                new Expression(ArithmeticFunctions.Plus,
                        new StringSymbol("x"),
                        new Constant(12))
        );
    }

    @org.junit.Test
    public void testSumArgumentsSimplify_5() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Constant(1),
                        new StringSymbol("z"),
                        new Constant(2),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(1),
                                new StringSymbol("z"),
                                new StringSymbol("y")
                        ),
                        new StringSymbol("y"),
                        new StringSymbol("x"),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new StringSymbol("z"),
                                new StringSymbol("y"),
                                new StringSymbol("x")
                        ),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new StringSymbol("z"),
                                new StringSymbol("y")
                        ),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(1),
                                new StringSymbol("z"),
                                new StringSymbol("y"),
                                new StringSymbol("x")
                        )
                ),
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(
                                ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new StringSymbol("y"),
                                new StringSymbol("z"),
                                new Constant(3)
                        ),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("z"),
                                new StringSymbol("y"),
                                new Constant(3)
                        ),
                        new StringSymbol("x"),
                        new StringSymbol("y"),
                        new StringSymbol("z"),
                        new Constant(3))
        );
    }

    @org.junit.Test
    public void testSumArgumentsSimplify_6() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Constant(1),
                        new Expression(
                                new StringSymbol("Times"),
                                new StringSymbol("z"),
                                new Constant(2)
                        ),
                        new Constant(2),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(1),
                                new StringSymbol("z"),
                                new StringSymbol("y")
                        ),
                        new StringSymbol("y"),
                        new StringSymbol("x"),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new StringSymbol("z"),
                                new StringSymbol("y"),
                                new StringSymbol("x")
                        ),
                        new Expression(
                                new StringSymbol("Times"),
                                new StringSymbol("z"),
                                new Constant(2)
                        ),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new StringSymbol("z"),
                                new StringSymbol("y")
                        ),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(1),
                                new StringSymbol("z"),
                                new StringSymbol("y"),
                                new StringSymbol("x")
                        )
                ),
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(
                                ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new StringSymbol("y"),
                                new StringSymbol("z"),
                                new Constant(3)
                        ),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("y"),
                                new StringSymbol("z"),
                                new Constant(3)
                        ),
                        new Expression(
                                new StringSymbol("Times"),
                                new StringSymbol("z"),
                                new Constant(4)
                        ),
                        new StringSymbol("x"),
                        new StringSymbol("y"),
                        new Constant(3))
        );
    }

}

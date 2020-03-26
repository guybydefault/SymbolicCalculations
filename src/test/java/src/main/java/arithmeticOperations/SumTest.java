package src.main.java.arithmeticOperations;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;

import static ru.symbolic.dsl.functions.ArithmeticFunctions.*;
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

    @org.junit.Test
    public void testSumArgumentsFlatten_7() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(Times,
                                new StringSymbol("x"),
                                new Expression(Plus,
                                        new StringSymbol("y"),
                                        new StringSymbol("z"),
                                        new StringSymbol("w")
                                )),
                        new StringSymbol("z")),
                new Expression(
                        Plus,
                        new Expression(
                                Times,
                                new StringSymbol("w"),
                                new StringSymbol("x")
                        ),
                        new Expression(
                                Times,
                                new StringSymbol("x"),
                                new StringSymbol("y")
                        ),
                        new Expression(
                                Times,
                                new StringSymbol("x"),
                                new StringSymbol("z")
                        ),
                        new StringSymbol("z")
                )
        );
    }

    @org.junit.Test
    public void testSumArgumentsFlattenAndSimplify_8() {
        evaluateAndAssert(
                new Expression(
                        ArithmeticFunctions.Plus,
                        new Expression(Times,
                                new StringSymbol("z"),
                                new Expression(Plus,
                                        new StringSymbol("x"),
                                        new StringSymbol("y")
                                )),
                        new Expression(
                                Times,
                                new Expression(
                                        Plus,
                                        new StringSymbol("y"),
                                        new StringSymbol("x"),
                                        new StringSymbol("z")
                                ),
                                new StringSymbol("w")
                        ),
                        new Expression(
                                Times,
                                new StringSymbol("w"),
                                new StringSymbol("y")
                        ),
                        new Expression(
                                Times,
                                new StringSymbol("w"),
                                new StringSymbol("z")
                        ),
                        new Constant(10)
                ),
                new Expression(
                        Plus,
                        new Expression(
                                Times,
                                new StringSymbol("w"),
                                new StringSymbol("x")
                        ),
                        new Expression(Times,
                                new StringSymbol("w"),
                                new StringSymbol("y"),
                                new Constant(2)),
                        new Expression(Times,
                                new StringSymbol("w"),
                                new StringSymbol("z"),
                                new Constant(2)),
                        new Expression(
                                Times,
                                new StringSymbol("x"),
                                new StringSymbol("z")
                        ),
                        new Expression(
                                Times,
                                new StringSymbol("y"),
                                new StringSymbol("z")
                        ),
                        new Constant(10)
                )
        );
    }

    @org.junit.Test
    public void t_1() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(3),
                                new StringSymbol("x")),
                        new StringSymbol("x")),
                new Expression(ArithmeticFunctions.Times,
                        new StringSymbol("x"),
                        new Constant(4))
        );
    }

    //Add[Mul[-1, b], Mul[3, b]]
    @org.junit.Test
    public void t_2() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(-1),
                                new StringSymbol("b")),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(3),
                                new StringSymbol("b"))),
                new Expression(ArithmeticFunctions.Times,
                        new StringSymbol("b"),
                        new Constant(2))
        );
    }

    //Add[a, Mul[y, 2], b, c, Mul[3, x], d, x, Mul[-1, y]]
    @org.junit.Test
    public void t_3() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new StringSymbol("a"),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("y"),
                                new Constant(2)),
                        new StringSymbol("b"),
                        new StringSymbol("c"),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new Constant(3)),
                        new StringSymbol("d"),
                        new StringSymbol("x"),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("y"),
                                new Constant(-1))),
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new Constant(4)),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("y"),
                                new Constant(1)),
                        new StringSymbol("a"),
                        new StringSymbol("b"),
                        new StringSymbol("c"),
                        new StringSymbol("d")
                )
        );
    }

    //Add[Mul[a, b], Mul[-1, a, b]]
    @org.junit.Test
    public void t_4() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("a"),
                                new StringSymbol("b")),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(-1),
                                new StringSymbol("a"),
                                new StringSymbol("b"))),
                new Constant(0)
        );
    }

    //Add[Mul[2, x], Mul[2, a], Mul[-2, a]]
    @org.junit.Test
    public void t_5() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new StringSymbol("x")),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new StringSymbol("a")),
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(-2),
                                new StringSymbol("a"))),
                new Expression(ArithmeticFunctions.Times,
                        new StringSymbol("x"),
                        new Constant(2))
        );
    }

    //Add[front, Mul[x, y], Mul[x, y], back]
    @org.junit.Test
    public void t_6() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new StringSymbol("front"),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new StringSymbol("y")),
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new StringSymbol("y")),
                        new StringSymbol("back")),
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                new StringSymbol("x"),
                                new StringSymbol("y"),
                                new Constant(2)),
                        new StringSymbol("back"),
                        new StringSymbol("front"))
        );
    }

    //    Mul[a, b, a]
    @org.junit.Test
    public void t_7() {
        evaluateAndAssert(
                new Expression(Times,
                        new StringSymbol("a"),
                        new StringSymbol("b"),
                        new StringSymbol("a")),
                new Expression(Times,
                        new Expression(ArithmeticFunctions.Power,
                                new StringSymbol("a"),
                                new Constant(2)),
                        new StringSymbol("b"))
        );
    }

    //    Mul[a, b, b, a, c, a]
    @org.junit.Test
    public void t_8() {
        evaluateAndAssert(
                new Expression(Times,
                        new StringSymbol("a"),
                        new StringSymbol("b"),
                        new StringSymbol("b"),
                        new StringSymbol("a"),
                        new StringSymbol("c"),
                        new StringSymbol("a"))
                ,
                new Expression(Times,
                        new Expression(ArithmeticFunctions.Power,
                                new StringSymbol("a"),
                                new Constant(3)),
                        new Expression(Power,
                                new StringSymbol("b"),
                                new Constant(2)),
                        new StringSymbol("c"))
        );
    }

    //    Power[Power[x, 2], 2]
    @org.junit.Test
    public void t_9() {
        evaluateAndAssert(
                new Expression(Power,
                        new Expression(Power,
                                new StringSymbol("x"),
                                new Constant(2)),
                        new Constant(2)
                ),
                new Expression(ArithmeticFunctions.Power,
                        new StringSymbol("x"),
                        new Constant(4)
                ));
    }

    //    Mul[Power[Power[x, 3], 2], x, Power[x, 3]]
    @org.junit.Test
    public void t_10() {
        evaluateAndAssert(
                new Expression(
                        Times,
                        new Expression(Power,
                                new Expression(Power,
                                        new StringSymbol("x"),
                                        new Constant(3)),
                                new Constant(2)
                        ),
                        new StringSymbol("x"),
                        new Expression(Power,
                                new StringSymbol("x"),
                                new Constant(3))),

                new Expression(ArithmeticFunctions.Power,
                        new StringSymbol("x"),
                        new Constant(10))
        );
    }
}

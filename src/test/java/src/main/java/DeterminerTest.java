package src.main.java;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import java.util.Collections;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class DeterminerTest {
    @org.junit.Test
    public void testDeterminer_1() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.Determiner,
                        Collections.singletonList((
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4)))))),
                new Constant(-2)
        );
    }

    @org.junit.Test
    public void testDeterminer_2() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.Determiner,
                        Collections.singletonList((
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2), new Constant(7)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4), new Constant(8)),
                                        new Expression(ListFunctions.List, new Constant(5), new Constant(6), new Constant(9)))))),
                new Constant(0)
        );
    }

    @org.junit.Test
    public void testDeterminer_3() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Minus, new Constant(1)),
                new Constant(-1)
        );
    }

    @org.junit.Test
    public void testDeterminer_4() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Minus,
                        new Expression(ArithmeticFunctions.Times,
                                new Constant(2),
                                new Constant(3))),
                new Constant(-6)
        );
    }

    @org.junit.Test
    public void testDeterminer_5() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                    new Expression(ArithmeticFunctions.Minus,
                            new Expression(ArithmeticFunctions.Times,
                                    new Constant(2),
                                    new Constant(3))),
                    new Expression(ArithmeticFunctions.Times,
                            new Constant(1),
                            new Constant(4))),
                new Constant(-2)
        );
    }

    @org.junit.Test
    public void testDeterminer_6() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Minus,
                        new Expression(ArithmeticFunctions.BinaryTimes,
                                new Constant(2),
                                new Constant(3))),
                new Constant(-6)
        );
    }
}

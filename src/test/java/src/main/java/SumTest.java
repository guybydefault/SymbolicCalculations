package src.main.java;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.library.Alphabet;

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
                                Alphabet.x),
                        new Expression(ArithmeticFunctions.Plus,
                                new Expression(ArithmeticFunctions.Plus,
                                        new Constant(3),
                                        new Constant(2)),
                                new Constant(2))),
                new Expression(ArithmeticFunctions.Plus,
                        Alphabet.x,
                        new Constant(12))
        );
    }

    @org.junit.Test
    public void testSum_5() {
        evaluateAndAssert(
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Plus,
                                new Constant(5),
                                Alphabet.x),
                        new Expression(ArithmeticFunctions.Plus,
                                new Expression(ArithmeticFunctions.Plus,
                                        new Constant(3),
                                        Alphabet.x),
                                new Constant(2))),
                new Expression(ArithmeticFunctions.Plus,
                        new Expression(ArithmeticFunctions.Times,
                                Alphabet.x,
                                new Constant(2)),
                        new Constant(10))
        );
    }
}

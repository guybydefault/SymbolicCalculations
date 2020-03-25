package src.main.java.booleanFunctions;

import org.junit.Test;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.BooleanFunctions;
import ru.symbolic.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class LogicOperatorsTest {
    @Test
    public void equalsTest_1() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq, new Constant(1), new Constant(2)),
                BooleanFunctions.False
        );
    }

    @Test
    public void equalsTest_2() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq, new Constant(1), new Constant(1)),
                BooleanFunctions.True
        );
    }

    @Test
    public void equalsTest_3() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq, new StringSymbol("x"), new StringSymbol("y")),
                BooleanFunctions.False
        );
    }

    @Test
    public void equalsTest_4() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq, new StringSymbol("x"), new StringSymbol("x")),
                BooleanFunctions.True
        );
    }

    @Test
    public void equalsTest_5() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        new Expression(Functions.Fun, new StringSymbol("x"), new Expression(ArithmeticFunctions.Plus, new Constant(1), new Constant(2))),
                        new Expression(Functions.Fun, new StringSymbol("x"), new Expression(ArithmeticFunctions.Plus, new Constant(1), new Constant(2)))
                ),
                BooleanFunctions.True
        );
    }

    @Test
    public void equalsTest_6() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        new Expression(Functions.Fun, new StringSymbol("x"), new Expression(ArithmeticFunctions.Plus, new Constant(1), new Constant(2))),
                        new Expression(Functions.Fun, new StringSymbol("x"), new Expression(ArithmeticFunctions.Plus, new Constant(3), new Constant(2)))
                ),
                BooleanFunctions.False
        );
    }

    @Test
    public void equalsTest_7() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.False,
                        new Expression(BooleanFunctions.Not, new Expression(BooleanFunctions.Not, BooleanFunctions.True))),
                BooleanFunctions.False
        );
    }

    @Test
    public void equalsTest_8() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.True,
                        new Expression(BooleanFunctions.Not, new Expression(BooleanFunctions.Not, new Expression(BooleanFunctions.Not, BooleanFunctions.True)))),
                BooleanFunctions.False
        );
    }

    @Test
    public void orTest_1() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.True,
                        new Expression(BooleanFunctions.Or,
                                new Expression(BooleanFunctions.Not, BooleanFunctions.False)), BooleanFunctions.True),
                BooleanFunctions.True
        );
    }

    @Test
    public void orTest_2() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.True,
                        new Expression(BooleanFunctions.Or, BooleanFunctions.False, BooleanFunctions.True)),
                BooleanFunctions.True
        );
    }

    @Test
    public void orTest_3() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.False,
                        new Expression(BooleanFunctions.Or,
                                new Expression(BooleanFunctions.Not, BooleanFunctions.True), BooleanFunctions.False)),
                BooleanFunctions.True
        );
    }

    @Test
    public void orTest_4() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.True,
                        new Expression(BooleanFunctions.Or,
                                BooleanFunctions.True, BooleanFunctions.False)),
                BooleanFunctions.True
        );
    }

    @Test
    public void andTest_1() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.False,
                        new Expression(BooleanFunctions.And, BooleanFunctions.True, BooleanFunctions.False)),
                BooleanFunctions.True
        );
    }

    @Test
    public void andTest_2() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.False,
                        new Expression(BooleanFunctions.And, BooleanFunctions.False, BooleanFunctions.True)),
                BooleanFunctions.True
        );
    }

    @Test
    public void andTest_3() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.False,
                        new Expression(BooleanFunctions.And, new Expression(BooleanFunctions.Not, BooleanFunctions.True), BooleanFunctions.False)),
                BooleanFunctions.True
        );
    }

    @Test
    public void andTest_4() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Eq,
                        BooleanFunctions.True,
                        new Expression(BooleanFunctions.And, BooleanFunctions.True, BooleanFunctions.True)),
                BooleanFunctions.True
        );
    }

    @Test
    public void lessTest_1() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Less,
                        new Constant(1),
                        new Constant(10)),
                BooleanFunctions.True
        );
    }

    @Test
    public void lessTest_2() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Less,
                        new Constant(1),
                        new Constant(1)),
                BooleanFunctions.False
        );
    }

    @Test
    public void lessTest_3() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.Less,
                        new Constant(10),
                        new Constant(1)),
                BooleanFunctions.False
        );
    }

    @Test
    public void moreTest_1() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.More,
                        new Constant(1),
                        new Constant(10)),
                BooleanFunctions.False
        );
    }

    @Test
    public void moreTest_2() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.More,
                        new Constant(1),
                        new Constant(1)),
                BooleanFunctions.False
        );
    }

    @Test
    public void moreTest_3() {
        evaluateAndAssert(
                new Expression(BooleanFunctions.More,
                        new Constant(10),
                        new Constant(1)),
                BooleanFunctions.True
        );
    }
}

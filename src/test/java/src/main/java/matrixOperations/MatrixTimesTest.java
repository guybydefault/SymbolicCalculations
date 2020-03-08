package src.main.java.matrixOperations;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import java.util.Arrays;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class MatrixTimesTest {
    @org.junit.Test
    public void testMatrixTimes_1() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.MatrixTimes,
                        Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Constant(2))),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List, new Constant(2), new Constant(4)),
                        new Expression(ListFunctions.List, new Constant(6), new Constant(8))
                )
        );
    }

    @org.junit.Test
    public void testMatrixTimes_2() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.MatrixTimes,
                        Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new StringSymbol("x"), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Constant(2))),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List, new Expression(ArithmeticFunctions.Times, new StringSymbol("x"), new Constant(2)), new Constant(4)),
                        new Expression(ListFunctions.List, new Constant(6), new Constant(8))
                )
        );
    }

    @org.junit.Test
    public void testMatrixTimes_3() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.MatrixTimes,
                        Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Expression(ArithmeticFunctions.Times, new StringSymbol("x"), new Constant(2)), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Constant(2))),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List, new Expression(ArithmeticFunctions.Times, new StringSymbol("x"), new Constant(4)), new Constant(4)),
                        new Expression(ListFunctions.List, new Constant(6), new Constant(8))
                )
        );
    }
}

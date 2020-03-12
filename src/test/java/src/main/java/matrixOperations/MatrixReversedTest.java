package src.main.java.matrixOperations;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import java.util.Arrays;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class MatrixReversedTest {

    @org.junit.Test
    public void testReverseMatrix_1() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.MatrixReversed,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(7), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Constant(3))
                        )),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(3), new Constant(-4)),
                        new Expression(ListFunctions.List, new Constant(-5), new Constant(7))
                ))
        );
    }



    @org.junit.Test
    public void testReverseMatrix_2() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.MatrixReversed,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(2), new Constant(-4), new Constant(3)),
                                new Expression(ListFunctions.List, new Constant(1), new Constant(-2), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(3), new Constant(-1), new Constant(5))
                        )),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(-6), new Constant(7), new Constant(5)),
                                new Expression(ListFunctions.List, new Constant(17), new Constant(1), new Constant(-10)),
                                new Expression(ListFunctions.List, new Constant(-10), new Constant(-5), new Constant(0))
                        ))
        );
    }
}

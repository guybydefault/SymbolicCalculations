package src.main.java.matrixOperations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class AlgrebraicMatrixTest {
    @Test
    public void algrebraicMatrixTest_1() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.AlgrebraicMatrix,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(2), new Constant(5), new Constant(7)),
                                new Expression(ListFunctions.List, new Constant(6), new Constant(3), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Constant(-2), new Constant(-3))
                        )
                ),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List, new Constant(-1), new Constant(38), new Constant(-27)),
                        new Expression(ListFunctions.List, new Constant(1), new Constant(-41), new Constant(29)),
                        new Expression(ListFunctions.List, new Constant(-1), new Constant(34), new Constant(-24))));
    }

    @Test
    public void algrebraicMatrixTest_2() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.AlgrebraicMatrix,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(7), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Constant(3))
                        )
                ),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List, new Constant(3), new Constant(-5)),
                        new Expression(ListFunctions.List, new Constant(-4), new Constant(7))
                ));
    }
}

package src.main;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import static src.main.tools.Test.evaluateAndAssert;

public class Test {

    @org.junit.Test
    public void testMatrixPlus() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.MatrixPlus,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                        )
                ),
                new Expression(
                        MatrixFunctions.MatrixPlus,
                        new Expression(ListFunctions.List, new Constant(1)),
                        new Expression(ListFunctions.List, new Constant(1))
                )
        );
    }
}

package src.main;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import java.util.Arrays;

import static src.main.tools.TestRunner.evaluateAndAssert;

public class MatrixOperationsTests {

    @org.junit.Test
    public void testMatrixPlus() {
        evaluateAndAssert(

//                new Expression(Seq,
//                        new Expression(SetDelayed, new StringSymbol("mplus")),
                new Expression(
                        MatrixFunctions.MatrixPlus, Arrays.asList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                        ),
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(2), new Constant(2)),
                        new Expression(ListFunctions.List, new Constant(2), new Constant(2))
                ))

        );
    }
}
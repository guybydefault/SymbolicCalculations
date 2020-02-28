package src.main.java;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import java.util.Arrays;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class MatrixSumOperationsTests {

    @org.junit.Test
    public void testMatrixPlusMatrix_1() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.MatrixPlus, Arrays.asList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                        ),
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(2), new Constant(3)),
                        new Expression(ListFunctions.List, new Constant(4), new Constant(5))
                ))
        );
    }

    @org.junit.Test
    public void testMatrixPlusMatrix_2() {
        evaluateAndAssert(new Expression(MatrixFunctions.MatrixPlus,
                        new Expression(
                                MatrixFunctions.MatrixPlus, Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                                ))
                        ),
                        new Expression(
                                MatrixFunctions.MatrixPlus, Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                                ))
                        )),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(4), new Constant(6)),
                        new Expression(ListFunctions.List, new Constant(8), new Constant(10))
                ))
        );
    }

    @org.junit.Test
    public void testMatrixPlusMatrix_3() {
        evaluateAndAssert(new Expression(MatrixFunctions.MatrixPlus,
                        new Expression(
                                MatrixFunctions.MatrixPlus, Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new StringSymbol("x"), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                                ))
                        ),
                        new Expression(
                                MatrixFunctions.MatrixPlus, Arrays.asList(
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                        new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                                ),
                                new Expression(ListFunctions.List,
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                                ))
                        )),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Expression(ArithmeticFunctions.Plus, new StringSymbol("x"), new Constant(3), new Constant(6)),
                        new Expression(ListFunctions.List, new Constant(8), new Constant(10))
                ))
        ));
    }

    @org.junit.Test
    public void testListPlusList() {
        evaluateAndAssert(
                new Expression(
                        ArithmeticFunctions.ListPlusList, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
                )
                ),
                new Expression(ListFunctions.List, new Constant(2), new Constant(2))
        );
    }
}

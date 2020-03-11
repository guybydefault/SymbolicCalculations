package src.main.java;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;

import java.util.Arrays;
import java.util.Collections;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class MatrixTransposeOperationsTests {
    @org.junit.Test
    public void testMatrixTranspose_1() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.Transpose, Collections.singletonList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                new Expression(ListFunctions.List, new Constant(3), new Constant(4))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(1), new Constant(3)),
                        new Expression(ListFunctions.List, new Constant(2), new Constant(4))
                ))
        );
    }

    @org.junit.Test
    public void testMatrixTranspose_2() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.Transpose, Collections.singletonList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                new Expression(ListFunctions.List, new Constant(3), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Constant(6))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(1), new Constant(3), new Constant(5)),
                        new Expression(ListFunctions.List, new Constant(2), new Constant(4), new Constant(6))
                ))
        );
    }

    @org.junit.Test
    public void testMatrixTranspose_3() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.Transpose, Collections.singletonList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                                new Expression(ListFunctions.List, new StringSymbol("x"), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Expression(ArithmeticFunctions.Plus, new Constant(6), new Constant(9)))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(1), new StringSymbol("x"), new Constant(5)),
                        new Expression(ListFunctions.List, new Constant(2), new Constant(4), new Constant(15))
                ))
        );
    }

    @org.junit.Test
    public void testMatrixTranspose_4() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.Transpose, Collections.singletonList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Expression(ArithmeticFunctions.Plus, new Constant(6), new Constant(9), new StringSymbol("x"))),
                                new Expression(ListFunctions.List, new StringSymbol("x"), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Expression(ArithmeticFunctions.Plus, new Constant(6), new Constant(9)))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(1), new StringSymbol("x"), new Constant(5)),
                        new Expression(ListFunctions.List, new Expression(ArithmeticFunctions.Plus, new Constant(15), new StringSymbol("x")), new Constant(4), new Constant(15))
                ))
        );
    }

    @org.junit.Test
    public void testMatrixTranspose_5() {
        evaluateAndAssert(
                new Expression(
                        MatrixFunctions.Transpose, Collections.singletonList(
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1), new Expression(ArithmeticFunctions.Plus, new StringSymbol("x"), new Expression(ArithmeticFunctions.Times, new StringSymbol("x"), new Constant(2)))),
                                new Expression(ListFunctions.List, new StringSymbol("x"), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(5), new Expression(ArithmeticFunctions.Plus, new Constant(6), new Constant(9)))
                        ))
                ),
                new Expression(ListFunctions.List, Arrays.asList(
                        new Expression(ListFunctions.List, new Constant(1), new StringSymbol("x"), new Constant(5)),
                        new Expression(ListFunctions.List, new Expression(ArithmeticFunctions.Times, new StringSymbol("x"), new Constant(3)), new Constant(4), new Constant(15))
                ))
        );
    }
}

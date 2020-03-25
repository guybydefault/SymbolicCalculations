package src.main.java.listFunctions;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

@RunWith(JUnit4.class)
public class SymbolicMapTest {

    @org.junit.Test
    public void testMapTimes_1() {
        evaluateAndAssert(
                new Expression(ListFunctions.Map,
                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                        new Expression(Functions.Fun, new StringSymbol("x"), new Expression(ArithmeticFunctions.Times, new StringSymbol("x"), new Constant(2)))),
                new Expression(ListFunctions.List, new Constant(2), new Constant(4))
        );
    }

}

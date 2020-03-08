package src.main.java.listFunctions;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.library.Functions;

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

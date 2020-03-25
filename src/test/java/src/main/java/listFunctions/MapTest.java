package src.main.java.listFunctions;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class MapTest {
    @org.junit.Test
    public void testMapTimes_1() {
        evaluateAndAssert(
                new Expression(ListFunctions.Map,
                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                        new Expression(Functions.Fun, Alphabet.x, new Expression(ArithmeticFunctions.Times, Alphabet.x, new Constant(2)))),
                new Expression(ListFunctions.List, new Constant(2), new Constant(4))
        );
    }
}

package src.main.java.listFunctions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class JavaFMapTest {
    @org.junit.Test
    public void testMapTimes_1() {
        evaluateAndAssert(
                new Expression(ListFunctions.FMap,
                        new Expression(ListFunctions.List, new Constant(1), new Constant(2)),
                        new Expression(Functions.Fun, Alphabet.x, new Expression(ArithmeticFunctions.Times, Alphabet.x, new Constant(2)))),
                new Expression(ListFunctions.List, new Constant(2), new Constant(4))
        );
    }
}

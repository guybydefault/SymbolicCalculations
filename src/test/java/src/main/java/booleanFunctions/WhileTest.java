package src.main.java.booleanFunctions;

import org.junit.Test;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.BooleanFunctions;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;


public class WhileTest {
    @Test
    public void whileTest_1() {
        evaluateAndAssert(
                new Expression(
                        BooleanFunctions.While,
                        new Constant(2),
                        new Expression(Functions.Fun, Alphabet.x, new Expression(BooleanFunctions.Less, Alphabet.x, new Constant(500))),
                        new Expression(Functions.Fun, Alphabet.x, new Expression(ArithmeticFunctions.Times, Alphabet.x, new Constant(2)))
                ),
                new Constant(512)
        );
    }
}

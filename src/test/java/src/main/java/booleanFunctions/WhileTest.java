package src.main.java.booleanFunctions;

import org.junit.Test;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.BooleanFunctions;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

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

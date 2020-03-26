package src.main.java.patternMatching;

import org.junit.Test;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class FirstTest {
    @Test
    public void test_1() {
        evaluateAndAssert(
                new Expression(
                        new StringSymbol("First"),
                        new Expression(
                                new StringSymbol("List"),
                                new Constant(1)
                        )
                ),
                new Constant(1)
        );
    }
}

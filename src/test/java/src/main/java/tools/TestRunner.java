package src.main.java.tools;

import org.junit.Assert;
import ru.symbolic.CalculationResult;
import ru.symbolic.Context;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.Symbol;


public class TestRunner {
    public static void evaluateAndAssert(
            Expression expression,
            Symbol expectedResult
    ) {
        Context context = new Context();
        CalculationResult result = context.run(expression);

        Assert.assertEquals(result.getSymbol(), expectedResult);
    }
}

package src.main.tools;

import org.junit.Assert;
import ru.guybydefault.CalculationResult;
import ru.guybydefault.Context;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.Symbol;


public class Test {
    public static void evaluateAndAssert(
            Expression expression,
            Symbol expectedResult
    ) {
        Context context = new Context();
        CalculationResult result = context.run(expression);

        Assert.assertEquals(result.getSymbol(), expectedResult);
    }
}

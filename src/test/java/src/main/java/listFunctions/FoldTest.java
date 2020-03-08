package src.main.java.listFunctions;

import org.junit.Test;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.library.Alphabet;
import ru.guybydefault.dsl.library.Functions;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class FoldTest {

    @Test
    public void foldTest_1() {
        evaluateAndAssert(
                new Expression(
                        new Expression(
                                new Expression(
                                        new Expression(
                                                ListFunctions.Fold,
                                                new Expression(ListFunctions.List, new Constant(1), new Constant(2), new Constant(3))
                                        ),
                                        new Constant(0))
                        ),
                        new Expression(
                                Functions.Fun,
                                Alphabet.acc,
                                new Expression(
                                        Functions.Fun,
                                        Alphabet.x,
                                        new Expression(
                                                ArithmeticFunctions.Plus,
                                                Alphabet.acc,
                                                Alphabet.x
                                        )
                                )
                        ))
                ,
                new Constant(6)
        );
    }
}

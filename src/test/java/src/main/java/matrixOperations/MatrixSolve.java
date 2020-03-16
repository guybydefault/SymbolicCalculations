package src.main.java.matrixOperations;

import org.junit.Test;
import org.xml.sax.SAXException;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;
import ru.guybydefault.io.xml.XMLParser;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static src.main.java.tools.TestRunner.evaluateAndAssert;

public class MatrixSolve {
    @Test
    public void solveMatrixTest_1() {
        evaluateAndAssert(
                new Expression(MatrixFunctions.MatrixSolve,
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(2), new Constant(-4), new Constant(3)),
                                new Expression(ListFunctions.List, new Constant(1), new Constant(-2), new Constant(4)),
                                new Expression(ListFunctions.List, new Constant(3), new Constant(-1), new Constant(5))
                        ),
                        new Expression(ListFunctions.List,
                                new Expression(ListFunctions.List, new Constant(1)),
                                new Expression(ListFunctions.List, new Constant(3)),
                                new Expression(ListFunctions.List, new Constant(2))
                        )
                ),
                new Expression(ListFunctions.List,
                        new Expression(ListFunctions.List, new Constant(-1)),
                        new Expression(ListFunctions.List, new Constant(0)),
                        new Expression(ListFunctions.List, new Constant(1))
                )
        );
    }

//    @Test
//    public void test_2() throws ParserConfigurationException, IOException, SAXException {
//        XMLParser xmlParser = new XMLParser();
//        evaluateAndAssert(xmlParser.parse("src/main/resources/test.xml").visit(AsExpressionVisitor.getInstance()), new Constant(0));
//    }
}

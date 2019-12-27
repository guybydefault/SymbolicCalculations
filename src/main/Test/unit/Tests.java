package unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import ru.guybydefault.old.domain.ExpressionInfo;
import ru.guybydefault.old.services.XMLParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Tests extends Assert {

    private final XMLParser xmlParser = new XMLParser();

    private String testData = null;

    @Before
    void setUpTest1Data() throws IOException {
        testData = readFromFile("test1.ml");
    }

    @After
    void tearDownTest1Data() {
        testData = null;
    }

    @Test
    void test1() {
        ExpressionInfo expressionInfo = xmlParser.parseExpression(Paths.get("test1.xml").toFile());
        String actual = expressionInfo.toString(); //and simplify
        assertEquals(testData, actual);
    }

    //to test determinant when we'll add it
    @Before
    void setUpTest2Data() throws IOException {
        testData = readFromFile("test2.ml");
    }

    @After
    void tearDownTest2Data() {
        testData = null;
    }

    @Test
    void test2() {
        ExpressionInfo expressionInfo = xmlParser.parseExpression(Paths.get("test2.xml").toFile());
        String actual = expressionInfo.toString();
        assertEquals(testData, actual);
    }

    private String readFromFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllLines(path).get(0);
    }
}

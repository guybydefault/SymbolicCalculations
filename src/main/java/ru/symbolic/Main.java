package ru.symbolic;

import org.xml.sax.SAXException;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.Symbol;
import ru.symbolic.io.xml.XMLParser;
import ru.symbolic.visitors.OutputMathMLVisitor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        XMLParser parser = new XMLParser();
        Symbol symbol = parser.parse("src/main/resources/sampleAdd.xml");
        Context context = new Context();
        CalculationResult calculationResult = context.run(symbol);
        String res = calculationResult.getSymbol().visit(OutputMathMLVisitor.getInstance()).toString();
        System.out.println(OutputMathMLVisitor.getInstance().addMathMl(res));
    }
}

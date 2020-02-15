package ru.guybydefault;

import org.xml.sax.SAXException;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.visitors.OutputMathMLVisitor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        XMLParser parser = new XMLParser();
        Symbol symbol = parser.parse("src/main/resources/sampleAdd.xml");
        System.out.println(symbol.visit(OutputMathMLVisitor.getInstance()));

        Context context = new Context();
        CalculationResult calculationResult = context.run(symbol);
        System.out.println(calculationResult.getSymbol().visit(OutputMathMLVisitor.getInstance()));

    }
}

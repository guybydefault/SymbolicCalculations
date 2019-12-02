package ru.guybydefault.input;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import ru.guybydefault.domain.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class XMLParser {

    public ExpressionInfo parseExpression(File file) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            Element root = document.getRootElement();
            return new ExpressionInfo(parse(root));
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Matrix parseMatrix(Element element) {
        ArrayList<ArrayList<Expression>> matrixExpressionsList = new ArrayList<>();
        Iterator<Element> it = element.elementIterator();
        while (it.hasNext()) {
            Element list = it.next();
            if (!"l".equals(list.attributeValue("Name"))) {
                throw new IllegalArgumentException("Only Symbol[Name='l'] are allowed inside Symbol[Name='m']");
            }
            ArrayList<Expression> expressionsList = new ArrayList<>();
            Iterator<Element> listIt = list.elementIterator();
            while (listIt.hasNext()) {
                expressionsList.add(parse(listIt.next()));
            }
            matrixExpressionsList.add(expressionsList);
        }
        return new Matrix(matrixExpressionsList.size(), matrixExpressionsList.get(0).size(), matrixExpressionsList);
    }

    private Expression parse(Element element) {
        switch (element.getName()) {
            case "ExpressionInfo":
                return parse(element.elementIterator().next());
            case "BinaryOp":
                Iterator<Element> it = element.elementIterator();
                Element child1 = it.next();
                Element child2 = it.next();
                return new BinaryOp(element.attributeValue("Name"), parse(child1), parse(child2));
            case "UnaryOp":
                return new UnaryOp(element.attributeValue("Name"), parse(element.elementIterator().next()));
            case "ApplySymbol":
                if (element.attributeValue("Name").equals("m")) {
                    return parseMatrix(element);
                } else {
                    throw new UnsupportedOperationException("Unknown ApplySymbol " + element.attributeValue("Name") + ": " + element.asXML());
                }
            case "Symbol":
                return new Value(ValueType.VARIABLE, element.attributeValue("Name"));
            case "Const":
                return new Value(ValueType.CONST, element.attributeValue("Value"));
            case "Function":

            default:
                return null;
        }
    }
}

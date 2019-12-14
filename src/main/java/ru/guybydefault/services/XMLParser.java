package ru.guybydefault.services;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import ru.guybydefault.domain.*;
import ru.guybydefault.domain.operations.BinaryOp;

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
        int height = 0;
        int width = 0;
        while (it.hasNext()) {
            Element list = it.next();
            if (!"l".equals(list.attributeValue("Name"))) {
                throw new IllegalArgumentException("Only Symbol[Name='l'] are allowed inside Symbol[Name='m']");
            }
            ArrayList<Expression> expressionsList = new ArrayList<>();
            Iterator<Element> listIt = list.elementIterator();
            while (listIt.hasNext()) {
                expressionsList.add(parse(listIt.next()));
                if (height == 0) {
                    width++;
                }
            }
            if (height > 0 && expressionsList.size() != width) {
                throw new IllegalArgumentException("Matrix row #" + (height + 1) + " does not fit width " + width);
            }
            matrixExpressionsList.add(expressionsList);
            height++;
        }
        return new Matrix(height, width, matrixExpressionsList);
    }

    private Function parseFunction(Element element) {
        Iterator<Element> elementIterator = element.elementIterator();
        Expression arg1 = parse(elementIterator.next());
        Expression arg2 = elementIterator.hasNext() ? parse(elementIterator.next()) : null;
        return arg2 == null ? new Function(element.attributeValue("Name"), arg1) : new Function(element.attributeValue("Name"), arg1, arg2);
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
                return parseFunction(element);
            default:
                return null;
        }
    }
}

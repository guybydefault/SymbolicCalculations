package ru.symbolic.io.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.visitors.cast.AsExpressionVisitor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import static ru.symbolic.dsl.library.Functions.SetDelayed;
import static ru.symbolic.io.xml.XMLConstants.STRING_SYMBOL_ATTRIBUTE_NAME;

public class XMLPrinterVisitor implements ISymbolVisitor<Element> {

    private DocumentBuilder documentBuilder;
    private File file;
    private Element root;
    private Document document;

    public XMLPrinterVisitor(File file) throws ParserConfigurationException {
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.file = file;
        document = documentBuilder.newDocument();
    }

    @Override
    public Element visitExpression(Expression expression) {
        Element element = document.createElement(XMLConstants.EXPRESSION);
        element.appendChild(expression.getHead().visit(this));
        expression.getArguments().forEach(arg -> {
            Expression expr = arg.visit(AsExpressionVisitor.getInstance());
            if (expr!= null && expr.getHead().equals(SetDelayed)) {
                return;
            }
            element.appendChild(arg.visit(this));
        });
        return element;
    }

    @Override
    public Element visitSymbol(StringSymbol symbol) {
        Element element = document.createElement(XMLConstants.STRING_SYMBOL);
        element.setAttribute(STRING_SYMBOL_ATTRIBUTE_NAME, symbol.getName());
        return element;
    }

    @Override
    public Element visitConstant(Constant constant) {
        Element element = document.createElement(XMLConstants.CONSTANT);
        element.appendChild(document.createTextNode(String.valueOf(constant.getValue())));
        return element;
    }

    public void print(Symbol symbol) throws TransformerException {
        root = symbol.visit(this);
        document.appendChild(root);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(domSource, streamResult);
    }
}

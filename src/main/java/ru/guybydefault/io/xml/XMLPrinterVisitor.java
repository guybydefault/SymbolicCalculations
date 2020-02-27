package ru.guybydefault.io.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.visitors.ISymbolVisitor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import static ru.guybydefault.io.xml.XMLConstants.STRING_SYMBOL_ATTRIBUTE_NAME;

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
        expression.getArguments().forEach(arg -> element.appendChild(arg.visit(this)));
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
        element.setNodeValue(String.valueOf(constant.getValue()));
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

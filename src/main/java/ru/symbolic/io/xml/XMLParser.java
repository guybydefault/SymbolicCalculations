package ru.symbolic.io.xml;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.symbolic.io.xml.XMLConstants.STRING_SYMBOL_ATTRIBUTE_NAME;

public class XMLParser {

    private static StringSymbolHashMapService stringSymbolHashMapService;

    private static DocumentBuilder documentBuilder;

    public XMLParser() throws ParserConfigurationException, IllegalAccessException {
        stringSymbolHashMapService = new StringSymbolHashMapService();
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    private static boolean validateXMLSchema(String xmlFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(ru.symbolic.io.xml.XMLConstants.XSD_SCHEMA_PATH));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            return false;
        }
        return true;
    }

    public Symbol parse(String fileName) throws IOException, SAXException {
        adaptFileToParser(fileName);
        Symbol result = null;
        if (validateXMLSchema(fileName)) {
            Document document = documentBuilder.parse(fileName);
            Node root = document.getDocumentElement();
            result = dfsParse(root.getFirstChild());
        }
        return result;
    }

    private void adaptFileToParser(String filename) throws IOException {
        FileUtils.writeStringToFile(
                FileUtils.getFile(filename),
                FileUtils.readFileToString(FileUtils.getFile(filename.replace(".xml", "_edited.xml")))
                        .replaceAll("\n", "")
                        .replaceAll("\t", "")
                        .replaceAll("\r", "")
                .replaceAll(">[\\s]*<", "><")
        );
    }

    private Symbol dfsParse(Node node) {
        switch (node.getNodeName()) {
            case ru.symbolic.io.xml.XMLConstants.EXPRESSION:
                return parseExpression(node);
            case ru.symbolic.io.xml.XMLConstants.STRING_SYMBOL:
                return parseStringSymbol(node);
            case ru.symbolic.io.xml.XMLConstants.CONSTANT:
                return parseConstant(node);
            default:
                throw new IllegalArgumentException("There must be only Expression," +
                        "StringSymbol or Constant types in parsing XML file!");
        }
    }

    private Expression parseExpression(Node node) {
        List<Symbol> arguments = new ArrayList<>();
        for (int i = 1; i < node.getChildNodes().getLength(); i++) {
            arguments.add(dfsParse(node.getChildNodes().item(i)));
        }
        return new Expression(dfsParse(node.getFirstChild()), arguments);
    }

    private StringSymbol parseStringSymbol(Node node) {
        StringSymbol[] arguments = new StringSymbol[node.getChildNodes().getLength()];
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            arguments[i] = parseStringSymbol(node.getChildNodes().item(i));
        }
        String stringSymbolName = node.getAttributes().getNamedItem(STRING_SYMBOL_ATTRIBUTE_NAME).getNodeValue();
        StringSymbol symbol = stringSymbolHashMapService.get(stringSymbolName);
        return symbol != null ? symbol : new StringSymbol(stringSymbolName);
    }

    private Constant parseConstant(Node node) {
        return new Constant(Double.parseDouble(node.getTextContent()));
    }
}

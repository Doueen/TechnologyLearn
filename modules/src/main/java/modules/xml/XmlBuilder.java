package modules.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-06 17:28
 */
public class XmlBuilder {

    public Document getDocument() {
        return document;
    }

    private final Document document;
    private final Element rootElement;

    public XmlBuilder(String rootElementName) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
        rootElement = document.createElement(rootElementName);
        document.appendChild(rootElement);
    }

    public void addElement(String parentElementName, String elementName) {
        Element parentElement;
        if (parentElementName.equals(rootElement.getTagName())) {
            parentElement = rootElement;
        } else {
            parentElement = (Element) rootElement.getElementsByTagName(parentElementName).item(0);
        }
        Element element = document.createElement(elementName);
        parentElement.appendChild(element);
    }

    public void addAttribute(String elementName, String attributeName, String attributeValue) {
        Element element = (Element) rootElement.getElementsByTagName(elementName).item(0);
        element.setAttribute(attributeName, attributeValue);
    }

    public void addText(String elementName, String text) {
        Element element = (Element) rootElement.getElementsByTagName(elementName).item(0);
        element.appendChild(document.createTextNode(text));
    }

    public void saveToFile(String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

    @Override
    public String toString() {
        TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();

        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        // ����һ��DOMSource����
        DOMSource source = new DOMSource(document);
        // ����һ��StringWriter����
        StringWriter writer = new StringWriter();

        // ����һ��StreamResult����
        StreamResult result = new StreamResult(writer);

        // ʹ��transform()������DOM����ת��Ϊ�ַ���
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        // ��ȡת������ַ���
        String xmlString = writer.toString();
        xmlString = xmlString.replace("standalone=\"no\"", "");
        return xmlString;

    }


}

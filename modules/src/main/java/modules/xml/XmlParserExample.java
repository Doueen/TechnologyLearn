package modules.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 15:48
 */
public class XmlParserExample {

    static String xmlString= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<message>\n" +
            "    <head>\n" +
            "        <version>1.0</version>\n" +
            "        <servicetype>OriginalService</servicetype>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <appid>drapp</appid>\n" +
            "    </body>\n" +
            "</message>";
    public static void parseXml(String xmlString) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlString)));

        Element root = document.getDocumentElement();
        NodeList headNodes = root.getElementsByTagName("head");
        for (int i = 0; i < headNodes.getLength(); i++) {
            Node headNode = headNodes.item(i);
            if (headNode.getNodeType() == Node.ELEMENT_NODE) {
                Element headElement = (Element) headNode;
                String version = headElement.getElementsByTagName("version").item(0).getTextContent();
                String servicetype = headElement.getElementsByTagName("servicetype").item(0).getTextContent();
                System.out.println(version+";"+servicetype);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        parseXmlU(xmlString);
    }

    public static void parseXmlU(String xmlString)throws Exception{
        System.out.println(XmlUtils.readXmlStringToPipeline(xmlString).getElementByName("head").getElementByName("servicetype").getElementText());
    }
}


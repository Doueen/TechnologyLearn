package modules.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringReader;


/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-02 16:07
 */


public class XmlUtils {


    /**
     * 从 XML 文件中读取文档对象
     *
     * @param filename XML 文件名
     * @return 根元素
     */
    public static Element readXmlFile(String filename) throws Exception {
        File xmlFile = new File(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlFile).getDocumentElement();
    }

    public static XmlOperationPipeline readXmlFileToPipeline(String filename) throws Exception {
        return new XmlOperationPipeline(readXmlFile(filename));
    }

    public static Element readXmlString(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlString))).getDocumentElement();
    }

    public static XmlOperationPipeline readXmlStringToPipeline(String xmlString) throws Exception {
        return new XmlOperationPipeline(readXmlString(xmlString));
    }

    /**
     * 获取指定名称的元素列表
     *
     * @param element 元素
     * @param name    元素名称
     * @return 元素列表
     */
    public static NodeList getElementsByName(Element element, String name) {
        return element.getElementsByTagName(name);
    }

    public static Element getElementByName(Element element, String name) {
        return (Element) element.getElementsByTagName(name).item(0);
    }

    public static XmlOperationPipeline getElementPipelineByName(Element element, String name) {
        return new XmlOperationPipeline((Element) element.getElementsByTagName(name).item(0));
    }

    /**
     * 获取指定元素的属性值
     *
     * @param element 元素对象
     * @param attr    属性名称
     * @return 属性值
     */
    public static String getAttributeValue(Element element, String attr) {
        return element.getAttribute(attr);
    }

    /**
     * 获取指定元素的文本内容
     *
     * @param element 元素对象
     * @return 文本内容
     */
    public static String getElementText(Element element) {
        return element.getTextContent();
    }

    public static class XmlOperationPipeline {

        private final Element thisElement;

        public XmlOperationPipeline(Element thisElement) {
            this.thisElement = thisElement;
        }

        /**
         * 获取指定名称的元素列表
         *
         * @param name 元素名称
         * @return 元素列表
         */
        public NodeList getElementsByName(String name) {
            return thisElement.getElementsByTagName(name);
        }

        public XmlOperationPipeline getElementByName(String name) {
            return new XmlOperationPipeline((Element) thisElement.getElementsByTagName(name).item(0));
        }

        /**
         * 获取指定元素的属性值
         *
         * @param attr 属性名称
         * @return 属性值
         */
        public String getAttributeValue(String attr) {
            return thisElement.getAttribute(attr);
        }

        /**
         * 获取指定元素的文本内容
         *
         * @return 文本内容
         */
        public String getElementText() {
            return thisElement.getTextContent();
        }


    }

}


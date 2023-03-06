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
     * �� XML �ļ��ж�ȡ�ĵ�����
     *
     * @param filename XML �ļ���
     * @return ��Ԫ��
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
     * ��ȡָ�����Ƶ�Ԫ���б�
     *
     * @param element Ԫ��
     * @param name    Ԫ������
     * @return Ԫ���б�
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
     * ��ȡָ��Ԫ�ص�����ֵ
     *
     * @param element Ԫ�ض���
     * @param attr    ��������
     * @return ����ֵ
     */
    public static String getAttributeValue(Element element, String attr) {
        return element.getAttribute(attr);
    }

    /**
     * ��ȡָ��Ԫ�ص��ı�����
     *
     * @param element Ԫ�ض���
     * @return �ı�����
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
         * ��ȡָ�����Ƶ�Ԫ���б�
         *
         * @param name Ԫ������
         * @return Ԫ���б�
         */
        public NodeList getElementsByName(String name) {
            return thisElement.getElementsByTagName(name);
        }

        public XmlOperationPipeline getElementByName(String name) {
            return new XmlOperationPipeline((Element) thisElement.getElementsByTagName(name).item(0));
        }

        /**
         * ��ȡָ��Ԫ�ص�����ֵ
         *
         * @param attr ��������
         * @return ����ֵ
         */
        public String getAttributeValue(String attr) {
            return thisElement.getAttribute(attr);
        }

        /**
         * ��ȡָ��Ԫ�ص��ı�����
         *
         * @return �ı�����
         */
        public String getElementText() {
            return thisElement.getTextContent();
        }


    }

}


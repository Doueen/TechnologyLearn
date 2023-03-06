package modules.xml;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-06 17:28
 */

import javax.xml.parsers.ParserConfigurationException;

public class XmlBuilderExample {

    public static void main(String[] args) throws ParserConfigurationException {
        XmlBuilder builder = new XmlBuilder("books");
        builder.addElement("books", "book");
        builder.addAttribute("book", "id", "1");
        //    builder.addText("book", "Java Programming");

        builder.addElement("books", "book");
        builder.addAttribute("book", "id", "2");
        // builder.addText("book", "Python Programming");

        builder.addElement("book", "bookAttr");
        builder.addText("bookAttr", "100yuan");


        System.out.println(builder.toString());


    }
}
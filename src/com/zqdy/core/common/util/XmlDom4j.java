package com.zqdy.core.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlDom4j {

	public void createXml() {
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("root");
		rootElement.addComment("root of xml");
		rootElement.addProcessingInstruction("taget", "text");

		Element familyElement = rootElement.addElement("family");
		familyElement.addAttribute("native", "china");

		Element father = familyElement.addElement("father");
		father.addText("guan");
		Element mother = familyElement.addElement("mother");
		mother.addText("liu");		

		try {
			XMLWriter output = new XMLWriter(new FileWriter(new File(
					"c:/myfamily.xml")));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Iterator getOptions(String filePath, String fileName) {

		SAXReader sax = new SAXReader();
		String path=filePath+fileName;
		Document document = null;
		try {
			File xmlFile = new File(path);
			document = sax.read(xmlFile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List list = document.selectNodes("//optionName");
		Iterator it = list.iterator();
		return it;
	}

	public static void main(String[] args) {
		XmlDom4j dom = new XmlDom4j();
		//dom.getOptions(new File("c:/myfamily.xml"), "china");
	}

}

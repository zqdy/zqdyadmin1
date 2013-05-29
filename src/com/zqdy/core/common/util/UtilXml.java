package com.zqdy.core.common.util;

import java.io.FileOutputStream;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class UtilXml {
	private Document doc;

	private SAXBuilder sb;

	private Format format = Format.getCompactFormat();

	private String PATH;

	private XMLOutputter XMLOut;

	/** ******���캯��******* */
	public UtilXml(String PATH) throws Exception {
		this.PATH = PATH;
		sb = new SAXBuilder();
		doc = sb.build(PATH);
	}

	/** ******��ȡ�ڵ�ֵ*********** */
	public String getNodeValue(String Node) throws Exception {

		Element root = doc.getRootElement();
		List ls = root.getChildren();
		String value = "";
		for (int i = 0; i < ls.size(); i++) {
			Element item = (Element) ls.get(i);
			if (item.getName().equals(Node))
				value = item.getText().trim();
		}
		return value;
	}

	/***************************************************************************
	 * ����?�ڵ���Ʋ��ҽڵ�?index >= 0 Ϊ�ҵ��ýڵ� index = -1 �޴˽ڵ� index = -2 �ҵ��ڵ㵫�˽ڵ���ֵ
	 * 
	 **************************************************************************/

	public int found(String Node, String value) throws Exception {
		int index = -1;
		Element root = doc.getRootElement();
		List ls = root.getChildren();
		for (int i = 0; i < ls.size(); i++) {
			Element item = (Element) ls.get(i);
			if (item.getName().equals(Node)) {
				String NodeValue = item.getText().trim();
				if (value.equals(NodeValue)) {
					index = i;
					break;
				} else {
					index = -2;
					break;
				}
			}
		}
		return index;
	}

	/** ******�޸Ľڵ�����********** */
	public void ModNodeValue(String Node, String value) throws Exception {

		Element root = doc.getRootElement();
		List ls = root.getChildren();
		for (int i = 0; i < ls.size(); i++) {
			Element item = (Element) ls.get(i);
			if (item.getName().equals(Node)) {
				item.setText(value);
			}
		}
		format.setIndent("    "); // ����xml�ļ�������Ϊ4���ո�

		XMLOut = new XMLOutputter(format);// Ԫ�غ���һ��Ԫ�����ĸ�
		XMLOut.output(doc, new FileOutputStream(PATH));
	}

	public void ModNodeValue(String Node, String value, int index)
			throws Exception {
		Element root = doc.getRootElement();
		List ls = root.getChildren();
		if (index <= ls.size()) {
			Element item = (Element) ls.get(index);
			if (item.getName().equals(Node)) {
				item.setText(value);
			}
		}
		format.setIndent("    "); // ����xml�ļ�������Ϊ4���ո�

		XMLOut = new XMLOutputter(format);// Ԫ�غ���һ��Ԫ�����ĸ�
		XMLOut.output(doc, new FileOutputStream(PATH));
	}

	/***************************************************************************
	 * *******�޸��ӽڵ�����
	 * 
	 * 
	 * @throws
	 * @throws Exception
	 */
	public void ModNodeChild(String Node, String ChildNode, String value)
			throws Exception {
		Element root = doc.getRootElement();
		List ls = root.getChildren();
		for (int i = 0; i < ls.size(); i++) {
			Element item = (Element) ls.get(i);
			if (item.getName().equals(Node)) {
				List ls2 = item.getChildren();
				for (int j = 0; j < ls2.size(); j++) {
					Element item2 = (Element) ls2.get(j);
					if (item2.getName().equals(ChildNode)) {
						item2.setText(value);
					}
				}
			}
		}
		format.setIndent("    "); // ����xml�ļ�������Ϊ4���ո�

		XMLOut = new XMLOutputter(format);// Ԫ�غ���һ��Ԫ�����ĸ�
		XMLOut.output(doc, new FileOutputStream(PATH));
	}

	public void ModNodeChild(String Node, String ChildNode, String value,
			int index) throws Exception {
		Element root = doc.getRootElement();
		List ls = root.getChildren();
		for (int i = 0; i < ls.size(); i++) {
			Element item = (Element) ls.get(i);
			if (item.getName().equals(Node)) {
				List ls2 = item.getChildren();
				Element item2 = (Element) ls2.get(index);
				if (item2.getName().equals(ChildNode)) {
					item2.setText(value);
				}
			}
		}
		format.setIndent("    "); // ����xml�ļ�������Ϊ4���ո�

		XMLOut = new XMLOutputter(format);// Ԫ�غ���һ��Ԫ�����ĸ�
		XMLOut.output(doc, new FileOutputStream(PATH));
	}

	/** ********�޸�����ֵ********** */
	public void ModAttribute(String Node, String Attribute, String value)
			throws Exception {
		Element root = doc.getRootElement();
		List ls = root.getChildren();
		for (int i = 0; i < ls.size(); i++) {
			Element item = (Element) ls.get(i);
			if (item.getName().equals(Node)) {
				item.getAttribute(Attribute).setValue(value);
			}
		}
		format.setIndent("    "); // ����xml�ļ�������Ϊ4���ո�

		XMLOut = new XMLOutputter(format);// Ԫ�غ���һ��Ԫ�����ĸ�
		XMLOut.output(doc, new FileOutputStream(PATH));
	}

	public void ModAttribute(String Node, String Attribute, String value,
			int index) throws Exception {
		Element root = doc.getRootElement();
		List ls = root.getChildren();
		Element item = (Element) ls.get(index);
		if (item.getName().equals(Node)) {
			item.getAttribute(Attribute).setValue(value);
		}
		format.setIndent("    "); // ����xml�ļ�������Ϊ4���ո�

		XMLOut = new XMLOutputter(format);// Ԫ�غ���һ��Ԫ�����ĸ�
		XMLOut.output(doc, new FileOutputStream(PATH));
	}

	/** ********��������********** */
	public static Attribute xmlAttribute(String strKey, String strValue)
			throws Exception {
		Attribute attribute = new Attribute(strKey, strValue);
		return attribute;
	}

}
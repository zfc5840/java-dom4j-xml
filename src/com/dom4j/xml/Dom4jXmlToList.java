package com.dom4j.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jXmlToList {
	public static void main(String[] args){
		xmlToList("books.xml");
	}
	
	//使用dom4j方式解析xml成String
	public static  void xmlToList(String filename){
		List<Map<String, String>> dbConnections = new ArrayList<Map<String, String>>();
	     SAXReader saxReader = new SAXReader();
	     Document document = null;
		try {
			document = saxReader.read("books.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	     Element connections = document.getRootElement();//获取根节点
		@SuppressWarnings("unchecked")
		Iterator<Element> rootIter = connections.elementIterator();
	     while (rootIter.hasNext()) {
	         Element connection = rootIter.next();
			@SuppressWarnings("unchecked")
			Iterator<Element> childIter = connection.elementIterator();
			Map<String, String> connectionInfo = new HashMap<String, String>();
	         @SuppressWarnings("unchecked")
			List<Attribute> attributes = connection.attributes();
	         for (int i = 0; i < attributes.size(); ++i) { // 添加节点属性
	             connectionInfo.put(attributes.get(i).getName(), attributes.get(i).getValue());
	         }
	        while (childIter.hasNext()) { // 添加子节点
	             Element attr = childIter.next();
	             connectionInfo.put(attr.getName().trim(), attr.getText().trim());
	         }
	         dbConnections.add(connectionInfo);
	     }
	 System.out.println("ddd");
	}
}

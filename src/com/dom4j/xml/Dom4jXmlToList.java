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
		//构建读取实例
	     SAXReader saxReader = new SAXReader();
	     Document document = null;
		try {
			document = saxReader.read("books2.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	     Element connections = document.getRootElement();//获取根节点，即bookstore
		@SuppressWarnings("unchecked")
		Iterator<Element> rootIter = connections.elementIterator();//获取节点集合，即book集合
		//循环遍历节点book
	     while (rootIter.hasNext()) {
	    	 //获取要遍历的节点，book节点
	         Element connection = rootIter.next();
			@SuppressWarnings("unchecked")
			//获取book节点下子节点的集合
			Iterator<Element> childIter = connection.elementIterator();
			Map<String, String> connectionInfo = new HashMap<String, String>();
	         @SuppressWarnings("unchecked")
	         //获取book节点属性集合
			List<Attribute> attributes = connection.attributes();
	         for (int i = 0; i < attributes.size(); ++i) { // 添加节点属性
	             connectionInfo.put(attributes.get(i).getName(), attributes.get(i).getValue());
	         }
	         //循环遍历book下子节点
	        while (childIter.hasNext()) { // 添加子节点
	        	//获取当前子节点
	             Element attr = childIter.next();
	             //判断当前节点是否存在子节点
	             Iterator<Element> childattr=  attr.elementIterator();
	             if(childattr.hasNext()){
	            	 foreachElement(attr);
	             }else{
	            	 System.out.println("没有节点");
	             }
	             connectionInfo.put(attr.getName().trim(), attr.getText().trim());
	         }
	         dbConnections.add(connectionInfo);
	     }
	}
	
	@SuppressWarnings("unchecked")
	public static void foreachElement(Element element){
		 Iterator<Element> childattr=  element.elementIterator();
		 boolean bl=childattr.hasNext();
         if(bl){
        	 Element attr = childattr.next();
        	 foreachElement(attr);
        	 Iterator<Element> childattr2=  attr.elementIterator();
    		 boolean bl2=childattr2.hasNext();
    		 if(bl2){
    			 System.out.println("节点名22："+attr.getName().trim()+"   节点值22："+ attr.getText().trim());
    		 }
        	
         }else{
        	 System.out.println("节点名："+element.getName().trim()+"   节点值："+ element.getText().trim());
         }
	}
}

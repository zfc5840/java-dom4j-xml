package com.dom4j.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class StringToXml {
  
	public static void main(String[] args){
	
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><bookstore><book id=\"1\"><name>火影忍者</name><author>岸本齐史</author><date>20041002</date><price>34</price></book><book id=\"2\" version=\"1.0\"><name>海贼王</name><address>日本</address><price>28</price></book></bookstore>";
		List<Map<String,Object>> list=getList(xml);
		System.out.println("书的个数为："+list.size());
	}
	
	public static  List<Map<String,Object>> getList(String xml){
	
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		  /*
	     * 将String转换为xml
	     */
	    Document docc =null;
        try {
			docc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
        Element rootElt = docc.getRootElement(); // 获取根节点
        Iterator iter = rootElt.elementIterator("book"); // 获取根节点下的子节点book
        // 遍历book节点
        while (iter.hasNext()){
        	 Element recordEle = (Element) iter.next();
        	 /*判断是否存在name节点*/
             List list1=recordEle.selectNodes("name");
             if (list1.size()==0){
            	 System.out.println("不存在name节点");
             }else{
            	 System.out.println("存在name节点");
             }
        	Map<String,Object> obj=new HashMap<String,Object>();
             for(Iterator ieson = recordEle.elementIterator(); ieson.hasNext();){
        		 Element elementSon = (Element) ieson.next();
        		 String  name = elementSon.getName();
        		 String  value = elementSon.getText();
        		 obj.put(name, value);
        		System.out.println("name="+name+" value="+value);               		 
        	 }	   
             list.add(obj);
        }
		
		return list;
	}
}

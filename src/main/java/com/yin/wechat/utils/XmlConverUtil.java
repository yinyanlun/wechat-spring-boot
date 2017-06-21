package com.yin.wechat.utils;


import java.io.IOException;
import java.io.StringReader;
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
import org.xml.sax.InputSource;

public class XmlConverUtil {
	public Map<String, Object> rowMap;
	
	public Map<String, Object> getRowMap() {
		return rowMap;
	}
	public void setRowMap(Map<String, Object> rowMap) {
		this.rowMap = rowMap;
	}
	private Map<String, Object> map = new HashMap<>();
	
	public Map<String,Object> xmlElements(String xmlDoc) throws DocumentException {
	        //创建一个新的字符串
	        StringReader read = new StringReader(xmlDoc);
	        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
	        InputSource source = new InputSource(read);
	        //创建一个新的SAXBuilder
	        SAXReader saxReader = new SAXReader();
	        Document document=saxReader.read(source);//获取document对象,如果文档无节点，则会抛出Exception提前结束  
	        Element root=document.getRootElement();//获取根节点  
	        this.getNodes(root);//从根节点开始遍历所有节点
	        rowMap = map;
        return rowMap;
    }
    /** 
	* 从指定节点开始,递归遍历所有子节点 
	* @author chenleixing 
	*/  
	public void getNodes(Element node){  
 	  //当前节点的名称、文本内容和属性  
	  map.put(node.getName(), node.getTextTrim());
	  System.out.println("当前节点名称："+node.getName());//当前节点名称  
	  System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称  
	  List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list  
	  for(Attribute attr:listAttr){//遍历当前节点的所有属性  
	      String name=attr.getName();//属性名称  
	      String value=attr.getValue();//属性的值  
	      map.put(name, value);
	      System.out.println("属性名称："+name+"属性值："+value);  
	  }  
	    
	  //递归遍历当前节点所有的子节点  
	  List<Element> listElement=node.elements();//所有一级子节点的list  
	  for(Element e:listElement){//遍历所有一级子节点  
	      this.getNodes(e);//递归  
	  }  
	}  
	
	public List<Map<String,Object>> xmlElementsByNodes(String xmlDoc) throws DocumentException
	{
		List<Map<String, Object>> listMap = new ArrayList<>();
		 //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXReader saxReader = new SAXReader();
        Document document=saxReader.read(source);//获取document对象,如果文档无节点，则会抛出Exception提前结束  
        Element root=document.getRootElement();//获取根节点  

        Element response = root.element("response");
        Element departments = response.element("departments");
        List<Element> list = departments.elements();
        for(Element ele:list){
        	  List<Element> listAttr=ele.elements();//当前节点的所有属性的list 
        	  map = new HashMap<>();
        	  for(Element attr:listAttr){//遍历当前节点的所有属性  
        	      String name=attr.getName();//属性名称  
        	      String value=attr.getTextTrim();//属性的值  
        	      map.put(name, value);
        	  }   
        	  listMap.add(map);
        }
		return listMap;
	}
	
}

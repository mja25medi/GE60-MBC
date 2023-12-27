package egovframework.edutrack.comm.util.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class DecoratorUtil {
	private static final Log log = LogFactory.getLog(DecoratorUtil.class);
	private Map<String, List> decoratorMap = new HashMap<String, List>();
	private static final String fileNm = "decorator.xml"; 
	

	public DecoratorUtil() {
		//-- 생성자
		try {
			getXmlData(new ClassPathResource(fileNm).getFile());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nSystem Error --> xml File Not Found!");
		}
	}
	
	private void getXmlData(File xmlFile)  {
	    
		try {
	        // 1. 빌더 팩토리 생성.
	        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	        // 2. 빌더 팩토리로부터 빌더 생성
	        DocumentBuilder builder = builderFactory.newDocumentBuilder();
	        // 3. 빌더를 통해 XML 문서를 파싱해서 Document 객체로 가져온다.
	        Document document = builder.parse(xmlFile);
	        // 문서 구조 안정화 
	        document.getDocumentElement().normalize();

	        // XML 데이터 중 <decorator> 태그의 내용을 가져온다.
	        NodeList tagList = document.getElementsByTagName("auth");
	        // <person> 태그 리스트를 하나씩 돌면서 값들을 가져온다.
	        List mappingArr = new ArrayList();
	        String keyName = "";
	        for (int i = 0; i < tagList.getLength(); ++i) {
	        	Node tplNode = tagList.item(i);
	        	if (tplNode.getNodeType() == Node.ELEMENT_NODE) {
		        	Element element = (Element)tplNode;
		        	keyName = element.getAttribute("name");
		        	// tpl 태그
					NodeList tplList= element.getElementsByTagName("tpl");
					String tplType="";

					for (int j = 0; j < tplList.getLength(); j++) {
						Element tplElmnt = (Element) tplList.item(j);
						tplType = tplElmnt.getAttribute("type");
						
			        	// mapping 태그
						NodeList mappingList= tplElmnt.getElementsByTagName("mapping");
						for (int k = 0; k < mappingList.getLength(); k++) {
							
							Element mappingElmnt = (Element) mappingList.item(k);
							String[] data = {tplType, mappingElmnt.getAttribute("action") };
							mappingArr.add(data);
						}						
						
					}
					decoratorMap.put(keyName, mappingArr);

	        	}
	         }
	        

		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	public boolean urlMatches(String key, String action)  {
		boolean result = false;
		List pathArr = (List)decoratorMap.get(key);
		
		for(int i=0 ; i<pathArr.size(); i++) {
			String[] dataArr = (String[])pathArr.get(i);
			if(action.endsWith(dataArr[1]) ) {
				result = true;
				break;
			}
		}
		
		return result;
    }
	public String getTplTypeCd(String key, String action)  {
		String result = "";
		boolean isMatches =false;
		List pathArr = (List)decoratorMap.get(key);
		
		if(pathArr != null) {
			for(int i=0 ; i<pathArr.size(); i++) {
				String[] dataArr = (String[])pathArr.get(i);
				String[] urlArr = StringUtil.split(dataArr[1],"*");
				if(urlArr.length > 1) {
					boolean urlMatches = true;
					for(int k=0; k<urlArr.length; k++) {
						if(k==0 && !action.startsWith(urlArr[0])) {
							urlMatches = false;
						} else if (urlArr.length-1 == k && !action.endsWith(urlArr[k])) {
							urlMatches = false;
						} else if (action.indexOf(urlArr[k]) < 0 ){
							urlMatches = false;
						}
					}
					if(urlMatches) {
						result = dataArr[0];
						break;
					}
				} else if(action.endsWith(dataArr[1])) {
					result = dataArr[0];
					break;
				}
			}
		}
		
		return result;
    }
	
}

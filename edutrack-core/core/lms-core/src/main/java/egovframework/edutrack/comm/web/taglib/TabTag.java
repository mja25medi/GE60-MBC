package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import egovframework.edutrack.comm.util.web.LocaleUtil;
import egovframework.edutrack.comm.util.web.StringUtil;


/**
 * 탭 페이지를 출력해준다. 
 * @author 
 */
public class TabTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3343746878998803324L;
	private String messageKey = ""; // 메시지키 Or 메시지
	private String funcName;
	private String authGroup;
	private String tabId;
	private String pageCode;
	private String tabCode;
	private String className;
	
	
	/**
	 * 탭을 클릭했을때 호출되는 자바스크립트 함수명을 설정한다.
	 * @param funcName
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public void setAuthGroup(String authGroup) {
		this.authGroup = StringUtil.ReplaceAll(authGroup,"|","");
	}

	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey
	 * the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	/**
	 * 탭 서비스 페이지 코드 (권한처리용)
	 * @param pageCode
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	/**
	 * 탭 노출 권한 처리 코드 (권한처리용)
	 * @param tabCode
	 */
	public void setTabCode(String tabCode) {
		this.tabCode = tabCode;
	}
	public void setClassName(String className) {
		this.className = className;
	}



	/**
	 * 페이징 번호를 출력해준다.
	 */
	public int doEndTag() throws JspException{
		try {
			PageContext context = this.pageContext;
			HttpServletRequest req = (HttpServletRequest) context
						.getRequest();
			Locale locale = LocaleUtil.getLocale(req);
			WebApplicationContext appContext = 
					ContextLoader.getCurrentWebApplicationContext();
			MessageSource messageSource = (MessageSource)appContext.getBean("messageSource");
			
			String messageName = messageSource.getMessage(messageKey, null, "", locale);
			if(messageName == null || messageName.equals("")) {
				messageName = messageKey;
			}
			
			StringBuilder page = new StringBuilder();
//			page.append("<li id=\""+tabId+"\"><span onMouseDown=\""+funcName+"\">"+messageName+"</span></li>");
			page.append("<li class=\""+className+"\"><a href=\"javascript:"+funcName+"\">"+messageName+"</a></li>");
			if(isAuthorize()) {
				if(getAuth()) {pageContext.getOut().println(page.toString());	}
			} else {
				// 권한체크가 없이 태그를 출력한다. 
				pageContext.getOut().println(page.toString());		
			}

		} catch (IOException ignored) { }
		return EVAL_PAGE;
	}
	
	private boolean getAuth() {
		// xml 파싱 빌드업
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		boolean result = false;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			// xml 파일을 document로 파싱하기
			Document document = builder.parse(new ClassPathResource("/page-auth.xml").getInputStream());
			
			document.getDocumentElement().normalize();
			NodeList pageList = document.getElementsByTagName("page");
			Element pageElement = getElement(pageCode, pageList);
			if(pageElement != null) {
				NodeList tabList = pageElement.getElementsByTagName("tab");
				Element tabElement = getElement(tabCode, tabList);
				if(tabElement != null) {
					NodeList authList = tabElement.getElementsByTagName("auth_group");
					Element authElement = getElement(authGroup, authList);
					if(authElement != null) {
						result = true;
					}
				}
			}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	// 일치하는 Element 값을 가지고 온다.
	private static Element getElement(String code, NodeList nList) {
		Element resultElement = null;
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++){		
				Node nNode = nList.item(i);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element nElement = (Element) nNode;
					/*
					 * System.out.println("######################"); System.out.println("코드  : " +
					 * getTagValue("code", nElement)); System.out.println("이름  : " +
					 * getTagValue("name", nElement));
					 */
					if(code.equals(getTagValue("code", nElement))) {
						resultElement = nElement;
						break;
					}

				}	// if end
			}	// for end
		}		    
		return resultElement;
	}
	
	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		String result = "";

		NodeList nlList = eElement.getChildNodes();
		for(int i = 0; i < nlList.getLength(); i++){ 
			Node nNode = nlList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element nElement = (Element) nNode;
				if(nElement.getNodeName().equals(tag) ) {
					result= nElement.getTextContent();
					break;
				}
			}
		}
	    return result;
	}

	/**
	 * 사용자가 권한체크가 필요한 권한그룹인지 점검 
	 * @param 
	 * @return
	 */
	private boolean isAuthorize() {
		
		return !authGroup.equals("ADMIN")
			&& !authGroup.equals("MANAGER");
	}

}

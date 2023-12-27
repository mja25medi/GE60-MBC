package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.edutrack.comm.util.web.DateTimeUtil;



/**
 * 날짜를 포맷에 맞게 변환해준다.
 * @author 장철웅
 */
public class DateFormatterTag extends TagSupport {
	private static final long serialVersionUID = 3566135104706641507L;
	
    private String property;
    private String type;
    private String delimeter;
    
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	
	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the delimeter
	 */
	public String getDelimeter() {
		return delimeter;
	}
	
	/**
	 * @param delimeter the delimeter to set
	 */
	public void setDelimeter(String delimeter) {
		this.delimeter = delimeter;
	}
	
	/**
     * 날짜를 포맷에 맞게 출력해 준다.
     */
	public int doEndTag() throws JspException{
        try{
        	String convertDate = null;
	    	String inputDate = null;
	    	inputDate = property;
	    	
	    	if(delimeter != null && !delimeter.equals("")) convertDate = DateTimeUtil.getDateType(Integer.parseInt(type), inputDate, delimeter); 
	    	else convertDate = DateTimeUtil.getDateType(Integer.parseInt(type), inputDate);
	    	
		    pageContext.getOut().print(convertDate);
	    }catch(IOException ignored){}
		
	    return EVAL_PAGE;
    }
}
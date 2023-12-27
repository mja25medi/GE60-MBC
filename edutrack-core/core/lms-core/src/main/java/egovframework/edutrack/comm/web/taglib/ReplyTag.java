package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 버튼 그룹을 감싸는 태그를 생성.
 * 
 * @author SungKook
 * 
 */
public class ReplyTag extends TagSupport {
	private static final long serialVersionUID = -1455435929684657578L;
	
	private static final String IMG_FRAMEWORK_ICON_PATH = "/img/framework/icon/";

	private int level = 0;	// 글의 레벨
	
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();

		String html = "";
		
		if( level > 0 ) {
			for(int i = 0; i < level; i++) {
				html += "&nbsp;&nbsp;&nbsp;&nbsp;";	// 레벨만큼 공백을 생성
			}
			// 꺽쇠 아이콘 삽입
			html += "<img src=\"" + getContextPath() + IMG_FRAMEWORK_ICON_PATH + "reply.gif\" alt=\"답글\"/>&nbsp;";
			try {
				writer.print(html);
			} catch (IOException none) {}
		}

		return EVAL_BODY_INCLUDE;
	}
	
	
	private String getContextPath() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return request.getContextPath();
	}
}

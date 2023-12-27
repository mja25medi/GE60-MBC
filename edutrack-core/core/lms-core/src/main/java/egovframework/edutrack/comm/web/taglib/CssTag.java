package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * CSS Link 태그를 생성해준다.
 * @author 장철웅
 */
public class CssTag extends TagSupport {
	private static final long serialVersionUID = -8600351825648153337L;

	private String href;

	/**
	 * CSS 파일의 위치를 돌려준다.
	 * @return Returns the href.
	 */
	public String getHref() {
		return href;
	}
	/**
	 * CSS 파일의 위치 셋팅한다.
	 * @param href The href to set.
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * CSS 링크 태그를 돌려준다.
	 */
	@Override
	public int doEndTag() {
		HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
		String lmsContext = req.getContextPath();

		try {
			StringBuilder tag = new StringBuilder()
			.append("<link rel=\"stylesheet\" href=\"")
			.append(lmsContext+"/")
			.append(href)
			.append("\" type=\"text/css\" />");

			pageContext.getOut().print(tag.toString());
		} catch (IOException ignored) { }
		return EVAL_PAGE;

	}
}

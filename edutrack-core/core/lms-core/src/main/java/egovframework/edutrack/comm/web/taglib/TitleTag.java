package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

public class TitleTag extends TagSupport{

	private static final long serialVersionUID = 29088029610968931L;

	private String alt; // Alt
	private String title; // 타이틀
	private String width = "300";
	private String height = "35";


	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 이미지 태그를 생성해준다.
	 */
	@Override
	public int doEndTag() {
		HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
		String lmsContext = req.getContextPath();
		String encTitle = "";
		try {
			encTitle = URLEncoder.encode(title, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		try {
			StringBuffer tag = new StringBuffer("");
			tag.append("<span style='font-size:16pt;font-weight:bold;'>"+title+"</span>");
			pageContext.getOut().print(tag.toString());
		} catch (IOException ignored) {

		}
		return EVAL_PAGE;
	}

}

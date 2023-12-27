package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.edutrack.comm.util.web.StringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



/**
 * ListDTO를 사용하여 페이징 페이지를 출력할 시 페이지 구분을 출력해준다. 
 * @author 장철웅
 */
public class PagingTag extends TagSupport {
	private static final long serialVersionUID = 2508056547525242158L;
	
	private String funcName;
	private String name;
	private PaginationInfo pageInfo;

	/**
	 * 페이지번호를 클릭했을때 호출되는 자바스크립트 함수명을 설정한다.
	 * @param funcName
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * PageInfo를 직접 설정한다. (name, property를 사용하지 않아도 된다.)
	 * @param pageInfo
	 */
	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * 페이징 번호를 출력해준다.
	 */
	public int doEndTag() throws JspException{
		try {
			StringBuilder page = new StringBuilder();
			
			if(StringUtil.nvl(name,"").equals("front")) {
				page.append("<a href=\"javascript:"+funcName+"(1)\" class=\"page_first\" title=\"첫페이지\">");
				page.append("	<i class=\"xi-angle-left-min\" aria-hidden=\"true\"></i>");
				page.append("	<span class=\"sr_only\">첫페이지</span>");
				page.append("</a>");
				
				if(pageInfo.getCurrentPageNo()>1){
					page.append("<a href=\"javascript:"+funcName+"("+ (pageInfo.getCurrentPageNo()-1) +")\" class=\"page_prev\" title=\"이전페이지\">");
				}else{
					page.append("<a href=\"#\" class=\"page_prev\" title=\"이전페이지\">");
				}
				
				page.append("	<i class=\"xi-angle-left-min\" aria-hidden=\"true\"></i>");
				page.append("	<span class=\"sr_only\">이전페이지</span>");
				page.append("</a>");
				
				for(int i=1; i<=pageInfo.getTotalPageCount(); i++) {
					if(i==pageInfo.getCurrentPageNo()) {
						page.append("<a href=\"javascript:"+funcName+"("+ i +")\" class=\"page_now\" title=\""+i+"페이지\">");
						page.append("<strong>" + i + "</strong>");
						page.append("</a>");
					}else {
						page.append("<a href=\"javascript:"+funcName+"("+ i +")\" class=\"page_none\" title=\""+i+"페이지\">");
						page.append(i);
						page.append("</a>");
					}
				}
				
				if(pageInfo.getCurrentPageNo() < pageInfo.getTotalPageCount()) {
					page.append("<a href=\"javascript:"+funcName+"("+ (pageInfo.getCurrentPageNo()+1) + ")\" class=\"page_next\" title=\"다음페이지\">");
				}else {
					page.append("<a href=\"#\" class=\"page_next\" title=\"다음페이지\">");
				}
				page.append("	<i class=\"xi-angle-right-min\" aria-hidden=\"true\"></i>");
				page.append("	<span class=\"sr_only\">다음페이지</span>");
				page.append("</a>");
				
				page.append("<a href=\"javascript:"+funcName+"("+pageInfo.getTotalPageCount()+")\" class=\"page_last\" title=\"마지막페이지\">");
				page.append("<i class=\"xi-angle-right-min\" aria-hidden=\"true\"></i>");
				page.append("<span class=\"sr_only\">마지막페이지</span>");
				page.append("</a>");
			}else if(StringUtil.nvl(name,"").equals("lect")) {

				if(pageInfo.getCurrentPageNo()>1){
					page.append("<a href=\"javascript:"+funcName+"("+ (pageInfo.getCurrentPageNo()-1) +")\" aria-label=\"Previous\" title=\"이전페이지\">");
				} else{
					page.append("<a href=\"#\"  aria-label=\"Previous\" title=\"이전페이지\">");
				}
				page.append("	<span aria-hidden=\"true\">«</span>");
				page.append("</a>");
				
				for(int i=1; i<=pageInfo.getTotalPageCount(); i++) {
					if(i==pageInfo.getCurrentPageNo()) {
						page.append("<a href=\"javascript:"+funcName+"("+ i +")\" class=\"page_now\" title=\""+i+"페이지\">");
						page.append("<strong>" + i + "</strong>");
						page.append("</a>");
					}else {
						page.append("<a href=\"javascript:"+funcName+"("+ i +")\" class=\"page_none\" title=\""+i+"페이지\">");
						page.append(i);
						page.append("</a>");
					}
				}
				if(pageInfo.getCurrentPageNo() < pageInfo.getTotalPageCount()) {
					page.append("<a href=\"javascript:"+funcName+"("+ (pageInfo.getCurrentPageNo()+1) + ")\"    aria-label=\"Next\"  title=\"다음페이지\">");
				}else {
					page.append("<a href=\"#\"    aria-label=\"Next\"  title=\"다음페이지\">");
				}
				page.append("	<span aria-hidden=\"true\">»</span>");
				page.append("</a>");

			}else {
				page.append("<div id='paginator_box'>");
				page.append("    <div class='paginator' id='paginator'></div>");
				page.append("    <div class='paginator_pages'>"+pageInfo.getTotalPageCount()+" pages</div>");
				page.append("    <script type='text/javascript'>");
				page.append("        page = new Paginator('paginator',"+pageInfo.getTotalPageCount()+","+pageInfo.getPageSize()+","+pageInfo.getCurrentPageNo()+",'javascript:"+funcName+"');");
				page.append("    </script>");
				page.append("</div>");
			}
			
			pageContext.getOut().println(page.toString());			

		} catch (IOException ignored) { }
		return EVAL_PAGE;
	}
}

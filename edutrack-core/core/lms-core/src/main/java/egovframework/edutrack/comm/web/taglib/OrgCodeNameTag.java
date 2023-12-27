package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.edutrack.comm.service.OrgCodeMemService;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;

/**
 * 지정된 코드에 대한 코드명을 돌려준다.
 * @author shil
 *
 */
@SuppressWarnings("serial")
public class OrgCodeNameTag extends TagSupport {

	private String category;	// 시스템코드 카테고리
	private String code;		// 시스템코드
	private String orgCd;		// 기관코드
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the orgCd
	 */
	public String getOrgCd() {
		return orgCd;
	}
	/**
	 * @param orgCd the code to set
	 */
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}


	public int doEndTag() throws JspException {
		OrgCodeMemService service = WebApplicationContextUtils
				.getWebApplicationContext(pageContext.getServletContext())
				.getBean(OrgCodeMemService.class);

		if (service == null)
			throw new JspException("웹어플리케이션 컨텍스트의 서비스를 로드하지 못했습니다.");

		try {
			String codeName = code;

	    	if (!StringUtil.nvl(category).equals("")) {
	    		List<OrgCodeVO> codeList ;
	    		try {
	    			codeList = service.getOrgCodeList(orgCd, category);
	    		} catch (Exception e) {
	    			codeList = new ArrayList<OrgCodeVO>(); 
	    		}

	    		PageContext context = this.pageContext;
	    		HttpServletRequest req = (HttpServletRequest)context.getRequest();
	    		String locale = UserBroker.getLocaleKey(req);

	    		for (OrgCodeVO socvo :  codeList) {
	    			if ((socvo.getCodeCd()).equals(code)) {
	    				codeName = socvo.getCodeNm();
	    				/*
	    				for(OrgCodeLangDTO orgCodeLangDTO : orgCodeDTO.getCodeLangList()) {
	    					if(locale.equals(orgCodeLangDTO.getLangCd())) codeName = orgCodeLangDTO.getCodeNm();
	    				}
	    				*/
	    				break;
	    			}
				}

	    	}

			pageContext.getOut().print(codeName);
		}
		catch (IOException ignored) { }

		return EVAL_PAGE;
	}

}

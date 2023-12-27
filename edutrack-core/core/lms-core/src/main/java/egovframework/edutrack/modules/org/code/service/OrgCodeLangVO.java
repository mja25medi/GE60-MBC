package egovframework.edutrack.modules.org.code.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgCodeLangVO extends DefaultVO{

	private static final long serialVersionUID = 7255231731359148003L;

	private String	orgCd;
	private String  codeCtgrCd;
	private String  codeCd;
	private String  langCd;
	private String  codeNm;
	private String  codeDesc;
	
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCodeCtgrCd() {
		return codeCtgrCd;
	}
	public void setCodeCtgrCd(String codeCtgrCd) {
		this.codeCtgrCd = codeCtgrCd;
	}
	public String getCodeCd() {
		return codeCd;
	}
	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
}

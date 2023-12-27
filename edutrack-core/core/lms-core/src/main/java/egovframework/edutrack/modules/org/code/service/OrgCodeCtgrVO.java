package egovframework.edutrack.modules.org.code.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgCodeCtgrVO extends DefaultVO{

	private static final long serialVersionUID = -4265527871171838050L;
	private String	orgCd;
	private String 	codeCtgrCd;
	private String 	codeCtgrNm;
	private int		codeCtgrOdr;
	private String	codeCtgrDesc;
	private String	useYn = "Y";
	
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
	public String getCodeCtgrNm() {
		return codeCtgrNm;
	}
	public void setCodeCtgrNm(String codeCtgrNm) {
		this.codeCtgrNm = codeCtgrNm;
	}
	public int getCodeCtgrOdr() {
		return codeCtgrOdr;
	}
	public void setCodeCtgrOdr(int codeCtgrOdr) {
		this.codeCtgrOdr = codeCtgrOdr;
	}
	public String getCodeCtgrDesc() {
		return codeCtgrDesc;
	}
	public void setCodeCtgrDesc(String codeCtgrDesc) {
		this.codeCtgrDesc = codeCtgrDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}

package egovframework.edutrack.modules.board.faq.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class BrdFaqCtgrVO extends DefaultVO {

	private static final long serialVersionUID = 6415889258415774897L;

	private String  orgCd;
	private String  ctgrCd;
	private String  ctgrNm;
	private Integer ctgrOdr;
	private String  ctgrDesc;
	private String  useYn;
	private String  autoMakeYn;
	
	private Integer subCnt;
		
	
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public Integer getCtgrOdr() {
		return ctgrOdr;
	}
	public void setCtgrOdr(Integer ctgrOdr) {
		this.ctgrOdr = ctgrOdr;
	}
	public String getCtgrDesc() {
		return ctgrDesc;
	}
	public void setCtgrDesc(String ctgrDesc) {
		this.ctgrDesc = ctgrDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getSubCnt() {
		return subCnt;
	}
	public void setSubCnt(Integer subCnt) {
		this.subCnt = subCnt;
	}
}

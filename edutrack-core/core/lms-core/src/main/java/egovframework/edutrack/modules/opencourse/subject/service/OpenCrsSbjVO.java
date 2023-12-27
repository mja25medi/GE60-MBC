package egovframework.edutrack.modules.opencourse.subject.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OpenCrsSbjVO extends DefaultVO {

	private static final long serialVersionUID = 6753278459299181839L;
	private String  crsCd;
	private String  sbjCd;
	private Integer sbjOdr;
	private String  orgCd;
	private String  sbjNm;
	private String  contentsCnt;
	private String  winMenuUseYn;

	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public Integer getSbjOdr() {
		return sbjOdr;
	}
	public void setSbjOdr(Integer sbjOdr) {
		this.sbjOdr = sbjOdr;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public String getContentsCnt() {
		return contentsCnt;
	}
	public void setContentsCnt(String contentsCnt) {
		this.contentsCnt = contentsCnt;
	}
	public String getWinMenuUseYn() {
		return winMenuUseYn;
	}
	public void setWinMenuUseYn(String winMenuUseYn) {
		this.winMenuUseYn = winMenuUseYn;
	}
}

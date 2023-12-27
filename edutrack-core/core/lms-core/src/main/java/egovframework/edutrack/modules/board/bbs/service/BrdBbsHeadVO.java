package egovframework.edutrack.modules.board.bbs.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class BrdBbsHeadVO extends DefaultVO {

	private static final long serialVersionUID = 2881737304246255878L;
	private String  orgCd;
	private String  bbsCd;
	private String  headCd;
	private String  headNm;
	private Integer headOdr;
	private String  useYn;
	private String  useYnNm;
	private String  autoMakeYn;
	
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getBbsCd() {
		return bbsCd;
	}
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	public String getHeadCd() {
		return headCd;
	}
	public void setHeadCd(String headCd) {
		this.headCd = headCd;
	}
	public String getHeadNm() {
		return headNm;
	}
	public void setHeadNm(String headNm) {
		this.headNm = headNm;
	}
	public Integer getHeadOdr() {
		return headOdr;
	}
	public void setHeadOdr(Integer headOdr) {
		this.headOdr = headOdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
}

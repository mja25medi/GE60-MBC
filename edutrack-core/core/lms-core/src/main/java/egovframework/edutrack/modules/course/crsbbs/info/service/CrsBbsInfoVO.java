package egovframework.edutrack.modules.course.crsbbs.info.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class CrsBbsInfoVO extends DefaultVO {

	private static final long serialVersionUID = 4479042892560124419L;
	private String  crsCd;
	private String  bbsCd;
	private String  bbsNm;
	private String  bbsDesc;
	private String  ansrUseYn;
	private String  cmntUseYn;
	
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getBbsCd() {
		return bbsCd;
	}
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	public String getBbsNm() {
		return bbsNm;
	}
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
	public String getBbsDesc() {
		return bbsDesc;
	}
	public void setBbsDesc(String bbsDesc) {
		this.bbsDesc = bbsDesc;
	}
	public String getAnsrUseYn() {
		return ansrUseYn;
	}
	public void setAnsrUseYn(String ansrUseYn) {
		this.ansrUseYn = ansrUseYn;
	}
	public String getCmntUseYn() {
		return cmntUseYn;
	}
	public void setCmntUseYn(String cmntUseYn) {
		this.cmntUseYn = cmntUseYn;
	}
}

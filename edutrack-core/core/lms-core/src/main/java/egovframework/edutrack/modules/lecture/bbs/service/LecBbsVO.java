package egovframework.edutrack.modules.lecture.bbs.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LecBbsVO
		extends DefaultVO {

	private static final long	serialVersionUID	= -1994432148801966786L;
	
	private String	crsCreCd;										// VARCHAR2(10 BYTE)
	private String	bbsCd;											// NUMBER(9)
	private String	bbsNm;											// NVARCHAR2(50),
	private String	bbsDesc;										// NVARCHAR2(2000),
	private String	ansrUseYn;										// CHAR(1 BYTE),
	private String	cmntUseYn;										// CHAR(1 BYTE),

	public LecBbsVO() {

	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
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
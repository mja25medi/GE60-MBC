package egovframework.edutrack.modules.student.worklog.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class StdEduRsltWorkLogVO extends DefaultVO {

	private static final long serialVersionUID = 9030233540855764439L;
	private String  regDttm;
	private String  crsCreCd;
	private String  regDivCd;
	private String  regNo;
	private String  regMthd;
	private String  regCts;

	private String  regNm;

	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getRegDivCd() {
		return regDivCd;
	}
	public void setRegDivCd(String regDivCd) {
		this.regDivCd = regDivCd;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegMthd() {
		return regMthd;
	}
	public void setRegMthd(String regMthd) {
		this.regMthd = regMthd;
	}
	public String getRegCts() {
		return regCts;
	}
	public void setRegCts(String regCts) {
		this.regCts = regCts;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
}

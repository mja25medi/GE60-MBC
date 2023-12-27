package egovframework.edutrack.modules.course.creterm.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class CourseCretermVO extends DefaultVO {

	private static final long serialVersionUID = -5152588029257101526L;

	private String  orgCd;
	private String crsTermCd;
	private String creYear;
	private String creTerm;
	private String enrlAplcStartDttm;
	private String enrlAplcEndDttm;
	private String enrlStartDttm;
	private String enrlEndDttm;
	private String termEndDttm;
	private String scoreStartDttm;
	private String scoreEndDttm;
	private String termDesc;
	private String deptCd;
	
	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getCrsTermCd() {
		return crsTermCd;
	}

	public void setCrsTermCd(String crsTermCd) {
		this.crsTermCd = crsTermCd;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getTermDesc() {
		return termDesc;
	}

	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}

	public String getScoreEndDttm() {
		return scoreEndDttm;
	}

	public void setScoreEndDttm(String scoreEndDttm) {
		this.scoreEndDttm = scoreEndDttm;
	}

	public String getScoreStartDttm() {
		return scoreStartDttm;
	}

	public void setScoreStartDttm(String scoreStartDttm) {
		this.scoreStartDttm = scoreStartDttm;
	}

	public String getTermEndDttm() {
		return termEndDttm;
	}

	public void setTermEndDttm(String termEndDttm) {
		this.termEndDttm = termEndDttm;
	}

	public String getEnrlEndDttm() {
		return enrlEndDttm;
	}

	public void setEnrlEndDttm(String enrlEndDttm) {
		this.enrlEndDttm = enrlEndDttm;
	}

	public String getEnrlStartDttm() {
		return enrlStartDttm;
	}

	public void setEnrlStartDttm(String enrlStartDttm) {
		this.enrlStartDttm = enrlStartDttm;
	}

	public String getEnrlAplcEndDttm() {
		return enrlAplcEndDttm;
	}

	public void setEnrlAplcEndDttm(String enrlAplcEndDttm) {
		this.enrlAplcEndDttm = enrlAplcEndDttm;
	}

	public String getEnrlAplcStartDttm() {
		return enrlAplcStartDttm;
	}

	public void setEnrlAplcStartDttm(String enrlAplcStartDttm) {
		this.enrlAplcStartDttm = enrlAplcStartDttm;
	}

	public String getCreTerm() {
		return creTerm;
	}

	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}

	public String getCreYear() {
		return creYear;
	}

	public void setCreYear(String creYear) {
		this.creYear = creYear;
	}

}
package egovframework.edutrack.modules.course.coursesubject.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 과정 온라인 과목 VO
 * 
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 온라인 과목
 * </pre>
 * 
 * @author MediopiaTech
 */
public class CrsOnlnSbjVO extends DefaultVO {

	private static final long serialVersionUID = -5014048175579288650L;

	private String  orgCd;
	private String  crsCd;
	private String  sbjCd;
	private String  studyMthd;
	private String  studyMthdNm;
	private Integer sbjOdr;
	
	private String  sbjNm;
	private String  sbjCtgrCd;
	private String  sbjCtgrNm;
	private Integer unitCnt;
	private String  sbjType;
	
	
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
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
	public String getStudyMthd() {
		return studyMthd;
	}
	public void setStudyMthd(String studyMthd) {
		this.studyMthd = studyMthd;
	}
	public String getStudyMthdNm() {
		return studyMthdNm;
	}
	public void setStudyMthdNm(String studyMthdNm) {
		this.studyMthdNm = studyMthdNm;
	}
	public Integer getSbjOdr() {
		return sbjOdr;
	}
	public void setSbjOdr(Integer sbjOdr) {
		this.sbjOdr = sbjOdr;
	}
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public String getSbjCtgrCd() {
		return sbjCtgrCd;
	}
	public void setSbjCtgrCd(String sbjCtgrCd) {
		this.sbjCtgrCd = sbjCtgrCd;
	}
	public String getSbjCtgrNm() {
		return sbjCtgrNm;
	}
	public void setSbjCtgrNm(String sbjCtgrNm) {
		this.sbjCtgrNm = sbjCtgrNm;
	}
	public Integer getUnitCnt() {
		return unitCnt;
	}
	public void setUnitCnt(Integer unitCnt) {
		this.unitCnt = unitCnt;
	}
	public String getSbjType() {
		return sbjType;
	}
	public void setSbjType(String sbjType) {
		this.sbjType = sbjType;
	}
}

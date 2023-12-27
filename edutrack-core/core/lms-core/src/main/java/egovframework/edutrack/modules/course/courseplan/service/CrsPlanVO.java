package egovframework.edutrack.modules.course.courseplan.service;

import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 년간 과정 계획 VO
 * 
 * <pre>
 * <b>업  무 :</b> 과정 - 년간 과정 계획
 * </pre>
 * 
 * @author MediopiaTech
 */
public class CrsPlanVO extends DefaultVO {

	private static final long serialVersionUID = 5756277100200171827L;
	
	private String  crsCd;
	private String  creYear;
	private Integer creCnt;
	private Integer yearNopCnt;
	private Integer termNopCnt;
	private String  planDesc;
	private List<CrsPlanVO> addCrsPlanList;
	
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getCreYear() {
		return creYear;
	}
	public void setCreYear(String creYear) {
		this.creYear = creYear;
	}
	public Integer getCreCnt() {
		return creCnt;
	}
	public void setCreCnt(Integer creCnt) {
		this.creCnt = creCnt;
	}
	public Integer getYearNopCnt() {
		return yearNopCnt;
	}
	public void setYearNopCnt(Integer yearNopCnt) {
		this.yearNopCnt = yearNopCnt;
	}
	public Integer getTermNopCnt() {
		return termNopCnt;
	}
	public void setTermNopCnt(Integer termNopCnt) {
		this.termNopCnt = termNopCnt;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public List<CrsPlanVO> getAddCrsPlanList() {
		return addCrsPlanList;
	}
	public void setAddCrsPlanList(List<CrsPlanVO> addCrsPlanList) {
		this.addCrsPlanList = addCrsPlanList;
	}
}

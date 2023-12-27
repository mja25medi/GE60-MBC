package egovframework.edutrack.modules.log.educourse.service;

import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogEduTeamStatusVO extends DefaultVO {

	private static final long serialVersionUID = -2334974264230463407L;

	private String  creYear;
	private String  creMonth;
	private String  eduTeamCd;
	private String  eduTeamNm;
	
	private Integer creCnt;
	private Integer enrlCnt;
	private Integer	cpltCnt;
	private Integer	failCnt;
	
	private List<LogEduCourseStatusVO> subList;
	
	public String getCreYear() {
		return creYear;
	}
	public void setCreYear(String creYear) {
		this.creYear = creYear;
	}
	public String getCreMonth() {
		return creMonth;
	}
	public void setCreMonth(String creMonth) {
		this.creMonth = creMonth;
	}
	public String getEduTeamCd() {
		return eduTeamCd;
	}
	public void setEduTeamCd(String eduTeamCd) {
		this.eduTeamCd = eduTeamCd;
	}
	public String getEduTeamNm() {
		return eduTeamNm;
	}
	public void setEduTeamNm(String eduTeamNm) {
		this.eduTeamNm = eduTeamNm;
	}
	public Integer getCreCnt() {
		return creCnt;
	}
	public void setCreCnt(Integer creCnt) {
		this.creCnt = creCnt;
	}
	public Integer getEnrlCnt() {
		return enrlCnt;
	}
	public void setEnrlCnt(Integer enrlCnt) {
		this.enrlCnt = enrlCnt;
	}
	public Integer getCpltCnt() {
		return cpltCnt;
	}
	public void setCpltCnt(Integer cpltCnt) {
		this.cpltCnt = cpltCnt;
	}
	public Integer getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}
	public List<LogEduCourseStatusVO> getSubList() {
		return subList;
	}
	public void setSubList(List<LogEduCourseStatusVO> subList) {
		this.subList = subList;
	}
}

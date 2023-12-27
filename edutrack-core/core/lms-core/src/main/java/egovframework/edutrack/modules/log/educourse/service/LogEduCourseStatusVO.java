package egovframework.edutrack.modules.log.educourse.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogEduCourseStatusVO extends DefaultVO {

	private static final long serialVersionUID = -1730782647586812062L;

	private String  crsCreCd;
	private String  crsNm;
	private String  crsCreNm;
	private String  creYear;
	private String  creMonth;
	private String  creTerm;
	private String  eduTeamCd;
	private String  enrlStartDttm;
	private String  enrlEndDttm;
	private String  oflnStartDttm;
	private String  oflnEndDttm;
	private String  crsOperMthd;
	private String  crsOperType;

	private Integer enrlCnt;
	private Integer	cpltCnt;
	private Integer	failCnt;
	private Integer planCreCnt;
	private Integer planEnrlCnt;
	private Integer creCnt;
	private Integer eduPrice;
	private Double  creRatio;
	private Double  eduRatio;
	private String  orgCd;
	private String  orgNm;
	
	private String  enrlStartDttmStart;
	private String  enrlStartDttmEnd;
	private String  enrlEndDttmStart;
	private String  enrlEndDttmEnd;
	private String  deptCd;
	private String  sbjCd;
	private String  crsCd;
	private String  deptNm;
	private String searchKey;
	private String searchValue;

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
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
	public String getCreTerm() {
		return creTerm;
	}
	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}
	public String getEduTeamCd() {
		return eduTeamCd;
	}
	public void setEduTeamCd(String eduTeamCd) {
		this.eduTeamCd = eduTeamCd;
	}
	public String getEnrlStartDttm() {
		return enrlStartDttm;
	}
	public void setEnrlStartDttm(String enrlStartDttm) {
		this.enrlStartDttm = enrlStartDttm;
	}
	public String getEnrlEndDttm() {
		return enrlEndDttm;
	}
	public void setEnrlEndDttm(String enrlEndDttm) {
		this.enrlEndDttm = enrlEndDttm;
	}
	public String getOflnStartDttm() {
		return oflnStartDttm;
	}
	public void setOflnStartDttm(String oflnStartDttm) {
		this.oflnStartDttm = oflnStartDttm;
	}
	public String getOflnEndDttm() {
		return oflnEndDttm;
	}
	public void setOflnEndDttm(String oflnEndDttm) {
		this.oflnEndDttm = oflnEndDttm;
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
	public Integer getPlanCreCnt() {
		return planCreCnt;
	}
	public void setPlanCreCnt(Integer planCreCnt) {
		this.planCreCnt = planCreCnt;
	}
	public Integer getPlanEnrlCnt() {
		return planEnrlCnt;
	}
	public void setPlanEnrlCnt(Integer planEnrlCnt) {
		this.planEnrlCnt = planEnrlCnt;
	}
	public Integer getCreCnt() {
		return creCnt;
	}
	public void setCreCnt(Integer creCnt) {
		this.creCnt = creCnt;
	}
	public Integer getEduPrice() {
		return eduPrice;
	}
	public void setEduPrice(Integer eduPrice) {
		this.eduPrice = eduPrice;
	}
	public Double getCreRatio() {
		return creRatio;
	}
	public void setCreRatio(Double creRatio) {
		this.creRatio = creRatio;
	}
	public Double getEduRatio() {
		return eduRatio;
	}
	public void setEduRatio(Double eduRatio) {
		this.eduRatio = eduRatio;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getCrsOperMthd() {
		return crsOperMthd;
	}
	public void setCrsOperMthd(String crsOperMthd) {
		this.crsOperMthd = crsOperMthd;
	}
	public String getCrsOperType() {
		return crsOperType;
	}
	public void setCrsOperType(String crsOperType) {
		this.crsOperType = crsOperType;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getEnrlStartDttmStart() {
		return enrlStartDttmStart;
	}
	public void setEnrlStartDttmStart(String enrlStartDttmStart) {
		this.enrlStartDttmStart = enrlStartDttmStart;
	}
	public String getEnrlStartDttmEnd() {
		return enrlStartDttmEnd;
	}
	public void setEnrlStartDttmEnd(String enrlStartDttmEnd) {
		this.enrlStartDttmEnd = enrlStartDttmEnd;
	}
	public String getEnrlEndDttmStart() {
		return enrlEndDttmStart;
	}
	public void setEnrlEndDttmStart(String enrlEndDttmStart) {
		this.enrlEndDttmStart = enrlEndDttmStart;
	}
	public String getEnrlEndDttmEnd() {
		return enrlEndDttmEnd;
	}
	public void setEnrlEndDttmEnd(String enrlEndDttmEnd) {
		this.enrlEndDttmEnd = enrlEndDttmEnd;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}

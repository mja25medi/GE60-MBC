package egovframework.edutrack.modules.log.orgstatus.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogOrgStatusVO extends DefaultVO {

	private static final long serialVersionUID = 5768666601258062534L;

	private String orgCd;
	private String orgNm;
	private String domainNm;
	private String useYn;

	private Integer userUseCnt = 0;
	private Integer userOutCnt = 0;

	private Integer crsUseCnt = 0;
	private Integer crsNouseCnt = 0;

	private Integer creCrsBefCnt = 0;
	private Integer creCrsIngCnt = 0;
	private Integer creCrsEndCnt = 0;

	private Integer stuEnrlCnt = 0;
	private Integer stuCpltCnt = 0;
	private Integer stuFailCnt = 0;

	private Integer totalOrgCnt = 0;
	private Integer totalUserCnt = 0;
	private Integer totalCrsCnt = 0;
	
	private Integer totalDCnt = 0;
	private Integer totalECnt = 0;
	private Integer totalDUserCnt = 0;
	private Integer totalEUserCnt = 0;

	private Integer todayConnectCnt = 0;
	private Integer todayLoginCnt = 0;
	private Integer usingSpace = 0;
	private String  usingSpaceStr;

	private Integer crsCreCnt = 0; //-- 개설 과정 수

	private Integer newAtclCnt = 0;
	
	private Integer totalKnowCnt = 0;

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getDomainNm() {
		return domainNm;
	}

	public void setDomainNm(String domainNm) {
		this.domainNm = domainNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Integer getUserUseCnt() {
		return userUseCnt;
	}

	public void setUserUseCnt(Integer userUseCnt) {
		this.userUseCnt = userUseCnt;
	}

	public Integer getUserOutCnt() {
		return userOutCnt;
	}

	public void setUserOutCnt(Integer userOutCnt) {
		this.userOutCnt = userOutCnt;
	}

	public Integer getCrsUseCnt() {
		return crsUseCnt;
	}

	public void setCrsUseCnt(Integer crsUseCnt) {
		this.crsUseCnt = crsUseCnt;
	}
	
	public Integer getTotalDCnt() {
		return totalDCnt;
	}

	public void setTotalDCnt(Integer totalDCnt) {
		this.totalDCnt = totalDCnt;
	}

	public Integer getTotalECnt() {
		return totalECnt;
	}

	public void setTotalECnt(Integer totalECnt) {
		this.totalECnt = totalECnt;
	}

	public Integer getTotalDUserCnt() {
		return totalDUserCnt;
	}

	public void setTotalDUserCnt(Integer totalDUserCnt) {
		this.totalDUserCnt = totalDUserCnt;
	}

	public Integer getTotalEUserCnt() {
		return totalEUserCnt;
	}

	public void setTotalEUserCnt(Integer totalEUserCnt) {
		this.totalEUserCnt = totalEUserCnt;
	}

	public Integer getCrsNouseCnt() {
		return crsNouseCnt;
	}

	public void setCrsNouseCnt(Integer crsNouseCnt) {
		this.crsNouseCnt = crsNouseCnt;
	}

	public Integer getCreCrsBefCnt() {
		return creCrsBefCnt;
	}

	public void setCreCrsBefCnt(Integer creCrsBefCnt) {
		this.creCrsBefCnt = creCrsBefCnt;
	}

	public Integer getCreCrsIngCnt() {
		return creCrsIngCnt;
	}

	public void setCreCrsIngCnt(Integer creCrsIngCnt) {
		this.creCrsIngCnt = creCrsIngCnt;
	}

	public Integer getCreCrsEndCnt() {
		return creCrsEndCnt;
	}

	public void setCreCrsEndCnt(Integer creCrsEndCnt) {
		this.creCrsEndCnt = creCrsEndCnt;
	}

	public Integer getStuEnrlCnt() {
		return stuEnrlCnt;
	}

	public void setStuEnrlCnt(Integer stuEnrlCnt) {
		this.stuEnrlCnt = stuEnrlCnt;
	}

	public Integer getStuCpltCnt() {
		return stuCpltCnt;
	}

	public void setStuCpltCnt(Integer stuCpltCnt) {
		this.stuCpltCnt = stuCpltCnt;
	}

	public Integer getStuFailCnt() {
		return stuFailCnt;
	}

	public void setStuFailCnt(Integer stuFailCnt) {
		this.stuFailCnt = stuFailCnt;
	}

	public Integer getTotalOrgCnt() {
		return totalOrgCnt;
	}

	public void setTotalOrgCnt(Integer totalOrgCnt) {
		this.totalOrgCnt = totalOrgCnt;
	}

	public Integer getTotalUserCnt() {
		return totalUserCnt;
	}

	public void setTotalUserCnt(Integer totalUserCnt) {
		this.totalUserCnt = totalUserCnt;
	}

	public Integer getTotalCrsCnt() {
		return totalCrsCnt;
	}

	public void setTotalCrsCnt(Integer totalCrsCnt) {
		this.totalCrsCnt = totalCrsCnt;
	}

	public Integer getTodayConnectCnt() {
		return todayConnectCnt;
	}

	public void setTodayConnectCnt(Integer todayConnectCnt) {
		this.todayConnectCnt = todayConnectCnt;
	}

	public Integer getTodayLoginCnt() {
		return todayLoginCnt;
	}

	public void setTodayLoginCnt(Integer todayLoginCnt) {
		this.todayLoginCnt = todayLoginCnt;
	}

	public Integer getUsingSpace() {
		return usingSpace;
	}

	public void setUsingSpace(Integer usingSpace) {
		this.usingSpace = usingSpace;
	}

	public Integer getCrsCreCnt() {
		return crsCreCnt;
	}

	public void setCrsCreCnt(Integer crsCreCnt) {
		this.crsCreCnt = crsCreCnt;
	}

	public String getUsingSpaceStr() {
		return usingSpaceStr;
	}

	public void setUsingSpaceStr(String usingSpaceStr) {
		this.usingSpaceStr = usingSpaceStr;
	}

	public Integer getNewAtclCnt() {
		return newAtclCnt;
	}

	public void setNewAtclCnt(Integer newAtclCnt) {
		this.newAtclCnt = newAtclCnt;
	}

	public Integer getTotalKnowCnt() {
		return totalKnowCnt;
	}

	public void setTotalKnowCnt(Integer totalKnowCnt) {
		this.totalKnowCnt = totalKnowCnt;
	}
	
	
}

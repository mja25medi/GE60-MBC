package egovframework.edutrack.modules.org.fac.service;

import egovframework.edutrack.comm.code.impl.ReserveStatusCode;
import egovframework.edutrack.comm.service.DefaultVO;

public class OrgFacResVO extends DefaultVO {

	private static final long serialVersionUID = -1006534933400590160L;
	
	private String resCd;
	private String facCd;
	
	private String orgCd;
	
	private String userNo;
	private String userNm;
	private String userId;
	
	private String resType;
	private String resDt;
	private String resStm;
	private String resEtm;
	
	private ReserveStatusCode resSts;
	private String resStsNm;
	
	private String eventTitle;
	private String eventDesc;
	
	private int attCnt;
	
	private String memo;
	
	//-------------
	private String facCtgrCd;
	private String facCtgrNm;
	private String facNm;
	private String facArea;
	private int facCap;
	
	private String facUse;
	private String facEqu;
	private String facLoc;
	private String useYn;
	//------------------------
	
	private String workStartDate;
	private String workEndDate;
	
	private int cnt;
	
	private String mobileNo;
	private String email;
	private String homePostNo;
	private String homeAddr1;
	private String homeAddr2;
	
	public boolean isValidRes() {
		if(resSts == null) return false;
		return resSts.getValidInfo();
	}
	
	public String getResCd() {
		return resCd;
	}
	public void setResCd(String resCd) {
		this.resCd = resCd;
	}
	public String getFacCd() {
		return facCd;
	}
	public void setFacCd(String facCd) {
		this.facCd = facCd;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getResDt() {
		return resDt;
	}
	public void setResDt(String resDt) {
		this.resDt = resDt;
	}
	public String getResStm() {
		return resStm;
	}
	public void setResStm(String resStm) {
		this.resStm = resStm;
	}
	public String getResEtm() {
		return resEtm;
	}
	public void setResEtm(String resEtm) {
		this.resEtm = resEtm;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public int getAttCnt() {
		return attCnt;
	}
	public void setAttCnt(int attCnt) {
		this.attCnt = attCnt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}
	public String getWorkEndDate() {
		return workEndDate;
	}
	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getFacNm() {
		return facNm;
	}
	public void setFacNm(String facNm) {
		this.facNm = facNm;
	}
	public String getFacArea() {
		return facArea;
	}
	public void setFacArea(String facArea) {
		this.facArea = facArea;
	}
	public int getFacCap() {
		return facCap;
	}
	public void setFacCap(int facCap) {
		this.facCap = facCap;
	}
	public String getFacUse() {
		return facUse;
	}
	public void setFacUse(String facUse) {
		this.facUse = facUse;
	}
	public String getFacEqu() {
		return facEqu;
	}
	public void setFacEqu(String facEqu) {
		this.facEqu = facEqu;
	}
	public String getFacLoc() {
		return facLoc;
	}
	public void setFacLoc(String facLoc) {
		this.facLoc = facLoc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getResStsNm() {
		return resStsNm;
	}
	public void setResStsNm(String resStsNm) {
		this.resStsNm = resStsNm;
	}
	public ReserveStatusCode getResSts() {
		return resSts;
	}
	public void setResSts(ReserveStatusCode resSts) {
		this.resSts = resSts;
	}
	public void setResSts(String resSts) {
		this.resSts = ReserveStatusCode.valueOf(resSts);
	}
	public String getFacCtgrCd() {
		return facCtgrCd;
	}
	public void setFacCtgrCd(String facCtgrCd) {
		this.facCtgrCd = facCtgrCd;
	}
	public String getFacCtgrNm() {
		return facCtgrNm;
	}
	public void setFacCtgrNm(String facCtgrNm) {
		this.facCtgrNm = facCtgrNm;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public String getHomePostNo() {
		return homePostNo;
	}
	public String getHomeAddr1() {
		return homeAddr1;
	}
	public String getHomeAddr2() {
		return homeAddr2;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setHomePostNo(String homePostNo) {
		this.homePostNo = homePostNo;
	}
	public void setHomeAddr1(String homeAddr1) {
		this.homeAddr1 = homeAddr1;
	}
	public void setHomeAddr2(String homeAddr2) {
		this.homeAddr2 = homeAddr2;
	}
	
}

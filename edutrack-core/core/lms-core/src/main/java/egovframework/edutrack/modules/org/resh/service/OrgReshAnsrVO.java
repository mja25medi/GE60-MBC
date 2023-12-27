package egovframework.edutrack.modules.org.resh.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgReshAnsrVO extends DefaultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4133772303952255263L;
	
	private String  orgCd;
	private Integer reshSn;
	private String  userNo;
	private Integer reshItemSn;
	private String  reshAnsr;
	private String  reshAnsrStr;
	private String  userId;
	private String  userNm;
	private int  reshCnt;
	private String reshAnsrTypeCd;
	private String etcAnsr;
	private String crsTermCd;
	private String reshItemCts;
	private String reshItemCtsK;
	private String reshItemTypeCd;
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public Integer getReshSn() {
		return reshSn;
	}
	public void setReshSn(Integer reshSn) {
		this.reshSn = reshSn;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Integer getReshItemSn() {
		return reshItemSn;
	}
	public void setReshItemSn(Integer reshItemSn) {
		this.reshItemSn = reshItemSn;
	}
	public String getReshAnsr() {
		return reshAnsr;
	}
	public void setReshAnsr(String reshAnsr) {
		this.reshAnsr = reshAnsr;
	}
	public String getReshAnsrStr() {
		return reshAnsrStr;
	}
	public void setReshAnsrStr(String reshAnsrStr) {
		this.reshAnsrStr = reshAnsrStr;
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
	public int getReshCnt() {
		return reshCnt;
	}
	public void setReshCnt(int reshCnt) {
		this.reshCnt = reshCnt;
	}
	public String getReshAnsrTypeCd() {
		return reshAnsrTypeCd;
	}
	public void setReshAnsrTypeCd(String reshAnsrTypeCd) {
		this.reshAnsrTypeCd = reshAnsrTypeCd;
	}
	public String getEtcAnsr() {
		return etcAnsr;
	}
	public void setEtcAnsr(String etcAnsr) {
		this.etcAnsr = etcAnsr;
	}
	public String getCrsTermCd() {
		return crsTermCd;
	}
	public void setCrsTermCd(String crsTermCd) {
		this.crsTermCd = crsTermCd;
	}
	public String getReshItemCts() {
		return reshItemCts;
	}
	public void setReshItemCts(String reshItemCts) {
		this.reshItemCts = reshItemCts;
	}
	public String getReshItemCtsK() {
		return reshItemCtsK;
	}
	public void setReshItemCtsK(String reshItemCtsK) {
		this.reshItemCtsK = reshItemCtsK;
	}
	public String getReshItemTypeCd() {
		return reshItemTypeCd;
	}
	public void setReshItemTypeCd(String reshItemTypeCd) {
		this.reshItemTypeCd = reshItemTypeCd;
	}
	
}

package egovframework.edutrack.modules.course.creCrsResh.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class CrsReshAnsrVO extends DefaultVO{

	private static final long serialVersionUID = 3496117495144861425L;
	private String  crsCreCd;
	private Integer reshSn;
	private String  stdNo;
	private Integer reshItemSn;
	private String  reshAnsr;
	private String  reshAnsrStr;
	private String  userId;
	private String  userNm;
	private int  reshCnt;
	private String reshAnsrTypeCd;

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getReshSn() {
		return reshSn;
	}
	public void setReshSn(Integer reshSn) {
		this.reshSn = reshSn;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
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

}

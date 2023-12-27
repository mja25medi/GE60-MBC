package egovframework.edutrack.modules.course.oflnsbjtch.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OflnSbjTchVO extends DefaultVO {

	private static final long serialVersionUID = 6101819815222802720L;

	private String  crsCreCd;
	private String  sbjCd;
	private String  sbjNm;
	private String  userNo;
	private String  userNm;
	private Integer declsNo;
	private String  tchDivCd;
	private String  tchDivNm;
	private String  lecKindCd;
	private String  lecKindNm;
	private String  tchLvlCd;
	private String  tchLvlNm;
	private String  tchPosCd;
	private String  tchPosNm;
	private Double  lecTm;
	private Integer tchfee;
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getSbjNm() {
		return sbjNm;
	}
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public String getTchDivCd() {
		return tchDivCd;
	}
	public void setTchDivCd(String tchDivCd) {
		this.tchDivCd = tchDivCd;
	}
	public String getTchDivNm() {
		return tchDivNm;
	}
	public void setTchDivNm(String tchDivNm) {
		this.tchDivNm = tchDivNm;
	}
	public String getLecKindCd() {
		return lecKindCd;
	}
	public void setLecKindCd(String lecKindCd) {
		this.lecKindCd = lecKindCd;
	}
	public String getLecKindNm() {
		return lecKindNm;
	}
	public void setLecKindNm(String lecKindNm) {
		this.lecKindNm = lecKindNm;
	}
	public String getTchLvlCd() {
		return tchLvlCd;
	}
	public void setTchLvlCd(String tchLvlCd) {
		this.tchLvlCd = tchLvlCd;
	}
	public String getTchLvlNm() {
		return tchLvlNm;
	}
	public void setTchLvlNm(String tchLvlNm) {
		this.tchLvlNm = tchLvlNm;
	}
	public Double getLecTm() {
		return lecTm;
	}
	public void setLecTm(Double lecTm) {
		this.lecTm = lecTm;
	}
	public Integer getTchfee() {
		return tchfee;
	}
	public void setTchfee(Integer tchfee) {
		this.tchfee = tchfee;
	}
	public String getTchPosCd() {
		return tchPosCd;
	}
	public void setTchPosCd(String tchPosCd) {
		this.tchPosCd = tchPosCd;
	}
	public String getTchPosNm() {
		return tchPosNm;
	}
	public void setTchPosNm(String tchPosNm) {
		this.tchPosNm = tchPosNm;
	}
}

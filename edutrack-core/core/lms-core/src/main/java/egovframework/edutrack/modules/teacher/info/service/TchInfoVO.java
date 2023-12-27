/**
 *
 */
package egovframework.edutrack.modules.teacher.info.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 회원 VO
 */

public class TchInfoVO	extends DefaultVO {

	private static final long			serialVersionUID	= 1864461876411435221L;

	private String  userNo;
	private String  userNm;
	private String  tchCtgrCd;
	private String  tchCtgrNm;
	private String  tchDivCd;
	private String  tchDivNm;
	private String  lecfeePayCritCd;
	private String  lecfeePayCritNm;
	private String  tchSsn;
	private String  bank;
	private String  bankNm;
	private String  acntNo;
	private String  acntNm;
	private String  crer;
	private String  major;
	private String  tchPosCd;
	private String  tchPosNm;
	private String  posng;
	private String  isPop;

	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getTchCtgrCd() {
		return tchCtgrCd;
	}
	public void setTchCtgrCd(String tchCtgrCd) {
		this.tchCtgrCd = tchCtgrCd;
	}
	public String getTchCtgrNm() {
		return tchCtgrNm;
	}
	public void setTchCtgrNm(String tchCtgrNm) {
		this.tchCtgrNm = tchCtgrNm;
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
	public String getLecfeePayCritCd() {
		return lecfeePayCritCd;
	}
	public void setLecfeePayCritCd(String lecfeePayCritCd) {
		this.lecfeePayCritCd = lecfeePayCritCd;
	}
	public String getLecfeePayCritNm() {
		return lecfeePayCritNm;
	}
	public void setLecfeePayCritNm(String lecfeePayCritNm) {
		this.lecfeePayCritNm = lecfeePayCritNm;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAcntNo() {
		return acntNo;
	}
	public void setAcntNo(String acntNo) {
		this.acntNo = acntNo;
	}
	public String getAcntNm() {
		return acntNm;
	}
	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
	}
	public String getCrer() {
		return crer;
	}
	public void setCrer(String crer) {
		this.crer = crer;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getTchSsn() {
		return tchSsn;
	}
	public void setTchSsn(String tchSsn) {
		this.tchSsn = tchSsn;
	}
	public String getBankNm() {
		return bankNm;
	}
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
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
	public String getPosng() {
		return posng;
	}
	public void setPosng(String posng) {
		this.posng = posng;
	}
	public String getIsPop() {
		return isPop;
	}
	public void setIsPop(String isPop) {
		this.isPop = isPop;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
}
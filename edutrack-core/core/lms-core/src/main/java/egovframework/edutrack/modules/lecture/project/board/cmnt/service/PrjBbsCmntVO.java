package egovframework.edutrack.modules.lecture.project.board.cmnt.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class PrjBbsCmntVO extends DefaultVO {

	private static final long serialVersionUID = 6372482939611781685L;

	private String crsCreCd;
	private Integer prjtSn;
	private String 	bbsCd;
	private Integer atclSn;
	private Integer cmntSn;
	private String cmntCts;
	private Integer emoticonNo;
	private String regNo;
	private String regNm;
	private String regDttm;
	private String modNo;
	private String  modDttm;
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getPrjtSn() {
		return prjtSn;
	}
	public void setPrjtSn(Integer prjtSn) {
		this.prjtSn = prjtSn;
	}
	public String getBbsCd() {
		return bbsCd;
	}
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	public Integer getAtclSn() {
		return atclSn;
	}
	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}
	public Integer getCmntSn() {
		return cmntSn;
	}
	public void setCmntSn(Integer cmntSn) {
		this.cmntSn = cmntSn;
	}
	public String getCmntCts() {
		return cmntCts;
	}
	public void setCmntCts(String cmntCts) {
		this.cmntCts = cmntCts;
	}
	public Integer getEmoticonNo() {
		return emoticonNo;
	}
	public void setEmoticonNo(Integer emoticonNo) {
		this.emoticonNo = emoticonNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	
	
	
	
}

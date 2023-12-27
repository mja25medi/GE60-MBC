package egovframework.edutrack.modules.log.usercnsl.service;

import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogUserCnslLogVO extends DefaultVO {

	private static final long serialVersionUID = 8540719837810917612L;
	
	private String sn;
	private String title;
	private String cnslCtgr;
	private String qstnCts;
	private String cnslInfo;
	private String userNo;
	private String qstnDttm;
	private String cnslSts;
	
	private List<LogUserCnslLogVO> ansrList;
	
	private String ansrCts;
	private String ansrRegNo;
	private String ansrRegNm;
	private String ansrDttm;
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCnslCtgr() {
		return cnslCtgr;
	}

	public void setCnslCtgr(String cnslCtgr) {
		this.cnslCtgr = cnslCtgr;
	}

	public String getQstnCts() {
		return qstnCts;
	}

	public void setQstnCts(String qstnCts) {
		this.qstnCts = qstnCts;
	}

	public String getCnslInfo() {
		return cnslInfo;
	}

	public void setCnslInfo(String cnslInfo) {
		this.cnslInfo = cnslInfo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public List<LogUserCnslLogVO> getAnsrList() {
		return ansrList;
	}

	public void setAnsrList(List<LogUserCnslLogVO> ansrList) {
		this.ansrList = ansrList;
	}
	
	public String getAnsrCts() {
		return ansrCts;
	}

	public void setAnsrCts(String ansrCts) {
		this.ansrCts = ansrCts;
	}

	public String getAnsrRegNo() {
		return ansrRegNo;
	}

	public void setAnsrRegNo(String ansrRegNo) {
		this.ansrRegNo = ansrRegNo;
	}

	public String getAnsrRegNm() {
		return ansrRegNm;
	}

	public void setAnsrRegNm(String ansrRegNm) {
		this.ansrRegNm = ansrRegNm;
	}

	public String getQstnDttm() {
		return qstnDttm;
	}

	public void setQstnDttm(String qstnDttm) {
		this.qstnDttm = qstnDttm;
	}

	public String getAnsrDttm() {
		return ansrDttm;
	}

	public void setAnsrDttm(String ansrDttm) {
		this.ansrDttm = ansrDttm;
	}

	public String getCnslSts() {
		return cnslSts;
	}

	public void setCnslSts(String cnslSts) {
		this.cnslSts = cnslSts;
	}
}
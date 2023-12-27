package egovframework.edutrack.modules.student.certprint.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 학습결과 VO
 */
public class CertPrintVO  extends DefaultVO {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5602324615658617909L;
	private int		prnSn;
	private String  prnNo;
	private String	prnDttm;
	private String	prnCnt;
	private String	prnCts;
	private String	chrgDept;
	private String	chrgNm;
	private String	phoneNo;
	
	private String	isuNo;
	private String  usrNm;
	private String	ssn;
	private String	usrDept;
	private String	eduNm;
	private String	eduDuration;
	private String	prnYear;
	private String	prnMonth;
	private String	prnDay;
	
	
	
	public String getIsuNo() {
		return this.isuNo;
	}


	
	public void setIsuNo(String isuNo) {
		this.isuNo = isuNo;
	}


	
	public String getUsrNm() {
		return this.usrNm;
	}


	
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}


	
	public String getSsn() {
		return this.ssn;
	}


	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	
	public String getUsrDept() {
		return this.usrDept;
	}


	
	public void setUsrDept(String usrDept) {
		this.usrDept = usrDept;
	}


	
	public String getEduNm() {
		return this.eduNm;
	}


	
	public void setEduNm(String eduNm) {
		this.eduNm = eduNm;
	}


	
	public String getEduDuration() {
		return this.eduDuration;
	}


	
	public void setEduDuration(String eduDuration) {
		this.eduDuration = eduDuration;
	}


	
	public String getPrnYear() {
		return this.prnYear;
	}


	
	public void setPrnYear(String prnYear) {
		this.prnYear = prnYear;
	}


	
	public String getPrnMonth() {
		return this.prnMonth;
	}


	
	public void setPrnMonth(String prnMonth) {
		this.prnMonth = prnMonth;
	}


	
	public String getPrnDay() {
		return this.prnDay;
	}


	
	public void setPrnDay(String prnDay) {
		this.prnDay = prnDay;
	}


	public String getPrnNo() {
		return this.prnNo;
	}

	
	public void setPrnNo(String prnNo) {
		this.prnNo = prnNo;
	}

	public int getPrnSn() {
		return this.prnSn;
	}
	
	public void setPrnSn(int prnSn) {
		this.prnSn = prnSn;
	}
	
	public String getPrnDttm() {
		return this.prnDttm;
	}
	
	public void setPrnDttm(String prnDttm) {
		this.prnDttm = prnDttm;
	}
	
	public String getPrnCnt() {
		return this.prnCnt;
	}
	
	public void setPrnCnt(String prnCnt) {
		this.prnCnt = prnCnt;
	}
	
	public String getPrnCts() {
		return this.prnCts;
	}
	
	public void setPrnCts(String prnCts) {
		this.prnCts = prnCts;
	}
	
	public String getChrgDept() {
		return this.chrgDept;
	}
	
	public void setChrgDept(String chrgDept) {
		this.chrgDept = chrgDept;
	}
	
	public String getChrgNm() {
		return this.chrgNm;
	}
	
	public void setChrgNm(String chrgNm) {
		this.chrgNm = chrgNm;
	}
	
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
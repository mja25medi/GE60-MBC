package egovframework.edutrack.modules.log.prnlog.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogPrnLogVO extends DefaultVO{

	private static final long serialVersionUID = 2878991087916982562L;
	private Integer prnSn;
	private String  userNo;
	private String  userNm;
	private String  prnDoc;
	private String  param;
	private String  prnDttm;
	
	private String  startDate;
	private String  endDate;
	
	public Integer getPrnSn() {
		return prnSn;
	}
	public void setPrnSn(Integer prnSn) {
		this.prnSn = prnSn;
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
	public String getPrnDoc() {
		return prnDoc;
	}
	public void setPrnDoc(String prnDoc) {
		this.prnDoc = prnDoc;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getPrnDttm() {
		return prnDttm;
	}
	public void setPrnDttm(String prnDttm) {
		this.prnDttm = prnDttm;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

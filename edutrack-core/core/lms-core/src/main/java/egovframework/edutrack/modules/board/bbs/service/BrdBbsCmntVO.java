package egovframework.edutrack.modules.board.bbs.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class BrdBbsCmntVO extends DefaultVO {

	private static final long serialVersionUID = 8041661488327822250L;
	private Integer atclSn;
	private Integer cmntSn;
	private String  cmntCts;
	private Integer emoticonNo;
	private String  regNm;
	
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
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
}

/**
 *
 */
package egovframework.edutrack.modules.course.subject.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 강의실 VO
 */
public class LecRoomVO extends DefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6179763960942865122L;
	
	private String lecRoomSn;
	private String postNo;
	private String addr1;
	private String addr2;
	private String usageCd;
	private String lecRoomDesc;
	private String useYn;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	private String codeNm;//분류
	private String codeCd;//분류
	
	private String autoMakeYn = "Y";
	
	
	
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getLecRoomSn() {
		return lecRoomSn;
	}
	public void setLecRoomSn(String lecRoomSn) {
		this.lecRoomSn = lecRoomSn;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getUsageCd() {
		return usageCd;
	}
	public void setUsageCd(String usageCd) {
		this.usageCd = usageCd;
	}
	public String getLecRoomDesc() {
		return lecRoomDesc;
	}
	public void setLecRoomDesc(String lecRoomDesc) {
		this.lecRoomDesc = lecRoomDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	
	public String getCodeCd() {
		return codeCd;
	}
	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
	
	
	
	
}
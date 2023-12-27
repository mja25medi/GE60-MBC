package egovframework.edutrack.modules.log.message.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.StringUtil;

/**
 * 메시지 관리 VO
 */
public class LogMsgMngVO extends DefaultVO {
	private static final long serialVersionUID = -7159565063784162261L;
	private String  no;
	private String  msgDivNm;
	private String  msgDivCd;
	private String  msgSn;
	private String  title;
	private String  sendNm;
	private String  recvNm;
	private String  sendSts;
	private String  recvCnt;
	private String  regDttm;
	private String  cts;
	
	private String  msgDivType;
	private String  searchType;
	private String  searchKey;
	
	private String recvId;
	private String recvAddr;
	private String userNo;
	
	public void setTempTitleForNull() {
		if(title == null || "".equals(title)) {
			String ctsValue = StringUtil.nvl(cts, "[내용없음]");
			String tempTitle = ctsValue.length() > 20 ? ctsValue.substring(0, 20) +"..." : ctsValue;
			this.title = tempTitle;
		}
	}
	
	public String getNo() {
		return no;
	}
	public String getMsgDivType() {
		return msgDivType;
	}
	public void setMsgDivType(String msgDivType) {
		this.msgDivType = msgDivType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public String getMsgDivCd() {
		return msgDivCd;
	}
	public void setMsgDivCd(String msgDivCd) {
		this.msgDivCd = msgDivCd;
	}
	public String getMsgSn() {
		return msgSn;
	}
	public void setMsgSn(String msgSn) {
		this.msgSn = msgSn;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMsgDivNm() {
		return msgDivNm;
	}
	public void setMsgDivNm(String msgDivNm) {
		this.msgDivNm = msgDivNm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendNm() {
		return sendNm;
	}
	public void setSendNm(String sendNm) {
		this.sendNm = sendNm;
	}
	public String getRecvNm() {
		return recvNm;
	}
	public void setRecvNm(String recvNm) {
		this.recvNm = recvNm;
	}
	public String getSendSts() {
		return sendSts;
	}
	public void setSendSts(String sendSts) {
		this.sendSts = sendSts;
	}
	public String getRecvCnt() {
		return recvCnt;
	}
	public void setRecvCnt(String recvCnt) {
		this.recvCnt = recvCnt;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRecvId() {
		return recvId;
	}
	public void setRecvId(String recvId) {
		this.recvId = recvId;
	}
	public String getRecvAddr() {
		return recvAddr;
	}
	public void setRecvAddr(String recvAddr) {
		this.recvAddr = recvAddr;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
}

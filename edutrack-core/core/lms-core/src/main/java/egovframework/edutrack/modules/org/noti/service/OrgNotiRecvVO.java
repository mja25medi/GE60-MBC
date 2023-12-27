package egovframework.edutrack.modules.org.noti.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;

public class OrgNotiRecvVO extends DefaultVO {

	private static final long serialVersionUID = 1L;
	
	private Long recvId;
	private String orgCd;
	private String notiCtgr;
	private String email;
	
	public LogMsgTransLogVO toMsgTransVO() {
		LogMsgTransLogVO trans = new LogMsgTransLogVO();
		trans.setRecvAddr(email);
		trans.setRecvNm("FAC".equals(notiCtgr) ? "시설관리자" : "장비관리자");
		trans.setRecvYn("Y");
		return trans;
	}
	
	public Long getRecvId() {
		return recvId;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public String getNotiCtgr() {
		return notiCtgr;
	}
	public String getEmail() {
		return email;
	}
	public void setRecvId(Long recvId) {
		this.recvId = recvId;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public void setNotiCtgr(String notiCtgr) {
		this.notiCtgr = notiCtgr;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

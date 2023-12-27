package egovframework.edutrack.comm.event;

import egovframework.edutrack.modules.org.equ.service.OrgEquRentVO;

public class AddEquRentEvent {

	private String menuCd;
	private String msgDivCd;
	private OrgEquRentVO equRentInfo;
	
	public AddEquRentEvent(String menuCd, String msgDivCd, OrgEquRentVO equRentInfo) {
		this.menuCd = menuCd;
		this.msgDivCd = msgDivCd;
		this.equRentInfo = equRentInfo;
	}
	
	public String getMenuCd() {
		return menuCd;
	}
	public String getMsgDivCd() {
		return msgDivCd;
	}
	public OrgEquRentVO getEquRentInfo() {
		return equRentInfo;
	}
	
}

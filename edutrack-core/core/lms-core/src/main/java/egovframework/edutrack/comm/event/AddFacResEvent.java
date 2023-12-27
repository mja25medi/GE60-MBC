package egovframework.edutrack.comm.event;

import egovframework.edutrack.modules.org.fac.service.OrgFacResVO;

public class AddFacResEvent {

	private String menuCd;
	private String msgDivCd;
	private OrgFacResVO facResInfo;
	
	public AddFacResEvent(String menuCd, String msgDivCd, OrgFacResVO facResInfo) {
		this.menuCd = menuCd;
		this.msgDivCd = msgDivCd;
		this.facResInfo = facResInfo;
	}
	
	public String getMenuCd() {
		return menuCd;
	}
	public String getMsgDivCd() {
		return msgDivCd;
	}
	public OrgFacResVO getFacResInfo() {
		return facResInfo;
	}
}

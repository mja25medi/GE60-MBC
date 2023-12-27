package egovframework.edutrack.modules.system.menu.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SysAuthGrpLangVO extends DefaultVO{

	private static final long serialVersionUID = -8832599594151509739L;
	private String  menuType;
	private String  authGrpCd;
	private String  langCd;
	private String  authGrpNm;
	private String  authGrpDesc;
	
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getAuthGrpCd() {
		return authGrpCd;
	}
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getAuthGrpNm() {
		return authGrpNm;
	}
	public void setAuthGrpNm(String authGrpNm) {
		this.authGrpNm = authGrpNm;
	}
	public String getAuthGrpDesc() {
		return authGrpDesc;
	}
	public void setAuthGrpDesc(String authGrpDesc) {
		this.authGrpDesc = authGrpDesc;
	}
}

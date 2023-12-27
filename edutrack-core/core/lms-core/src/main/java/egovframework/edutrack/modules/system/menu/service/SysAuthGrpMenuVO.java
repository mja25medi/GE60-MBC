package egovframework.edutrack.modules.system.menu.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SysAuthGrpMenuVO extends DefaultVO {

	private static final long serialVersionUID = -4103690938762903435L;
	private String	menuType;
	private	String	authGrpCd;
	private	String	menuCd;
	private	String	viewAuth = "Y";
	private String	creAuth = "N";
	private String	modAuth = "N";
	private String	delAuth = "N";
	
	private String	viewAuthArray;
	private String	creAuthArray;
	
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
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getViewAuth() {
		return viewAuth;
	}
	public void setViewAuth(String viewAuth) {
		this.viewAuth = viewAuth;
	}
	public String getCreAuth() {
		return creAuth;
	}
	public void setCreAuth(String creAuth) {
		this.creAuth = creAuth;
	}
	public String getModAuth() {
		return modAuth;
	}
	public void setModAuth(String modAuth) {
		this.modAuth = modAuth;
	}
	public String getDelAuth() {
		return delAuth;
	}
	public void setDelAuth(String delAuth) {
		this.delAuth = delAuth;
	}
	public String getViewAuthArray() {
		return viewAuthArray;
	}
	public void setViewAuthArray(String viewAuthArray) {
		this.viewAuthArray = viewAuthArray;
	}
	public String getCreAuthArray() {
		return creAuthArray;
	}
	public void setCreAuthArray(String creAuthArray) {
		this.creAuthArray = creAuthArray;
	}

}

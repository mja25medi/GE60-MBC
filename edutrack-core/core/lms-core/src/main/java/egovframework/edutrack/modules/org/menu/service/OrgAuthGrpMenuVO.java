package egovframework.edutrack.modules.org.menu.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgAuthGrpMenuVO extends DefaultVO {

	private static final long serialVersionUID = -3308430116976261759L;

	private String  orgCd;
	private String  menuType;
	private String  authGrpCd;
	private String  menuCd;
	private String  viewAuth;
	private String  creAuth;
	private String  modAuth;
	private String  delAuth;

	private String	viewAuthArray;
	private String	creAuthArray;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
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

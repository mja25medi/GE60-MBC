package egovframework.edutrack.modules.org.menu.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgMenuLangVO extends DefaultVO {

	private static final long serialVersionUID = 120008412427443984L;
	private String  orgCd;
	private String  menuCd;
	private String  langCd;
	private String  menuNm;
	private String  menuDesc;
	private String  menuTitle;
	private String  leftMenuTitle;
	private String  topMenuImg;
	private String  leftMenuImg;

	//-- 추가 : 초기화 기능시 필요
	private String  menuType;

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getLangCd() {
		return langCd;
	}

	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getLeftMenuTitle() {
		return leftMenuTitle;
	}

	public void setLeftMenuTitle(String leftMenuTitle) {
		this.leftMenuTitle = leftMenuTitle;
	}

	public String getTopMenuImg() {
		return topMenuImg;
	}

	public void setTopMenuImg(String topMenuImg) {
		this.topMenuImg = topMenuImg;
	}

	public String getLeftMenuImg() {
		return leftMenuImg;
	}

	public void setLeftMenuImg(String leftMenuImg) {
		this.leftMenuImg = leftMenuImg;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
}

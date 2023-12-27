package egovframework.edutrack.modules.system.menu.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SysMenuLangVO extends DefaultVO {

	private static final long serialVersionUID = 4126284758357079859L;
	private String  menuCd;
	private String  langCd;
	private String  menuNm;
	private String  menuDesc;
	private String  menuTitle;
	private String  leftMenuTitle;
	private String  topMenuImg;
	private String  leftMenuImg;
	
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
}

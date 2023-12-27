package egovframework.edutrack.modules.log.menuconn.service;

import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogWwwMenuConnLogVO extends DefaultVO {

	private static final long serialVersionUID = 1003526061347261128L;

	private String	menuCd;
	private String	menuNm;
	private int		menuOdr;
	private int		menuLvl;
	private String	year;
	private String	month;
	private String	day;
	private String	tm;
	private int		hits;

	private String	codeNm;
	private String	startDt;
	private String	endDt;
	private String	viewType;
	private String	dateType;

	private String  orgCd;

	private List<LogWwwMenuConnLogVO> subList = null;


	public String getMenuNm() {
		return this.menuNm;
	}


	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}


	public int getMenuOdr() {
		return this.menuOdr;
	}


	public void setMenuOdr(int menuOdr) {
		this.menuOdr = menuOdr;
	}


	public int getMenuLvl() {
		return this.menuLvl;
	}


	public void setMenuLvl(int menuLvl) {
		this.menuLvl = menuLvl;
	}


	public List<LogWwwMenuConnLogVO> getSubList() {
		return this.subList;
	}


	public void setSubList(List<LogWwwMenuConnLogVO> subList) {
		this.subList = subList;
	}

	public String getMenuCd() {
		return this.menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTm() {
		return this.tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public int getHits() {
		return this.hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getCodeNm() {
		return this.codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getStartDt() {
		return this.startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return this.endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getViewType() {
		return this.viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getDateType() {
		return this.dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}


	public String getOrgCd() {
		return orgCd;
	}


	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
}

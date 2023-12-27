package egovframework.edutrack.modules.etc.relatedsite.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class EtcRelatedSiteVO extends DefaultVO {

	private static final long serialVersionUID = 6301098132154809546L;

	private String  orgCd;
	private String  ctgrCd;     //분류고유번호
	private String  siteSn;     //사이트 고유번호
	private String  title;      //제목
	private Integer siteOdr;
	private String  siteDesc;   //사이트 설명
	private String  siteUrl;	    //url

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getSiteSn() {
		return siteSn;
	}
	public void setSiteSn(String siteSn) {
		this.siteSn = siteSn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSiteOdr() {
		return siteOdr;
	}
	public void setSiteOdr(Integer siteOdr) {
		this.siteOdr = siteOdr;
	}
	public String getSiteDesc() {
		return siteDesc;
	}
	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
}

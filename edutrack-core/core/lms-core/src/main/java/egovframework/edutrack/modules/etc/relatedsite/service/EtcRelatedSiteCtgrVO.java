package egovframework.edutrack.modules.etc.relatedsite.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class EtcRelatedSiteCtgrVO extends DefaultVO {

	private static final long serialVersionUID = 6203362187150928331L;

	private String  orgCd;
	private String	ctgrCd;    //분류고유번호
	private String	title;     //제목
	private String	ctgrDesc;  //분류설명
	private String	ctgrOdr;       //순서
	private String  autoMakeYn;

	private List<EtcRelatedSiteVO> subList;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCtgrDesc() {
		return ctgrDesc;
	}

	public void setCtgrDesc(String ctgrDesc) {
		this.ctgrDesc = ctgrDesc;
	}

	public String getCtgrOdr() {
		return ctgrOdr;
	}

	public void setCtgrOdr(String ctgrOdr) {
		this.ctgrOdr = ctgrOdr;
	}

	public List<EtcRelatedSiteVO> getSubList() {
		return this.subList;
	}

	public void setSubList(List<EtcRelatedSiteVO> subList) {
		if (this.subList == null)
			this.subList = new ArrayList<EtcRelatedSiteVO>();
		this.subList = subList;
	}

	public String getAutoMakeYn() {
		return autoMakeYn;
	}

	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
}

package egovframework.edutrack.modules.org.fac.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgFacCtgrVO extends DefaultVO {
	
	private static final long serialVersionUID = 5372886871994874700L;
	
	private String facCtgrCd;
	private String orgCd;
	private String facCtgrNm;
	private String facCtgrDesc;
	private String useYn;

	public String getFacCtgrCd() {
		return facCtgrCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public String getFacCtgrNm() {
		return facCtgrNm;
	}
	public String getFacCtgrDesc() {
		return facCtgrDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setFacCtgrCd(String facCtgrCd) {
		this.facCtgrCd = facCtgrCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public void setFacCtgrNm(String facCtgrNm) {
		this.facCtgrNm = facCtgrNm;
	}
	public void setFacCtgrDesc(String facCtgrDesc) {
		this.facCtgrDesc = facCtgrDesc;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}

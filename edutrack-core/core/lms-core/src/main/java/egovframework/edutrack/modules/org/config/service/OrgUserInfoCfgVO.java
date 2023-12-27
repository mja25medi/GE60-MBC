package egovframework.edutrack.modules.org.config.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgUserInfoCfgVO extends DefaultVO {

	private static final long serialVersionUID = 5277595024721481029L;
	private String	fieldNm;
	private String	orgCd;
	private String	useYn;
	private String	requiredYn;
	private String	viewYn;
	private String	dfltYn;
	private Integer	viewOdr;
	private String  fieldNms;

	public String getFieldNm() {
		return fieldNm;
	}
	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRequiredYn() {
		return requiredYn;
	}
	public void setRequiredYn(String requiredYn) {
		this.requiredYn = requiredYn;
	}
	public String getViewYn() {
		return viewYn;
	}
	public void setViewYn(String viewYn) {
		this.viewYn = viewYn;
	}
	public String getDfltYn() {
		return dfltYn;
	}
	public void setDfltYn(String dfltYn) {
		this.dfltYn = dfltYn;
	}
	public Integer getViewOdr() {
		return viewOdr;
	}
	public void setViewOdr(Integer viewOdr) {
		this.viewOdr = viewOdr;
	}
	public String getFieldNms() {
		return fieldNms;
	}
	public void setFieldNms(String fieldNms) {
		this.fieldNms = fieldNms;
	}

}

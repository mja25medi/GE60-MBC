package egovframework.edutrack.modules.system.config.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SysCfgCtgrVO extends DefaultVO {

	private static final long serialVersionUID = 981227100986189693L;
	private String cfgCtgrCd;
	private String ctgrNm;  
	private String ctgrDesc;  
	private String useYn;
	
	public String getCfgCtgrCd() {
		return cfgCtgrCd;
	}
	public void setCfgCtgrCd(String cfgCtgrCd) {
		this.cfgCtgrCd = cfgCtgrCd;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCtgrDesc() {
		return ctgrDesc;
	}
	public void setCtgrDesc(String ctgrDesc) {
		this.ctgrDesc = ctgrDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}

package egovframework.edutrack.modules.system.file.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SysFileRepoLangVO extends DefaultVO {

	private static final long serialVersionUID = 6356206911787417215L;
	private String  repoCd;
	private String  langCd;
	private String  repoNm;
	
	public String getRepoCd() {
		return repoCd;
	}
	public void setRepoCd(String repoCd) {
		this.repoCd = repoCd;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getRepoNm() {
		return repoNm;
	}
	public void setRepoNm(String repoNm) {
		this.repoNm = repoNm;
	}
	
}

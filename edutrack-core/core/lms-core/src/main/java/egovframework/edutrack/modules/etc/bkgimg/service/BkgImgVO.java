package egovframework.edutrack.modules.etc.bkgimg.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class BkgImgVO extends DefaultVO {

	private static final long	serialVersionUID	= 4152827997854404227L;
	private Integer  bkgrImgSn;
	private String  bkgrImgSns;
	private String  orgCd;
	private String  bkgrImgNm;
	private Integer bkgrImgOdr;
	private String  useYn;
	private Integer mainImgFileSn;

	private SysFileVO mainImgFile;

	public Integer getBkgrImgSn() {
		return bkgrImgSn;
	}
	public void setBkgrImgSn(Integer bkgrImgSn) {
		this.bkgrImgSn = bkgrImgSn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getBkgrImgNm() {
		return bkgrImgNm;
	}
	public void setBkgrImgNm(String bkgrImgNm) {
		this.bkgrImgNm = bkgrImgNm;
	}
	public Integer getBkgrImgOdr() {
		return bkgrImgOdr;
	}
	public void setBkgrImgOdr(Integer bkgrImgOdr) {
		this.bkgrImgOdr = bkgrImgOdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	//-- 메인 썸네일 파일 관련 처리
	public Integer getMainImgFileSn() {
		if(ValidationUtils.isNull(this.mainImgFileSn))
		return this.getMainImgFile().getFileSn();
		else return this.mainImgFileSn;
	}
	public void setMainImgFileSn(Integer mainImgFileSn) {
		this.getMainImgFile().setFileSn(mainImgFileSn);
	}
	public SysFileVO getMainImgFile() {
		if (this.mainImgFile == null)
			this.mainImgFile = new SysFileVO();
		return this.mainImgFile;
	}
	public void setMainImgFile(SysFileVO mainImgFile) {
		this.mainImgFile = mainImgFile;
	}
	public String getMainImgFileJson() {
		return SysFileVOUtil.getJson(this.getMainImgFile(), false);
	}
	public String getBkgrImgSns() {
		return bkgrImgSns;
	}
	public void setBkgrImgSns(String bkgrImgSns) {
		this.bkgrImgSns = bkgrImgSns;
	}



}

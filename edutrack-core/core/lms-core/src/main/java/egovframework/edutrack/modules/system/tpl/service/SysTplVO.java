package egovframework.edutrack.modules.system.tpl.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class SysTplVO extends DefaultVO {

	private static final long serialVersionUID = 8184174090307725805L;
	private String  tplCd;
	private String  tplNm;
	private String  tplDesc;
	private Integer tplOdr;
	private Integer mainImgFileSn;
	private Integer subImgFileSn;
	private String  useYn;

	private SysFileVO mainImgFile;
	private SysFileVO subImgFile;

	public String getTplCd() {
		return tplCd;
	}
	public void setTplCd(String tplCd) {
		this.tplCd = tplCd;
	}
	public String getTplNm() {
		return tplNm;
	}
	public void setTplNm(String tplNm) {
		this.tplNm = tplNm;
	}
	public String getTplDesc() {
		return tplDesc;
	}
	public void setTplDesc(String tplDesc) {
		this.tplDesc = tplDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getTplOdr() {
		return tplOdr;
	}
	public void setTplOdr(Integer tplOdr) {
		this.tplOdr = tplOdr;
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

	//-- 서브 썸네일 파일 관련 처리
	public Integer getSubImgFileSn() {
		if(ValidationUtils.isNull(this.subImgFileSn))
		return this.getSubImgFile().getFileSn();
		else return this.subImgFileSn;
	}
	public void setSubImgFileSn(Integer subImgFileSn) {
		this.getSubImgFile().setFileSn(subImgFileSn);
	}
	public SysFileVO getSubImgFile() {
		if (this.subImgFile == null)
			this.subImgFile = new SysFileVO();
		return this.subImgFile;
	}
	public void setSubImgFile(SysFileVO subImgFile) {
		this.subImgFile = subImgFile;
	}
	public String getSubImgFileJson() {
		return SysFileVOUtil.getJson(this.getSubImgFile(), false);
	}
}

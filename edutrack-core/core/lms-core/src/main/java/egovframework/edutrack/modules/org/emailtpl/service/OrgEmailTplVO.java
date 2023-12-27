package egovframework.edutrack.modules.org.emailtpl.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgEmailTplVO extends DefaultVO {

	private static final long serialVersionUID = 3493441892244138338L;

	private String  tplCd;
	private String  orgCd;
	private String  tplNm;
	private String  tplDesc;
	private String  tplCts;
	private Integer tplOdr;
	private String  useYn;
	private String  editorYn;
	private String  autoMakeYn;
	private String  tplType;	//EMAIL, SMS 2017.01.23 arothy

	private List<SysFileVO>		attachImages;									// 첨부사진 목록

	
	public String getTplType() {
		return tplType;
	}
	public void setTplType(String tplType) {
		this.tplType = tplType;
	}
	public String getTplCd() {
		return tplCd;
	}
	public void setTplCd(String tplCd) {
		this.tplCd = tplCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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
	public String getTplCts() {
		return tplCts;
	}
	public void setTplCts(String tplCts) {
		this.tplCts = tplCts;
	}
	public Integer getTplOdr() {
		return tplOdr;
	}
	public void setTplOdr(Integer tplOdr) {
		this.tplOdr = tplOdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	//--파일 관련 처리
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}

	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
}

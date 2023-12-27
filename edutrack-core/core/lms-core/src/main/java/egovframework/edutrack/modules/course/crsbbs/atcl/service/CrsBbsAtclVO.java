package egovframework.edutrack.modules.course.crsbbs.atcl.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.modules.course.crsbbs.info.service.CrsBbsInfoVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class CrsBbsAtclVO extends CrsBbsInfoVO {

	private static final long serialVersionUID = -4708756443791837717L;

	private Integer atclSn;
	private Integer parAtclSn;
	private Integer atclLvl;
	private Integer atclOdr;
	private String  title;
	private String  cts;
	private Integer hits;
	private String  editorYn;
	
	private Integer cmntCnt;

	private List<SysFileVO>	attachImages;		// 첨부사진 목록
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록
	
	public Integer getAtclSn() {
		return atclSn;
	}

	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}

	public Integer getParAtclSn() {
		return parAtclSn;
	}

	public void setParAtclSn(Integer parAtclSn) {
		this.parAtclSn = parAtclSn;
	}

	public Integer getAtclLvl() {
		return atclLvl;
	}

	public void setAtclLvl(Integer atclLvl) {
		this.atclLvl = atclLvl;
	}

	public Integer getAtclOdr() {
		return atclOdr;
	}

	public void setAtclOdr(Integer atclOdr) {
		this.atclOdr = atclOdr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCts() {
		return cts;
	}

	public void setCts(String cts) {
		this.cts = cts;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getCmntCnt() {
		return cmntCnt;
	}

	public void setCmntCnt(Integer cmntCnt) {
		this.cmntCnt = cmntCnt;
	}
	
	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	//-- 파일 관련 처리 Method

	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}

	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getAttachFilesSn() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}

	public void setAttachFilesSn(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}

	public String getAttachImagesSn() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}

	public void setAttachImagesSn(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}

	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}
}

package egovframework.edutrack.modules.board.qna.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class BrdQnaAnsrVO extends DefaultVO{

	private static final long	serialVersionUID	= -8730585649647979800L;
	
	private Integer qnaSn;
	private String  qnaCts;
	private String  chrgPrsnNm;
	private String  editorYn;

	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록
	

	public Integer getQnaSn() {
		return qnaSn;
	}
	public void setQnaSn(Integer qnaSn) {
		this.qnaSn = qnaSn;
	}
	public String getQnaCts() {
		return qnaCts;
	}
	public void setQnaCts(String qnaCts) {
		this.qnaCts = qnaCts;
	}
	public String getChrgPrsnNm() {
		return chrgPrsnNm;
	}
	public void setChrgPrsnNm(String chrgPrsnNm) {
		this.chrgPrsnNm = chrgPrsnNm;
	}
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}
	//-- 첨부 이미지 관련 처리
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}
	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}
	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}

	//-- 첨부 파일 관련 처리
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}
	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}
	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}	

}

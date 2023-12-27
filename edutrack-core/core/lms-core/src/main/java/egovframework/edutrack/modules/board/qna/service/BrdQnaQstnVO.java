package egovframework.edutrack.modules.board.qna.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class BrdQnaQstnVO extends DefaultVO{

	private static final long	serialVersionUID	= 4008108253651984097L;
	
	private Integer qnaSn;	//상담글 고유 번호
	private String  orgCd;	//기관 코드
	private String  userNo;	//사용자 번호
	private String  userNm; //사용자 이름
	private String  stsCd;  //초기 사용값 
	private String  qnaTitle;// 상담 제목
	private String  qnaCts; // 상담 내용
	private String  qnaTag; // 태그
	private String  openYn; // 공개 여부
	private String  editorYn; //
	private String  viewMode; // 
	
	private String qnaCtgrCd;
	private String qnaCtgrNm;

	private Integer ansrCnt;
	
	private String lecYn;
	private String crsCreCd;
	private String unitCd;
	private String unitNm;
	
	private String parUnitCd;
	private String parUnitNm;
	
	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록

	public Integer getQnaSn() {
		return qnaSn;
	}
	public void setQnaSn(Integer qnaSn) {
		this.qnaSn = qnaSn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getStsCd() {
		return stsCd;
	}
	public void setStsCd(String stsCd) {
		this.stsCd = stsCd;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaCts() {
		return qnaCts;
	}
	public void setQnaCts(String qnaCts) {
		this.qnaCts = qnaCts;
	}
	public String getQnaTag() {
		return qnaTag;
	}
	public void setQnaTag(String qnaTag) {
		this.qnaTag = qnaTag;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Integer getAnsrCnt() {
		return ansrCnt;
	}
	public void setAnsrCnt(Integer ansrCnt) {
		this.ansrCnt = ansrCnt;
	}
	public String getViewMode() {
		return viewMode;
	}
	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
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
	public String getQnaCtgrCd() {
		return qnaCtgrCd;
	}
	public void setQnaCtgrCd(String qnaCtgrCd) {
		this.qnaCtgrCd = qnaCtgrCd;
	}
	public String getQnaCtgrNm() {
		return qnaCtgrNm;
	}
	public void setQnaCtgrNm(String qnaCtgrNm) {
		this.qnaCtgrNm = qnaCtgrNm;
	}

	public String getLecYn() {
		return lecYn;
	}

	public void setLecYn(String lecYn) {
		this.lecYn = lecYn;
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public String getUnitCd() {
		return unitCd;
	}

	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}

	public String getUnitNm() {
		return unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public String getParUnitNm() {
		return parUnitNm;
	}

	public void setParUnitNm(String parUnitNm) {
		this.parUnitNm = parUnitNm;
	}

	public String getParUnitCd() {
		return parUnitCd;
	}

	public void setParUnitCd(String parUnitCd) {
		this.parUnitCd = parUnitCd;
	}
}

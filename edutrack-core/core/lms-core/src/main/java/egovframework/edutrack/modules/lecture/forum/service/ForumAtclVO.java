package egovframework.edutrack.modules.lecture.forum.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class ForumAtclVO extends DefaultVO {

	private static final long	serialVersionUID	= -2925287275386965761L;
	private String  crsCreCd    ; //  VARCHAR2(10 BYTE)
	private Integer atclSn     ; //  NUMBER(9)
	private Integer forumSn    ; //  NUMBER(9)
	private Integer parAtclSn  ; //  NUMBER(9),
	private Integer atclLvl    ; //  NUMBER(3),
	private Integer atclOdr    ; //  NUMBER(3),
	private String  atclTypeCd ; //  VARCHAR2(10 BYTE),
	private String  title      ; //  NVARCHAR2(200),
	private String  cts        ; //  NCLOB,
	private Integer  hits       ; //  NUMBER(9),
	private String  regNm      ; //  NVARCHAR2(20),
    private Integer	commentCount;	// 댓글 갯수
    private Integer replyCnt=0;
    private String  editorYn;

    private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
    private List<SysFileVO>	attachFiles; // 첨부파일 목록


	public ForumAtclVO(){
		super();
	}

	public ForumAtclVO(Integer atclSn ){
		super();
		this.atclSn =atclSn;
	}

	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public Integer getAtclSn() {
		return atclSn;
	}

	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}

	public Integer getForumSn() {
		return this.forumSn;
	}

	public void setForumSn(Integer forumSn) {
		this.forumSn = forumSn;
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

	public String getAtclTypeCd() {
		return atclTypeCd;
	}

	public void setAtclTypeCd(String atclTypeCd) {
		this.atclTypeCd = atclTypeCd;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCts() {
		return this.cts;
	}

	public void setCts(String cts) {
		this.cts = cts;
	}

	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getRegNm() {
		return this.regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	//-- 첨부 파일 관련 처리
	public List<SysFileVO> getAttachFiles() {
		if(this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getAttachFilesSn() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}
	public void setAttachFilesSn(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
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

}
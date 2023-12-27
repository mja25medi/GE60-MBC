package egovframework.edutrack.modules.lecture.bbs.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class LecBbsAtclVO
		extends DefaultVO {

	private static final long	serialVersionUID	= 5808506797799694111L;
	private String	crsCreCd;									// VARCHAR2(10 BYTE)
	private String	bbsCd;										// NUMBER(9)
	private int		atclLvl;									// NUMBER(3),
	private int		atclOdr;									// NUMBER(3),
	private String	title;										// NVARCHAR2(200),
	private String	cts;										// NCLOB,
	private int		hits;										// NUMBER(9),
	private String	regNm;										// NVARCHAR2(20),
	private String	bbsNm;										// NVARCHAR2(20),
	private String	regMngNm;									// NVARCHAR2(20), 관리자명

	private Integer	atclSn;									// NUMBER(19)
	private Integer	parAtclSn;									// NUMBER(19),
	private String  editorYn;
	private int		cmntCnt;								// 댓글 갯수
	private int		replyCnt;								// 답글 갯수
	private String  lockYn;
	private String  originRegNo;
	private String  parRegNo;

	private List<SysFileVO>		attachImages;								// 첨부사진 목록
	private List<SysFileVO>		attachFiles;								// 첨부파일 목록

	private String recently;
	
	private List<LecBbsCmntVO> cmntList;

	public LecBbsAtclVO() {
		super();
	}

	public LecBbsAtclVO(Integer atclSn) {
		super();
		this.atclSn = atclSn;
	}

	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public String getBbsCd() {
		return this.bbsCd;
	}

	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}

	public int getAtclLvl() {
		return this.atclLvl;
	}

	public void setAtclLvl(int atclLvl) {
		this.atclLvl = atclLvl;
	}

	public int getAtclOdr() {
		return this.atclOdr;
	}

	public void setAtclOdr(int atclOdr) {
		this.atclOdr = atclOdr;
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

	public int getHits() {
		return this.hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public String getRegNm() {
		return this.regNm;
	}

	@Override
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getBbsNm() {
		return this.bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public String getRegMngNm() {
		return this.regMngNm;
	}

	public void setRegMngNm(String regMngNm) {
		this.regMngNm = regMngNm;
	}

	public int getCmntCnt() {
		return this.cmntCnt;
	}

	public void setCmntCnt(int cmntCnt) {
		this.cmntCnt = cmntCnt;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

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

	public Integer getAtclSn() {
		return atclSn;
	}

	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	public Integer getParAtclSn() {
		return parAtclSn;
	}

	public void setParAtclSn(Integer parAtclSn) {
		this.parAtclSn = parAtclSn;
	}

	public String getRecently() {
		return recently;
	}

	public void setRecently(String recently) {
		this.recently = recently;
	}

	public String getLockYn() {
		return lockYn;
	}

	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}

	public String getOriginRegNo() {
		return originRegNo;
	}

	public void setOriginRegNo(String originRegNo) {
		this.originRegNo = originRegNo;
	}

	public String getParRegNo() {
		return parRegNo;
	}

	public void setParRegNo(String parRegNo) {
		this.parRegNo = parRegNo;
	}

	public List<LecBbsCmntVO> getCmntList() {
		return cmntList;
	}

	public void setCmntList(List<LecBbsCmntVO> cmntList) {
		this.cmntList = cmntList;
	}
}

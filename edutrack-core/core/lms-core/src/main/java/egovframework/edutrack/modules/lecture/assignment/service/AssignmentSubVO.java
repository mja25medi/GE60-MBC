package egovframework.edutrack.modules.lecture.assignment.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class AssignmentSubVO
		extends DefaultVO {

	private static final long	serialVersionUID	= -7328506918474481750L;
	private String				crsCreCd;										// VARCHAR2(10 BYTE)

	private int					asmtSn;										// NUMBER(9)
	private int					asmtSubSn;										// NUMBER(9)
	private String				asmtTitle;										// NVARCHAR2(200),
	private String				devLangCd;										
	private String				diffLvlCd;										
	private String				asmtCts;										// NCLOB,
	private String				editorYn;
	private String[]			constCtsList;
	
	private String				stdNo;
	private int					sendCnt;
	private List<AssignmentSubConstVO> asmtSubConstVOList;

	private List<SysFileVO>		attachImages;									// 첨부사진 목록
	private List<SysFileVO>		attachFiles;									// 첨부파일 목록

	private String				sendTitle;
	private String				sendCts;
	private String				gptResult;
	private double				score;
	
	public String[] getConstCtsList() {
		return constCtsList;
	}

	public void setConstCtsList(String[] constCtsList) {
		this.constCtsList = constCtsList;
	}

	public String getDevLangCd() {
		return devLangCd;
	}

	public void setDevLangCd(String devLangCd) {
		this.devLangCd = devLangCd;
	}

	public String getDiffLvlCd() {
		return diffLvlCd;
	}

	public void setDiffLvlCd(String diffLvlCd) {
		this.diffLvlCd = diffLvlCd;
	}

	public AssignmentSubVO() {}

	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public int getAsmtSn() {
		return this.asmtSn;
	}

	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}

	public int getAsmtSubSn() {
		return this.asmtSubSn;
	}

	public void setAsmtSubSn(int asmtSubSn) {
		this.asmtSubSn = asmtSubSn;
	}

	public String getAsmtTitle() {
		return this.asmtTitle;
	}

	public void setAsmtTitle(String asmtTitle) {
		this.asmtTitle = asmtTitle;
	}

	public String getAsmtCts() {
		return this.asmtCts;
	}

	public void setAsmtCts(String asmtCts) {
		this.asmtCts = asmtCts;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	public List<SysFileVO> getAttachImages() {
		if(this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	public List<SysFileVO> getAttachFiles() {
		if(this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}

	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}

	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}

	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}

	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}

	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}

	public List<AssignmentSubConstVO> getAsmtSubConstVOList() {
		return asmtSubConstVOList;
	}

	public void setAsmtSubConstVOList(List<AssignmentSubConstVO> asmtSubConstVOList) {
		this.asmtSubConstVOList = asmtSubConstVOList;
	}

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public int getSendCnt() {
		return sendCnt;
	}

	public void setSendCnt(int sendCnt) {
		this.sendCnt = sendCnt;
	}

	public String getSendTitle() {
		return sendTitle;
	}

	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}

	public String getSendCts() {
		return sendCts;
	}

	public void setSendCts(String sendCts) {
		this.sendCts = sendCts;
	}

	public String getGptResult() {
		return gptResult;
	}

	public void setGptResult(String gptResult) {
		this.gptResult = gptResult;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
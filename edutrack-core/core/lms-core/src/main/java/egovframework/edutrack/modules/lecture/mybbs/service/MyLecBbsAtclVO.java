package egovframework.edutrack.modules.lecture.mybbs.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class MyLecBbsAtclVO extends DefaultVO{

	private static final long serialVersionUID = 8681555064272414153L;
	private String  crsCreCd;
	private String  crsCreNm;
	private String  creYear;
	private String  creTerm;
	private String  bbsCd;
	private String  bbsNm;
	private Integer atclSn;
	private Integer parAtclSn;
	private Integer arclLvl;
	private Integer arclOdr;
	private String  title;
	private String  cts;
	private String  regNo;
	private String  regDttm;
	private Integer hits;
	
	private String  editorYn;
	private int		commentCount;								// 댓글 갯수

	private List<SysFileVO>		attachImages;								// 첨부사진 목록
	private List<SysFileVO>		attachFiles;								// 첨부파일 목록
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
	public String getCreYear() {
		return creYear;
	}
	public void setCreYear(String creYear) {
		this.creYear = creYear;
	}
	public String getCreTerm() {
		return creTerm;
	}
	public void setCreTerm(String creTerm) {
		this.creTerm = creTerm;
	}
	public String getBbsCd() {
		return bbsCd;
	}
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	public String getBbsNm() {
		return bbsNm;
	}
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
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
	public Integer getArclLvl() {
		return arclLvl;
	}
	public void setArclLvl(Integer arclLvl) {
		this.arclLvl = arclLvl;
	}
	public Integer getArclOdr() {
		return arclOdr;
	}
	public void setArclOdr(Integer arclOdr) {
		this.arclOdr = arclOdr;
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
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}	
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
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
}

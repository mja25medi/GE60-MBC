package egovframework.edutrack.modules.lecture.objt.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class LecObjtVO extends DefaultVO{

	private static final long serialVersionUID = 6572285842308798978L;
	
	private int objtSn;
	private String crsCreCd;
	private String title;
	private String cts;
	private int hits;
	private String stsCd;
	private int cmntsCnt;
	
	private List<LecObjtCmntVO> cmntList;
	
	private List<SysFileVO>	attachFiles;	// 첨부파일 목록

	public int getObjtSn() {
		return objtSn;
	}

	public void setObjtSn(int objtSn) {
		this.objtSn = objtSn;
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
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

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getStsCd() {
		return stsCd;
	}

	public void setStsCd(String stsCd) {
		this.stsCd = stsCd;
	}

	public List<LecObjtCmntVO> getCmntList() {
		return cmntList;
	}

	public void setCmntList(List<LecObjtCmntVO> cmntList) {
		this.cmntList = cmntList;
	}

	public int getCmntsCnt() {
		return cmntsCnt;
	}

	public void setCmntsCnt(int cmntsCnt) {
		this.cmntsCnt = cmntsCnt;
	}
	
	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}
	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}
	
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}
}

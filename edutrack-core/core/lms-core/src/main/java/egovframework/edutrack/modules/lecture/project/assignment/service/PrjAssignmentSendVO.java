package egovframework.edutrack.modules.lecture.project.assignment.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class PrjAssignmentSendVO extends DefaultVO {

	private static final long serialVersionUID = -7873093338423336363L;

	//프로젝트 과제 제출
	private String crsCreCd;
	private int prjtSn;
	private int prjtTeamSn;
	private String prjtTeamNm;
	private int	asmtSn;
	private String sendCts;
	
	private List<SysFileVO>	attachFiles;	// 첨부파일 목록
	
	public int getPrjtTeamSn() {
		return prjtTeamSn;
	}
	public void setPrjtTeamSn(int prjtTeamSn) {
		this.prjtTeamSn = prjtTeamSn;
	}
	public String getSendCts() {
		return sendCts;
	}
	public void setSendCts(String sendCts) {
		this.sendCts = sendCts;
	}
	public String getPrjtTeamNm() {
		return prjtTeamNm;
	}
	public void setPrjtTeamNm(String prjtTeamNm) {
		this.prjtTeamNm = prjtTeamNm;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public int getPrjtSn() {
		return prjtSn;
	}
	public void setPrjtSn(int prjtSn) {
		this.prjtSn = prjtSn;
	}
	public int getAsmtSn() {
		return asmtSn;
	}
	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}
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
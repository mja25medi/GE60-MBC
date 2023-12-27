package egovframework.edutrack.modules.lecture.project.info.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.edutrack.modules.lecture.project.result.service.ProjectResultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class ProjectVO extends DefaultVO {
	
	private static final long	serialVersionUID	= -155074520767701661L;
	
	private String crsCreCd;
	private int prjtSn;
	private String prjtTitle;
	private String prjtStartDttm;
	private String prjtEndDttm;
	private String scoreCfrmDttm;
	private String scoreOpenYn = "N";
	private String prjtCts;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	private String teamCnt;
	private String scoreOpenYnNm;
	private String mbrCnt;
	private String userNo;
	private String teamLeaderDiv;
	
	private String prjtTeamSn; //팀정보
	
	private List<SysFileVO>	attachFiles;	// 첨부파일 목록
	
	private ProjectResultVO projectResultVO;
	private PrjAssignmentSendVO prjAssignmentSendVO;
	
	public ProjectVO() {
		setProjectResultVO(new ProjectResultVO());
		setPrjAssignmentSendVO(new PrjAssignmentSendVO());
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
	public String getPrjtTitle() {
		return prjtTitle;
	}
	public void setPrjtTitle(String prjtTitle) {
		this.prjtTitle = prjtTitle;
	}
	public String getPrjtStartDttm() {
		return prjtStartDttm;
	}
	public void setPrjtStartDttm(String prjtStartDttm) {
		this.prjtStartDttm = prjtStartDttm;
	}
	public String getPrjtEndDttm() {
		return prjtEndDttm;
	}
	public void setPrjtEndDttm(String prjtEndDttm) {
		this.prjtEndDttm = prjtEndDttm;
	}
	
	public String getScoreCfrmDttm() {
		return scoreCfrmDttm;
	}
	public void setScoreCfrmDttm(String scoreCfrmDttm) {
		this.scoreCfrmDttm = scoreCfrmDttm;
	}
	
	public String getScoreOpenYn() {
		return scoreOpenYn;
	}
	public void setScoreOpenYn(String scoreOpenYn) {
		this.scoreOpenYn = scoreOpenYn;
	}
	public String getPrjtCts() {
		return prjtCts;
	}
	public void setPrjtCts(String prjtCts) {
		this.prjtCts = prjtCts;
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
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getScoreOpenYnNm() {
		return scoreOpenYnNm;
	}
	public void setScoreOpenYnNm(String scoreOpenYnNm) {
		this.scoreOpenYnNm = scoreOpenYnNm;
	}
	public String getTeamCnt() {
		return teamCnt;
	}
	public void setTeamCnt(String teamCnt) {
		this.teamCnt = teamCnt;
	}
	public String getMbrCnt() {
		return mbrCnt;
	}
	public void setMbrCnt(String mbrCnt) {
		this.mbrCnt = mbrCnt;
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
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
		
	}
	public String getTeamLeaderDiv() {
		return teamLeaderDiv;
	}
	public void setTeamLeaderDiv(String teamLeaderDiv) {
		this.teamLeaderDiv = teamLeaderDiv;
	}
	public String getPrjtTeamSn() {
		return prjtTeamSn;
	}
	public void setPrjtTeamSn(String prjtTeamSn) {
		this.prjtTeamSn = prjtTeamSn;
	}

	public ProjectResultVO getProjectResultVO() {
		return projectResultVO;
	}

	public void setProjectResultVO(ProjectResultVO projectResultVO) {
		this.projectResultVO = projectResultVO;
	}

	public PrjAssignmentSendVO getPrjAssignmentSendVO() {
		return prjAssignmentSendVO;
	}

	public void setPrjAssignmentSendVO(PrjAssignmentSendVO prjAssignmentSendVO) {
		this.prjAssignmentSendVO = prjAssignmentSendVO;
	}
	
}   
    
package egovframework.edutrack.modules.lecture.project.assignment.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class PrjAssignmentVO extends DefaultVO {
	
	private static final long	serialVersionUID	= -155074520767701661L;
	
	private String crsCreCd;
	private int prjtSn;
	private int asmtSn;
	private String asmtTitle;
	private String asmtCts;
	private String asmtStartDttm;
	private String asmtEndDttm;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	//과제 시작 ~ 종료일시
	private String asmtStartDate;
	private String asmtStartHour;
	private String asmtStartMin;
	private String asmtEndDate;
	private String asmtEndHour;
	private String asmtEndMin;
	
	private List<SysFileVO>	attachFiles;	// 첨부파일 목록
	private List<SysFileVO>	attachImages;	// 첨부사진 목록

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
	public String getAsmtTitle() {
		return asmtTitle;
	}
	public void setAsmtTitle(String asmtTitle) {
		this.asmtTitle = asmtTitle;
	}
	public String getAsmtCts() {
		return asmtCts;
	}
	public void setAsmtCts(String asmtCts) {
		this.asmtCts = asmtCts;
	}
	public String getAsmtStartDttm() {
		return asmtStartDttm;
	}
	public void setAsmtStartDttm(String asmtStartDttm) {
		this.asmtStartDttm = asmtStartDttm;
	}
	public String getAsmtEndDttm() {
		return asmtEndDttm;
	}
	public void setAsmtEndDttm(String asmtEndDttm) {
		this.asmtEndDttm = asmtEndDttm;
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
	public String getAsmtStartHour() {
		return asmtStartHour;
	}
	public void setAsmtStartHour(String asmtStartHour) {
		this.asmtStartHour = asmtStartHour;
	}
	public String getAsmtStartMin() {
		return asmtStartMin;
	}
	public void setAsmtStartMin(String asmtStartMin) {
		this.asmtStartMin = asmtStartMin;
	}
	public String getAsmtEndHour() {
		return asmtEndHour;
	}
	public void setAsmtEndHour(String asmtEndHour) {
		this.asmtEndHour = asmtEndHour;
	}
	public String getAsmtEndMin() {
		return asmtEndMin;
	}
	public void setAsmtEndMin(String asmtEndMin) {
		this.asmtEndMin = asmtEndMin;
	}
	public String getAsmtStartDate() {
		return asmtStartDate;
	}
	public void setAsmtStartDate(String asmtStartDate) {
		this.asmtStartDate = asmtStartDate;
	}
	public String getAsmtEndDate() {
		return asmtEndDate;
	}
	public void setAsmtEndDate(String asmtEndDate) {
		this.asmtEndDate = asmtEndDate;
	}

	
	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public List<SysFileVO> getAttachImages() {
		if(this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

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
	
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}
	
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}
	
}   
    
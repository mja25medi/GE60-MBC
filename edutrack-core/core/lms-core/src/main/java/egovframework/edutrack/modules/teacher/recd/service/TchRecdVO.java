/**
 *
 */
package egovframework.edutrack.modules.teacher.recd.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

/**
 * 강의기록 VO
 */

public class TchRecdVO
		extends DefaultVO {

	private static final long	serialVersionUID	= 6120590572747690939L;
	private String				userNo;
	private Integer				lecRecdSn;
	private String				lecNm;
	private String				lecOrgNm;
	private String				lecDt;
	private String				startTm;
	private String				endTm;
	private String				eduTarget;
	private Integer				eduNopCnt;
	private String				lecType;

	private List<SysFileVO>		lecRecdFiles;								// 첨부파일 목록

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Integer getLecRecdSn() {
		return this.lecRecdSn;
	}

	public void setLecRecdSn(Integer lecRecdSn) {
		this.lecRecdSn = lecRecdSn;
	}

	public String getLecNm() {
		return this.lecNm;
	}

	public void setLecNm(String lecNm) {
		this.lecNm = lecNm;
	}

	public String getLecOrgNm() {
		return this.lecOrgNm;
	}

	public void setLecOrgNm(String lecOrgNm) {
		this.lecOrgNm = lecOrgNm;
	}

	public String getLecDt() {
		return this.lecDt;
	}

	public void setLecDt(String lecDt) {
		this.lecDt = lecDt;
	}

	public String getStartTm() {
		return this.startTm;
	}

	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}

	public String getEndTm() {
		return this.endTm;
	}

	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}

	public String getEduTarget() {
		return this.eduTarget;
	}

	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}

	public Integer getEduNopCnt() {
		return this.eduNopCnt;
	}

	public void setEduNopCnt(Integer eduNopCnt) {
		this.eduNopCnt = eduNopCnt;
	}

	public String getLecType() {
		return this.lecType;
	}

	public void setLecType(String lecType) {
		this.lecType = lecType;
	}

	public List<SysFileVO> getLecRecdFiles() {
		if(this.lecRecdFiles == null)
			this.lecRecdFiles = new ArrayList<SysFileVO>();
		return this.lecRecdFiles;
	}
	
	public void setLecRecdFiles(List<SysFileVO> lecRecdFiles) {
		this.lecRecdFiles = lecRecdFiles;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getLecRecdFilesSn() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getLecRecdFiles());
	}

	public void setLecRecdFilesSn(String lecRecdFilesSn) {
		this.setLecRecdFiles(SysFileVOUtil.convertStringToSysFileVOList(lecRecdFilesSn));
	}

	public String getLecRecdFilesJson() {
		return SysFileVOUtil.getJson(this.getLecRecdFiles(), false);
	}
}
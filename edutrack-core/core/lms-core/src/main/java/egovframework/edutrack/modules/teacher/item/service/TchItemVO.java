/**
 *
 */
package egovframework.edutrack.modules.teacher.item.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

/**
 * 강사 강의안 VO
 */

public class TchItemVO
		extends DefaultVO {

	private static final long	serialVersionUID	= 1834284565847041497L;
	private String				userNo;
	private int					lecItemSn;
	private String				crsNm;
	private String				lecTitle;

	private List<SysFileVO>		lecItemFiles;								// 첨부파일 목록

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getLecItemSn() {
		return this.lecItemSn;
	}

	public void setLecItemSn(int lecItemSn) {
		this.lecItemSn = lecItemSn;
	}

	public String getCrsNm() {
		return this.crsNm;
	}

	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}

	public String getLecTitle() {
		return this.lecTitle;
	}

	public void setLecTitle(String lecTitle) {
		this.lecTitle = lecTitle;
	}

	public List<SysFileVO> getLecItemFiles() {
		if(this.lecItemFiles == null)
			this.lecItemFiles = new ArrayList<SysFileVO>();
		return this.lecItemFiles;
	}
	
	public void setLecItemFiles(List<SysFileVO> lecItemFiles) {
		this.lecItemFiles = lecItemFiles;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getLecItemFilesSn() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getLecItemFiles());
	}

	public void setLecItemFilesSn(String lecItemFilesSn) {
		this.setLecItemFiles(SysFileVOUtil.convertStringToSysFileVOList(lecItemFilesSn));
	}

	public String getLecItemFilesJson() {
		return SysFileVOUtil.getJson(this.getLecItemFiles(), false);
	}
}
package egovframework.edutrack.modules.lecture.project.board.atcl.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class PrjBbsAtclVO extends DefaultVO {
	
	private static final long	serialVersionUID	= -155074520767701661L;
	
	private Integer atclSn;
	private String bbsCd;
	private int prjtSn;
	private String crsCreCd;
	private Integer parAtclSn;
	private String atclTitle;
	private String atclCts;
	private Integer atclLvl;
	private Integer atclOdr;
	private Integer hits=0;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	
	private String bbsNm;
	private String regNm;
	private String regId;
	
	private String searchKey;
	private String searchValue;

	private List<SysFileVO>	attachFiles;	// 첨부파일 목록

	public Integer getAtclSn() {
		return atclSn;
	}
	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}
	public String getBbsCd() {
		return bbsCd;
	}
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	public int getPrjtSn() {
		return prjtSn;
	}
	public void setPrjtSn(int prjtSn) {
		this.prjtSn = prjtSn;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getParAtclSn() {
		return parAtclSn;
	}
	public void setParAtclSn(Integer parAtclSn) {
		this.parAtclSn = parAtclSn;
	}
	public String getAtclTitle() {
		return atclTitle;
	}
	public void setAtclTitle(String atclTitle) {
		this.atclTitle = atclTitle;
	}
	public String getAtclCts() {
		return atclCts;
	}
	public void setAtclCts(String atclCts) {
		this.atclCts = atclCts;
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
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
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
	public String getBbsNm() {
		return bbsNm;
	}
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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



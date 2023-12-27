package egovframework.edutrack.modules.org.equ.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgEquVO extends DefaultVO {

	private static final long serialVersionUID = 2705947307571836880L;
	
	private String equCd;
	private String orgCd;
	private String equNm;
	private String equDesc;
	private String useYn;
	private int equOdr;
	
	private Integer thumbFileSn;
	private SysFileVO thumbFile;
	
	private List<SysFileVO> attachFiles;
	
	private int itemCnt;
	private String equCdArray;
	
	public String getEquCd() {
		return equCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public String getEquNm() {
		return equNm;
	}
	public String getEquDesc() {
		return equDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setEquCd(String equCd) {
		this.equCd = equCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public void setEquNm(String equNm) {
		this.equNm = equNm;
	}
	public void setEquDesc(String equDesc) {
		this.equDesc = equDesc;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getEquOdr() {
		return equOdr;
	}
	public void setEquOdr(int equOdr) {
		this.equOdr = equOdr;
	}
	public int getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}
	
	public Integer getThumbFileSn() {
		if(ValidationUtils.isNull(this.thumbFileSn))
		return this.getThumbFile().getFileSn();
		else return this.thumbFileSn;
	}
	public void setThumbFileSn(Integer thumbFileSn) {
		this.getThumbFile().setFileSn(thumbFileSn);
	}
	public SysFileVO getThumbFile() {
		if (this.thumbFile == null)
			this.thumbFile = new SysFileVO();
		return this.thumbFile;
	}
	public void setThumbFile(SysFileVO thumbFile) {
		this.thumbFile = thumbFile;
	}
	public String getThumbFileJson() {
		return SysFileVOUtil.getJson(this.getThumbFile(), false);
	}
	
	//-- 첨부 파일 관련 처리
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
	public String getEquCdArray() {
		return equCdArray;
	}
	public void setEquCdArray(String equCdArray) {
		this.equCdArray = equCdArray;
	}
	
}

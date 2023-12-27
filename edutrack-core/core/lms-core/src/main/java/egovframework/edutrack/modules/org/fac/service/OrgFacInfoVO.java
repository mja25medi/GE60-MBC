package egovframework.edutrack.modules.org.fac.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgFacInfoVO extends DefaultVO {
	
	private static final long serialVersionUID = -1459958159109229052L;
	
	private String facCd;
	private String orgCd;
	private String facCtgrCd;
	private String facCtgrNm;
	private String facNm;
	private int facArea;
	private int facCap;
	
	private String facUse;
	private String facEqu;
	private String facLoc;
	private String facOperTime;
	private String useYn;
	private int facOdr;
	
	private String userNo;
	
	
	//<파일 업로드>
	private Integer thumbFileSn;
	private SysFileVO thumbFile;
	
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록
	
	private int cnt;
	
	private String facCdArray;
	
	public String getFacCd() {
		return facCd;
	}
	public void setFacCd(String facCd) {
		this.facCd = facCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getFacNm() {
		return facNm;
	}
	public void setFacNm(String facNm) {
		this.facNm = facNm;
	}
	public int getFacCap() {
		return facCap;
	}
	public void setFacCap(int facCap) {
		this.facCap = facCap;
	}
	public String getFacUse() {
		return facUse;
	}
	public void setFacUse(String facUse) {
		this.facUse = facUse;
	}
	public String getFacEqu() {
		return facEqu;
	}
	public void setFacEqu(String facEqu) {
		this.facEqu = facEqu;
	}
	public String getFacLoc() {
		return facLoc;
	}
	public void setFacLoc(String facLoc) {
		this.facLoc = facLoc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getFacOdr() {
		return facOdr;
	}
	public void setFacOdr(int facOdr) {
		this.facOdr = facOdr;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	//-- 썸네일 파일 관련 처리
	public Integer getThumbFileSn() {
		if(ValidationUtils.isNull(thumbFileSn))
		return getThumbFile().getFileSn();
		else return thumbFileSn;
	}
	public void setThumbFileSn(Integer thumbFileSn) {
		this.getThumbFile().setFileSn(thumbFileSn);
	}
	public SysFileVO getThumbFile() {
		if (thumbFile == null)
			thumbFile = new SysFileVO();
		return thumbFile;
	}
	public void setThumbFile(SysFileVO thumbFile) {
		this.thumbFile = thumbFile;
	}
	public String getThumbFileJson() {
		return SysFileVOUtil.getJson(this.getThumbFile(), false);
	}
	
	//-- 첨부 파일 관련 처리
	public List<SysFileVO> getAttachFiles() {
		if (attachFiles == null)
			attachFiles = new ArrayList<SysFileVO>();
		return attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(getAttachFiles(), false);
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getFacArea() {
		return facArea;
	}
	public void setFacArea(int facArea) {
		this.facArea = facArea;
	}
	public String getFacCdArray() {
		return facCdArray;
	}
	public void setFacCdArray(String facCdArray) {
		this.facCdArray = facCdArray;
	}
	public String getFacCtgrCd() {
		return facCtgrCd;
	}
	public void setFacCtgrCd(String facCtgrCd) {
		this.facCtgrCd = facCtgrCd;
	}
	public String getFacCtgrNm() {
		return facCtgrNm;
	}
	public void setFacCtgrNm(String facCtgrNm) {
		this.facCtgrNm = facCtgrNm;
	}
	public String getFacOperTime() {
		return facOperTime;
	}
	public void setFacOperTime(String facOperTime) {
		this.facOperTime = facOperTime;
	}
}

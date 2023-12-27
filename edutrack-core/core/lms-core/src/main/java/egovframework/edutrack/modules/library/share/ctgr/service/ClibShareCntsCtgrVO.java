package egovframework.edutrack.modules.library.share.ctgr.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;

public class ClibShareCntsCtgrVO extends DefaultVO{

	private static final long serialVersionUID = 601078952803622207L;
	private String  ctgrCd;
	private String  parCtgrCd;
	private String  orgCd;
	private String  ctgrNm;
	private Integer ctgrLvl;
	private Integer ctgrOdr;
	private String  ctgrPath;
	private String  ctgrPathNm;
	private String  ctgrDesc;
	private String  useYn;

	private Integer subCnt = 0;
	private Integer olcCnt = 0;
	private Integer mediaCnt = 0;
	private Integer cntsCnt = 0;

	private String  firstCd;
	private String  lastCd;
	private String  parCtgrNm;
	private String  divCd;

	private List<ClibShareCntsCtgrVO> subList;

	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getParCtgrCd() {
		return parCtgrCd;
	}
	public void setParCtgrCd(String parCtgrCd) {
		this.parCtgrCd = parCtgrCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public Integer getCtgrLvl() {
		return ctgrLvl;
	}
	public void setCtgrLvl(Integer ctgrLvl) {
		this.ctgrLvl = ctgrLvl;
	}
	public Integer getCtgrOdr() {
		return ctgrOdr;
	}
	public void setCtgrOdr(Integer ctgrOdr) {
		this.ctgrOdr = ctgrOdr;
	}
	public String getCtgrPath() {
		return ctgrPath;
	}
	public void setCtgrPath(String ctgrPath) {
		this.ctgrPath = ctgrPath;
	}
	public String getCtgrPathNm() {
		return ctgrPathNm;
	}
	public void setCtgrPathNm(String ctgrPathNm) {
		this.ctgrPathNm = ctgrPathNm;
	}
	public String getCtgrDesc() {
		return ctgrDesc;
	}
	public void setCtgrDesc(String ctgrDesc) {
		this.ctgrDesc = ctgrDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getSubCnt() {
		return subCnt;
	}
	public void setSubCnt(Integer subCnt) {
		this.subCnt = subCnt;
	}
	public Integer getOlcCnt() {
		return olcCnt;
	}
	public void setOlcCnt(Integer olcCnt) {
		this.olcCnt = olcCnt;
	}
	public Integer getMediaCnt() {
		return mediaCnt;
	}
	public void setMediaCnt(Integer mediaCnt) {
		this.mediaCnt = mediaCnt;
	}
	public Integer getCntsCnt() {
		return cntsCnt;
	}
	public void setCntsCnt(Integer cntsCnt) {
		this.cntsCnt = cntsCnt;
	}
	public String getFirstCd() {
		return firstCd;
	}
	public void setFirstCd(String firstCd) {
		this.firstCd = firstCd;
	}
	public String getLastCd() {
		return lastCd;
	}
	public void setLastCd(String lastCd) {
		this.lastCd = lastCd;
	}
	public String getParCtgrNm() {
		return parCtgrNm;
	}
	public void setParCtgrNm(String parCtgrNm) {
		this.parCtgrNm = parCtgrNm;
	}

	//-- 트리 구조 만들기용
	public List<ClibShareCntsCtgrVO> getSubList() {
		if (this.subList == null)
			this.subList = new ArrayList<ClibShareCntsCtgrVO>();
		return subList;
	}
	public void setSubList(List<ClibShareCntsCtgrVO> subList) {
		this.subList = subList;
	}

	public void addSubVO(ClibShareCntsCtgrVO clibCntsShareCtgrVO) {
		this.getSubList().add(clibCntsShareCtgrVO);
	}
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}
}

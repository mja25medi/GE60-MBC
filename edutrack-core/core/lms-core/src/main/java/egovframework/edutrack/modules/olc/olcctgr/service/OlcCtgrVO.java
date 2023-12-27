package egovframework.edutrack.modules.olc.olcctgr.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OlcCtgrVO extends DefaultVO {

	private static final long serialVersionUID = -6872547695202554469L;
	private String  ctgrCd;
	private String  parCtgrCd;
	private String  parCtgrNm;
	private Integer parCtgrLvl;
	private String  orgCd;
	private String  userNo;
	private String  ctgrNm;
	private Integer ctgrLvl;
	private Integer ctgrOdr;
	private String  ctgrPath;
	private String  ctgrPathNm;
	private String  ctgrDesc;
	private String  useYn;

	private String  firstCd;
	private String  lastCd;

	private Integer subCnt = 0;
	private Integer olcCnt = 0;

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
	public String getParCtgrNm() {
		return parCtgrNm;
	}
	public void setParCtgrNm(String parCtgrNm) {
		this.parCtgrNm = parCtgrNm;
	}
	public Integer getParCtgrLvl() {
		return parCtgrLvl;
	}
	public void setParCtgrLvl(Integer parCtgrLvl) {
		this.parCtgrLvl = parCtgrLvl;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}

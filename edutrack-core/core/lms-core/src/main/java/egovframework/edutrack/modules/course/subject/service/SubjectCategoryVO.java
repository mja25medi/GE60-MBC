/**
 *
 */
package egovframework.edutrack.modules.course.subject.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 과목 분류 VO
 */
public class SubjectCategoryVO extends DefaultVO {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6428909068114819640L;
	private String 	sbjCtgrCd;
	private String 	sbjCtgrTypeCd;
	private String	sbjCtgrTypeNm;
	private String 	sbjCtgrNm;
	private String	parSbjCtgrCd;
	private String	parSbjCtgrNm = "ROOT";
	private	Integer	ctgrLvl;
	private Integer parCtgrLvl;
	private Integer	ctgrOdr;
	private String	useYn = "Y";
	private String	useYnNm;
	private String	sbjCtgrPath;
	private String	sbjCtgrPathNm;
	private Integer	subCnt;
	private Integer sbjCnt;
	private String  orgCd;
	

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public Integer getSbjCnt() {
		return this.sbjCnt;
	}
	public void setSbjCnt(Integer sbjCnt) {
		this.sbjCnt = sbjCnt;
	}
	public Integer getSubCnt() {
		return this.subCnt;
	}
	public void setSubCnt(Integer subCnt) {
		this.subCnt = subCnt;
	}
	public String getSbjCtgrCd() {
		return this.sbjCtgrCd;
	}
	public void setSbjCtgrCd(String sbjCtgrCd) {
		this.sbjCtgrCd = sbjCtgrCd;
	}
	public String getSbjCtgrTypeCd() {
		return this.sbjCtgrTypeCd;
	}
	public void setSbjCtgrTypeCd(String sbjCtgrTypeCd) {
		this.sbjCtgrTypeCd = sbjCtgrTypeCd;
	}
	public String getSbjCtgrTypeNm() {
		return this.sbjCtgrTypeNm;
	}
	public void setSbjCtgrTypeNm(String sbjCtgrTypeNm) {
		this.sbjCtgrTypeNm = sbjCtgrTypeNm;
	}
	public String getSbjCtgrNm() {
		return this.sbjCtgrNm;
	}
	public void setSbjCtgrNm(String sbjCtgrNm) {
		this.sbjCtgrNm = sbjCtgrNm;
	}
	public String getParSbjCtgrCd() {
		return this.parSbjCtgrCd;
	}
	public void setParSbjCtgrCd(String parSbjCtgrCd) {
		this.parSbjCtgrCd = parSbjCtgrCd;
	}
	public String getParSbjCtgrNm() {
		return this.parSbjCtgrNm;
	}
	public void setParSbjCtgrNm(String parSbjCtgrNm) {
		this.parSbjCtgrNm = parSbjCtgrNm;
	}
	public Integer getCtgrLvl() {
		return this.ctgrLvl;
	}
	public void setCtgrLvl(Integer ctgrLvl) {
		this.ctgrLvl = ctgrLvl;
	}
	public Integer getParCtgrLvl() {
		return this.parCtgrLvl;
	}
	public void setParCtgrLvl(Integer parCtgrLvl) {
		this.parCtgrLvl = parCtgrLvl;
	}
	public Integer getCtgrOdr() {
		return this.ctgrOdr;
	}
	public void setCtgrOdr(Integer ctgrOdr) {
		this.ctgrOdr = ctgrOdr;
	}
	public String getUseYn() {
		return this.useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getSbjCtgrPath() {
		return sbjCtgrPath;
	}
	public void setSbjCtgrPath(String sbjCtgrPath) {
		this.sbjCtgrPath = sbjCtgrPath;
	}
	public String getSbjCtgrPathNm() {
		return sbjCtgrPathNm;
	}
	public void setSbjCtgrPathNm(String sbjCtgrPathNm) {
		this.sbjCtgrPathNm = sbjCtgrPathNm;
	}
}
package egovframework.edutrack.modules.opencourse.course.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OpenCrsVO extends DefaultVO{

	private static final long serialVersionUID = -6098480976526620366L;
	private String  crsCd;
	private String  ctgrCd;
	private String  ctgrNm;
	private String  crsNm;
	private String  crsDesc;
	private String  crsImg;
	private String  crsImgDesc;
	private String  userLvlCd;
	private String  useYn;
	private Integer crsOdr;
	private String  exposureYn;
	private String  orgCd;
	private String  sbjCnt;
	private String  sampleCntsPath;
	private String  sbjCd;

	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getCrsDesc() {
		return crsDesc;
	}
	public void setCrsDesc(String crsDesc) {
		this.crsDesc = crsDesc;
	}
	public String getCrsImg() {
		return crsImg;
	}
	public void setCrsImg(String crsImg) {
		this.crsImg = crsImg;
	}
	public String getCrsImgDesc() {
		return crsImgDesc;
	}
	public void setCrsImgDesc(String crsImgDesc) {
		this.crsImgDesc = crsImgDesc;
	}
	public String getUserLvlCd() {
		return userLvlCd;
	}
	public void setUserLvlCd(String userLvlCd) {
		this.userLvlCd = userLvlCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getCrsOdr() {
		return crsOdr;
	}
	public void setCrsOdr(Integer crsOdr) {
		this.crsOdr = crsOdr;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getSbjCnt() {
		return sbjCnt;
	}
	public void setSbjCnt(String sbjCnt) {
		this.sbjCnt = sbjCnt;
	}
	public String getExposureYn() {
		return exposureYn;
	}
	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}
	public String getSampleCntsPath() {
		return sampleCntsPath;
	}
	public void setSampleCntsPath(String sampleCntsPath) {
		this.sampleCntsPath = sampleCntsPath;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
}

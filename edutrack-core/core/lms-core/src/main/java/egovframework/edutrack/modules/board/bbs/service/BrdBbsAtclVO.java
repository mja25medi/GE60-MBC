package egovframework.edutrack.modules.board.bbs.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class BrdBbsAtclVO extends DefaultVO {

	private static final long serialVersionUID = 5248390587068287399L;
	private Integer atclSn;
	private String  orgCd;
	private String  bbsCd;
	private String	bbsNm;
	private Integer parAtclSn;
	private String  headCd;
	private String  headNm;
	private Integer thumbFileSn;
	private String  atclTitle;
	private String  atclCts;
	private String  atclCtsStr;
	private String  atclTag;
	private Integer atclLvl;
	private Integer atclOdr;
	private String  noticeYn;
	private String  lockYn;
	private Integer hits;
	private String  regNm;
	private String  editorYn;
	private String  listViewMode;
	private String  mobileUrl;
	private String  originRegNo;
	private String  parRegNo;
	private String  bbsTypeCd;

	private Integer topCnt;
	private Integer cmntCnt;
	private Integer childCnt;

	private SysFileVO thumbFile;

	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록
	private List<SysFileVO>	attachPhotos;		// 첨부겔러리 목록 : 첨부로 등록된 이미지

	private String recently;
	
	private String nextAtclTitle;	//다음글 제목	2017-01-06 이전글 다음글 추가
	private String nextAtclSn;		//다음글 번호
	private String preAtclTitle;	//이전글 제목
	private String preAtclSn;		//이전글 번호
	private String password;
	private String passwordCheck;
	private String encryptData;
	private String ctgrCd;
	
	private String serviceRegNm;
	private String servicePw;
	private String serviceEmail;
	private String serviceTel;
	private String hopeCurriculum;
	private String companyName;
	private String serviceEnableDate;
	private String serviceEnableTime;
	private String starScore;
	private String crsCreCd;
	private String crsCreNm;
	
	public String getNextAtclTitle() {
		return nextAtclTitle;
	}
	public void setNextAtclTitle(String nextAtclTitle) {
		this.nextAtclTitle = nextAtclTitle;
	}
	public String getNextAtclSn() {
		return nextAtclSn;
	}
	public void setNextAtclSn(String nextAtclSn) {
		this.nextAtclSn = nextAtclSn;
	}
	public String getPreAtclTitle() {
		return preAtclTitle;
	}
	public void setPreAtclTitle(String preAtclTitle) {
		this.preAtclTitle = preAtclTitle;
	}
	public String getPreAtclSn() {
		return preAtclSn;
	}
	public void setPreAtclSn(String preAtclSn) {
		this.preAtclSn = preAtclSn;
	}
	public Integer getAtclSn() {
		return atclSn;
	}
	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getBbsCd() {
		return bbsCd;
	}
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	public String getBbsNm() {
		return bbsNm;
	}
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
	public Integer getParAtclSn() {
		return parAtclSn;
	}
	public void setParAtclSn(Integer parAtclSn) {
		this.parAtclSn = parAtclSn;
	}
	public String getHeadCd() {
		return headCd;
	}
	public void setHeadCd(String headCd) {
		this.headCd = headCd;
	}
	public String getHeadNm() {
		return headNm;
	}
	public void setHeadNm(String headNm) {
		this.headNm = headNm;
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
	public String getAtclCtsStr() {
		return atclCtsStr;
	}
	public void setAtclCtsStr(String atclCtsStr) {
		this.atclCtsStr = atclCtsStr;
	}
	public String getAtclTag() {
		return atclTag;
	}
	public void setAtclTag(String atclTag) {
		this.atclTag = atclTag;
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
	public String getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public String getLockYn() {
		return lockYn;
	}
	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}
	public String getListViewMode() {
		return listViewMode;
	}
	public void setListViewMode(String listViewMode) {
		this.listViewMode = listViewMode;
	}
	public String getMobileUrl() {
		return mobileUrl;
	}
	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}
	public Integer getTopCnt() {
		return topCnt;
	}
	public void setTopCnt(Integer topCnt) {
		this.topCnt = topCnt;
	}
	public String getRecently() {
		return recently;
	}
	public void setRecently(String recently) {
		this.recently = recently;
	}
	public String getOriginRegNo() {
		return originRegNo;
	}
	public void setOriginRegNo(String originRegNo) {
		this.originRegNo = originRegNo;
	}
	public String getParRegNo() {
		return parRegNo;
	}
	public void setParRegNo(String parRegNo) {
		this.parRegNo = parRegNo;
	}
	public String getBbsTypeCd() {
		return bbsTypeCd;
	}
	public void setBbsTypeCd(String bbsTypeCd) {
		this.bbsTypeCd = bbsTypeCd;
	}
	
	//-- 썸네일 파일 관련 처리
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
	public Integer getCmntCnt() {
		return cmntCnt;
	}
	public void setCmntCnt(Integer cmntCnt) {
		this.cmntCnt = cmntCnt;
	}
	public Integer getChildCnt() {
		return childCnt;
	}
	public void setChildCnt(Integer childCnt) {
		this.childCnt = childCnt;
	}

	//-- 첨부 이미지 관련 처리
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}
	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}
	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}

	//-- 첨부 파일 관련 처리
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

	//-- 첨부 사진 관련 처리
	public String getAttachPhotoSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachPhotos());
	}
	public void setAttachPhotoSns(String attachPhotoSns) {
		this.setAttachPhotos(SysFileVOUtil.convertStringToSysFileVOList(attachPhotoSns));
	}
	public List<SysFileVO> getAttachPhotos() {
		if (this.attachPhotos == null)
			this.attachPhotos = new ArrayList<SysFileVO>();
		return this.attachPhotos;
	}
	public void setAttachPhotos(List<SysFileVO> attachPhotos) {
		this.attachPhotos = attachPhotos;
	}
	public String getAttachPhotosJson() {
		return SysFileVOUtil.getJson(this.getAttachPhotos(), false);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptData() {
		return encryptData;
	}
	public void setEncryptData(String encryptData) {
		this.encryptData = encryptData;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getServiceRegNm() {
		return serviceRegNm;
	}
	public void setServiceRegNm(String serviceRegNm) {
		this.serviceRegNm = serviceRegNm;
	}
	public String getHopeCurriculum() {
		return hopeCurriculum;
	}
	public void setHopeCurriculum(String hopeCurriculum) {
		this.hopeCurriculum = hopeCurriculum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getServiceEnableDate() {
		return serviceEnableDate;
	}
	public void setServiceEnableDate(String serviceEnableDate) {
		this.serviceEnableDate = serviceEnableDate;
	}
	public String getServiceEnableTime() {
		return serviceEnableTime;
	}
	public void setServiceEnableTime(String serviceEnableTime) {
		this.serviceEnableTime = serviceEnableTime;
	}
	public String getServicePw() {
		return servicePw;
	}
	public void setServicePw(String servicePw) {
		this.servicePw = servicePw;
	}
	public String getServiceEmail() {
		return serviceEmail;
	}
	public void setServiceEmail(String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}
	public String getServiceTel() {
		return serviceTel;
	}
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	public String getStarScore() {
		return starScore;
	}
	public void setStarScore(String starScore) {
		this.starScore = starScore;
	}
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}

}

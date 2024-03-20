package egovframework.edutrack.modules.library.cnts.media.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class ClibMediaCntsVO extends DefaultVO{

	private static final long serialVersionUID = 4420397458442550041L;

	private String  cntsCd;
	private String  orgCd;
	private String  userNo;
	private String  ctgrCd;
	private Integer thumbFileSn;
	private String  cntsNm;
	private String  cntsTag;
	private String  cntsCts;
	private Integer cntsOdr;
	private String  useYn;
	private Integer hits;
	private String  playerDiv;
	private String  fileNm;
	private String  filePath;
	private String  uldStsCd;
	private String  uldFileKey;
	private String  mediaCntsKey;
	private String  chanlKey;
	private String  chanlNm;
	private String  profileKey;

	private String  ctgrNm;
	private String  cclCd;
	private String  shareCntsCd;
	private String  cntsType;
	private String  cntsFilePath;
	
	private Integer sharedCnt = 0;

	//콘텐츠 공유를 위한 코드
	private String  shareDivCd;
	private String  shareStsCd;

	private SysFileVO thumbFile;
	
	private String crsCreCd;
	private String crsCreNm;

	public String getCntsCd() {
		return cntsCd;
	}
	public void setCntsCd(String cntsCd) {
		this.cntsCd = cntsCd;
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
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getCntsNm() {
		return cntsNm;
	}
	public void setCntsNm(String cntsNm) {
		this.cntsNm = cntsNm;
	}
	public String getCntsTag() {
		return cntsTag;
	}
	public void setCntsTag(String cntsTag) {
		this.cntsTag = cntsTag;
	}
	public String getCntsCts() {
		return cntsCts;
	}
	public void setCntsCts(String cntsCts) {
		this.cntsCts = cntsCts;
	}
	public Integer getCntsOdr() {
		return cntsOdr;
	}
	public void setCntsOdr(Integer cntsOdr) {
		this.cntsOdr = cntsOdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public String getPlayerDiv() {
		return playerDiv;
	}
	public void setPlayerDiv(String playerDiv) {
		this.playerDiv = playerDiv;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getUldStsCd() {
		return uldStsCd;
	}
	public void setUldStsCd(String uldStsCd) {
		this.uldStsCd = uldStsCd;
	}
	public String getUldFileKey() {
		return uldFileKey;
	}
	public void setUldFileKey(String uldFileKey) {
		this.uldFileKey = uldFileKey;
	}
	public String getMediaCntsKey() {
		return mediaCntsKey;
	}
	public void setMediaCntsKey(String mediaCntsKey) {
		this.mediaCntsKey = mediaCntsKey;
	}
	public String getChanlKey() {
		return chanlKey;
	}
	public void setChanlKey(String chanlKey) {
		this.chanlKey = chanlKey;
	}
	public String getChanlNm() {
		return chanlNm;
	}
	public void setChanlNm(String chanlNm) {
		this.chanlNm = chanlNm;
	}
	public String getProfileKey() {
		return profileKey;
	}
	public void setProfileKey(String profileKey) {
		this.profileKey = profileKey;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCclCd() {
		return cclCd;
	}
	public void setCclCd(String cclCd) {
		this.cclCd = cclCd;
	}
	public String getShareCntsCd() {
		return shareCntsCd;
	}
	public void setShareCntsCd(String shareCntsCd) {
		this.shareCntsCd = shareCntsCd;
	}
	public Integer getSharedCnt() {
		return sharedCnt;
	}
	public void setSharedCnt(Integer sharedCnt) {
		this.sharedCnt = sharedCnt;
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
	public String getShareDivCd() {
		return shareDivCd;
	}
	public void setShareDivCd(String shareDivCd) {
		this.shareDivCd = shareDivCd;
	}
	public String getShareStsCd() {
		return shareStsCd;
	}
	public void setShareStsCd(String shareStsCd) {
		this.shareStsCd = shareStsCd;
	}
	public String getCntsType() {
		return cntsType;
	}
	public void setCntsType(String cntsType) {
		this.cntsType = cntsType;
	}
	public String getCntsFilePath() {
		return cntsFilePath;
	}
	public void setCntsFilePath(String cntsFilePath) {
		this.cntsFilePath = cntsFilePath;
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

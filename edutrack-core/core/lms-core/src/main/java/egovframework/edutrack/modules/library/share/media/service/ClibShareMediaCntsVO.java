package egovframework.edutrack.modules.library.share.media.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class ClibShareMediaCntsVO extends DefaultVO {

	private static final long serialVersionUID = -5718913354407286954L;
	private String  cntsCd;
	private String  ctgrCd;
	private String  orgCd;
	private String  originCntsCd;
	private String  originUserNo;
	private String  cclCd;
	private Integer thumbFileSn;
	private String  cntsNm;
	private String  cntsTag;
	private Integer cntsOdr;
	private String  useYn;
	private String  shareStsCd;
	private Integer hits;
	private String  playerDiv;
	private String  fileNm;
	private String  filePath;
	private String  uldFileKey;
	private String  mediaCntsKey;
	private String  chanlKey;
	private String  chanlNm;
	private String  profileKey;

	private String  ctgrNm;
	private Integer useCnt;

	private SysFileVO thumbFile;

	//콘텐츠/지식 공유를 위한 코드
	private String  shareDivCd;
	private String  shareReqStsCd;
	private Integer reqSn;
	
	private String cntsTypeCd;	//콘텐츠 유형코드

	public String getCntsCd() {
		return cntsCd;
	}
	public void setCntsCd(String cntsCd) {
		this.cntsCd = cntsCd;
	}
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOriginCntsCd() {
		return originCntsCd;
	}
	public void setOriginCntsCd(String originCntsCd) {
		this.originCntsCd = originCntsCd;
	}
	public String getOriginUserNo() {
		return originUserNo;
	}
	public void setOriginUserNo(String originUserNo) {
		this.originUserNo = originUserNo;
	}
	public String getCclCd() {
		return cclCd;
	}
	public void setCclCd(String cclCd) {
		this.cclCd = cclCd;
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
	public String getShareStsCd() {
		return shareStsCd;
	}
	public void setShareStsCd(String shareStsCd) {
		this.shareStsCd = shareStsCd;
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
	public Integer getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(Integer useCnt) {
		this.useCnt = useCnt;
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
	public String getShareReqStsCd() {
		return shareReqStsCd;
	}
	public void setShareReqStsCd(String shareReqStsCd) {
		this.shareReqStsCd = shareReqStsCd;
	}
	public Integer getReqSn() {
		return reqSn;
	}
	public void setReqSn(Integer reqSn) {
		this.reqSn = reqSn;
	}
	public String getCntsTypeCd() {
		return cntsTypeCd;
	}
	public void setCntsTypeCd(String cntsTypeCd) {
		this.cntsTypeCd = cntsTypeCd;
	}
	
}


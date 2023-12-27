package egovframework.edutrack.modules.library.share.olc.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class ClibShareOlcCntsVO extends DefaultVO{

	private static final long serialVersionUID = -8518045826123888596L;

	private String  cntsCd;
	private String  ctgrCd;
	private String  orgCd;
	private String  originCntsCd;
	private String  originUserNo;
	private String  cclCd;
	private String  cntsNm;
	private String  cntsTag;
	private Integer  cntsOdr;
	private String  useYn;
	private String  shareStsCd;
	private Integer hits;
	private String  cntsTitleColor;
	private String  cntsTableBkgrColor;
	private String  cntsTableFontColor;
	private String  cntsTablePosCd;
	private String  pageNoPosCd;
	private String  bkgrImgTypeCd;
	private String  bkgrImgUldDiv;
	private Integer thumbFileSn;
	private Integer logoImgSn;
	private Integer bkgrImgSn;
	private String  userNo;
	private String  ctgrNm;

	private SysFileVO	thumbFile;
	private SysFileVO	logoImg;
	private SysFileVO	bkgrImg;

	//콘텐츠/지식 공유를 위한 코드
	private String  shareDivCd;
	private String  shareReqStsCd;
	private Integer reqSn;

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
		shareStsCd = shareStsCd;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public String getCntsTitleColor() {
		return cntsTitleColor;
	}
	public void setCntsTitleColor(String cntsTitleColor) {
		this.cntsTitleColor = cntsTitleColor;
	}
	public String getCntsTableBkgrColor() {
		return cntsTableBkgrColor;
	}
	public void setCntsTableBkgrColor(String cntsTableBkgrColor) {
		this.cntsTableBkgrColor = cntsTableBkgrColor;
	}
	public String getCntsTableFontColor() {
		return cntsTableFontColor;
	}
	public void setCntsTableFontColor(String cntsTableFontColor) {
		this.cntsTableFontColor = cntsTableFontColor;
	}
	public String getCntsTablePosCd() {
		return cntsTablePosCd;
	}
	public void setCntsTablePosCd(String cntsTablePosCd) {
		this.cntsTablePosCd = cntsTablePosCd;
	}
	public String getPageNoPosCd() {
		return pageNoPosCd;
	}
	public void setPageNoPosCd(String pageNoPosCd) {
		this.pageNoPosCd = pageNoPosCd;
	}
	public String getBkgrImgTypeCd() {
		return bkgrImgTypeCd;
	}
	public void setBkgrImgTypeCd(String bkgrImgTypeCd) {
		this.bkgrImgTypeCd = bkgrImgTypeCd;
	}
	public String getBkgrImgUldDiv() {
		return bkgrImgUldDiv;
	}
	public void setBkgrImgUldDiv(String bkgrImgUldDiv) {
		this.bkgrImgUldDiv = bkgrImgUldDiv;
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

	//-- Logo 파일 관련 처리
	public Integer getLogoImgSn() {
		if(ValidationUtils.isNull(this.logoImgSn))
		return this.getLogoImg().getFileSn();
		else return this.logoImgSn;
	}
	public void setLogoImgSn(Integer logoImgSn) {
		this.getLogoImg().setFileSn(logoImgSn);
	}
	public SysFileVO getLogoImg() {
		if (this.logoImg == null)
			this.logoImg = new SysFileVO();
		return this.logoImg;
	}
	public void setLogoImg(SysFileVO logoImg) {
		this.logoImg = logoImg;
	}
	public String getLogoImgJson() {
		return SysFileVOUtil.getJson(this.getLogoImg(), false);
	}

	//-- bkgr 파일 관련 처리
	public Integer getBkgrImgSn() {
		if(ValidationUtils.isNull(this.bkgrImgSn))
		return this.getBkgrImg().getFileSn();
		else return this.bkgrImgSn;
	}
	public void setBkgrImgSn(Integer bkgrImgSn) {
		this.getBkgrImg().setFileSn(bkgrImgSn);
	}
	public SysFileVO getBkgrImg() {
		if (this.bkgrImg == null)
			this.bkgrImg = new SysFileVO();
		return this.bkgrImg;
	}
	public void setBkgrImg(SysFileVO bkgrImg) {
		this.bkgrImg = bkgrImg;
	}
	public String getBkgrImgJson() {
		return SysFileVOUtil.getJson(this.getBkgrImg(), false);
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



}

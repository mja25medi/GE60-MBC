package egovframework.edutrack.modules.olc.olccart.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OlcCartrgVO extends DefaultVO {

	private static final long serialVersionUID = -6990766158950202262L;

	private String  cartrgCd;
	private String  orgCd;
	private String  userNo;
	private String  ctgrCd;
	private String  ctgrNm;
	private String  cartrgNm;
	private String  cartrgTag;
	private Integer cartrgOdr;
	private String  useYn;
	private String  openYn;

	private String  shareCtgrCd;
	private String  shareCtgrNm;

	private Integer cntsCnt = 0;
	private Integer shareCnt = 0;
	private Integer useCnt = 0;

	private Integer thumbFileSn;
	private SysFileVO thumbFile;

	private String knowShareCd;
	private String cntsShareCd;

	private String knowOpenYn;

	private String ctgrDivCd;

	private String olcNameColor;
	private String listBgColor;
	private String contentBgColor;
	private String pageNoLocation;
	private String listLocation;

	private Integer topLogoImgSn;
	private Integer subLogoImgSn;
	private Integer bkgImgSn;

	private SysFileVO	topLogoImg;
	private SysFileVO	subLogoImg;
	private SysFileVO	bkgImg;

	private String listFontColor;
	private String bkgImgType;

	private String shareAplcDttm;

	private String bkgImgSelType;

	public String getCartrgCd() {
		return cartrgCd;
	}
	public void setCartrgCd(String cartrgCd) {
		this.cartrgCd = cartrgCd;
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
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCartrgNm() {
		return cartrgNm;
	}
	public void setCartrgNm(String cartrgNm) {
		this.cartrgNm = cartrgNm;
	}
	public String getCartrgTag() {
		return cartrgTag;
	}
	public void setCartrgTag(String cartrgTag) {
		this.cartrgTag = cartrgTag;
	}
	public Integer getCartrgOdr() {
		return cartrgOdr;
	}
	public void setCartrgOdr(Integer cartrgOdr) {
		this.cartrgOdr = cartrgOdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public Integer getCntsCnt() {
		return cntsCnt;
	}
	public void setCntsCnt(Integer cntsCnt) {
		this.cntsCnt = cntsCnt;
	}
	public String getShareCtgrCd() {
		return shareCtgrCd;
	}
	public void setShareCtgrCd(String shareCtgrCd) {
		this.shareCtgrCd = shareCtgrCd;
	}
	public String getShareCtgrNm() {
		return shareCtgrNm;
	}
	public void setShareCtgrNm(String shareCtgrNm) {
		this.shareCtgrNm = shareCtgrNm;
	}
	public Integer getShareCnt() {
		return shareCnt;
	}
	public void setShareCnt(Integer shareCnt) {
		this.shareCnt = shareCnt;
	}
	public Integer getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(Integer useCnt) {
		this.useCnt = useCnt;
	}
	public Integer getThumbFileSn() {
		return thumbFileSn;
	}
	public void setThumbFileSn(Integer thumbFileSn) {
		this.thumbFileSn = thumbFileSn;
	}

	public SysFileVO getThumbFile() {
		if (this.thumbFile == null){
			this.thumbFile = new SysFileVO();
			this.thumbFile.setFileSn(this.thumbFileSn);
		}

		return this.thumbFile;
	}
	public void setThumbFile(SysFileVO thumbFile) {
		this.thumbFile = thumbFile;
	}
	public String getThumbFileJson() {
		return SysFileVOUtil.getJson(this.getThumbFile(), false);
	}

	public String getKnowShareCd() {
		return knowShareCd;
	}
	public void setKnowShareCd(String knowShareCd) {
		this.knowShareCd = knowShareCd;
	}
	public String getCntsShareCd() {
		return cntsShareCd;
	}
	public void setCntsShareCd(String cntsShareCd) {
		this.cntsShareCd = cntsShareCd;
	}
	public String getKnowOpenYn() {
		return knowOpenYn;
	}
	public void setKnowOpenYn(String knowOpenYn) {
		this.knowOpenYn = knowOpenYn;
	}
	public String getCtgrDivCd() {
		return ctgrDivCd;
	}
	public void setCtgrDivCd(String ctgrDivCd) {
		this.ctgrDivCd = ctgrDivCd;
	}
	public String getOlcNameColor() {
		return olcNameColor;
	}
	public void setOlcNameColor(String olcNameColor) {
		this.olcNameColor = olcNameColor;
	}
	public String getListBgColor() {
		return listBgColor;
	}
	public void setListBgColor(String listBgColor) {
		this.listBgColor = listBgColor;
	}
	public String getContentBgColor() {
		return contentBgColor;
	}
	public void setContentBgColor(String contentBgColor) {
		this.contentBgColor = contentBgColor;
	}
	public String getPageNoLocation() {
		return pageNoLocation;
	}
	public void setPageNoLocation(String pageNoLocation) {
		this.pageNoLocation = pageNoLocation;
	}
	public String getListLocation() {
		return listLocation;
	}
	public void setListLocation(String listLocation) {
		this.listLocation = listLocation;
	}

	public Integer getTopLogoImgSn() {
		return topLogoImgSn;
	}
	public void setTopLogoImgSn(Integer topLogoImgSn) {
		this.topLogoImgSn = topLogoImgSn;
	}
	public SysFileVO getTopLogoImg() {
		if (this.topLogoImg == null){
			this.topLogoImg = new SysFileVO();
			this.topLogoImg.setFileSn(this.topLogoImgSn);
		}
		return topLogoImg;
	}
	public void setTopLogoImg(SysFileVO topLogoImg) {
		this.topLogoImg = topLogoImg;
	}
	public String getTopLogoImgJson() {
		return SysFileVOUtil.getJson(this.getTopLogoImg(), false);
	}

	public Integer getSubLogoImgSn() {
		return subLogoImgSn;
	}
	public void setSubLogoImgSn(Integer subLogoImgSn) {
		this.subLogoImgSn = subLogoImgSn;
	}
	public SysFileVO getSubLogoImg() {
		if (this.subLogoImg == null){
			this.subLogoImg = new SysFileVO();
			this.subLogoImg.setFileSn(this.subLogoImgSn);
		}
		return subLogoImg;
	}
	public void setSubLogoImg(SysFileVO subLogoImg) {
		this.subLogoImg = subLogoImg;
	}
	public String getSubLogoImgJson() {
		return SysFileVOUtil.getJson(this.getSubLogoImg(), false);
	}

	public Integer getBkgImgSn() {
		return bkgImgSn;
	}
	public void setBkgImgSn(Integer bkgImgSn) {
		this.bkgImgSn = bkgImgSn;
	}
	public SysFileVO getBkgImg() {
		if (this.bkgImg == null){
			this.bkgImg = new SysFileVO();
			this.bkgImg.setFileSn(this.bkgImgSn);
		}
		return bkgImg;
	}
	public void setBkgImg(SysFileVO bkgImg) {
		this.bkgImg = bkgImg;
	}
	public String getBkgImgJson() {
		return SysFileVOUtil.getJson(this.getBkgImg(), false);
	}
	public String getListFontColor() {
		return listFontColor;
	}
	public void setListFontColor(String listFontColor) {
		this.listFontColor = listFontColor;
	}
	public String getBkgImgType() {
		return bkgImgType;
	}
	public void setBkgImgType(String bkgImgType) {
		this.bkgImgType = bkgImgType;
	}
	public String getShareAplcDttm() {
		return shareAplcDttm;
	}
	public void setShareAplcDttm(String shareAplcDttm) {
		this.shareAplcDttm = shareAplcDttm;
	}
	public String getBkgImgSelType() {
		return bkgImgSelType;
	}
	public void setBkgImgSelType(String bkgImgSelType) {
		this.bkgImgSelType = bkgImgSelType;
	}
}

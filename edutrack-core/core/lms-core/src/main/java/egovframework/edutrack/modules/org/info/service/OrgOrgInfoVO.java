package egovframework.edutrack.modules.org.info.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgOrgInfoVO extends DefaultVO{

	private static final long serialVersionUID = -7605381152519680765L;

	private String  orgCd;
	private String  orgNm;
	private String  orgDomain;
	private String  domainNm;
	private String  startDttm;
	private String  endDttm;
	private String  useYn;
	private String  dfltLangCd;
	private String  productCd;
	private String  layoutTplCd;
	private String  colorTplCd;
	private Integer limitNopCnt;
	private String  tplCd;
	private String  autoMakeYn;
	private String  durationYn;
	private String  wwwFooter;
	private String  admFooter;
	private String  menuVer;
	private String  bankNm;
	private String  acntNo;
	private String  acntNm;
	private String  emailAddr;
	private String  emailNm;
	private String  rprstPhoneNo;
	private String  rprstDomain;
	private String  kollusUseYn;
	private String  kollusKeyCd;
	private String  kollusConnUrl;
	private String  kollusTokenCd;
	private String  kollusCtgrCd;

	private String  subLogo1Url;
	private String  subLogo2Url;
	private String  chrgPrsnInfo;
	private String  mbrIdType;
	private String  mbrAplcUseYn;

	private SysFileVO	topLogoFileVO;
	private SysFileVO	subLogo1FileVO;
	private SysFileVO	subLogo2FileVO;
	private SysFileVO	admLogoFileVO;
	private SysFileVO	contractFileVO;
	private int logCnt;
	
	private String[] contextArr;
	private String 	contextName;
	private String productServiceType;
	
	//개설신청번호
	private Integer creAplcSn;
	//통합회원 사용여부
	private String  itgrtMbrUseYn;
	
	//사이트 용도
	private String  siteUsageCd;
	
	//컨텐츠 권한 코드
	private String  conAuthCd;
	
	//산업인력공단(HRD) API 사용여부
	private String hrdApiUseYn;
	
	// xr 클라우드 sceneId
	private String sceneId;
	// xr 클라우드 roomUrl
	private String roomUrl;
	
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getRoomUrl() {
		return roomUrl;
	}
	public void setRoomUrl(String roomUrl) {
		this.roomUrl = roomUrl;
	}

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getDomainNm() {
		return domainNm;
	}
	public void setDomainNm(String domainNm) {
		this.domainNm = domainNm;
	}
	public String getStartDttm() {
		return startDttm;
	}
	public void setStartDttm(String startDttm) {
		this.startDttm = startDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDfltLangCd() {
		return dfltLangCd;
	}
	public void setDfltLangCd(String dfltLangCd) {
		this.dfltLangCd = dfltLangCd;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getLayoutTplCd() {
		return layoutTplCd;
	}
	public void setLayoutTplCd(String layoutTplCd) {
		this.layoutTplCd = layoutTplCd;
	}
	public String getColorTplCd() {
		return colorTplCd;
	}
	public void setColorTplCd(String colorTplCd) {
		this.colorTplCd = colorTplCd;
	}
	public Integer getLimitNopCnt() {
		return limitNopCnt;
	}
	public void setLimitNopCnt(Integer limitNopCnt) {
		this.limitNopCnt = limitNopCnt;
	}
	public String getTplCd() {
		return tplCd;
	}
	public void setTplCd(String tplCd) {
		this.tplCd = tplCd;
	}
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
	public String getDurationYn() {
		return durationYn;
	}
	public void setDurationYn(String durationYn) {
		this.durationYn = durationYn;
	}
	public String getWwwFooter() {
		return wwwFooter;
	}
	public void setWwwFooter(String wwwFooter) {
		this.wwwFooter = wwwFooter;
	}
	public String getAdmFooter() {
		return admFooter;
	}
	public void setAdmFooter(String admFooter) {
		this.admFooter = admFooter;
	}
	public String getMenuVer() {
		return menuVer;
	}
	public void setMenuVer(String menuVer) {
		this.menuVer = menuVer;
	}
	public String getBankNm() {
		return bankNm;
	}
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}
	public String getAcntNo() {
		return acntNo;
	}
	public void setAcntNo(String acntNo) {
		this.acntNo = acntNo;
	}
	public String getAcntNm() {
		return acntNm;
	}
	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getEmailNm() {
		return emailNm;
	}
	public void setEmailNm(String emailNm) {
		this.emailNm = emailNm;
	}
	public String getRprstPhoneNo() {
		return rprstPhoneNo;
	}
	public void setRprstPhoneNo(String rprstPhoneNo) {
		this.rprstPhoneNo = rprstPhoneNo;
	}
	public String getRprstDomain() {
		return rprstDomain;
	}
	public void setRprstDomain(String rprstDomain) {
		this.rprstDomain = rprstDomain;
	}
	public String getSubLogo1Url() {
		return subLogo1Url;
	}
	public void setSubLogo1Url(String subLogo1Url) {
		this.subLogo1Url = subLogo1Url;
	}
	public String getSubLogo2Url() {
		return subLogo2Url;
	}
	public void setSubLogo2Url(String subLogo2Url) {
		this.subLogo2Url = subLogo2Url;
	}
	public String getChrgPrsnInfo() {
		return chrgPrsnInfo;
	}
	public void setChrgPrsnInfo(String chrgPrsnInfo) {
		this.chrgPrsnInfo = chrgPrsnInfo;
	}
	public String getMbrIdType() {
		return mbrIdType;
	}
	public void setMbrIdType(String mbrIdType) {
		this.mbrIdType = mbrIdType;
	}
	public String getKollusUseYn() {
		return kollusUseYn;
	}
	public String getKollusKeyCd() {
		return kollusKeyCd;
	}
	public void setKollusKeyCd(String kollusKeyCd) {
		this.kollusKeyCd = kollusKeyCd;
	}
	public void setKollusUseYn(String kollusUseYn) {
		this.kollusUseYn = kollusUseYn;
	}
	public String getKollusConnUrl() {
		return kollusConnUrl;
	}
	public void setKollusConnUrl(String kollusConnUrl) {
		this.kollusConnUrl = kollusConnUrl;
	}
	public String getKollusTokenCd() {
		return kollusTokenCd;
	}
	public void setKollusTokenCd(String kollusTokenCd) {
		this.kollusTokenCd = kollusTokenCd;
	}
	public String getKollusCtgrCd() {
		return kollusCtgrCd;
	}
	public void setKollusCtgrCd(String kollusCtgrCd) {
		this.kollusCtgrCd = kollusCtgrCd;
	}
	public int getLogCnt() {
		return logCnt;
	}
	public void setLogCnt(int logCnt) {
		this.logCnt = logCnt;
	}
	public String getMbrAplcUseYn() {
		return mbrAplcUseYn;
	}
	public void setMbrAplcUseYn(String mbrAplcUseYn) {
		this.mbrAplcUseYn = mbrAplcUseYn;
	}
	public Integer getCreAplcSn() {
		return creAplcSn;
	}
	public void setCreAplcSn(Integer creAplcSn) {
		this.creAplcSn = creAplcSn;
	}
	public String getItgrtMbrUseYn() {
		return itgrtMbrUseYn;
	}
	public void setItgrtMbrUseYn(String itgrtMbrUseYn) {
		this.itgrtMbrUseYn = itgrtMbrUseYn;
	}
	
	
	//-- 파일 관련 처리
	public SysFileVO getTopLogoFileVO() {
		if (this.topLogoFileVO == null)
			this.topLogoFileVO = new SysFileVO();
		return topLogoFileVO;
	}
	public void setTopLogoFileVO(SysFileVO topLogoFileVO) {
		this.topLogoFileVO = topLogoFileVO;
	}
	public Integer getTopLogoFileSn() {
		return this.getTopLogoFileVO().getFileSn();
	}
	public void setTopLogoFileSn(Integer topLogoFileSn) {
		this.topLogoFileVO = new SysFileVO(topLogoFileSn);
	}
	public String getTopLogoFileJson() {
		return SysFileVOUtil.getJson(this.getTopLogoFileVO(), false);
	}

	public SysFileVO getSubLogo1FileVO() {
		if (this.subLogo1FileVO == null)
			this.subLogo1FileVO = new SysFileVO();
		return subLogo1FileVO;
	}
	public void setSubLogo1FileVO(SysFileVO subLogo1FileVO) {
		this.subLogo1FileVO = subLogo1FileVO;
	}
	public Integer getSubLogo1FileSn() {
		return this.getSubLogo1FileVO().getFileSn();
	}
	public void setSubLogo1FileSn(Integer subLogo1FileSn) {
		this.subLogo1FileVO = new SysFileVO(subLogo1FileSn);
	}
	public String getSubLogo1FileJson() {
		return SysFileVOUtil.getJson(this.getSubLogo1FileVO(), false);
	}

	public SysFileVO getSubLogo2FileVO() {
		if (this.subLogo2FileVO == null)
			this.subLogo2FileVO = new SysFileVO();
		return subLogo2FileVO;
	}
	public void setSubLogo2FileVO(SysFileVO subLogo2FileVO) {
		this.subLogo2FileVO = subLogo2FileVO;
	}
	public Integer getSubLogo2FileSn() {
		return this.getSubLogo2FileVO().getFileSn();
	}
	public void setSubLogo2FileSn(Integer subLogo2FileSn) {
		this.subLogo2FileVO = new SysFileVO(subLogo2FileSn);
	}
	public String getSubLogo2FileJson() {
		return SysFileVOUtil.getJson(this.getSubLogo2FileVO(), false);
	}

	public SysFileVO getAdmLogoFileVO() {
		if (this.admLogoFileVO == null)
			this.admLogoFileVO = new SysFileVO();
		return admLogoFileVO;
	}
	public void setAdmLogoFileVO(SysFileVO admLogoFileVO) {
		this.admLogoFileVO = admLogoFileVO;
	}
	public Integer getAdmLogoFileSn() {
		return this.getAdmLogoFileVO().getFileSn();
	}
	public void setAdmLogoFileSn(Integer admLogoFileSn) {
		this.admLogoFileVO = new SysFileVO(admLogoFileSn);
	}
	public String getAdmLogoFileJson() {
		return SysFileVOUtil.getJson(this.getAdmLogoFileVO(), false);
	}
	
	public SysFileVO getContractFileVO() {
		if (this.contractFileVO == null)
			this.contractFileVO = new SysFileVO();
		return contractFileVO;
	}
	public void setContractFileVO(SysFileVO contractFileVO) {
		this.contractFileVO = contractFileVO;
	}
	public Integer getContractFileSn() {
		return this.getContractFileVO().getFileSn();
	}
	public void setContractFileSn(Integer contractFileSn) {
		this.contractFileVO = new SysFileVO(contractFileSn);
	}
	public String getContractFileJson() {
		return SysFileVOUtil.getJson(this.getContractFileVO(), false);
	}
	
	public String[] getContextArr() {
		return contextArr;
	}
	public void setContextArr(String[] contextArr) {
		this.contextArr = contextArr;
	}
	public String getProductServiceType() {
		return productServiceType;
	}
	public void setProductServiceType(String productServiceType) {
		this.productServiceType = productServiceType;
	}
	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	public String getOrgDomain() {
		return orgDomain;
	}
	public void setOrgDomain(String orgDomain) {
		this.orgDomain = orgDomain;
	}
	
	public String getSiteUsageCd() {
		return siteUsageCd;
	}
	public void setSiteUsageCd(String siteUsageCd) {
		this.siteUsageCd = siteUsageCd;
	}
	public String getConAuthCd() {
		return conAuthCd;
	}
	public void setConAuthCd(String conAuthCd) {
		this.conAuthCd = conAuthCd;
	}
	public String getHrdApiUseYn() {
		return hrdApiUseYn;
	}
	public void setHrdApiUseYn(String hrdApiUseYn) {
		this.hrdApiUseYn = hrdApiUseYn;
	}
	
}

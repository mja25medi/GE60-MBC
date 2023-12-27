package egovframework.edutrack.modules.user.info.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class UsrLoginVO extends DefaultVO {

	private static final long serialVersionUID = 5941614641360094267L;
	
	private String userNo;
	private String userId;
	private String loginId;
	private String userPass;
	private String userPassConfirm;
	private String newUserPass;
	private String newUserPassConfirm;
	private String encUserPass;
	private String adminLoginAcptDivCd;
	private String gpkiInfo;
	private String loginFailDttm;
	private Integer loginFailCnt;
	private String lastLoginDttm;
	private Integer loginCnt;
	private String aplcDttm;
	private String aprvDttm;
	private String secedeDttm;
	private String userSts;
	private String userStsNm;
	private String isUseable = "N";
	private String loginUseYn;
	private String cfgLoginCnt;
	private String pswdChgReqDttm;
	private String pswdChgReqYn;
	private String orgCd;

	private String encryptData;
	
	/* 2016-12-21 arothy about SNS*/
	private String snsKey;
	private String snsDiv;
	private String snsCode;
	
	private String ssoKey;
	private String ssoIdx;
	
	private String	wwwAuthGrpCd;
	private String	adminAuthGrpCd;
	private String  mngAuthGrpCd;
	private String	wwwAuthGrpNm;
	private String	adminAuthGrpNm;
	private String  mngAuthGrpNm;
	
	private String exceptYn;
	
	private String phoneVeriYn;
	
	public String getWwwAuthGrpCd() {
		return wwwAuthGrpCd;
	}
	public void setWwwAuthGrpCd(String wwwAuthGrpCd) {
		this.wwwAuthGrpCd = wwwAuthGrpCd;
	}
	public String getAdminAuthGrpCd() {
		return adminAuthGrpCd;
	}
	public void setAdminAuthGrpCd(String adminAuthGrpCd) {
		this.adminAuthGrpCd = adminAuthGrpCd;
	}
	public String getMngAuthGrpCd() {
		return mngAuthGrpCd;
	}
	public void setMngAuthGrpCd(String mngAuthGrpCd) {
		this.mngAuthGrpCd = mngAuthGrpCd;
	}
	public String getWwwAuthGrpNm() {
		return wwwAuthGrpNm;
	}
	public void setWwwAuthGrpNm(String wwwAuthGrpNm) {
		this.wwwAuthGrpNm = wwwAuthGrpNm;
	}
	public String getAdminAuthGrpNm() {
		return adminAuthGrpNm;
	}
	public void setAdminAuthGrpNm(String adminAuthGrpNm) {
		this.adminAuthGrpNm = adminAuthGrpNm;
	}
	public String getMngAuthGrpNm() {
		return mngAuthGrpNm;
	}
	public void setMngAuthGrpNm(String mngAuthGrpNm) {
		this.mngAuthGrpNm = mngAuthGrpNm;
	}
	public String getSnsKey() {
		return snsKey;
	}
	public void setSnsKey(String snsKey) {
		this.snsKey = snsKey;
	}
	public String getSnsDiv() {
		return snsDiv;
	}
	public void setSnsDiv(String snsDiv) {
		this.snsDiv = snsDiv;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPswd) {
		this.userPass = userPswd;
	}
	public String getUserPassConfirm() {
		return userPassConfirm;
	}
	public void setUserPassConfirm(String userPswdConfirm) {
		this.userPassConfirm = userPswdConfirm;
	}
	public String getNewUserPass() {
		return newUserPass;
	}
	public void setNewUserPass(String newUserPass) {
		this.newUserPass = newUserPass;
	}
	public String getNewUserPassConfirm() {
		return newUserPassConfirm;
	}
	public void setNewUserPassConfirm(String newUserPassConfirm) {
		this.newUserPassConfirm = newUserPassConfirm;
	}
	public String getEncUserPass() {
		return encUserPass;
	}
	public void setEncUserPass(String encUserPass) {
		this.encUserPass = encUserPass;
	}
	public String getAdminLoginAcptDivCd() {
		return adminLoginAcptDivCd;
	}
	public void setAdminLoginAcptDivCd(String adminLoginAcptDivCd) {
		this.adminLoginAcptDivCd = adminLoginAcptDivCd;
	}
	public String getGpkiInfo() {
		return gpkiInfo;
	}
	public void setGpkiInfo(String gpkiInfo) {
		this.gpkiInfo = gpkiInfo;
	}
	public String getLoginFailDttm() {
		return loginFailDttm;
	}
	public void setLoginFailDttm(String loginFailDttm) {
		this.loginFailDttm = loginFailDttm;
	}
	public Integer getLoginFailCnt() {
		return loginFailCnt;
	}
	public void setLoginFailCnt(Integer loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	public String getLastLoginDttm() {
		return lastLoginDttm;
	}
	public void setLastLoginDttm(String lastLoginDttm) {
		this.lastLoginDttm = lastLoginDttm;
	}
	public Integer getLoginCnt() {
		return loginCnt;
	}
	public void setLoginCnt(Integer loginCnt) {
		this.loginCnt = loginCnt;
	}
	public String getAplcDttm() {
		return aplcDttm;
	}
	public void setAplcDttm(String aplcDttm) {
		this.aplcDttm = aplcDttm;
	}
	public String getAprvDttm() {
		return aprvDttm;
	}
	public void setAprvDttm(String aprvDttm) {
		this.aprvDttm = aprvDttm;
	}
	public String getSecedeDttm() {
		return secedeDttm;
	}
	public void setSecedeDttm(String secedeDttm) {
		this.secedeDttm = secedeDttm;
	}
	public String getUserSts() {
		return userSts;
	}
	public void setUserSts(String userSts) {
		this.userSts = userSts;
	}
	public String getUserStsNm() {
		return userStsNm;
	}
	public void setUserStsNm(String userStsNm) {
		this.userStsNm = userStsNm;
	}	
	
	public String getEncryptData() {
		return encryptData;
	}
	public void setEncryptData(String encryptData) {
		this.encryptData = encryptData;
	}
	public String getIsUseable() {
		return isUseable;
	}
	public void setIsUseable(String isUseable) {
		this.isUseable = isUseable;
	}
	public String getLoginUseYn() {
		return loginUseYn;
	}
	public void setLoginUseYn(String loginUseYn) {
		this.loginUseYn = loginUseYn;
	}
	public String getCfgLoginCnt() {
		return cfgLoginCnt;
	}
	public void setCfgLoginCnt(String cfgLoginCnt) {
		this.cfgLoginCnt = cfgLoginCnt;
	}
	public String getPswdChgReqDttm() {
		return pswdChgReqDttm;
	}
	public void setPswdChgReqDttm(String pswdChgReqDttm) {
		this.pswdChgReqDttm = pswdChgReqDttm;
	}
	public String getPswdChgReqYn() {
		return pswdChgReqYn;
	}
	public void setPswdChgReqYn(String pswdChgReqYn) {
		this.pswdChgReqYn = pswdChgReqYn;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getSnsCode() {
		return snsCode;
	}
	public void setSnsCode(String snsCode) {
		this.snsCode = snsCode;
	}
	public String getSsoKey() {
		return ssoKey;
	}
	public void setSsoKey(String ssoKey) {
		this.ssoKey = ssoKey;
	}
	public String getSsoIdx() {
		return ssoIdx;
	}
	public void setSsoIdx(String ssoIdx) {
		this.ssoIdx = ssoIdx;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getExceptYn() {
		return exceptYn;
	}
	public void setExceptYn(String exceptYn) {
		this.exceptYn = exceptYn;
	}
	
	public String getPhoneVeriYn() {
		return phoneVeriYn;
	}

	public void setPhoneVeriYn(String phoneVeriYn) {
		this.phoneVeriYn = phoneVeriYn;
	}
}

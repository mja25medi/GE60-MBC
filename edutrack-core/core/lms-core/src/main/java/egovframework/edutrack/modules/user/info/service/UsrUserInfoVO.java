package egovframework.edutrack.modules.user.info.service;

import egovframework.edutrack.comm.util.security.KISASeed;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;
import egovframework.edutrack.modules.teacher.info.service.TchInfoVO;

public class UsrUserInfoVO extends UsrLoginVO {

	private static final long serialVersionUID = -484409939756667137L;

	private String  userDivCd;
	private String  userDivNm;
	private String  userNm;
	private Integer photoFileSn;
	private String  birth;
	private String  sexCd;
	private String  sexNm;
	private String  dupInfo;
	private String  homeAddrDivNm;
	private String  homePostNo;
	private String  homeAddr1;
	private String  homeAddr2;
	private String  homePhoneno;
	private String  mobileNo;
	private String  email;
	private String  compNm;
	private String  deptNm;
	private String  deptCd;
	private String  areaCd;
	private String  areaNm;
	private String  orgCd;
	private String  orgNm;
	private String  compPhoneno;
	private String  compFaxNo;
	private String  jobCd;
	private String  jobNm;

	private String  compAddrDivNm;
	private String  compPostNo;
	private String  compAddr1;
	private String  compAddr2;
	private String  tchSbj;

	private String	wwwAuthGrpCd;
	private String	adminAuthGrpCd;
	private String  mngAuthGrpCd;
	private String	wwwAuthGrpNm;
	private String	adminAuthGrpNm;
	private String  mngAuthGrpNm;

	private String  menuType;
	private String  authGrpCd;
	private String  searchAuthGrp;
	private String  searchUserStatus;

	private String  checkCertiYn = "N";
	private String  checkAgree = "N";

	private String  emailRecv;
	private String  smsRecv;
	private String  msgRecv;
	private String  tempYn = "N";
	private String  migChk;
	
	private String exceptYn;

	//김현욱 추가
	private String interestField;//관심분야

	private String tchCtgrCd;
	private String tchDivCd;

	//-- excel upload용으로 추가
	private String  lineNo;
	private String  errorCode;

	private String interestFieldCd;
	private String interestFieldNm;

	private String  userNmGana;
	private String  userNmEng;
	private String  etcPhoneno;
	private String  disablilityYn;
	private String  memo;

	private SysFileVO photoFile;
	
	/* 2016-11-18 arothy */
	private String	usrKnowCtgrCds; //나의 관심분야(str)
	private String	usrKnowKeywords; //나의 관심검색어(str)
	
	/* 2016-12-26 kjbg */
	private String	usrKnowCtgrNames; //나의 관심분야 이름(str)
	
	private String	itgrtMbrUseYn; //통합로그인
	
	private String rtnJson; //통합로그인 json 데이타
	
	private String userNo;
	private String userId;
	
	private String juminNo;
	private String stdDivCd;		// 훈련생 구분코드
	private String nonReguDivCd;	// 비정규직 구분코드
	private String costCompNo;		// 비용수급사업장 번호
	
	private String fileName;
	private String filePath;
	
	private TchInfoVO tchInfoVO;
	
	//전화상담 관련
	private String cnstCd;
	private String cnstUser;
	private String cnstId;
	private String cnstDesc;
	
	private String foreignYn;
	
	private String avatar;
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 생성자
	 */
	public UsrUserInfoVO() {
		tchInfoVO = new TchInfoVO();
	}
	
	public void encryptJuminNo() {
		if(this.juminNo != null && this.juminNo != "")
		this.juminNo = KISASeed.seedEncryption(this.juminNo);
	}
	
	public void decryptJuminNo() {
		this.juminNo = KISASeed.seedDecryption(this.juminNo);
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
	public String getUsrKnowCtgrNames() {
		return usrKnowCtgrNames;
	}
	public void setUsrKnowCtgrNames(String usrKnowCtgrNames) {
		this.usrKnowCtgrNames = usrKnowCtgrNames;
	}
	public String getItgrtMbrUseYn() {
		return itgrtMbrUseYn;
	}
	public void setItgrtMbrUseYn(String itgrtMbrUseYn) {
		this.itgrtMbrUseYn = itgrtMbrUseYn;
	}
	public String getUsrKnowKeywords() {
		return usrKnowKeywords;
	}
	public void setUsrKnowKeywords(String usrKnowKeywords) {
		this.usrKnowKeywords = usrKnowKeywords;
	}
	public String getUsrKnowCtgrCds() {
		return usrKnowCtgrCds;
	}
	public void setUsrKnowCtgrCds(String usrKnowCtgrCds) {
		this.usrKnowCtgrCds = usrKnowCtgrCds;
	}
	public String getUserDivCd() {
		return userDivCd;
	}
	public void setUserDivCd(String userDivCd) {
		this.userDivCd = userDivCd;
	}
	public String getUserDivNm() {
		return userDivNm;
	}
	public void setUserDivNm(String userDivNm) {
		this.userDivNm = userDivNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getSexNm() {
		return sexNm;
	}
	public void setSexNm(String sexNm) {
		this.sexNm = sexNm;
	}
	public String getDupInfo() {
		return dupInfo;
	}
	public void setDupInfo(String dupInfo) {
		this.dupInfo = dupInfo;
	}
	public String getHomeAddrDivNm() {
		return homeAddrDivNm;
	}
	public void setHomeAddrDivNm(String homeAddrDivNm) {
		this.homeAddrDivNm = homeAddrDivNm;
	}
	public String getHomePostNo() {
		return homePostNo;
	}
	public void setHomePostNo(String homePostNo) {
		this.homePostNo = homePostNo;
	}
	public String getHomeAddr1() {
		return homeAddr1;
	}
	public void setHomeAddr1(String homeAddr1) {
		this.homeAddr1 = homeAddr1;
	}
	public String getHomeAddr2() {
		return homeAddr2;
	}
	public void setHomeAddr2(String homeAddr2) {
		this.homeAddr2 = homeAddr2;
	}
	public String getHomePhoneno() {
		return homePhoneno;
	}
	public void setHomePhoneno(String homePhoneno) {
		this.homePhoneno = homePhoneno;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}
	public String getAreaNm() {
		return areaNm;
	}
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCompPhoneno() {
		return compPhoneno;
	}
	public void setCompPhoneno(String compPhoneno) {
		this.compPhoneno = compPhoneno;
	}
	public String getCompFaxNo() {
		return compFaxNo;
	}
	public void setCompFaxNo(String compFaxNo) {
		this.compFaxNo = compFaxNo;
	}
	public String getCompAddrDivNm() {
		return compAddrDivNm;
	}
	public void setCompAddrDivNm(String compAddrDivNm) {
		this.compAddrDivNm = compAddrDivNm;
	}
	public String getCompPostNo() {
		return compPostNo;
	}
	public void setCompPostNo(String compPostNo) {
		this.compPostNo = compPostNo;
	}
	public String getCompAddr1() {
		return compAddr1;
	}
	public void setCompAddr1(String compAddr1) {
		this.compAddr1 = compAddr1;
	}
	public String getCompAddr2() {
		return compAddr2;
	}
	public void setCompAddr2(String compAddr2) {
		this.compAddr2 = compAddr2;
	}
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
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getAuthGrpCd() {
		return authGrpCd;
	}
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
	}
	public String getSearchAuthGrp() {
		return searchAuthGrp;
	}
	public void setSearchAuthGrp(String searchAuthGrp) {
		this.searchAuthGrp = searchAuthGrp;
	}
	public String getCheckCertiYn() {
		return checkCertiYn;
	}
	public void setCheckCertiYn(String checkCertiYn) {
		this.checkCertiYn = checkCertiYn;
	}
	public String getCheckAgree() {
		return checkAgree;
	}
	public void setCheckAgree(String checkAgree) {
		this.checkAgree = checkAgree;
	}
	public String getEmailRecv() {
		return emailRecv;
	}
	public void setEmailRecv(String emailRecv) {
		this.emailRecv = emailRecv;
	}
	public String getSmsRecv() {
		return smsRecv;
	}
	public void setSmsRecv(String smsRecv) {
		this.smsRecv = smsRecv;
	}
	public String getMsgRecv() {
		return msgRecv;
	}
	public void setMsgRecv(String msgRecv) {
		this.msgRecv = msgRecv;
	}
	public String getTempYn() {
		return tempYn;
	}
	public void setTempYn(String tempYn) {
		this.tempYn = tempYn;
	}
	public String getMigChk() {
		return migChk;
	}
	public void setMigChk(String migChk) {
		this.migChk = migChk;
	}
	public String getTchSbj() {
		return tchSbj;
	}
	public void setTchSbj(String tchSbj) {
		this.tchSbj = tchSbj;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getSearchUserStatus() {
		return searchUserStatus;
	}
	public void setSearchUserStatus(String searchUserStatus) {
		this.searchUserStatus = searchUserStatus;
	}
	public String getInterestField() {
		return interestField;
	}
	public void setInterestField(String interestField) {
		this.interestField = interestField;
	}
	public String getTchCtgrCd() {
		return tchCtgrCd;
	}
	public void setTchCtgrCd(String tchCtgrCd) {
		this.tchCtgrCd = tchCtgrCd;
	}
	public String getTchDivCd() {
		return tchDivCd;
	}
	public void setTchDivCd(String tchDivCd) {
		this.tchDivCd = tchDivCd;
	}
	public String getInterestFieldCd() {
		return interestFieldCd;
	}
	public void setInterestFieldCd(String interestFieldCd) {
		this.interestFieldCd = interestFieldCd;
	}
	public String getUserNmGana() {
		return userNmGana;
	}
	public void setUserNmGana(String userNmGana) {
		this.userNmGana = userNmGana;
	}
	public String getUserNmEng() {
		return userNmEng;
	}
	public void setUserNmEng(String userNmEng) {
		this.userNmEng = userNmEng;
	}
	public String getEtcPhoneno() {
		return etcPhoneno;
	}
	public void setEtcPhoneno(String etcPhoneno) {
		this.etcPhoneno = etcPhoneno;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getDisablilityYn() {
		return disablilityYn;
	}
	public void setDisablilityYn(String disablilityYn) {
		this.disablilityYn = disablilityYn;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	//-- 사용자 사진 파일 관련 처리
	public Integer getPhotoFileSn() {
		if(ValidationUtils.isNull(this.photoFileSn))
		return this.getPhotoFile().getFileSn();
		else return this.photoFileSn;
	}
	public void setPhotoFileSn(Integer photoFileSn) {
		this.getPhotoFile().setFileSn(photoFileSn);
	}

	public SysFileVO getPhotoFile() {
		if(this.photoFile == null)
			this.photoFile = new SysFileVO();
		return this.photoFile;
	}
	public void setPhotoFile(SysFileVO photoFile) {
		this.photoFile = photoFile;
	}
	public String getPhotoFileJson(){
		return SysFileVOUtil.getJson(this.getPhotoFile(), false);
	}
	public String getInterestFieldNm() {
		return interestFieldNm;
	}
	public void setInterestFieldNm(String interestFieldNm) {
		this.interestFieldNm = interestFieldNm;
	}
	public String getJobCd() {
		return jobCd;
	}
	public void setJobCd(String jobCd) {
		this.jobCd = jobCd;
	}
	public String getJobNm() {
		return jobNm;
	}
	public void setJobNm(String jobNm) {
		this.jobNm = jobNm;
	}
	
	public String getString() {
		String retVal = "{";
		if(ValidationUtils.isNotEmpty(this.userDivCd)) {
			retVal += "'userDivCd':"+this.userDivCd;
		}
		if(ValidationUtils.isNotEmpty(this.userNm)) {
			retVal += "'userNm':"+this.userNm;
		}
		if(ValidationUtils.isNotEmpty(this.birth)) {
			retVal += "'birth':"+this.birth;
		}
		if(ValidationUtils.isNotEmpty(this.sexCd)) {
			retVal += "'sexCd':"+this.sexCd;
		}
		if(ValidationUtils.isNotEmpty(this.homeAddr1)) {
			retVal += "'homeAddr1':"+this.homeAddr1;
		}
		if(ValidationUtils.isNotEmpty(this.homeAddr2)) {
			retVal += "'homeAddr2':"+this.homeAddr2;
		}
		if(ValidationUtils.isNotEmpty(this.homePhoneno)) {
			retVal += "'homePhoneno':"+this.homePhoneno;
		}		
		if(ValidationUtils.isNotEmpty(this.mobileNo)) {
			retVal += "'mobileNo':"+this.mobileNo;
		}
		if(ValidationUtils.isNotEmpty(this.email)) {
			retVal += "'email':"+this.email;
		}
		if(ValidationUtils.isNotEmpty(this.compNm)) {
			retVal += "'compNm':"+this.compNm;
		}
		if(ValidationUtils.isNotEmpty(this.deptNm)) {
			retVal += "'deptNm':"+this.deptNm;
		}
		if(ValidationUtils.isNotEmpty(this.deptCd)) {
			retVal += "'deptCd':"+this.deptCd;
		}
		if(ValidationUtils.isNotEmpty(this.areaCd)) {
			retVal += "'areaCd':"+this.areaCd;
		}		
		if(ValidationUtils.isNotEmpty(this.orgCd)) {
			retVal += "'orgCd':"+this.orgCd;
		}
		if(ValidationUtils.isNotEmpty(this.compPhoneno)) {
			retVal += "'compPhoneno':"+this.compPhoneno;
		}
		if(ValidationUtils.isNotEmpty(this.compFaxNo)) {
			retVal += "'compFaxNo':"+this.compFaxNo;
		}		
		if(ValidationUtils.isNotEmpty(this.compAddr1)) {
			retVal += "'compAddr1':"+this.compAddr1;
		}
		if(ValidationUtils.isNotEmpty(this.compAddr2)) {
			retVal += "'compAddr2':"+this.compAddr2;
		}
		if(ValidationUtils.isNotEmpty(this.wwwAuthGrpCd)) {
			retVal += "'wwwAuthGrpCd':"+this.wwwAuthGrpCd;
		}
		if(ValidationUtils.isNotEmpty(this.adminAuthGrpCd)) {
			retVal += "'adminAuthGrpCd':"+this.adminAuthGrpCd;
		}
		if(ValidationUtils.isNotEmpty(this.mngAuthGrpCd)) {
			retVal += "'mngAuthGrpCd':"+this.mngAuthGrpCd;
		}
		if(ValidationUtils.isNotEmpty(this.mngAuthGrpCd)) {
			retVal += "'mngAuthGrpCd':"+this.mngAuthGrpCd;
		}		
		if(ValidationUtils.isNotEmpty(this.emailRecv)) {
			retVal += "'emailRecv':"+this.emailRecv;
		}
		if(ValidationUtils.isNotEmpty(this.smsRecv)) {
			retVal += "'smsRecv':"+this.smsRecv;
		}
		if(ValidationUtils.isNotEmpty(this.msgRecv)) {
			retVal += "'msgRecv':"+this.msgRecv;
		}
		if(ValidationUtils.isNotEmpty(this.interestField)) {
			retVal += "'interestField':"+this.interestField;
		}
		if(ValidationUtils.isNotEmpty(this.interestFieldCd)) {
			retVal += "'interestFieldCd':"+this.interestFieldCd;
		}		
		if(ValidationUtils.isNotEmpty(this.userNmGana)) {
			retVal += "'userNmGana':"+this.userNmGana;
		}
		if(ValidationUtils.isNotEmpty(this.userNmEng)) {
			retVal += "'userNmEng':"+this.userNmEng;
		}
		if(ValidationUtils.isNotEmpty(this.etcPhoneno)) {
			retVal += "'etcPhoneno':"+this.etcPhoneno;
		}
		return retVal;
	}
	public String getRtnJson() {
		return rtnJson;
	}
	public void setRtnJson(String rtnJson) {
		this.rtnJson = rtnJson;
	}
	public TchInfoVO getTchInfoVO() {
		return tchInfoVO;
	}
	public void setTchInfoVO(TchInfoVO tchInfoVO) {
		this.tchInfoVO = tchInfoVO;
	}

	public String getStdDivCd() {
		return stdDivCd;
	}

	public void setStdDivCd(String stdDivCd) {
		this.stdDivCd = stdDivCd;
	}

	public String getNonReguDivCd() {
		return nonReguDivCd;
	}

	public void setNonReguDivCd(String nonReguDivCd) {
		this.nonReguDivCd = nonReguDivCd;
	}

	public String getCostCompNo() {
		return costCompNo;
	}

	public void setCostCompNo(String costCompNo) {
		this.costCompNo = costCompNo;
	}

	public String getJuminNo() {
		return juminNo;
	}

	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExceptYn() {
		return exceptYn;
	}

	public void setExceptYn(String exceptYn) {
		this.exceptYn = exceptYn;
	}

	public String getCnstCd() {
		return cnstCd;
	}

	public void setCnstCd(String cnstCd) {
		this.cnstCd = cnstCd;
	}

	public String getCnstUser() {
		return cnstUser;
	}

	public void setCnstUser(String cnstUser) {
		this.cnstUser = cnstUser;
	}

	public String getCnstDesc() {
		return cnstDesc;
	}

	public void setCnstDesc(String cnstDesc) {
		this.cnstDesc = cnstDesc;
	}

	public String getCnstId() {
		return cnstId;
	}

	public void setCnstId(String cnstId) {
		this.cnstId = cnstId;
	}

	public String getForeignYn() {
		return foreignYn;
	}

	public void setForeignYn(String foreignYn) {
		this.foreignYn = foreignYn;
	}
}

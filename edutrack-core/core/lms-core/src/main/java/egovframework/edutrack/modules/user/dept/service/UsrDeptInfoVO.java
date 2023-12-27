package egovframework.edutrack.modules.user.dept.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class UsrDeptInfoVO extends DefaultVO{

	private static final long serialVersionUID = -374363516904145789L;
	private String orgCd;
	private String deptCd;
	private String areaCd;
	private String deptNm;
	private String deptTypeCd;
	private String deptAddr;
	private String phoneno;
	private String faxNo;
	private Integer deptOdr;
	private String useYn;
	private String deleteYn;
	private String regNo;
	private String regNm;
	private String regDttm;
	private String modNo;
	private String modNm;
	private String modDttm;
	private String searchKey;
	private String searchValue;

	//-- excel upload용으로 추가
	private String  areaNm;
	private String  lineNo;
	private String  errorCode;
	private String  isUseable = "N";
	private String fileName;
	private String filePath;
	
	private String deptMngNo;
	private String deptMngNm;
	private String regNum;
	private String deptEngNm;
	private String expStartDate;
	private String expEndDate;
	private String ceoNm;
	private int staffCnt;
	private String bsnsTypeCd;
	private String bsnsCndtn;
	private String deptPostNo;
	private String deptAddr1;
	private String deptAddr2;
	private String deptEmail;
	private String infoMngNm;
	private String storeRegNo;
	private String partner;
	private Integer bsnsLcnsFileSn;

	//이미지 관련
	private SysFileVO bsnsLcnsFile;
//	private SysFileVO usrPhotoFile2;
//	private SysFileVO usrPhotoFile3;
	
	private String  searchMode;
	private String crsCd;
	private String sbjCd;
	
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getSbjCd() {
		return sbjCd;
	}
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getDeptTypeCd() {
		return deptTypeCd;
	}
	public void setDeptTypeCd(String deptTypeCd) {
		this.deptTypeCd = deptTypeCd;
	}
	public String getDeptAddr() {
		return deptAddr;
	}
	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Integer getDeptOdr() {
		return deptOdr;
	}
	public void setDeptOdr(Integer deptOdr) {
		this.deptOdr = deptOdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getModNm() {
		return modNm;
	}
	public void setModNm(String modNm) {
		this.modNm = modNm;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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
	public String getIsUseable() {
		return isUseable;
	}
	public void setIsUseable(String isUseable) {
		this.isUseable = isUseable;
	}
	public String getAreaNm() {
		return areaNm;
	}
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}	

	public String getDeptMngNo() {
		return deptMngNo;
	}
	public void setDeptMngNo(String deptMngNo) {
		this.deptMngNo = deptMngNo;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getDeptEngNm() {
		return deptEngNm;
	}
	public void setDeptEngNm(String deptEngNm) {
		this.deptEngNm = deptEngNm;
	}
	public String getExpStartDate() {
		return expStartDate;
	}
	public void setExpStartDate(String expStartDate) {
		this.expStartDate = expStartDate;
	}
	public String getExpEndDate() {
		return expEndDate;
	}
	public void setExpEndDate(String expEndDate) {
		this.expEndDate = expEndDate;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public int getStaffCnt() {
		return staffCnt;
	}
	public void setStaffCnt(int staffCnt) {
		this.staffCnt = staffCnt;
	}
	public String getBsnsTypeCd() {
		return bsnsTypeCd;
	}
	public void setBsnsTypeCd(String bsnsTypeCd) {
		this.bsnsTypeCd = bsnsTypeCd;
	}
	public String getBsnsCndtn() {
		return bsnsCndtn;
	}
	public void setBsnsCndtn(String bsnsCndtn) {
		this.bsnsCndtn = bsnsCndtn;
	}
	public String getDeptPostNo() {
		return deptPostNo;
	}
	public void setDeptPostNo(String deptPostNo) {
		this.deptPostNo = deptPostNo;
	}
	public String getDeptAddr1() {
		return deptAddr1;
	}
	public void setDeptAddr1(String deptAddr1) {
		this.deptAddr1 = deptAddr1;
	}
	public String getDeptAddr2() {
		return deptAddr2;
	}
	public void setDeptAddr2(String deptAddr2) {
		this.deptAddr2 = deptAddr2;
	}
	public String getDeptEmail() {
		return deptEmail;
	}
	public void setDeptEmail(String deptEmail) {
		this.deptEmail = deptEmail;
	}
	public String getInfoMngNm() {
		return infoMngNm;
	}
	public void setInfoMngNm(String infoMngNm) {
		this.infoMngNm = infoMngNm;
	}
	public String getStoreRegNo() {
		return storeRegNo;
	}
	public void setStoreRegNo(String storeRegNo) {
		this.storeRegNo = storeRegNo;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	
	//사업자등록증 관련
	public Integer getBsnsLcnsFileSn() {
		if(ValidationUtils.isNull(this.bsnsLcnsFileSn))
		return this.getBsnsLcnsFile().getFileSn();
		else return this.bsnsLcnsFileSn;
	}
	public void setBsnsLcnsFileSn(Integer bsnsLcnsFileSn) {
		this.getBsnsLcnsFile().setFileSn(bsnsLcnsFileSn);
	}
	
	public SysFileVO getBsnsLcnsFile() {
		if (this.bsnsLcnsFile == null)
			this.bsnsLcnsFile = new SysFileVO();
		return this.bsnsLcnsFile;
	}
	public void setBsnsLcnsFile(SysFileVO bsnsLcnsFile) {
		this.bsnsLcnsFile = bsnsLcnsFile;
	}
	
	public String getBsnsLcnsFileJson(){
		return SysFileVOUtil.getJson(this.getBsnsLcnsFile(), false);
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
	public String getDeptMngNm() {
		return deptMngNm;
	}
	public void setDeptMngNm(String deptMngNm) {
		this.deptMngNm = deptMngNm;
	}	
	
	
//	public SysFileVO getUsrPhotoFile2() {
//		if (this.usrPhotoFile2 == null)
//			this.usrPhotoFile2 = new SysFileVO();
//		return this.usrPhotoFile2;
//	}
//	public void setUsrPhotoFile2(SysFileVO usrPhotoFile2) {
//		this.usrPhotoFile2 = usrPhotoFile2;
//	}
//	
//	
//	
//	
//	public SysFileVO getUsrPhotoFile3() {
//		if (this.usrPhotoFile3 == null)
//			this.usrPhotoFile3 = new SysFileVO();
//		return this.usrPhotoFile3;
//	}
//	public void setUsrPhotoFile3(SysFileVO usrPhotoFile3) {
//		this.usrPhotoFile3 = usrPhotoFile3;
//	}
}

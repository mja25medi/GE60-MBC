package egovframework.edutrack.modules.board.bbs.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class BrdBbsInfoVO extends DefaultVO {

	private static final long serialVersionUID = 4049399949561983404L;

	private String  orgCd;
	private String  orgNm;
    private String  bbsCd;
    private String  bbsNm;
    private String  bbsDesc;
    private String  bbsTypeCd;
    private String  bbsTypeNm;
    private String  detlViewCd;
    private String  detlViewNm;
    //private Integer mainImgFileSn;
    private String  writeUseYn		= "Y";
    private String  writeUseYnNm;
    private String  cmntUseYn		= "Y";
    private String  cmntUseYnNm;
    private String  ansrUseYn		= "Y";
    private String  ansrUseYnNm;
    private String  atchUseYn		= "Y";
    private String  atchUseYnNm;
    private Integer listViewCnt		= 15;
    private String  editorUseYn		= "N";
    private String  mobileUseYn		= "N";
    private String  dfltYn			= "N";
    private String  useYn			= "Y";
    private String  menuCd;
    private String  isNewAtcl		= "N";
    private String  lockUseYn		=  "N";
    
    private String  autoMakeYn;
    
    private SysFileVO mainImgFile;

    private Integer atclCnt;
    private Integer cmntCnt;


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
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getBbsNm() {
		return bbsNm;
	}
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
	public String getBbsDesc() {
		return bbsDesc;
	}
	public void setBbsDesc(String bbsDesc) {
		this.bbsDesc = bbsDesc;
	}
	public String getBbsTypeCd() {
		return bbsTypeCd;
	}
	public void setBbsTypeCd(String bbsTypeCd) {
		this.bbsTypeCd = bbsTypeCd;
	}
	public String getBbsTypeNm() {
		return bbsTypeNm;
	}
	public void setBbsTypeNm(String bbsTypeNm) {
		this.bbsTypeNm = bbsTypeNm;
	}
	public String getDetlViewCd() {
		return detlViewCd;
	}
	public void setDetlViewCd(String detlViewCd) {
		this.detlViewCd = detlViewCd;
	}
	public String getDetlViewNm() {
		return detlViewNm;
	}
	public void setDetlViewNm(String detlViewNm) {
		this.detlViewNm = detlViewNm;
	}
	public String getWriteUseYn() {
		return writeUseYn;
	}
	public void setWriteUseYn(String writeUseYn) {
		this.writeUseYn = writeUseYn;
	}
	public String getWriteUseYnNm() {
		return writeUseYnNm;
	}
	public void setWriteUseYnNm(String writeUseYnNm) {
		this.writeUseYnNm = writeUseYnNm;
	}
	public String getCmntUseYn() {
		return cmntUseYn;
	}
	public void setCmntUseYn(String cmntUseYn) {
		this.cmntUseYn = cmntUseYn;
	}
	public String getCmntUseYnNm() {
		return cmntUseYnNm;
	}
	public void setCmntUseYnNm(String cmntUseYnNm) {
		this.cmntUseYnNm = cmntUseYnNm;
	}
	public String getAnsrUseYn() {
		return ansrUseYn;
	}
	public void setAnsrUseYn(String ansrUseYn) {
		this.ansrUseYn = ansrUseYn;
	}
	public String getAnsrUseYnNm() {
		return ansrUseYnNm;
	}
	public void setAnsrUseYnNm(String ansrUseYnNm) {
		this.ansrUseYnNm = ansrUseYnNm;
	}
	public String getAtchUseYn() {
		return atchUseYn;
	}
	public void setAtchUseYn(String atchUseYn) {
		this.atchUseYn = atchUseYn;
	}
	public String getAtchUseYnNm() {
		return atchUseYnNm;
	}
	public void setAtchUseYnNm(String atchUseYnNm) {
		this.atchUseYnNm = atchUseYnNm;
	}
	public Integer getListViewCnt() {
		return listViewCnt;
	}
	public void setListViewCnt(Integer listViewCnt) {
		this.listViewCnt = listViewCnt;
	}
	public String getEditorUseYn() {
		return editorUseYn;
	}
	public void setEditorUseYn(String editorUseYn) {
		this.editorUseYn = editorUseYn;
	}
	public String getMobileUseYn() {
		return mobileUseYn;
	}
	public void setMobileUseYn(String mobileUseYn) {
		this.mobileUseYn = mobileUseYn;
	}
	public Integer getAtclCnt() {
		return atclCnt;
	}
	public void setAtclCnt(Integer atclCnt) {
		this.atclCnt = atclCnt;
	}
	public Integer getCmntCnt() {
		return cmntCnt;
	}
	public void setCmntCnt(Integer cmntCnt) {
		this.cmntCnt = cmntCnt;
	}
	public String getDfltYn() {
		return dfltYn;
	}
	public void setDfltYn(String dfltYn) {
		this.dfltYn = dfltYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getIsNewAtcl() {
		return isNewAtcl;
	}
	public void setIsNewAtcl(String isNewAtcl) {
		this.isNewAtcl = isNewAtcl;
	}

	//-- 파일 핸들링 : thumbFile
	public Integer getMainImgFileSn() {
		return this.getMainImgFile().getFileSn();
	}
	public void setMainImgFileSn(Integer mainImgFileSn) {
		this.getMainImgFile().setFileSn(mainImgFileSn);
	}
	public SysFileVO getMainImgFile() {
		if (this.mainImgFile == null)
			this.mainImgFile = new SysFileVO();
		return this.mainImgFile;
	}
	public void setMainImgFile(SysFileVO mainImgFile) {
		this.mainImgFile = mainImgFile;
	}

	public String getMainImgFileJson() {
		return SysFileVOUtil.getJson(this.getMainImgFile(), false);
	}
	public String getLockUseYn() {
		return lockUseYn;
	}
	public void setLockUseYn(String lockUseYn) {
		this.lockUseYn = lockUseYn;
	}
	public String getAutoMakeYn() {
		return autoMakeYn;
	}
	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
}

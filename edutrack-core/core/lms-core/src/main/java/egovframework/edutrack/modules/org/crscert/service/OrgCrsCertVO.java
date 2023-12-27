package egovframework.edutrack.modules.org.crscert.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgCrsCertVO extends DefaultVO{

	private static final long serialVersionUID = 5746304581030788478L;

	private String  orgCd;
	private Integer imgFileSn;
	private String  prnDirec;
	private Integer cpltnoX;
	private Integer cpltnoY;
	private Integer crsNmX;
	private Integer crsNmY;
	private Integer prndayX;
	private Integer prndayY;
	private String  userNmUseYn;
	private String  birthUseYn;
	private String  crsNmUseYn;
	private String  eduTmUseYn;
	private String  eduPeriodUseYn;

	private SysFileVO	imgFileVO;

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getPrnDirec() {
		return prnDirec;
	}
	public void setPrnDirec(String prnDirec) {
		this.prnDirec = prnDirec;
	}
	public Integer getCpltnoX() {
		return cpltnoX;
	}
	public void setCpltnoX(Integer cpltnoX) {
		this.cpltnoX = cpltnoX;
	}
	public Integer getCpltnoY() {
		return cpltnoY;
	}
	public void setCpltnoY(Integer cpltnoY) {
		this.cpltnoY = cpltnoY;
	}
	public Integer getCrsNmX() {
		return crsNmX;
	}
	public void setCrsNmX(Integer crsNmX) {
		this.crsNmX = crsNmX;
	}
	public Integer getCrsNmY() {
		return crsNmY;
	}
	public void setCrsNmY(Integer crsNmY) {
		this.crsNmY = crsNmY;
	}
	public Integer getPrndayX() {
		return prndayX;
	}
	public void setPrndayX(Integer prndayX) {
		this.prndayX = prndayX;
	}
	public Integer getPrndayY() {
		return prndayY;
	}
	public void setPrndayY(Integer prndayY) {
		this.prndayY = prndayY;
	}

	//-- 첨부 이미지 관련 처리
	public SysFileVO getImgFileVO() {
		if (this.imgFileVO == null)
			this.imgFileVO = new SysFileVO();
		return imgFileVO;
	}
	public void setImgFileVO(SysFileVO imgFileVO) {
		this.imgFileVO = imgFileVO;
	}
	public Integer getImgFileSn() {
		return this.getImgFileVO().getFileSn();
	}
	public void setImgFileSn(Integer imgFileSn) {
		this.imgFileVO = new SysFileVO(imgFileSn);
	}
	public String getImgFileJson() {
		return SysFileVOUtil.getJson(this.getImgFileVO(), false);
	}
	public String getUserNmUseYn() {
		return userNmUseYn;
	}
	public void setUserNmUseYn(String userNmUseYn) {
		this.userNmUseYn = userNmUseYn;
	}
	public String getBirthUseYn() {
		return birthUseYn;
	}
	public void setBirthUseYn(String birthUseYn) {
		this.birthUseYn = birthUseYn;
	}
	public String getCrsNmUseYn() {
		return crsNmUseYn;
	}
	public void setCrsNmUseYn(String crsNmUseYn) {
		this.crsNmUseYn = crsNmUseYn;
	}
	public String getEduTmUseYn() {
		return eduTmUseYn;
	}
	public void setEduTmUseYn(String eduTmUseYn) {
		this.eduTmUseYn = eduTmUseYn;
	}
	public String getEduPeriodUseYn() {
		return eduPeriodUseYn;
	}
	public void setEduPeriodUseYn(String eduPeriodUseYn) {
		this.eduPeriodUseYn = eduPeriodUseYn;
	}
}

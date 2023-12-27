package egovframework.edutrack.modules.etc.banner.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class EtcBnnrVO extends DefaultVO {

	private static final long serialVersionUID = 6882276585882225089L;

	private Integer	bnnrSn;
	private String  orgCd;
	private String	bnnrNm;
	private String	bnnrUrl;
	private String  bnnrPosCd;
	private String  bnnrPosNm;
	private Integer	bnnrOdr;
	private String	bnnrTrgt = "_blank";
	private Integer	bnnrImgSn;
	private String  bnnrDesc;
	private String  openYn;

	private String	bnnrSns;

	private SysFileVO	bnnrFileVO;

	public EtcBnnrVO() {

	}

	public Integer getBnnrSn() {
		return bnnrSn;
	}

	public void setBnnrSn(Integer bnnrSn) {
		this.bnnrSn = bnnrSn;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getBnnrNm() {
		return bnnrNm;
	}

	public void setBnnrNm(String bnnrNm) {
		this.bnnrNm = bnnrNm;
	}

	public String getBnnrUrl() {
		return bnnrUrl;
	}

	public void setBnnrUrl(String bnnrUrl) {
		this.bnnrUrl = bnnrUrl;
	}

	public String getBnnrPosCd() {
		return bnnrPosCd;
	}

	public void setBnnrPosCd(String bnnrPosCd) {
		this.bnnrPosCd = bnnrPosCd;
	}

	public String getBnnrPosNm() {
		return bnnrPosNm;
	}

	public void setBnnrPosNm(String bnnrPosNm) {
		this.bnnrPosNm = bnnrPosNm;
	}

	public Integer getBnnrOdr() {
		return bnnrOdr;
	}

	public void setBnnrOdr(Integer bnnrOdr) {
		this.bnnrOdr = bnnrOdr;
	}

	public String getBnnrTrgt() {
		return bnnrTrgt;
	}

	public void setBnnrTrgt(String bnnrTrgt) {
		this.bnnrTrgt = bnnrTrgt;
	}

	public String getBnnrDesc() {
		return bnnrDesc;
	}

	public void setBnnrDesc(String bnnrDesc) {
		this.bnnrDesc = bnnrDesc;
	}

	public String getBnnrSns() {
		return bnnrSns;
	}

	public void setBnnrSns(String bnnrSns) {
		this.bnnrSns = bnnrSns;
	}

	public Integer getBnnrImgSn() {
		return bnnrImgSn;
	}

	public void setBnnrImgSn(Integer bnnrImgSn) {
		this.bnnrImgSn = bnnrImgSn;
	}

	public String getOpenYn() {
		return openYn;
	}

	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public SysFileVO getbnnrFileVO() {
		if (this.bnnrFileVO == null)
			this.bnnrFileVO = new SysFileVO();
		return bnnrFileVO;
	}

	public void setbnnrFileVO(SysFileVO bnnrFileVO) {
		this.bnnrFileVO = bnnrFileVO;
	}

	public Integer getBnnrFileSn() {
		return this.getbnnrFileVO().getFileSn();
	}

	public void setBnnrFileSn(Integer bnnrFileSn) {
		this.bnnrFileVO = new SysFileVO(bnnrFileSn);
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getBnnrFileJson() {
		return SysFileVOUtil.getJson(this.getbnnrFileVO(), false);
	}
}

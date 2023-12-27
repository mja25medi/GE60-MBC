package egovframework.edutrack.modules.org.image.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgImgInfoVO  extends DefaultVO {

	private static final long serialVersionUID = 1015491435217112595L;

	private String  orgCd;
	private Integer imgSn;
	private String  imgTypeCd;
	private String  bkgrImgNm;
	private String  bkgrFileUrl;
	private String  descImgNm;
	private String  descFileUrl;
	private String  connUrl;
	private String  connTrgt;
	private Integer imgOdr;

	private SysFileVO	bkgrFileVO;
	private SysFileVO	descFileVO;
	private SysFileVO	connFileVO;
	private String imgSns;
	private String imgTitle;


	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public Integer getImgSn() {
		return imgSn;
	}
	public void setImgSn(Integer imgSn) {
		this.imgSn = imgSn;
	}
	public String getImgTypeCd() {
		return imgTypeCd;
	}
	public void setImgTypeCd(String imgTypeCd) {
		this.imgTypeCd = imgTypeCd;
	}
	public String getBkgrImgNm() {
		return bkgrImgNm;
	}
	public void setBkgrImgNm(String bkgrImgNm) {
		this.bkgrImgNm = bkgrImgNm;
	}
	public String getDescImgNm() {
		return descImgNm;
	}
	public void setDescImgNm(String descImgNm) {
		this.descImgNm = descImgNm;
	}
	public String getConnUrl() {
		return connUrl;
	}
	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}
	public String getConnTrgt() {
		return connTrgt;
	}
	public void setConnTrgt(String connTrgt) {
		this.connTrgt = connTrgt;
	}
	public Integer getImgOdr() {
		return imgOdr;
	}
	public void setImgOdr(Integer imgOdr) {
		this.imgOdr = imgOdr;
	}
	public String getBkgrFileUrl() {
		return bkgrFileUrl;
	}
	public void setBkgrFileUrl(String bkgrFileUrl) {
		this.bkgrFileUrl = bkgrFileUrl;
	}
	public String getDescFileUrl() {
		return descFileUrl;
	}
	public void setDescFileUrl(String descFileUrl) {
		this.descFileUrl = descFileUrl;
	}
	public String getImgSns() {
		return imgSns;
	}
	public void setImgSns(String imgSns) {
		this.imgSns = imgSns;
	}
	public String getImgTitle() {
		return imgTitle;
	}
	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}
	
	
	//-- 파일 관련 처리
	public SysFileVO getBkgrFileVO() {
		if (this.bkgrFileVO == null)
			this.bkgrFileVO = new SysFileVO();
		return bkgrFileVO;
	}
	public void setBkgrFileVO(SysFileVO bkgrFileVO) {
		this.bkgrFileVO = bkgrFileVO;
	}
	public Integer getBkgrFileSn() {
		return this.getBkgrFileVO().getFileSn();
	}
	public void setBkgrFileSn(Integer bkgrFileSn) {
		this.bkgrFileVO = new SysFileVO(bkgrFileSn);
	}
	public String getBkgrFileJson() {
		return SysFileVOUtil.getJson(this.getBkgrFileVO(), false);
	}

	public SysFileVO getDescFileVO() {
		if (this.descFileVO == null)
			this.descFileVO = new SysFileVO();
		return descFileVO;
	}
	public void setDescFileVO(SysFileVO descFileVO) {
		this.descFileVO = descFileVO;
	}
	public Integer getDescFileSn() {
		return this.getDescFileVO().getFileSn();
	}
	public void setDescFileSn(Integer descFileSn) {
		this.descFileVO = new SysFileVO(descFileSn);
	}
	public String getDescFileJson() {
		return SysFileVOUtil.getJson(this.getDescFileVO(), false);
	}

	public SysFileVO getConnFileVO() {
		if (this.connFileVO == null)
			this.connFileVO = new SysFileVO();
		return connFileVO;
	}
	public void setConnFileVO(SysFileVO connFileVO) {
		this.connFileVO = connFileVO;
	}
	public Integer getConnFileSn() {
		return this.getConnFileVO().getFileSn();
	}
	public void setConnFileSn(Integer connFileSn) {
		this.connFileVO = new SysFileVO(connFileSn);
	}
	public String getConnFileJson() {
		return SysFileVOUtil.getJson(this.getConnFileVO(), false);
	}
}

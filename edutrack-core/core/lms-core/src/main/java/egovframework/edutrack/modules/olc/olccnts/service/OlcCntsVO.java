package egovframework.edutrack.modules.olc.olccnts.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OlcCntsVO extends DefaultVO {

	private static final long serialVersionUID = 816677361232769527L;
	private String  cntsCd;
	private String  orgCd;
	private String  userNo;
	private String  cartrgCd;
	private String  cntsNm;
	private Integer cntsOdr;
	private String  cntsDiv;
	private String  cntsLoc;
	private String  cntsCts;
	private String  cntsFilePath;

	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록

	public String getCntsCd() {
		return cntsCd;
	}
	public void setCntsCd(String cntsCd) {
		this.cntsCd = cntsCd;
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
	public String getCartrgCd() {
		return cartrgCd;
	}
	public void setCartrgCd(String cartrgCd) {
		this.cartrgCd = cartrgCd;
	}
	public String getCntsNm() {
		return cntsNm;
	}
	public void setCntsNm(String cntsNm) {
		this.cntsNm = cntsNm;
	}
	public Integer getCntsOdr() {
		return cntsOdr;
	}
	public void setCntsOdr(Integer cntsOdr) {
		this.cntsOdr = cntsOdr;
	}
	public String getCntsDiv() {
		return cntsDiv;
	}
	public void setCntsDiv(String cntsDiv) {
		this.cntsDiv = cntsDiv;
	}
	public String getCntsLoc() {
		return cntsLoc;
	}
	public void setCntsLoc(String cntsLoc) {
		this.cntsLoc = cntsLoc;
	}
	public String getCntsCts() {
		return cntsCts;
	}
	public void setCntsCts(String cntsCts) {
		this.cntsCts = cntsCts;
	}

	//-- 첨부 이미지 관련 처리
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}
	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}
	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}




	//-- 첨부 파일 관련 처리
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}
	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}
	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}
	public String getCntsFilePath() {
		return cntsFilePath;
	}
	public void setCntsFilePath(String cntsFilePath) {
		this.cntsFilePath = cntsFilePath;
	}
}

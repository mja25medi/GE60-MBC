package egovframework.edutrack.modules.library.cnts.olc.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class ClibOlcPageVO extends DefaultVO {

	private static final long serialVersionUID = -7565953698405478050L;
	private String  cntsCd;
	private String  pageCd;
	private String  pageNm;
	private Integer pageOdr;
	private String  pageDiv;
	private String  pageCts;
	private String  filePath;
	private String  pageDesc;

	private String  orgCd;
	private String  userNo;
	private String  shareCntsCd;
	private String  sharePageCd;

	private String  pageCds;

	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
	private List<SysFileVO>	attachFiles;		// 첨부파일 목록

	private int returnResult = 0; // 처리 결과
	private String returnMessage; // 결과 메시지

	public String getCntsCd() {
		return cntsCd;
	}
	public void setCntsCd(String cntsCd) {
		this.cntsCd = cntsCd;
	}
	public String getPageCd() {
		return pageCd;
	}
	public void setPageCd(String pageCd) {
		this.pageCd = pageCd;
	}
	public String getPageNm() {
		return pageNm;
	}
	public void setPageNm(String pageNm) {
		this.pageNm = pageNm;
	}
	public Integer getPageOdr() {
		return pageOdr;
	}
	public void setPageOdr(Integer pageOdr) {
		this.pageOdr = pageOdr;
	}
	public String getPageDiv() {
		return pageDiv;
	}
	public void setPageDiv(String pageDiv) {
		this.pageDiv = pageDiv;
	}
	public String getPageCts() {
		return pageCts;
	}
	public void setPageCts(String pageCts) {
		this.pageCts = pageCts;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
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
	public String getSharePageCd() {
		return sharePageCd;
	}
	public void setSharePageCd(String sharePageCd) {
		this.sharePageCd = sharePageCd;
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
	public String getShareCntsCd() {
		return shareCntsCd;
	}
	public void setShareCntsCd(String shareCntsCd) {
		this.shareCntsCd = shareCntsCd;
	}
	public String getPageCds() {
		return pageCds;
	}
	public void setPageCds(String pageCds) {
		this.pageCds = pageCds;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public int getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(int returnResult) {
		this.returnResult = returnResult;
	}

}

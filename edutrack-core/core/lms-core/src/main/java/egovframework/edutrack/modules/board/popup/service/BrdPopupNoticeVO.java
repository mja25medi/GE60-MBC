package egovframework.edutrack.modules.board.popup.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class BrdPopupNoticeVO extends DefaultVO{

	private static final long serialVersionUID = 4932544152796087248L;
	
	private Integer popupSn;
	private String orgCd;
	private String title;
	private String cts;
	private String bkgrImg;
	private String popupTypeCd;
	private Integer xSize = 300;
	private Integer ySize = 500;
	private Integer xPos = 0;
	private Integer yPos = 0;
	private String scrollYn;
	private String startDttm;
	private String startHour = "00";
	private String startMin = "00";
	private String endDttm;
	private String endHour = "23";
	private String endMin = "59";	
	private String useYnWww;
	private String useYnZine;
	private String editorYn;
	private String regNm;
	private String modNm;
	private String searchDate;
	
	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
//	private List<SysFileVO>	attachFiles;		// 첨부파일 목록
//	private List<SysFileVO>	attachPhotos;
	
	public Integer getPopupSn() {
		return popupSn;
	}
	public void setPopupSn(Integer popupSn) {
		this.popupSn = popupSn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public String getBkgrImg() {
		return bkgrImg;
	}
	public void setBkgrImg(String bkgrImg) {
		this.bkgrImg = bkgrImg;
	}
	public String getPopupTypeCd() {
		return popupTypeCd;
	}
	public void setPopupTypeCd(String popupTypeCd) {
		this.popupTypeCd = popupTypeCd;
	}
	public Integer getxSize() {
		return xSize;
	}
	public void setxSize(Integer xSize) {
		this.xSize = xSize;
	}
	public Integer getySize() {
		return ySize;
	}
	public void setySize(Integer ySize) {
		this.ySize = ySize;
	}
	public Integer getxPos() {
		return xPos;
	}
	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}
	public Integer getyPos() {
		return yPos;
	}
	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}
	public String getScrollYn() {
		return scrollYn;
	}
	public void setScrollYn(String scrollYn) {
		this.scrollYn = scrollYn;
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
	public String getUseYnWww() {
		return useYnWww;
	}
	public void setUseYnWww(String useYnWww) {
		this.useYnWww = useYnWww;
	}
	public String getUseYnZine() {
		return useYnZine;
	}
	public void setUseYnZine(String useYnZine) {
		this.useYnZine = useYnZine;
	}
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
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
	
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

//	//-- 첨부 파일 관련 처리
//	public String getAttachFileSns() {
//		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
//	}
//	public void setAttachFileSns(String attachFileSns) {
//		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
//	}
//	public List<SysFileVO> getAttachFiles() {
//		if (this.attachFiles == null)
//			this.attachFiles = new ArrayList<SysFileVO>();
//		return this.attachFiles;
//	}
//	public void setAttachFiles(List<SysFileVO> attachFiles) {
//		this.attachFiles = attachFiles;
//	}
//	public String getAttachFilesJson() {
//		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
//	}
//
//	//-- 첨부 사진 관련 처리
//	public String getAttachPhotoSns() {
//		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachPhotos());
//	}
//	public void setAttachPhotoSns(String attachPhotoSns) {
//		this.setAttachPhotos(SysFileVOUtil.convertStringToSysFileVOList(attachPhotoSns));
//	}
//	public List<SysFileVO> getAttachPhotos() {
//		if (this.attachPhotos == null)
//			this.attachPhotos = new ArrayList<SysFileVO>();
//		return this.attachPhotos;
//	}
//	public void setAttachPhotos(List<SysFileVO> attachPhotos) {
//		this.attachPhotos = attachPhotos;
//	}
//	public String getAttachPhotosJson() {
//		return SysFileVOUtil.getJson(this.getAttachPhotos(), false);
//	}
	

}

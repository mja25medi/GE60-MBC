package egovframework.edutrack.modules.org.page.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgPageVO extends DefaultVO{

	private static final long serialVersionUID = 6546163634651782623L;
	private String		orgCd;
	private String		orgNm;
	private String		pageCd;
	private String		pageNm;
	private String		pageCts;
	private Integer		pageOdr;
	private String		useYn				= "Y";
	private String      dfltYn				= "N";
	private String		menuCd;
	private String      editorYn;

	private List<SysFileVO>		attachImages;									// 첨부사진 목록

	/**
	 * 생성자
	 */
	public OrgPageVO() {
		//
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getPageCd() {
		return pageCd;
	}

	public void setPageCd(String pageCd) {
		this.pageCd = pageCd;
	}

	public Integer getPageOdr() {
		return pageOdr;
	}

	public void setPageOdr(Integer pageOdr) {
		this.pageOdr = pageOdr;
	}

	public String getUseYn() {
		return this.useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getPageCts() {
		return this.pageCts;
	}

	public void setPageCts(String pageCts) {
		this.pageCts = pageCts;
	}

	public String getPageNm() {
		return this.pageNm;
	}

	public void setPageNm(String pageNm) {
		this.pageNm = pageNm;
	}

	public String getDfltYn() {
		return dfltYn;
	}

	public void setDfltYn(String dfltYn) {
		this.dfltYn = dfltYn;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}

	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}
}

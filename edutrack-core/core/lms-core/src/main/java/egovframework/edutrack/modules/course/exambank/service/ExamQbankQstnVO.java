package egovframework.edutrack.modules.course.exambank.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


/**
 * 시험 문제은행 문제 VO
 */
public class ExamQbankQstnVO extends DefaultVO {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 8045214355738984599L;
	private String	sbjCd;
	private Integer	qstnSn;
	private String	ctgrCd;
	private String	qstnType;
	private String	qstnTypeNm;
	private String	qstnCts;
	private String	view1;
	private String	view2;
	private String	view3;
	private String 	view4;
	private String	view5;
	private String 	rgtAnsr;
	private String	multiRgtAnsr;
	private String	qstnExpl;
	private String 	title;
	private Integer	qstnNo;
	private String ctgrNm;
	private String crsNm;
	private String parCtgrCd;
	private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
	private String useYn;
	private String orgCd;

    //-- excel upload용으로 추가
	private String  lineNo;
  	private String  errorCode;
	/**
	 * @return the sbjCd
	 */
	public String getSbjCd() {
		return sbjCd;
	}
	/**
	 * @param sbjCd the sbjCd to set
	 */
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	/**
	 * @return the qstnSn
	 */
	public Integer getQstnSn() {
		return qstnSn;
	}
	/**
	 * @param qstnSn the qstnSn to set
	 */
	public void setQstnSn(Integer qstnSn) {
		this.qstnSn = qstnSn;
	}
	/**
	 * @return the ctgrCd
	 */
	public String getCtgrCd() {
		return ctgrCd;
	}
	/**
	 * @param ctgrCd the ctgrCd to set
	 */
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	/**
	 * @return the qstnType
	 */
	public String getQstnType() {
		return qstnType;
	}
	/**
	 * @param qstnType the qstnType to set
	 */
	public void setQstnType(String qstnType) {
		this.qstnType = qstnType;
	}
	/**
	 * @return the qstnCts
	 */
	public String getQstnCts() {
		return qstnCts;
	}
	/**
	 * @param qstnCts the qstnCts to set
	 */
	public void setQstnCts(String qstnCts) {
		this.qstnCts = qstnCts;
	}
	/**
	 * @return the view1
	 */
	public String getView1() {
		return view1;
	}
	/**
	 * @param view1 the view1 to set
	 */
	public void setView1(String view1) {
		this.view1 = view1;
	}
	/**
	 * @return the view2
	 */
	public String getView2() {
		return view2;
	}
	/**
	 * @param view2 the view2 to set
	 */
	public void setView2(String view2) {
		this.view2 = view2;
	}
	/**
	 * @return the view3
	 */
	public String getView3() {
		return view3;
	}
	/**
	 * @param view3 the view3 to set
	 */
	public void setView3(String view3) {
		this.view3 = view3;
	}
	/**
	 * @return the view4
	 */
	public String getView4() {
		return view4;
	}
	/**
	 * @param view4 the view4 to set
	 */
	public void setView4(String view4) {
		this.view4 = view4;
	}
	/**
	 * @return the view5
	 */
	public String getView5() {
		return view5;
	}
	/**
	 * @param view5 the view5 to set
	 */
	public void setView5(String view5) {
		this.view5 = view5;
	}
	/**
	 * @return the rgtAnsr
	 */
	public String getRgtAnsr() {
		return rgtAnsr;
	}
	/**
	 * @param rgtAnsr the rgtAnsr to set
	 */
	public void setRgtAnsr(String rgtAnsr) {
		this.rgtAnsr = rgtAnsr;
	}
	/**
	 * @return the multiRgtAnsr
	 */
	public String getMultiRgtAnsr() {
		return multiRgtAnsr;
	}
	/**
	 * @param multiRgtAnsr the multiRgtAnsr to set
	 */
	public void setMultiRgtAnsr(String multiRgtAnsr) {
		this.multiRgtAnsr = multiRgtAnsr;
	}
	/**
	 * @return the qstnExpl
	 */
	public String getQstnExpl() {
		return qstnExpl;
	}
	/**
	 * @param qstnExpl the qstnExpl to set
	 */
	public void setQstnExpl(String qstnExpl) {
		this.qstnExpl = qstnExpl;
	}

	/**
	 * @return the qstnTypeNm
	 */
	public String getQstnTypeNm() {
		return qstnTypeNm;
	}
	/**
	 * @param qstnTypeNm the qstnTypeNm to set
	 */
	public void setQstnTypeNm(String qstnTypeNm) {
		this.qstnTypeNm = qstnTypeNm;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getQstnNo() {
		return qstnNo;
	}
	public void setQstnNo(Integer qstnNo) {
		this.qstnNo = qstnNo;
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
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getParCtgrCd() {
		return parCtgrCd;
	}
	public void setParCtgrCd(String parCtgrCd) {
		this.parCtgrCd = parCtgrCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	
}
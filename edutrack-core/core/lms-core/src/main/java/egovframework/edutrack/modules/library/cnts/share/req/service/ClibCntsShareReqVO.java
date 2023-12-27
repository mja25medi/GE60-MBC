package egovframework.edutrack.modules.library.cnts.share.req.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ClibCntsShareReqVO extends DefaultVO{

	private static final long serialVersionUID = -3883880276468162718L;

	private Integer reqSn;
	private String  cntsCd;
	private String  cntsTypeCd;
	private String  shareDivCd;
	private String  shareStsCd;
	private String  reqDttm;
	private String  arrvDttm;
	private String  cclCd;


	public Integer getReqSn() {
		return reqSn;
	}
	public void setReqSn(Integer reqSn) {
		this.reqSn = reqSn;
	}
	public String getCntsCd() {
		return cntsCd;
	}
	public void setCntsCd(String cntsCd) {
		this.cntsCd = cntsCd;
	}
	public String getCntsTypeCd() {
		return cntsTypeCd;
	}
	public void setCntsTypeCd(String cntsTypeCd) {
		this.cntsTypeCd = cntsTypeCd;
	}
	public String getShareDivCd() {
		return shareDivCd;
	}
	public void setShareDivCd(String shareDivCd) {
		this.shareDivCd = shareDivCd;
	}
	public String getShareStsCd() {
		return shareStsCd;
	}
	public void setShareStsCd(String shareStsCd) {
		this.shareStsCd = shareStsCd;
	}
	public String getReqDttm() {
		return reqDttm;
	}
	public void setReqDttm(String reqDttm) {
		this.reqDttm = reqDttm;
	}
	public String getArrvDttm() {
		return arrvDttm;
	}
	public void setArrvDttm(String arrvDttm) {
		this.arrvDttm = arrvDttm;
	}
	public String getCclCd() {
		return cclCd;
	}
	public void setCclCd(String cclCd) {
		this.cclCd = cclCd;
	}



}

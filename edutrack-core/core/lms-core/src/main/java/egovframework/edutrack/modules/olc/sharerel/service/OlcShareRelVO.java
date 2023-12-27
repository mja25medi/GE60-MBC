package egovframework.edutrack.modules.olc.sharerel.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OlcShareRelVO extends DefaultVO{

	private static final long serialVersionUID = -1476423300554059314L;
	private String  ctgrCd;
	private String  ctgrNm;
	private String  cartrgCd;
	private String  cartrgNm;
	private String  orgCd;
	private String  userNo;
	private String  userNm;
	private String  useYn;
	private Integer cntsCnt;
	private String ctgrDivCd;
	private String fileSn;
	private int hits;

	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getCartrgCd() {
		return cartrgCd;
	}
	public void setCartrgCd(String cartrgCd) {
		this.cartrgCd = cartrgCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCartrgNm() {
		return cartrgNm;
	}
	public void setCartrgNm(String cartrgNm) {
		this.cartrgNm = cartrgNm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Integer getCntsCnt() {
		return cntsCnt;
	}
	public void setCntsCnt(Integer cntsCnt) {
		this.cntsCnt = cntsCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCtgrDivCd() {
		return ctgrDivCd;
	}
	public void setCtgrDivCd(String ctgrDivCd) {
		this.ctgrDivCd = ctgrDivCd;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
}

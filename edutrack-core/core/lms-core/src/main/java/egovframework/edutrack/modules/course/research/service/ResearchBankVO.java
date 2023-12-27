package egovframework.edutrack.modules.course.research.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ResearchBankVO  extends DefaultVO {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -1488061542485783858L;
	private String  orgCd;
    private int     reshSn;			//  NUMBER(9)
    private String  reshTitle;		//  NVARCHAR2(200),
    private String  reshCts;		//  NCLOB,
    private String	regYn = "N";
    private String 	regYnNm;
	private int		itemCnt ;
	private int		useCnt ; //과정에서 해당 설문을 사용하는 수
	private String   crsCreCd;//과정 코드
	private String searchValue;
	private String searchCnt;

	public ResearchBankVO(){}


	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public int getReshSn() {
		return this.reshSn;
	}
	public void setReshSn(int reshSn) {
		this.reshSn = reshSn;
	}
	public String getReshTitle() {
		return this.reshTitle;
	}
	public void setReshTitle(String reshTitle) {
		this.reshTitle = reshTitle;
	}
	public String getReshCts() {
		return this.reshCts;
	}
	public void setReshCts(String reshCts) {
		this.reshCts = reshCts;
	}
	public String getRegYn() {
		return this.regYn;
	}
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	public String getRegYnNm() {
		return this.regYnNm;
	}
	public void setRegYnNm(String regYnNm) {
		this.regYnNm = regYnNm;
	}
	public int getItemCnt() {
		return this.itemCnt;
	}
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public int getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(int useCnt) {
		this.useCnt = useCnt;
	}


	public String getCrsCreCd() {
		return crsCreCd;
	}


	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}


	public String getSearchCnt() {
		return searchCnt;
	}


	public void setSearchCnt(String searchCnt) {
		this.searchCnt = searchCnt;
	}
}

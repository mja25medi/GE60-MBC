package egovframework.edutrack.modules.org.resh.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgReshVO extends DefaultVO{

	private static final long serialVersionUID = 9080010980933522092L;
	
	private String  orgCd;
	private Integer reshSn;
	private String  useYn;
	private String  reshTitle;	
	private String  reshCts;	
	private String  startDttm;
	private String  endDttm;
	private String  reshTypeCd;
	private String  reshType;
	private String   regYn;
	private int		itemCnt ;
	private int		useCnt ; //과정에서 해당 설문을 사용하는 수
	
	private String searchValue;
	private String searchDate;
	private String searchCnt;
	
	private String joinYn;
	private String anserUseYn;
	private String sendRegDttm;
	
	private String userNo;
	private String reshCnt;
	
	public String getReshCnt() {
		return reshCnt;
	}
	public void setReshCnt(String reshCnt) {
		this.reshCnt = reshCnt;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public Integer getReshSn() {
		return reshSn;
	}
	public void setReshSn(Integer reshSn) {
		this.reshSn = reshSn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getReshTitle() {
		return reshTitle;
	}
	public void setReshTitle(String reshTitle) {
		this.reshTitle = reshTitle;
	}
	public String getReshCts() {
		return reshCts;
	}
	public void setReshCts(String reshCts) {
		this.reshCts = reshCts;
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
	public String getReshTypeCd() {
		return reshTypeCd;
	}
	public void setReshTypeCd(String reshTypeCd) {
		this.reshTypeCd = reshTypeCd;
	}
	public String getRegYn() {
		return regYn;
	}
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	public int getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}
	public int getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(int useCnt) {
		this.useCnt = useCnt;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public String getSearchCnt() {
		return searchCnt;
	}
	public void setSearchCnt(String searchCnt) {
		this.searchCnt = searchCnt;
	}
	public String getReshType() {
		return reshType;
	}
	public void setReshType(String reshType) {
		this.reshType = reshType;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getAnserUseYn() {
		return anserUseYn;
	}
	public void setAnserUseYn(String anserUseYn) {
		this.anserUseYn = anserUseYn;
	}
	public String getSendRegDttm() {
		return sendRegDttm;
	}
	public void setSendRegDttm(String sendRegDttm) {
		this.sendRegDttm = sendRegDttm;
	}
	public String getJoinYn() {
		return joinYn;
	}
	public void setJoinYn(String joinYn) {
		this.joinYn = joinYn;
	}
	
	
}

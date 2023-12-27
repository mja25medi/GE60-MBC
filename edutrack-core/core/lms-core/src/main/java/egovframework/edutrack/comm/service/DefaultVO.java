package egovframework.edutrack.comm.service;

import java.io.Serializable;

import egovframework.edutrack.Constants;

public class DefaultVO implements Serializable {

	private static final long serialVersionUID = -5656745315178291132L;
	protected String mcd;		// 메뉴코드
	protected String gubun;		// 
	protected String regNo;		// 등록자 번호
	protected String regId;		// 등록자 아이디
	protected String regDttm;	// 등록 일시
	protected String modNo;		// 수정자 번호
	protected String modDttm;	// 수정 일시
	protected String regNm;		// 등록자 이름
	protected String modNm;		// 수정자 이름

	// 검색용 프로퍼티
	protected String searchKey;
	protected String searchValue;
	protected String searchFrom;
	protected String searchTo;
	protected String sortKey = "";
	private String searchCd;

	
	private String searchSort = ""; 	//정렬파라미터
	private String searchText = "";	 	//검색어
	private String searchMenu = ""; 	//메뉴종류
	private String pageNumber = ""; 	//페이지번호
	private String moreNumber = ""; 	//더보기 페이지번호
	private String searchMore = ""; 	//더보기 페이지파라미터
	private String resFlag = ""; 		//재검색 유무
	private String movStrQuery = ""; 	//동영상 이전검색쿼리
	private String docStrQuery = ""; 	//문서 이전검색쿼리
	private String htmlStrQuery = ""; 	//HTML 이전검색쿼리
	private String eduStrQuery = ""; 	//교육 이전검색쿼리
	private String searchType = ""; 	//ajax 구분 파라미터
	private String searchDuration = "";
	
	// 리턴용 파라미터
	protected String message;
	protected String messageDetail;

	// 시스템 언어
	protected String systemLocale;
	
	// 페이징 관련 파라미터
	private int pageIndex = 1;
	private int firstIndex = 1;
	private int lastIndex = 1;
	private int listScale = Constants.LIST_SCALE;
	private int pageScale = Constants.LIST_PAGE_SCALE;
	private int curPage=1;
	
	private String goMcd;
	private String goUrl;
	private String subParam;

	public DefaultVO() {
		super();
	}

	public String getMcd() {
		return mcd;
	}

	public void setMcd(String mcd) {
		this.mcd = mcd;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getModNo() {
		return modNo;
	}

	public void setModNo(String modNo) {
		this.modNo = modNo;
	}

	public String getModDttm() {
		return modDttm;
	}

	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchFrom() {
		return searchFrom;
	}

	public void setSearchFrom(String searchFrom) {
		this.searchFrom = searchFrom;
	}

	public String getSearchTo() {
		return searchTo;
	}

	public void setSearchTo(String searchTo) {
		this.searchTo = searchTo;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	public String getSystemLocale() {
		return systemLocale;
	}

	public void setSystemLocale(String systemLocale) {
		this.systemLocale = systemLocale;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getListScale() {
		return listScale;
	}

	public void setListScale(int listScale) {
		this.listScale = listScale;
	}

	public int getPageScale() {
		return pageScale;
	}

	public void setPageScale(int pageScale) {
		this.pageScale = pageScale;
	}

	public String getGoMcd() {
		return goMcd;
	}

	public void setGoMcd(String goMcd) {
		this.goMcd = goMcd;
	}

	public String getGoUrl() {
		return goUrl;
	}

	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}

	public String getSubParam() {
		return subParam;
	}

	public void setSubParam(String subParam) {
		this.subParam = subParam;
	}

	public String getSearchSort() {
		return searchSort;
	}

	public void setSearchSort(String searchSort) {
		this.searchSort = searchSort;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSearchMenu() {
		return searchMenu;
	}

	public void setSearchMenu(String searchMenu) {
		this.searchMenu = searchMenu;
	}

	public String getResFlag() {
		return resFlag;
	}

	public void setResFlag(String resFlag) {
		this.resFlag = resFlag;
	}

	public String getMovStrQuery() {
		return movStrQuery;
	}

	public void setMovStrQuery(String movStrQuery) {
		this.movStrQuery = movStrQuery;
	}

	public String getDocStrQuery() {
		return docStrQuery;
	}

	public void setDocStrQuery(String docStrQuery) {
		this.docStrQuery = docStrQuery;
	}

	public String getHtmlStrQuery() {
		return htmlStrQuery;
	}

	public void setHtmlStrQuery(String htmlStrQuery) {
		this.htmlStrQuery = htmlStrQuery;
	}

	public String getEduStrQuery() {
		return eduStrQuery;
	}

	public void setEduStrQuery(String eduStrQuery) {
		this.eduStrQuery = eduStrQuery;
	}

	public String getSearchMore() {
		return searchMore;
	}

	public void setSearchMore(String searchMore) {
		this.searchMore = searchMore;
	}

	public String getMoreNumber() {
		return moreNumber;
	}

	public void setMoreNumber(String moreNumber) {
		this.moreNumber = moreNumber;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getSearchCd() {
		return searchCd;
	}

	public void setSearchCd(String searchCd) {
		this.searchCd = searchCd;
	}

	public String getSearchDuration() {
		return searchDuration;
	}

	public void setSearchDuration(String searchDuration) {
		this.searchDuration = searchDuration;
	}

}

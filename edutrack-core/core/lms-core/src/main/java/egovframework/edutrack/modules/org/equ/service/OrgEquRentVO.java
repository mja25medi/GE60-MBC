package egovframework.edutrack.modules.org.equ.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import egovframework.edutrack.comm.code.impl.RentStatusCode;
import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;

public class OrgEquRentVO extends DefaultVO {

	private static final long serialVersionUID = 6502687007317145747L;
	
	private String rentCd;
	private String equCd;
	private String userNo;
	private int rentCnt;
	private String rentStartDttm;
	private String rentEndDttm;
	private RentStatusCode rentSts;
	private String rentDesc;
	private String memo;
	
	private String orgCd;
	
	private List<OrgEquRentDetailVO> detailList = new ArrayList<>();
	private String itemCds;
	
	private String equNm;
	private String userNm;
	private String userId;
	private String mobileNo;
	private String email;
	
	private String homePostNo;
	private String homeAddr1;
	private String homeAddr2;

	private String parseStartDttm;
	private String parseEndDttm;
	
	public void transDetailList(String[] itemCds) {
		this.detailList = Arrays.stream(itemCds)
							.filter(this::chkCdValid)
							.map(this::genDefaultDetailVO)
							.collect(Collectors.toList());
	}
	
	private boolean chkCdValid(String cd) {
		return cd != null && cd != ""; 
	}
	
	private OrgEquRentDetailVO genDefaultDetailVO(String itemCd) {
		OrgEquRentDetailVO detail = new OrgEquRentDetailVO();
		detail.setRentCd(rentCd);
		detail.setItemCd(itemCd);
		detail.setModNo(modNo);
		detail.setRegNo(regNo);
		return detail;
	}
	
	public void parseRentDttmForCalendar() {
		if(rentStartDttm != null && rentStartDttm.length() == 14)
			parseStartDttm = DateTimeUtil.parseDttmToLocalDateTime(rentStartDttm).toString();
		if(rentEndDttm != null && rentEndDttm.length() == 14)
			parseEndDttm = DateTimeUtil.parseDttmToLocalDateTime(rentEndDttm).plusDays(1L).toString();
	}
	
	public boolean isValidRent() {
		if(rentSts == null) return false;
		return rentSts.getValidInfo();
	}
	
	public void parseRentDttmForExcel(DateTimeFormatter formatter) {
		parseStartDttm = DateTimeUtil.parseDttmToLocalDateTime(rentStartDttm).format(formatter);
		parseEndDttm = DateTimeUtil.parseDttmToLocalDateTime(rentEndDttm).format(formatter);
	}
	
	public String getRentCd() {
		return rentCd;
	}
	public String getEquCd() {
		return equCd;
	}
	public String getUserNo() {
		return userNo;
	}
	public int getRentCnt() {
		return rentCnt;
	}
	public String getRentStartDttm() {
		return rentStartDttm;
	}
	public String getRentEndDttm() {
		return rentEndDttm;
	}
	public RentStatusCode getRentSts() {
		return rentSts;
	}
	public void setRentCd(String rentCd) {
		this.rentCd = rentCd;
	}
	public void setEquCd(String equCd) {
		this.equCd = equCd;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}
	public void setRentStartDttm(String rentStartDttm) {
		this.rentStartDttm = rentStartDttm;
	}
	public void setRentEndDttm(String rentEndDttm) {
		this.rentEndDttm = rentEndDttm;
	}
	public void setRentSts(RentStatusCode rentSts) {
		this.rentSts = rentSts;
	}
	public void setRentSts(String rentSts) {
		this.rentSts = RentStatusCode.valueOf(rentSts);
	}
	public List<OrgEquRentDetailVO> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OrgEquRentDetailVO> detailList) {
		this.detailList = detailList;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEquNm() {
		return equNm;
	}
	public void setEquNm(String equNm) {
		this.equNm = equNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHomePostNo() {
		return homePostNo;
	}
	public void setHomePostNo(String homePostNo) {
		this.homePostNo = homePostNo;
	}
	public String getHomeAddr1() {
		return homeAddr1;
	}
	public void setHomeAddr1(String homeAddr1) {
		this.homeAddr1 = homeAddr1;
	}
	public String getHomeAddr2() {
		return homeAddr2;
	}
	public void setHomeAddr2(String homeAddr2) {
		this.homeAddr2 = homeAddr2;
	}
	public String getRentDesc() {
		return rentDesc;
	}
	public void setRentDesc(String rentDesc) {
		this.rentDesc = rentDesc;
	}
	public String getItemCds() {
		return itemCds;
	}
	public void setItemCds(String itemCds) {
		this.itemCds = itemCds;
	}

	public String getParseStartDttm() {
		return parseStartDttm;
	}

	public void setParseStartDttm(String parseStartDttm) {
		this.parseStartDttm = parseStartDttm;
	}

	public String getParseEndDttm() {
		return parseEndDttm;
	}

	public void setParseEndDttm(String parseEndDttm) {
		this.parseEndDttm = parseEndDttm;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
}

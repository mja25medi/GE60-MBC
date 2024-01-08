package egovframework.edutrack.modules.course.contents.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * 과목 교재 VO
 */
public class ContentsVO extends DefaultVO {

	private static final long	serialVersionUID	= -6200452633946412561L;
	private String 	crsCreCd;
	private String 	sbjCd;
	private String 	unitCd;
	private String 	parUnitCd;
	private String	unitNm;
	private String	parNnitNm;
	private int		unitLvl = 0;
	private int		parUnitLvl = 0;
	private int		unitOdr;
	private int		maxOdr;
	private String	unitType;
	private String  cntsTypeCd;
	private String	unitFilePath;
	private int		quizCnt;
	private int		quizPassScore;
	private String	prgrChkType = "TIME";
	private int		critTm;
	private int		totalPage;
	private String	prgrChkYn;
	private String	atchFilePath;

	private String	moveType;
	private int		subCnt;
	private int 	quizQstnCnt;
	private int 	bookmarkCnt;

	private String  mobileFilePath;
	private String  mobileFilePath2;
	private String  mobileYn;

	private String  olcYn = "N";
	private String  unitCdStr;
	
	private String	teacherNo;
	private String	classStartTime;
	private String	classEndTime;
	
	private String	classStartHour;
	private String	classStartMin;
	private String	classEndHour;
	private String	classEndMin;
	
	private String	zoomUrl;
	private String sceneId;
	private String roomId;
	private String asmtSn;
	
	private String preUnitCd; /* 이전차시 */
	private String nextUnitCd; /* 이후차시 */
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	
	public String getAsmtSn() {
		return asmtSn;
	}

	public void setAsmtSn(String asmtSn) {
		this.asmtSn = asmtSn;
	}

	public String getPreUnitCd() {
		return preUnitCd;
	}

	public void setPreUnitCd(String preUnitCd) {
		this.preUnitCd = preUnitCd;
	}

	public String getNextUnitCd() {
		return nextUnitCd;
	}

	public void setNextUnitCd(String nextUnitCd) {
		this.nextUnitCd = nextUnitCd;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	private List<ContentsVO> subList;

	public ContentsVO() {
		subList = new ArrayList<ContentsVO>();
	}

	public String getSbjCd() {
		return sbjCd;
	}

	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	public String getUnitCd() {
		return unitCd;
	}

	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}

	public String getParUnitCd() {
		return parUnitCd;
	}

	public void setParUnitCd(String parUnitCd) {
		this.parUnitCd = parUnitCd;
	}

	public String getUnitNm() {
		return unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public String getParNnitNm() {
		return parNnitNm;
	}

	public void setParNnitNm(String parNnitNm) {
		this.parNnitNm = parNnitNm;
	}

	public int getUnitLvl() {
		return unitLvl;
	}

	public void setUnitLvl(int unitLvl) {
		this.unitLvl = unitLvl;
	}

	public int getParUnitLvl() {
		return parUnitLvl;
	}

	public void setParUnitLvl(int parUnitLvl) {
		this.parUnitLvl = parUnitLvl;
	}

	public int getUnitOdr() {
		return unitOdr;
	}

	public void setUnitOdr(int unitOdr) {
		this.unitOdr = unitOdr;
	}

	public int getMaxOdr() {
		return maxOdr;
	}

	public void setMaxOdr(int maxOdr) {
		this.maxOdr = maxOdr;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getCntsTypeCd() {
		return cntsTypeCd;
	}

	public void setCntsTypeCd(String cntsTypeCd) {
		this.cntsTypeCd = cntsTypeCd;
	}

	public String getUnitFilePath() {
		return unitFilePath;
	}

	public void setUnitFilePath(String unitFilePath) {
		this.unitFilePath = unitFilePath;
	}

	public int getQuizCnt() {
		return quizCnt;
	}

	public void setQuizCnt(int quizCnt) {
		this.quizCnt = quizCnt;
	}

	public int getQuizPassScore() {
		return quizPassScore;
	}

	public void setQuizPassScore(int quizPassScore) {
		this.quizPassScore = quizPassScore;
	}

	public String getPrgrChkType() {
		return prgrChkType;
	}

	public void setPrgrChkType(String prgrChkType) {
		this.prgrChkType = prgrChkType;
	}

	public int getCritTm() {
		return critTm;
	}

	public void setCritTm(int critTm) {
		this.critTm = critTm;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getPrgrChkYn() {
		return prgrChkYn;
	}

	public void setPrgrChkYn(String prgrChkYn) {
		this.prgrChkYn = prgrChkYn;
	}

	public String getAtchFilePath() {
		return atchFilePath;
	}

	public void setAtchFilePath(String atchFilePath) {
		this.atchFilePath = atchFilePath;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public int getSubCnt() {
		return subCnt;
	}

	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}

	public List<ContentsVO> getSubList() {
		return subList;
	}

	public void setSubList(List<ContentsVO> subList) {
		this.subList = subList;
	}

	public int getQuizQstnCnt() {
		return quizQstnCnt;
	}

	public void setQuizQstnCnt(int quizQstnCnt) {
		this.quizQstnCnt = quizQstnCnt;
	}

	public int getBookmarkCnt() {
		return bookmarkCnt;
	}

	public void setBookmarkCnt(int bookmarkCnt) {
		this.bookmarkCnt = bookmarkCnt;
	}

	public String getMobileFilePath() {
		return mobileFilePath;
	}

	public void setMobileFilePath(String mobileFilePath) {
		this.mobileFilePath = mobileFilePath;
	}

	public String getMobileYn() {
		return mobileYn;
	}

	public void setMobileYn(String mobileYn) {
		this.mobileYn = mobileYn;
	}

	public String getMobileFilePath2() {
		return mobileFilePath2;
	}

	public void setMobileFilePath2(String mobileFilePath2) {
		this.mobileFilePath2 = mobileFilePath2;
	}

	public String getOlcYn() {
		return olcYn;
	}

	public void setOlcYn(String olcYn) {
		this.olcYn = olcYn;
	}

	public String getUnitCdStr() {
		return unitCdStr;
	}

	public void setUnitCdStr(String unitCdStr) {
		this.unitCdStr = unitCdStr;
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getClassStartTime() {
		return classStartTime;
	}

	public void setClassStartTime(String classStartTime) {
		this.classStartTime = classStartTime;
	}

	public String getClassEndTime() {
		return classEndTime;
	}

	public void setClassEndTime(String classEndTime) {
		this.classEndTime = classEndTime;
	}

	public String getClassStartHour() {
		return classStartHour;
	}

	public void setClassStartHour(String classStartHour) {
		this.classStartHour = classStartHour;
	}

	public String getClassStartMin() {
		return classStartMin;
	}

	public void setClassStartMin(String classStartMin) {
		this.classStartMin = classStartMin;
	}

	public String getClassEndHour() {
		return classEndHour;
	}

	public void setClassEndHour(String classEndHour) {
		this.classEndHour = classEndHour;
	}

	public String getClassEndMin() {
		return classEndMin;
	}

	public void setClassEndMin(String classEndMin) {
		this.classEndMin = classEndMin;
	}

	public String getZoomUrl() {
		return zoomUrl;
	}

	public void setZoomUrl(String zoomUrl) {
		this.zoomUrl = zoomUrl;
	}


}
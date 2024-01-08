package egovframework.edutrack.modules.lecture.bookmark.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.code.impl.AttendStatusCode;
import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.StringUtil;



public class BookmarkVO
		extends DefaultVO {

	private static final long	serialVersionUID	= 3836034377388156251L;
	private String				stdNo;				// NVARCHAR2(12)
	private String				sbjCd;				// VARCHAR2(10 BYTE)
	private String				unitCd;			// VARCHAR2(12 BYTE)
	private String				parUnitCd;
	private String				unitNm;
	private int					unitLvl;
	private int					unitOdr;
	private String				unitType;
	private String				unitFilePath;
	private int					quizCnt;
	private int					quizPassScore;
	private String				prgrChkType;
	private int					critTm;
	private int					totalPage;
	private String				prgrChkYn;
	private String				studyYn;			// CHAR(1 BYTE),
	private String				todayStudyYn;
	private String				studyDttm;			// VARCHAR2(14 BYTE),
	private String				quizPassYn;		// CHAR(1 BYTE),
	private String				quizYn;
	private String				quizUnitCd;
	private int					passScore;			// NUMBER(3),
	private int					maxScore;			// NUMBER(3),
	private int					minScore;			// NUMBER(3),
	private int					connTotTm;			// NUMBER(9),
	private int					connTm;			// NUMBER(9),
	private String				finalConnPage;		// NVARCHAR2(100),
	private String				accmConnPage;		// NVARCHAR2(1000),
	private int					connPageCnt;		// NUMBER(9),
	private int					totPageCnt;		// NUMBER(9),
	private int					prgrRatio;			// NUMBER(3),
	private int					connCnt;			// NUMBER(9),
	private String				lastConnUintCd;
	private String				unitPath;
	private String				firstStudyDttm;
	private String				atchFilePath;
	private String				studyDiv;
	private String				studyDivNm;
	private String				schlStudyDt;
	private String				schlStudyStartTm;
	private String				schlStudyEndTm;
	private String				schlStudyDivCd;
	private String				schlStudyDivNm;
	private String				aprvUserNo;
	private String				aprvUserNm;
	private String				seekTime = "0";
	private Integer				mobileTm = 0;
	private String				studyBlockInfo;
	private String				loginDttm;
	private String				deviceType;
	private String				browserType;
	private String				appType;
	private String				connIp;
	private String				mobileType;
	private String				cntsTypeCd ;
	private String				sceneId ;
	private String				roomId ;
	private String				zoomUrl ;

	private String				errorCode	= "0";
	private String				result;

	private int					subCnt;
	private List<BookmarkVO>	subList;

	private String				userNm;
	private String				userId;
	private String				userNo;
	private String				crsCreCd;
	private String				crsCreNm;
	private String				banCd;
	private String				gradeCd;

	private Integer				stdCnt;
	private Integer				classStudyCnt;
	private Integer				sbjBookmarkCnt;

	private String				olcYn;

	private String				crsCd;
	private String				regIp;
	
	private int					prgrLecCount;
	private int					stareLecCount;
	private int 				stareSemiExam;
	
	private String 				returnValueCheck;
	private String 				teacherNm;
	private String 				classStartTime;
	private String 				classEndTime;
	
	private AttendStatusCode	attendStsCd;
	private int 				asmtSn;
	private int					asmtSubSn;
	
	//성적 API 발송 용 필드 (start의 경우 eduResult를 사용하지 않아 사용하는 VO별 선언)
  	private String scoreSaveType;//START,END,RATE,RESET
  	private String scoreCategory;//EXAM,ASMT,BOOKMARK

	public BookmarkVO() {}
	
	public void checkStudyByAttendSts() {
		if(attendStsCd == null) return;
		this.prgrRatio = attendStsCd.calcPrgrRatio();
		if(!attendStsCd.equals(AttendStatusCode.ABSENCE))
			this.studyYn = "Y";
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getTodayStudyYn() {
		return this.todayStudyYn;
	}
	public void setTodayStudyYn(String todayStudyYn) {
		this.todayStudyYn = todayStudyYn;
	}

	public String getFirstStudyDttm() {
		return this.firstStudyDttm;
	}

	public void setFirstStudyDttm(String firstStudyDttm) {
		this.firstStudyDttm = firstStudyDttm;
	}

	public String getAtchFilePath() {
		return this.atchFilePath;
	}

	public void setAtchFilePath(String atchFilePath) {
		this.atchFilePath = atchFilePath;
	}

	public String getTeacherNm() {
		return teacherNm;
	}

	public void setTeacherNm(String teacherNm) {
		this.teacherNm = teacherNm;
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

	public String getCntsTypeCd() {
		return cntsTypeCd;
	}

	public void setCntsTypeCd(String cntsTypeCd) {
		this.cntsTypeCd = cntsTypeCd;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getZoomUrl() {
		return zoomUrl;
	}

	public void setZoomUrl(String zoomUrl) {
		this.zoomUrl = zoomUrl;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public String getUnitPath() {
		return this.unitPath;
	}

	public String getQuizUnitCd() {
		return this.quizUnitCd;
	}

	public void setQuizUnitCd(String quizUnitCd) {
		this.quizUnitCd = quizUnitCd;
	}

	public String getQuizYn() {
		return this.quizYn;
	}

	public void setQuizYn(String quizYn) {
		this.quizYn = quizYn;
	}

	public void setUnitPath(String unitPath) {
		this.unitPath = unitPath;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<BookmarkVO> getSubList() {
		if (this.subList == null)
			this.subList = new ArrayList<BookmarkVO>();
		return this.subList;
	}

	public void setSubList(List<BookmarkVO> subList) {
		this.subList = subList;
	}

	public String getLastConnUintCd() {
		if ("".equals(this.lastConnUintCd))
			this.lastConnUintCd = this.unitCd;
		return this.lastConnUintCd;
	}

	public void setLastConnUintCd(String lastConnUintCd) {
		this.lastConnUintCd = lastConnUintCd;
	}

	public int getSubCnt() {
		return this.subCnt;
	}

	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}

	public String getStdNo() {
		return this.stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getSbjCd() {
		return this.sbjCd;
	}

	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	public String getUnitCd() {
		return this.unitCd;
	}

	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}

	public String getParUnitCd() {
		return this.parUnitCd;
	}

	public void setParUnitCd(String parUnitCd) {
		this.parUnitCd = parUnitCd;
	}

	public String getUnitNm() {
		return this.unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public int getUnitLvl() {
		return this.unitLvl;
	}

	public void setUnitLvl(int unitLvl) {
		this.unitLvl = unitLvl;
	}

	public int getUnitOdr() {
		return this.unitOdr;
	}

	public void setUnitOdr(int unitOdr) {
		this.unitOdr = unitOdr;
	}

	public String getUnitType() {
		return this.unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitFilePath() {
		return this.unitFilePath;
	}

	public void setUnitFilePath(String unitFilePath) {
		this.unitFilePath = unitFilePath;
	}

	public int getQuizCnt() {
		return this.quizCnt;
	}

	public void setQuizCnt(int quizCnt) {
		this.quizCnt = quizCnt;
	}

	public int getQuizPassScore() {
		return this.quizPassScore;
	}

	public void setQuizPassScore(int quizPassScore) {
		this.quizPassScore = quizPassScore;
	}

	public String getPrgrChkType() {
		return this.prgrChkType;
	}

	public void setPrgrChkType(String prgrChkType) {
		this.prgrChkType = prgrChkType;
	}

	public int getCritTm() {
		return this.critTm;
	}

	public void setCritTm(int critTm) {
		this.critTm = critTm;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getPrgrChkYn() {
		return this.prgrChkYn;
	}

	public void setPrgrChkYn(String prgrChkYn) {
		this.prgrChkYn = prgrChkYn;
	}

	public String getStudyYn() {
		return this.studyYn;
	}

	public void setStudyYn(String studyYn) {
		this.studyYn = studyYn;
	}

	public String getStudyDttm() {
		return this.studyDttm;
	}

	public void setStudyDttm(String studyDttm) {
		this.studyDttm = studyDttm;
	}

	public String getStudyBlockInfo() {
		return studyBlockInfo;
	}

	public void setStudyBlockInfo(String studyBlockInfo) {
		this.studyBlockInfo = studyBlockInfo;
	}

	public String getQuizPassYn() {
		return this.quizPassYn;
	}

	public void setQuizPassYn(String quizPassYn) {
		this.quizPassYn = quizPassYn;
	}

	public int getPassScore() {
		return this.passScore;
	}

	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}

	public int getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getMinScore() {
		return this.minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public int getConnTotTm() {
		return this.connTotTm;
	}

	public void setConnTotTm(int connTotTm) {
		this.connTotTm = connTotTm;
	}

	public int getConnTm() {
		return this.connTm;
	}

	public void setConnTm(int connTm) {
		this.connTm = connTm;
	}

	public String getFinalConnPage() {
		return this.finalConnPage;
	}

	public void setFinalConnPage(String finalConnPage) {
		this.finalConnPage = finalConnPage;
	}

	public String getAccmConnPage() {
		return this.accmConnPage;
	}

	public void setAccmConnPage(String accmConnPage) {
		this.accmConnPage = accmConnPage;
	}

	public int getConnPageCnt() {
		return this.connPageCnt;
	}

	public void setConnPageCnt(int connPageCnt) {
		this.connPageCnt = connPageCnt;
	}

	public int getTotPageCnt() {
		return this.totPageCnt;
	}

	public void setTotPageCnt(int totPageCnt) {
		this.totPageCnt = totPageCnt;
	}

	public int getPrgrRatio() {
		return this.prgrRatio;
	}

	public void setPrgrRatio(int prgrRatio) {
		this.prgrRatio = prgrRatio;
	}

	public int getConnCnt() {
		return this.connCnt;
	}

	public void setConnCnt(int connCnt) {
		this.connCnt = connCnt;
	}

	public void addSubVO(BookmarkVO bookmarkVO) {
		this.getSubList().add(bookmarkVO);
	}

	public int getSummaryRatio() {
		if (this.getSubList().size() == 0)
			return this.getPrgrRatio();
		int sumRatio = 0;
		int count = 0;

		for (BookmarkVO VO : this.getSubList()) {
			if (VO.getSubList().size() > 0) {
				sumRatio += VO.getSummaryRatio();
				count++;
			} else {
				sumRatio += VO.getPrgrRatio();
				count++;
			}
		}
		if (count == 0)
			return 0;
		return sumRatio / count;
	}

	public int getTotalConnect() {
		if (this.getSubList().size() == 0)
			return this.getConnCnt();
		int totalConnect = 0;
		for (BookmarkVO VO : this.getSubList()) {
			if (VO.getSubList().size() > 0) {
				totalConnect += VO.getTotalConnect();
			} else {
				totalConnect += VO.getConnCnt();
			}
		}
		return totalConnect;
	}

	public int getTotalConnectTime() {
		if (this.getSubList().size() == 0)
			return this.getConnTotTm();
		int totalConnectTime = 0;
		for (BookmarkVO VO : this.getSubList()) {
			if (VO.getSubList().size() > 0) {
				totalConnectTime += VO.getTotalConnectTime();
			} else {
				totalConnectTime += VO.getConnTotTm();
			}
		}
		return totalConnectTime;
	}

	public String getLastConnectUinitCd(String firstCd) {
		if (this.getSubList().size() == 0)
			return this.getUnitCd();
		String unitCd = "";
		for (BookmarkVO VO : this.getSubList()) {
			if (VO.getSubList().size() > 0) {
				unitCd = VO.getLastConnectUinitCd(firstCd);
			} else {
				if ("".equals(StringUtil.nvl(firstCd)))
					firstCd = VO.getUnitCd();
				if ("Y".equals(VO.getStudyYn()))
					unitCd = VO.getUnitCd();
			}
		}
		if ("".equals(unitCd))
			unitCd = firstCd;
		return unitCd;
	}

	public String getQuiz() {
		if (this.getSubList().size() == 0)
			return this.getQuizUnitCd();
		String unitCd = "";
		for (BookmarkVO VO : this.getSubList()) {
			if (VO.getSubList().size() > 0) {
				unitCd = VO.getQuiz();
			} else {
				unitCd += "|" + VO.getQuizUnitCd();
			}
		}
		return unitCd;
	}

	public String getStudyDiv() {
		return studyDiv;
	}

	public void setStudyDiv(String studyDiv) {
		this.studyDiv = studyDiv;
	}

	public Integer getStdCnt() {
		return stdCnt;
	}

	public void setStdCnt(Integer stdCnt) {
		this.stdCnt = stdCnt;
	}

	public Integer getClassStudyCnt() {
		return classStudyCnt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public void setClassStudyCnt(Integer classStudyCnt) {
		this.classStudyCnt = classStudyCnt;
	}

	public String getBanCd() {
		return banCd;
	}

	public void setBanCd(String banCd) {
		this.banCd = banCd;
	}

	public String getGradeCd() {
		return gradeCd;
	}

	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}

	public String getStudyDivNm() {
		return studyDivNm;
	}

	public void setStudyDivNm(String studyDivNm) {
		this.studyDivNm = studyDivNm;
	}

	public String getSchlStudyDt() {
		return schlStudyDt;
	}

	public void setSchlStudyDt(String schlStudyDt) {
		this.schlStudyDt = schlStudyDt;
	}

	public String getSchlStudyStartTm() {
		return schlStudyStartTm;
	}

	public void setSchlStudyStartTm(String schlStudyStartTm) {
		this.schlStudyStartTm = schlStudyStartTm;
	}

	public String getSchlStudyEndTm() {
		return schlStudyEndTm;
	}

	public void setSchlStudyEndTm(String schlStudyEndTm) {
		this.schlStudyEndTm = schlStudyEndTm;
	}

	public String getSchlStudyDivCd() {
		return schlStudyDivCd;
	}

	public void setSchlStudyDivCd(String schlStudyDivCd) {
		this.schlStudyDivCd = schlStudyDivCd;
	}

	public String getSchlStudyDivNm() {
		return schlStudyDivNm;
	}

	public void setSchlStudyDivNm(String schlStudyDivNm) {
		this.schlStudyDivNm = schlStudyDivNm;
	}

	public String getAprvUserNo() {
		return aprvUserNo;
	}

	public void setAprvUserNo(String aprvUserNo) {
		this.aprvUserNo = aprvUserNo;
	}

	public String getAprvUserNm() {
		return aprvUserNm;
	}

	public void setAprvUserNm(String aprvUserNm) {
		this.aprvUserNm = aprvUserNm;
	}

	public String getUnitFileExt() {
		return FileUtil.getFileExtention(this.unitFilePath);
	}

	public String getSeekTime() {
		return seekTime;
	}

	public void setSeekTime(String seekTime) {
		this.seekTime = seekTime;
	}

	public String getOlcYn() {
		return olcYn;
	}

	public void setOlcYn(String olcYn) {
		this.olcYn = olcYn;
	}

	public String getCrsCd() {
		return crsCd;
	}

	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}

	public Integer getMobileTm() {
		return mobileTm;
	}

	public void setMobileTm(Integer mobileTm) {
		this.mobileTm = mobileTm;
	}

	public Integer getSbjBookmarkCnt() {
		return sbjBookmarkCnt;
	}

	public void setSbjBookmarkCnt(Integer sbjBookmarkCnt) {
		this.sbjBookmarkCnt = sbjBookmarkCnt;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getConnIp() {
		return connIp;
	}

	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public String getCrsCreNm() {
		return crsCreNm;
	}

	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}

	public String getLoginDttm() {
		return loginDttm;
	}

	public void setLoginDttm(String loginDttm) {
		this.loginDttm = loginDttm;
	}

	public int getPrgrLecCount() {
		return prgrLecCount;
	}

	public void setPrgrLecCount(int prgrLecCount) {
		this.prgrLecCount = prgrLecCount;
	}

	public int getStareLecCount() {
		return stareLecCount;
	}

	public void setStareLecCount(int stareLecCount) {
		this.stareLecCount = stareLecCount;
	}

	public int getStareSemiExam() {
		return stareSemiExam;
	}

	public void setStareSemiExam(int stareSemiExam) {
		this.stareSemiExam = stareSemiExam;
	}

	public String getReturnValueCheck() {
		return returnValueCheck;
	}

	public void setReturnValueCheck(String returnValueCheck) {
		this.returnValueCheck = returnValueCheck;
	}

	public String getScoreSaveType() {
		return scoreSaveType;
	}

	public void setScoreSaveType(String scoreSaveType) {
		this.scoreSaveType = scoreSaveType;
	}

	public String getScoreCategory() {
		return scoreCategory;
	}

	public void setScoreCategory(String scoreCategory) {
		this.scoreCategory = scoreCategory;
	}

	public AttendStatusCode getAttendStsCd() {
		return attendStsCd;
	}

	public void setAttendStsCd(AttendStatusCode attendStsCd) {
		this.attendStsCd = attendStsCd;
	}
	
	public void setAttendStsCd(String codeName) {
		this.attendStsCd = AttendStatusCode.findCode(codeName);
	}

	public int getAsmtSn() {
		return asmtSn;
	}

	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}

	public int getAsmtSubSn() {
		return asmtSubSn;
	}

	public void setAsmtSubSn(int asmtSubSn) {
		this.asmtSubSn = asmtSubSn;
	}
	
}
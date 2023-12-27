package egovframework.edutrack.modules.lecture.exam.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;


public class ExamStareVO  extends DefaultVO {
    /**
	 *
	 */
	private static final long	serialVersionUID	= 5375143093804024828L;
	private String  crsCreCd     ; //VARCHAR2(10 BYTE)
    private String  stdNo        ; // NVARCHAR2(12)
    private int   	examSn       ; // NUMBER(9)
    private String	qstnNos;
    private String  qstnRgtAnsrs  ; //NVARCHAR2(100),
    private String  qstnScores    ; // NUMBER(3),
    private String  stareAnss     ; // NVARCHAR2(100),
    private String	getScores     ; // NUMBER(3),
    private double  totGetScore  ; //NUMBER(3),
    private Integer	stareCnt	;
    private Integer stareTm      ; // NUMBER(9),
    private String  startDttm    ; // VARCHAR2(14 BYTE),
    private String  endDttm      ; // VARCHAR2(14 BYTE),
    private String  atchCts      ; // NCLOB,
    private String  rateYn       ; // CHAR(1 BYTE),
    private String  agreeYn		 ;

    private String	rateYnNm;

    private String	userNo;
    private String	userNm;
    private String	userId;
    private String	email;
    private String	birth;
    private String 	eduNo;
    private String	mobileNo;
    private int     qstnNo;
    private Integer examQstnSn;
    private String	getScore;
    private String  qstnType;
    private String  rgtAnsr;
    private String  qstnScore;
    private String  userNoObj;
    private String[] userNoObjArr;

    private String examTypeCd;//on, off 시험 유형

    private String stareYn;
    private String examSnChk;
    private String semiExamYn;
    private String regIp;
    private String startFlagYn;//시작여부 Y:시작, N: 종료
    
    
    private String copyRatioUri;      //모사 URI
    private String completeStatus ;  //모사 완료 여부
    private int   	totalCopyRatio;   //모사율
    private String dispTotalCopyRatio; //모사 % 문구
    private String completeDate;
    
    private int examSetCnt;
    private List<String> stdNoList;
    
    //성적 API 발송 용 필드 (start의 경우 eduResult를 사용하지 않아 사용하는 VO별 선언)
  	private String scoreSaveType;//START,END,RATE,RESET
  	private String scoreCategory;//EXAM,ASMT,BOOKMARK

	public ExamStareVO(){}
	
	public List<String> getStdNoList() {
		if(stdNoList == null) {
			stdNoList = new ArrayList<>();
		}
		return stdNoList;
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

	public void setStdNoList(List<String> stdNoList) {
		this.stdNoList = stdNoList;
	}

	public String getDispTotalCopyRatio() {
		return dispTotalCopyRatio;
	}

	public void setDispTotalCopyRatio(String dispTotalCopyRatio) {
		this.dispTotalCopyRatio = dispTotalCopyRatio;
	}

	public String getCompleteDate() {
		return completeDate;
	}



	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}



	public String getStartFlagYn() {
		return startFlagYn;
	}

	public void setStartFlagYn(String startFlagYn) {
		this.startFlagYn = startFlagYn;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String[] getUserNoObjArr() {
		return userNoObjArr;
	}

	public void setUserNoObjArr(String[] userNoObjArr) {
		this.userNoObjArr = userNoObjArr;
	}

	public double getTotGetScore() {
		return this.totGetScore;
	}

	public void setTotGetScore(double totGetScore) {
		this.totGetScore = totGetScore;
	}


	/**
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return this.crsCreCd;
	}


	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}


	/**
	 * @return the stdNo
	 */
	public String getStdNo() {
		return this.stdNo;
	}


	/**
	 * @param stdNo the stdNo to set
	 */
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}


	/**
	 * @return the examSn
	 */
	public int getExamSn() {
		return this.examSn;
	}


	/**
	 * @param examSn the examSn to set
	 */
	public void setExamSn(int examSn) {
		this.examSn = examSn;
	}


	/**
	 * @return the qstnNos
	 */
	public String getQstnNos() {
		return this.qstnNos;
	}


	/**
	 * @param qstnNos the qstnNos to set
	 */
	public void setQstnNos(String qstnNos) {
		this.qstnNos = qstnNos;
	}


	/**
	 * @return the qstnRgtAnsrs
	 */
	public String getQstnRgtAnsrs() {
		return this.qstnRgtAnsrs;
	}


	/**
	 * @param qstnRgtAnsrs the qstnRgtAnsrs to set
	 */
	public void setQstnRgtAnsrs(String qstnRgtAnsrs) {
		this.qstnRgtAnsrs = qstnRgtAnsrs;
	}


	/**
	 * @return the qstnScores
	 */
	public String getQstnScores() {
		return this.qstnScores;
	}


	/**
	 * @param qstnScores the qstnScores to set
	 */
	public void setQstnScores(String qstnScores) {
		this.qstnScores = qstnScores;
	}


	/**
	 * @return the stareAnss
	 */
	public String getStareAnss() {
		return this.stareAnss;
	}


	/**
	 * @param stareAnss the stareAnss to set
	 */
	public void setStareAnss(String stareAnss) {
		this.stareAnss = stareAnss;
	}


	/**
	 * @return the getScores
	 */
	public String getGetScores() {
		return this.getScores;
	}


	/**
	 * @param getScores the getScores to set
	 */
	public void setGetScores(String getScores) {
		this.getScores = getScores;
	}


	/**
	 * @return the stareCnt
	 */
	public Integer getStareCnt() {
		return this.stareCnt;
	}


	/**
	 * @param stareCnt the stareCnt to set
	 */
	public void setStareCnt(Integer stareCnt) {
		this.stareCnt = stareCnt;
	}


	/**
	 * @return the stareTm
	 */
	public Integer getStareTm() {
		return this.stareTm;
	}


	/**
	 * @param stareTm the stareTm to set
	 */
	public void setStareTm(Integer stareTm) {
		this.stareTm = stareTm;
	}


	/**
	 * @return the startDttm
	 */
	public String getStartDttm() {
		return this.startDttm;
	}


	/**
	 * @param startDttm the startDttm to set
	 */
	public void setStartDttm(String startDttm) {
		this.startDttm = startDttm;
	}


	/**
	 * @return the endDttm
	 */
	public String getEndDttm() {
		return this.endDttm;
	}


	/**
	 * @param endDttm the endDttm to set
	 */
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}


	/**
	 * @return the atchCts
	 */
	public String getAtchCts() {
		return this.atchCts;
	}


	/**
	 * @param atchCts the atchCts to set
	 */
	public void setAtchCts(String atchCts) {
		this.atchCts = atchCts;
	}


	/**
	 * @return the rateYn
	 */
	public String getRateYn() {
		return this.rateYn;
	}


	/**
	 * @param rateYn the rateYn to set
	 */
	public void setRateYn(String rateYn) {
		this.rateYn = rateYn;
	}

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return this.userNo;
	}


	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return this.userNm;
	}


	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return this.userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the birth
	 */
	public String getBirth() {
		return this.birth;
	}


	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}


	/**
	 * @return the eduNo
	 */
	public String getEduNo() {
		return this.eduNo;
	}


	/**
	 * @param eduNo the eduNo to set
	 */
	public void setEduNo(String eduNo) {
		this.eduNo = eduNo;
	}


	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return this.mobileNo;
	}


	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	/**
	 * @return the rateYnNm
	 */
	public String getRateYnNm() {
		return this.rateYnNm;
	}



	/**
	 * @param rateYnNm the rateYnNm to set
	 */
	public void setRateYnNm(String rateYnNm) {
		this.rateYnNm = rateYnNm;
	}



	public int getQstnNo() {
		return this.qstnNo;
	}



	public void setQstnNo(int qstnNo) {
		this.qstnNo = qstnNo;
	}



	public Integer getExamQstnSn() {
		return this.examQstnSn;
	}



	public void setExamQstnSn(Integer examQstnSn) {
		this.examQstnSn = examQstnSn;
	}



	public String getQstnType() {
		return this.qstnType;
	}



	public void setQstnType(String qstnType) {
		this.qstnType = qstnType;
	}



	public String getRgtAnsr() {
		return this.rgtAnsr;
	}



	public void setRgtAnsr(String rgtAnsr) {
		this.rgtAnsr = rgtAnsr;
	}



	public String getQstnScore() {
		return this.qstnScore;
	}



	public void setQstnScore(String qstnScore) {
		this.qstnScore = qstnScore;
	}



	public String getUserNoObj() {
		return this.userNoObj;
	}



	public void setUserNoObj(String userNoObj) {
		this.userNoObj = userNoObj;
	}


	public String getGetScore() {
		return getScore;
	}

	public void setGetScore(String getScore) {
		this.getScore = getScore;
	}

	public String getExamTypeCd() {
		return examTypeCd;
	}



	public void setExamTypeCd(String examTypeCd) {
		this.examTypeCd = examTypeCd;
	}



	public String getStareYn() {
		return stareYn;
	}



	public void setStareYn(String stareYn) {
		this.stareYn = stareYn;
	}



	public String getExamSnChk() {
		return examSnChk;
	}



	public void setExamSnChk(String examSnChk) {
		this.examSnChk = examSnChk;
	}

	public String getAgreeYn() {
		return agreeYn;
	}

	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}

	public String getSemiExamYn() {
		return semiExamYn;
	}

	public void setSemiExamYn(String semiExamYn) {
		this.semiExamYn = semiExamYn;
	}

	public int getExamSetCnt() {
		return examSetCnt;
	}

	public void setExamSetCnt(int examSetCnt) {
		this.examSetCnt = examSetCnt;
	}

	public int getTotalCopyRatio() {
		return totalCopyRatio;
	}

	public void setTotalCopyRatio(int totalCopyRatio) {
		this.totalCopyRatio = totalCopyRatio;
	}

	public String getCopyRatioUri() {
		return copyRatioUri;
	}

	public void setCopyRatioUri(String copyRatioUri) {
		this.copyRatioUri = copyRatioUri;
	}

	public String getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}


}

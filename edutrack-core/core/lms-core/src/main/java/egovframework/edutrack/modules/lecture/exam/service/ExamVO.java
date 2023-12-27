package egovframework.edutrack.modules.lecture.exam.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ExamVO
		extends DefaultVO {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 7056609150239459808L;
	private String	crsCreCd;			// VARCHAR2(10 BYTE)
	private int		examSn;			// NUMBER(9)
	private String	examTitle;			// NVARCHAR2(200),
	private String	examCts;			// NCLOB,
	private String  semiExamYn;				
	private String	examTypeCd;		// VARCHAR2(10 BYTE),
	private String	examTypeNm;
	private String	examStareTypeCd;	// VARCHAR2(10 BYTE),
	private String	examStareTypeNm;
	private String	examStartDttm;		// VARCHAR2(14 BYTE),
	private String	examStartHour;
	private String	examStartMin;
	private String	examEndDttm;		// VARCHAR2(14 BYTE),
	private String	examEndHour;
	private String	examEndMin;
	private String	rsltCfrmDttm;		// VARCHAR2(14 BYTE),
	private String	rsltCfrmHour;
	private String	rsltCfrmMin;
	private int		examStareTm;		// NUMBER(9),
	private String	examViewTypeCd;	// VARCHAR2(10 BYTE),
	private String	examViewTypeNm;
	private String	stareTmUseYn;		// CHAR(1 BYTE),
	private String	stareTmUseYnNm;
	private int		stareCritPrgrRatio; // NUMBER(3),
	private int		stareLimitCnt;		// NUMBER(9),
	private int 	stareLecCount;
	private int 	prgrLecCount;
	private String	regYn;
	private String	regYnNm;
	private String	stdNo;
	private String  qstnNos;

	private double	totGetScore;		// NUMBER(3),

	private int		qstnCnt;
	private int		stareCnt;
	private String	qstnScores;
	private String	rateYn;
	private String	connYn;
	private int		stdRatio;
	private String	rsltYn;
	private String	userNm;
	private String	stareYn;
	private String endDttm;

	private String sbjCd;			//과목코드:VARCHAR2(10)
	private String sbjNm;			//과목이름:VARCHAR2(50)

	private Integer declsNo;
	private String 	strQstnNo;		//문제 번호 배열

	private String examQstnSn;   //시험문제 고유번호

	private double avgScore;
	private double myScore;
	
	private ExamStareVO examStareVO;
	
	private int examSetCnt;
	
	private int selCnt  ;//선택형(객관식,진위형) 문항 수
	private int selPnt  ;//선택형 배점
	private int shortCnt;//단답형 문항수
	private int shortPnt;//단답형 배점
	private int desCnt  ;//서술형 문항수
	private int desPnt  ;//서술형 배점
	
	private String[] stdNoObjArr;

	public ExamVO() {
		examStareVO = new ExamStareVO();
	}

	//===========시험의 문항수, 배점에 대한 조회 함수 start
	//선택형 문항수 * 배점
	public int getCalCulateSelScore() {
		return selCnt * selPnt;
	}
	
	//단답형 문항수 * 배점
	public int getCalCulateShortScore() {
		return shortCnt * shortPnt;
	}
	
	//서술형 문항수 * 배점
	public int getCalCulateDesScore() {
		return desCnt * desPnt;
	}
	
	//시험 배점 총합 = (선택형 문항수 * 배점) + (단답형 문항수 * 배점) + (서술형 문항수 * 배점)
	public int getCalCulateTotScore() {
		return getCalCulateSelScore() + getCalCulateShortScore() + getCalCulateDesScore();
	}
	
	//시험 배점 총합 100점이면 true, 아니면 false
	public boolean getIsCalCulateTotScore() {
		if(getCalCulateTotScore() == 100) {
			return true;
		}else {
			return false;
		}
	}
	
	//시험의 문항 수 = 선택형 + 단답형 + 서술형
	public int getSumSelShortDesCnt() {
		return selCnt + shortCnt + desCnt;
	}
	
	//갯수와 배점이 다르면 true, 같으면 false
	public boolean getIsOtherExamSelShortDesDiff(ExamVO vo) {
		boolean checkFlag = false;
		if(selCnt != vo.getSelCnt() || shortCnt != vo.getShortCnt() || desCnt != vo.getDesCnt()) {//갯수가 미일치
			checkFlag = true;
		}		
		if(selPnt != vo.getSelPnt() || shortPnt != vo.getShortPnt() || desPnt != vo.getDesPnt()) {//배점이 미일치
			checkFlag = true;
		}
		return checkFlag;
	}
	//=============== end

	public double getTotGetScore() {
		return this.totGetScore;
	}

	public void setTotGetScore(double totGetScore) {
		this.totGetScore = totGetScore;
	}


	public String getStareYn() {
		return this.stareYn;
	}


	public void setStareYn(String stareYn) {
		this.stareYn = stareYn;
	}

	public String getConnYn() {
		return this.connYn;
	}

	public void setConnYn(String connYn) {
		this.connYn = connYn;
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
	 * @return the qstnCnt
	 */
	public int getQstnCnt() {
		return this.qstnCnt;
	}

	/**
	 * @return the stareCnt
	 */
	public int getStareCnt() {
		return this.stareCnt;
	}

	/**
	 * @param stareCnt the stareCnt to set
	 */
	public void setStareCnt(int stareCnt) {
		this.stareCnt = stareCnt;
	}

	/**
	 * @param qstnCnt the qstnCnt to set
	 */
	public void setQstnCnt(int qstnCnt) {
		this.qstnCnt = qstnCnt;
	}

	/**
	 * @return the examStartHour
	 */
	public String getExamStartHour() {
		return this.examStartHour;
	}

	/**
	 * @return the rsltCfrmHour
	 */
	public String getRsltCfrmHour() {
		return this.rsltCfrmHour;
	}

	/**
	 * @param rsltCfrmHour the rsltCfrmHour to set
	 */
	public void setRsltCfrmHour(String rsltCfrmHour) {
		this.rsltCfrmHour = rsltCfrmHour;
	}

	/**
	 * @return the rsltCfrmMin
	 */
	public String getRsltCfrmMin() {
		return this.rsltCfrmMin;
	}

	/**
	 * @param rsltCfrmMin the rsltCfrmMin to set
	 */
	public void setRsltCfrmMin(String rsltCfrmMin) {
		this.rsltCfrmMin = rsltCfrmMin;
	}

	/**
	 * @param examStartHour the examStartHour to set
	 */
	public void setExamStartHour(String examStartHour) {
		this.examStartHour = examStartHour;
	}

	/**
	 * @return the examStartMin
	 */
	public String getExamStartMin() {
		return this.examStartMin;
	}

	/**
	 * @param examStartMin the examStartMin to set
	 */
	public void setExamStartMin(String examStartMin) {
		this.examStartMin = examStartMin;
	}

	/**
	 * @return the examEndHour
	 */
	public String getExamEndHour() {
		return this.examEndHour;
	}

	/**
	 * @param examEndHour the examEndHour to set
	 */
	public void setExamEndHour(String examEndHour) {
		this.examEndHour = examEndHour;
	}

	/**
	 * @return the examEndMin
	 */
	public String getExamEndMin() {
		return this.examEndMin;
	}

	/**
	 * @param examEndMin the examEndMin to set
	 */
	public void setExamEndMin(String examEndMin) {
		this.examEndMin = examEndMin;
	}

	/**
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return crsCreCd;
	}

	/**
	 * @return the examTypeNm
	 */
	public String getExamTypeNm() {
		return this.examTypeNm;
	}

	/**
	 * @param examTypeNm the examTypeNm to set
	 */
	public void setExamTypeNm(String examTypeNm) {
		this.examTypeNm = examTypeNm;
	}

	/**
	 * @return the examStareTypeNm
	 */
	public String getExamStareTypeNm() {
		return this.examStareTypeNm;
	}

	/**
	 * @param examStareTypeNm the examStareTypeNm to set
	 */
	public void setExamStareTypeNm(String examStareTypeNm) {
		this.examStareTypeNm = examStareTypeNm;
	}

	/**
	 * @return the examViewTypeNm
	 */
	public String getExamViewTypeNm() {
		return this.examViewTypeNm;
	}

	/**
	 * @param examViewTypeNm the examViewTypeNm to set
	 */
	public void setExamViewTypeNm(String examViewTypeNm) {
		this.examViewTypeNm = examViewTypeNm;
	}

	/**
	 * @return the stareTmUseYnNm
	 */
	public String getStareTmUseYnNm() {
		return this.stareTmUseYnNm;
	}

	/**
	 * @param stareTmUseYnNm the stareTmUseYnNm to set
	 */
	public void setStareTmUseYnNm(String stareTmUseYnNm) {
		this.stareTmUseYnNm = stareTmUseYnNm;
	}

	/**
	 * @return the retYnNm
	 */
	public String getRegYnNm() {
		return this.regYnNm;
	}

	/**
	 * @param retYnNm the retYnNm to set
	 */
	public void setRegYnNm(String regYnNm) {
		this.regYnNm = regYnNm;
	}

	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	/**
	 * @return the examSn
	 */
	public int getExamSn() {
		return examSn;
	}

	/**
	 * @param examSn the examSn to set
	 */
	public void setExamSn(int examSn) {
		this.examSn = examSn;
	}

	/**
	 * @return the examTitle
	 */
	public String getExamTitle() {
		return examTitle;
	}

	/**
	 * @param examTitle the examTitle to set
	 */
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	/**
	 * @return the examCts
	 */
	public String getExamCts() {
		return examCts;
	}

	/**
	 * @param examCts the examCts to set
	 */
	public void setExamCts(String examCts) {
		this.examCts = examCts;
	}

	/**
	 * @return the examTypeCd
	 */
	public String getExamTypeCd() {
		return examTypeCd;
	}

	/**
	 * @param examTypeCd the examTypeCd to set
	 */
	public void setExamTypeCd(String examTypeCd) {
		this.examTypeCd = examTypeCd;
	}

	/**
	 * @return the examStareTypeCd
	 */
	public String getExamStareTypeCd() {
		return examStareTypeCd;
	}

	/**
	 * @param examStareTypeCd the examStareTypeCd to set
	 */
	public void setExamStareTypeCd(String examStareTypeCd) {
		this.examStareTypeCd = examStareTypeCd;
	}

	/**
	 * @return the examStartDttm
	 */
	public String getExamStartDttm() {
		return examStartDttm;
	}

	/**
	 * @param examStartDttm the examStartDttm to set
	 */
	public void setExamStartDttm(String examStartDttm) {
		this.examStartDttm = examStartDttm;
	}

	/**
	 * @return the examEndDttm
	 */
	public String getExamEndDttm() {
		return examEndDttm;
	}

	/**
	 * @param examEndDttm the examEndDttm to set
	 */
	public void setExamEndDttm(String examEndDttm) {
		this.examEndDttm = examEndDttm;
	}

	/**
	 * @return the rsltCfrmDttm
	 */
	public String getRsltCfrmDttm() {
		return rsltCfrmDttm;
	}

	/**
	 * @param rsltCfrmDttm the rsltCfrmDttm to set
	 */
	public void setRsltCfrmDttm(String rsltCfrmDttm) {
		this.rsltCfrmDttm = rsltCfrmDttm;
	}

	/**
	 * @return the examStareTm
	 */
	public int getExamStareTm() {
		return examStareTm;
	}

	/**
	 * @param examStareTm the examStareTm to set
	 */
	public void setExamStareTm(int examStareTm) {
		this.examStareTm = examStareTm;
	}

	/**
	 * @return the examViewTypeCd
	 */
	public String getExamViewTypeCd() {
		return examViewTypeCd;
	}

	/**
	 * @param examViewTypeCd the examViewTypeCd to set
	 */
	public void setExamViewTypeCd(String examViewTypeCd) {
		this.examViewTypeCd = examViewTypeCd;
	}

	/**
	 * @return the stareTmUseYn
	 */
	public String getStareTmUseYn() {
		return stareTmUseYn;
	}

	/**
	 * @param stareTmUseYn the stareTmUseYn to set
	 */
	public void setStareTmUseYn(String stareTmUseYn) {
		this.stareTmUseYn = stareTmUseYn;
	}

	/**
	 * @return the stareCritPrgrRatio
	 */
	public int getStareCritPrgrRatio() {
		return stareCritPrgrRatio;
	}

	/**
	 * @param stareCritPrgrRatio the stareCritPrgrRatio to set
	 */
	public void setStareCritPrgrRatio(int stareCritPrgrRatio) {
		this.stareCritPrgrRatio = stareCritPrgrRatio;
	}

	/**
	 * @return the stareLimitCnt
	 */
	public int getStareLimitCnt() {
		return stareLimitCnt;
	}

	/**
	 * @param stareLimitCnt the stareLimitCnt to set
	 */
	public void setStareLimitCnt(int stareLimitCnt) {
		this.stareLimitCnt = stareLimitCnt;
	}
	
	public int getStareLecCount() {
		return stareLecCount;
	}

	public void setStareLecCount(int stareLecCount) {
		this.stareLecCount = stareLecCount;
	}

	public int getPrgrLecCount() {
		return prgrLecCount;
	}



	public void setPrgrLecCount(int prgrLecCount) {
		this.prgrLecCount = prgrLecCount;
	}



	/**
	 * @return the regYn
	 */
	public String getRegYn() {
		return regYn;
	}

	/**
	 * @param regYn the regYn to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}

	public String getRateYn() {
		return this.rateYn;
	}

	public void setRateYn(String rateYn) {
		this.rateYn = rateYn;
	}

	public String getStdNo() {
		return this.stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public int getStdRatio() {
		return this.stdRatio;
	}

	public void setStdRatio(int stdRatio) {
		this.stdRatio = stdRatio;
	}

	public String getRsltYn() {
		return this.rsltYn;
	}

	public void setRsltYn(String rsltYn) {
		this.rsltYn = rsltYn;
	}

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
	 * @return the sbjNm
	 */
	public String getSbjNm() {
		return sbjNm;
	}

	/**
	 * @param sbjNm the sbjNm to set
	 */
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}

	public Integer getDeclsNo() {
		return declsNo;
	}

	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}

	public String getStrQstnNo() {
		return strQstnNo;
	}

	public void setStrQstnNo(String strQstnNo) {
		this.strQstnNo = strQstnNo;
	}

	public String getExamQstnSn() {
		return examQstnSn;
	}

	public void setExamQstnSn(String examQstnSn) {
		this.examQstnSn = examQstnSn;
	}

	public String getQstnNos() {
		return qstnNos;
	}

	public void setQstnNos(String qstnNos) {
		this.qstnNos = qstnNos;
	}



	public double getAvgScore() {
		return avgScore;
	}



	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}



	public double getMyScore() {
		return myScore;
	}



	public void setMyScore(double myScore) {
		this.myScore = myScore;
	}



	public ExamStareVO getExamStareVO() {
		return examStareVO;
	}



	public void setExamStareVO(ExamStareVO examStareVO) {
		this.examStareVO = examStareVO;
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



	public int getSelCnt() {
		return selCnt;
	}



	public void setSelCnt(int selCnt) {
		this.selCnt = selCnt;
	}



	public int getSelPnt() {
		return selPnt;
	}



	public void setSelPnt(int selPnt) {
		this.selPnt = selPnt;
	}



	public int getShortCnt() {
		return shortCnt;
	}



	public void setShortCnt(int shortCnt) {
		this.shortCnt = shortCnt;
	}



	public int getShortPnt() {
		return shortPnt;
	}



	public void setShortPnt(int shortPnt) {
		this.shortPnt = shortPnt;
	}



	public int getDesCnt() {
		return desCnt;
	}



	public void setDesCnt(int desCnt) {
		this.desCnt = desCnt;
	}



	public int getDesPnt() {
		return desPnt;
	}

	public void setDesPnt(int desPnt) {
		this.desPnt = desPnt;
	}

	public String getEndDttm() {
		return endDttm;
	}

	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}

	public String[] getStdNoObjArr() {
		return stdNoObjArr;
	}

	public void setStdNoObjArr(String[] stdNoObjArr) {
		this.stdNoObjArr = stdNoObjArr;
	}
	
}
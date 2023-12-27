package egovframework.edutrack.modules.student.result.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdStdSearchable;


/**
 * 학습결과 DTO
 */
public class EduResultVO  extends DefaultVO implements HrdStdSearchable {

	private static final long	serialVersionUID	= -8761286077170007661L;

	private String 	stdNo		;
	private String	crsCreCd	; //-- 추가 필드
	private String	userNo		; //-- 추가 필드
	private String	userNm		; //-- 추가 필드
	private String	userId		; //-- 추가 필드
	private String	email		; //-- 추가 필드
	private String	birth		; //-- 추가 필드
	private String	enrlSts		; //-- 추가 필드
	private String	enrlStsNm	; //-- 추가 필드
	private String	eduNo		; //-- 추가 필드

	private double	prgrScore	= 0;
	private double	attdScore	= 0;
	private double	examScore	= 0;
	private double	semiExamScore	= 0;
	private double	asmtScore	= 0;
	private double	forumScore	= 0;
	private double	prjtScore	= 0;
	private double	joinScore	= 0;
	private double	etcScore	= 0;
	private double	etcScore2	= 0;
	private double	etcScore3	= 0;
	private double	etcScore4	= 0;
	private double	etcScore5	= 0;
	private double	totScore	= 0;
	private double	convScore	= 0;
	private int		totRatio	= 0;
	private int		totBmkTime	= 0;
	
	private int 	prgrRate	= 0;
	
	private String	enrlDuration;
	private String  scoreEcltYn;

	private String 	resultYn	;

	private String sbjCd;		//--과목 코드

	private Integer declsNo;

	private String	banCd;
	private String	gradeCd;
	private String	deptCd;
	private String	deptNm;
	
	private String crsCreNm;
	private String crsCd;
	private String crsYear;
	private String crsTerm;
	private String crsNm;
	private String refundYn;
	private String cpltNo;
	
	public EduResultVO() {};
	
	public EduResultVO(HrdStdSearchable target) {
		super();
		this.stdNo = target.callHrdStdNo();
	}

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEnrlSts() {
		return enrlSts;
	}

	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}

	public String getEnrlStsNm() {
		return enrlStsNm;
	}

	public void setEnrlStsNm(String enrlStsNm) {
		this.enrlStsNm = enrlStsNm;
	}

	public String getEduNo() {
		return eduNo;
	}

	public void setEduNo(String eduNo) {
		this.eduNo = eduNo;
	}

	public double getPrgrScore() {
		return prgrScore;
	}

	public void setPrgrScore(double prgrScore) {
		this.prgrScore = prgrScore;
	}

	public double getAttdScore() {
		return attdScore;
	}

	public void setAttdScore(double attdScore) {
		this.attdScore = attdScore;
	}

	public double getExamScore() {
		return examScore;
	}

	public void setExamScore(double examScore) {
		this.examScore = examScore;
	}

	public double getSemiExamScore() {
		return semiExamScore;
	}

	public void setSemiExamScore(double semiExamScore) {
		this.semiExamScore = semiExamScore;
	}

	public double getAsmtScore() {
		return asmtScore;
	}

	public void setAsmtScore(double asmtScore) {
		this.asmtScore = asmtScore;
	}

	public double getForumScore() {
		return forumScore;
	}

	public void setForumScore(double forumScore) {
		this.forumScore = forumScore;
	}

	public double getPrjtScore() {
		return prjtScore;
	}

	public void setPrjtScore(double prjtScore) {
		this.prjtScore = prjtScore;
	}

	public double getJoinScore() {
		return joinScore;
	}

	public void setJoinScore(double joinScore) {
		this.joinScore = joinScore;
	}

	public double getEtcScore() {
		return etcScore;
	}

	public void setEtcScore(double etcScore) {
		this.etcScore = etcScore;
	}

	public double getEtcScore2() {
		return etcScore2;
	}

	public void setEtcScore2(double etcScore2) {
		this.etcScore2 = etcScore2;
	}

	public double getEtcScore3() {
		return etcScore3;
	}

	public void setEtcScore3(double etcScore3) {
		this.etcScore3 = etcScore3;
	}

	public double getEtcScore4() {
		return etcScore4;
	}

	public void setEtcScore4(double etcScore4) {
		this.etcScore4 = etcScore4;
	}

	public double getEtcScore5() {
		return etcScore5;
	}

	public void setEtcScore5(double etcScore5) {
		this.etcScore5 = etcScore5;
	}

	public double getTotScore() {
		return totScore;
	}

	public void setTotScore(double totScore) {
		this.totScore = totScore;
	}

	public double getConvScore() {
		return convScore;
	}

	public void setConvScore(double convScore) {
		this.convScore = convScore;
	}

	public int getTotRatio() {
		return totRatio;
	}

	public void setTotRatio(int totRatio) {
		this.totRatio = totRatio;
	}

	public int getTotBmkTime() {
		return totBmkTime;
	}

	public void setTotBmkTime(int totBmkTime) {
		this.totBmkTime = totBmkTime;
	}

	public String getEnrlDuration() {
		return enrlDuration;
	}

	public void setEnrlDuration(String enrlDuration) {
		this.enrlDuration = enrlDuration;
	}

	public String getResultYn() {
		return resultYn;
	}

	public void setResultYn(String resultYn) {
		this.resultYn = resultYn;
	}

	public String getSbjCd() {
		return sbjCd;
	}

	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	public Integer getDeclsNo() {
		return declsNo;
	}

	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}

	public String getScoreEcltYn() {
		return scoreEcltYn;
	}

	public void setScoreEcltYn(String scoreEcltYn) {
		this.scoreEcltYn = scoreEcltYn;
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

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getStringValue() {
		String retVal = "";
		if(ValidationUtils.isNotEmpty(this.stdNo)) retVal += "stdNo="+this.stdNo+"\n";
		if(ValidationUtils.isNotEmpty(this.crsCreCd)) retVal+= "crsCreCd="+this.crsCreCd+"\n";
		if(ValidationUtils.isNotEmpty(this.userNo)) retVal+= "userNo="+this.userNo+"\n";
		if(ValidationUtils.isNotEmpty(this.userNm)) retVal+= "userNm="+this.userNm+"\n";
		if(ValidationUtils.isNotEmpty(this.userId)) retVal+= "userId="+this.userId+"\n";
		if(ValidationUtils.isNotEmpty(this.email)) retVal+= "email="+this.email+"\n";
		if(ValidationUtils.isNotEmpty(this.birth)) retVal+= "birth="+this.birth+"\n";
		if(ValidationUtils.isNotEmpty(this.enrlSts)) retVal+= "enrlSts="+this.enrlSts+"\n";
		if(ValidationUtils.isNotEmpty(this.enrlStsNm)) retVal+= "enrlStsNm="+this.enrlStsNm+"\n";
		if(ValidationUtils.isNotEmpty(this.eduNo)) retVal+= "eduNo="+this.eduNo+"\n";
		if(ValidationUtils.isNotEmpty(this.prgrScore)) retVal+= "prgrScore="+this.prgrScore+"\n";
		if(ValidationUtils.isNotEmpty(this.attdScore)) retVal+= "attdScore="+this.attdScore+"\n";
		if(ValidationUtils.isNotEmpty(this.examScore)) retVal+= "examScore="+this.examScore+"\n";
		if(ValidationUtils.isNotEmpty(this.asmtScore)) retVal+= "asmtScore="+this.asmtScore+"\n";
		if(ValidationUtils.isNotEmpty(this.forumScore)) retVal+= "forumScore="+this.forumScore+"\n";
		if(ValidationUtils.isNotEmpty(this.prjtScore)) retVal+= "prjtScore="+this.prjtScore+"\n";
		if(ValidationUtils.isNotEmpty(this.joinScore)) retVal+= "joinScore="+this.joinScore+"\n";
		if(ValidationUtils.isNotEmpty(this.etcScore)) retVal+= "etcScore="+this.etcScore+"\n";
		if(ValidationUtils.isNotEmpty(this.totScore)) retVal+= "totScore="+this.totScore+"\n";
		if(ValidationUtils.isNotEmpty(this.convScore)) retVal+= "convScore="+this.convScore+"\n";
		if(ValidationUtils.isNotEmpty(this.totRatio)) retVal+= "totRatio="+this.totRatio+"\n";
		if(ValidationUtils.isNotEmpty(this.totBmkTime)) retVal+= "totBmkTime="+this.totBmkTime+"\n";
		if(ValidationUtils.isNotEmpty(this.enrlDuration)) retVal+= "enrlDuration="+this.enrlDuration+"\n";
		if(ValidationUtils.isNotEmpty(this.scoreEcltYn)) retVal+= "scoreEcltYn="+this.scoreEcltYn+"\n";
		if(ValidationUtils.isNotEmpty(this.resultYn)) retVal+= "resultYn="+this.resultYn+"\n";
		if(ValidationUtils.isNotEmpty(this.sbjCd)) retVal+= "sbjCd="+this.sbjCd+"\n";
		if(ValidationUtils.isNotEmpty(this.declsNo)) retVal+= "declsNo="+this.declsNo+"\n";
		if(ValidationUtils.isNotEmpty(this.banCd)) retVal+= "banCd="+this.banCd+"\n";
		if(ValidationUtils.isNotEmpty(this.gradeCd)) retVal+= "gradeCd="+this.gradeCd+"\n";
		return retVal;
	}

	public String getCrsCreNm() {
		return crsCreNm;
	}

	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}

	public String getCrsTerm() {
		return crsTerm;
	}

	public void setCrsTerm(String crsTerm) {
		this.crsTerm = crsTerm;
	}

	public String getCrsYear() {
		return crsYear;
	}

	public void setCrsYear(String crsYear) {
		this.crsYear = crsYear;
	}

	public String getRefundYn() {
		return refundYn;
	}

	public void setRefundYn(String refundYn) {
		this.refundYn = refundYn;
	}

	public String getCrsNm() {
		return crsNm;
	}

	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}

	public String getCrsCd() {
		return crsCd;
	}

	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}

	public String getCpltNo() {
		return cpltNo;
	}

	public void setCpltNo(String cpltNo) {
		this.cpltNo = cpltNo;
	}

	@Override
	public String callHrdStdNo() {
		return this.stdNo != null 
				? this.stdNo
				: "";
	}

	public int getPrgrRate() {
		return prgrRate;
	}

	public void setPrgrRate(int prgrRate) {
		this.prgrRate = prgrRate;
	}
	
	
}
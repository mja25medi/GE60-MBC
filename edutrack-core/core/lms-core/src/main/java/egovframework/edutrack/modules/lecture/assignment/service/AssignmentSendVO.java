package egovframework.edutrack.modules.lecture.assignment.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class AssignmentSendVO extends DefaultVO {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 4778946101367504886L;
	private String  stdNo       ;
	private String  crsCreCd    ;
	private int		asmtSn      ;
	private String  sendTitle   ;
	private String  sendCts     ;
	private int		sendCnt     ;
	private double	score       ;
	private String  atchCts     ;
	private int		asmtSubSn   ;
	private String scoreYn;	//점수평가 여부

    private String	userNo;
    private String	userNm;
    private String	userId;
    private String	email;
    private String	birth;
    private String 	eduNo;
    private String	mobileNo;

    private String userNoObj;
    private String[] userNoObjArr;

    private int afileNo = 0;


	private List<SysFileVO>	attachFiles;	// 첨부파일 목록

	private String rateYn;//평가여부
	private String regIp;//제출 IP
	private String sendDttm;//제출일자
	private String startFlagYn;
	
	private String copyRatioUri;      //모사 URI
    private String completeStatus ;  //모사 완료 여부
    private int   	totalCopyRatio;   //모사율
    private String dispTotalCopyRatio;  //모사 %
    
	private String rateNo;
	private String rateDttm;
    
    //성적 API 발송 용 필드 (start의 경우 eduResult를 사용하지 않아 사용하는 VO별 선언)
  	private String scoreSaveType;//START,END,RATE,RESET
  	private String scoreCategory;//EXAM,ASMT,BOOKMARK
  	
  	//chatGPT 채점 결과 저장(코딩과제용)
  	private String gptResult;
  	
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

	public String[] getUserNoObjArr() {
		return userNoObjArr;
	}

	public void setUserNoObjArr(String[] userNoObjArr) {
		this.userNoObjArr = userNoObjArr;
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

	public String getSendDttm() {
		return sendDttm;
	}

	public void setSendDttm(String sendDttm) {
		this.sendDttm = sendDttm;
	}

	public int getAfileNo() {
		return this.afileNo;
	}

	public void setAfileNo(int afileNo) {
		this.afileNo = afileNo;
	}


	public AssignmentSendVO(){

	}

	public String getStdNo() {
		return this.stdNo;
	}


	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}


	public String getCrsCreCd() {
		return this.crsCreCd;
	}


	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}


	public int getAsmtSn() {
		return this.asmtSn;
	}


	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}


	public String getSendTitle() {
		return this.sendTitle;
	}


	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}


	public String getSendCts() {
		return this.sendCts;
	}


	public void setSendCts(String sendCts) {
		this.sendCts = sendCts;
	}


	public int getSendCnt() {
		return this.sendCnt;
	}


	public void setSendCnt(int sendCnt) {
		this.sendCnt = sendCnt;
	}


	public double getScore() {
		return this.score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public String getAtchCts() {
		return this.atchCts;
	}


	public void setAtchCts(String atchCts) {
		this.atchCts = atchCts;
	}


	public int getAsmtSubSn() {
		return this.asmtSubSn;
	}


	public void setAsmtSubSn(int asmtSubSn) {
		this.asmtSubSn = asmtSubSn;
	}


	public String getUserNo() {
		return this.userNo;
	}


	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	public String getUserNm() {
		return this.userNm;
	}


	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}


	public String getUserId() {
		return this.userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBirth() {
		return this.birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getEduNo() {
		return this.eduNo;
	}


	public void setEduNo(String eduNo) {
		this.eduNo = eduNo;
	}


	public String getMobileNo() {
		return this.mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}

	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}

	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}

	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}




	public String getUserNoObj() {
		return this.userNoObj;
	}




	public void setUserNoObj(String userNoObj) {
		this.userNoObj = userNoObj;
	}



	public String getRateYn() {
		return rateYn;
	}



	public void setRateYn(String rateYn) {
		this.rateYn = rateYn;
	}



	public String getScoreYn() {
		return scoreYn;
	}



	public void setScoreYn(String scoreYn) {
		this.scoreYn = scoreYn;
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

	public int getTotalCopyRatio() {
		return totalCopyRatio;
	}

	public void setTotalCopyRatio(int totalCopyRatio) {
		this.totalCopyRatio = totalCopyRatio;
	}

	public String getDispTotalCopyRatio() {
		return dispTotalCopyRatio;
	}

	public void setDispTotalCopyRatio(String dispTotalCopyRatio) {
		this.dispTotalCopyRatio = dispTotalCopyRatio;
	}

	public String getRateNo() {
		return rateNo;
	}

	public void setRateNo(String rateNo) {
		this.rateNo = rateNo;
	}

	public String getRateDttm() {
		return rateDttm;
	}

	public void setRateDttm(String rateDttm) {
		this.rateDttm = rateDttm;
	}

	public String getGptResult() {
		return gptResult;
	}

	public void setGptResult(String gptResult) {
		this.gptResult = gptResult;
	}
}
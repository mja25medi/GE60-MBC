package egovframework.edutrack.modules.log.message.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class LogMsgLogVO extends DefaultVO{

	private static final long serialVersionUID = 6954758324630386155L;

	private Integer					msgSn;
	private String					msgDivCd;
	private String					sendMenuCd;
	private String					title;
	private String					cts;
	private String					sendNm;
	private String					sendAddr;
	private String					rsrvSendDttm;
	private String					sendSts;
	
	private String					sendId;

	private String					startDttm;
	private String					endDttm;

	private Integer					transCount;
	private Integer					transSuccessCount;		
	private Integer					transDenialCount;
	private Integer					transFailCount;
	private Integer					transStandbyCount;
	private Integer					topCnt;
	
	private String					transSts;
	private String					transDttm;
	private Integer					msgTransSn;

	private List<SysFileVO>			attachFiles;								// 첨부파일 목록

	private String					sendMenuCdNm;

	private LogMsgTransLogVO			logMsgTransLogVO				= new LogMsgTransLogVO();

	private List<LogMsgTransLogVO>	subTransList;

	private MessageDecorator		messageDecorator;

	private Integer					prevMsgSn;

	private String					orgCd;
	
	private Integer					msgGrpSn;									// 학습독려 메세지 묶음
	
	private String 					userNo;
	
	// 메시지 예약시간 입력 (액션에서 messageDTO.setRsrvDttm으로 합치는 작업을 처리한다.)
	private String				rsrvDate;
	private String				rsrvHour;
	private String				rsrvMin;
	
	public LogMsgLogVO setTempTitleForNull() {
		if(title == null || "".equals(title)) {
			String ctsValue = StringUtil.nvl(cts, "[내용없음]");
			String tempTitle = ctsValue.length() > 20 ? ctsValue.substring(0, 20) +"..." : ctsValue;
			this.title = tempTitle;
		}
		return this;
	}
	
	public Integer getMsgGrpSn() {
		return msgGrpSn;
	}

	public void setMsgGrpSn(Integer msgGrpSn) {
		this.msgGrpSn = msgGrpSn;
	}

	public Integer getMsgSn() {
		return this.msgSn;
	}

	public void setMsgSn(Integer msgSn) {
		this.msgSn = msgSn;
	}

	public String getMsgDivCd() {
		return this.msgDivCd;
	}

	public void setMsgDivCd(String msgDivCd) {
		this.msgDivCd = msgDivCd;
	}

	public String getSendMenuCd() {
		return this.sendMenuCd;
	}

	public void setSendMenuCd(String sendMenuCd) {
		this.sendMenuCd = sendMenuCd;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCts() {
		return this.cts;
	}

	public void setCts(String cts) {
		this.cts = cts;
	}

	public String getSendNm() {
		return this.sendNm;
	}

	public void setSendNm(String sendNm) {
		this.sendNm = sendNm;
	}

	public String getSendAddr() {
		return this.sendAddr;
	}

	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}

	public String getRsrvSendDttm() {
		return this.rsrvSendDttm;
	}

	public void setRsrvSendDttm(String rsrvSendDttm) {
		this.rsrvSendDttm = rsrvSendDttm;
	}

	public String getSendSts() {
		return this.sendSts;
	}

	public void setSendSts(String sendSts) {
		this.sendSts = sendSts;
	}


	public List<LogMsgTransLogVO> getSubTransList() {
		if (this.subTransList == null)
			this.subTransList = new ArrayList<LogMsgTransLogVO>();
		return this.subTransList;
	}

	public void setSubTransList(List<LogMsgTransLogVO> subTransList) {
		for (LogMsgTransLogVO LogMsgTransLogVO : subTransList) {
			LogMsgTransLogVO.setMessage(this);
		}
		this.subTransList = subTransList;
	}

	public void addSubTrans(LogMsgTransLogVO trans) {
		trans.setMessage(this);
		this.getSubTransList().add(trans);
	}

	public Integer getTransCount() {
		return this.transCount;
	}

	public void setTransCount(Integer transCount) {
		this.transCount = transCount;
	}

	public String getSendMenuCdNm() {
		return this.sendMenuCdNm;
	}

	public void setSendMenuCdNm(String sendMenuCdNm) {
		this.sendMenuCdNm = sendMenuCdNm;
	}

	public void setMessageDecorator(MessageDecorator messageDecorator) {
		this.messageDecorator = messageDecorator;
	}

	public String getTransSts() {
		return transSts;
	}

	public void setTransSts(String transSts) {
		this.transSts = transSts;
	}

	public String getTransDttm() {
		return transDttm;
	}

	public Integer getMsgTransSn() {
		return msgTransSn;
	}

	public void setMsgTransSn(Integer msgTransSn) {
		this.msgTransSn = msgTransSn;
	}

	public void setTransDttm(String transDttm) {
		this.transDttm = transDttm;
	}

	/**
	 * 메시지 데코레이터가 null이 아니면 HTML메시지임.
	 *
	 * @return
	 */
	public boolean isHtml() {
		return (this.messageDecorator != null);
	}

	/**
	 * 데코레이터에 의해 HTML로 변형된 메시지를 반환한다. 데코레이터가 null일 경우 원형 메시지를 반환한다.
	 *
	 * @return
	 */
	public String getCtsHtml() {
		if (this.isHtml())
			return this.messageDecorator.decorate(this.cts);
		else
			return this.getCts();
	}

	public Integer getTransSuccessCount() {
		return this.transSuccessCount;
	}

	public void setTransSuccessCount(Integer transSuccessCount) {
		this.transSuccessCount = transSuccessCount;
	}

	public Integer getTransDenialCount() {
		return this.transDenialCount;
	}

	public void setTransDenialCount(Integer transDenialCount) {
		this.transDenialCount = transDenialCount;
	}

	public Integer getTransFailCount() {
		return this.transFailCount;
	}

	public void setTransFailCount(Integer transFailCount) {
		this.transFailCount = transFailCount;
	}

	public String getStartDttm() {
		return this.startDttm;
	}

	public void setStartDttm(String startDttm) {
		this.startDttm = startDttm;
	}

	public String getEndDttm() {
		return this.endDttm;
	}

	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}

	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}

	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}

	/* 첨부파일 핸들링용 매서드 */
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}

	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}

	public Integer getTransStandbyCount() {
		return transStandbyCount;
	}

	public void setTransStandbyCount(Integer transStandbyCount) {
		this.transStandbyCount = transStandbyCount;
	}

	public Integer getPrevMsgSn() {
		return prevMsgSn;
	}

	public void setPrevMsgSn(Integer prevMsgSn) {
		this.prevMsgSn = prevMsgSn;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}



	public String getRsrvDate() {
		return rsrvDate;
	}

	public void setRsrvDate(String rsrvDate) {
		this.rsrvDate = rsrvDate;
	}

	public String getRsrvHour() {
		return rsrvHour;
	}

	public void setRsrvHour(String rsrvHour) {
		this.rsrvHour = rsrvHour;
	}

	public String getRsrvMin() {
		return rsrvMin;
	}

	public void setRsrvMin(String rsrvMin) {
		this.rsrvMin = rsrvMin;
	}

	public LogMsgTransLogVO getLogMsgTransLogVO() {
		return logMsgTransLogVO;
	}

	public void setLogMsgTransLogVO(LogMsgTransLogVO logMsgTransLogVO) {
		this.logMsgTransLogVO = logMsgTransLogVO;
	}

	public Integer getTopCnt() {
		return topCnt;
	}

	public void setTopCnt(Integer topCnt) {
		this.topCnt = topCnt;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	
}

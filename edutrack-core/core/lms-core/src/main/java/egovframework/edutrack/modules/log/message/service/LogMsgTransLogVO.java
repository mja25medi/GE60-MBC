package egovframework.edutrack.modules.log.message.service;



public class LogMsgTransLogVO {

	public static final String	SEND_SUCCES	= "S";
	public static final String	SEND_FAILED	= "F";
	public static final String	SEND_WATING	= "R";
	public static final String	SEND_DENIED	= "D";
	public static final String	SEARCH_TRANS_STATUS	= "TRANS_SEARCH";

	private Integer				msgTransSn;
	private String				recvNm;
	private String				recvAddr;
	private String				transSts;

	private String				transDttm;
	private String				errorMsg;

	private String				recvYn;

	private String				transStsNm;
	
	private String				msgDivCd;

	private LogMsgLogVO			message;
	
	//2017.02.02 학습독려메세지
	private String userNo;
	private Integer knowSn;
	
	// 페이징 관련 파라미터
	private int pageIndex = 1;
	private int firstIndex = 1;
	private int lastIndex = 1;
	private int listScale = 20;
	private int pageScale = 10;	
	
	
	
	private String userNoList;
	private String stdNoList;
	private String etcMbrSnList;
	private String entrySnList;
	private String[] userNoListArr;
	private String[] stdNoListArr;
	
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Integer getKnowSn() {
		return knowSn;
	}

	public void setKnowSn(Integer knowSn) {
		this.knowSn = knowSn;
	}

	public Integer getMsgTransSn() {
		return this.msgTransSn;
	}

	public void setMsgTransSn(Integer msgTransSn) {
		this.msgTransSn = msgTransSn;
	}

	public Integer getMsgSn() {
		return this.getMessage().getMsgSn();
	}

	public void setMsgSn(Integer msgSn) {
		this.getMessage().setMsgSn(msgSn);
	}
	
	public Integer getPrevMsgSn() {
		return this.getMessage().getPrevMsgSn();
	}

	public void setPrevMsgSn(Integer prevMsgSn) {
		this.getMessage().setPrevMsgSn(prevMsgSn);
	}

	public String getRecvNm() {
		return this.recvNm;
	}

	public void setRecvNm(String recvNm) {
		this.recvNm = recvNm;
	}

	public String getRecvAddr() {
		return this.recvAddr;
	}

	public void setRecvAddr(String recvAddr) {
		this.recvAddr = recvAddr;
	}

	public String getTransSts() {
		return this.transSts;
	}

	public void setTransSts(String transSts) {
		this.transSts = transSts;
	}

	public String getTransDttm() {
		return this.transDttm;
	}

	public void setTransDttm(String transDttm) {
		this.transDttm = transDttm;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorCd) {
		this.errorMsg = errorCd;
	}

	public String getRecvYn() {
		return this.recvYn;
	}

	public void setRecvYn(String recvYn) {
		this.recvYn = recvYn;
	}

	public String getTransStsNm() {
		return this.transStsNm;
	}

	public void setTransStsNm(String transStsNm) {
		this.transStsNm = transStsNm;
	}

	public LogMsgLogVO getMessage() {
		if(this.message == null) {
			this.message = new LogMsgLogVO();
		}
		return this.message;
	}

	public void setMessage(LogMsgLogVO message) {
		this.message = message;
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

	public String getMsgDivCd() {
		return msgDivCd;
	}

	public void setMsgDivCd(String msgDivCd) {
		this.msgDivCd = msgDivCd;
	}
	
	public String getUserNoList() {
		return this.userNoList;
	}
	
	public void setUserNoList(String userNoList) {
		this.userNoList = userNoList;
	}
	
	public String getStdNoList() {
		return this.stdNoList;
	}
	
	public void setStdNoList(String stdNoList) {
		this.stdNoList = stdNoList;
	}
	
	public String getEtcMbrSnList() {
		return this.etcMbrSnList;
	}
	
	public void setEtcMbrSnList(String etcMbrSnList) {
		this.etcMbrSnList = etcMbrSnList;
	}
	
	public String getEntrySnList() {
		return this.entrySnList;
	}
	
	public void setEntrySnList(String entrySnList) {
		this.entrySnList = entrySnList;
	}

	public String[] getUserNoListArr() {
		return userNoListArr;
	}

	public void setUserNoListArr(String[] userNoListArr) {
		this.userNoListArr = userNoListArr;
	}

	public String[] getStdNoListArr() {
		return stdNoListArr;
	}

	public void setStdNoListArr(String[] stdNoListArr) {
		this.stdNoListArr = stdNoListArr;
	}
	

}

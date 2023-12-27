package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj.SyncType;
import egovframework.edutrack.comm.service.DefaultVO;

public class HrdApiOnlnSbjVO extends DefaultVO {

	private static final long serialVersionUID = 5577973900897577256L;
	
	private int 	num;               //'자동증가번호'
	private String 	agentPk;        //'훈련기관 PK'
	private int 	seq;               //'시퀀스'
	private String	courseAgentPk;  //'훈련기관 과정PK'
	private String 	name;		   //'과정 이름'
	private String 	simsaCode;	   //'심사 코드'
	private String 	postCourseFlag; //'우편과정 여부'
	private String 	changeState;	   //'변경 상태'
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regDate; //'등록 일시'
	
	private String 	tracseId;	   //'HRD-Net 훈련과정코드'
	
	private String syncStatus;	   //연동상태
	private String syncResultMsg;  //연동결과 메시지
	
	public HrdApiOnlnSbjVO() {}
	
	public HrdApiOnlnSbjVO(HrdCourseSendable target, SyncType syncType) {
		super();
		this.defaultSetSbjInfo(target);
		this.changeState = syncType.getStringValue();
	}
	
	public void defaultSetSbjInfo(HrdCourseSendable target) {
		this.agentPk = "edulife";
		this.courseAgentPk = target.callHrdCouresAgentPk();
		this.name = target.callHrdName();
		this.simsaCode = target.callHrdSimsaCode();
		this.postCourseFlag = "0";
		this.tracseId = target.callHrdTracseId();
		this.syncStatus = "W";
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(String agentPk) {
		this.agentPk = agentPk;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCourseAgentPk() {
		return courseAgentPk;
	}
	public void setCourseAgentPk(String courseAgentPk) {
		this.courseAgentPk = courseAgentPk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSimsaCode() {
		return simsaCode;
	}
	public void setSimsaCode(String simsaCode) {
		this.simsaCode = simsaCode;
	}
	public String getPostCourseFlag() {
		return postCourseFlag;
	}
	public void setPostCourseFlag(String postCourseFlag) {
		this.postCourseFlag = postCourseFlag;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getTracseId() {
		return tracseId;
	}
	public void setTracseId(String tracseId) {
		this.tracseId = tracseId;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	public String getSyncResultMsg() {
		return syncResultMsg;
	}
	public void setSyncResultMsg(String syncResultMsg) {
		this.syncResultMsg = syncResultMsg;
	}
}

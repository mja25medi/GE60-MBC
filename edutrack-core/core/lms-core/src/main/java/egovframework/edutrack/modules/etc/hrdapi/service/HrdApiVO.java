package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HrdApiVO extends DefaultVO {

	private String agentPk;
	private String seq;
	private String userAgentPk;
	private String userName;
	private String resNo;
	private String encResNo;
	private String email;
	private String mobile;
	private String changeState;
	private String regDate;
	private String nwIno;
	private String trneeSe;
	private String irglbrSe;
	private String apiKey;
	private String url;
	private String userId;
	
	private String status;
	private String code;
	private String msg;
	private String data_cnt;
	private JSONObject detail;
	
	
	public String getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(String agentPk) {
		this.agentPk = agentPk;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getUserAgentPk() {
		return userAgentPk;
	}
	public void setUserAgentPk(String userAgentPk) {
		this.userAgentPk = userAgentPk;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getResNo() {
		return resNo;
	}
	public void setResNo(String resNo) {
		this.resNo = resNo;
	}
	public String getEncResNo() {
		return encResNo;
	}
	public void setEncResNo(String encResNo) {
		this.encResNo = encResNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getNwIno() {
		return nwIno;
	}
	public void setNwIno(String nwIno) {
		this.nwIno = nwIno;
	}
	public String getTrneeSe() {
		return trneeSe;
	}
	public void setTrneeSe(String trneeSe) {
		this.trneeSe = trneeSe;
	}
	public String getIrglbrSe() {
		return irglbrSe;
	}
	public void setIrglbrSe(String irglbrSe) {
		this.irglbrSe = irglbrSe;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData_cnt() {
		return data_cnt;
	}
	public void setData_cnt(String data_cnt) {
		this.data_cnt = data_cnt;
	}
	public JSONObject getDetail() {
		return detail;
	}
	public void setDetail(JSONObject detail) {
		this.detail = detail;
	}
}

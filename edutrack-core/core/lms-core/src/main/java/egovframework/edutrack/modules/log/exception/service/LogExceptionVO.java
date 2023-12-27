package egovframework.edutrack.modules.log.exception.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogExceptionVO extends DefaultVO {

	private static final long serialVersionUID = -762668367513264585L;
	private String	exceptionDiv;
	private String	originDt;
	private String	methodNm;
	private String	exceptionNm;
	private String	stackTrace;
	private String	modDttm;
	private Integer	originCnt;

	public String getExceptionDiv() {
		return this.exceptionDiv;
	}

	public void setExceptionDiv(String exceptionSrc) {
		this.exceptionDiv = exceptionSrc;
	}

	public String getOriginDt() {
		return this.originDt;
	}

	public void setOriginDt(String originDt) {
		this.originDt = originDt;
	}

	public String getMethodNm() {
		return this.methodNm;
	}

	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}

	public String getExceptionNm() {
		return this.exceptionNm;
	}

	public void setExceptionNm(String exceptionNm) {
		this.exceptionNm = exceptionNm;
	}

	public String getStackTrace() {
		return this.stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getModDttm() {
		return this.modDttm;
	}

	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}

	public Integer getOriginCnt() {
		return this.originCnt;
	}

	public void setOriginCnt(Integer originCnt) {
		this.originCnt = originCnt;
	}
}

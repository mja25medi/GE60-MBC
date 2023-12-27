package egovframework.edutrack.modules.org.ide.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgIdeInfoVO   extends DefaultVO {
	
	
	private static final long serialVersionUID = 1015491435217112595L;
	
	private String  ideUrl;
	private String  orgCd;
	private String  useYn;
	
	private String fileName;
	private String filePath;
	
	//-- excel upload용으로 추가
	private String  lineNo;
	private String  errorCode;
	
	public String getIdeUrl() {
		return ideUrl;
	}
	public void setIdeUrl(String ideUrl) {
		this.ideUrl = ideUrl;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}

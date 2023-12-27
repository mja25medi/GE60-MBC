package egovframework.edutrack.modules.org.code.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;

public class OrgCodeVO extends DefaultVO{

	private static final long serialVersionUID = -2693102996681703820L;
	
	private String	orgCd;
	private String	codeCtgrCd;
	private String	codeCd;
	private String	codeNm;
	private String	useYn = "Y";
	private String	useYnNm = "Y";
	private String	codeDesc;
	private String	codeOptn;
	private int		codeOdr;
	private String	regName;
	private String	modName;
	
	private String autoMakeYn;

	//-- excel upload용으로 추가
	private String  lineNo;
	private String  errorCode;
	private String  isUseable = "N";

	private List<OrgCodeLangVO> codeLangList;

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getCodeCtgrCd() {
		return codeCtgrCd;
	}

	public void setCodeCtgrCd(String codeCtgrCd) {
		this.codeCtgrCd = codeCtgrCd;
	}

	public String getCodeCd() {
		return codeCd;
	}

	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodeOptn() {
		return codeOptn;
	}

	public void setCodeOptn(String codeOptn) {
		this.codeOptn = codeOptn;
	}

	public int getCodeOdr() {
		return codeOdr;
	}

	public void setCodeOdr(int codeOdr) {
		this.codeOdr = codeOdr;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public List<OrgCodeLangVO> getCodeLangList() {
		if(ValidationUtils.isEmpty(codeLangList)) codeLangList = new ArrayList<OrgCodeLangVO>();
		return codeLangList;
	}

	public void setCodeLangList(List<OrgCodeLangVO> codeLangList) {
		this.codeLangList = codeLangList;
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

	public String getIsUseable() {
		return isUseable;
	}

	public void setIsUseable(String isUseable) {
		this.isUseable = isUseable;
	}

	public String getAutoMakeYn() {
		return autoMakeYn;
	}

	public void setAutoMakeYn(String autoMakeYn) {
		this.autoMakeYn = autoMakeYn;
	}
	
	public String getUseYnNm() {
		return useYnNm;
	}

	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	
}

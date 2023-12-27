package egovframework.edutrack.modules.system.code.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;

public class SysCodeVO extends DefaultVO {

	private static final long serialVersionUID = 5090990479351461516L;
	
	private String	codeCtgrCd;
	private String	codeCd;
	private String	codeNm;
	private int		codeOdr;
	private String	codeDesc;
	private String	codeOptn;
	private String	useYn;

	private Integer subCnt = 0;

	private List<SysCodeLangVO> codeLangList;
	
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

	public int getCodeOdr() {
		return codeOdr;
	}

	public void setCodeOdr(int codeOdr) {
		this.codeOdr = codeOdr;
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

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Integer getSubCnt() {
		return subCnt;
	}

	public void setSubCnt(Integer subCnt) {
		this.subCnt = subCnt;
	}

	public List<SysCodeLangVO> getCodeLangList() {
		if(ValidationUtils.isEmpty(codeLangList)) codeLangList = new ArrayList<SysCodeLangVO>();
		return codeLangList;
	}

	public void setCodeLangList(List<SysCodeLangVO> codeLangList) {
		this.codeLangList = codeLangList;
	}

}

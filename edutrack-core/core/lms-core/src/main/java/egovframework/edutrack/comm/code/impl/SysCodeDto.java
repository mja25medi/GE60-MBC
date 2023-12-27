package egovframework.edutrack.comm.code.impl;

import egovframework.edutrack.comm.code.CodeType;

public class SysCodeDto {
	String codeCd;
	String codeNm;
	
	public SysCodeDto(CodeType code) {
		this.codeCd = code.getCodeTypeCd();
		this.codeNm = code.getCodeTypeNm();
	}

	public String getCodeCd() {
		return codeCd;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	
	
}

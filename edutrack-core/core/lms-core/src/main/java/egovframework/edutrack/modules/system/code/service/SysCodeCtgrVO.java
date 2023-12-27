package egovframework.edutrack.modules.system.code.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class SysCodeCtgrVO extends DefaultVO {

	private static final long serialVersionUID = 3868841871775315453L;

	private String 	codeCtgrCd;
	private String 	codeCtgrNm;
	private int		codeCtgrOdr;
	private String	codeCtgrDesc;
	private String	useYn;
	private int		subCnt = 0;
	
	public String getCodeCtgrCd() {
		return codeCtgrCd;
	}
	public void setCodeCtgrCd(String codeCtgrCd) {
		this.codeCtgrCd = codeCtgrCd;
	}
	public String getCodeCtgrNm() {
		return codeCtgrNm;
	}
	public void setCodeCtgrNm(String codeCtgrNm) {
		this.codeCtgrNm = codeCtgrNm;
	}
	public int getCodeCtgrOdr() {
		return codeCtgrOdr;
	}
	public void setCodeCtgrOdr(int codeCtgrOdr) {
		this.codeCtgrOdr = codeCtgrOdr;
	}
	public String getCodeCtgrDesc() {
		return codeCtgrDesc;
	}
	public void setCodeCtgrDesc(String codeCtgrDesc) {
		this.codeCtgrDesc = codeCtgrDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getSubCnt() {
		return subCnt;
	}
	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}
}

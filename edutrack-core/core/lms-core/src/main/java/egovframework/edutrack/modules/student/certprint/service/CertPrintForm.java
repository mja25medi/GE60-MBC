package egovframework.edutrack.modules.student.certprint.service;

import java.util.List;


@SuppressWarnings("serial")
public class CertPrintForm{
	
	private CertPrintVO certPrintVO;
	private List<CertPrintVO> certPrintList;
	
	public CertPrintForm() {
		this.certPrintVO = new CertPrintVO();
	}
	
	public CertPrintVO getCertPrintVO() {
		return this.certPrintVO;
	}
	
	public void setCertPrintVO(CertPrintVO certPrintVO) {
		this.certPrintVO = certPrintVO;
	}
	
	public List<CertPrintVO> getCertPrintList() {
		return this.certPrintList;
	}
	
	public void setCertPrintList(List<CertPrintVO> certPrintList) {
		this.certPrintList = certPrintList;
	}
	
	
}

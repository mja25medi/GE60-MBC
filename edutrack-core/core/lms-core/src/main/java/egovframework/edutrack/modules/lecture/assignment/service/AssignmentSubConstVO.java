package egovframework.edutrack.modules.lecture.assignment.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class AssignmentSubConstVO
		extends DefaultVO {

	private static final long	serialVersionUID	= -7328506918474481750L;
	private String				crsCreCd;										// VARCHAR2(10 BYTE)

	private int					asmtSn;										// NUMBER(9)
	private int					asmtSubSn;										// NUMBER(9)
	private int					constSn;										// NUMBER(9)
	private String				constCts;


	
	

	public int getConstSn() {
		return constSn;
	}

	public void setConstSn(int constSn) {
		this.constSn = constSn;
	}

	public String getConstCts() {
		return constCts;
	}

	public void setConstCts(String constCts) {
		this.constCts = constCts;
	}

	public AssignmentSubConstVO() {}

	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public int getAsmtSn() {
		return this.asmtSn;
	}

	public void setAsmtSn(int asmtSn) {
		this.asmtSn = asmtSn;
	}

	public int getAsmtSubSn() {
		return this.asmtSubSn;
	}

	public void setAsmtSubSn(int asmtSubSn) {
		this.asmtSubSn = asmtSubSn;
	}

}
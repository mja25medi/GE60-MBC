package egovframework.edutrack.modules.lecture.exam.service;

import java.io.Serializable;

import egovframework.edutrack.modules.system.file.service.SysFileVO;


public class ExamQuestionFileVO extends SysFileVO implements Serializable {

	private static final long	serialVersionUID	= 2342207266477182214L;

	private String 	crsCreCd    ;//VARCHAR2(10 BYTE)           
	private int		examSn      ;// NUMBER(9)                  
	private int		examQstnSn  ;//NUMBER(9)

	public ExamQuestionFileVO() {
		super();		
	}
	
	public ExamQuestionFileVO(String crsCreCd, int examSn, int examQstnSn, Integer fileSn, String fileType) {
		super();
		this.crsCreCd = crsCreCd;
		this.examSn = examSn;
		this.examQstnSn = examQstnSn;
		super.fileSn = fileSn;
		super.fileType = fileType;
	}

	/**
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	
	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	
	/**
	 * @return the examSn
	 */
	public int getExamSn() {
		return this.examSn;
	}

	
	/**
	 * @param examSn the examSn to set
	 */
	public void setExamSn(int examSn) {
		this.examSn = examSn;
	}

	
	/**
	 * @return the examQstnSn
	 */
	public int getExamQstnSn() {
		return this.examQstnSn;
	}

	
	/**
	 * @param examQstnSn the examQstnSn to set
	 */
	public void setExamQstnSn(int examQstnSn) {
		this.examQstnSn = examQstnSn;
	}
	
}
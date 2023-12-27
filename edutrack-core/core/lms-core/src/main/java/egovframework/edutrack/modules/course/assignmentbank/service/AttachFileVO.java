package egovframework.edutrack.modules.course.assignmentbank.service;

import java.io.Serializable;

import egovframework.edutrack.modules.system.file.service.SysFileVO;

/**
 * 게시판 - 일반 게시글 첨부파일 VO
 * 시스템 파일을 상속
 * <pre>
 * 업  무 : 게시판 - 일반 게시판
 * </pre>
 * @author SungKook
 */
public class AttachFileVO extends SysFileVO implements Serializable {
	private static final long	serialVersionUID	= 8914041444855158152L;

	private Integer qstnSn;      // 문제은행 파일 고유번호
	private String  sbjCd;      // 문제은행과목코드 
	private String  ctgrCd;      // 문제은행 카테고리 코드

	public AttachFileVO() {
		super();
	}

	public AttachFileVO(String ctgrCd,String sbjCd,Integer qstnSn, Integer fileSn, String fileType) {
		super();
		this.ctgrCd =ctgrCd;
		this.sbjCd = sbjCd;
		this.qstnSn = qstnSn;
		super.fileSn = fileSn;
		super.fileType = fileType;
	}

	public Integer getQstnSn() {
        return qstnSn;
    }

    public void seQstnSn(Integer qstnSn) {
        this.qstnSn = qstnSn;
    }

    public String  getSbjCd() {
        return sbjCd;
    }
    
    public void setSbjCd(String sbjCd ) {
    	this.sbjCd =sbjCd;
    	
    }

	/**
	 * @return the ctgrCd
	 */
	public String getCtgrCd() {
		return ctgrCd;
	}

	/**
	 * @param ctgrCd the ctgrCd to set
	 */
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
}

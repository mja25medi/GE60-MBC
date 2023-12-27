package egovframework.edutrack.modules.lecture.forum.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class ForumCmntVO  extends DefaultVO {
	
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 8408545630189351826L;
	private String  crsCreCd   ;  // VARCHAR2(10 BYTE)      
    private Integer  forumSn    ;  // NUMBER(9)              
    private Integer  atclSn     ;  // NUMBER(9)              
    private Integer  cmntSn     ;  // NUMBER(9)              
    private String  cmntCts    ;  // NCLOB,                 
    private String  emoticonNo ;  // NUMBER(9),             
    private String  regNm      ;  // NVARCHAR2(20),         
    
	public ForumCmntVO(){}

	
	public String getCrsCreCd() {
		return this.crsCreCd;
	}

	
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	
	public Integer getForumSn() {
		return this.forumSn;
	}

	
	public void setForumSn(Integer forumSn) {
		this.forumSn = forumSn;
	}

	public Integer getAtclSn() {
		return atclSn;
	}


	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}


	
	public Integer getCmntSn() {
		return this.cmntSn;
	}

	
	public void setCmntSn(Integer cmntSn) {
		this.cmntSn = cmntSn;
	}

	
	public String getCmntCts() {
		return this.cmntCts;
	}

	
	public void setCmntCts(String cmntCts) {
		this.cmntCts = cmntCts;
	}

	
	public String getEmoticonNo() {
		return this.emoticonNo;
	}

	
	public void setEmoticonNo(String emoticonNo) {
		this.emoticonNo = emoticonNo;
	}

	
	public String getRegNm() {
		return this.regNm;
	}

	
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
}
	
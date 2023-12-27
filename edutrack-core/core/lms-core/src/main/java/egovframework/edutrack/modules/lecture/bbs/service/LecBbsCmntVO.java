package egovframework.edutrack.modules.lecture.bbs.service;

import egovframework.edutrack.comm.service.DefaultVO;


public class LecBbsCmntVO  extends DefaultVO {


    /**
	 * 
	 */
	private static final long	serialVersionUID	= -5329653052136773838L;
	private String crsCreCd   ; //VARCHAR2(10 BYTE)    
    private String bbsCd      ; // NUMBER(9)           
    private int    arclSn     ; // NUMBER(9)           
    private Integer    cmntSn     ; // NUMBER(9)           
    private String cmntCts    ; // NCLOB,              
    private Integer     emoticonNo ; // NUMBER(9),         
    private String regNm      ; // NVARCHAR2(20),      
  
    private Integer atclSn;	//NUMBER(19)
    
    public  LecBbsCmntVO(){
    	
    }

	/**
	 * @return the crsCreCd
	 */
	public String getCrsCreCd() {
		return crsCreCd;
	}

	/**
	 * @param crsCreCd the crsCreCd to set
	 */
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	/**
	 * @return the bbsSn
	 */
	public String getBbsCd() {
		return bbsCd;
	}

	/**
	 * @param bbsSn the bbsSn to set
	 */
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}

	/**
	 * @return the arclSn
	 */
	public int getArclSn() {
		return arclSn;
	}

	/**
	 * @param arclSn the arclSn to set
	 */
	public void setArclSn(int arclSn) {
		this.arclSn = arclSn;
	}

	/**
	 * @return the cmntSn
	 */
	public Integer getCmntSn() {
		return cmntSn;
	}

	/**
	 * @param cmntSn the cmntSn to set
	 */
	public void setCmntSn(Integer cmntSn) {
		this.cmntSn = cmntSn;
	}

	/**
	 * @return the cmntCts
	 */
	public String getCmntCts() {
		return cmntCts;
	}

	/**
	 * @param cmntCts the cmntCts to set
	 */
	public void setCmntCts(String cmntCts) {
		this.cmntCts = cmntCts;
	}

	/**
	 * @return the emoticonNo
	 */
	public Integer getEmoticonNo() {
		return emoticonNo;
	}

	/**
	 * @param emoticonNo the emoticonNo to set
	 */
	public void setEmoticonNo(Integer emoticonNo) {
		this.emoticonNo = emoticonNo;
	}

	/**
	 * @return the regNm
	 */
	public String getRegNm() {
		return regNm;
	}

	/**
	 * @param regNm the regNm to set
	 */
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public Integer getAtclSn() {
		return atclSn;
	}

	public void setAtclSn(Integer atclSn) {
		this.atclSn = atclSn;
	}

	
	

}
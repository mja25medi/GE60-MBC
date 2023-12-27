package egovframework.edutrack.modules.student.sync.service;

import egovframework.edutrack.comm.service.DefaultVO;


/**
 * EAI 연계 VO
 */
public class EaiNkoreducompsendVO
		extends DefaultVO {
	
	private static final long	serialVersionUID	= -8735069446190815220L;
	private String	eainum;
	private String	linkid;
	private String	eaicivildocnum;
	private String	nm;
	private String	residregistnum;
	private String	educompdate;
	private String	educlass;
	private String	visitareacd;
	private String	purposelargeroup;
	private String	cell;
	private String	addr1;
	private String	addr2;
	private String	postno;
	private String	tel;
	private String	companytelnum;
	private String	turninpersid;
	private String	linkstatus;
	
	public String getEainum() {
		return this.eainum;
	}
	
	public void setEainum(String eainum) {
		this.eainum = eainum;
	}
	
	public String getLinkid() {
		return this.linkid;
	}
	
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	
	public String getEaicivildocnum() {
		return this.eaicivildocnum;
	}
	
	public void setEaicivildocnum(String eaicivildocnum) {
		this.eaicivildocnum = eaicivildocnum;
	}
	
	public String getNm() {
		return this.nm;
	}
	
	public void setNm(String nm) {
		this.nm = nm;
	}
	
	public String getResidregistnum() {
		return this.residregistnum;
	}
	
	public void setResidregistnum(String residregistnum) {
		this.residregistnum = residregistnum;
	}
	
	public String getEducompdate() {
		return this.educompdate;
	}
	
	public void setEducompdate(String educompdate) {
		this.educompdate = educompdate;
	}
	
	public String getEduclass() {
		return this.educlass;
	}
	
	public void setEduclass(String educlass) {
		this.educlass = educlass;
	}
	
	public String getVisitareacd() {
		return this.visitareacd;
	}
	
	public void setVisitareacd(String visitareacd) {
		this.visitareacd = visitareacd;
	}
	
	public String getPurposelargeroup() {
		return this.purposelargeroup;
	}
	
	public void setPurposelargeroup(String purposelargeroup) {
		this.purposelargeroup = purposelargeroup;
	}
	
	public String getCell() {
		return this.cell;
	}
	
	public void setCell(String cell) {
		this.cell = cell;
	}
	
	public String getAddr1() {
		return this.addr1;
	}
	
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	public String getAddr2() {
		return this.addr2;
	}
	
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public String getPostno() {
		return this.postno;
	}
	
	public void setPostno(String postno) {
		this.postno = postno;
	}
	
	public String getTel() {
		return this.tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getCompanytelnum() {
		return this.companytelnum;
	}
	
	public void setCompanytelnum(String companytelnum) {
		this.companytelnum = companytelnum;
	}
	
	public String getTurninpersid() {
		return this.turninpersid;
	}
	
	public void setTurninpersid(String turninpersid) {
		this.turninpersid = turninpersid;
	}
	
	public String getLinkstatus() {
		return this.linkstatus;
	}
	
	public void setLinkstatus(String linkstatus) {
		this.linkstatus = linkstatus;
	}
	
}
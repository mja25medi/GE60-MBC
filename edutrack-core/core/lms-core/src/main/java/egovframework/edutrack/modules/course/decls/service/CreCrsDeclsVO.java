package egovframework.edutrack.modules.course.decls.service;

import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 개설 과정 분반 DTO
 * 
 * <pre>
 * <b>업  무 :</b> 과정 - 개설 과정 분반 DTO
 * </pre>
 * 
 * @author MediopiaTech
 */
public class CreCrsDeclsVO extends DefaultVO{

	private static final long serialVersionUID = 2651455520704920859L;

	private String  crsCreCd;
	private Integer declsNo;

	private Integer stuCnt;
	private Integer enrlCnt;
	private Integer cnfmCnt;
	private Integer cpltCnt;

	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getDeclsNo() {
		return declsNo;
	}
	public void setDeclsNo(Integer declsNo) {
		this.declsNo = declsNo;
	}
	public Integer getStuCnt() {
		return stuCnt;
	}
	public void setStuCnt(Integer stuCnt) {
		this.stuCnt = stuCnt;
	}
	public Integer getEnrlCnt() {
		return enrlCnt;
	}
	public void setEnrlCnt(Integer enrlCnt) {
		this.enrlCnt = enrlCnt;
	}
	public Integer getCnfmCnt() {
		return cnfmCnt;
	}
	public void setCnfmCnt(Integer cnfmCnt) {
		this.cnfmCnt = cnfmCnt;
	}
	public Integer getCpltCnt() {
		return cpltCnt;
	}
	public void setCpltCnt(Integer cpltCnt) {
		this.cpltCnt = cpltCnt;
	}
}

package egovframework.edutrack.modules.org.equ.service;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class OrgEquRentDetailVO implements Serializable {

	private static final long serialVersionUID = 7578402019354345795L;

	private int detailSn;
	private String rentCd;
	private String itemCd;
	
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	
	public int getDetailSn() {
		return detailSn;
	}
	public String getRentCd() {
		return rentCd;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setDetailSn(int detailSn) {
		this.detailSn = detailSn;
	}
	public void setRentCd(String rentCd) {
		this.rentCd = rentCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getRegNo() {
		return regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}

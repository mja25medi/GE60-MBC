package egovframework.edutrack.modules.org.equ.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgEquItemVO extends DefaultVO {

	private static final long serialVersionUID = 5703285135443031953L;
	
	private String itemCd;
	private String equCd;
	private String itemNm;
	private String itemDesc;
	private String useYn;
	
	private String itemCdArray;
	
	private String rentCd;
	private String rentStartDttm;
	private String rentEndDttm;
	
	private String fileName;
	private String filePath;
	
	public String getItemCd() {
		return itemCd;
	}
	public String getEquCd() {
		return equCd;
	}
	public String getItemNm() {
		return itemNm;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public void setEquCd(String equCd) {
		this.equCd = equCd;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getItemCdArray() {
		return itemCdArray;
	}
	public void setItemCdArray(String itemCdArray) {
		this.itemCdArray = itemCdArray;
	}
	public String getRentStartDttm() {
		return rentStartDttm;
	}
	public void setRentStartDttm(String rentStartDttm) {
		this.rentStartDttm = rentStartDttm;
	}
	public String getRentEndDttm() {
		return rentEndDttm;
	}
	public void setRentEndDttm(String rentEndDttm) {
		this.rentEndDttm = rentEndDttm;
	}
	public String getRentCd() {
		return rentCd;
	}
	public void setRentCd(String rentCd) {
		this.rentCd = rentCd;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

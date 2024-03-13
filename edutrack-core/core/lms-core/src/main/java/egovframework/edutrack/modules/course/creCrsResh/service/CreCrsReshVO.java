package egovframework.edutrack.modules.course.creCrsResh.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class CreCrsReshVO extends DefaultVO{

	private static final long serialVersionUID = 4568016071053503435L;
	private String  crsCreCd;
	private String  crsCreNm;
	private Integer reshSn;
	private String  reshTitle;
	private String  reshCts;
	private String  useYn;
	private String  startDttm;
	private String	 startHour;
	private String	 startMin;
	private String  endDttm;
	private String	 endHour;
	private String	 endMin;
	private String   regYn;
	private Integer itemCnt;
	private Integer ansrCnt;
	private Integer stdAnsrCnt;
	private String  stdNo;
	private Integer stdCnt;

	private String  joinYn;
	private String  ansrUseYn;
	
	private String  creOperTypeCd;
	private String  reshStareTypeCd;
	private Integer reshDayCnt;

	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getReshSn() {
		return reshSn;
	}
	public void setReshSn(Integer reshSn) {
		this.reshSn = reshSn;
	}
	public String getReshTitle() {
		return reshTitle;
	}
	public void setReshTitle(String reshTitle) {
		this.reshTitle = reshTitle;
	}
	public String getReshCts() {
		return reshCts;
	}
	public void setReshCts(String reshCts) {
		this.reshCts = reshCts;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getStartDttm() {
		return startDttm;
	}
	public void setStartDttm(String startDttm) {
		this.startDttm = startDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
	public String getRegYn() {
		return regYn;
	}
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	public Integer getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(Integer itemCnt) {
		this.itemCnt = itemCnt;
	}
	public Integer getAnsrCnt() {
		return ansrCnt;
	}
	public void setAnsrCnt(Integer ansrCnt) {
		this.ansrCnt = ansrCnt;
	}
	public String getJoinYn() {
		return joinYn;
	}
	public void setJoinYn(String joinYn) {
		this.joinYn = joinYn;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getAnsrUseYn() {
		return ansrUseYn;
	}
	public void setAnsrUseYn(String ansrUseYn) {
		this.ansrUseYn = ansrUseYn;
	}
	public Integer getStdCnt() {
		return stdCnt;
	}
	public void setStdCnt(Integer stdCnt) {
		this.stdCnt = stdCnt;
	}
	public String getCrsCreNm() {
		return crsCreNm;
	}
	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
	public Integer getStdAnsrCnt() {
		return stdAnsrCnt;
	}
	public void setStdAnsrCnt(Integer stdAnsrCnt) {
		this.stdAnsrCnt = stdAnsrCnt;
	}
	public String getCreOperTypeCd() {
		return creOperTypeCd;
	}
	public void setCreOperTypeCd(String creOperTypeCd) {
		this.creOperTypeCd = creOperTypeCd;
	}
	public String getReshStareTypeCd() {
		return reshStareTypeCd;
	}
	public void setReshStareTypeCd(String reshStareTypeCd) {
		this.reshStareTypeCd = reshStareTypeCd;
	}
	public Integer getReshDayCnt() {
		return reshDayCnt;
	}
	public void setReshDayCnt(Integer reshDayCnt) {
		this.reshDayCnt = reshDayCnt;
	}
}

package egovframework.edutrack.modules.etc.event.service;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class EtcEventVO extends DefaultVO {

	private static final long serialVersionUID = 6882276585882225089L;

	private Integer	eventSn;
	private String  orgCd;
	private String	eventNm;
	private String	eventUrl;
	private String  eventPosCd;
	private String  eventPosNm;
	private Integer	eventOdr;
	private String	eventTrgt = "_blank";
	private Integer	eventImgSn;
	private String  eventDesc;
	private String  openYn;

	private String	eventSns;

	private SysFileVO	eventFileVO;

	public EtcEventVO() {

	}

	public Integer getEventSn() {
		return eventSn;
	}

	public void setEventSn(Integer eventSn) {
		this.eventSn = eventSn;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getEventNm() {
		return eventNm;
	}

	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	public String getEventPosCd() {
		return eventPosCd;
	}

	public void setEventPosCd(String eventPosCd) {
		this.eventPosCd = eventPosCd;
	}

	public String getEventPosNm() {
		return eventPosNm;
	}

	public void setEventPosNm(String eventPosNm) {
		this.eventPosNm = eventPosNm;
	}

	public Integer getEventOdr() {
		return eventOdr;
	}

	public void setEventOdr(Integer eventOdr) {
		this.eventOdr = eventOdr;
	}

	public String getEventTrgt() {
		return eventTrgt;
	}

	public void setEventTrgt(String eventTrgt) {
		this.eventTrgt = eventTrgt;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getEventSns() {
		return eventSns;
	}

	public void setEventSns(String eventSns) {
		this.eventSns = eventSns;
	}

	public Integer getEventImgSn() {
		return eventImgSn;
	}

	public void setEventImgSn(Integer eventImgSn) {
		this.eventImgSn = eventImgSn;
	}

	public String getOpenYn() {
		return openYn;
	}

	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public SysFileVO geteventFileVO() {
		if (this.eventFileVO == null)
			this.eventFileVO = new SysFileVO();
		return eventFileVO;
	}

	public void seteventFileVO(SysFileVO eventFileVO) {
		this.eventFileVO = eventFileVO;
	}

	public Integer getEventFileSn() {
		return this.geteventFileVO().getFileSn();
	}

	public void setEventFileSn(Integer eventFileSn) {
		this.eventFileVO = new SysFileVO(eventFileSn);
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getEventFileJson() {
		return SysFileVOUtil.getJson(this.geteventFileVO(), false);
	}
}

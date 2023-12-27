package egovframework.edutrack.modules.course.creCrsResh.service;

import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;

public class CrsReshResultVO extends ResearchBankItemVO {

	private static final long serialVersionUID = 4268241951781996874L;

	private String   crsCreCd			 ;
	private Integer  emplTotCnt			 ;
    private Integer  empl1Cnt            ;
    private Integer  empl2Cnt            ;
    private Integer  empl3Cnt            ;
    private Integer  empl4Cnt            ;
    private Integer  empl5Cnt            ;
    private Integer  empl6Cnt            ;
    private Integer  empl7Cnt            ;
    private Integer  empl8Cnt            ;
    private Integer  empl9Cnt            ;
    private Integer  empl10Cnt           ;
    private Integer  empl11Cnt           ;
    private Integer  empl12Cnt           ;
    private Integer  empl13Cnt           ;
    private Integer  empl14Cnt           ;
    private Integer  empl15Cnt           ;
    private Integer  empl16Cnt           ;
    private Integer  empl17Cnt           ;
    private Integer  empl18Cnt           ;
    private Integer  empl19Cnt           ;
    private Integer  empl20Cnt           ;
    private String   emplOption			 ;
    private Integer  empl1Ratio          ;
    private Integer  empl2Ratio          ;
    private Integer  empl3Ratio          ;
    private Integer  empl4Ratio          ;
    private Integer  empl5Ratio          ;
    private Integer  empl6Ratio          ;
    private Integer  empl7Ratio          ;
    private Integer  empl8Ratio          ;
    private Integer  empl9Ratio          ;
    private Integer  empl10Ratio         ;
    private Integer  empl11Ratio         ;
    private Integer  empl12Ratio         ;
    private Integer  empl13Ratio         ;
    private Integer  empl14Ratio         ;
    private Integer  empl15Ratio         ;
    private Integer  empl16Ratio         ;
    private Integer  empl17Ratio         ;
    private Integer  empl18Ratio         ;
    private Integer  empl19Ratio         ;
    private Integer  empl20Ratio         ;

    public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public Integer getEmplTotCnt() {
		return emplTotCnt;
	}
	public void setEmplTotCnt(Integer emplTotCnt) {
		this.emplTotCnt = emplTotCnt;
	}
	public Integer getEmpl1Cnt() {
		return empl1Cnt;
	}
	public void setEmpl1Cnt(Integer empl1Cnt) {
		this.empl1Cnt = empl1Cnt;
	}
	public Integer getEmpl2Cnt() {
		return empl2Cnt;
	}
	public void setEmpl2Cnt(Integer empl2Cnt) {
		this.empl2Cnt = empl2Cnt;
	}
	public Integer getEmpl3Cnt() {
		return empl3Cnt;
	}
	public void setEmpl3Cnt(Integer empl3Cnt) {
		this.empl3Cnt = empl3Cnt;
	}
	public Integer getEmpl4Cnt() {
		return empl4Cnt;
	}
	public void setEmpl4Cnt(Integer empl4Cnt) {
		this.empl4Cnt = empl4Cnt;
	}
	public Integer getEmpl5Cnt() {
		return empl5Cnt;
	}
	public void setEmpl5Cnt(Integer empl5Cnt) {
		this.empl5Cnt = empl5Cnt;
	}
	public Integer getEmpl6Cnt() {
		return empl6Cnt;
	}
	public void setEmpl6Cnt(Integer empl6Cnt) {
		this.empl6Cnt = empl6Cnt;
	}
	public Integer getEmpl7Cnt() {
		return empl7Cnt;
	}
	public void setEmpl7Cnt(Integer empl7Cnt) {
		this.empl7Cnt = empl7Cnt;
	}
	public Integer getEmpl8Cnt() {
		return empl8Cnt;
	}
	public void setEmpl8Cnt(Integer empl8Cnt) {
		this.empl8Cnt = empl8Cnt;
	}
	public Integer getEmpl9Cnt() {
		return empl9Cnt;
	}
	public void setEmpl9Cnt(Integer empl9Cnt) {
		this.empl9Cnt = empl9Cnt;
	}
	public Integer getEmpl10Cnt() {
		return empl10Cnt;
	}
	public void setEmpl10Cnt(Integer empl10Cnt) {
		this.empl10Cnt = empl10Cnt;
	}
	public Integer getEmpl11Cnt() {
		return empl11Cnt;
	}
	public void setEmpl11Cnt(Integer empl11Cnt) {
		this.empl11Cnt = empl11Cnt;
	}
	public Integer getEmpl12Cnt() {
		return empl12Cnt;
	}
	public void setEmpl12Cnt(Integer empl12Cnt) {
		this.empl12Cnt = empl12Cnt;
	}
	public Integer getEmpl13Cnt() {
		return empl13Cnt;
	}
	public void setEmpl13Cnt(Integer empl13Cnt) {
		this.empl13Cnt = empl13Cnt;
	}
	public Integer getEmpl14Cnt() {
		return empl14Cnt;
	}
	public void setEmpl14Cnt(Integer empl14Cnt) {
		this.empl14Cnt = empl14Cnt;
	}
	public Integer getEmpl15Cnt() {
		return empl15Cnt;
	}
	public void setEmpl15Cnt(Integer empl15Cnt) {
		this.empl15Cnt = empl15Cnt;
	}
	public Integer getEmpl16Cnt() {
		return empl16Cnt;
	}
	public void setEmpl16Cnt(Integer empl16Cnt) {
		this.empl16Cnt = empl16Cnt;
	}
	public Integer getEmpl17Cnt() {
		return empl17Cnt;
	}
	public void setEmpl17Cnt(Integer empl17Cnt) {
		this.empl17Cnt = empl17Cnt;
	}
	public Integer getEmpl18Cnt() {
		return empl18Cnt;
	}
	public void setEmpl18Cnt(Integer empl18Cnt) {
		this.empl18Cnt = empl18Cnt;
	}
	public Integer getEmpl19Cnt() {
		return empl19Cnt;
	}
	public void setEmpl19Cnt(Integer empl19Cnt) {
		this.empl19Cnt = empl19Cnt;
	}
	public Integer getEmpl20Cnt() {
		return empl20Cnt;
	}
	public void setEmpl20Cnt(Integer empl20Cnt) {
		this.empl20Cnt = empl20Cnt;
	}
	public String getEmplOption() {
		return emplOption;
	}
	public void setEmplOption(String emplOption) {
		this.emplOption = emplOption;
	}

	public Integer getEmpl1Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl1Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl1Ratio(Integer empl1Ratio) {
		this.empl1Ratio = empl1Ratio;
	}
	public Integer getEmpl2Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl2Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl2Ratio(Integer empl2Ratio) {
		this.empl2Ratio = empl2Ratio;
	}
	public Integer getEmpl3Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl3Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl3Ratio(Integer empl3Ratio) {
		this.empl3Ratio = empl3Ratio;
	}
	public Integer getEmpl4Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl4Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl4Ratio(Integer empl4Ratio) {
		this.empl4Ratio = empl4Ratio;
	}
	public Integer getEmpl5Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl5Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl5Ratio(Integer empl5Ratio) {
		this.empl5Ratio = empl5Ratio;
	}
	public Integer getEmpl6Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl6Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl6Ratio(Integer empl6Ratio) {
		this.empl6Ratio = empl6Ratio;
	}
	public Integer getEmpl7Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl7Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl7Ratio(Integer empl7Ratio) {
		this.empl7Ratio = empl7Ratio;
	}
	public Integer getEmpl8Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl8Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl8Ratio(Integer empl8Ratio) {
		this.empl8Ratio = empl8Ratio;
	}
	public Integer getEmpl9Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl9Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl9Ratio(Integer empl9Ratio) {
		this.empl9Ratio = empl9Ratio;
	}
	public Integer getEmpl10Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl10Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl10Ratio(Integer empl10Ratio) {
		this.empl10Ratio = empl10Ratio;
	}
	public Integer getEmpl11Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl11Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl11Ratio(Integer empl11Ratio) {
		this.empl11Ratio = empl11Ratio;
	}
	public Integer getEmpl12Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl12Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl12Ratio(Integer empl12Ratio) {
		this.empl12Ratio = empl12Ratio;
	}
	public Integer getEmpl13Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl13Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl13Ratio(Integer empl13Ratio) {
		this.empl13Ratio = empl13Ratio;
	}
	public Integer getEmpl14Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl14Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl14Ratio(Integer empl14Ratio) {
		this.empl14Ratio = empl14Ratio;
	}
	public Integer getEmpl15Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl15Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl15Ratio(Integer empl15Ratio) {
		this.empl15Ratio = empl15Ratio;
	}
	public Integer getEmpl16Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl16Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl16Ratio(Integer empl16Ratio) {
		this.empl16Ratio = empl16Ratio;
	}
	public Integer getEmpl17Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl17Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl17Ratio(Integer empl17Ratio) {
		this.empl17Ratio = empl17Ratio;
	}
	public Integer getEmpl18Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl18Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl18Ratio(Integer empl18Ratio) {
		this.empl18Ratio = empl18Ratio;
	}
	public Integer getEmpl19Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl19Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl19Ratio(Integer empl19Ratio) {
		this.empl19Ratio = empl19Ratio;
	}
	public Integer getEmpl20Ratio() {
		if(emplTotCnt == 0) return 0;
		else {
			return (int) Math.round((double) empl20Cnt/emplTotCnt*100);
		}
	}
	public void setEmpl20Ratio(Integer empl20Ratio) {
		this.empl20Ratio = empl20Ratio;
	}



}

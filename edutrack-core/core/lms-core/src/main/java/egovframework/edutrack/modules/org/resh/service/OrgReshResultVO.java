package egovframework.edutrack.modules.org.resh.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class OrgReshResultVO  extends OrgReshItemVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289213393399909575L;
	private String orgCd ;
	private String   crsTermCd			 ;
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

	private Integer  empTotScore = 0	 ;
    private Integer  emp1Score   = 0     ;
    private Integer  emp2Score   = 0     ;
    private Integer  emp3Score   = 0     ;
    private Integer  emp4Score   = 0     ;
    private Integer  emp5Score   = 0     ;
    private Integer  emp6Score   = 0     ;
    private Integer  emp7Score   = 0     ;
    private Integer  emp8Score   = 0     ;
	private Integer  emp9Score   = 0     ;
    private Integer  emp10Score  = 0     ;
    private Integer  emp11Score  = 0     ;
    private Integer  emp12Score  = 0     ;
    private Integer  emp13Score  = 0     ;
    private Integer  emp14Score  = 0     ;
    private Integer  emp15Score  = 0     ;
    private Integer  emp16Score  = 0     ;
    private Integer  emp17Score  = 0     ;
    private Integer  emp18Score  = 0     ;
    private Integer  emp19Score  = 0     ;
    private Integer  emp20Score  = 0     ;
    
    private String stdNo;
    private String reshAnsr;
    
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

	public Integer getEmp1Score() {
		if(emp1Score == 0) return 0;
		else {
			return (int) Math.round((double) emp1Score/empl1Cnt);
		}
	}

	public void setEmp1Score(Integer emp1Score) {
		this.emp1Score = emp1Score;
		this.setEmpTotScore(emp1Score);
	}

	public Integer getEmp2Score() {
		if(emp2Score == 0) return 0;
		else {
			return (int) Math.round((double) emp2Score/empl2Cnt);
		}
	}
	public void setEmp2Score(Integer emp2Score) {
		this.emp2Score = emp2Score;
		this.setEmpTotScore(emp2Score);
	}
	public Integer getEmp3Score() {
		if(emp3Score == 0) return 0;
		else {
			return (int) Math.round((double) emp3Score/empl3Cnt);
		}
	}
	public void setEmp3Score(Integer emp3Score) {
		this.emp3Score = emp3Score;
		this.setEmpTotScore(emp3Score);
	}
	public Integer getEmp4Score() {
		if(emp4Score == 0) return 0;
		else {
			return (int) Math.round((double) emp4Score/empl4Cnt);
		}
	}
	public void setEmp4Score(Integer emp4Score) {
		this.emp4Score = emp4Score;
		this.setEmpTotScore(emp4Score);
	}
	public Integer getEmp5Score() {
		if(emp5Score == 0) return 0;
		else {
			return (int) Math.round((double) emp5Score/empl5Cnt);
		}
	}
	public void setEmp5Score(Integer emp5Score) {
		this.emp5Score = emp5Score;
		this.setEmpTotScore(emp5Score);
	}
	public Integer getEmp6Score() {
		if(emp6Score == 0) return 0;
		else {
			return (int) Math.round((double) emp6Score/empl6Cnt);
		}
	}
	public void setEmp6Score(Integer emp6Score) {
		this.emp6Score = emp6Score;
		this.setEmpTotScore(emp6Score);
	}
	public Integer getEmp7Score() {
		if(emp7Score == 0) return 0;
		else {
			return (int) Math.round((double) emp7Score/empl7Cnt);
		}
	}
	public void setEmp7Score(Integer emp7Score) {
		this.emp7Score = emp7Score;
		this.setEmpTotScore(emp7Score);
	}
	public Integer getEmp8Score() {
		if(emp8Score == 0) return 0;
		else {
			return (int) Math.round((double) emp8Score/empl8Cnt);
		}
	}
	public void setEmp8Score(Integer emp8Score) {
		this.emp8Score = emp8Score;
		this.setEmpTotScore(emp8Score);
	}
	public Integer getEmp9Score() {
		if(emp9Score == 0) return 0;
		else {
			return (int) Math.round((double) emp9Score/empl9Cnt);
		}
	}
	public void setEmp9Score(Integer emp9Score) {
		this.emp9Score = emp9Score;
		this.setEmpTotScore(emp9Score);
	}
	public Integer getEmp10Score() {
		if(emp10Score == 0) return 0;
		else {
			return (int) Math.round((double) emp10Score/empl10Cnt);
		}
	}
	public void setEmp10Score(Integer emp10Score) {
		this.emp10Score = emp10Score;
		this.setEmpTotScore(emp10Score);
	}
	public Integer getEmp11Score() {
		if(emp11Score == 0) return 0;
		else {
			return (int) Math.round((double) emp11Score/empl11Cnt);
		}
	}
	public void setEmp11Score(Integer emp11Score) {
		this.emp11Score = emp11Score;
		this.setEmpTotScore(emp11Score);
	}
	public Integer getEmp12Score() {
		if(emp12Score == 0) return 0;
		else {
			return (int) Math.round((double) emp12Score/empl12Cnt);
		}
	}
	public void setEmp12Score(Integer emp12Score) {
		this.emp12Score = emp12Score;
		this.setEmpTotScore(emp12Score);
	}
	public Integer getEmp13Score() {
		if(emp13Score == 0) return 0;
		else {
			return (int) Math.round((double) emp13Score/empl13Cnt);
		}
	}
	public void setEmp13Score(Integer emp13Score) {
		this.emp13Score = emp13Score;
		this.setEmpTotScore(emp13Score);
	}
	public Integer getEmp14Score() {
		if(emp14Score == 0) return 0;
		else {
			return (int) Math.round((double) emp14Score/empl14Cnt);
		}
	}
	public void setEmp14Score(Integer emp14Score) {
		this.emp14Score = emp14Score;
		this.setEmpTotScore(emp14Score);
	}
	public Integer getEmp15Score() {
		if(emp15Score == 0) return 0;
		else {
			return (int) Math.round((double) emp15Score/empl15Cnt);
		}
	}
	public void setEmp15Score(Integer emp15Score) {
		this.emp15Score = emp15Score;
		this.setEmpTotScore(emp15Score);
	}
	public Integer getEmp16Score() {
		if(emp16Score == 0) return 0;
		else {
			return (int) Math.round((double) emp16Score/empl16Cnt);
		}
	}
	public void setEmp16Score(Integer emp16Score) {
		this.emp16Score = emp16Score;
		this.setEmpTotScore(emp16Score);
	}
	public Integer getEmp17Score() {
		if(emp17Score == 0) return 0;
		else {
			return (int) Math.round((double) emp17Score/empl17Cnt);
		}
	}
	public void setEmp17Score(Integer emp17Score) {
		this.emp17Score = emp17Score;
		this.setEmpTotScore(emp17Score);
	}
	public Integer getEmp18Score() {
		if(emp18Score == 0) return 0;
		else {
			return (int) Math.round((double) emp18Score/empl18Cnt);
		}
	}
	public void setEmp18Score(Integer emp18Score) {
		this.emp18Score = emp18Score;
		this.setEmpTotScore(emp18Score);
	}
	public Integer getEmp19Score() {
		if(emp19Score == 0) return 0;
		else {
			return (int) Math.round((double) emp19Score/empl19Cnt);
		}
	}
	public void setEmp19Score(Integer emp19Score) {
		this.emp19Score = emp19Score;
		this.setEmpTotScore(emp19Score);
	}
	public Integer getEmp20Score() {
		if(emp20Score == 0) return 0;
		else {
			return (int) Math.round((double) emp20Score/empl20Cnt);
		}
	}
	public void setEmp20Score(Integer emp20Score) {
		this.emp20Score = emp20Score;
		this.setEmpTotScore(emp20Score);
	}
	
	public Integer getEmpTotScore() {
		int score = 0;
		int totScore = 0;
		int cnt = 0;
		if(empTotScore == 0) return 0;
		else {
			for(int i=1; i<21; i++){
				if(i == 1){
					score = this.getEmp1Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 2){
					score = this.getEmp2Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 3){
					score = this.getEmp3Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 4){
					score = this.getEmp4Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 5){
					score = this.getEmp5Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 6){
					score = this.getEmp6Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 7){
					score = this.getEmp7Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 8){
					score = this.getEmp8Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 9){
					score = this.getEmp9Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 10){
					score = this.getEmp10Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 11){
					score = this.getEmp11Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 12){
					score = this.getEmp12Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 13){
					score = this.getEmp13Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 14){
					score = this.getEmp14Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 15){
					score = this.getEmp15Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 16){
					score = this.getEmp16Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 17){
					score = this.getEmp17Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 18){
					score = this.getEmp18Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 19){
					score = this.getEmp19Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}else if(i == 20){
					score = this.getEmp20Score();
					if(score > 0){
						cnt++;
						totScore += score;
					}
				}
				
				
			}
			this.empTotScore = totScore/cnt;
			return empTotScore;
		}
	}

	public void setEmpTotScore(Integer empTotScore) {
		this.empTotScore += empTotScore;
	}
	public String getReshAnsr() {
		return reshAnsr;
	}
	public void setReshAnsr(String reshAnsr) {
		this.reshAnsr = reshAnsr;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getCrsTermCd() {
		return crsTermCd;
	}
	public void setCrsTermCd(String crsTermCd) {
		this.crsTermCd = crsTermCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	
}

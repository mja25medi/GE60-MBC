package egovframework.edutrack.modules.course.attendance.service;

/**
 * 출석체크 VO
 * 회차, 날짜별 출석정보를 담는다
 */
public class AttendanceVO {
	
	/** 회차코드 */
	private String crsCreCd;
	
	/** 출석일 */
	private String attendDttm;
	/** 학생번호 */
	private String stdNo;
	private String userNo;
	/** 학생명 */
	private String	userNm;
	
	private String [] clsAttendDttm;
	
	private String	col01;
	private String	col02;
	private String	col03;
	private String	col04;
	private String	col05;
	private String	col06;
	private String	col07;
	private String	col08;
	
	private String	col11;
	private String	col12;
	private String	col13;
	private String	col14;
	private String	col15;
	private String	col16;
	private String	col17;
	private String	col18;
	
	private String	col21;
	private String	col22;
	private String	col23;
	private String	col24;
	private String	col25;
	private String	col26;
	private String	col27;
	private String	col28;
	
	private String	col31;
	private String	col32;
	private String	col33;
	private String	col34;
	private String	col35;
	private String	col36;
	private String	col37;
	private String	col38;
	
	private String	col41;
	private String	col42;
	private String	col43;
	private String	col44;
	private String	col45;
	private String	col46;
	private String	col47;
	private String	col48;
	
	/** 수정 로그용*/
	private String	classTime;
	private String	classStat;
	
	private String	classStat1;
	private String	classStat2;
	private String	classStat3;
	private String	classStat4;
	private String	classStat5;
	private String	classStat6;
	private String	classStat7;
	private String	classStat8;
	
	private String	regNo;
	private String	regDttm;
	private String	modNo;
	private String	modDttm;
	
	/** 소정 출석일 */
	private Integer totalDay;
	/** 실제 출석일 */
	private Integer realDay;
	/** 결석 */
	private Integer absentDay;
	/** 지각 */
	private Integer lateDay;
	/** 조퇴 */
	private Integer leftDay;
	/** 외출 */
	private Integer outDay;
	/** 입실,퇴실 Flag I(입실)/O(퇴실) */
	private String	enterFlag;
	/** 입실 시간 */
	private String	enterTime;
	/** 퇴실 시간 */
	private String	quitTime;
	
	public String getCrsCreCd() {
		return crsCreCd;
	}
	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}
	public String getAttendDttm() {
		return attendDttm;
	}
	public void setAttendDttm(String attendDttm) {
		this.attendDttm = attendDttm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String[] getClsAttendDttm() {
		return clsAttendDttm;
	}
	public void setClsAttendDttm(String[] clsAttendDttm) {
		this.clsAttendDttm = clsAttendDttm;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getModNo() {
		return modNo;
	}
	public void setModNo(String modNo) {
		this.modNo = modNo;
	}
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getCol01() {
		return col01;
	}
	public void setCol01(String col01) {
		this.col01 = col01;
	}
	public String getCol02() {
		return col02;
	}
	public void setCol02(String col02) {
		this.col02 = col02;
	}
	public String getCol03() {
		return col03;
	}
	public void setCol03(String col03) {
		this.col03 = col03;
	}
	public String getCol04() {
		return col04;
	}
	public void setCol04(String col04) {
		this.col04 = col04;
	}
	public String getCol05() {
		return col05;
	}
	public void setCol05(String col05) {
		this.col05 = col05;
	}
	public String getCol06() {
		return col06;
	}
	public void setCol06(String col06) {
		this.col06 = col06;
	}
	public String getCol07() {
		return col07;
	}
	public void setCol07(String col07) {
		this.col07 = col07;
	}
	public String getCol08() {
		return col08;
	}
	public void setCol08(String col08) {
		this.col08 = col08;
	}
	public String getCol11() {
		return col11;
	}
	public void setCol11(String col11) {
		this.col11 = col11;
	}
	public String getCol12() {
		return col12;
	}
	public void setCol12(String col12) {
		this.col12 = col12;
	}
	public String getCol13() {
		return col13;
	}
	public void setCol13(String col13) {
		this.col13 = col13;
	}
	public String getCol14() {
		return col14;
	}
	public void setCol14(String col14) {
		this.col14 = col14;
	}
	public String getCol15() {
		return col15;
	}
	public void setCol15(String col15) {
		this.col15 = col15;
	}
	public String getCol16() {
		return col16;
	}
	public void setCol16(String col16) {
		this.col16 = col16;
	}
	public String getCol17() {
		return col17;
	}
	public void setCol17(String col17) {
		this.col17 = col17;
	}
	public String getCol18() {
		return col18;
	}
	public void setCol18(String col18) {
		this.col18 = col18;
	}
	public String getCol21() {
		return col21;
	}
	public void setCol21(String col21) {
		this.col21 = col21;
	}
	public String getCol22() {
		return col22;
	}
	public void setCol22(String col22) {
		this.col22 = col22;
	}
	public String getCol23() {
		return col23;
	}
	public void setCol23(String col23) {
		this.col23 = col23;
	}
	public String getCol24() {
		return col24;
	}
	public void setCol24(String col24) {
		this.col24 = col24;
	}
	public String getCol25() {
		return col25;
	}
	public void setCol25(String col25) {
		this.col25 = col25;
	}
	public String getCol26() {
		return col26;
	}
	public void setCol26(String col26) {
		this.col26 = col26;
	}
	public String getCol27() {
		return col27;
	}
	public void setCol27(String col27) {
		this.col27 = col27;
	}
	public String getCol28() {
		return col28;
	}
	public void setCol28(String col28) {
		this.col28 = col28;
	}
	public String getCol31() {
		return col31;
	}
	public void setCol31(String col31) {
		this.col31 = col31;
	}
	public String getCol32() {
		return col32;
	}
	public void setCol32(String col32) {
		this.col32 = col32;
	}
	public String getCol33() {
		return col33;
	}
	public void setCol33(String col33) {
		this.col33 = col33;
	}
	public String getCol34() {
		return col34;
	}
	public void setCol34(String col34) {
		this.col34 = col34;
	}
	public String getCol35() {
		return col35;
	}
	public void setCol35(String col35) {
		this.col35 = col35;
	}
	public String getCol36() {
		return col36;
	}
	public void setCol36(String col36) {
		this.col36 = col36;
	}
	public String getCol37() {
		return col37;
	}
	public void setCol37(String col37) {
		this.col37 = col37;
	}
	public String getCol38() {
		return col38;
	}
	public void setCol38(String col38) {
		this.col38 = col38;
	}
	public String getCol41() {
		return col41;
	}
	public void setCol41(String col41) {
		this.col41 = col41;
	}
	public String getCol42() {
		return col42;
	}
	public void setCol42(String col42) {
		this.col42 = col42;
	}
	public String getCol43() {
		return col43;
	}
	public void setCol43(String col43) {
		this.col43 = col43;
	}
	public String getCol44() {
		return col44;
	}
	public void setCol44(String col44) {
		this.col44 = col44;
	}
	public String getCol45() {
		return col45;
	}
	public void setCol45(String col45) {
		this.col45 = col45;
	}
	public String getCol46() {
		return col46;
	}
	public void setCol46(String col46) {
		this.col46 = col46;
	}
	public String getCol47() {
		return col47;
	}
	public void setCol47(String col47) {
		this.col47 = col47;
	}
	public String getCol48() {
		return col48;
	}
	public void setCol48(String col48) {
		this.col48 = col48;
	}
	public String getClassStat1() {
		return classStat1;
	}
	public void setClassStat1(String classStat1) {
		this.classStat1 = classStat1;
	}
	public String getClassStat2() {
		return classStat2;
	}
	public void setClassStat2(String classStat2) {
		this.classStat2 = classStat2;
	}
	public String getClassStat3() {
		return classStat3;
	}
	public void setClassStat3(String classStat3) {
		this.classStat3 = classStat3;
	}
	public String getClassStat4() {
		return classStat4;
	}
	public void setClassStat4(String classStat4) {
		this.classStat4 = classStat4;
	}
	public String getClassStat5() {
		return classStat5;
	}
	public void setClassStat5(String classStat5) {
		this.classStat5 = classStat5;
	}
	public String getClassStat6() {
		return classStat6;
	}
	public void setClassStat6(String classStat6) {
		this.classStat6 = classStat6;
	}
	public String getClassStat7() {
		return classStat7;
	}
	public void setClassStat7(String classStat7) {
		this.classStat7 = classStat7;
	}
	public String getClassStat8() {
		return classStat8;
	}
	public void setClassStat8(String classStat8) {
		this.classStat8 = classStat8;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getClassStat() {
		return classStat;
	}
	public void setClassStat(String classStat) {
		this.classStat = classStat;
	}
	public Integer getTotalDay() {
		return totalDay;
	}
	public void setTotalDay(Integer totalDay) {
		this.totalDay = totalDay;
	}
	public Integer getRealDay() {
		return realDay;
	}
	public void setRealDay(Integer realDay) {
		this.realDay = realDay;
	}
	public Integer getAbsentDay() {
		return absentDay;
	}
	public void setAbsentDay(Integer absentDay) {
		this.absentDay = absentDay;
	}
	public Integer getLateDay() {
		return lateDay;
	}
	public void setLateDay(Integer lateDay) {
		this.lateDay = lateDay;
	}
	public Integer getLeftDay() {
		return leftDay;
	}
	public void setLeftDay(Integer leftDay) {
		this.leftDay = leftDay;
	}
	public Integer getOutDay() {
		return outDay;
	}
	public void setOutDay(Integer outDay) {
		this.outDay = outDay;
	}
	public String getEnterFlag() {
		return enterFlag;
	}
	public void setEnterFlag(String enterFlag) {
		this.enterFlag = enterFlag;
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	public String getQuitTime() {
		return quitTime;
	}
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	
	
	
	

	
	
}

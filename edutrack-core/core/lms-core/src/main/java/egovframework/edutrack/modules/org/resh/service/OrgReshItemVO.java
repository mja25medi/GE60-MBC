package egovframework.edutrack.modules.org.resh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class OrgReshItemVO  extends DefaultVO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2409583726460502882L;
	private int     reshSn           ; // NUMBER(9)
    private int     reshItemSn       ; // NUMBER(9)
    private String  reshItemTypeCd   ; // VARCHAR2(10 BYTE),
    private String	reshItemTypeNm	;
    private String  reshItemCts      ; // NCLOB,
    private Integer itemOdr			;
    private String  empl1            ; // NVARCHAR2(1000),
    private String  empl2            ; // NVARCHAR2(1000),
    private String  empl3            ; // NVARCHAR2(1000),
    private String  empl4            ; // NVARCHAR2(1000),
    private String  empl5            ; // NVARCHAR2(1000),
    private String  empl6            ; // NVARCHAR2(1000),
    private String  empl7            ; // NVARCHAR2(1000),
    private String  empl8            ; // NVARCHAR2(1000),
    private String  empl9            ; // NVARCHAR2(1000),
    private String  empl10           ; // NVARCHAR2(1000),
    private String  empl11           ; // NVARCHAR2(1000),
    private String  empl12           ; // NVARCHAR2(1000),
    private String  empl13           ; // NVARCHAR2(1000),
    private String  empl14           ; // NVARCHAR2(1000),
    private String  empl15           ; // NVARCHAR2(1000),
    private String  empl16           ; // NVARCHAR2(1000),
    private String  empl17           ; // NVARCHAR2(1000),
    private String  empl18           ; // NVARCHAR2(1000),
    private String  empl19           ; // NVARCHAR2(1000),
    private String  empl20           ; // NVARCHAR2(1000),

    private Integer emplCnt          ; // NUMBER(9),
    private Integer	emplScore1	= 1	 ;
    private Integer	emplScore2	= 2	 ;
    private Integer	emplScore3	= 3	 ;
    private Integer	emplScore4	= 4	 ;
    private Integer	emplScore5	= 5	 ;
    private Integer	emplScore6	= 0	 ;
    private Integer	emplScore7	= 0	 ;
    private Integer	emplScore8	= 0	 ;
    private Integer	emplScore9	= 0	 ;
    private Integer	emplScore10	= 0	 ;
    private Integer	emplScore11	= 0	 ;
    private Integer	emplScore12	= 0	 ;
    private Integer	emplScore13	= 0	 ;
    private Integer	emplScore14	= 0	 ;
    private Integer	emplScore15	= 0	 ;
    private Integer	emplScore16	= 0	 ;
    private Integer	emplScore17	= 0	 ;
    private Integer	emplScore18	= 0	 ;
    private Integer	emplScore19	= 0	 ;
    private Integer	emplScore20	= 0	 ;

    private String 	emplViewType;
    private String  editorYn;
    private String  reshItemSns   ;

    private List<SysFileVO>	attachImages;		// 첨부사진 목록 : 게시물 내용상의 이미지
    private List<SysFileVO>	attachFiles;		// 첨부파일 목록
    
    private String  reshTypeCd;
    private String etcNo;
    //-- excel upload용으로 추가
	private String  lineNo;
  	private String  errorCode;

  	private String reshType;
  	private String reshAnsrTypeCd;
  	
  	// 통계 추가
  	private String orgCd;
  	private String crsTermCd;
  	private String useYn;
  	private String regYn;
  	private String reshTitle;
  	private String startDttm;
  	private String endDttm;
  	private String reshTarCnt;
  	private String reshCnt;
  	private String reshNotCnt;
  	private String reshPer;
  	private String reshTarStd;
  	private String reshStd;
  	private String reshNotStd;
  	private String reshAvgTarTm;
  	private String crsCreCd;
  	private String userNo;
  	private String crsCurriCd;
  	private String crsCurriNm;
  	private String crsCreNm;
  	private String declsNoInt;
  	private String areaCd;
  	private String areaNm;
  	private String enrlSts;
  	private String enrlStsNm;
  	private String userId;
  	private String userNm;
  	private String mngNo;
  	private String mngId;
  	private String mngNm;
  	private String ansrUserNo;
  	private String ansrRegDttm;
  	private String reshAnsr;
  	private String reshItemSnList;
  	private String reshAnsrList;
  	private String reshItemSnArr[];
  	private String reshAnsrArr[];
  	private String separ = ":풱:";
  	private String sumReshAnsrScore;
  	private String avgReshAnsrScore;
  	
	public String getReshAnsrTypeCd() {
		return reshAnsrTypeCd;
	}

	public void setReshAnsrTypeCd(String reshAnsrTypeCd) {
		this.reshAnsrTypeCd = reshAnsrTypeCd;
	}

	public String getSumReshAnsrScore() {
		return sumReshAnsrScore;
	}

	public void setSumReshAnsrScore(String sumReshAnsrScore) {
		this.sumReshAnsrScore = sumReshAnsrScore;
	}

	public String getAvgReshAnsrScore() {
		return avgReshAnsrScore;
	}

	public void setAvgReshAnsrScore(String avgReshAnsrScore) {
		this.avgReshAnsrScore = avgReshAnsrScore;
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCrsCurriCd() {
		return crsCurriCd;
	}

	public void setCrsCurriCd(String crsCurriCd) {
		this.crsCurriCd = crsCurriCd;
	}

	public String getCrsCurriNm() {
		return crsCurriNm;
	}

	public void setCrsCurriNm(String crsCurriNm) {
		this.crsCurriNm = crsCurriNm;
	}

	public String getCrsCreNm() {
		return crsCreNm;
	}

	public void setCrsCreNm(String crsCreNm) {
		this.crsCreNm = crsCreNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegYn() {
		return regYn;
	}

	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}

	public String getReshTitle() {
		return reshTitle;
	}

	public void setReshTitle(String reshTitle) {
		this.reshTitle = reshTitle;
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

	public String getReshTarCnt() {
		return reshTarCnt;
	}

	public void setReshTarCnt(String reshTarCnt) {
		this.reshTarCnt = reshTarCnt;
	}

	public String getReshCnt() {
		return reshCnt;
	}

	public void setReshCnt(String reshCnt) {
		this.reshCnt = reshCnt;
	}

	public String getReshNotCnt() {
		return reshNotCnt;
	}

	public void setReshNotCnt(String reshNotCnt) {
		this.reshNotCnt = reshNotCnt;
	}

	public String getReshPer() {
		return reshPer;
	}

	public void setReshPer(String reshPer) {
		this.reshPer = reshPer;
	}

	public String getReshTarStd() {
		return reshTarStd;
	}

	public void setReshTarStd(String reshTarStd) {
		this.reshTarStd = reshTarStd;
	}

	public String getReshStd() {
		return reshStd;
	}

	public void setReshStd(String reshStd) {
		this.reshStd = reshStd;
	}

	public String getReshNotStd() {
		return reshNotStd;
	}

	public void setReshNotStd(String reshNotStd) {
		this.reshNotStd = reshNotStd;
	}

	public String getReshAvgTarTm() {
		return reshAvgTarTm;
	}

	public void setReshAvgTarTm(String reshAvgTarTm) {
		this.reshAvgTarTm = reshAvgTarTm;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getCrsTermCd() {
		return crsTermCd;
	}

	public void setCrsTermCd(String crsTermCd) {
		this.crsTermCd = crsTermCd;
	}

	public int getReshSn() {
		return reshSn;
	}

	public void setReshSn(int reshSn) {
		this.reshSn = reshSn;
	}

	public int getReshItemSn() {
		return reshItemSn;
	}

	public void setReshItemSn(int reshItemSn) {
		this.reshItemSn = reshItemSn;
	}

	public String getReshItemTypeCd() {
		return reshItemTypeCd;
	}

	public void setReshItemTypeCd(String reshItemTypeCd) {
		this.reshItemTypeCd = reshItemTypeCd;
	}

	public String getReshItemTypeNm() {
		return reshItemTypeNm;
	}

	public void setReshItemTypeNm(String reshItemTypeNm) {
		this.reshItemTypeNm = reshItemTypeNm;
	}

	public String getReshItemCts() {
		return reshItemCts;
	}

	public void setReshItemCts(String reshItemCts) {
		this.reshItemCts = reshItemCts;
	}

	public Integer getItemOdr() {
		return itemOdr;
	}

	public void setItemOdr(Integer itemOdr) {
		this.itemOdr = itemOdr;
	}

	public String getEmpl1() {
		return empl1;
	}

	public void setEmpl1(String empl1) {
		this.empl1 = empl1;
	}

	public String getEmpl2() {
		return empl2;
	}

	public void setEmpl2(String empl2) {
		this.empl2 = empl2;
	}

	public String getEmpl3() {
		return empl3;
	}

	public void setEmpl3(String empl3) {
		this.empl3 = empl3;
	}

	public String getEmpl4() {
		return empl4;
	}

	public void setEmpl4(String empl4) {
		this.empl4 = empl4;
	}

	public String getEmpl5() {
		return empl5;
	}

	public void setEmpl5(String empl5) {
		this.empl5 = empl5;
	}

	public String getEmpl6() {
		return empl6;
	}

	public void setEmpl6(String empl6) {
		this.empl6 = empl6;
	}

	public String getEmpl7() {
		return empl7;
	}

	public void setEmpl7(String empl7) {
		this.empl7 = empl7;
	}

	public String getEmpl8() {
		return empl8;
	}

	public void setEmpl8(String empl8) {
		this.empl8 = empl8;
	}

	public String getEmpl9() {
		return empl9;
	}

	public void setEmpl9(String empl9) {
		this.empl9 = empl9;
	}

	public String getEmpl10() {
		return empl10;
	}

	public void setEmpl10(String empl10) {
		this.empl10 = empl10;
	}

	public String getEmpl11() {
		return empl11;
	}

	public void setEmpl11(String empl11) {
		this.empl11 = empl11;
	}

	public String getEmpl12() {
		return empl12;
	}

	public void setEmpl12(String empl12) {
		this.empl12 = empl12;
	}

	public String getEmpl13() {
		return empl13;
	}

	public void setEmpl13(String empl13) {
		this.empl13 = empl13;
	}

	public String getEmpl14() {
		return empl14;
	}

	public void setEmpl14(String empl14) {
		this.empl14 = empl14;
	}

	public String getEmpl15() {
		return empl15;
	}

	public void setEmpl15(String empl15) {
		this.empl15 = empl15;
	}

	public String getEmpl16() {
		return empl16;
	}

	public void setEmpl16(String empl16) {
		this.empl16 = empl16;
	}

	public Integer getEmplCnt() {
		return emplCnt;
	}

	public void setEmplCnt(Integer emplCnt) {
		this.emplCnt = emplCnt;
	}

	public Integer getEmplScore1() {
		return emplScore1;
	}

	public void setEmplScore1(Integer emplScore1) {
		this.emplScore1 = emplScore1;
	}

	public Integer getEmplScore2() {
		return emplScore2;
	}

	public void setEmplScore2(Integer emplScore2) {
		this.emplScore2 = emplScore2;
	}

	public Integer getEmplScore3() {
		return emplScore3;
	}

	public void setEmplScore3(Integer emplScore3) {
		this.emplScore3 = emplScore3;
	}

	public Integer getEmplScore4() {
		return emplScore4;
	}

	public void setEmplScore4(Integer emplScore4) {
		this.emplScore4 = emplScore4;
	}

	public Integer getEmplScore5() {
		return emplScore5;
	}

	public void setEmplScore5(Integer emplScore5) {
		this.emplScore5 = emplScore5;
	}

	public Integer getEmplScore6() {
		return emplScore6;
	}

	public void setEmplScore6(Integer emplScore6) {
		this.emplScore6 = emplScore6;
	}

	public Integer getEmplScore7() {
		return emplScore7;
	}

	public void setEmplScore7(Integer emplScore7) {
		this.emplScore7 = emplScore7;
	}

	public Integer getEmplScore8() {
		return emplScore8;
	}

	public void setEmplScore8(Integer emplScore8) {
		this.emplScore8 = emplScore8;
	}

	public Integer getEmplScore9() {
		return emplScore9;
	}

	public void setEmplScore9(Integer emplScore9) {
		this.emplScore9 = emplScore9;
	}

	public Integer getEmplScore10() {
		return emplScore10;
	}

	public void setEmplScore10(Integer emplScore10) {
		this.emplScore10 = emplScore10;
	}

	public Integer getEmplScore11() {
		return emplScore11;
	}

	public void setEmplScore11(Integer emplScore11) {
		this.emplScore11 = emplScore11;
	}

	public Integer getEmplScore12() {
		return emplScore12;
	}

	public void setEmplScore12(Integer emplScore12) {
		this.emplScore12 = emplScore12;
	}

	public Integer getEmplScore13() {
		return emplScore13;
	}

	public void setEmplScore13(Integer emplScore13) {
		this.emplScore13 = emplScore13;
	}

	public Integer getEmplScore14() {
		return emplScore14;
	}

	public void setEmplScore14(Integer emplScore14) {
		this.emplScore14 = emplScore14;
	}

	public Integer getEmplScore15() {
		return emplScore15;
	}

	public void setEmplScore15(Integer emplScore15) {
		this.emplScore15 = emplScore15;
	}

	public Integer getEmplScore16() {
		return emplScore16;
	}

	public void setEmplScore16(Integer emplScore16) {
		this.emplScore16 = emplScore16;
	}

	public String getEmplViewType() {
		return emplViewType;
	}

	public void setEmplViewType(String emplViewType) {
		this.emplViewType = emplViewType;
	}

	public String getEmpl17() {
		return empl17;
	}

	public void setEmpl17(String empl17) {
		this.empl17 = empl17;
	}

	public String getEmpl18() {
		return empl18;
	}

	public void setEmpl18(String empl18) {
		this.empl18 = empl18;
	}

	public String getEmpl19() {
		return empl19;
	}

	public void setEmpl19(String empl19) {
		this.empl19 = empl19;
	}

	public String getEmpl20() {
		return empl20;
	}

	public void setEmpl20(String empl20) {
		this.empl20 = empl20;
	}

	public Integer getEmplScore17() {
		return emplScore17;
	}

	public void setEmplScore17(Integer emplScore17) {
		this.emplScore17 = emplScore17;
	}

	public Integer getEmplScore18() {
		return emplScore18;
	}

	public void setEmplScore18(Integer emplScore18) {
		this.emplScore18 = emplScore18;
	}

	public Integer getEmplScore19() {
		return emplScore19;
	}

	public void setEmplScore19(Integer emplScore19) {
		this.emplScore19 = emplScore19;
	}

	public Integer getEmplScore20() {
		return emplScore20;
	}

	public void setEmplScore20(Integer emplScore20) {
		this.emplScore20 = emplScore20;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	//-- 첨부 이미지 관련 처리
	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}
	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}
	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}

	//-- 첨부 파일 관련 처리
	public String getAttachFileSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
	}
	public void setAttachFileSns(String attachFileSns) {
		this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
	}
	public List<SysFileVO> getAttachFiles() {
		if (this.attachFiles == null)
			this.attachFiles = new ArrayList<SysFileVO>();
		return this.attachFiles;
	}
	public void setAttachFiles(List<SysFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getAttachFilesJson() {
		return SysFileVOUtil.getJson(this.getAttachFiles(), false);
	}

	public String getReshItemSns() {
		return reshItemSns;
	}

	public void setReshItemSns(String reshItemSns) {
		this.reshItemSns = reshItemSns;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getReshType() {
		return reshType;
	}

	public void setReshType(String reshType) {
		this.reshType = reshType;
	}

	public String getReshTypeCd() {
		return reshTypeCd;
	}

	public void setReshTypeCd(String reshTypeCd) {
		this.reshTypeCd = reshTypeCd;
	}

	public String getEtcNo() {
		return etcNo;
	}

	public void setEtcNo(String etcNo) {
		this.etcNo = etcNo;
	}

	public String getDeclsNoInt() {
		return declsNoInt;
	}

	public void setDeclsNoInt(String declsNoInt) {
		this.declsNoInt = declsNoInt;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getAreaNm() {
		return areaNm;
	}

	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}

	public String getEnrlSts() {
		return enrlSts;
	}

	public void setEnrlSts(String enrlSts) {
		this.enrlSts = enrlSts;
	}

	public String getEnrlStsNm() {
		return enrlStsNm;
	}

	public void setEnrlStsNm(String enrlStsNm) {
		this.enrlStsNm = enrlStsNm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getMngNo() {
		return mngNo;
	}

	public void setMngNo(String mngNo) {
		this.mngNo = mngNo;
	}

	public String getMngId() {
		return mngId;
	}

	public void setMngId(String mngId) {
		this.mngId = mngId;
	}

	public String getMngNm() {
		return mngNm;
	}

	public void setMngNm(String mngNm) {
		this.mngNm = mngNm;
	}

	public String getAnsrUserNo() {
		return ansrUserNo;
	}

	public void setAnsrUserNo(String ansrUserNo) {
		this.ansrUserNo = ansrUserNo;
	}

	public String getAnsrRegDttm() {
		return ansrRegDttm;
	}

	public void setAnsrRegDttm(String ansrRegDttm) {
		this.ansrRegDttm = ansrRegDttm;
	}

	public String getReshAnsr() {
		return reshAnsr;
	}

	public void setReshAnsr(String reshAnsr) {
		this.reshAnsr = reshAnsr;
	}
	
	public String getReshItemSnList() {
		return reshItemSnList;
	}

	public void setReshItemSnList(String reshItemSnList) {
		this.reshItemSnList = reshItemSnList;
		this.setReshItemSnArr(reshItemSnList);
	}

	public String getReshAnsrList() {
		return reshAnsrList;
	}

	public void setReshAnsrList(String reshAnsrList) {
		this.reshAnsrList = reshAnsrList;
		this.setReshAnsrArr(reshAnsrList);
	}

	public String[] getReshItemSnArr() {
		return reshItemSnArr;
	}

	public void setReshItemSnArr(String[] reshItemSnArr) {
		this.reshItemSnArr = reshItemSnArr;
	}
	
	public void setReshItemSnArr(String reshItemSnArr) {
		if (reshItemSnArr != null) {
			this.reshItemSnArr = reshItemSnArr.split(separ);
		}
	}

	public String[] getReshAnsrArr() {
		return reshAnsrArr;
	}

	public void setReshAnsrArr(String[] reshAnsrArr) {
		this.reshAnsrArr = reshAnsrArr;
	}
	
	public void setReshAnsrArr(String reshAnsrArr) {
		if (reshItemSnArr != null) {
			this.reshAnsrArr = reshAnsrArr.split(separ);
		}
	}
	
	public String getScore(String tempAnsr) {
		try {
			if (tempAnsr.equals("1")) {
				return Integer.toString(this.getEmplScore1());
			} else if (tempAnsr.equals("2")) {
				return Integer.toString(this.getEmplScore2());
			} else if (tempAnsr.equals("3")) {
				return Integer.toString(this.getEmplScore3());
			} else if (tempAnsr.equals("4")) {
				return Integer.toString(this.getEmplScore4());
			} else if (tempAnsr.equals("5")) {
				return Integer.toString(this.getEmplScore5());
			} else if (tempAnsr.equals("6")) {
				return Integer.toString(this.getEmplScore6());
			} else if (tempAnsr.equals("7")) {
				return Integer.toString(this.getEmplScore7());
			} else if (tempAnsr.equals("8")) {
				return Integer.toString(this.getEmplScore8());
			} else if (tempAnsr.equals("9")) {
				return Integer.toString(this.getEmplScore9());
			} else if (tempAnsr.equals("10")) {
				return Integer.toString(this.getEmplScore10());
			} else if (tempAnsr.equals("11")) {
				return Integer.toString(this.getEmplScore11());
			} else if (tempAnsr.equals("12")) {
				return Integer.toString(this.getEmplScore12());
			} else if (tempAnsr.equals("13")) {
				return Integer.toString(this.getEmplScore13());
			} else if (tempAnsr.equals("14")) {
				return Integer.toString(this.getEmplScore14());
			} else if (tempAnsr.equals("15")) {
				return Integer.toString(this.getEmplScore15());
			} else if (tempAnsr.equals("16")) {
				return Integer.toString(this.getEmplScore16());
			} else if (tempAnsr.equals("17")) {
				return Integer.toString(this.getEmplScore17());
			} else if (tempAnsr.equals("18")) {
				return Integer.toString(this.getEmplScore18());
			} else if (tempAnsr.equals("19")) {
				return Integer.toString(this.getEmplScore19());
			} else if (tempAnsr.equals("20")) {
				return Integer.toString(this.getEmplScore20());
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
		
	}
	
}
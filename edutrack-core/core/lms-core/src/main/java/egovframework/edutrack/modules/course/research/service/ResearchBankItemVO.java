package egovframework.edutrack.modules.course.research.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class ResearchBankItemVO  extends DefaultVO {

	private static final long	serialVersionUID	= 7786279016132721859L;

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

    //-- excel upload용으로 추가
	private String  lineNo;
  	private String  errorCode;

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
}
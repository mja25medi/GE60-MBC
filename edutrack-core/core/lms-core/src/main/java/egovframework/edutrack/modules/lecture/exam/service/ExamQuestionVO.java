package egovframework.edutrack.modules.lecture.exam.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


public class ExamQuestionVO extends DefaultVO {

	private static final long	serialVersionUID	= 5858081977954995235L;
	private String				crsCreCd;									// VARCHAR2(10 BYTE)
	private Integer				examSn;									// NUMBER(9)
	private Integer				examQstnSn;								// NUMBER(9)
	private Integer				qstnNo = 0;
	private String				title;
	private Integer				subNo;
	private Integer				qstnCnt;
	private String				qstnType;									// VARCHAR2(10 BYTE),
	private String				qstnTypeNm;
	private String				qstnCts;									// NCLOB,
	private String				empl1;										// NVARCHAR2(1000),
	private String				empl2;										// NVARCHAR2(1000),
	private String				empl3;										// NVARCHAR2(1000),
	private String				empl4;										// NVARCHAR2(1000),
	private String				empl5;										// NVARCHAR2(1000),
	private String				rgtAnsr;									// NVARCHAR2(1000),
	private String				multiRgtAnsr;								// NVARCHAR2(100),
	private String				qstnExpl;									// NVARCHAR2(2000),
	private double				qstnScore;									// NUMBER(3),

	private String				strQstnSn;
	private String				sbjCd;
	private String				ctgrCd;
	private String				strCtgrCd;
	private String				qstnScores;
	private String				strExamQstnSn;

	private String				fileType;

	private String				editorYn;

	private List<SysFileVO>		attachImages;								// 첨부사진 목록

	private String qstnNoSort;				//시험문제 순서변경용 문제번호
	private String examQstnSnSort;	//시험문제 순서변경용 고유번호

	 //-- excel upload용으로 추가
	private String  lineNo;
  	private String  errorCode;
  	
  	private int examSetCnt;
  	private int stareCnt;//응시자 수
  	
  	private String[] sqlForeach;
  	
	public ExamQuestionVO() {}

	public int getStareCnt() {
		return stareCnt;
	}

	public void setStareCnt(int stareCnt) {
		this.stareCnt = stareCnt;
	}

	public String getCrsCreCd() {
		return crsCreCd;
	}

	public void setCrsCreCd(String crsCreCd) {
		this.crsCreCd = crsCreCd;
	}

	public Integer getExamSn() {
		return examSn;
	}

	public void setExamSn(Integer examSn) {
		this.examSn = examSn;
	}

	public Integer getExamQstnSn() {
		return examQstnSn;
	}

	public void setExamQstnSn(Integer examQstnSn) {
		this.examQstnSn = examQstnSn;
	}

	public Integer getQstnNo() {
		return qstnNo;
	}

	public void setQstnNo(Integer qstnNo) {
		this.qstnNo = qstnNo;
	}

	public Integer getSubNo() {
		return subNo;
	}

	public void setSubNo(Integer subNo) {
		this.subNo = subNo;
	}

	public Integer getQstnCnt() {
		return qstnCnt;
	}

	public void setQstnCnt(Integer qstnCnt) {
		this.qstnCnt = qstnCnt;
	}

	public String getQstnType() {
		return qstnType;
	}

	public void setQstnType(String qstnType) {
		this.qstnType = qstnType;
	}

	public String getQstnTypeNm() {
		return qstnTypeNm;
	}

	public void setQstnTypeNm(String qstnTypeNm) {
		this.qstnTypeNm = qstnTypeNm;
	}

	public String getQstnCts() {
		return qstnCts;
	}

	public void setQstnCts(String qstnCts) {
		this.qstnCts = qstnCts;
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

	public String getRgtAnsr() {
		return rgtAnsr;
	}

	public void setRgtAnsr(String rgtAnsr) {
		this.rgtAnsr = rgtAnsr;
	}

	public String getMultiRgtAnsr() {
		return multiRgtAnsr;
	}

	public void setMultiRgtAnsr(String multiRgtAnsr) {
		this.multiRgtAnsr = multiRgtAnsr;
	}

	public String getQstnExpl() {
		return qstnExpl;
	}

	public void setQstnExpl(String qstnExpl) {
		this.qstnExpl = qstnExpl;
	}

	public double getQstnScore() {
		return qstnScore;
	}

	public void setQstnScore(double qstnScore) {
		this.qstnScore = qstnScore;
	}

	public String getStrQstnSn() {
		return strQstnSn;
	}

	public void setStrQstnSn(String strQstnSn) {
		this.strQstnSn = strQstnSn;
	}

	public String getSbjCd() {
		return sbjCd;
	}

	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}

	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	public String getQstnScores() {
		return qstnScores;
	}

	public void setQstnScores(String qstnScores) {
		this.qstnScores = qstnScores;
	}

	public String getStrExamQstnSn() {
		return strExamQstnSn;
	}

	public void setStrExamQstnSn(String strExamQstnSn) {
		this.strExamQstnSn = strExamQstnSn;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getEditorYn() {
		return editorYn;
	}

	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	/* 첨부파일 핸들링용 매서드 */
	public List<SysFileVO> getAttachImages() {
		if (this.attachImages == null)
			this.attachImages = new ArrayList<SysFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<SysFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	public String getAttachImageSns() {
		return SysFileVOUtil.convertSysFileVOListToString(this.getAttachImages());
	}

	public void setAttachImageSns(String attachImageSns) {
		this.setAttachImages(SysFileVOUtil.convertStringToSysFileVOList(attachImageSns));
	}

	/* 첨부파일 Json 정보 getter용 */
	public String getAttachImagesJson() {
		return SysFileVOUtil.getJson(this.getAttachImages(), true);
	}



	public String getQstnNoSort() {
		return qstnNoSort;
	}



	public void setQstnNoSort(String qstnNoSort) {
		this.qstnNoSort = qstnNoSort;
	}



	public String getExamQstnSnSort() {
		return examQstnSnSort;
	}



	public void setExamQstnSnSort(String examQstnSnSort) {
		this.examQstnSnSort = examQstnSnSort;
	}



	public String getLineNo() {
		return lineNo;
	}



	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}



	public String getErrorCode() {
		return errorCode;
	}



	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



	public String getStrCtgrCd() {
		return strCtgrCd;
	}



	public void setStrCtgrCd(String strCtgrCd) {
		this.strCtgrCd = strCtgrCd;
	}


	public int getExamSetCnt() {
		return examSetCnt;
	}


	public void setExamSetCnt(int examSetCnt) {
		this.examSetCnt = examSetCnt;
	}



	public String[] getSqlForeach() {
		return sqlForeach;
	}



	public void setSqlForeach(String[] sqlForeach) {
		this.sqlForeach = sqlForeach;
	}
	
}
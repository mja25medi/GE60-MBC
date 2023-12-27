package egovframework.edutrack.modules.lecture.score.service;

import java.io.Serializable;

import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;

public class ScoreDataDto implements Serializable {

	private static final long serialVersionUID = 2452880746113736812L;
	
	private String dataSn;
	private String dataType;
	private String dataTypeDetail;
	private String dataTitle;
	private double dataScore;
	private String dataScoreDttm;
	
	public ScoreDataDto(ExamVO examScore) {
		super();
		this.dataSn = String.valueOf(examScore.getExamSn());
		this.dataType = "Y".equals(examScore.getSemiExamYn()) ? "SEMI" : "EXAM";
		this.dataTypeDetail = examScore.getExamTypeCd();
		this.dataTitle = examScore.getExamTitle();
		this.dataScore = examScore.getTotGetScore();
		this.dataScoreDttm = examScore.getEndDttm();
	}
	
	public ScoreDataDto(AssignmentVO asmtScore) {
		super();
		this.dataSn = String.valueOf(asmtScore.getAsmtSn());
		this.dataType = "ASMT";
		this.dataTypeDetail = asmtScore.getAsmtTypeCd();
		this.dataTitle = asmtScore.getAsmtTitle();
		this.dataScore = asmtScore.getScore();
		this.dataScoreDttm = asmtScore.getSendDttm();
	}
	
	public String getDataSn() {
		return dataSn;
	}
	public String getDataType() {
		return dataType;
	}
	public String getDataTypeDetail() {
		return dataTypeDetail;
	}
	public String getDataTitle() {
		return dataTitle;
	}
	public double getDataScore() {
		return dataScore;
	}
	public String getDataScoreDttm() {
		return dataScoreDttm;
	}
	public void setDataSn(String dataSn) {
		this.dataSn = dataSn;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setDataTypeDetail(String dataTypeDetail) {
		this.dataTypeDetail = dataTypeDetail;
	}
	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}
	public void setDataScore(double dataScore) {
		this.dataScore = dataScore;
	}
	public void setDataScoreDttm(String dataScoreDttm) {
		this.dataScoreDttm = dataScoreDttm;
	}
}

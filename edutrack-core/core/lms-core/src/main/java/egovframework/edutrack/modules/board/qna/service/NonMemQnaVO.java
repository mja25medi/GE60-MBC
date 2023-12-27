package egovframework.edutrack.modules.board.qna.service;
 
import egovframework.edutrack.comm.service.DefaultVO;

/**
 * 비회원 상담 신청 
 */
public class NonMemQnaVO extends DefaultVO {
	
	private static final long serialVersionUID = 1L;
	private int qnaSn; // 고유번호
	private String orgCd; // 기관코드
	private String regNm; // 등록자 이름
	private String regDttm; // 등록일시
	private String qsTel; // 연락처
	private String qsCts; // 상담 내용
	private String ansCts; // 답변 내용
	private String ansYn; // 답변 여부
	private String ansNo; // 답변자 번호
	private String ansDttm; // 답변일시
	private String modDttm; // 수정일시
	
	private String  viewMode; //

	public int getQnaSn() {
		return qnaSn;
	}

	public void setQnaSn(int qnaSn) {
		this.qnaSn = qnaSn;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getQsTel() {
		return qsTel;
	}

	public void setQsTel(String qsTel) {
		this.qsTel = qsTel;
	}

	public String getQsCts() {
		return qsCts;
	}

	public void setQsCts(String qsCts) {
		this.qsCts = qsCts;
	}

	public String getAnsCts() {
		return ansCts;
	}

	public void setAnsCts(String ansCts) {
		this.ansCts = ansCts;
	}

	public String getAnsYn() {
		return ansYn;
	}

	public void setAnsYn(String ansYn) {
		this.ansYn = ansYn;
	}

	public String getAnsNo() {
		return ansNo;
	}

	public void setAnsNo(String ansNo) {
		this.ansNo = ansNo;
	}

	public String getAnsDttm() {
		return ansDttm;
	}

	public void setAnsDttm(String ansDttm) {
		this.ansDttm = ansDttm;
	}

	public String getModDttm() {
		return modDttm;
	}

	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}
}

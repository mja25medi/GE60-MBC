package egovframework.edutrack.web.home.student.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class RefundAdd {
	@NotBlank(message = "환불은행명 입력 바랍니다.")
	@Size(min=2, max=20, message = "환불은행명을 입력바랍니다.")
	@Pattern(regexp="^[가-힣a-zA-Z]*$", message = "환불은행명을 다시 입력바랍니다. (숫자, 특수문자 불가)")
	private String repayBankNm;
	@NotNull(message = "계좌 사용자명을 입력 바랍니다.")
	@Pattern(regexp="^[가-힣a-zA-Z]*$", message = "계좌 사용자명을 다시 입력바랍니다. (숫자, 특수문자 불가)")
	@Size(min=2, max=30, message = "계좌 사용자명을 입력 바랍니다.")
	private String repayUserNm;
	@NotBlank(message = "계좌번호를 입력 바랍니다.")
	@Pattern(regexp="^(\\d{1,})(-(\\d{1,})){1,}", message = "계좌번호 형태가 올바르지 않습니다. (숫자, - 입력가능)")
	private String repayAcntNo;
	@NotBlank(message = "환불 사유를 입력 바랍니다.")
	@Size(min=2, max=1000, message = "환불 사유는 최소 2글자 이상 입력바랍니다.")
	private String repayReason;
	@NotBlank(message = "수강생 번호가 유효하지 않습니다.")
	private String stdNo;
	
	public String getRepayBankNm() {
		return repayBankNm;
	}
	public void setRepayBankNm(String repayBankNm) {
		this.repayBankNm = repayBankNm;
	}
	public String getRepayUserNm() {
		return repayUserNm;
	}
	public void setRepayUserNm(String repayUserNm) {
		this.repayUserNm = repayUserNm;
	}
	public String getRepayAcntNo() {
		return repayAcntNo;
	}
	public void setRepayAcntNo(String repayAcntNo) {
		this.repayAcntNo = repayAcntNo;
	}
	public String getRepayReason() {
		return repayReason;
	}
	public void setRepayReason(String repayReason) {
		this.repayReason = repayReason;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	
	
}

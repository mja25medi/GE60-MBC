package egovframework.edutrack.comm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RefundHistory{
	
	RefundType value();
	
	public enum RefundType {
		REQUEST("R","환불요청"),//환불신청
		USER_COMPLETE("UC","수강생 환불완료"),//수강생이 직접 취소하는 경우(이니시스 카드결제,실시간계좌이체) - 환불완료, 수강상태 변경
		ADMIN_COMPLETE("AC","관리자 환불완료"), //관리자가 직접 환불 처리하는 경우(이니시스 카드결제,실시간계좌이체 / 수강생에게 직접입금) - 환불완료, 수강상태 변경
		MEMO("M","메모 수정"), //환불메모변경
		CANCEL("N","환불 취소"), //환불취소
		//PART_COMPLETE("PC", "부분 환불"),//미사용
		EMPTY("", "");
		//부분환불완료 - 금액만 일부 환불 / 수강상태 미변경
		;
		
		private String code;
		private String value;
		
		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
		
		public String getKey() {
	        return name();
	    }

		RefundType(String code, String value) {
			this.code = code;
			this.value = value;
		}
		
		 public static RefundType findByCodeValue(String code) {
	    	return Arrays.stream(RefundType.values())
	    			.filter(refundType -> refundType.getCode().equals(code))
	    			.findAny()
	    			.orElse(EMPTY);
	    }
		
	}
}

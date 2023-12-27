package egovframework.edutrack.modules.student.payment.service;

import java.util.Arrays;

public enum PayMthodCode {
	PC_Card("Card", "신용카드(안심클릭)(PC)")
	,PC_VCard("VCard", "신용카드(ISP)(PC)")
	,PC_DIRECTBANK("DirectBank", "실시간계좌이체(PC)")
	,PC_VBANK("VBank", "가상계좌(무통장입금)(PC)")
	,MOBILE_CARD("CARD","신용카드(안심클릭,ISP)(모바일)")
	,MOBILE_BANK("BANK","실시간계좌이체(모바일)")
	,MOBILE_VBANK("VBANK","가상계좌(무통장입금)(모바일)")
	,EMPTY("없음", "");
	
	private String code;
	private String value;
	
	private PayMthodCode(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getKey() {
        return name();
    }
	
	public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
    
    public static PayMthodCode findByCodeValue(String code) {
    	return Arrays.stream(PayMthodCode.values())
    			.filter(cardCode -> cardCode.getCode().equals(code))
    			.findAny()
    			.orElse(EMPTY);
    }
    
}

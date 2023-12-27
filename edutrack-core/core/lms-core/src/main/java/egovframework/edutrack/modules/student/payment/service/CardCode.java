package egovframework.edutrack.modules.student.payment.service;

import java.util.Arrays;

public enum CardCode {
	BC("11", "BC카드")
	,SAMSUNG("12", "삼성카드")
	,SINHAN("14", "신한카드")
	,HANMI("15", "한미카드")
	,NH("16", "NH카드")	
	,HANASK("17", "하나 SK카드")
	,GVISA("21", "글로벌 VISA")
	,GMASTER("22", "글로벌 MASTER")
	,GJCB("23", "글로벌 JCB")	
	,GAEC("24", "글로벌 아멕스")
	,GDINERS("25", "글로벌 다이너스")
	,NAVERPOINT("91", "네이버포인트(포인트 100% 사용)")
	,TOSSPOINT("93", "토스머니(포인트 100% 사용)")	
	,SSGMONEY("94", "SSG머니(포인트 100% 사용)")
	,LPOING("96", "엘포인트(포인트 100% 사용)")
	,KAKAOMONEY("97", "카카오머니")
	,PAYCO("98", "페이코(포인트 100% 사용)")	
	,KEB("01", "외환카드")
	,LOTTE("03", "롯데카드")
	,HYUNDAI("04", "현대카드")
	,KOOKMIN("06", "국민카드")
	,EMPTY("없음", "");
	
	private String code;
	private String value;
	
	private CardCode(String code, String value) {
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
    
    public static CardCode findByCodeValue(String code) {
    	return Arrays.stream(CardCode.values())
    			.filter(cardCode -> cardCode.getCode().equals(code))
    			.findAny()
    			.orElse(EMPTY);
    }
    
}

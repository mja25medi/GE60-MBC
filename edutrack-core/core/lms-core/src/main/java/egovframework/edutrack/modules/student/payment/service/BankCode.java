package egovframework.edutrack.modules.student.payment.service;

import java.util.Arrays;

public enum BankCode {
	NHCENTER("11",	"농협중앙회")	
	,NHUNIT("12",	"단위농협")
	,COOPER("16",	"축협중앙회")
	,WOORI("20",	"우리은행")
	,ChOHUNG("21",	"구)조흥은행")	
	,SANGUP("22",	"상업은행")
	,SC("23",	"SC제일은행")	
	,HANIL("24",	"한일은행")
	,SEOUL("25",	"서울은행")
	,SINHANOLD("26",	"구)신한은행")
	,CITYBANKHANMI("27",	"한국씨티은행 (구 한미)")	
	,DAEGU("31",	"대구은행")
	,BUSAN("32",	"부산은행")
	,GWANGJU("34",	"광주은행")
	,JAEJU("35",	"제주은행")	
	,JEONBUK("37",	"전북은행")
	,GANKWON("38",	"강원은행")
	,GYEONGNAM("39",	"경남은행")
	,BC("41",	"비씨카드")	
	,KFCC("45",	"새마을금고")
	,CU("48",	"신용협동조합중앙회")	
	,FSB("50",	"상호저축은행")
	,CITYBANK("53",	"한국씨티은행")	
	,HSBC("54",	"홍콩상하이은행")
	,DB("55",	"도이치은행")	
	,ABN("56",	"ABN암로")
	,JP("57",	"JP모건")	
	,UFJ("59",	"미쓰비시도쿄은행")
	,BOA("60",	"BOA(Bank of America)")	
	,NFCF("64",	"산림조합")
	,BARO("70",	"신안상호저축은행")	
	,EPOST("71",	"우체국")
	,KEBHANA("81",	"하나은행")	
	,WOORIBANK("83",	"평화은행")
	,SIN("87",	"신세계")	
	,SINHAN("88",	"신한(통합)은행")
	,KBANK("89",	"케이뱅크")	
	,KAKAO("90",	"카카오뱅크")
	,NAVERPOINT("91",	"네이버포인트(포인트 100% 사용)")	
	,TOSS("92",	"토스뱅크")
	,TOSSMONEY("93",	"토스머니(포인트100% 사용)")
	,SSGMONEY("94",	"SSG머니(포인트 100% 사용)")
	,LPOINT("96",	"엘포인트(포인트 100% 사용)")	
	,KAKAOMONEY("97",	"카카오 머니")
	,PAYCO("98",	"페이코 (포인트 100% 사용)")	
	,KDB("02",	"한국산업은행")
	,IBK("03",	"기업은행")	
	,KOOKMIN("04",	"국민은행")
	,HANAOLD("05",	"하나은행 (구 외환)")	
	,KOOKMINHOUSE("06",	"국민은행 (구 주택)")
	,SUHYUP("07",	"수협중앙회")	
	,MYASSETDONGYANG("D1",	"유안타증권(구 동양증권)")
	,HYUNDAI_INVEST("D2",	"현대증권")	
	,MIRAE_INVEST("D3",	"미래에셋증권")
	,KOREA_INVEST("D4",	"한국투자증권")
	,WOORI_INVEST("D5",	"우리투자증권")
	,HI_INVEST("D6",	"하이투자증권")	
	,HMC_INVEST("D7",	"HMC투자증권")
	,SK_INVEST("D8",	"SK증권")	
	,DAESIN_INVEST("D9",	"대신증권")
	,HANA_INVEST("DA",	"하나대투증권")	
	,SINHAN_INVEST("DB",	"굿모닝신한증권")
	,DONGBU_INVEST("DC",	"동부증권")	
	,YUJIN_INVEST("DD",	"유진투자증권")
	,MERITZ_INVEST("DE",	"메리츠증권")	
	,SINYOUNG_INVEST("DF",	"신영증권")
	,DAEWOO_INVEST("DG",	"대우증권")	
	,SAMSUNG_INVEST("DH",	"삼성증권")
	,KYOBO_INVEST("DI",	"교보증권")	
	,KIYUM_INVEST("DJ",	"키움증권")
	,ETRADE_INVEST("DK",	"이트레이드")	
	,SOLOMON_INVEST("DL",	"솔로몬증권")
	,HANHWA_INVEST("DM",	"한화증권")	
	,NH_INVEST("DN",	"NH증권")
	,BOOKOOK_INVEST("DO",	"부국증권")	
	,LIG_INVEST("DP",	"LIG증권")
	,BANK_WALLLET("BW",	"뱅크월렛")
	,EMPTY("없음", "");
	
	private String code;
	private String value;
	
	private BankCode(String code, String value) {
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

	public static BankCode findByCodeValue(String code) {
    	return Arrays.stream(BankCode.values())
    			.filter(bankCode -> bankCode.getCode().equals(code))
    			.findAny()
    			.orElse(EMPTY);
	}
	
}

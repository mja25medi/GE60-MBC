package egovframework.edutrack.comm.code.impl;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import egovframework.edutrack.comm.code.CodeType;

@JsonFormat(shape = Shape.OBJECT)
public enum RentStatusCode implements CodeType{
	
	  REQUEST("대여신청", "request", true)
	, APPROVED("승인완료", "approved", true)
	, RENT("대여중","rent", true)
	, RENT_CANCEL("대여취소","rent cancel" , false)
	, REQ_CANCEL("신청취소", "request cancel" , false)
	, CLOSE("반납완료","close" , false)
	, OVERDUE("미반납", "overdue", false)
	, EMPTY("","", false);
	;
	
	private final String codeNm;
	private final String enCodeNm;
	private final boolean validInfo;
	
	private RentStatusCode(String codeNm, String enCodeNm, boolean validInfo) {
		this.codeNm = codeNm;
		this.enCodeNm = enCodeNm;
		this.validInfo = validInfo;
	}
	
	public static RentStatusCode findCode(String name) {
		return Arrays.stream(RentStatusCode.values())
				.filter(code -> code.name().equals(name))
				.findAny()
				.orElse(EMPTY);
	}
	
	@Override
	public String getCodeTypeCd() {
		return this.name();
	}
	
	@Override
	public String getCodeTypeNm() {
		Locale locale = LocaleContextHolder.getLocale();
		if(Locale.KOREAN.equals(locale)) return codeNm;
		if(Locale.ENGLISH.equals(locale)) return enCodeNm;
		return this.name().toLowerCase(); 
	}
	
	public String getCodeNm() {
		return codeNm;
	}
	public String getEnCodeNm() {
		return enCodeNm;
	}
	public boolean getValidInfo() {
		return validInfo;
	}
}

package egovframework.edutrack.comm.code.impl;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import egovframework.edutrack.comm.code.CodeType;

@JsonFormat(shape = Shape.OBJECT)
public enum ReserveStatusCode implements CodeType{
	
	  WAIT("예약대기", "wait", true)
	, RESERVED("예약완료", "reserved", true)
	, RETURN("예약반려", "return", false)
	, CANCEL("예약취소", "cancel", false)
	, CLOSE("예약종료", "close", false)
	, EMPTY("", "", false);
	;
	
	private final String codeNm;
	private final String enCodeNm;
	private final boolean validInfo;
	
	private ReserveStatusCode(String codeNm, String enCodeNm, boolean validInfo) {
		this.codeNm = codeNm;
		this.enCodeNm = enCodeNm;
		this.validInfo = validInfo;
	}
	
	public static ReserveStatusCode findCode(String name) {
		return Arrays.stream(ReserveStatusCode.values())
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

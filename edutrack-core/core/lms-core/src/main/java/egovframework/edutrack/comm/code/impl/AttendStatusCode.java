package egovframework.edutrack.comm.code.impl;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import egovframework.edutrack.comm.code.CodeType;

@JsonFormat(shape = Shape.OBJECT)
public enum AttendStatusCode implements CodeType {
	
	  ATTEND("출석", "attend" , 1)
	, ABSENCE("미출석", "absence", 0)
	, ALTER("보강출석", "alter", 0.5)
	, LATE("지각", "late", 0.5)
	, EMPTY("", "", 0);
	;
	
	private final String codeNm;
	private final String enCodeNm;
	private final double scoreWeight;
	
	private AttendStatusCode(String codeNm, String enCodeNm, double scoreWeight) {
		this.codeNm = codeNm;
		this.enCodeNm = enCodeNm;
		this.scoreWeight = scoreWeight;
	}
	
	public static AttendStatusCode findCode(String name) {
		return Arrays.stream(AttendStatusCode.values())
					.filter(eNum -> eNum.name().equals(name))
					.findAny()
					.orElse(EMPTY);
	}
	
	public int calcPrgrRatio() {
		return (int) (scoreWeight*100);
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
	
	public double getScoreWeight() {
		return scoreWeight;
	}
	
	public String getCodeNm() {
		return codeNm;
	}
}

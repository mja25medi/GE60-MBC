package egovframework.edutrack.comm.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * 과정 정보 HRD API 연계용 어노테이션 
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface HrdApiCrsCreCrs {
	CreSyncType  value();
	
	public enum CreSyncType {
		CREATE("C"), CREUPDATE("U"), DELETE("D"), CRSUPDATE("U");
		
		private CreSyncType(String s) {
			this.setStringValue(s);
		}
		
		private String stringValue;
		
		public String getStringValue() {
			return stringValue;
		}

		public void setStringValue(String stringValue) {
			this.stringValue = stringValue;
		}
	}
}

package egovframework.edutrack.modules.log.userupdate.service;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface UserInfoLog {
	LogType value();
	
	public enum LogType {
		SELECT, UPDATE, DELETE;		
	}
}

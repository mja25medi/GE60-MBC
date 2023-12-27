package egovframework.edutrack.comm.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 *  HRD API 사용 여부 확인용 어노테이션 
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface HrdApiUseYn {
	
}

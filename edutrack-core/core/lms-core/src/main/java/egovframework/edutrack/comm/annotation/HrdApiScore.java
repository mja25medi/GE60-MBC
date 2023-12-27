package egovframework.edutrack.comm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HrdApiScore {
	/*enum Category{EXAM, ASMT, BOOKMARK}
	enum SaveType{START,END,RATE,RESET}//시작,종료,평가,초기화
	
	Category category();
	SaveType saveType();*/
}

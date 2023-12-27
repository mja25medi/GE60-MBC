package egovframework.edutrack.comm.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

public class TaglibUtil {
	
	@Autowired
	private static MessageSource messageSource;
	
	@Autowired
	private static LocaleResolver localeResolver;

	/**
	 * 메시지 반환
	 * @param pageContext
	 * @param msgkey
	 * @return
	 */
	public static String getMessage(PageContext pageContext, String msgkey) {
		String bundle = null;
		String message = null;
		
		if (msgkey != null && msgkey.indexOf(".") > -1) {
			int idx = msgkey.indexOf(".");
			bundle = msgkey.substring(0, idx);
		}
		else {
			return msgkey;
		}

		HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
 
 		try {
			message = messageSource.getMessage(msgkey, null, "", localeResolver.resolveLocale(req));
			if(message == null) {
				message = msgkey;
			}
 		}
 		catch (Exception e) { 
 			return msgkey;
 		}

 		return message;
	}
}

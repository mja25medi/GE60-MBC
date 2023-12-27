package egovframework.edutrack.comm.web.taglib;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.MessageSource;

import egovframework.edutrack.comm.util.web.LocaleUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;

/**
 * @author 장철웅
 * 
 */
public class MessageTag extends TagSupport {
	private static final long serialVersionUID = -7495773875386347845L;

	String messageKey = ""; // 메시지키 Or 메시지
	String args1 = "";
	String args2 = "";
	String args3 = "";

    /** MessageSource */
    @Resource(name = "messageSource")
    private MessageSource messageSource;
	
	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey
	 * the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	

	public String getArgs1() {
		return args1;
	}

	public void setArgs1(String args1) {
		this.args1 = args1;
	}

	public String getArgs2() {
		return args2;
	}

	public void setArgs2(String args2) {
		this.args2 = args2;
	}

	public String getArgs3() {
		return args3;
	}

	public void setArgs3(String args3) {
		this.args3 = args3;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			String title = null;
			if (messageKey != null && !messageKey.equalsIgnoreCase("null")
					&& !messageKey.equals("")) {
				int bundleIdx = messageKey.indexOf(".");
				if (bundleIdx > 0) {
					String bundle = messageKey.substring(0, bundleIdx);
					PageContext context = this.pageContext;
					HttpServletRequest req = (HttpServletRequest) context
							.getRequest();
					Locale locale = LocaleUtil.getLocale(req);

					String[] args = null;
					int argsCnt = 0;
					if(ValidationUtils.isNotEmpty(args1)) argsCnt ++;
					if(ValidationUtils.isNotEmpty(args2)) argsCnt ++;
					if(ValidationUtils.isNotEmpty(args3)) argsCnt ++;
					
					if(argsCnt > 0) {
						args= new String[argsCnt];
						int lineNum = 0;
						if(ValidationUtils.isNotEmpty(args1)) {
							args[lineNum] = args1;
							lineNum++;
						}
						if(ValidationUtils.isNotEmpty(args2)) {
							args[lineNum] = args2;
							lineNum++;
						}
						if(ValidationUtils.isNotEmpty(args3)) {
							args[lineNum] = args3;
							lineNum++;
						}
					}

					try {
						if(argsCnt > 0) {
							title = messageSource.getMessage(messageKey, args, "", locale);
						} else {
							title = messageSource.getMessage(messageKey, null, "", locale);
						}
					} catch (Exception e) {
						title = messageKey;
					}
				}
			}

			pageContext.getOut().print(title);
		} catch (IOException ignored) {
		}

		return EVAL_PAGE;
	}
}

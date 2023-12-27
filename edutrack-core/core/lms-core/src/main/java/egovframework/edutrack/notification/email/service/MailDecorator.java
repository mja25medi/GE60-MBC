package egovframework.edutrack.notification.email.service;


import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.log.message.service.MessageDecorator;


/**
 * 기본 메일 포맷 데코레이터
 * @author SungKook
 *
 */
public class MailDecorator implements MessageDecorator {


	@Override
	public String decorate(String contents) {
		return contents;
	}

	private String convertHtml(String contents) {
		return StringUtil.getHtmlContents(contents);
	}

}

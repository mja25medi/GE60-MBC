package egovframework.edutrack.notification;

import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;

public interface SendService {

	public abstract void sendMessage(LogMsgTransLogVO msg)
			throws MessageNotificationException;

	//-- 2015.11.18 jamfam 썬더메일 대량 메일 연동시 생성.
	public abstract void sendMassMessage(LogMsgLogVO msg)
			throws MessageNotificationException;

}
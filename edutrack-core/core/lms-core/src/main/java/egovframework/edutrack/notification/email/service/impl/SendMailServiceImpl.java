package egovframework.edutrack.notification.email.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.andwise.tm6.api.jars.automail.AutomailAPI;
import com.andwise.tm6.api.jars.massmail.MassmailAPI;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.CommonService;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.notification.MessageNotificationException;
import egovframework.edutrack.notification.SendService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 *  <b>공통 - 메시지 : 메일 전송 </b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sendMailService")
public class SendMailServiceImpl extends 
	EgovAbstractServiceImpl implements SendService {
	
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private JavaMailSender		mailSender;

	@Resource(name="commonService")
	private CommonService 		commonService;

	@Resource(name="orgEmailTplService")
	private OrgEmailTplService 	orgEmailTplService;

	@Override
	public void sendMessage(LogMsgTransLogVO msg) throws MessageNotificationException {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			if("ThunderMail".equals(Constants.MAIL_AGENT)) {
				AutomailAPI automailAPI = new AutomailAPI();
				automailAPI.setApiURL(Constants.THUNDER_AUTO_APIURL);					//썬더메일UI URL
				automailAPI.setAutomailIDEncrypt(Constants.THUNDER_AUTO_MAILID);		//썬더메일ID 암호화값
				automailAPI.setMailTitle(msg.getMessage().getTitle());					//메일 제목
				automailAPI.setMailContent(msg.getMessage().getCtsHtml());				//메일 내용
				automailAPI.setSenderName(msg.getMessage().getSendNm());				//보내는 사람 이름
				automailAPI.setSenderEmail(msg.getMessage().getSendAddr());				//보내는 사람 이메일
				automailAPI.setReceiverName(msg.getRecvNm());							//받는 사람 이름
				automailAPI.setReceiverEmail(msg.getRecvAddr());						//받는 사람 이메일
				automailAPI.setReturnMail(msg.getMessage().getSendAddr());				//반송 이메일
				//automailAPI.setOnetooneInfo("[$name]Ð홍길동æ[$birthday]Ð2010-01-01æ");
												//일대일치환 정보
				//automailAPI.setReserveDate("2015-07-22 15:00:00");					//예약발송 일시
				automailAPI.sendEmail();												//메일 발송(리턴결과는 7페이지 발송결과 확인 참고)

				System.out.println("thundermail ---------- auto code = " + automailAPI.getCode());
				System.out.println("thundermail ---------- auto msg = " + automailAPI.getMsg());
				if("100".equals(automailAPI.getCode())) {
					msg.setTransDttm(commonService.getCurrentDBTime());
					msg.setTransSts(LogMsgTransLogVO.SEND_SUCCES);
				} else {
					msg.setTransSts(LogMsgTransLogVO.SEND_FAILED);
					msg.setErrorMsg(automailAPI.getMsg());
				}
			} else {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, Constants.ENCODING_SYSTEM);
				InternetAddress sender = new InternetAddress(msg.getMessage().getSendAddr(), msg
						.getMessage().getSendNm());
				InternetAddress recever = new InternetAddress(msg.getRecvAddr(),
						msg.getRecvNm());

				helper.setFrom(sender);
				helper.setTo(recever);

				helper.setSubject(msg.getMessage().getTitle());
				helper.setText(msg.getMessage().getCtsHtml(), msg.getMessage().isHtml());

				msg.setTransDttm(commonService.getCurrentDBTime());

				// 첨부파일이 있을 경우 파일도 첨부..
				List<SysFileVO> attachFiles = msg.getMessage().getAttachFiles();
				if(!attachFiles.isEmpty()) {
					for (SysFileVO fileVO : attachFiles) {
						FileSystemResource resource = new FileSystemResource(
								Constants.FILE_STORAGE_PATH + File.separator +StringUtil.nvl(fileVO.getOrgCd(),"ORG0000001") + fileVO.getSaveFilePath());
	//					helper.addAttachment(SysFileVO.getFileNm(), resource);
						helper.addAttachment(new String(fileVO.getFileNm().getBytes("UTF-8"), "8859_1"), resource);
					}
				}

				mailSender.send(message);
				msg.setTransSts(LogMsgTransLogVO.SEND_SUCCES);
			}

		} catch (Exception e) {
			msg.setTransSts(LogMsgTransLogVO.SEND_FAILED);
			msg.setErrorMsg(e.getMessage());
		//	throw new MessageNotificationException(e);
		}
		log.info("Send mail to " + msg.getRecvAddr() + " : status[" + msg.getTransSts() + "]");
	}

	//-- 2015.11.18 jamfam 썬더메일 대량 메일 연동시 생성.
	@Override
	public void sendMassMessage(LogMsgLogVO msg) throws MessageNotificationException {
		MimeMessage message = mailSender.createMimeMessage();
		try {

			//-- Email 데코레이션
			String orgCd = msg.getOrgCd();
			Map<String, Object> argu = new HashMap<String, Object>();
			argu.put("Title", msg.getTitle());
			argu.put("Contents", msg.getCts());
			orgEmailTplService.decorator(orgCd, "EM000", argu, msg);

			if("ThunderMail".equals(Constants.MAIL_AGENT)) {
				MassmailAPI massmailAPI = new MassmailAPI();
				massmailAPI.setApiURL(Constants.THUNDER_MASS_APIURL);					//썬더메일UI url
				massmailAPI.setUserCodeEncrypt(Constants.THUNDER_MASS_USERCD);			//메일 작성자 ID
				massmailAPI.setMailTitle(msg.getTitle());								//메일 제목
				massmailAPI.setMassmailTitle(msg.getTitle());							//썬더메일 내에서의 제목
				massmailAPI.setSenderEmail(msg.getSendAddr()); 							//보내는 사람 이메일
				massmailAPI.setSenderName(msg.getSendNm());								//보내는 사람 이름
				massmailAPI.setReceiverName("[$name] 님");								//받는 사람 이름
				massmailAPI.setMailContent(msg.getCtsHtml());

				massmailAPI.setSendType("1");
				// massmailAPI.setExpectSendDate(“2015-12-31”);						//sendType이 2일 때 필수 (yyyy-MM-dd)
				// massmailAPI.setExpectSendTime(“13:10”);							//sendType이 2일 때 필수 (HH:dd)

				// 수신자 그룹을 만들자.
				String targetStr = "";
				int targetCnt = 0;
				for(LogMsgTransLogVO mtdto : msg.getSubTransList()) {
					if(ValidationUtils.isNotEmpty(mtdto.getRecvAddr())) {
						//-- 이메일 주소가 있는 것만 보내기.
						targetCnt ++;
						if(targetCnt > 1) targetStr += "Æ";
						targetStr += mtdto.getRecvAddr()+","+mtdto.getRecvNm();
					}
				}
				massmailAPI.setTargetString(targetStr);
				massmailAPI.setOnetooneInfos("1:EMAIL:1^email,2:NAME:3^name");

				// 첨부파일이 있을 경우 파일도 첨부..
				String attachFileNames = "";
				String attachSaveNames = "";
				int attachCnt = 0;
				List<SysFileVO> attachFiles = msg.getAttachFiles();
				if(!attachFiles.isEmpty()) {
					for (SysFileVO fileVO : attachFiles) {
						attachCnt++;
						if(attachCnt > 1) {
							attachFileNames += ";";
							attachSaveNames += ";";
						}
						attachFileNames += fileVO.getFileNm();
						attachSaveNames += Constants.FILE_STORAGE_PATH+fileVO.getFilePath()+"/"+fileVO.getFileNm();
					}
				}
				massmailAPI.setFileNames(attachFileNames);		// 첨부 할 파일명 (2개 이상이면 세미콜론(;) 구분)
				massmailAPI.setFilePaths(attachSaveNames);		// 첨부 할 파일경로 (2개 이상이면 세미콜론(;) 구분) massmailAPI.sendEmail()		//메일 발송, 연동 결과 리턴

				massmailAPI.sendEmail();

				System.out.println("thundermail ---------- mass code = " + massmailAPI.getCode());
				System.out.println("thundermail ---------- mass msg = " + massmailAPI.getMsg());
				if("100".equals(massmailAPI.getCode())) {
					msg.setSendSts("S");
				} else {
					msg.setSendSts("F");
					msg.setTransSts(massmailAPI.getMsg());
				}
			} else {
				msg.setSendSts("F");
			}
		} catch (Exception e) {
			msg.setSendSts("F");
			throw new MessageNotificationException(e);
		}
	}
}

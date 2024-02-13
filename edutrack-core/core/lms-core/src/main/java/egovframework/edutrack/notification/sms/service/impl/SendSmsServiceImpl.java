package egovframework.edutrack.notification.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.CommonService;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.notification.MessageNotificationException;
import egovframework.edutrack.notification.SendService;
import egovframework.edutrack.notification.sms.service.Message;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>공통 - 메시지 : SMS 전송 </b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sendSmsService")
public class SendSmsServiceImpl 
	extends EgovAbstractServiceImpl implements SendService {

	@Resource(name="commonService")
	private CommonService 	commonService;

	private static final String	SMS_USERCODE	= "danuri";
	private static final String	SMS_USERNAME	= "다누리";
	private static final String	SMS_DEPTCODE	= "XB-QJS-77";
	private static final String	SMS_DEPTNAME	= "다문화가정";


	@Override
	public void sendMessage(LogMsgTransLogVO trans) throws MessageNotificationException {

		Message sms = new Message();

		/* 기본 적으로 받아야 하는 값들 */
		//private String					member;				// Client측 key일련번호
		//private String					usercode		= null; // 사용자 발신 코드
		//private String					username		= null; // 사용자명
		//private String					callphone1		= null; // 호출 번호 #1
		//private String					callphone2		= null; // 호출 번호 #2
		//private String					callphone3		= null; // 호출 번호 #3
		//private String					callmessage		= null; // 호출 메시지
		//private String					rdate			= null; // 메시지 전송 예약일자
		//private String					rtime			= null; // 메시지 전송 예약시간
		//private String					reqphone1		= null; // 회신 번호#1
		//private String					reqphone2		= null; // 회신 번호#2
		//private String					reqphone3		= null; // 회신 번호#3
		//private String					callname		= null;
		//private String					deptcode		= null; // 회사 코드
		//private String					deptname		= null; // 회사명
		//private String					callurl			= null; // CallUrl
		//private String					status			= null;

		// 우선 실패로 상태를 기록..
		trans.setTransSts(LogMsgTransLogVO.SEND_FAILED);

		if(trans.getMessage().getMsgSn() == null) {
			throw new MessageNotificationException("SMS 송신에 필요한 MessageDTO 정보가 null입니다.");
		}

		String requestNo[] = splitPhoneNoWhidCheck(trans.getMessage().getSendAddr());
		String reciverNo[] = splitPhoneNoWhidCheck(trans.getRecvAddr());

		String rDttm = StringUtil.nvl(trans.getMessage().getRsrvSendDttm(), "00000000000000");

		String smsResult = sms.sendMain(
				trans.getMsgTransSn().toString(),
				SMS_USERCODE,
				SMS_USERNAME,
				reciverNo[0], reciverNo[1], reciverNo[2],
				trans.getMessage().getCts(),
				rDttm.substring(0, 8),	// rdate
				rDttm.substring(8),		// rtime
				requestNo[0], requestNo[1], requestNo[2],
				StringUtil.nvl(trans.getMessage().getSendNm(), SMS_USERNAME),
				SMS_DEPTCODE,
				SMS_DEPTNAME);
		try {
			trans.setTransDttm(commonService.getCurrentDBTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(smsResult.equals("O")) {
			trans.setTransSts(LogMsgTransLogVO.SEND_SUCCES);
		} else {
			throw new MessageNotificationException("전송처리 결과가 정상 처리값(O)이 아닙니다. : " + smsResult);
		}
	}

	private String[] splitPhoneNoWhidCheck(String phoneNo) {
		String[] seperate = null;
		try {
			if(phoneNo.indexOf("-") > -1) {
				seperate = phoneNo.split("-");
				for (int i = 0; i < 3; i++) {
					Integer.parseInt(seperate[i]);
				}
			} else {
				if(phoneNo.length() >= 11) {
					seperate = new String[3];
					seperate[0] = StringUtil.substring(phoneNo, 0, 3);
					seperate[1] = StringUtil.substring(phoneNo, 3, 7);
					seperate[2] = StringUtil.substring(phoneNo, phoneNo.length()-4, phoneNo.length());
				}
			}
		} catch (Exception ex) {
			throw new MessageNotificationException("잘못된 유형의 전화번호입니다. : " + phoneNo);
		}
		return seperate;
	}

	@Override
	public void sendMassMessage(LogMsgLogVO msg) throws MessageNotificationException {

	}
}

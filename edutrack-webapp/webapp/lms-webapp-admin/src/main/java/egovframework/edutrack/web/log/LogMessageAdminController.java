package egovframework.edutrack.web.log;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.exception.service.LogExceptionService;
import egovframework.edutrack.modules.log.exception.service.LogExceptionVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/log/message")
public class LogMessageAdminController extends GenericController {

	 /** service */
	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService logMsgLogService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService orgInfoService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 메시지 발송폼 (msgDivCd, msgPurpDivCd 파라매터가 필요하다)
	 * @param LogMsgLogVO 
	 * @param commandMap
	 * @param model
	 * @return  "/log/message/write_email.jsp"
	 * @throws Exception
	 */	
	@RequestMapping(value="/addMessagePop")
	public String addMessagePop(LogMsgLogVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		List<LogMsgTransLogVO> reciverList = logMsgLogService.listReceiver(vo).getReturnList();
		
		//String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(StringUtil.nvl(vo.getOrgCd()));
		orgInfoVO = orgInfoService.view(orgInfoVO);
		
		vo.setSendAddr(orgInfoVO.getEmailAddr());
		vo.setSendNm(orgInfoVO.getEmailNm());
	
		// 메시지 종류에 따라 컨피그에서 기본값을 설정한다.
		if("MSG".equals(vo.getMsgDivCd())) {
			//-- 메시지의 경우 보네는 사람의 메시지 전송 정보를 입력한다.
			vo.setSendAddr(UserBroker.getUserNo(request));
			vo.setSendNm(UserBroker.getUserName(request));
		} else {
			//-- EMAIL, SMS의 경우 시스템의 정보를 가져와 입력한다.
			if("EMAIL".equals(vo.getMsgDivCd())) {
				vo.setSendAddr(orgInfoVO.getEmailAddr());
			} else if("SMS".equals(vo.getMsgDivCd())) {
				vo.setSendAddr(orgInfoVO.getRprstPhoneNo());
			}
			vo.setSendNm(orgInfoVO.getEmailNm());
		}
		
		
		model.addAttribute("targetList", reciverList);
		model.addAttribute("vo", vo);
		
		String returnUrl="log/message/write_email_pop";
		if("EMAIL".equals(vo.getMsgDivCd())) {
			model.addAttribute("fileupload", "Y");
			returnUrl = "log/message/write_email_pop";
		} else if("SMS".equals(vo.getMsgDivCd())) {
			returnUrl = "log/message/write_sms_pop";
		} else {
			model.addAttribute("uploadify", "Y");
			returnUrl = "log/message/write_msg_pop";
		}
		
		return returnUrl;
	}

	/**
     * @Method Name : addMessage
     * @Method 설명 : 메일보내기
	 * @param LogMsgLogVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addMessage")
	public String addMessage(LogMsgLogVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO result = new ProcessResultVO();
		
		try {
			// 발신 처리 메뉴 코드 저장
			vo.setSendMenuCd(UserBroker.getMenuCode(request));

			//예약발신일시
			String rsrvDttm = "00000000000000";
			
			String sendType = StringUtil.nvl(request.getParameter("sendType"));
			if("R".equals(sendType)) {
				rsrvDttm = StringUtil.nvl(vo.getRsrvDate(), "00000000").replaceAll("\\.", "")
						+ StringUtil.nvl(vo.getRsrvHour(), "00")
						+ StringUtil.nvl(vo.getRsrvMin(),  "00") + "00";
			}
			
			vo.setRsrvSendDttm(rsrvDttm);
			
			// 저장후 발신
			LogMsgLogVO resultDTO = logMsgLogService.addMessage(vo);
			
			result.setResult(1);
			result.setMessage(getMessage(request, "log.message.msg.note.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "log.message.msg.note.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
}

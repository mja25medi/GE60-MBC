package egovframework.edutrack.web.lecture.message;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.notification.MessageNotificationException;


/**
 * 로그 이메일 액션 컨트롤러
 * @author DongKwang
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/message")
public class MessageLectureController extends GenericController {

	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService messageService;
	
	@Autowired @Qualifier("sysCfgService")
	private SysCfgService configService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService orgInfoService;

    /**
	 * 메시지 발송폼 (msgDivCd, msgPurpDivCd 파라매터가 필요하다)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/messageWritePop")
	public String messageWritePop(LogMsgLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		LogMsgTransLogVO transVO = vo.getLogMsgTransLogVO();

		String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);
		
		// 만약 SMS이고 월별 전송 제한 횟수를 넘어서 발송이 불가능 하면..
/*		if(vo.getMsgDivCd().equals("SMS")
				&& !messageService.isPossibleSendSmsByMonthLimit())
			throw new ServiceProcessException(getMessage(request, "log.message.msg.alert.sms.limited"));*/
		
		vo.setLogMsgTransLogVO(transVO);
		List<LogMsgTransLogVO> reciverList = messageService.listReceiver(vo).getReturnList();
		
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

		request.setAttribute("targetList", reciverList);
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		
		String forwardName = "";
		if("EMAIL".equals(vo.getMsgDivCd())) forwardName = "home/lecture/message/write_email_pop";
		else if("SMS".equals(vo.getMsgDivCd())) forwardName = "home/lecture/message/write_sms_pop";
		else forwardName = "home/lecture/message/write_msg_pop";
		
		return forwardName;
	}
	
	@RequestMapping(value="/addMessage")
	public String addMessage(LogMsgLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		// 발신 처리 메뉴 코드 저장
		vo.setSendMenuCd(UserBroker.getMenuCode(request));
		
		String rsrvDttm = StringUtil.nvl(vo.getRsrvDate(), "00000000").replaceAll("\\.", "")
			+ StringUtil.nvl(vo.getRsrvHour(), "00")
			+ StringUtil.nvl(vo.getRsrvMin(),  "00") + "00";
		
		vo.setRsrvSendDttm(rsrvDttm);
		// 저장후 발신
		ProcessResultVO<LogMsgLogVO> resultVO =  new ProcessResultVO<LogMsgLogVO>();
		try{
			messageService.addMessage(vo);
			resultVO.setMessage(getMessage(request, "log.message.msg.note.add.success"));
		}catch (MessageNotificationException ex) {
			resultVO.setMessage(getMessage(request, "log.message.msg.note.add.failed"));		
		}

		return JsonUtil.responseJson(response, resultVO, this.getMessageJsonConfig());
	}
	
	/**
	 * 순환참조를 막기 위해 MessageVO와 MessageTransVO의 상호 연관관계에서
	 * MessageTransVO의 message를 Json으로 반환시키지 않는다.
	 * @return
	 */
	private JsonConfig getMessageJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object obj, String name, Object value) {
				if(obj instanceof LogMsgTransLogVO && name.equals("message")) {
					return true;
				}
				return false;
			}
		});
		return jsonConfig;
	}
	
	/**
	 * 받은쪽지함 목록
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listRecvPop")
	public String listRecvPop(LogMsgLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "home/lecture/message/msg_recv_main_pop";
	}
	
	@RequestMapping(value="/listMsg")
	public String listMsg(LogMsgLogVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setListScale(10);
		ProcessResultListVO<LogMsgLogVO> resultList = messageService.listRecvMsgList(vo, vo.getCurPage());
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("messageList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "/home/lecture/message/msg_recv_list_div";
	}

	/**
	 * 쪽지 보기
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewMsg")
	public String viewMsgMain(LogMsgLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setRegNo(UserBroker.getUserNo(request));
		
		LogMsgTransLogVO logMsgTransLogVO = new LogMsgTransLogVO();
		logMsgTransLogVO.setMsgSn(vo.getMsgSn());
		logMsgTransLogVO.setMsgTransSn(vo.getMsgTransSn());
		
		LogMsgLogVO message = messageService.viewMessage(vo);
		
		messageService.readCheck(logMsgTransLogVO);
		
		request.setAttribute("vo", message);
		
		return "home/lecture/message/msg_view";
	}
	
	@RequestMapping(value = "/deleteMsg")
	@ResponseBody
	public AbstractResult deleteMsg(LogMsgTransLogVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		vo.setRecvAddr(UserBroker.getUserNo(request));
		try {
			messageService.deleteMsg(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 과정 중 오류가 발생했습니다.");
		}
		return result;
	}
}
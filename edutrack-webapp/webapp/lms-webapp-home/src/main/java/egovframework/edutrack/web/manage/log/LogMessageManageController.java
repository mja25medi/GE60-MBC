package egovframework.edutrack.web.manage.log;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgMngService;
import egovframework.edutrack.modules.log.message.service.LogMsgMngVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;

@Controller
@RequestMapping(value="/mng/log/message")
public class LogMessageManageController extends GenericController {

    /** service */
	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService logMsgLogService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService orgInfoService;
	
	@Autowired @Qualifier("logMsgMngService")
	private LogMsgMngService logMsgMngService;
	
	
	/**
     * @Method Name : main
     * @Method 설명 : 메시지 관리 메인
	 * @param LogMsgLogVO 
	 * @param commandMap
	 * @param model
	 * @return  "/log/message/message_main.jsp"
	 * @throws Exception
	 */	
	@RequestMapping(value="/messageMain")
	public String messageMain(LogMsgLogVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");

		return "mng/log/message/message_main";
	}
	
	/**
	 * 개설 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listMessage")
	public String listMessageMain(LogMsgMngVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("msgDivType", vo.getMsgDivType());		
		request.setAttribute("searchType", vo.getSearchType());

		ProcessResultListVO<LogMsgMngVO> resultList = logMsgMngService.listMessageMainPageing(vo, vo.getCurPage(), vo.getListScale());
		List<LogMsgMngVO> msgList = resultList.getReturnList();
		msgList.forEach(LogMsgMngVO::setTempTitleForNull);
		
		request.setAttribute("vo", vo);
		request.setAttribute("messageMainList", msgList);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/log/message/message_list_div";
	}
	
	/**
	 * 팀프로젝트 관리화면
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value = "/detailFormPop")
	public String editForm(LogMsgMngVO vo, Map commandMap, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String msgSn = vo.getMsgSn();

		LogMsgMngVO logMsgMngVO = new LogMsgMngVO();
		logMsgMngVO.setMsgSn(msgSn);
		ProcessResultVO<LogMsgMngVO> detailMsg = logMsgMngService.view(logMsgMngVO);
		request.setAttribute("detailMsg", detailMsg.getReturnVO());
		
		ProcessResultListVO<LogMsgMngVO> resultList = logMsgMngService.listMsgTrans(vo);
		request.setAttribute("transList", resultList.getReturnList());
		
		return "mng/log/message/message_info_detail_pop";
	}
	
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
		String stdNoList = request.getParameter("logMsgTransLogVO.stdNoList");
		
		String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
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
		model.addAttribute("stdNoList", stdNoList);
		model.addAttribute("vo", vo);
		
		String returnUrl="mng/log/message/write_email_pop";
		if("EMAIL".equals(vo.getMsgDivCd())) {
			model.addAttribute("fileupload", "Y");
			returnUrl = "mng/log/message/write_email_pop";
		} else if("SMS".equals(vo.getMsgDivCd())) {
			returnUrl = "mng/log/message/write_sms_pop";
		} else {
			model.addAttribute("uploadify", "Y");
			returnUrl = "mng/log/message/write_msg_pop";
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
	
	/**
     * [CRM] 메시지 리스트 조회
	 * @param LogMsgLogVO 
	 * @param HttpServletRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/crmMsgListPop")
	public String crmMsgList(LogMsgLogVO vo, HttpServletRequest request) {
		ProcessResultListVO<LogMsgLogVO> result = logMsgLogService.listPageingForCRM(vo);
		List<LogMsgLogVO> msgList = result.getReturnList();
		msgList.forEach(LogMsgLogVO::setTempTitleForNull);
		
		request.setAttribute("msgVO", vo);
		request.setAttribute("userMsgList", msgList);
		request.setAttribute("pageInfo", result.getPageInfo());
		
		return "/mng/user/info/user_crm_msg_list_div";
	}

}

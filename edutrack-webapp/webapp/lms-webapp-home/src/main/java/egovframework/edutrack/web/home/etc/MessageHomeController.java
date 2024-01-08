package egovframework.edutrack.web.home.etc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;



/**
 * 개설 과정 엑션 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/message")
public class MessageHomeController extends GenericController {

	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService 	logMsgLogService;
	

	/**
	 * 받은쪽지함 목록
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listRecvMain")
	public String listRecvMain(LogMsgLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "home/log/message/recv_list_main";
	}
	
	@RequestMapping(value="/listMsg")
	public String listMsg(LogMsgLogVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setListScale(5);
		ProcessResultListVO<LogMsgLogVO> resultList = logMsgLogService.listRecvMsgList(vo, vo.getCurPage());
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("messageList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "/home/log/message/recv_list_div";
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
		
		LogMsgLogVO message = logMsgLogService.viewMessage(vo);
		
		logMsgLogService.readCheck(logMsgTransLogVO);
		
		request.setAttribute("vo", message);
		
		return "home/log/message/msg_view";
	}
	
	@RequestMapping(value = "/deleteMsg")
	@ResponseBody
	public AbstractResult deleteMsg(LogMsgTransLogVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		vo.setRecvAddr(UserBroker.getUserNo(request));
		try {
			logMsgLogService.deleteMsg(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 과정 중 오류가 발생했습니다.");
		}
		return result;
	}
}
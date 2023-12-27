package egovframework.edutrack.web.manage.etc;

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
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.etc.event.service.EtcEventVO;
import egovframework.edutrack.modules.etc.event.service.EtcEventService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/etc/event")
public class EventManageController extends GenericController {

	@Autowired @Qualifier("etcEventService")
	private EtcEventService	etcEventService; // 인터페이스 선언부

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	/**
	 * 이벤트관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/etc/event/event_main";
	}

	/**
	 * 이벤트 목록 조회
	 * @param request
	 * @return Ajax Json
	 */

	@RequestMapping(value="/list")
	public String list(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<EtcEventVO> resultList = etcEventService.listPageing(vo, vo.getCurPage());

		request.setAttribute("eventList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/etc/event/event_list_div";
	}

	/**
	 * 이벤트 정보 입력 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPop")
	public String addPop(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
        List<SysCodeVO> eventTrgtList = sysCodeMemService.getSysCodeList("BNNR_TRGT", UserBroker.getLocaleKey(request));
		request.setAttribute("eventTrgtList", eventTrgtList);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/etc/event/event_write_pop";
	}


	/**
	 * 이벤트 정보 수정 폼
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/editPop")
	public String editPop(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
        List<SysCodeVO> eventTrgtList = sysCodeMemService.getSysCodeList("BNNR_TRGT", UserBroker.getLocaleKey(request));
		request.setAttribute("eventTrgtList", eventTrgtList);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = etcEventService.view(vo);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "mng/etc/event/event_write_pop";

	}

	/**
	 * 이벤트 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response`	`
	 * @return json
	 */
	@RequestMapping(value="/add")
	public String add(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<EtcEventVO> result = etcEventService.add(vo);
		if(result.getResult() > 0) {
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.event.add.success"));
		} else {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.event.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 이벤트 수정
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/edit")
	public String edit(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 저장
		ProcessResultVO<EtcEventVO> result =etcEventService.edit(vo); //서비스 딴으로 넘길 변수
		if(result.getResult() > 0) {
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.event.edit.success"));
		} else {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.event.edit.failed"));
		}
		return JsonUtil.responseJson(response, result); //
	}

	/**
	 * 이벤트 정보 순서 변경 폼
	 *
	 * @return
	 */
	@RequestMapping(value="/sortPop")
	public String sortPop(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<EtcEventVO> list = etcEventService.list(vo).getReturnList();
		request.setAttribute("eventList", list);
		return "mng/etc/event/event_sort_pop";
	}

	/**
	 * 이벤트 순서변경
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sort")
	public String sort(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 저장
		ProcessResultVO<EtcEventVO> result = new ProcessResultVO<EtcEventVO>();
		try {
			etcEventService.sort(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.event.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.event.sort.failed"));
		}

		return JsonUtil.responseJson(response, result);

	}
	/**
	 * 이벤트 삭제
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/delete")
	public String delete(EtcEventVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<EtcEventVO> result = new ProcessResultVO<EtcEventVO>();
		try {
			etcEventService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.event.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.event.delete.failed"));
		}


		return JsonUtil.responseJson(response, result); //
	}
}

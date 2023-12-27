package egovframework.edutrack.web.home.org;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshService;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrService;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultService;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankService;
import egovframework.edutrack.modules.course.research.service.ResearchBankVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshAnsrVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshItemVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshResultVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshService;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/resh")
public class OrgReshController extends GenericController {

	@Autowired @Qualifier("orgReshService")
	private OrgReshService	orgReshService;

	/**
	 * 학습자의 설문 참여 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);
		String joinYn = request.getParameter("joinYn");
		vo.setUseYn("Y");
		vo.setRegYn("Y"); //-- 등록 완료된 설문 목록 셋팅

		vo.setUserNo(userNo);
		vo.setReshTypeCd("G"); // 퇴교 설문 (D) 가 아닌 일반 설문만 조회 한다.
		vo.setJoinYn(joinYn); 
		vo.setSearchValue(vo.getSearchValue());
		vo.setCurPage(vo.getCurPage());
		request.setAttribute("vo", vo);
		
		return "home/org/resh/main";

	}
	/**
	 * 학습자의 설문 참여 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listResh")
	public String listResh(OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//현재 시간
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		request.setAttribute("nowTime", date);

		String userNo = UserBroker.getUserNo(request);
		String joinYn = request.getParameter("joinYn");
		vo.setUseYn("Y");
		vo.setRegYn("Y"); //-- 등록 완료된 설문 목록 셋팅

		vo.setUserNo(userNo);
		vo.setReshTypeCd("G"); // 퇴교 설문 (D) 가 아닌 일반 설문만 조회 한다.
		vo.setJoinYn(joinYn); 
		vo.setSearchValue(vo.getSearchValue());
		vo.setCurPage(vo.getCurPage());
		ProcessResultListVO<OrgReshVO> reshResultList = orgReshService.listMyReshPageing(vo);
		request.setAttribute("orgReshList", reshResultList.getReturnList());
		request.setAttribute("pageInfo", reshResultList.getPageInfo());
		
		return "home/org/resh/resh_list_div";

	}
	/**
	 * 설문 정보 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewReshPop")
	public String viewReshPop(OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);

		//-- 설문 정보 검색
		vo = orgReshService.viewResearch(vo).getReturnVO();

		request.setAttribute("vo", vo);


		return "home/org/resh/resh_info_pop";
	}
	
	/**
	 * 학습자의 설문 참여창
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/joinMain")
	public String joinMain(OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);

		//-- 설문 정보 검색
		vo = orgReshService.viewResearch(vo).getReturnVO();

		String end = vo.getEndDttm();
		Long endTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(end).getTime()/1000 ;
		request.setAttribute("endTime", endTime);
		
		ResearchBankVO reshBankVO = new ResearchBankVO();
		reshBankVO.setReshSn(vo.getReshSn());

		OrgReshAnsrVO orgReshAnsrVO = new OrgReshAnsrVO();
		orgReshAnsrVO.setReshSn(vo.getReshSn());
		orgReshAnsrVO.setUserNo(userNo);

		List<OrgReshItemVO> reshItemList = orgReshService.listItem(vo).getReturnList();
		request.setAttribute("reshItemList", reshItemList);

		request.setAttribute("vo", vo);
		request.setAttribute("orgReshAnsrVO", orgReshAnsrVO);
		request.setAttribute("textareaResize", "none");

		return "home/org/resh/join_main";
	}
	
	/**
	 * 개설 과정 설문 참여
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/join")
	public String join(OrgReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		
		ProcessResultVO<BrdBbsAtclVO> result = new ProcessResultVO<BrdBbsAtclVO>();
		try {
			orgReshService.addReshAnsr(vo);

			result.setResult(1);
			result.setMessage(getMessage(request, "lecture.message.research.join.success"));
		} catch (Exception e) {
			result.setResult(-1);
//			log.error("Exception occurred");
			result.setMessage(getMessage(request, "lecture.message.research.join.failed"));

		}
		return JsonUtil.responseJson(response, result);
		/*
		 * try { crsReshAnsrService.add(vo); setAlertMessage(request,
		 * getMessage(request, "lecture.message.research.join.success")); return
		 * "redirect:"+ new URLBuilder("/lec/creCrsResh/listReshStdMain") .toString(); }
		 * catch (Exception e) { log.error("Exception occurred");
		 * setAlertMessage(request, getMessage(request,
		 * "lecture.message.research.join.failed")); return "redirect:"+ new
		 * URLBuilder("/lec/creCrsResh/joinMain") .addParameter("crsCreCd",
		 * vo.getCrsCreCd()) .addParameter("reshSn", vo.getReshSn()) .toString(); }
		 */

	}
	@ResponseBody
	@RequestMapping("/ajaxAnsrList")
	public List<OrgReshAnsrVO> ajaxAnsrList(OrgReshAnsrVO orgReshAnsrVO) throws Exception {
		return orgReshService.getAnsrList(orgReshAnsrVO).getReturnList();
	}
	/**
	 * 개설 과정 설문 결과 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rsltMain")
	public String rsltMain(OrgReshResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);

		OrgReshVO orgReshVO = new OrgReshVO();
		orgReshVO.setReshSn(vo.getReshSn());
		//-- 설문 정보 검색
		orgReshVO = orgReshService.viewResearch(orgReshVO).getReturnVO();

		//설문 리스트,교육생의 설문 응시 답 포함.
		vo.setUserNo(userNo);
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		List<OrgReshResultVO> orgReshResultList = orgReshService.listReshAnsr(vo).getReturnList();
		request.setAttribute("vo", vo);
		request.setAttribute("orgReshVO", orgReshVO);
		request.setAttribute("orgReshResultList", orgReshResultList);


		return "home/org/resh/result";
	}
}

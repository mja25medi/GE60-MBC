package egovframework.edutrack.web.manage.course;

import java.util.ArrayList;
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
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.courseplan.service.CrsPlanService;
import egovframework.edutrack.modules.course.courseplan.service.CrsPlanVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/coursePlan")
public class CourseCoursePlanManageController extends GenericController{

	@Autowired @Qualifier("crsPlanService")
	private CrsPlanService			crsPlanService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService			sysCfgService;

	/**
	 * 개설 과정 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/courseMain")
	public String mainCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String curYear = DateTimeUtil.getYear();

		//-- 시작년도 가져오기
		SysCfgVO sysCfgVO = new SysCfgVO();
		sysCfgVO.setCfgCtgrCd("SYSTEM");
		sysCfgVO.setCfgCd("START_YEAR");
		sysCfgVO = sysCfgService.viewCfg(sysCfgVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(sysCfgVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("curYear", curYear);
		request.setAttribute("yearList", yearList);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");
		return "mng/course/crsplan/course_main";
	}

	/**
	 * 과정 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCourse")
	public String listCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		request.setAttribute("creYear", vo.getCreYear());
		//-- 사용중인 것만 검색
		vo.setUseYn("Y");
		vo.setOrgCd(orgCd);
		ProcessResultListVO<CourseVO> resultList = crsPlanService.listPageing(vo, vo.getCurPage(), vo.getListScale());
		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("courseVO", vo);
		
		return "mng/course/crsplan/course_list_div";
	}

	/**
	 * 년간 과정 계획 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@RequestMapping(value="/add")
	public String add( CrsPlanVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<CrsPlanVO> resultVO = crsPlanService.marge(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.courseplan.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.courseplan.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 년간 과정 계획 일괄 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addAll")
	public String addAll( CrsPlanVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		
		List<CrsPlanVO> listCrsPlan = vo.getAddCrsPlanList();

		ProcessResultVO<CrsPlanVO> resultVO = crsPlanService.margeAll(listCrsPlan, vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.courseplan.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.courseplan.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
}

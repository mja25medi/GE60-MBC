package egovframework.edutrack.web.manage.course;

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
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermService;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermVO;


@Controller
@RequestMapping(value="/mng/course/creterm")
public class CourseCreateCretermController extends GenericController{

	@Autowired @Qualifier("courseCretermService")
	private CourseCretermService			courseCretermService;



	/**
	 * 기수 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/cretermMain")
	public String mainCreateCourse( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/course/creterm/creterm_main";
	}

	/**
	 * 기수 관리 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreterm")
	public String listCourse( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseCretermVO> resultList = courseCretermService.listPageing(vo);
		
		request.setAttribute("cretermList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		
		return "mng/course/creterm/creterm_list_div";
	}

	/**
	 * 기수 관리 디테일 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cretermInfo")
	public String cretermInfo( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseCretermVO> resultList = courseCretermService.listPageing(vo);
		
		ProcessResultVO<CourseCretermVO> result = courseCretermService.view(vo);
		
		request.setAttribute("vo", result.getReturnVO());
		request.setAttribute("cretermCourseList", null);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "mng/course/creterm/creterm_info_div";
	}

	/**
	 * 기수 관리 등록 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addFormCreterm")
	public String addFormCreterm( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		
		return "mng/course/creterm/creterm_write";
	}
		
	/**
	 * 기수 관리 수정 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editFormCreterm")
	public String editFormCreterm( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		
		return "mng/course/creterm/creterm_write";
	}
	
	/**
	 * 기수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCrerterm")
	public String addCrerterm( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<CourseCretermVO> resultVO = courseCretermService.add(vo);
		//term_dept 테이블에도 add해야함.
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 기수 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCrerterm")
	public String editCrerterm( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<CourseCretermVO> resultVO = courseCretermService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 기수 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCrerterm")
	public String deleteCrerterm( CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<?> resultVO = courseCretermService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 등록 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addFormCreateCourse")
	public String addFormCreateCourse(CourseCretermVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		
		return "mng/course/creterm/course_write";
	}
}

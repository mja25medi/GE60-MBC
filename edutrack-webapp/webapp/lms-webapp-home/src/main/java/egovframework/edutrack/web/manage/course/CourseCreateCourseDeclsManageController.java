package egovframework.edutrack.web.manage.course;

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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/createCourse/decls")
public class CourseCreateCourseDeclsManageController extends GenericController{

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService		creCrsDeclsService;

	/**
	 * 개설 과정 분반 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/main")
	public String main( CreCrsDeclsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정 정보
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 과정정보
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		request.setAttribute("vo", vo);
		return "mng/course/decls/decls_main";
	}

	/**
	 * 개설 과정 분반 목록 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list( CreCrsDeclsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(vo).getReturnList();

		int declsCnt = creCrsDeclsService.getCount(vo);
		request.setAttribute("declsCnt", declsCnt);

		request.setAttribute("creCrsDeclsList", creCrsDeclsList);
		return "mng/course/decls/decls_list_div";
	}

	/**
	 * 개설 과정 분반 추가
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add( CreCrsDeclsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CreCrsDeclsVO> resultVO = creCrsDeclsService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.decls.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.decls.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 분반 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/remove")
	public String remove( CreCrsDeclsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = creCrsDeclsService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.decls.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.decls.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
}

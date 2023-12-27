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

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/createCertification")
public class CourseCertificationCreateCourseManageController extends GenericController{

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;



	/**
	 * 개설 과정 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/createCourseMain")
	public String mainCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO.setSortKey("CRS_CD_DESC");
		courseVO.setCrsOperType("E");
		courseVO.setOrgCd(orgCd);
		List<CourseVO> courseList = courseService.list(courseVO).getReturnList();
		request.setAttribute("courseList", courseList);

		return "mng/course/createcertification/create_course_main";
	}

	/**
	 * 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCourse")
	public String listCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseVO> resultList = courseService.listPageing(vo, vo.getCurPage(), vo.getListScale());
		
		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("courseVO", vo);
		
		return "mng/course/createcertification/course_list_div";
	}

	/**
	 * 개설 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreateCourse")
	public String listCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		vo.setCreOperTypeCd("E");
		List<CreateCourseVO> createCourseList = createCourseService.listCreateCourse(vo).getReturnList();
		request.setAttribute("vo", vo);
		request.setAttribute("createCourseList", createCourseList);
		return "mng/course/createcertification/create_course_list_div";
	}

	/**
	 * 과정 목록 조회(Json)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCourseJson")
	public String listCourseJson( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, courseService.list(vo));
		
	}

	/**
	 * 개설 과정 목록 조회(Json)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreateCourseJson")
	public String listCreateCourseJson( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
			.responseJson(response, createCourseService.listCreateCourse(vo));
	}

	/**
	 * 개설 과정 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCreateCoursePop")
	public String addFormCreateCoursePop( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//-- 과정의 정보르 검색 하여 과정명 셋팅
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		ProcessResultVO<CourseVO> ResultVO = courseService.view(courseVO);
		courseVO = ResultVO.getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("courseVO", courseVO);

		request.setAttribute("gubun", "A");
		return "mng/course/createcertification/create_course_write";
	}

	/**
	 * 개설 과정 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCreateCourse")
	public String addCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCreOperTypeCd("E");
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		try {
			resultVO = createCourseService.addCertificateCreateCourse(vo);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.success"));
		}catch (Exception e){
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
/*	@RequestMapping(value="/editFormCreateCourse")
	public String editFormCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCtgrCd = vo.getCrsCtgrCd();
		String parcrsCtgrCd = vo.getParCtgrCd();

		//-- 개설 과정 정보를 가져온다.
		ProcessResultVO<CreateCourseVO> resultVO = createCourseService.viewCreateCourse(vo);
		vo = resultVO.getReturnVO();
		vo.setCrsCtgrCd(crsCtgrCd);
		vo.setParCtgrCd(parcrsCtgrCd);
		request.setAttribute("vo", vo);
		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
		
		request.setAttribute("nopLimitList", nopLimitList);

		request.setAttribute("gubun", "E");
		return "mng/course/createcourse/create_course_edit";
	}*/

	/**
	 * 개설 과정 수정 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCreateCoursePop")
	public String editCreateCoursePop(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCreOperTypeCd("E");
		//-- 개설 과정 정보를 가져온다.
		ProcessResultVO<CreateCourseVO> resultVO = createCourseService.viewCreateCourse(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		request.setAttribute("gubun", "E");
		return "mng/course/createcertification/create_course_write";
	}

	/**
	 * 개설 과정 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCreateCourse")
	public String editCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		try {
			resultVO = createCourseService.editCreateCourse(vo);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.edit.success"));
		}catch(Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCreateCourse")
	public String deleteCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCreOperTypeCd("E");
		ProcessResultVO<?> resultVO = new ProcessResultVO<Object>();
		try {
			resultVO = createCourseService.deleteCertCreateCourse(vo);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.delete.success"));
		}catch(Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정별 기수 목록 조회(Json)
	 */
	@RequestMapping(value="/listCourseTermJson")
	public String listCourseTermJson( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, createCourseService.listCourseTerm(vo));
	}

}

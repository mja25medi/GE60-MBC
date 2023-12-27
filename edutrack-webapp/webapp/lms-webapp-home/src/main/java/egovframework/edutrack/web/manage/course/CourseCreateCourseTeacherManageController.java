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
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/createCourse/teacher")
public class CourseCreateCourseTeacherManageController extends GenericController {

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	createCourseTeacherService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;

	/**
	 * 개설 과정 강사 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/teacherMain")
	public String teacherMain( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		//-- 과정의 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		request.setAttribute("vo", vo);
		
		return "mng/course/createcourseteacher/teacher_main";
	}

	/**
	 * 개설 과정 강사 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listTeacher")
	public String listTeacher( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TCHMGR");
		
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(vo).getReturnList();
		request.setAttribute("teacherListVO", teacherList);
		return "mng/course/createcourseteacher/teacher_list_div";
		//return JsonUtil
		//	.responseJson(response, createCourseTeacherService.listTeacher(teacherVO));
	}
	
	/**
	 * 개설 과정 튜터 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listTutor")
	public String listTutor( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("ASSTCHMGR");

		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(vo).getReturnList();
		request.setAttribute("teacherListVO", teacherList);
		return "mng/course/createcourseteacher/tutor_list_div";
		//return JsonUtil
		//	.responseJson(response, createCourseTeacherService.listTeacher(teacherVO));
	}

	/**
	 * 회차 담임/부담임 검색 목록
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSearch")
	public String listSearch( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<UsrUserInfoVO> userList = createCourseTeacherService.listSearch(vo).getReturnList();
		request.setAttribute("userList", userList);
		return"mng/course/createcourseteacher/search_list_div";
		//return JsonUtil
		//	.responseJson(response, createCourseTeacherService.listTeacher(teacherVO));
	}

	/**
	 * 회차 담임 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormTeacherPop")
	public String addFormTeacherPop( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/course/createcourseteacher/teacher_write_pop";
	}

	/**
	 * 회차 담임 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTeacher")
	public String addTeacher( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(vo).getReturnList();
		for(int i=0; i<teacherList.size(); i++) {
			if(teacherList.get(i).getTchType().equals("TCHMGR") && teacherList.get(i).getUserId() != null) {
				resultVO.setMessage(getMessage(request, "course.message.teacher.add.failed"));
				resultVO.setResultFailed();
				return JsonUtil.responseJson(response, resultVO);
			} else if(teacherList.get(i).getTchType().equals("ASSTCHMGR") && teacherList.get(i).getUserId() != null) {
				resultVO.setMessage(getMessage(request, "course.message.teacher.tutor.failed"));
				resultVO.setResultFailed();
				return JsonUtil.responseJson(response, resultVO);
			} 
		}
		resultVO = createCourseTeacherService.addTeacher(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.add.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 강사 평가 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormTeacherScore")
	public String addFormTeacherScore( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = createCourseTeacherService.viewTeacher(vo).getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/course/createcourseteacher/teacher_score_write_pop";
	}


	/**
	 * 개설 과정 강사 평가
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTeacherScore")
	public String addTeacherScore( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<TeacherVO> resultVO = createCourseTeacherService.editTeacher(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.eval.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.eval.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 개설 과정 강사 순서 변경 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sortFormTeacher")
	public String sortFormTeacher( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("teacherList", createCourseTeacherService.listTeacher(vo).getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/createcourseteacher/teacher_sort";
	}
	
	/**
	 * 과정 강사 순서 변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortTeacher")
	public String sortTeacher( TeacherVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<?> resultVO = createCourseTeacherService.sortTeacher(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.sort.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}	

	/**
	 * 개설 과정 강사 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteTeacher")
	public String deleteTeacher( TeacherVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = createCourseTeacherService.deleteTeacher(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.delete.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}
}

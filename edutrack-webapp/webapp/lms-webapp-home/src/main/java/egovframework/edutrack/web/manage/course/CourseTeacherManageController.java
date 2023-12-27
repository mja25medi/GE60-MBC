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
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.crstch.service.CrsTchService;
import egovframework.edutrack.modules.course.crstch.service.CrsTchVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/teacher")
public class CourseTeacherManageController extends GenericController {

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;
	
	@Autowired @Qualifier("crsTchService")
	private CrsTchService			crsTchService;
	
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService		usrUserInfoService;
	
	/**
	 * 과정 강사/튜터 메인 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		return "mng/course/crstch/teacher_main";
	}	
	
	/**
	 * 과정 강사 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listTeacher")
	public String listTeacher( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TEACHER");
		ProcessResultListVO<CrsTchVO> resultList = crsTchService.list(vo);
		request.setAttribute("crsTchList", resultList.getReturnList());
		return "mng/course/crstch/teacher_list_div";
	}
	
	/**
	 * 과정 튜터 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listTutor")
	public String listTutor( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TUTOR");
		ProcessResultListVO<CrsTchVO> resultList = crsTchService.list(vo);
		request.setAttribute("crsTchList", resultList.getReturnList());
		return "mng/course/crstch/tutor_list_div";
	}
	
	/**
	 * 강사 검색 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchTeacherPop")
	public String searchTeacher( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		return "mng/course/crstch/teacher_search_pop";
	}
	
	/**
	 * 강사 권한의 사용자 중 과정에 등록되지 않은 사용자 목록을 반환한다.
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSearchTeacher")
	public String listSearchTeacher( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);		
		vo.setTchType("TEACHER");
		
		ProcessResultListVO<UsrUserInfoVO> resultList = crsTchService.listSearch(vo);
		request.setAttribute("userInfoList", resultList.getReturnList());
		return "mng/course/crstch/teacher_search_list_div";
	}
	
	/**
	 * 강사 권한의 사용자의 정보를 반환한다.
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewSearchTeacher")
	public String viewSearchTeacher( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		UsrUserInfoVO uiVO = new UsrUserInfoVO();
		uiVO.setUserNo(vo.getUserNo());
		
		request.setAttribute("userInfoVO", usrUserInfoService.view(uiVO));
		return "mng/course/crstch/teacher_search_view_div";
	}
	
	/**
	 * 과정 강사 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTeacher")
	public String addTeacher( CrsTchVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TEACHER");
		
		ProcessResultVO<CrsTchVO> resultVO = crsTchService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 과정 강사 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/delTeacher")
	public String delTeacher( CrsTchVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<?> resultVO = crsTchService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.delete.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 과정 강사 순서 변경 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sortFormTeacher")
	public String sortFormTeacher( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);	
		vo.setTchType("TEACHER");
		
		request.setAttribute("teacherList", crsTchService.list(vo).getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/crstch/teacher_sort";
	}
	
	/**
	 * 과정 강사 순서 변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortTeacher")
	public String sortTeacher( CrsTchVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TEACHER");
		
		ProcessResultVO<?> resultVO = crsTchService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.teacher.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.teacher.sort.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 튜터 검색 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchTutorPop")
	public String searchTutor( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		
		request.setAttribute("crsTchVO", vo);
		return "mng/course/crstch/tutor_search_pop";
	}
	
	/**
	 * 튜터 권한의 사용자 중 과정에 등록되지 않은 사용자 목록을 반환한다.
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSearchTutor")
	public String listSearchTutor( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);	
		vo.setTchType("TUTOR");
		
		ProcessResultListVO<UsrUserInfoVO> resultList = crsTchService.listSearch(vo);
		request.setAttribute("userInfoList", resultList.getReturnList());
		return "mng/course/crstch/tutor_search_list_div";
	}
	
	/**
	 * 튜터 권한의 사용자의 정보를 반환한다.
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewSearchTutor")
	public String viewSearchTutor( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		UsrUserInfoVO uiVO = new UsrUserInfoVO();
		uiVO.setUserNo(vo.getUserNo());
		
		request.setAttribute("userInfoVO", usrUserInfoService.view(uiVO));
		return "mng/course/crstch/tutor_search_view_div";
	}
	
	/**
	 * 과정 튜터 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTutor")
	public String addTutor( CrsTchVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TUTOR");
		
		ProcessResultVO<CrsTchVO> resultVO = crsTchService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.tutor.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.tutor.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 과정 튜터 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/delTutor")
	public String delTutor( CrsTchVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<?> resultVO = crsTchService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.tutor.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.tutor.delete.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 과정 튜터 순서 변경 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sortFormTutor")
	public String sortFormTutor( CrsTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);	
		vo.setTchType("TUTOR");
		
		request.setAttribute("tutorList", crsTchService.list(vo).getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/crstch/tutor_sort";
	}
	
	/**
	 * 과정 튜터 순서 변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortTutor")
	public String sortTutor( CrsTchVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setTchType("TUTOR");
		
		ProcessResultVO<?> resultVO = crsTchService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.tutor.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.tutor.sort.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
}

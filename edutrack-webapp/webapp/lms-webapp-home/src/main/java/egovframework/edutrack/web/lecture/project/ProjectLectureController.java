package egovframework.edutrack.web.lecture.project;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentService;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentVO;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsService;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsVO;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectService;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;



@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/project")
public class ProjectLectureController extends GenericController {

	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService sysCodeMemService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService createCourseService;

	@Autowired @Qualifier("courseService")
	private CourseService courseService;

	@Autowired @Qualifier("prjTeamService")
	private PrjTeamService prjTeamService;

	@Autowired @Qualifier("prjBbsService")
	private PrjBbsService prjBbsService;

	@Autowired @Qualifier("prjAssignmentService")
	private PrjAssignmentService prjAssignmentService;

	/**
	 * 팀프로젝트 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainProject")
	public String mainProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		request.setAttribute("projectListVO", projectService.list(vo).getReturnList());
		request.setAttribute("projectVO", vo);
		return "home/lecture/project/info/project_main_teacher";
	}

	/**
	 * 팀프로젝트 목록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listProject")
	public String listProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		vo.setUserNo(UserBroker.getUserNo(request));

		request.setAttribute("projectVO", vo);
		request.setAttribute("projectListVO", projectService.listPrjStudent(vo).getReturnList());
		return "home/lecture/project/info/project_main_student";
	}

	/**
	 * 팀프로젝트 정보 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/readProject")
	public String readProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		vo.setUserNo(UserBroker.getUserNo(request));

		//왼쪽 세로바 새로고침 여부
		String bun = StringUtil.nvl(request.getParameter("bun"),"0");
		request.setAttribute("bun", bun);

		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//-- 팀목록을 가져온다.
		PrjTeamVO prjTeamVO = new PrjTeamVO();
		prjTeamVO.setCrsCreCd(vo.getCrsCreCd());
		prjTeamVO.setPrjtSn(vo.getPrjtSn());
		request.setAttribute("teamList", prjTeamService.list(prjTeamVO).getReturnList());
		//-- 팀프로젝트 게시판 목록을 가져온다.
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		request.setAttribute("bbsList", prjBbsService.listBbs(prjBbsVO).getReturnList());
		//-- 팀프로젝트 과제 정보를 가져온다.
		PrjAssignmentVO prjAssignmentVO = new PrjAssignmentVO();
		prjAssignmentVO.setCrsCreCd(vo.getCrsCreCd());
		prjAssignmentVO.setPrjtSn(vo.getPrjtSn());
		try{
			prjAssignmentVO = prjAssignmentService.view(prjAssignmentVO).getReturnVO();
		}catch(EmptyResultDataAccessException e){
		}

		try{
			request.setAttribute("vo", projectService.viewPrjStudent(vo).getReturnVO());
		}catch(EmptyResultDataAccessException e){

		}
		request.setAttribute("prjAssignmentVO", prjAssignmentVO);
		
		return "home/lecture/project/info/project_read_pop_student";
	}

	/**
	 * 팀프로젝트 정보 수정 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editFormPrjStudent")
	public String editFormPrjStudent( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		request.setAttribute("vo", projectService.view(vo).getReturnVO());
		return "home/lecture/project/info/project_edit_pop_student";
	}

	/**
	 * 팀프로젝트 정보 등록 폼 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addFormPrject")
	public String addFormPrject(ProjectVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

			//OPEN_YN 시스템 코드 목록 조회
			List<SysCodeVO> scoreOpenYn = sysCodeMemService.getSysCodeList("OPEN_YN");
			request.setAttribute("scoreOpenYn", scoreOpenYn);

			//등록 구분 코드
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			return "home/lecture/project/info/project_write_teacher";
	}

	/**
	 * 팀프로젝트 정보 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addProject")
	public String addProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
			
		try {
			projectService.add(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "프로젝트를 등록하지 못했습니다.");
			return this.editFormProject( vo, commandMap, model, request, response);
		}

		setAlertMessage(request, "프로젝트를 등록 하였습니다.");

		return "redirect:"+(
				new URLBuilder("/lec/project/mainProject")
					.addParameter("projectVO.prjtSn", vo.getPrjtSn())
					.toString()
		);
	}

	/**
	 * 팀프로젝트 관리화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/editFormProject")
	public String editFormProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		request.setAttribute("vo", projectService.view(vo).getReturnVO());
		request.setAttribute("tab", request.getParameter("tab"));
		//OPEN_YN 시스템 코드 목록 조회
		List<SysCodeVO> scoreOpenYn = sysCodeMemService.getSysCodeList("OPEN_YN");
		request.setAttribute("scoreOpenYn", scoreOpenYn);

		return "home/lecture/project/info/project_edit_team_teacher";
	}

	/**
	 * 팀프로젝트 정보 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editProject")
	public String editProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
	
		try {
			projectService.edit(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "프로젝트를 수정하지 못했습니다.");
			return this.editFormProject(vo, commandMap, model, request, response);
		}

		setAlertMessage(request, "프로젝트를 수정 하였습니다.");

		return "redirect:"+(
				new URLBuilder("/lec/project/editFormProject")
					.addParameter("projectVO.prjtSn", vo.getPrjtSn())
					.toString()
		);
	}

	/**
	 * 팀프로젝트 정보 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/removeProject")
	public String removeProject( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		
		try {
			projectService.remove(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "프로젝트를 삭제하지 못했습니다.");
			return this.editFormProject(vo, commandMap, model, request, response);
		}
		setAlertMessage(request, "프로젝트를 삭제 하였습니다.");

		return "redirect:"+(
				new URLBuilder("/lec/project/mainProject")
					.toString()
		);
		}

}

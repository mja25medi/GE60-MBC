package egovframework.edutrack.web.manage.lecture;

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
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectService;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/project")
public class LectureProjectManageController extends GenericController {

	@Autowired 
	@Qualifier("projectService")
	private ProjectService projectService;
	
	@Autowired
	@Qualifier("sysCodeService")
	private SysCodeService sysCodeService;
		
	/**
	 * 팀프로젝트 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/main")
	public String main( ProjectVO vo,Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		return "mng/lecture/project/info/project_info_main";
	}

	/**
	 * 팀프로젝트 목록 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			
			ProcessResultListVO<ProjectVO> resultList = projectService.list(vo);
			request.setAttribute("projectListVO", resultList.getReturnList());
			request.setAttribute("pageInfo", resultList.getPageInfo());
			return "mng/lecture/project/info/project_info_list_div";
	}
	
	/**
	 * 팀프로젝트 정보 등록 폼 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addForm")
	public String addForm(ProjectVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		//OPEN_YN 시스템 코드 목록 조회
		List<SysCodeVO> scoreOpenYn = sysCodeService.listCode("OPEN_YN").getReturnList();
		request.setAttribute("scoreOpenYn", scoreOpenYn);
			
		//등록 구분 코드
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/lecture/project/info/project_info_write_pop";
	}
	
	/**
	 * 팀프로젝트 정보 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add")
	public String add( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ProjectVO> resultVO = projectService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 팀프로젝트 관리화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForm")
	public String editForm( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
			
		request.setAttribute("projectVO", projectService.view(vo).getReturnVO());
		//OPEN_YN 시스템 코드 목록 조회
		List<SysCodeVO> scoreOpenYn = sysCodeService.listCode("OPEN_YN").getReturnList();
		request.setAttribute("scoreOpenYn", scoreOpenYn);
			
		request.setAttribute("gubun", "E");
		return "mng/lecture/project/info/project_info_write_pop";
	}
	
	/**
	 * 팀프로젝트 정보 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ProjectVO> resultVO = projectService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 팀프로젝트 정보 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/remove")
	public String remove( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<?> resultVO = projectService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 팀프로젝트 관리화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/manageForm")
	public String manageForm( ProjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
			
		request.setAttribute("vo", projectService.view(vo).getReturnVO());
		//OPEN_YN 시스템 코드 목록 조회
		List<SysCodeVO> scoreOpenYn = sysCodeService.listCode("OPEN_YN").getReturnList();
		request.setAttribute("scoreOpenYn", scoreOpenYn);
			
		return "mng/lecture/project/info/project_manage";
	}
}

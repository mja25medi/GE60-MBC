package egovframework.edutrack.web.manage.lecture;

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
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/prjTeam")
public class LecturePrjTeamManageController extends GenericController {

	@Autowired 
	@Qualifier("prjTeamService")
	private PrjTeamService prjTeamService;
	
	@Autowired 
	@Qualifier("prjMemberService") 
	private PrjMemberService prjMemberService;
	
	/**
	 * 팀관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/main")
	public String main( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			
		
		return "mng/lecture/project/member/member_main";
	}

	/**
	 * 팀관리 목록 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<PrjTeamVO> resultList = prjTeamService.list(vo);
		request.setAttribute("prjTeamListVO", resultList.getReturnList());
		return "mng/lecture/project/member/member_list_div";
		//return JsonUtil.responseJson(response, prjTeamService.list(prjTeamVO));
	}
	
	/**
	 * 신규팀 등록 폼 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addForm")
	public String addForm( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		//등록 구분 코드
		request.setAttribute("gubun", "A");
		
		return "mng/lecture/project/member/member_write_pop";
	}
	
	/**
	 * 신규팀 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/add")
	public String add( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PrjTeamVO> resultVO = prjTeamService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 팀 수정 품
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForm")
	public String editForm( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//팀 정보 조회
		request.setAttribute("prjTeamVO", prjTeamService.view(vo).getReturnVO());
		//수정 구분 코드
		request.setAttribute("gubun", "E");
		return "mng/lecture/project/member/member_write_pop";
	}
	
	/**
	 * 팀 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/edit")
	public String edit( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PrjTeamVO> resultVO = prjTeamService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 팀 자동생성 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/createForm")
	public String createForm( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
			
		PrjMemberVO prjMemberVO = new PrjMemberVO();
		prjMemberVO.setCrsCreCd(vo.getCrsCreCd());
		request.setAttribute("prjMemberVO", prjMemberService.selectLearnerCnt(prjMemberVO).getReturnVO());
		request.setAttribute("prjTeamVO", vo);
		return "mng/lecture/project/member/member_create_pop";
	}
	
	/**
	 * 팀 자동생성
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addTeamCreate")
	public String addTeamCreate( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PrjTeamVO> resultVO = prjTeamService.addTeamCreate(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 이전 프로젝트 복사
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addProjectTeamCopy")
	public String addProjectTeamCopy( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PrjTeamVO> resultVO = prjTeamService.addProjectTeamCopy(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.copy.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.copy.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 팀 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/remove")
	public String remove( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PrjTeamVO> resultVO = prjTeamService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.project.team.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
}

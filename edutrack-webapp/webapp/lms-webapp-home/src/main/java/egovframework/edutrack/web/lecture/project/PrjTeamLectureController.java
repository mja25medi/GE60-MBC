package egovframework.edutrack.web.lecture.project;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectService;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;



@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/prj/team")
public class PrjTeamLectureController extends GenericController {

	@Autowired 
	@Qualifier("prjTeamService")
	private PrjTeamService prjTeamService;
	
	@Autowired 
	@Qualifier("prjMemberService")
	private PrjMemberService prjMemberService;
	
	@Autowired 
	@Qualifier("projectService")
	private ProjectService projectService;
	
	/**
	 * 팀관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainPrjTeam")
	public String mainPrjTeam( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);
		request.setAttribute("prjTeamListVO", prjTeamService.list(vo).getReturnList());
		request.setAttribute("vo", vo);
		return "home/lecture/project/member/team_list_teacher";
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
	@RequestMapping(value="/listPrjTeam")
	public String listPrjTeam( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
		commonVOProcessing(vo, request);
		
		return JsonUtil.responseJson(response, prjTeamService.list(vo));
	}
	
	/**
	 * 신규팀 등록 폼 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormPrjTeam")
	public String addFormPrjTeam( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		request.setAttribute("refreshYn", refreshYn);
		
		//등록 구분 코드
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "home/lecture/project/member/team_write_pop_teacher";
	}
	
	/**
	 * 신규팀 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjTeam")
	public String addPrjTeam( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		try {
			prjTeamService.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "신규 팀 등록을 하지 못했습니다.");
			return "redirect:"+(
					new URLBuilder("/lec/prj/team/addFormPrjTeam")
						.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
						.toString()
			);
		}
		
		setAlertMessage(request, "신규 팀 등록을 하였 습니다.");
		// 읽기 화면으로 
		return "redirect:"+(
				new URLBuilder("/lec/prj/team/addFormPrjTeam")
				.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
				.addParameter("refreshYn", "Y")
				.toString()
		);
	}
	
	/**
	 * 팀 수정 품
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/editFormPrjTeam")
	public String editFormPrjTeam( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		request.setAttribute("refreshYn", refreshYn);

		//팀 정보 조회
		request.setAttribute("prjTeamVO", prjTeamService.view(vo).getReturnVO());
		//수정 구분 코드
		request.setAttribute("gubun", "E");
		return "home/lecture/project/member/team_write_pop_teacher";
	}
	
	/**
	 * 팀 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editPrjTeam")
	public String editPrjTeam( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		commonVOProcessing(vo, request);
		
		try {
			prjTeamService.edit(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "팀을 수정 하지 못했습니다.");
			return "redirect:"+(
					new URLBuilder("/lec/prj/team/editFormPrjTeam")
						.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjTeamVO.prjtTeamSn", vo.getPrjtTeamSn())
						.toString()
			);
		}
		
		setAlertMessage(request, "팀을 수정 하였습니다.");
		// 읽기 화면으로 
		return "redirect:"+(
				new URLBuilder("/lec/prj/team/editFormPrjTeam")
				.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
				.addParameter("prjTeamVO.prjtTeamSn", vo.getPrjtTeamSn())
				.addParameter("refreshYn", "Y")
				.toString()
		);
	}
	
	/**
	 * 팀 자동생성 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/addFormTeamCreate")
	public String addFormTeamCreate( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		request.setAttribute("refreshYn", refreshYn);
		
		PrjMemberVO prjMemberVO = new PrjMemberVO();
		prjMemberVO.setCrsCreCd(vo.getCrsCreCd());
		request.setAttribute("prjMemberVO", prjMemberService.selectLearnerCnt(prjMemberVO).getReturnVO());
		
		ProjectVO projectVO = new ProjectVO();
		projectVO.setCrsCreCd(vo.getCrsCreCd());
		request.setAttribute("projectListVO", projectService.list(projectVO).getReturnList());
		request.setAttribute("vo", vo);
		return "home/lecture/project/member/team_create_pop_teacher";
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
			HttpServletRequest request, HttpServletResponse response){
		commonVOProcessing(vo, request);
	
		try {
			prjTeamService.addTeamCreate(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "팀 자동생성을 하지 못했습니다.");
			return "redirect:"+(
					new URLBuilder("/lec/prj/team/addFormTeamCreate")
						.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
						.toString()
			);
		}
		
		setAlertMessage(request, "팀 자동생성을 하였습니다.");
		// 읽기 화면으로 
		return "redirect:"+(
				new URLBuilder("/lec/prj/team/addFormTeamCreate")
				.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
				.addParameter("refreshYn", "Y")
				.toString()
		);
	}
	
	/**
	 * 이전 프로젝트 복사
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addTeamCopy")
	public String addTeamCopy( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		commonVOProcessing(vo, request);
	
		try {
			prjTeamService.addProjectTeamCopy(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "이전 프로젝트 복사를 하지 못했습니다.");
			return "redirect:"+(
					new URLBuilder("/lec/prj/team/addFormTeamCreate")
						.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
						.toString()
			);
		}
		
		setAlertMessage(request, "이전 프로젝트 복사를 하였습니다.");
		// 읽기 화면으로 
		return "redirect:"+(
				new URLBuilder("/lec/prj/team/addFormTeamCreate")
				.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
				.addParameter("refreshYn", "Y")
				.toString()
		);
	}
	
	/**
	 * 팀 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/remove")
	public String remove( PrjTeamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
	
		try {
			prjTeamService.remove(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "팀을 삭제 하지 못했습니다.");
			return this.mainPrjTeam(vo, commandMap, model, request, response);
		}
		
		setAlertMessage(request, "팀을 삭제 하였습니다.");
		// 읽기 화면으로 
		return "redirect:"+(
				new URLBuilder("/lec/prj/team/mainPrjTeam")
				.addParameter("prjTeamVO.prjtSn", vo.getPrjtSn())
				.toString()
		);
			
	}
}

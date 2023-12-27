package egovframework.edutrack.web.lecture.project;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
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
@RequestMapping(value="/lec/prj/member")
public class PrjMemberLectureController extends GenericController {

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
	 * 팀원 목록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainPrjMember")
	public String mainPrjMember( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
	
			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			
			// 부모창 새로고침 여부
			String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
			request.setAttribute("refreshYn", refreshYn);
			
			PrjTeamVO prjTeamVO = new PrjTeamVO();
			prjTeamVO.setCrsCreCd(vo.getCrsCreCd());
			prjTeamVO.setPrjtSn(vo.getPrjtSn());
			prjTeamVO.setPrjtTeamSn(vo.getPrjtTeamSn());
			
			request.setAttribute("teamList", prjTeamService.list(prjTeamVO).getReturnList());
			request.setAttribute("prjTeamVO", prjTeamService.view(prjTeamVO).getReturnVO());

			request.setAttribute("stdList", prjMemberService.stdList(vo).getReturnList());
			request.setAttribute("mbrList", prjMemberService.list(vo).getReturnList());
			request.setAttribute("vo", vo);
			return "home/lecture/project/member/member_edit_pop_teacher";
	}
	
	/**
	 * 팀원 목록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listPrjMember")
	public String listPrjMember( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			request.setAttribute("mbrList", prjMemberService.list(vo).getReturnList());
			
			PrjTeamVO prjTeamVO = new PrjTeamVO();
			prjTeamVO.setCrsCreCd(vo.getCrsCreCd());
			prjTeamVO.setPrjtSn(vo.getPrjtSn());
			prjTeamVO.setPrjtTeamSn(vo.getPrjtTeamSn());
			request.setAttribute("prjTeamVO", prjTeamService.view(prjTeamVO).getReturnVO());
			request.setAttribute("vo", vo);
			
			ProjectVO projectVO = new ProjectVO();
			projectVO.setCrsCreCd(vo.getCrsCreCd());
			projectVO.setPrjtSn(vo.getPrjtSn());
			projectVO.setUserNo(UserBroker.getUserNo(request));
			projectVO.setPrjtTeamSn(vo.getPrjtTeamSn());
			try{
				request.setAttribute("projectVO", projectService.viewPrjStudent(projectVO).getReturnVO());
				
			} catch (EmptyResultDataAccessException e) {
				
			}

			return "home/lecture/project/member/member_edit_pop_student";
	}
	
	/**
	 * 학습자 목록 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/stdList")
	public String stdList( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			return JsonUtil.responseJson(response, prjMemberService.stdList(vo));
	}
	
	/**
	 * 팀원 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjMember")
	public String addPrjMember( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			try {
				prjMemberService.add(vo);
			} catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "팀원을 등록 하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/member/mainPrjMember")
							.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
							.toString()
				);
			}
			
			setAlertMessage(request, "팀원을 등록 하였습니다.");
			// 읽기 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/member/mainPrjMember")
					.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
					.addParameter("refreshYn", "Y")
					.toString()
			);
	}
	
	/**
	 * 팀장 선정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjMember")
	public String editPrjMember( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
			
			try {
				prjMemberService.edit(vo);
			} catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "팀장 선정을 하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/member/mainPrjMember")
							.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
							.toString()
				);
			}
			
			setAlertMessage(request, "팀장 선정을 하였습니다.");
			// 읽기 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/member/mainPrjMember")
					.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
					.toString()
			);
	}
	
	/**
	 * 역할명세서 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjMemberStu")
	public String editPrjMemberStu( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
			
			try {
				prjMemberService.editPrjMbr(vo);
			} catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "역할정의를 수정 하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/member/listPrjMember")
							.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
							.toString()
				);
			}
			
			setAlertMessage(request, "역할정의를 수정 하였습니다.");
			// 읽기 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/member/listPrjMember")
					.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
					.toString()
			);
	}
	
	/**
	 * 팀원 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removePrjMember")
	public String removePrjMember( PrjMemberVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			try {
				prjMemberService.remove(vo);
			} catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "팀원을 삭제 하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/member/mainPrjMember")
							.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
							.toString()
				);
			}
			
			setAlertMessage(request, "팀원을 삭제 하였습니다.");
			// 읽기 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/member/mainPrjMember")
					.addParameter("prjMemberVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjMemberVO.prjtTeamSn", vo.getPrjtTeamSn())
					.toString()
			);
	}
	
	
}

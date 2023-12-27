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

import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentService;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamService;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/prj/assignment")
public class PrjAssignmentLectureController extends GenericController {

	@Autowired 
	@Qualifier("prjAssignmentService")
	private PrjAssignmentService prjAssignmentService;
	
	@Autowired @Qualifier("prjTeamService")
	private PrjTeamService prjTeamService;
	
	/**
	 * 팀 프로젝트 과제 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjAssignment")
	public String addPrjAssignment(PrjAssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()));
			try {
				prjAssignmentService.add(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "과제를 저장하지 못하였습니다.");
				return this.editFormPrjAssignment(vo, commandMap, model, request, response);
			}
			
			setAlertMessage(request, "과제를 저장 하였습니다.");
			
			return "redirect:"+ new URLBuilder("/lec/prj/assignment/editFormPrjAssignment")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("prjtSn", vo.getPrjtSn())
					.addParameter("asmtSn", vo.getAsmtSn())
					.toString();
	}
	
	/**
	 * 팀프로젝트 과제관리 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	@RequestMapping(value="/editFormPrjAssignment")
	public String editFormPrjAssignment(PrjAssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
			
			String crsCreCd = UserBroker.getCreCrsCd(request);
			vo.setCrsCreCd(crsCreCd);
			try{
				request.setAttribute("vo", prjAssignmentService.view(vo).getReturnVO());
				
			}catch(EmptyResultDataAccessException e){
				e.printStackTrace();
				return "home/lecture/project/assignment/assignment_write_teacher";
			}
			
			return "home/lecture/project/assignment/assignment_write_teacher";
	}
	
	/**
	 * 팀 프로젝트 과제 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjAssignment")
	public String editPrjAssignment(PrjAssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

			try {
				prjAssignmentService.edit(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "과제를 수정하지 못하였습니다.");
				return this.editFormPrjAssignment(vo, commandMap, model, request, response);
			}
			
			setAlertMessage(request, "과제를 수정 하였습니다.");
			
			return "redirect:"+ new URLBuilder("/lec/prj/assignment/editFormPrjAssignment")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("prjtSn", vo.getPrjtSn())
					.addParameter("asmtSn", vo.getAsmtSn())
					.toString();
	}
	
	/**
	 * 팀 프로젝트 과제 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removePrjAssignment")
	public String removePrjAssignment(PrjAssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
			
			try {
				prjAssignmentService.remove(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "과제를 삭제하지 못하였습니다.");
				return this.editFormPrjAssignment(vo, commandMap, model, request, response);
			}
			
			setAlertMessage(request, "과제를 삭제 하였습니다.");
			
			return "redirect:"+ new URLBuilder("/lec/prj/assignment/editFormPrjAssignment")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("prjtSn", vo.getPrjtSn())
					.toString();
		}


	
	/**
	 * 팀프로젝트 과제제출 폼(학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addFormSend")
	public String addFormSend(PrjAssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

			
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			
			String returnUrl = "home/lecture/project/assignment/assignment_send_read_pop_student";
			
			//-- 팀목록을 가져온다.
			PrjTeamVO prjTeamVO = new PrjTeamVO();
			prjTeamVO.setCrsCreCd(vo.getCrsCreCd());
			prjTeamVO.setPrjtSn(vo.getPrjtSn());
			prjTeamVO.setPrjtTeamSn(Integer.toString(vo.getPrjtTeamSn()));
			
			request.setAttribute("prjTeamVO", prjTeamService.view(prjTeamVO).getReturnVO());
			
			//-- 팀프로젝트 과제 정보를 가져온다.
			PrjAssignmentVO prjAssignmentVO = new PrjAssignmentVO();
			prjAssignmentVO.setCrsCreCd(vo.getCrsCreCd());
			prjAssignmentVO.setPrjtSn(vo.getPrjtSn());
			prjAssignmentVO = prjAssignmentService.view(prjAssignmentVO).getReturnVO();
			request.setAttribute("prjAssignmentVO", prjAssignmentVO);


			try{
				vo.setAsmtSn(prjAssignmentVO.getAsmtSn());
				request.setAttribute("vo", prjAssignmentService.viewSend(vo).getReturnVO());
			}catch(EmptyResultDataAccessException e){
				returnUrl = "assignment_send_write_student";
			}
			request.setAttribute("gubun", "A");

			return returnUrl;
	}
	
	/**
	 * 팀 프로젝트 과제 제출 등록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addSend")
	public String addSend(PrjAssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
			try {
				prjAssignmentService.addSend(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "과제를 제출 하지 못하였습니다.");
				return this.editFormPrjAssignment(new PrjAssignmentVO(), commandMap, model, request, response);
			}
			
			setAlertMessage(request, "과제를 제출 하였습니다.");
			
			return "redirect:"+ new URLBuilder("/lec/prj/assignment/addFormSend")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("prjtSn", vo.getPrjtSn())
					.addParameter("prjtTeamSn", vo.getPrjtTeamSn())
					.addParameter("asmtSn", vo.getAsmtSn())
					.toString();
	}
	
	

	/**
	 * 팀프로젝트 과제제출 폼(학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	@RequestMapping(value="/editFormSend")
	public String editFormSend(PrjAssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

			
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			
			//-- 팀목록을 가져온다.
			PrjTeamVO prjTeamVO = new PrjTeamVO();
			prjTeamVO.setCrsCreCd(vo.getCrsCreCd());
			prjTeamVO.setPrjtSn(vo.getPrjtSn());
			prjTeamVO.setPrjtTeamSn(Integer.toString(vo.getPrjtTeamSn()));
			request.setAttribute("prjTeamVO", prjTeamService.view(prjTeamVO).getReturnVO());
				
			//-- 팀프로젝트 과제 정보를 가져온다.
			PrjAssignmentVO prjAssignmentVO = new PrjAssignmentVO();
			prjAssignmentVO.setCrsCreCd(vo.getCrsCreCd());
			prjAssignmentVO.setPrjtSn(vo.getPrjtSn());
			request.setAttribute("prjAssignmentVO", prjAssignmentService.view(prjAssignmentVO).getReturnVO());
			request.setAttribute("vo", prjAssignmentService.viewSend(vo).getReturnVO());
			request.setAttribute("gubun", "E");

			return "home/lecture/project/assignment/assignment_send_write_pop_student";
	}
	
	/**
	 * 팀 프로젝트 과제 제출 수정(학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editSend")
	public String editSend(PrjAssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


			
			try {
				prjAssignmentService.editSend(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "과제 제출 정보를 수정 하지 못하였습니다.");
				return this.editFormPrjAssignment(new PrjAssignmentVO(), commandMap, model, request, response);
			}
			
			setAlertMessage(request, "과제 제출 정보를 수정 하였습니다.");
			
			return "redirect:"+ new URLBuilder("/lec/prj/assignment/addFormSend")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("prjtSn", vo.getPrjtSn())
					.addParameter("prjtTeamSn", vo.getPrjtTeamSn())
					.addParameter("asmtSn", vo.getAsmtSn())
					.toString();
	}
	
	/**
	 * 팀 프로젝트 과제 제출 삭제(학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removeSend")
	public String removeSend(PrjAssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		
			
			try {
				prjAssignmentService.removeSend(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "과제 제출 정보를 삭제하지 못하였습니다.");
				return this.editFormPrjAssignment(new PrjAssignmentVO(), commandMap, model, request, response);
			}
			
			setAlertMessage(request, "과제 제출 정보를 삭제 하였습니다.");
			
			return "redirect:"+ new URLBuilder("/lec/prj/assignment/addFormSend")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("prjtSn", vo.getPrjtSn())
					.addParameter("prjtTeamSn", vo.getPrjtTeamSn())
					.addParameter("asmtSn", vo.getAsmtSn())
					.toString();
		}
}

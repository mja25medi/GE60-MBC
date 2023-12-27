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
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectVO;
import egovframework.edutrack.modules.lecture.project.result.service.ProjectResultService;
import egovframework.edutrack.modules.lecture.project.result.service.ProjectResultVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/prj/result")
public class PrjResultLectureController extends GenericController {

	@Autowired 
	@Qualifier("projectResultService")
	private ProjectResultService projectResultService;
	
	/**
	 * 팀관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainPrjResult")
	public String mainPrjResult( ProjectResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			String crsCreCd = UserBroker.getCreCrsCd(request);
			vo.setCrsCreCd(crsCreCd);
			
			request.setAttribute("vo", vo);
			request.setAttribute("projectResultList", projectResultService.list(vo).getReturnList());
			return "home/lecture/project/result/result_list_teacher";
	}
	
	/**
	 * 팀관리 목록 
	 * @throws Exception 
	 */
	@RequestMapping(value="/listPrjResult")
	public String listPrjResult( ProjectResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			commonVOProcessing(vo, request);
				
			return JsonUtil.responseJson(response, projectResultService.list(vo));
	}
	
	/**
	 * 팀 점수저장
	 */
	@RequestMapping(value="/editScoreAll")
	public String editScoreAll( ProjectResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
			commonVOProcessing(vo, request);
			
			String strTeamScore = request.getParameter("strTeamScore");
			String strPrjtTeamSn = request.getParameter("strPrjtTeamSn");
			
			try {
				projectResultService.editScoreAll(vo, strTeamScore, strPrjtTeamSn);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "팀 점수를 저장하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/result/mainPrjResult")
							.addParameter("projectResultVO.prjtSn", vo.getPrjtSn())
							.toString()
				);
			}
			
			setAlertMessage(request, "팀 점수를 저장 하였습니다.");
			
			return "redirect:"+(
					new URLBuilder("/lec/prj/result/mainPrjResult")
						.addParameter("projectResultVO.prjtSn", vo.getPrjtSn())
						.toString()
			);
			
	}
	
	
	/**
	 * 팀원 보기
	 * @throws Exception 
	 */
	@RequestMapping(value="/teamMain")
	public String teamMain( ProjectResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			commonVOProcessing(vo, request);
		
			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			
			// 부모창 새로고침 여부
			String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
			request.setAttribute("refreshYn", refreshYn);
			
			//팀원 보기
			request.setAttribute("vo", projectResultService.view(vo).getReturnVO());
			request.setAttribute("teamList", projectResultService.teamList(vo).getReturnList());
			
			return "home/lecture/project/result/teamlist_pop_teacher";
	}
	/**
	 * 팀원 목록
	 * @throws Exception 
	 */
	@RequestMapping(value="/teamList")
	public String teamList( ProjectResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			commonVOProcessing(vo, request);
			
			return JsonUtil.responseJson(response, projectResultService.teamList(vo));
	}
	
	/**
	 * 팀 회원 점수저장
	 */
	@RequestMapping(value="/editMbScoreAll")
	public String editMbScoreAll( ProjectResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
			commonVOProcessing(vo, request);
			
			String strMbrScore = request.getParameter("strMbrScore");
			String strPrjtMbrSn = request.getParameter("strPrjtMbrSn");
			
			try {
				projectResultService.editMbScoreAll(vo, strMbrScore, strPrjtMbrSn);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "회원 점수를 저장하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/result/teamMain")
							.addParameter("projectResultVO.prjtSn", vo.getPrjtSn())
							.addParameter("projectResultVO.prjtTeamSn", vo.getPrjtTeamSn())
							.toString()
				);
			}
			
			setAlertMessage(request, "회원 점수를 저장 하였습니다.");
			
			return "redirect:"+(
					new URLBuilder("/lec/prj/result/teamMain")
					.addParameter("projectResultVO.prjtSn", vo.getPrjtSn())
					.addParameter("projectResultVO.prjtTeamSn", vo.getPrjtTeamSn())
					.addParameter("refreshYn", "Y")
					.toString()
					);
			
	}
	
	/**
	 * 과제 정보 보기
	 */
	@RequestMapping(value="/siInfoMain")
	public String siInfoMain( ProjectResultVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
			commonVOProcessing(vo, request);

			PrjAssignmentSendVO prjAssignmentSendVO = new PrjAssignmentSendVO();
			try {
				//과제 정보
				request.setAttribute("vo", projectResultService.siInfoView(vo).getReturnVO());
			} catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "미제출로 볼수 없습니다.");
			}
			
			try{
				//제출정보
				prjAssignmentSendVO.setCrsCreCd(vo.getCrsCreCd());
				prjAssignmentSendVO.setPrjtSn(vo.getPrjtSn());
				prjAssignmentSendVO.setAsmtSn(vo.getAsmtSn());
				request.setAttribute("prjAssignmentSendVO", projectResultService.submitInfoView(prjAssignmentSendVO).getReturnVO());
			}catch (Exception e) {
				e.printStackTrace();
				String prjAssignmentSend = "N";
				request.setAttribute("prjAssignmentSend", prjAssignmentSend);
			}

			
			return "home/lecture/project/result/siinfo_read_pop_teacher";
	}
	
}

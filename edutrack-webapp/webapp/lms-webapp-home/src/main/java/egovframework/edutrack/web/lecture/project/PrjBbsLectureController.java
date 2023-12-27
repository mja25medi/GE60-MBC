package egovframework.edutrack.web.lecture.project;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsService;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;



@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/prj/bbs")
public class PrjBbsLectureController extends GenericController {

	@Autowired
	@Qualifier("prjBbsService")
	private PrjBbsService prjBbsService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService sysCodeMemService;

	/**
	 * 팀프로젝트  게시판 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainPrjBbs")
	public String mainPrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			String crsCreCd = UserBroker.getCreCrsCd(request);
			vo.setCrsCreCd(crsCreCd);
			request.setAttribute("prjBbsListVO", prjBbsService.list(vo).getReturnList());
			request.setAttribute("vo", vo);
			return "home/lecture/project/board/board_list_teacher";
	}

	/**
	 * 팀프로젝트 게시판 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormPrjBbs")
	public String addFormPrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

			// 부모창 새로고침 여부
			String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
			request.setAttribute("refreshYn", refreshYn);

			//OPEN_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeUseYn = sysCodeMemService.getSysCodeList("USE_YN");

			//ALLOW_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeAllowYn = sysCodeMemService.getSysCodeList("ALLOW_YN");
			request.setAttribute("codeUseYn", codeUseYn);
			request.setAttribute("codeAllowYn", codeAllowYn);

			//등록 구분 코드
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			return "home/lecture/project/board/board_write_pop_teacher";
	}

	/**
	 * 팀프로젝트 게시판 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjBbs")
	public String addPrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);

			try {
				prjBbsService.add(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시판을 등록하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbs/addFormPrjBbs")
							.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
							.toString()
				);
			}

			setAlertMessage(request, "게시판을 등록 하였습니다.");

			return "redirect:"+(
					new URLBuilder("/lec/prj/bbs/addFormPrjBbs")
						.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
						.addParameter("refreshYn", "Y")
						.toString()
			);
	}

	/**
	 * 팀프로젝트 게시판 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editFormPrjBbs")
	public String editFormPrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			// 부모창 새로고침 여부
			String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
			request.setAttribute("refreshYn", refreshYn);

			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			//OPEN_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeUseYn = sysCodeMemService.getSysCodeList("USE_YN");

			//ALLOW_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeAllowYn = sysCodeMemService.getSysCodeList("ALLOW_YN");

			request.setAttribute("codeUseYn", codeUseYn);
			request.setAttribute("codeAllowYn", codeAllowYn);

			//게시판 정보 조회
			request.setAttribute("prjBbsVO", prjBbsService.view(vo).getReturnVO());

			//공통 입력 페이지 사용시 문자열 로 판단
			request.setAttribute("gubun", "E");
			return "home/lecture/project/board/board_write_pop_teacher";
	}

	/**
	 * 팀프로젝트 게시판 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjBbs")
	public String editPrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			try {
				prjBbsService.edit(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시판을 수정하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbs/editFormPrjBbs")
							.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjBbsVO.bbsCd", vo.getBbsCd())
							.toString()
				);
			}

			setAlertMessage(request, "게시판을 수정 하였습니다.");

			return "redirect:"+(
					new URLBuilder("/lec/prj/bbs/editFormPrjBbs")
						.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsVO.bbsCd", vo.getBbsCd())
						.addParameter("refreshYn", "Y")
						.toString()
			);

	}

	/**
	 * 팀프로젝트 게시판 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removePrjBbs")
	public String removePrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);

			try {
				prjBbsService.remove(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시판을 삭제하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbs/editFormPrjBbs")
							.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjBbsVO.bbsCd", vo.getBbsCd())
							.toString()
				);
			}
			setAlertMessage(request, "게시판을 삭제 하였습니다.");

			return "redirect:"+(
					new URLBuilder("/lec/prj/bbs/addFormPrjBbs")
						.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
						.addParameter("refreshYn", "Y")
						.toString()
			);
	}

	/**
	 * 팀프로젝트 게시판 목록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listPrjBbs")
	public String listPrjBbs( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			String crsCreCd = UserBroker.getCreCrsCd(request);
			vo.setCrsCreCd(crsCreCd);
			request.setAttribute("prjBbsListVO", prjBbsService.list(vo).getReturnList());
			request.setAttribute("vo", vo);
			return "home/lecture/project/board/board_list_pop_student";
	}

	/**
	 * 팀프로젝트 게시판 등록 폼  (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormPrjBbsStu")
	public String addFormPrjBbsStu( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

			// 부모창 새로고침 여부
			String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
			request.setAttribute("refreshYn", refreshYn);

			//OPEN_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeUseYn = sysCodeMemService.getSysCodeList("USE_YN");

			//ALLOW_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeAllowYn = sysCodeMemService.getSysCodeList("ALLOW_YN");
			request.setAttribute("codeUseYn", codeUseYn);
			request.setAttribute("codeAllowYn", codeAllowYn);

			//등록 구분 코드
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			return "home/lecture/project/board/board_write_pop_student";
	}

	/**
	 * 팀프로젝트 게시판 등록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjBbsStu")
	public String addPrjBbsStu( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			try {
				prjBbsService.add(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시판을 등록하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbs/addFormPrjBbsStu")
							.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
							.toString()
				);
			}

			setAlertMessage(request, "게시판을 등록 하였습니다.");

			return "redirect:"+(
					new URLBuilder("/lec/prj/bbs/addFormPrjBbsStu")
						.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
						.addParameter("refreshYn", "Y")
						.toString()
			);
	}

	/**
	 * 팀프로젝트 게시판 수정 폼 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editFormPrjBbsStu")
	public String editFormPrjBbsStu( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			// 부모창 새로고침 여부
			String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
			request.setAttribute("refreshYn", refreshYn);

			vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
			//OPEN_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeUseYn = sysCodeMemService.getSysCodeList("USE_YN");

			//ALLOW_YN 시스템 코드 목록 조회
			List<SysCodeVO> codeAllowYn = sysCodeMemService.getSysCodeList("ALLOW_YN");

			request.setAttribute("codeUseYn", codeUseYn);
			request.setAttribute("codeAllowYn", codeAllowYn);

			//게시판 정보 조회
			request.setAttribute("prjBbsVO", prjBbsService.view(vo).getReturnVO());

			//공통 입력 페이지 사용시 문자열 로 판단
			request.setAttribute("gubun", "E");
			return "home/lecture/project/board/board_write_pop_student";
	}

	/**
	 * 팀프로젝트 게시판 수정  (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjBbsStu")
	public String editPrjBbsStu( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			try {
				prjBbsService.edit(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시판을 수정하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbs/editFormPrjBbsStu")
							.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjBbsVO.bbsCd", vo.getBbsCd())
							.toString()
				);
			}

			setAlertMessage(request, "게시판을 수정 하였습니다.");

			return "redirect:"+(
					new URLBuilder("/lec/prj/bbs/editFormPrjBbsStu")
						.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsVO.bbsCd", vo.getBbsCd())
						.addParameter("refreshYn", "Y")
						.toString()
			);

	}

	/**
	 * 팀프로젝트 게시판 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removePrjBbsStu")
	public String removePrjBbsStu( PrjBbsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			try {
				prjBbsService.remove(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시판을 삭제하지 못했습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbs/editFormPrjBbsStu")
							.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
							.addParameter("prjBbsVO.bbsCd", vo.getBbsCd())
							.toString()
				);
			}
			setAlertMessage(request, "게시판을 삭제 하였습니다.");

			return "redirect:"+(
					new URLBuilder("/lec/prj/bbs/addFormPrjBbsStu")
						.addParameter("prjBbsVO.prjtSn", vo.getPrjtSn())
						.addParameter("refreshYn", "Y")
						.toString()
			);
	}
}






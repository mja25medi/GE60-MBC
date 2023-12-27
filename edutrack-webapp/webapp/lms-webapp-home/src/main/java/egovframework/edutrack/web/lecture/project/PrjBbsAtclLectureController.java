package egovframework.edutrack.web.lecture.project;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclService;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclVO;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntService;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntVO;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsService;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsVO;



@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/prj/bbsAtcl")
public class PrjBbsAtclLectureController extends GenericController {
	
	@Autowired 
	@Qualifier("prjBbsAtclService")
	private PrjBbsAtclService prjBbsAtclService;
	
	@Autowired 
	@Qualifier("prjBbsService")
	private PrjBbsService prjBbsService;
	
	@Autowired 
	@Qualifier("prjBbsCmntService")
	private PrjBbsCmntService prjBbsCmntService;
	
	/**
	 * 팀프로젝트  게시글 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainPrjBbsAtcl")
	public String mainPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		
		//게시판 목록
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		request.setAttribute("prjBbsList", prjBbsService.list(prjBbsVO).getReturnList());
		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		
		int listCale = 0;
		if(vo.getBbsCd() == null){
			listCale = 10;
		}else{
//			PrjBbsVO prjBbsVO = new PrjBbsVO();
			prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
			prjBbsVO.setPrjtSn(vo.getPrjtSn());
			prjBbsVO.setBbsCd(vo.getBbsCd());
			prjBbsVO = prjBbsService.view(prjBbsVO).getReturnVO();
			listCale = prjBbsVO.getListViewCnt();
		}
		
		ProcessResultListVO<PrjBbsAtclVO> resultList = prjBbsAtclService.listPageing(vo, curPage, listCale, 10); 

		request.setAttribute("prjBbsAtclList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "home/lecture/project/board/board_atcl_list_teacher";
	}
	
	/**
	 * 팀프로젝트 게시글 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormPrjBbsAtcl")
	public String addFormPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		//게시판 정보
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		prjBbsVO.setBbsCd(vo.getBbsCd());
		request.setAttribute("prjBbsVO", prjBbsService.view(prjBbsVO).getReturnVO());
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		
		return "home/lecture/project/board/board_atcl_write_teacher";
	}
	
	/**
	 * 팀프로젝트 게시글 등록 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjBbsAtcl")
	public String addPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setAtclCts(HtmlCleaner.cleanScript(vo.getAtclCts()));
		try {
			prjBbsAtclService.add(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "게시글을 저장하지 못하였습니다.");
			return this.addFormPrjBbsAtcl(vo, commandMap, model, request, response);
		}
		
		setAlertMessage(request, "게시글을 저장 하였습니다.");
		
		return "redirect:"+(
				new URLBuilder("/lec/prj/bbsAtcl/mainPrjBbsAtcl")
				.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
				.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
				.toString()
				);
	}
	
	/**
	 * 팀프로젝트 게시글 상세보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readPrjBbsAtcl")
	public String readPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		//댓글 추가시 조회수 (false)
		String strHitsup = request.getParameter("hitsup");
		
		boolean hitsup;
		
		if("false".equals(strHitsup)){
			hitsup = false;
		}else{
			hitsup = true;
		}
				
		//게시판 정보
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		prjBbsVO.setBbsCd(vo.getBbsCd());
		request.setAttribute("prjBbsVO", prjBbsService.view(prjBbsVO).getReturnVO());
		//게시글 정보
		request.setAttribute("vo", prjBbsAtclService.view(vo, hitsup).getReturnVO());
		return "home/lecture/project/board/board_atcl_read_teacher";
	}
	
	/**
	 * 팀프로젝트 게시글 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormPrjBbsAtcl")
	public String editFormPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		//게시판 정보
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		prjBbsVO.setBbsCd(vo.getBbsCd());
		request.setAttribute("prjBbsVO", prjBbsService.view(prjBbsVO).getReturnVO());
		//게시글 정보
		request.setAttribute("prjBbsAtclVO", prjBbsAtclService.view(vo).getReturnVO());
		
		request.setAttribute("gubun", "E");
		
		return "home/lecture/project/board/board_atcl_write_teacher";
	}
	
	/**
	 * 팀프로젝트 게시글 수정 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjBbsAtcl")
	public String editPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			prjBbsAtclService.edit(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "게시글을 수정하지 못하였습니다.");
			return this.editFormPrjBbsAtcl(vo, commandMap, model, request, response);
		}
		
		setAlertMessage(request, "게시글을 수정 하였습니다.");
		
		return "redirect:"+(
				new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtcl")
				.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
				.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
				.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
				.addParameter("hitsup", false)
				.toString()
				);
	}
	
	/**
	 * 팀프로젝트 게시글 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removePrjBbsAtcl")
	public String removePrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			try {
				prjBbsAtclService.remove(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시글을 삭제하지 못하였습니다.");
				return this.readPrjBbsAtcl(vo, commandMap, model, request, response);
			}
			
			setAlertMessage(request, "게시글을 삭제 하였습니다.");
			
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/mainPrjBbsAtcl")
					.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
					.toString()
					);
		}
	
	/**
	 * 게시글 댓글 목록 Ajax
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/listPrjBbsCmnt")
    public String listPrjBbsCmnt( PrjBbsCmntVO vo, Map commandMap, ModelMap model, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			return 
					JsonUtil.responseJson(response, prjBbsCmntService.listPageing(vo, vo.getCurPage(), 10, Constants.LIST_PAGE_SCALE));
    }
	
	/**
	 * 게시글 댓글 정보 등록 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/addPrjBbsCmnt")
	public String addPrjBbsCmnt( PrjBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			String crsCreCd = UserBroker.getCreCrsCd(request);
			
			vo.setCrsCreCd(crsCreCd);
			// 스크립트, 스타일 태그 제거
			vo.setCmntCts(HtmlCleaner.cleanScript(vo.getCmntCts()));
			
			ProcessResultVO<PrjBbsCmntVO> resultVO = prjBbsCmntService.add(vo);
			vo = resultVO.getReturnVO();
			
			// 목록 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtcl")
						.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
						.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
						.addParameter("hitsup", false)
						.toString()
			);
	}
	
	/**
	 * 게시글 댓글 정보 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/removePrjBbsCmnt")
	public String removePrjBbsCmnt( PrjBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			String crsCreCd = UserBroker.getCreCrsCd(request);
			String classUserType = UserBroker.getClassUserType(request);
			String userNo = UserBroker.getUserNo(request);
			
			vo.setCrsCreCd(crsCreCd);
			
			try {
				//교수자가 들어 왔을때 모두 삭제 가능
				if(classUserType == "TCH"){
					prjBbsCmntService.remove(vo);
				}else if(classUserType == "STU"){
					//-- 등록자와 동일한 경우만.. 삭제 가능하도록함.
					PrjBbsCmntVO viewCmntVO = prjBbsCmntService.view(vo).getReturnVO();
					if(!viewCmntVO.getRegNo().equals(userNo)){
						setAlertMessage(request, "댓글의 삭제는 등록자만 삭제 할 수 있습니다.");
							return "redirect:"+(
									new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtcl")
									.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
									.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
									.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
									.addParameter("hitsup", false)
									.toString()
								);
					}
					
					prjBbsCmntService.remove(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();

			}
			
			// 목록 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtcl")
						.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
						.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
						.addParameter("hitsup", false)
						.toString()
			);
	}
	
	
	/**
	 * 팀프로젝트 게시글 목록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listPrjBbsAtcl")
	public String listPrjBbsAtcl( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));

		int listCale = 0;
		if(vo.getBbsCd() == ""){
			listCale = 10;
		}else{
			PrjBbsVO prjBbsVO = new PrjBbsVO();
			prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
			prjBbsVO.setPrjtSn(vo.getPrjtSn());
			prjBbsVO.setBbsCd(vo.getBbsCd());
			prjBbsVO = prjBbsService.view(prjBbsVO).getReturnVO();
			listCale = prjBbsVO.getListViewCnt();
		}
		
		ProcessResultListVO<PrjBbsAtclVO> resultList = prjBbsAtclService.listPageing(vo, curPage, listCale, 10); 

		request.setAttribute("prjBbsAtclList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "home/lecture/project/board/board_atcl_list_pop_student";

	}
	
	/**
	 * 팀프로젝트 게시글 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormPrjBbsAtclStu")
	public String addFormPrjBbsAtclStu( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		//게시판 정보
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		prjBbsVO.setBbsCd(vo.getBbsCd());
		request.setAttribute("prjBbsVO", prjBbsService.view(prjBbsVO).getReturnVO());
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "home/lecture/project/board/board_atcl_write_pop_student";
	}
	
	/**
	 * 팀프로젝트 게시글 등록 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPrjBbsAtclStu")
	public String addPrjBbsAtclStu( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		vo.setAtclCts(HtmlCleaner.cleanScript(vo.getAtclCts()));
		try {
			prjBbsAtclService.add(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "게시글을 저장하지 못하였습니다.");
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/addFormPrjBbsAtclStu")
					.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
					.toString()
					);
		}
		
		setAlertMessage(request, "게시글을 저장 하였습니다.");
		
		return "redirect:"+(
				new URLBuilder("/lec/prj/bbsAtcl/listPrjBbsAtcl")
				.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
				.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
				.toString()
				);
	}
	
	/**
	 * 팀프로젝트 게시글 상세보기 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readPrjBbsAtclStu")
	public String readPrjBbsAtclStu( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		//댓글 추가시 조회수 (false)
		String strHitsup = request.getParameter("hitsup");
		
		boolean hitsup;
		
		if("false".equals(strHitsup)){
			hitsup = false;
		}else{
			hitsup = true;
		}
				
		//게시판 정보
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		prjBbsVO.setBbsCd(vo.getBbsCd());
		request.setAttribute("prjBbsVO", prjBbsService.view(prjBbsVO).getReturnVO());
		//게시글 정보
		request.setAttribute("vo", prjBbsAtclService.view(vo, hitsup).getReturnVO());
		return "home/lecture/project/board/board_atcl_read_pop_student";
	}
	
	/**
	 * 팀프로젝트 게시글 수정 폼 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormPrjBbsAtclStu")
	public String editFormPrjBbsAtclStu( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		
		//게시판 정보
		PrjBbsVO prjBbsVO = new PrjBbsVO();
		prjBbsVO.setCrsCreCd(vo.getCrsCreCd());
		prjBbsVO.setPrjtSn(vo.getPrjtSn());
		prjBbsVO.setBbsCd(vo.getBbsCd());
		request.setAttribute("prjBbsVO", prjBbsService.view(prjBbsVO).getReturnVO());
		
		//게시글 정보
		request.setAttribute("prjBbsAtclVO", prjBbsAtclService.view(vo).getReturnVO());
		
		request.setAttribute("gubun", "E");
		return "home/lecture/project/board/board_atcl_write_pop_student";
	}
	
	/**
	 * 팀프로젝트 게시글 수정 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPrjBbsAtclStu")
	public String editPrjBbsAtclStu( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		try {
			prjBbsAtclService.edit(vo);
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, "게시글을 수정하지 못하였습니다.");
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/editFormPrjBbsAtclStu")
					.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
					.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
					.toString()
					);
		}
		
		setAlertMessage(request, "게시글을 수정 하였습니다.");
		
		return "redirect:"+(
				new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtclStu")
				.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
				.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
				.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
				.addParameter("hitsup", false)
				.toString()
				);
	}
	
	/**
	 * 팀프로젝트 게시글 삭제 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removePrjBbsAtclStu")
	public String removePrjBbsAtclStu( PrjBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
			
			try {
				prjBbsAtclService.remove(vo);
			}catch (Exception e) {
				e.printStackTrace();
				setAlertMessage(request, "게시글을 삭제하지 못하였습니다.");
				return "redirect:"+(
						new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtclStu")
						.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
						.addParameter("hitsup", false)
						.toString()
						);
			}
			
			setAlertMessage(request, "게시글을 삭제 하였습니다.");
			
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/listPrjBbsAtcl")
					.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
					.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
					.toString()
					);
		}
	
	/**
	 * 게시글 댓글 정보 등록  (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/addPrjBbsCmntStu")
	public String addPrjBbsCmntStu( PrjBbsCmntVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
		
			String crsCreCd = UserBroker.getCreCrsCd(request);
			
			vo.setCrsCreCd(crsCreCd);
			// 스크립트, 스타일 태그 제거
			vo.setCmntCts(HtmlCleaner.cleanScript(vo.getCmntCts()));
			
			ProcessResultVO<PrjBbsCmntVO> resultVO = prjBbsCmntService.add(vo);
			vo = resultVO.getReturnVO();
			
			// 목록 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtclStu")
						.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
						.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
						.addParameter("hitsup", false)
						.toString()
			);
	}
	
	/**
	 * 게시글 댓글 정보 삭제 (학습자용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/removePrjBbsCmntStu")
	public String removePrjBbsCmntStu(PrjBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);
		
			String crsCreCd = UserBroker.getCreCrsCd(request);
			String classUserType = UserBroker.getClassUserType(request);
			String userNo = UserBroker.getUserNo(request);
			
			vo.setCrsCreCd(crsCreCd);
			
			try {
				//교수자가 들어 왔을때 모두 삭제 가능
				if(classUserType == "TCH"){
					prjBbsCmntService.remove(vo);
				}else if(classUserType == "STU"){
					//-- 등록자와 동일한 경우만.. 삭제 가능하도록함.
					PrjBbsCmntVO viewCmntVO = prjBbsCmntService.view(vo).getReturnVO();
					if(!viewCmntVO.getRegNo().equals(userNo)){
						setAlertMessage(request, "댓글의 삭제는 등록자만 삭제 할 수 있습니다.");
							return "redirect:"+(
									new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtclStu")
									.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
									.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
									.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
									.addParameter("hitsup", false)
									.toString()
								);
					}
					
					prjBbsCmntService.remove(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();

			}
			
			// 목록 화면으로 
			return "redirect:"+(
					new URLBuilder("/lec/prj/bbsAtcl/readPrjBbsAtclStu")
						.addParameter("prjBbsAtclVO.prjtSn", vo.getPrjtSn())
						.addParameter("prjBbsAtclVO.bbsCd", vo.getBbsCd())
						.addParameter("prjBbsAtclVO.atclSn", vo.getAtclSn())
						.addParameter("hitsup", false)
						.toString()
			);
	}
}






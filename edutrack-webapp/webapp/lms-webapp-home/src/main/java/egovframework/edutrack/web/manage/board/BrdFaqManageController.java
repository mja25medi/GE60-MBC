package egovframework.edutrack.web.manage.board;

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
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqService;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/brd/faq")
public class BrdFaqManageController extends GenericController {
	
	@Autowired @Qualifier("brdFaqService")
	private BrdFaqService 			brdFaqService;

	
	/**
     * @Method Name : main
     * @Method 설명 : FAQ 메인 화면
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/board/faq/main";
	}
	
	/**
	 * @Method Name : listCtgr
	 * @Method 설명 : FAQ 분류 목록
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/listCtgr")
	public String listCtgr(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<BrdFaqCtgrVO> brdFaqCtgrList = brdFaqService.listCtgr(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("brdFaqCtgrList", brdFaqCtgrList.getReturnList());
		return "mng/board/faq/list_ctgr";
	}
	
	/**
	 * @Method Name : addFormCtgr
	 * @Method 설명 : FAQ 분류 등록 화면
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormCtgrPop")
	public String addFormCtgrPop(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUseYn("Y");
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/board/faq/write_ctgr";
	}
	
	/**
     * @Method Name : addCtgr
     * @Method 설명 : FAQ 분류 등록
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<BrdFaqCtgrVO> result = new ProcessResultVO<BrdFaqCtgrVO>();
		
		try {
			brdFaqService.addCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.faq.category.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.faq.category.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	/**
	 * @Method Name : editFormCtgr
	 * @Method 설명 : FAQ 분류 수정 화면
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormCtgrPop")
	public String editFormCtgrPop(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		vo = brdFaqService.viewCtgr(vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/board/faq/write_ctgr";
	}
	
	/**
     * @Method Name : editCtgr
     * @Method 설명 : FAQ 분류 수정
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<BrdFaqCtgrVO> result = new ProcessResultVO<BrdFaqCtgrVO>();
		
		try {
			brdFaqService.editCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.faq.category.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.faq.category.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
	
	/**
	 * @Method Name : removeCtgr
	 * @Method 설명 : FAQ 분류 삭제
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeCtgr")
	public String removeCtgr(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<BrdFaqCtgrVO> result = new ProcessResultVO<BrdFaqCtgrVO>();
		
		try {
			brdFaqService.removeCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.faq.category.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.faq.category.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
	
	/**
	 * @Method Name : sortCtgr
	 * @Method 설명 : FAQ 분류 순서변경
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/sortCtgr")
	public String sortCtgr(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<BrdFaqCtgrVO> result = new ProcessResultVO<BrdFaqCtgrVO>();
		
		try {
			brdFaqService.sortCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.faq.category.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.faq.category.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
	
	
	
	
	
	
	/**
	 * @Method Name : listAtcl
	 * @Method 설명 : FAQ 글 목록
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/listAtcl")
	public String listAtcl(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<BrdFaqAtclVO> resultList = brdFaqService.listAtcl(vo);
		request.setAttribute("brdFaqAtclList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/board/faq/list_atcl";
	}
	
	/**
	 * @Method Name : addFormAtcl
	 * @Method 설명 : FAQ 글 등록 화면
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormAtclMain")
	public String addFormAtclMain(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		request.setAttribute("gubun", "A");// A: 등록 E: 수정
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", vo);
		request.setAttribute("summernote", vo);
		return "mng/board/faq/write_atcl";
	}
	
	/**
     * @Method Name : addAtcl
     * @Method 설명 : FAQ 글 등록
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addAtclMain")
	public String addAtclMain(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		// 스크립트, 스타일 태그 제거
		vo.setAtclCts( HtmlCleaner.cleanScript(vo.getAtclCts()) );

		try {
			brdFaqService.addAtcl(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.failed"));
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			request.setAttribute("fileupload", vo);
			request.setAttribute("summernote", vo);
			return "mng/board/faq/write_atcl";
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.success"));
		// 메인 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/faq/main")
				.addParameter("ctgrCd", vo.getCtgrCd())
				.addParameter("ctgrNm", vo.getCtgrNm())
				.addParameter("pageIndex", vo.getPageIndex())
				.toString();
	}
	
	/**
	 * @Method Name : editFormAtcl
	 * @Method 설명 : FAQ 글 수정 화면
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormAtclMain")
	public String editFormAtclMain(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		BrdFaqAtclVO bfaVo = brdFaqService.viewAtcl(vo);
		bfaVo.setSearchValue(vo.getSearchValue());
		
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", bfaVo);
		request.setAttribute("fileupload", vo);
		request.setAttribute("summernote", vo);
		return "mng/board/faq/write_atcl";
	}
	
	/**
	 * @Method Name : editAtcl
	 * @Method 설명 : FAQ 글 수정
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editAtcl")
	public String editAtcl(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		// 스크립트, 스타일 태그 제거
		vo.setAtclCts( HtmlCleaner.cleanScript(vo.getAtclCts()) );
		
		try {
			brdFaqService.editAtcl(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.failed"));
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);			
			return "mng/board/faq/write_atcl";
		}
		
		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.success"));
		// 읽기 화면으로
		return "redirect:"+
		new URLBuilder("/mng/brd/faq/main")
		.addParameter("ctgrCd", vo.getCtgrCd())
		.addParameter("ctgrNm", vo.getCtgrNm())
		.addParameter("pageIndex", vo.getPageIndex())
		.toString();
	}
	
	/**
	 * @Method Name : editAtcl
	 * @Method 설명 : FAQ 글 수정
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/removeAtcl")
	public String removeAtcl(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		
		try {
			brdFaqService.removeAtcl(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.failed"));
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);			
			return "mng/board/faq/write_atcl";
		}
		
		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.success"));
		// 읽기 화면으로
		return "redirect:"+
		new URLBuilder("/mng/brd/faq/main")
		.addParameter("ctgrCd", vo.getCtgrCd())
		.addParameter("ctgrNm", vo.getCtgrNm())
		.addParameter("pageIndex", vo.getPageIndex())
		.toString();
	}
	
	/**
     * @Method Name : viewAtcl
     * @Method 설명 : FAQ 글 상세화면
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/viewAtcl")
	public String viewAtcl(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
    	
		vo = brdFaqService.viewAtcl(vo);

		request.setAttribute("vo", vo);
		return "mng/board/faq/view_atcl";
	}
	
	
	/**
	 * @Method Name : sortAtcl
	 * @Method 설명 : FAQ 글 순서변경
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/sortAtcl")
	public String sortAtcl(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<BrdFaqCtgrVO> result = new ProcessResultVO<BrdFaqCtgrVO>();
		
		try {
			brdFaqService.sortAtcl(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "board.message.faq.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "board.message.faq.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	
	
}

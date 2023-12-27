package egovframework.edutrack.web.manage.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.qna.service.BrdQnaAnsrVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaQstnVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaService;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaService;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/brd/qna")
public class BrdQnaManageController extends GenericController {
	
	@Autowired @Qualifier("brdQnaService")
	private BrdQnaService 			brdQnaService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;
	
	@Autowired @Qualifier("nonMemQnaService")
	private NonMemQnaService 		nonMemQnaservice;
	
	/**
     * 1대1 문의 메인 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(BrdQnaQstnVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		List<SysCodeVO> qnaCtgrList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
		request.setAttribute("qnaCtgrList", qnaCtgrList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		return "mng/board/qna/qna_main";
	}
	
	/**
     * 1대1 문의 메인 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/lecMain")
	public String lecMain(BrdQnaQstnVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		return "mng/board/qna/qna_lec_main";
	}
	
	
	/**
     * 1대1 문의 목록 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/listQstn")
	public String list(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setViewMode("admin");
		
		ProcessResultListVO<BrdQnaQstnVO> resultList = brdQnaService.listPageing(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("qnaQstnList", resultList.getReturnList());
		
		return "mng/board/qna/qna_list_div";
	}
	
	/**
     * 1대1 문의 목록 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/listLecQstn")
	public String listLecQstn(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setViewMode("admin");
		
		ProcessResultListVO<BrdQnaQstnVO> resultList = brdQnaService.listPageing(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("qnaQstnList", resultList.getReturnList());
		
		return "mng/board/qna/qna_lec_list_div";
	}
	
	/**
     * 1대1 문의 상세 조회 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/viewQnaMain")
	public String viewQnaMain(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		BrdQnaQstnVO qstn = brdQnaService.viewQstn(vo);
		request.setAttribute("vo", qstn);
		
		return "/mng/board/qna/qna_view";
	}
	
	/**
     * 1대1 문의 및 답변 삭제
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult 
	 * @throws Exception
	 */
	@RequestMapping(value="deleteQstn")
	@ResponseBody
	public AbstractResult deleteQstn(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.deleteQstnAndAnsr(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생 했습니다.");
		}
		return result;
	}
	
	/**
     * 1대1 문의 답변 작성 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/writeAnsrMain")
	public String writeAnsrMain(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		
		return "/mng/board/qna/qna_ansr_write";
	}
	
	/**
     * 1대1 문의 답변 작성
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/writeAnsr")
	@ResponseBody
	public AbstractResult writeAnsr(BrdQnaAnsrVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		vo.setChrgPrsnNm(UserBroker.getUserName(request));
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.writeAnsr(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("답변 등록 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	/**
     * 1대1 문의 답변 수정 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editAnsrMain")
	public String editQstnMain(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		BrdQnaAnsrVO ansr = brdQnaService.viewAnsr(vo).getReturnVO();
		request.setAttribute("vo", ansr);
		request.setAttribute("gubun","E");
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		
		return "/mng/board/qna/qna_ansr_write";
	}
	
	/**
     * 1대1 문의 답변 수정
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/editAnsr")
	@ResponseBody
	public AbstractResult editQstn(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		vo.setChrgPrsnNm(UserBroker.getUserName(request));
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.editAnsr(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		}
		catch(Exception e) {
			result.setResult(-1);
			result.setMessage("수정 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	/** 
     * 비회원 상담 메인 뷰
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/nonMemMain")
	public String nonMemMain(NonMemQnaVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		return "mng/board/qna/qna_nonMem_main";
	}
	
	/** 
     * 비회원 상담 목록 뷰
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/listNonMemQstn")
	public String listNonMemQstn(NonMemQnaVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setViewMode("admin");
		
		ProcessResultListVO<NonMemQnaVO> resultList = nonMemQnaservice.nonMemListPageing(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("qnaQstnList", resultList.getReturnList());
		
		return "mng/board/qna/qna_nonMem_list_div";
	}
	
	/**
     * 비회원 상담 상세 조회 뷰
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/viewNonMemMain")
	public String viewNonMemMain(NonMemQnaVO vo, HttpServletRequest request) throws Exception {
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		NonMemQnaVO qstn = nonMemQnaservice.viewNonMemQstn(vo);
		
		request.setAttribute("vo", qstn);
		request.setAttribute("curPage", curPage);
		
		return "/mng/board/qna/qna_nonMem_view";
	}
	
	/**
     * 비회원 상담 삭제
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return AbstractResult 
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteNonMem")
	@ResponseBody
	public AbstractResult deleteNonMem(NonMemQnaVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		try {
			nonMemQnaservice.deleteNonMem(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생 했습니다.");
		}
		return result;
	}
	
	/**
     * 비회원 상담 작성 뷰
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/writeNonMemAnsrMain")
	public String writeNonMemAnsrMain(NonMemQnaVO vo, HttpServletRequest request) throws Exception {
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "N");
		request.setAttribute("gubun","A");
		request.setAttribute("vo", vo);
		
		return "/mng/board/qna/qna_nonMem_ansr_write";
	}
	
	/**
     * 비회원 상담 답변 작성
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/writeNonMemAnsr")
	@ResponseBody
	public AbstractResult writeNonMemAnsr(NonMemQnaVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		vo.setAnsNo(UserBroker.getUserNo(request));
		AbstractResult result = new AbstractResult();
		try {
			nonMemQnaservice.writeNonMemAnsr(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("답변 등록 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	/**
     * 비회원 상담 답변 수정 뷰
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editNonMemAnsrMain")
	public String editNonMemAnsrMain(NonMemQnaVO vo, HttpServletRequest request) throws Exception {
		NonMemQnaVO ansr = nonMemQnaservice.viewNonMemAnsr(vo).getReturnVO();
		request.setAttribute("vo", ansr);
		request.setAttribute("gubun","E"); 
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "N");
		
		return "/mng/board/qna/qna_nonMem_ansr_write";
	}
	
	/**
     * 비회원 상담 답변 수정
	 * @param NonMemQnaVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/editNonMemAnsr")
	@ResponseBody
	public AbstractResult editNonMemAnsr(NonMemQnaVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		vo.setAnsNo(UserBroker.getUserNo(request));
		AbstractResult result = new AbstractResult();
		try {
			nonMemQnaservice.editNonMemAnsr(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		}
		catch(Exception e) {
			result.setResult(-1);
			result.setMessage("수정 중 오류가 발생했습니다.");
		}
		return result;
	}
}

package egovframework.edutrack.web.home.board;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.qna.service.BrdQnaAnsrVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaQstnVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaService;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaService;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@RequestMapping(value="/home/brd/qna")
public class BrdQnaHomeController extends GenericController {
	
	@Autowired @Qualifier("brdQnaService")
	private BrdQnaService brdQnaService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;
	
	@Autowired @Qualifier("nonMemQnaService")
	private NonMemQnaService nonMemQnaService;
	
	/**
     * 1대1 문의 메인 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(BrdQnaQstnVO vo,	HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		//상태코드
		List<SysCodeVO> qnaCtgrList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("qnaCtgrList", qnaCtgrList);
		
		return "home/board/qna/qna_main";
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
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setLecYn("N");
		
		boolean fileBind = true;
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"") ;
		if(userType.contains("ADMIN") || userType.contains("TEACHER")) vo.setViewMode("admin");
		ProcessResultListVO<BrdQnaQstnVO> resultList = brdQnaService.listPageing(vo,fileBind);
		request.setAttribute("vo", vo);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("qnaQstnList", resultList.getReturnList());
		
		return "home/board/qna/qna_list_div";
	}
	
	/**
     * 1대1 문의 작성 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/writeQstnMain")
	public String writeQstnPop(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		List<SysCodeVO> qnaCtgrList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
		request.setAttribute("qnaCtgrList", qnaCtgrList);
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		
		return "home/board/qna/qna_qstn_write";
	}
	
	/**
     * 1대1 문의 작성 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/writeAnsrMain")
	public String writeAnsrPop(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		
		BrdQnaQstnVO qstn = new BrdQnaQstnVO();
		qstn.setQnaSn(vo.getQnaSn());
		qstn.setOrgCd(orgCd);
		qstn = brdQnaService.viewQstn(qstn);
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("qstn", qstn);
		request.setAttribute("vo", vo);
		
		return "home/board/qna/qna_ansr_write";
	}
	
	/**
     * 1대1 문의 작성 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editAnsrMain")
	public String editAnsrMain(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		
		BrdQnaQstnVO qstn = new BrdQnaQstnVO();
		qstn.setQnaSn(vo.getQnaSn());
		qstn = brdQnaService.viewQstn(qstn);
		
		vo = brdQnaService.viewAnsr(vo).getReturnVO();
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("qstn", qstn);
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		
		return "home/board/qna/qna_ansr_write";
	}
	
	/**
     * 1대1 문의 작성 
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/writeQstn")
	@ResponseBody
	public AbstractResult writeQstn(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		vo.setUserNo(UserBroker.getUserNo(request));
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		vo.setLecYn("N");
		
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.writeQstn(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("문의 등록 중 오류가 발생했습니다.");
		}
		return result;
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
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"") ;
		if(userType.contains("ADMIN") || userType.contains("TEACHER")) qstn.setViewMode("admin");
		request.setAttribute("vo", qstn);
		
		BrdQnaAnsrVO ansrVO = new BrdQnaAnsrVO();
		ansrVO.setQnaSn(vo.getQnaSn());
		ansrVO = brdQnaService.viewAnsr(ansrVO).getReturnVO();
		request.setAttribute("ansrVO", ansrVO);
		
		
		
		return "/home/board/qna/qna_view";
	}
	
	/**
     * 1대1 문의 수정 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editQstnMain")
	public String editQstnMain(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		BrdQnaQstnVO qstn = brdQnaService.viewQstn(vo);
		
		List<SysCodeVO> qnaCtgrList = sysCodeMemService.getSysCodeList("CATEGORY_CD");
		request.setAttribute("qnaCtgrList", qnaCtgrList);
		
		request.setAttribute("vo", qstn);
		request.setAttribute("gubun","E");
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		
		return "/home/board/qna/qna_qstn_write";
	}
	
	/**
     * 1대1 문의 수정 
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/editQstn")
	@ResponseBody
	public AbstractResult editQstn(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.editQstn(vo);
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
     * 1대1 문의 삭제 
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
			brdQnaService.deleteQstn(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생 했습니다.");
		}
		return result;
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
	 * @Method Name : nonMemQnaPop
	 * @Method 설명 : 비회원 상담 신청 화면을 보여준다.
	 * @param - 
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="/nonMemQnaPop")
	public String nonMemQnaPop(NonMemQnaVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		
		String returnUrl = "";
		
		request.setAttribute("vo", vo);
		returnUrl = "home/board/qna/nonMem_qna_pop";
		
		return returnUrl;
	}
	
	/**
	 * 비회원 상담 신청
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add(NonMemQnaVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<NonMemQnaVO> resultVO = nonMemQnaService.addQna(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.qna.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.qna.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
}

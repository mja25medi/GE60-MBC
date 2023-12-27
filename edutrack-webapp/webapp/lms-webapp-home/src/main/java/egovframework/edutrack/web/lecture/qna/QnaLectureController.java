package egovframework.edutrack.web.lecture.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.qna.service.BrdQnaAnsrVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaQstnVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaService;
import egovframework.edutrack.modules.course.contents.service.ContentsService;

@Controller
@RequestMapping(value="/lec/qna")
public class QnaLectureController extends GenericController{

	@Autowired @Qualifier("brdQnaService")
	private BrdQnaService brdQnaService;
	
	@Autowired @Qualifier("contentsService")
	private ContentsService			contentsService;
	
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
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setCrsCreCd(crsCreCd);
		vo.setLecYn("Y");
		
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"") ;
		if(userType.contains("TEACHER")) vo.setViewMode("admin");
		ProcessResultListVO<BrdQnaQstnVO> resultList = brdQnaService.listPageing(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "home/lecture/qna/qna_main";
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
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setLecYn("Y");
		vo.setCrsCreCd(crsCreCd);
		
		boolean fileBind = true;
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"") ;
		if(userType.contains("TEACHER")) vo.setViewMode("admin");
		ProcessResultListVO<BrdQnaQstnVO> resultList = brdQnaService.listPageing(vo,fileBind);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("qnaQstnList", resultList.getReturnList());
		
		return "home/lecture/qna/qna_list_div";
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
		String crsCreCd = UserBroker.getCreCrsCd(request);
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		
		vo.setCrsCreCd(crsCreCd);
		List<BrdQnaQstnVO> parCntsCdList = brdQnaService.getParCntsList(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("parCntsCdList", parCntsCdList);
		
		return "home/lecture/qna/qstn_write";
	}
	
	/**
     * 1대1 문의 작성 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editQstnMain")
	public String editQstnMain(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		BrdQnaQstnVO qstn = brdQnaService.viewLecQstn(vo);
		
		List<BrdQnaQstnVO> parCntsCdList = brdQnaService.getParCntsList(qstn);
		
		request.setAttribute("vo", qstn);
		request.setAttribute("gubun", "E" );
		request.setAttribute("parCntsCdList", parCntsCdList);
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		
		return "home/lecture/qna/qstn_write";
	}
	
	/**
     * 1대1 문의 작성 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/writeQstnFromCnts")
	public String writeQstnFromCnts(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		
		return "home/lecture/qna/cnts_qstn_write_div";
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

		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.writeQstn(vo);
			result.setResult(1);
			result.setMessage("문의가 등록 되었습니다.");
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
		BrdQnaQstnVO qstn = brdQnaService.viewLecQstn(vo);
		
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"") ;
		if(userType.contains("TEACHER")) qstn.setViewMode("admin");
		request.setAttribute("vo", qstn);
		
		BrdQnaAnsrVO ansrVO = new BrdQnaAnsrVO();
		ansrVO.setQnaSn(vo.getQnaSn());
		ansrVO = brdQnaService.viewAnsr(ansrVO).getReturnVO();
		request.setAttribute("ansrVO", ansrVO);
		
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		
		return "/home/lecture/qna/qna_view";
	}
	
	/**
     * 1대1 문의 상세 조회 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/writeAnsrForm")
	public String writeAnsrForm(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		request.setAttribute("ansr", vo);

		return "/home/lecture/qna/ansr_write_div";
	}
	
	/**
     * 1대1 문의 상세 조회 뷰
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editAnsrForm")
	public String editAnsrForm(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		vo = brdQnaService.viewAnsr(vo).getReturnVO();
		
		request.setAttribute("ansr", vo);
		request.setAttribute("gubun", "E");

		return "/home/lecture/qna/ansr_write_div";
	}
	
	/**
     * 1대1 문의 작성 
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/editQstn")
	@ResponseBody
	public AbstractResult editQstn(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		vo.setUserNo(UserBroker.getUserNo(request));
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(UserBroker.getUserOrgCd(request));
		
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.editQstn(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("문의 등록 중 오류가 발생했습니다.");
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
	@RequestMapping(value="/removeQstn")
	@ResponseBody
	public AbstractResult removeQstn(BrdQnaQstnVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.deleteQstn(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		}
		catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생했습니다.");
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
     * 1대1 문의 답변 수정
	 * @param BrdQnaQstnVO
	 * @param HttpServletRequest
	 * @return AbstractResult
	 * @throws Exception
	 */
	@RequestMapping(value="/removeAnsr")
	@ResponseBody
	public AbstractResult removeAnsr(BrdQnaAnsrVO vo, HttpServletRequest request) throws Exception {
		AbstractResult result = new AbstractResult();
		try {
			brdQnaService.deleteAnsr(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		}
		catch(Exception e) {
			result.setResult(-1);
			result.setMessage("삭제 중 오류가 발생했습니다.");
		}
		return result;
	}
}

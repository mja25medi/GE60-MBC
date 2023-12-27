package egovframework.edutrack.web.home.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO;
import egovframework.edutrack.modules.board.faq.service.BrdFaqService;

/**
 * FAQ 액션 컨트롤
 *
 * @author MediopiaTech
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/brd/faq")
public class FaqHomeController extends GenericController {

	@Autowired @Qualifier("brdFaqService")
	private BrdFaqService brdFaqService;
	
	/**
     * @Method Name : main
     * @Method 설명 : FAQ 메인
	 * @param BrdFaqAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  bbsListType
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(BrdFaqCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		BrdFaqAtclVO brdFaqAtclVO = new BrdFaqAtclVO();
		brdFaqAtclVO.setCtgrCd(vo.getCtgrCd());
		brdFaqAtclVO.setOrgCd(vo.getOrgCd());
		brdFaqAtclVO.setPageIndex(vo.getPageIndex());
		brdFaqAtclVO.setSearchValue(vo.getSearchValue());
		
		
		ProcessResultListVO<BrdFaqCtgrVO> faqCtgrList = brdFaqService.listCtgr(vo);
		ProcessResultListVO<BrdFaqAtclVO> resultList = brdFaqService.listAtclPageing(brdFaqAtclVO, brdFaqAtclVO.getPageIndex(), 10);
		
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("faqList", resultList.getReturnList());		
		request.setAttribute("faqCtgrList", faqCtgrList.getReturnList());
		
		request.setAttribute("vo", vo);
		
		return "home/board/faq/main_faq";
	}
	
	/**
     * @Method Name : list
     * @Method 설명 : FAQ 목록 조회
	 * @param BrdFaqAtclVO 
	 * @param commandMap
	 * @param model
	 * @return  bbsListType
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(BrdFaqAtclVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		request.setAttribute("vo", vo);
		
		vo.setOrgCd(orgCd);

		ProcessResultListVO<BrdFaqAtclVO> resultList = brdFaqService.listAtclPageing(vo, vo.getPageIndex(), 10);

		request.setAttribute("faqList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		return "home/board/faq/list_faq";
	}
}

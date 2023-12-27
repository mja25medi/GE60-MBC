package egovframework.edutrack.web.lecture.bbs;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.AjaxIllegalParameterException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsAtclVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsCmntVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsService;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/bbs")
public class BbsLectureController
		extends GenericController {

	@Autowired @Qualifier("lecBbsService")
	private LecBbsService lecBbsService;

	/**
	 * 강의 게시판글관련  글 관리 메인
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		
		return listAtclMain(vo, commandMap, model, request, response);
	}

	/**
	 * 게시글 리스트를 가져온다.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listAtclMain")
	public String listAtclMain(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);
		ProcessResultListVO<LecBbsAtclVO> resultList = lecBbsService.listAtclPageing(vo);
		
	   	request.setAttribute("vo", vo);
	   	request.setAttribute("paging", "Y");
	   	request.setAttribute("pageInfo", resultList.getPageInfo());
	   	
		return "home/lecture/bbs/list_bbs_main";
	}
	
	@RequestMapping(value="/listAtcl")
	public String listAtcl(LecBbsAtclVO vo, HttpServletRequest request) throws Exception{
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);
		vo.setListScale(10);
		ProcessResultListVO<LecBbsAtclVO> resultList = lecBbsService.listAtclPageing(vo);
		
		request.setAttribute("bbsAtclList", resultList.getReturnList());
	   	request.setAttribute("pageInfo", resultList.getPageInfo());
	   	
	   	return "home/lecture/bbs/list_bbs_div";
	}

	/**
	 * 게시글 단일 조회(조회수 증가)
	 */
	@RequestMapping(value="/readAtclMain")
	public String readAtclMain(LecBbsAtclVO vo, Map commandMap, ModelMap model,
		     HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		//댓글 추가시 조회수 (false)
		String strHitsup = request.getParameter("hitsup");
		boolean hitsup;
		if("false".equals(strHitsup)){
			hitsup = false;
		}else{
			hitsup = true;
		}

		//댓글 사용 여부(부모 태이블 조회)
		LecBbsVO lecBbsVO = new LecBbsVO();
		lecBbsVO.setCrsCreCd(crsCreCd);
		lecBbsVO.setBbsCd(vo.getBbsCd());
		lecBbsVO = lecBbsService.viewInfo(lecBbsVO).getReturnVO();
		request.setAttribute("lecBbsVO", lecBbsVO);

		String searchKey = vo.getSearchKey();
		String searchValue = vo.getSearchValue();

		
		ProcessResultVO<LecBbsAtclVO> resultVO = lecBbsService.viewAtcl(vo,hitsup);
		vo = resultVO.getReturnVO();
		
		if("Y".equals(lecBbsVO.getCmntUseYn())) {
			LecBbsCmntVO cmntVO = new LecBbsCmntVO();
			cmntVO.setAtclSn(vo.getAtclSn());
			vo.setCmntList(lecBbsService.listCmnt(cmntVO));
		}
		
		vo.setCts(StringUtil.getHtmlContents(vo.getCts()));
		vo.setSearchKey(searchKey);
		vo.setSearchValue(searchValue);
		request.setAttribute("vo", vo);
		request.setAttribute("commonPaging", "Y");

		return "home/lecture/bbs/read_bbs_main";
	}

	/**
	 * 게시글 등록 폼
	 */
	@RequestMapping(value="/addAtclMain")
	public String addAtclMain(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		String returnUrl = this.getEditorType(request);

		// 댓글을 위한 상위글 파라매터가 넘어오면 기본 제목을 설정
		if(ValidationUtils.isNotNull(vo.getParAtclSn())) {
			LecBbsAtclVO parent = new LecBbsAtclVO();
			parent.setCrsCreCd(vo.getCrsCreCd());
			parent.setBbsCd(vo.getBbsCd());
			parent.setAtclSn(vo.getParAtclSn());
			parent = lecBbsService.viewAtcl(parent,false).getReturnVO();
			vo.setTitle("["+getMessage(request, "common.title.answer")+"] " + parent.getTitle());
			vo.setBbsCd(parent.getBbsCd());
			vo.setLockYn(parent.getLockYn());
			vo.setOriginRegNo(parent.getOriginRegNo());
			//VO.setAtclSn(null);
			request.setAttribute("gubun", "AR"); //댓글일때 구분코드.
		}
		request.setAttribute("vo", vo);
		return returnUrl;
	}

	/**
	 * 게시글 등록
	 */
	@RequestMapping(value="/addAtcl")
	public String addAtcl(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		vo.setCts( HtmlCleaner.cleanScript(vo.getCts()));

		if(ValidationUtils.isEmpty(vo.getOriginRegNo()) ){
			vo.setOriginRegNo(vo.getRegNo());
		}
		try {
			lecBbsService.addAtcl(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.failed"));
			return "home/lecture/bbs/write_bbs";	// 다시 편집 화면으로
		}

		setAlertMessage(request, getMessage(request,  "board.message.bbs.atcl.add.success"));

		// 목록 화면으로
		return "redirect:" + new URLBuilder("/lec/bbs/main")
			.addParameter("bbsCd", vo.getBbsCd())
			.addParameter("searchKey", vo.getSearchKey())
			.addParameter("searchValue", vo.getSearchValue())
			.addParameter("curPage", vo.getCurPage())
			.toString();

	}

	/**
	 * 게시글 수정 폼
	 */
	@RequestMapping(value="/editAtclMain")
	public String editAtclMain(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		String returnUrl = this.getEditorType(request);

		ProcessResultVO<LecBbsAtclVO> resultVO = lecBbsService.viewAtcl(vo, false);
		vo = resultVO.getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");

		return returnUrl;
	}

	/**
	 * 게시글 수정
	 */
	@RequestMapping(value="/editAtcl")
	public String editAtcl(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		vo.setCts( HtmlCleaner.cleanScript(vo.getCts()) );
		//lecBbsAtclVO.setTitle( HtmlCleaner.cleanScript(lecBbsAtclVO.getTitle()) );

		try {
			lecBbsService.editAtcl(vo);
		} catch (Exception e) {
			request.setAttribute("gubun", "E");
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.failed"));
			return "home/lecture/bbs/write_bbs";	// 다시 편집 화면으로
		}

		request.setAttribute("lecBbsAtclVO", vo);

		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.add.success"));
		// 읽기 화면으로
		return "redirect:" + new URLBuilder("/lec/bbs/main")
			.addParameter("atclSn", vo.getAtclSn())
			.addParameter("bbsCd", vo.getBbsCd())
			.addParameter("searchKey", vo.getSearchKey())
			.addParameter("searchValue", vo.getSearchValue())
			.addParameter("curPage", vo.getCurPage())
			.toString();
	}



	/**
	 * 게시글 삭제
	 */
	@RequestMapping(value="/removeAtcl")
	public String removeAtcl(LecBbsAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String classUserType = UserBroker.getClassUserType(request);


		//공지사항 게시글 댓글
		LecBbsCmntVO lecBbsCmntVO = new LecBbsCmntVO();
		lecBbsCmntVO.setCrsCreCd(vo.getCrsCreCd());
		lecBbsCmntVO.setBbsCd(vo.getBbsCd());
		lecBbsCmntVO.setAtclSn(vo.getAtclSn());

		//공지사항 게시판 글 답변이 있을경우 리턴
		LecBbsAtclVO lbaVO = lecBbsService.viewAtcl(vo).getReturnVO();

		try {
			if("TCH".equals(classUserType)){
				//-- 교수자의 경우 답변이 없는 게시글은 삭제 가능하다. 뎃글이 있어도 삭제 가능
				if(lbaVO.getReplyCnt() == 0){
					lecBbsService.removeAtcl(vo);
				}else{
					setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.failed"));
					return "redirect:" + new URLBuilder("/lec/bbs/readAtcl")
						.addParameter("atclSn", vo.getAtclSn())
						.addParameter("bbsCd", vo.getBbsCd())
						.addParameter("searchKey", vo.getSearchKey())
						.addParameter("searchValue", vo.getSearchValue())
						.addParameter("curPage", vo.getCurPage())
						.toString();
				}
			}else if("STU".equals(classUserType)){
				//-- 학습자의 경우는 답글, 댓글 모두 없어야 삭제 가능
				if(lbaVO.getReplyCnt() == 0 && lbaVO.getCmntCnt() == 0){
					lecBbsService.removeAtcl(vo);
				}else{
					setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.failed"));
					return "redirect:" + new URLBuilder("/lec/bbs/readAtcl")
						.addParameter("atclSn", vo.getAtclSn())
						.addParameter("bbsCd", vo.getBbsCd())
						.addParameter("searchKey", vo.getSearchKey())
						.addParameter("searchValue", vo.getSearchValue())
						.addParameter("curPage", vo.getCurPage())
						.toString();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request,getMessage(request, "board.message.bbs.atcl.delete.failed"));
			return "redirect:" + new URLBuilder("/lec/bbs/readAtcl")
				.addParameter("atclSn", vo.getAtclSn())
				.addParameter("bbsCd", vo.getBbsCd())
				.addParameter("searchKey", vo.getSearchKey())
				.addParameter("searchValue", vo.getSearchValue())
				.addParameter("curPage", vo.getCurPage())
				.toString();
		}

		setAlertMessage(request, getMessage(request, "board.message.bbs.atcl.delete.success"));

		// 목록 화면으로
		return "redirect:" + new URLBuilder("/lec/bbs/main")
			.addParameter("atclSn", vo.getAtclSn())
			.addParameter("bbsCd", vo.getBbsCd())
			.addParameter("searchKey", vo.getSearchKey())
			.addParameter("searchValue", vo.getSearchValue())
			.addParameter("curPage", vo.getCurPage())
			.toString();
	}

	/**
	 * 게시글 댓글  목록 조회
	 */
	@RequestMapping(value="/listCmnt")
	public String listCmnt(LecBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		ProcessResultListVO<LecBbsCmntVO> cmntList = lecBbsService.listCmntPageing(vo);
		return JsonUtil.responseJson(response, cmntList);
	}

	/**
	 * 개시판 댓글 등록 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCmnt")
	public String addCmnt(LecBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String regNm = UserBroker.getUserName(request);
		vo.setCrsCreCd(crsCreCd);
		vo.setRegNm(regNm);

		// 스크립트, 스타일 태그 제거
		vo.setCmntCts( HtmlCleaner.cleanScript(vo.getCmntCts()) );

		this.validateCmnt(vo, request);

		ProcessResultVO<LecBbsCmntVO> result = lecBbsService.addCmnt(vo);
		if(result.getResult() > 0) {
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.add.success"));
		} else {
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}



	/**
	 * 댓글의 유효성 검사.
	 * @param comment
	 * @return
	 */
	private void validateCmnt(LecBbsCmntVO comment, HttpServletRequest request) {
		if(StringUtil.isNull(comment.getCmntCts())) {
			throw new AjaxIllegalParameterException(getMessage(request, "board.message.bbs.cmnt.alert.shortcnts"));
		}

		if(StringUtil.isNull(comment.getRegNm())) {
			throw new AjaxIllegalParameterException(getMessage(request, "board.message.bbs.cmnt.alert.nouser"));
		}
	}

	/**
	 * 개시판 댓글 삭제 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removeCmnt")
	public String removeCmnt(LecBbsCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		ProcessResultVO<?> result = lecBbsService.removeCmnt(vo);
		if(result.getResult() > 0) {
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.delete.success"));
		} else {
			result.setMessage(getMessage(request, "board.message.bbs.cmnt.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	private String getEditorType(HttpServletRequest request) {
		String returnUrl = "home/lecture/bbs/write_bbs_main";
		request.setAttribute("fileupload", "Y");
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				returnUrl = "home/lecture/bbs/write_bbs_daumeditor_main";
				request.setAttribute("daumeditor", "Y");
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				returnUrl = "home/lecture/bbs/write_bbs_summernote_main";
				request.setAttribute("summernote", "Y");
			}
		}
		return returnUrl;
	}
}
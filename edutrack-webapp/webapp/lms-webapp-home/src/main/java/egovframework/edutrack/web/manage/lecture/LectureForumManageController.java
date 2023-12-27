package egovframework.edutrack.web.manage.lecture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.AjaxIllegalFormatException;
import egovframework.edutrack.comm.service.CommStatusVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumAtclVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumCmntVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumJoinUserVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumService;
import egovframework.edutrack.modules.lecture.forum.service.ForumVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/forum")
public class LectureForumManageController extends GenericController {

	@Autowired @Qualifier("forumService")
	private ForumService		forumService;										// 인터페이스 선언부

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService createCourseSubjectService;

	@Autowired @Qualifier("sysCodeService")
	private SysCodeService sysCodeService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService creCrsDeclsService;

	/**
	 * 토론 관리 메인
	 * @author twkim
	 * @date 2013. 10. 21.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/main")
	public String main( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 개설 과정 연결 과목 목록을 가져온다.
		CreateCourseSubjectVO createCourseSubjectVO = new CreateCourseSubjectVO();
		createCourseSubjectVO.setCrsCreCd(vo.getCrsCreCd());

		List<CreateCourseSubjectVO> subjectList = createCourseSubjectService.listSubject(createCourseSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("vo", vo);

		return "mng/lecture/forum/forum_ctgr_main"; //최초로딩 화면
	}


	/**
	 * 토론 목록 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/listForum")
	public String listForum( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//홈페이지 강의실에서 학습자일 경우 등록된 토론만 보여주기위해
		vo.setForumRegYn("");

		ProcessResultListVO<ForumVO> resultList =  forumService.listForum(vo); //단순 등롣된 모든 토론 목록
		request.setAttribute("forumList", resultList.getReturnList());
		return "mng/lecture/forum/forum_ctgr_list_div";
	}


	/**
	 * 토론 정보 등록 폼
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addForumPop")
	public String addForumPop( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setPeriodAfterWriteYn("N"); // 기본값 설정

		//-- 개설 과정 연결 과목 목록을 가져온다.
		CreateCourseSubjectVO createCourseSubjectVO = new CreateCourseSubjectVO();
		createCourseSubjectVO.setCrsCreCd(vo.getCrsCreCd());

		List<CreateCourseSubjectVO> subjectList = createCourseSubjectService.listSubject(createCourseSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);

		//시스템 코드 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/lecture/forum/forum_ctgr_write_pop";
	}

	/**
	 * 토론 정보 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addForum")
	public String addForum( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setForumCts(HtmlCleaner.cleanScript(vo.getForumCts()) );

		ProcessResultVO<ForumVO> resultVO = forumService.addForum(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 토론 정보 수정 폼
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForumPop")
	public String editForumPop( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//토론 정보 가져오기
		request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());

		//시스템 코드 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		request.setAttribute("gubun", "E");
		return "mng/lecture/forum/forum_ctgr_write_pop";

	}


	/**
	 * 토론 정보 수정
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForum")
	public String editForum( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setForumCts(HtmlCleaner.cleanScript(vo.getForumCts()) );

		ProcessResultVO<ForumVO> resultVO = forumService.editForum(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 토론 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/deleteForum")
	public String deleteForum( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ForumVO> resultVO = forumService.deleteForum(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론 게시글 관리 메인
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/manageForumAtclMain")
	public String manageForumAtclMain( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//토론 정보 가져오기
		request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());
		request.setAttribute("paging", "Y");
		return "mng/lecture/forum/forum_manage_atcl_main";
	}

	/**
	 * 토론 평가 관리 메인
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/manageForumRateMain")
	public String manageForumRateMain( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//토론 정보 가져오기
		request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());
		String curDateTime = DateTimeUtil.getDateTime();
		request.setAttribute("curDateTime", curDateTime);
		request.setAttribute("paging", "Y");

		return "mng/lecture/forum/forum_manage_rate_main";
	}

	/**
	 * 결과 현황
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/manageForumRsltMain")
	public String manageForumRsltMain( ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		int declsNo = StringUtil.nvl(request.getParameter("declsNo"),0);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//토론 정보 가져오기
		request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());
		ForumJoinUserVO forumJoinUserVO = new ForumJoinUserVO();
		forumJoinUserVO.setCrsCreCd(vo.getCrsCreCd());
		forumJoinUserVO.setForumSn(vo.getForumSn());
		forumJoinUserVO.setDeclsNo(declsNo);

		List<ForumJoinUserVO> userList  = forumService.listJoinUser(forumJoinUserVO).getReturnList();
		List<CommStatusVO> statusList = new ArrayList<CommStatusVO>();
		for(int i = 0; i <= 100; i++ ) {
			CommStatusVO csVO = new CommStatusVO();
			csVO.setKeyCode(i+"");
			csVO.setKeyValue(0+"");
			statusList.add(csVO);
		}
		for(ForumJoinUserVO fjuVO : userList) {
			for(CommStatusVO csVO : statusList) {
				if(Math.round(fjuVO.getScore()) == Integer.parseInt(csVO.getKeyCode())) {
					int keyValue = Integer.parseInt(csVO.getKeyValue()) + 1;
					csVO.setKeyValue(keyValue+"");
				}
			}

		}
		request.setAttribute("statusList", statusList);
		request.setAttribute("paging", "Y");
		request.setAttribute("rslt", "Y");
		
		return "mng/lecture/forum/forum_manage_rslt_main";
	}

	/**
	 * 토론 게시글 목록 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/listAtcl")
	public String listAtcl( ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<ForumAtclVO> resultList = forumService.listAtclPageing(vo);
		request.setAttribute("forumAtclList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/lecture/forum/forum_atcl_list_div";
	}

	/**
	 * 토론 게시글 등록 폼
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addAtclPop")
	public String addAtclPop( ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("gubun", "A");
		String forward = this.getEditorType(request);

		// 댓글을 위한 상위글 파라매터가 넘어오면 기본 제목을 설정
		if(ValidationUtils.isNotNull(vo.getParAtclSn())) {
			ForumAtclVO parent = new ForumAtclVO(vo.getParAtclSn());
			parent.setCrsCreCd(vo.getCrsCreCd());
			parent.setForumSn(vo.getForumSn());
			parent = forumService.viewAtcl(parent,false).getReturnVO();
			vo.setTitle("[Reply] " + parent.getTitle());
			request.setAttribute("gubun", "AR");//댓글일때 구분코드.
		}
		request.setAttribute("vo", vo);

		return forward;
	}

	/**
	 * 토론 게시글 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addAtcl")
	public String addAtcl( ForumAtclVO vo, Map commandMap, ModelMap model,
			 HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setRegNm(UserBroker.getUserName(request));

		// 스크립트, 스타일 태그 제거
		vo.setCts(HtmlCleaner.cleanScript(vo.getCts()) );

		ProcessResultVO<ForumAtclVO> resultVO = forumService.addAtcl(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론 게시글 수정 폼
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editAtclPop")
	public String editAtclPop( ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", forumService.viewAtcl(vo,false).getReturnVO());
		String forward = this.getEditorType(request);

		request.setAttribute("gubun", "E");
		return forward;
	}

	/**
	 * 토론 게시글 수정
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editAtcl")
	public String editAtcl( ForumAtclVO vo, Map commandMap, ModelMap model,
			 HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setCts(HtmlCleaner.cleanScript(vo.getCts()) );

		ProcessResultVO<ForumAtclVO> resultVO = forumService.editAtcl(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론 게시글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/removeAtcl")
	public String removeAtcl( ForumAtclVO vo, Map commandMap, ModelMap model,
			   HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = forumService.removeAtcl(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론 게시글 상세 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/readAtclPop")
	public String readAtcl( ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//게시글 상세 조회
		request.setAttribute("vo", forumService.viewAtcl(vo,true).getReturnVO());
		request.setAttribute("commonPaging", "Y");
		return "mng/lecture/forum/forum_atcl_read_pop";
	}

	/**
	 * 토론 게시글 댓글 목록 조회
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/listComment")
	public String listComment( ForumCmntVO vo, Map commandMap, ModelMap model,
									 HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<ForumCmntVO> commentList = forumService.listCmntPageing(vo, vo.getCurPage());
		return JsonUtil.responseJson(response, commentList);
	}

	/**
	 * 토론 개시글 댓글 등록
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addComment")
	public String addComment( ForumCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setRegNm(UserBroker.getUserName(request));
		// 스크립트, 스타일 태그 제거
		vo.setCmntCts( HtmlCleaner.cleanScript(vo.getCmntCts()) );

		this.validateComment(vo, getMessage(request, "lecture.message.forum.atcl.alert.validation.cnts"));

		ProcessResultVO<ForumCmntVO> resultVO = forumService.addCmnt(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.title.forum.cmnt.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.title.forum.cmnt.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 댓글의 유효성 검사.
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param comment void
	 */
	@RequestMapping(value="/validateComment")
	private void validateComment(ForumCmntVO comment, String errMsg) {
		if(StringUtil.isNull(comment.getCmntCts())) {
			throw new AjaxIllegalFormatException(errMsg);
//			throw new Exception(errMsg);
		}
	}

	/**
	 * 토론 게시판 댓글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/removeComment")
	public String removeComment( ForumCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = forumService.removeCmnt(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.title.forum.cmnt.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.title.forum.cmnt.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 학습자 목록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/listForumStudent")
	public String listForumStudent( ForumJoinUserVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setPageScale(10);
		ProcessResultListVO<ForumJoinUserVO> resultList = forumService.listJoinUserPageing(vo);
		request.setAttribute("forumJoinUserList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("forumJoinUserVO", vo);
		return "mng/lecture/forum/forum_std_list_div";
	}

	/**
	 * 점수 개별 등록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addScore")
	public String addScore( ForumJoinUserVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ForumJoinUserVO> resultVO = forumService.addJoinUser(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.rate.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.rate.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 점수 일괄 등록
	 * @author twkim
	 * @date 2013. 10. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addScoreAll")
	public String addScoreAll( ForumJoinUserVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String strStdNo = request.getParameter("strStdNo");
		String strScore = request.getParameter("strScore");

		ProcessResultVO<ForumJoinUserVO> resultVO = forumService.addJoinUserAll(vo, strStdNo, strScore);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.rate.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.rate.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}



	/**
	 * 포럼 조인
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/joinForum")
	public String joinForum( ForumVO forumVO, ForumAtclVO forumAtclVO,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(forumVO, request);

		forumAtclVO.setCrsCreCd(forumVO.getCrsCreCd()); //조회조건1
		forumAtclVO.setForumSn(forumVO.getForumSn()); //조회조건2
		//게시판의 정보를 담아온다. 덧글,답변 유무 등등

		ProcessResultVO<ForumVO> resultVO = forumService.viewForum(forumVO); //포럼 정보를 가져온다(단건)
		forumVO = resultVO.getReturnVO();

		ProcessResultListVO<ForumAtclVO> result = forumService.listAtclPageing(forumAtclVO); //해당초럼에 등록된 의견리스트를 가져온다.
		request.setAttribute("forumAtclList", result.getReturnList());//조히결과를 jsp상 리스트에 넣음
		request.setAttribute("forumVO", forumVO);//토론장
		request.setAttribute("pageInfo", result.getPageInfo());//페이징
		return "froum_read";
	}

	private String getEditorType(HttpServletRequest request) {
		String forwardUrl = "mng/lecture/forum/forum_atcl_write_pop";
		request.setAttribute("fileupload", "Y");
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/lecture/forum/forum_atcl_write_daumeditor_pop";
				request.setAttribute("daumeditor", "Y");
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/lecture/forum/forum_atcl_write_summernote_pop";
				request.setAttribute("summernote", "Y");
			}
		}
		return forwardUrl;
	}
}

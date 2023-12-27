package egovframework.edutrack.web.lecture.forum;

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
import egovframework.edutrack.comm.service.CommStatusVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumAtclVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumCmntVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumJoinUserVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumService;
import egovframework.edutrack.modules.lecture.forum.service.ForumStatusVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumVO;
import egovframework.edutrack.modules.lecture.score.service.StdScoreService;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/forum")
public class ForumLectureController
		extends GenericController {

	@Autowired @Qualifier("forumService")
	private ForumService				forumService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			codeMemService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("stdScoreService")
	private StdScoreService			stdScoreService;

	/**
	 * 토론방 관리 메인
	 * @author twkim
	 * @date 2013. 11. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/main")
	public String main(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		String stdNo = UserBroker.getStudentNo(request);

		String returnUrl = "home/lecture/forum/teacher/forum_main";
		if("STU".equals(UserBroker.getClassUserType(request))){
			returnUrl = "home/lecture/forum/forum_main";
			vo.setStdNo(stdNo);
		}else{
			vo.setForumRegYn("");
		}

		request.setAttribute("forumList", forumService.listForum(vo).getReturnList());


		StdScoreVO ssVO = new StdScoreVO();
		ssVO.setCrsCreCd(vo.getCrsCreCd());
		List<StdScoreVO> stdScoreList = stdScoreService.list(ssVO).getReturnList();
		ForumStatusVO fsVO = new ForumStatusVO();
		String myScore = "0";
		for(StdScoreVO issVO : stdScoreList) {
			if(issVO.getForumScore() < 10) {
				fsVO.setScoreZone00(fsVO.getScoreZone00() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "0";
			} else if(issVO.getForumScore() < 20) {
				fsVO.setScoreZone10(fsVO.getScoreZone10() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "10";
			} else if(issVO.getForumScore() < 30) {
				fsVO.setScoreZone20(fsVO.getScoreZone20() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "20";
			} else if(issVO.getForumScore() < 40) {
				fsVO.setScoreZone30(fsVO.getScoreZone30() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "30";
			} else if(issVO.getForumScore() < 50) {
				fsVO.setScoreZone40(fsVO.getScoreZone40() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "40";
			} else if(issVO.getForumScore() < 60) {
				fsVO.setScoreZone50(fsVO.getScoreZone50() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "50";
			} else if(issVO.getForumScore() < 70) {
				fsVO.setScoreZone60(fsVO.getScoreZone60() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "60";
			} else if(issVO.getForumScore() < 80) {
				fsVO.setScoreZone70(fsVO.getScoreZone70() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "70";
			} else if(issVO.getForumScore() < 90) {
				fsVO.setScoreZone80(fsVO.getScoreZone80() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "80";
			} else if(issVO.getForumScore() < 100) {
				fsVO.setScoreZone90(fsVO.getScoreZone90() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "90";
			} else {
				fsVO.setScoreZone100(fsVO.getScoreZone100() + 1);
				if(stdNo.equals(issVO.getStdNo())) myScore = "100";
			}
		}
		request.setAttribute("forumStatusVO", fsVO);
		request.setAttribute("myScore", myScore);
		request.setAttribute("vo", vo);
		return returnUrl;
	}

	/**
	 * 토론 등록 폼
	 * @author twkim
	 * @date 2013. 11. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addForumMain")
	public String addForumMain(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		vo.setPeriodAfterWriteYn("N"); // 기본값 설정

		//시스템 코드 가져오기
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

	   	request.setAttribute("vo", vo);

		return "home/lecture/forum/teacher/forum_write_main";
	}

	/**
	 * 토론 정보 등록
	 * @author twkim
	 * @date 2013. 11. 22.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addForum")
	public String addForum(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setForumCts(HtmlCleaner.cleanScript(vo.getForumCts()));

		try {
			 forumService.addForum(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.forum.add.failed"));
			return "redirect:"+ new URLBuilder("/lec/forum/addForumMain")
						.toString();
		}
		setAlertMessage(request, getMessage(request, "lecture.message.forum.add.success"));
		// 읽기 화면으로 타이틀 명을 클릭했을때도 같은 이벤트 발생
		return "redirect:"+ new URLBuilder("/lec/forum/main")
					.toString();
	}

	/**
	 * 토론 관리 폼
	 * @author twkim
	 * @date 2013. 12. 2.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForumMain")
	public String editForumMain(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ForumJoinUserVO forumJoinUserVO = vo.getForumJoinUserVO();

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		//토론글 관리
		String returnUrl = "home/lecture/forum/teacher/forum_manage_atcl_main";

		//토론 정보
	   	request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());

		//시스템 코드 가져오기
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

		String curDateTime = DateTimeUtil.getDateTime();
		request.setAttribute("curDateTime", curDateTime);

		//토론글 관리
		if("A".equals(vo.getGubun())){
			//토론글 목록
			ForumAtclVO forumAtclVO = new ForumAtclVO();
			forumAtclVO.setCrsCreCd(vo.getCrsCreCd());
			forumAtclVO.setForumSn(vo.getForumSn());
			forumAtclVO.setSearchKey(vo.getSearchKey());
			forumAtclVO.setSearchValue(vo.getSearchValue());
			forumAtclVO.setCurPage(vo.getCurPage());
			ProcessResultListVO<ForumAtclVO> resultVO = forumService.listAtclPageing(forumAtclVO);

			request.setAttribute("forumAtclList", resultVO.getReturnList());
		   	request.setAttribute("pageInfo", resultVO.getPageInfo());

			//searchKey , searchValue setting
			request.setAttribute("searchKey", vo.getSearchKey());
		   	request.setAttribute("searchValue", vo.getSearchValue());

		} else if("S".equals(vo.getGubun())) { //결과통계
			CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
			creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
			List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
			request.setAttribute("creCrsDeclsList", creCrsDeclsList);

			String userNm = StringUtil.nvl(request.getParameter("userNm"));
			int declsNo = StringUtil.nvl(request.getParameter("declsNo"),0);

			//평가 목록
			forumJoinUserVO.setCrsCreCd(vo.getCrsCreCd());
			forumJoinUserVO.setForumSn(vo.getForumSn());
			forumJoinUserVO.setDeclsNo(declsNo);
			forumJoinUserVO.setUserNm(userNm);

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
			returnUrl = "home/lecture/forum/teacher/forum_manage_rslt_main";
		} else {	//평가관리
			//--- 분반 목록
			CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
			creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
			List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
			request.setAttribute("creCrsDeclsList", creCrsDeclsList);

			String userNm = StringUtil.nvl(request.getParameter("userNm"));
			int declsNo = StringUtil.nvl(request.getParameter("declsNo"),0);

			//평가 목록
			forumJoinUserVO.setCrsCreCd(vo.getCrsCreCd());
			forumJoinUserVO.setForumSn(vo.getForumSn());
			forumJoinUserVO.setDeclsNo(declsNo);
			forumJoinUserVO.setUserNm(userNm);
			forumJoinUserVO.setCurPage(vo.getCurPage());
			forumJoinUserVO.setListScale(vo.getListScale());

			ProcessResultListVO<ForumJoinUserVO> resultVO  = forumService.listJoinUserPageing(forumJoinUserVO);

			request.setAttribute("forumJoinUserList", resultVO.getReturnList());
		   	request.setAttribute("pageInfo", resultVO.getPageInfo());
		   	request.setAttribute("forumJoinUserVO", forumJoinUserVO);
			request.setAttribute("declsNo", declsNo);
			request.setAttribute("userNm", userNm);
			returnUrl = "home/lecture/forum/teacher/forum_manage_relt_main";

		}
		request.setAttribute("textareaResize", "none");
		request.setAttribute("paging", "Y");
		return returnUrl;

	}

	/**
	 * 토론 정보 수정
	 * @author twkim
	 * @date 2013. 11. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForm")
	public String editForm(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setForumCts(HtmlCleaner.cleanScript(vo.getForumCts()));

		try {
			forumService.editForum(vo);
		} catch (Exception e) {
		   	request.setAttribute("gubun", "E");
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.forum.edit.failed"));

			return "redirect:"+ new URLBuilder("/lec/forum/editForumMain")
						.addParameter("forumSn", vo.getForumSn())
						.addParameter("gubun", vo.getGubun())
						.toString();
		}
		setAlertMessage(request, getMessage(request, "lecture.message.forum.edit.success"));
		return "redirect:"+ new URLBuilder("/lec/forum/editForumMain")
					.addParameter("forumSn", vo.getForumSn())
					.addParameter("gubun", vo.getGubun())
					.toString();
	}

	/**
	 * 토론 정보 삭제
	 * @author twkim
	 * @date 2013. 11. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/deleteForum")
	public String deleteForum(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		try {
			forumService.deleteForum(vo);
		} catch (Exception e) {
			e.printStackTrace();

			setAlertMessage(request, getMessage(request, "lecture.message.forum.delete.failed"));
			return "redirect:"+ new URLBuilder("/lec/forum/editFormForumMain")
						.addParameter("forumSn", vo.getForumSn())
						.addParameter("gubun", vo.getGubun())
						.toString();
		}
		setAlertMessage(request, getMessage(request, "lecture.message.forum.delete.success"));
		return "redirect:"+ new URLBuilder("/lec/forum/main")
					.toString();
	}

	/**
	 * 토론방 입장
	 * @author twkim
	 * @date 2013. 12. 9.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/joinForumMain")
	public String joinForumMain(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		vo.setStdNo(UserBroker.getStudentNo(request));
		//토론 정보
	   	request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());

		//토론글 목록
		ForumAtclVO forumAtclVO = new ForumAtclVO();
		forumAtclVO.setCrsCreCd(vo.getCrsCreCd());
		forumAtclVO.setForumSn(vo.getForumSn());
		forumAtclVO.setSearchKey(vo.getSearchKey());
		forumAtclVO.setSearchValue(vo.getSearchValue());
		forumAtclVO.setCurPage(vo.getCurPage());
		ProcessResultListVO<ForumAtclVO> resultVO = forumService.listAtclPageing(forumAtclVO);

		//searchKey , searchValue setting
		request.setAttribute("searchKey", vo.getSearchKey());
	   	request.setAttribute("searchValue", vo.getSearchValue());

		request.setAttribute("forumAtclList", resultVO.getReturnList());
	   	request.setAttribute("pageInfo", resultVO.getPageInfo());
	   	request.setAttribute("paging", "Y");


		return "home/lecture/forum/forum_join_main";

	}

	/**
	 * 토론글 등록 폼
	 * @author twkim
	 * @date 2013. 11. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addForumArticlePop")
	public String addForumArticlePop(ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		request.setAttribute("refreshYn", refreshYn);
		request.setAttribute("gubun", vo.getGubun());

		// 댓글을 위한 상위글 파라매터가 넘어오면 기본 제목을 설정
		if(ValidationUtils.isNotNull(vo.getParAtclSn())) {
			String reply = getMessage(request, "common.title.reply");
			ForumAtclVO parent = new ForumAtclVO(vo.getParAtclSn());
			parent.setCrsCreCd(vo.getCrsCreCd());
			parent.setForumSn(vo.getForumSn());
			parent = forumService.viewAtcl(parent,false).getReturnVO();
			vo.setTitle("["+reply+"] " + parent.getTitle());
		   	request.setAttribute("gubun", "AR");//댓글일때 구분코드.
		}
		//
		if("E".equals(StringUtil.nvl(vo.getGubun(),"A"))){
			//게시글 상세 조회
		   	request.setAttribute("vo", forumService.viewAtcl(vo,false).getReturnVO());
		} else {
			request.setAttribute("vo", vo);
		}

		return this.getEditorType(request);
	}

	/**
	 * 토론 글 등록
	 * @author twkim
	 * @date 2013. 11. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addForumArticle")
	public String addForumArticle(ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setRegNm(UserBroker.getUserName(request));
		vo.setCts(HtmlCleaner.cleanScript(vo.getCts()));
		ProcessResultVO<ForumAtclVO> resultVO = forumService.addAtcl(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론 글 수정
	 * @author twkim
	 * @date 2013. 11. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editForumArticle")
	public String editForumArticle(ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCts(HtmlCleaner.cleanScript(vo.getCts()));

		ProcessResultVO<ForumAtclVO> resultVO = forumService.editAtcl(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론글 상세보기
	 * @author twkim
	 * @date 2013. 11. 29.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/readForumArticlePop")
	public String readForumArticlePop(ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		String isHitup = StringUtil.nvl(request.getParameter("isHitup"),"N");
		request.setAttribute("refreshYn", refreshYn);
		/*
		boolean hitup = true;
		if("Y".equals(refreshYn)){
			hitup = false;
		}
		*/
		boolean hitup = false;
		if("Y".equals(isHitup)){
			hitup = true;
		}

		//게시글 상세 조회
	   	request.setAttribute("vo", forumService.viewAtcl(vo,hitup).getReturnVO());


		//댓글 목록 조회
		ForumCmntVO forumCmntVO = new ForumCmntVO();
		forumCmntVO.setAtclSn(vo.getAtclSn());
		ProcessResultListVO<ForumCmntVO> commentList = forumService.listCmntPageing(forumCmntVO, vo.getCurPage());

		request.setAttribute("forumCmntList", commentList.getReturnList());
	   	request.setAttribute("pageInfo", commentList.getPageInfo());
	   	request.setAttribute("paging", "Y");

		return "home/lecture/forum/forum_atcl_read_pop";
	}

	/**
	 * 댓글 등록
	 * @author twkim
	 * @date 2013. 12. 2.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addComment")
	public String addComment(ForumCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setCmntCts( HtmlCleaner.cleanScript(vo.getCmntCts()) );
		vo.setRegNm(UserBroker.getUserName(request));
		//this.validateComment(forumCmntVO);

		try {
			forumService.addCmnt(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.forum.cmnt.add.failed"));
			return "redirect:"+ new URLBuilder("/lec/forum/readForumArticlePop")
						.addParameter("forumSn", vo.getForumSn())
						.addParameter("atclSn", vo.getAtclSn())
						.addParameter("curPage", vo.getCurPage())
						.addParameter("refreshYn", "N")
						.addParameter("isHitup", "N")
						.toString();
		}

		setAlertMessage(request, getMessage(request, "lecture.message.forum.cmnt.add.success"));
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/forum/readForumArticlePop")
					.addParameter("forumSn", vo.getForumSn())
					.addParameter("atclSn", vo.getAtclSn())
					.addParameter("curPage", vo.getCurPage())
					.addParameter("refreshYn", "N")
					.addParameter("isHitup", "N")
					.toString();
	}

	/**
	 * 댓글 삭제
	 * @author twkim
	 * @date 2013. 12. 2.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/removeComment")
	public String removeComment(ForumCmntVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		try {
			forumService.removeCmnt(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.forum.cmnt.delete.failed"));
			return "redirect:"+ new URLBuilder("/lec/forum/readForumArticlePop")
						.addParameter("forumSn", vo.getForumSn())
						.addParameter("atclSn", vo.getAtclSn())
						.addParameter("curPage", vo.getCurPage())
						.addParameter("refreshYn", "N")
						.addParameter("isHitup", "N")
						.toString();
		}

		setAlertMessage(request, getMessage(request, "lecture.message.forum.cmnt.delete.success"));
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/forum/readForumArticlePop")
					.addParameter("forumSn", vo.getForumSn())
					.addParameter("atclSn", vo.getAtclSn())
					.addParameter("curPage", vo.getCurPage())
					.addParameter("refreshYn", "N")
					.addParameter("isHitup", "N")
					.toString();
	}

	/**
	 * 게시글 삭제
	 * @author twkim
	 * @date 2013. 12. 2.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/removeForumArticle")
	public String removeForumArticle(ForumAtclVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String classUserType = UserBroker.getClassUserType(request);

		ProcessResultVO<?> resultVO = forumService.removeAtcl(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.atcl.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론 점수 개별 등록
	 * @author twkim
	 * @date 2013. 12. 2.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addScore")
	public String addScore(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ForumJoinUserVO forumJoinUserVO = new ForumJoinUserVO();
		forumJoinUserVO.setCrsCreCd(vo.getCrsCreCd());
		forumJoinUserVO.setForumSn(vo.getForumSn());
		forumJoinUserVO.setStdNo(vo.getForumJoinUserVO().getStdNo());
		forumJoinUserVO.setScore(vo.getForumJoinUserVO().getScore());

		try {
			forumService.addJoinUser(forumJoinUserVO);
		} catch (Exception e) {
		   	request.setAttribute("gubun", "E");
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.forum.rate.failed"));

			return "redirect:"+ new URLBuilder("/lec/forum/editForumMain")
						.addParameter("forumSn", vo.getForumSn())
						.addParameter("gubun", vo.getGubun())
						.toString();
		}
		setAlertMessage(request, getMessage(request, "lecture.message.forum.rate.success.home"));
		return "redirect:"+ new URLBuilder("/lec/forum/editForumMain")
					.addParameter("forumSn", vo.getForumSn())
					.addParameter("gubun", vo.getGubun())
					.toString();
	}

	/**
	 * 토론 점수 일괄 저장
	 * @author twkim
	 * @date 2013. 12. 2.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addScoreAll")
	public String addScoreAll(ForumJoinUserVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String strStdNo = request.getParameter("strStdNo");
		String strScore = request.getParameter("strScore");

		try {
			forumService.addJoinUserAll(vo, strStdNo, strScore);
		} catch (Exception e) {
		   	request.setAttribute("gubun", "E");
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.forum.rate.failed"));

			return "redirect:"+ new URLBuilder("/lec/forum/editFormForumMain")
						.addParameter("forumSn", vo.getForumSn())
						.addParameter("gubun", vo.getGubun())
						.toString();
		}
		setAlertMessage(request, getMessage(request, "lecture.message.forum.rate.success.home"));
		return "redirect:"+ new URLBuilder("/lec/forum/editFormForumMain")
					.addParameter("forumSn", vo.getForumSn())
					.addParameter("gubun", vo.getGubun())
					.toString();
	}

	/**
	 * 토론 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewForumPop")
	public String viewForumPop(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		//토론글 관리
		String returnUrl = "forum_edit_atcl_teacher";

		//토론 정보
	   	request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());

		//시스템 코드 가져오기
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

		return "home/lecture/forum/forum_view_pop";
	}

	/**
	 * 토론 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormForumMain")
	public String editFormForumMain(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//시스템 코드 가져오기
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

		//토론 정보
	   	request.setAttribute("vo", forumService.viewForum(vo).getReturnVO());
	   	request.setAttribute("gubun", "E");

		return "home/lecture/forum/teacher/forum_edit_pop";
	}


	/**
	 * 토론 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editForumPop")
	public String editForumPop(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ForumVO> resultVO = forumService.editForum(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.forum.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 토론별 성적차트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/forumStatus")
	public String forumStatus(ForumVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ForumStatusVO fsVO = null;
		String stdNo = UserBroker.getStudentNo(request);
		if(vo.getForumSn() == 0 ){
			StdScoreVO ssVO = new StdScoreVO();
			ssVO.setCrsCreCd(vo.getCrsCreCd());
			List<StdScoreVO> stdScoreList = stdScoreService.list(ssVO).getReturnList();
			fsVO = new ForumStatusVO();
			String myScore = "0";
			for(StdScoreVO issVO : stdScoreList) {
				if(issVO.getForumScore() < 10) {
					fsVO.setScoreZone00(fsVO.getScoreZone00() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "0";
				} else if(issVO.getForumScore() < 20) {
					fsVO.setScoreZone10(fsVO.getScoreZone10() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "10";
				} else if(issVO.getForumScore() < 30) {
					fsVO.setScoreZone20(fsVO.getScoreZone20() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "20";
				} else if(issVO.getForumScore() < 40) {
					fsVO.setScoreZone30(fsVO.getScoreZone30() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "30";
				} else if(issVO.getForumScore() < 50) {
					fsVO.setScoreZone40(fsVO.getScoreZone40() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "40";
				} else if(issVO.getForumScore() < 60) {
					fsVO.setScoreZone50(fsVO.getScoreZone50() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "50";
				} else if(issVO.getForumScore() < 70) {
					fsVO.setScoreZone60(fsVO.getScoreZone60() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "60";
				} else if(issVO.getForumScore() < 80) {
					fsVO.setScoreZone70(fsVO.getScoreZone70() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "70";
				} else if(issVO.getForumScore() < 90) {
					fsVO.setScoreZone80(fsVO.getScoreZone80() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "80";
				} else if(issVO.getForumScore() < 100) {
					fsVO.setScoreZone90(fsVO.getScoreZone90() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "90";
				} else {
					fsVO.setScoreZone100(fsVO.getScoreZone100() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "100";
				}
			}
		} else {

			ForumJoinUserVO forumJoinUserVO = new ForumJoinUserVO();
			forumJoinUserVO.setCrsCreCd(vo.getCrsCreCd());
			forumJoinUserVO.setForumSn(vo.getForumSn());

			List<ForumJoinUserVO> userList  = forumService.listJoinUser(forumJoinUserVO).getReturnList();
			fsVO = new ForumStatusVO();
			for(ForumJoinUserVO fjuVO : userList) {
				if(fjuVO.getScore() < 10) {
					fsVO.setScoreZone00(fsVO.getScoreZone00() + 1);
				} else if(fjuVO.getScore() < 20) {
					fsVO.setScoreZone10(fsVO.getScoreZone10() + 1);
				} else if(fjuVO.getScore() < 30) {
					fsVO.setScoreZone20(fsVO.getScoreZone20() + 1);
				} else if(fjuVO.getScore() < 40) {
					fsVO.setScoreZone30(fsVO.getScoreZone30() + 1);
				} else if(fjuVO.getScore() < 50) {
					fsVO.setScoreZone40(fsVO.getScoreZone40() + 1);
				} else if(fjuVO.getScore() < 60) {
					fsVO.setScoreZone50(fsVO.getScoreZone50() + 1);
				} else if(fjuVO.getScore() < 70) {
					fsVO.setScoreZone60(fsVO.getScoreZone60() + 1);
				} else if(fjuVO.getScore() < 80) {
					fsVO.setScoreZone70(fsVO.getScoreZone70() + 1);
				} else if(fjuVO.getScore() < 90) {
					fsVO.setScoreZone80(fsVO.getScoreZone80() + 1);
				} else if(fjuVO.getScore() < 100) {
					fsVO.setScoreZone90(fsVO.getScoreZone90() + 1);
				} else {
					fsVO.setScoreZone100(fsVO.getScoreZone100() + 1);
				}
			}
		}
		//

		return JsonUtil.responseJson(response, fsVO);
	}



	private String getEditorType(HttpServletRequest request) {
		String forwardUrl = "home/lecture/forum/forum_atcl_write_pop";
	   	request.setAttribute("fileupload", "Y");
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "home/lecture/forum/forum_atcl_write_daumeditor_pop";
			   	request.setAttribute("daumeditor", "Y");
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "home/lecture/forum/forum_atcl_write_summernote_pop";
			   	request.setAttribute("summernote", "Y");
			}
		}
		return forwardUrl;
	}
}

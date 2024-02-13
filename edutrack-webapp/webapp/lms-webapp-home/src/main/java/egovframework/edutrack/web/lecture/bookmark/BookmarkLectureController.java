
package egovframework.edutrack.web.lecture.bookmark;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.RedisUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.qna.service.BrdQnaQstnVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaService;
import egovframework.edutrack.modules.course.contents.service.ContentsFileVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubConstVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.lecture.main.service.MainLectureService;
import egovframework.edutrack.modules.lecture.main.service.MainLectureVO;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsService;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import net.sf.json.JSONObject;

/**
 * 학습교재 엑센 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/bookmark")
public class BookmarkLectureController
		extends GenericController {

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService			bookmarkService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService	createCourseSubjectService;

	@Autowired @Qualifier("subjectService")
	private SubjectService				subjectService;

	@Autowired @Qualifier("contentsService")
	private ContentsService			contentsService;

	@Autowired @Qualifier("eduResultService")
	private EduResultService			eduResultService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;

	@Autowired @Qualifier("studentService")
	private StudentService				studentService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService				codeMemService;

	@Autowired @Qualifier("courseService")
	private CourseService				courseService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService				configService;

	@Autowired @Qualifier("olcCartrgService")
	private OlcCartrgService 			olcCartrgService;

	@Autowired @Qualifier("olcCntsService")
	private OlcCntsService 			olcCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgInfoService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 		clibShareMediaCntsService;

	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;

	@Autowired @Qualifier("clibShareOlcPageService")
	private ClibShareOlcPageService 		clibShareOlcPageService;

	@Autowired @Qualifier("clibMediaCntsService")
	private ClibMediaCntsService 			clibMediaCntsService;
	
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;
	
	@Autowired @Qualifier("mainLectureService")
	private MainLectureService			mainLectureService;
	
	@Autowired @Qualifier("brdQnaService")
	private BrdQnaService brdQnaService;
	
	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	createCourseTeacherService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgOrgInfoService;
	
	@Autowired @Qualifier("assignmentService")
	private AssignmentService			assignmentService;
	
	@Autowired @Qualifier("examService")
	private ExamService				examService;
	
	private final static String GPT_URL = Constants.framework.getString("framework.gpt.call.url");
	private final static String GPT_VERSION = Constants.framework.getString("framework.gpt.version");
	private final static String GPT_KEY = Constants.framework.getString("framework.gpt.api.key");
	
	/**
	 * 교재 목차 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listBookmarkMain")
	public String listBookmarkMain(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String classUserType = UserBroker.getClassUserType(request);
		String stdNo = UserBroker.getStudentNo(request);
		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO.setStdNo(stdNo);	
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		/*if("OF".equals(createCourseVO.getCreTypeCd())) {
			setAlertMessage(request, "본 과정은 오프라인 과정입니다.");
			return "redirect:/lec/main/goMenuPage?mcd=ML01000000";
		}*/
		
		MainLectureVO mainLectureVO = new MainLectureVO();
		mainLectureVO.setCrsCreCd(crsCreCd);
		mainLectureVO.setStdNo(UserBroker.getStudentNo(request));
		mainLectureVO = mainLectureService.viewCreateCourseSchedule(mainLectureVO).getReturnVO();
		
		float termDayCnt = mainLectureVO.getTermDayCnt();
		float nowDayCnt = mainLectureVO.getNowDayCnt();
		
		int prpsRatio = Math.round((nowDayCnt/termDayCnt)*100);
		request.setAttribute("prpsRatio", Math.min(100, prpsRatio));//권장 진도율
		
		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);
		
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("mainLectureVO", mainLectureVO);
		
		return "home/lecture/bookmark/bookmark_list";
	}
	
	@RequestMapping(value="/listBookmarkManageMain")
	public String listBookmarkManageMain(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		
		List<CreateOnlineSubjectVO> onlineList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("onlineSubjectList", onlineList);
		return "home/lecture/bookmark/tch_bookmark_list";
	}
	
	/**
	 * 강사 교재 목차 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listTchPrgrRatioDetailMain")
	public String listTchPrgrRatioDetailMain(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		
		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		CreateOnlineSubjectVO coVO = new CreateOnlineSubjectVO();
		coVO.setCrsCreCd(crsCreCd);
		coVO.setSbjCd(vo.getSbjCd());
		coVO = createCourseSubjectService.viewOnlineSubject(coVO).getReturnVO();
		request.setAttribute("sbjVO", coVO);
		
		List<BookmarkVO> bookmarkList = null;
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);
		bookmarkList = bookmarkService.listBookmark(vo).getReturnList();

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);
		
		long time = System.currentTimeMillis();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = date.format(new Date(time));
		request.setAttribute("nowTime", nowTime);
		
		String productDomain = Constants.PRODUCT_DOMAIN;
		UsrUserInfoVO userVo = new UsrUserInfoVO();
		userVo.setOrgCd(UserBroker.getUserOrgCd(request));
		userVo.setUserNo(UserBroker.getUserNo(request));
			
		userVo = usrUserInfoService.view(userVo);
		
		request.setAttribute("productDomain", productDomain);
		request.setAttribute("userVo", userVo);
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("bookmarkList", bookmarkList);
		request.setAttribute("userNm", vo.getUserNm());
		request.setAttribute("vo", vo);
		return "home/lecture/bookmark/tch_view_learn_main";
	}
	
	/**
	 * 강사 교재 목차 목록 조회 (단순 목록 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listTchBookmark")
	public String listTchBookmark(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String stdNo = UserBroker.getStudentNo(request);
		String classUserType = UserBroker.getClassUserType(request);

		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CreateOnlineSubjectVO cosVO = new CreateOnlineSubjectVO();
		cosVO.setCrsCreCd(createCourseVO.getCrsCreCd());
		cosVO.setStdNo(stdNo);
		List<CreateOnlineSubjectVO> sbjList = createCourseSubjectService.listOnlineSubject(cosVO).getReturnList();

		request.setAttribute("sbjList", sbjList);
			
		return "home/lecture/bookmark/tch_contents_list_div";
	}

	/**
	 * 교재 목차 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listStdPrgrRatioDetailMain")
	public String listStdPrgrRatioDetail(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		
		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		CreateOnlineSubjectVO coVO = new CreateOnlineSubjectVO();
		coVO.setCrsCreCd(crsCreCd);
		coVO.setSbjCd(vo.getSbjCd());
		coVO = createCourseSubjectService.viewOnlineSubject(coVO).getReturnVO();
		request.setAttribute("sbjVO", coVO);
		
		List<BookmarkVO> bookmarkList = null;
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);
		bookmarkList = bookmarkService.listBookmark(vo).getReturnList();

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);
		
		long time = System.currentTimeMillis();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = date.format(new Date(time));
		request.setAttribute("nowTime", nowTime);
		
		String productDomain = Constants.PRODUCT_DOMAIN;
		UsrUserInfoVO userVo = new UsrUserInfoVO();
		userVo.setOrgCd(UserBroker.getUserOrgCd(request));
		userVo.setUserNo(UserBroker.getUserNo(request));
			
		userVo = usrUserInfoService.view(userVo);
		
		request.setAttribute("xrcloud_api_key", Constants.XRCLOUD_API_KEY);	
		request.setAttribute("xrcloud_project_id", Constants.XRCLOUD_PROJECT_ID);	
		request.setAttribute("product_host_url", Constants.PRODUCT_HOST_URL);
		
		request.setAttribute("productDomain", productDomain);
		request.setAttribute("userVo", userVo);
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("bookmarkList", bookmarkList);
		request.setAttribute("userNm", vo.getUserNm());
		request.setAttribute("vo", vo);
		return "home/lecture/bookmark/view_learn_main";
	}
	

	/**
	 * 교재 목차 목록 조회 (Json 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listBookmarkJson")
	public String listBookmarkJson(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String stdNo = UserBroker.getStudentNo(request);

		vo.setStdNo(stdNo);

		return JsonUtil
			.responseJson(response, bookmarkService.listBookmark(vo));
	}

	/**
	 * 학습자의 교재 목차 목록 조회 (단순 목록 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listStuBookmark")
	public String listStuBookmark(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String stdNo = UserBroker.getStudentNo(request);
		String classUserType = UserBroker.getClassUserType(request);

		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CreateOnlineSubjectVO cosVO = new CreateOnlineSubjectVO();
		cosVO.setCrsCreCd(createCourseVO.getCrsCreCd());
		cosVO.setStdNo(stdNo);
		List<CreateOnlineSubjectVO> sbjList = createCourseSubjectService.listOnlineSubject(cosVO).getReturnList();

		request.setAttribute("sbjList", sbjList);
			
		return "home/lecture/bookmark/stu_contents_list_div";
	}

	
	/**
	 * 학습자의 교재 목차 목록 조회 (단순 목록 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listTchBookmarkPop")
	public String listTchBookmarkPop(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		if(ValidationUtils.isNotEmpty(vo.getSbjCd())) {
			String crsCreCd = UserBroker.getCreCrsCd(request);
			
			CreateCourseVO createCourseVO = new CreateCourseVO();
			createCourseVO.setCrsCreCd(crsCreCd);
			createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
			createCourseVO.setEnrlStartDttm(StringUtil.dateNumber(createCourseVO.getEnrlStartDttm()));
			createCourseVO.setEnrlEndDttm(StringUtil.dateNumber(createCourseVO.getEnrlEndDttm()));
			request.setAttribute("createCourseVO", createCourseVO);
			
			OnlineSubjectVO osVO = new OnlineSubjectVO();
			osVO.setSbjCd(vo.getSbjCd());
			ProcessResultVO<OnlineSubjectVO> resultVO = subjectService.viewOnline(osVO);
			request.setAttribute("osVO", resultVO.getReturnVO());

			ContentsVO cVO = new ContentsVO();
			cVO.setSbjCd(osVO.getSbjCd());
			cVO.setCrsCreCd(crsCreCd);
			ProcessResultListVO<ContentsVO> result = contentsService.listCreateContents(cVO);
			request.setAttribute("contentsList", result.getReturnList());
		}
		return "home/lecture/bookmark/tch_bookmark_list_div";
	}

	/**
	 * 학습자의 교재 목차 목록 조회 (단순 목록 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listStuBookmarkTree")
	public String listStuBookmarkTree(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String stdNo = UserBroker.getStudentNo(request);
		String classUserType = UserBroker.getClassUserType(request);

		String totalRatio = "0";

		if("STU".equals(classUserType)) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(stdNo);
			studentVO = studentService.viewStudentInfo(studentVO).getReturnVO();
			totalRatio = studentVO.getPrgrRatio();
		}

		vo.setStdNo(stdNo);

		CreateOnlineSubjectVO cosVO = new CreateOnlineSubjectVO();
		cosVO.setCrsCreCd(vo.getCrsCreCd());
		cosVO.setSbjCd(vo.getSbjCd());
		cosVO = createCourseSubjectService.viewOnlineSubject(cosVO).getReturnVO();
		request.setAttribute("sbjVO", cosVO);

		BookmarkVO returnVO = bookmarkService.listBookmarkTreeVO(vo).getReturnVO();
		request.setAttribute("contentsVO", returnVO);

		if("TCH".equals(classUserType)) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(vo.getCrsCreCd());
			studentVO = studentService.viewCrsRatioInfo(studentVO).getReturnVO();
			totalRatio = studentVO.getCrsRatio();
		}
		request.setAttribute("totalRatio", totalRatio);

		return "home/lecture/bookmark/stu_contents_list_tree_div";
	}

	/**
	 * 콘텐츠 프레임 창
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewContents")
	public String viewContents(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String stdNo = UserBroker.getStudentNo(request);
		String classUserType = UserBroker.getClassUserType(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setStdNo(stdNo);
		vo.setRegIp(request.getRemoteAddr());
		vo.setCrsCreCd(crsCreCd);

		String studyYnCheck = "Y";
		
		//복습여부
		String review = request.getParameter("review");
		request.setAttribute("review", review);

		//-- 콘텐츠 정보를 가져온다.
		ContentsVO contentsVO = contentsService.viewCreContents(vo.getSbjCd(), vo.getCrsCreCd(),vo.getUnitCd(), stdNo).getReturnVO();
		request.setAttribute("contentsVO", contentsVO);
		
		CreateOnlineSubjectVO coVO = new CreateOnlineSubjectVO();
		coVO.setCrsCreCd(crsCreCd);
		coVO.setSbjCd(vo.getSbjCd());
		coVO = createCourseSubjectService.viewOnlineSubject(coVO).getReturnVO();
		request.setAttribute("sbjVO", coVO);
		
		//-- 차시 가져옴
		List<BookmarkVO> bookmarkList = null;
		bookmarkList = bookmarkService.listBookmark(vo).getReturnList();
		request.setAttribute("bookmarkList", bookmarkList);
		
		//과제목록
		AssignmentVO AssVo = new AssignmentVO();
		AssVo.setCrsCreCd(crsCreCd);
		AssVo.setStdNo(stdNo);
		AssVo.setSbjCd(vo.getSbjCd());
		AssVo.setUnitCd(vo.getUnitCd());
		ProcessResultListVO<AssignmentVO> AssignmentList = assignmentService.listAssignmentStd(AssVo);
		request.setAttribute("assignmentListVO", AssignmentList.getReturnList());
		
		//시험 목록 가져오기
		ExamVO examVO = new ExamVO(); 
		examVO.setCrsCreCd(crsCreCd);
		examVO.setSbjCd(vo.getSbjCd());
		examVO.setUnitCd(vo.getUnitCd());
		request.setAttribute("examListVO", examService.listExamStd(examVO).getReturnList());

		//-- 학습자인 경우 Bookmark 정보 생성
		if("STU".equals(classUserType)) {
			ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
			try {
				resultVO = bookmarkService.viewBookmark(vo);
				if(resultVO.getReturnVO() == null) {
					bookmarkService.addBookmark(vo);
				}
			} catch (Exception ex) {
				//-- today study yn check
				BookmarkVO bmkVO = bookmarkService.viewTodayStudyYn(vo).getReturnVO();
				if("Y".equals(bmkVO.getTodayStudyYn())) {
					bookmarkService.addBookmark(vo);
				} else {
					studyYnCheck = "N";
				}
			}
			
			try {
				vo.setConnIp(request.getRemoteAddr());
				vo.setUserNo(UserBroker.getUserNo(request));
				bookmarkService.addBookmarkLog(vo); //학습자 진도 로그 인서트
			} catch (Exception ex) {
				//
			}
		}
		vo.setTodayStudyYn(studyYnCheck);		
		

		//-- 콘텐츠 정보를 Bookmark 정보와 병합.
		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("bookmarkVO", vo);
		
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(stdNo);
		studentVO = studentService.viewStudentInfo(studentVO).getReturnVO();
		
		request.setAttribute("studentVO", studentVO);
		
		if (Constants.REDIS_CHECK_YN.equals("Y") && "STU".equals(classUserType)) {
			int expireTime = 24 * 60 * 60; // 24hour
			RedisUtil.setValue(Constants.REDIS_NAMESPACE+":SID:"+UserBroker.getUserId(request)+"_CRSCRECD",
					request.getSession().getId()+studentVO.getCrsCreCd(), expireTime);
		}


		if("OLCCNTS".equals(contentsVO.getCntsTypeCd())) { //-- OLC 콘텐츠의 경우
			//-- 카트리지 정보 가져오기
			//-- OLC공유 콘텐츠 정보 가져오기
			ClibShareOlcCntsVO csocVO = new ClibShareOlcCntsVO();
			csocVO.setOrgCd(orgCd);
			csocVO.setCntsCd(vo.getUnitFilePath());
			csocVO = clibShareOlcCntsService.view(csocVO).getReturnVO();
			request.setAttribute("clibOlcCntsVO", csocVO);

			//-- OLC공유 페이지 정보 가져오기
			ClibShareOlcPageVO cspcVO = new ClibShareOlcPageVO();
			cspcVO.setOrgCd(orgCd);
			cspcVO.setCntsCd(csocVO.getCntsCd());
			List<ClibShareOlcPageVO> resultList = clibShareOlcPageService.list(cspcVO).getReturnList();

			request.setAttribute("olcPageList", resultList);

			return "home/lecture/bookmark/olc_page_main_pop";
		} else if("VOD".equals(contentsVO.getCntsTypeCd())) { //-- 미디어 콘텐츠의 경우
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();

			request.setAttribute("playerDiv", clibShareMediaCntsVO.getPlayerDiv());

			String ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFileNm());
			String fileExt = "none";
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				fileExt = "mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				fileExt = "mp4";
			}
			request.setAttribute("filePath", "/contents"+clibShareMediaCntsVO.getFilePath());
			request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
			request.setAttribute("fileExt", fileExt);
			request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
			return "home/lecture/bookmark/media_contents_view_pop";

		} else if("CDN".equals(contentsVO.getCntsTypeCd())) { //-- CDN 콘텐츠의 경우
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();
			if(clibShareMediaCntsVO != null) {

				request.setAttribute("playerDiv", clibShareMediaCntsVO.getPlayerDiv());
				
				String ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFileNm());
				String filePath = "";
				if(clibShareMediaCntsVO.getCntsTypeCd().equals("CDN")) {
					ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFilePath());
					filePath = clibShareMediaCntsVO.getFilePath();
				}
	
				String fileExt = "none";
				if(Constants.MEDIA_FILE_MP3.contains(ext)) {
					fileExt = "mp3";
				} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
					fileExt = "mp4";
				}
				request.setAttribute("filePath", filePath);
				request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
				request.setAttribute("fileExt", fileExt);
				request.setAttribute("cntsTypeCd", clibShareMediaCntsVO.getCntsTypeCd());
				request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
				request.setAttribute("flowplayer", "Y");
			}
			request.setAttribute("orgInfoVO", orgInfoVO);
			
			return "home/lecture/bookmark/media_contents_view_pop";

		} else if("MLINK".equals(contentsVO.getCntsTypeCd())) { //-- 링크타입일 경우
			
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();
			if(clibShareMediaCntsVO != null) {
				request.setAttribute("filePath", clibShareMediaCntsVO.getFilePath());
				request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
			}
			request.setAttribute("orgInfoVO", orgInfoVO);
			
			return "home/lecture/bookmark/mlink_contents_view_pop";
			
		} else if("CODING_L".equals(contentsVO.getCntsTypeCd())) { //-- 코딩강의일 경우
			
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();
			if(clibShareMediaCntsVO != null) {

				request.setAttribute("playerDiv", clibShareMediaCntsVO.getPlayerDiv());
				
				String ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFileNm());
				String filePath = "";
				if(clibShareMediaCntsVO.getCntsTypeCd().equals("CDN")) {
					ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFilePath());
					filePath = clibShareMediaCntsVO.getFilePath();
				}
	
				String fileExt = "none";
				if(Constants.MEDIA_FILE_MP3.contains(ext)) {
					fileExt = "mp3";
				} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
					fileExt = "mp4";
				}
				request.setAttribute("filePath", filePath);
				request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
				request.setAttribute("fileExt", fileExt);
				request.setAttribute("cntsTypeCd", clibShareMediaCntsVO.getCntsTypeCd());
				request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
				request.setAttribute("flowplayer", "Y");
			}
			request.setAttribute("orgInfoVO", orgInfoVO);
			
			return "home/lecture/bookmark/view_coding_learn_pop";
			
		} else if("CODING_T".equals(contentsVO.getCntsTypeCd())) { //-- 코딩실습일경우
			
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);
			
			//과제 정보
			AssignmentVO avo = new AssignmentVO();
			avo.setCrsCreCd(crsCreCd);
			avo.setAsmtSn(vo.getAsmtSn());
			avo.setStdNo(stdNo);
			avo = assignmentService.viewAssignment(avo).getReturnVO();
			request.setAttribute("returnAssignmentVO", avo);
			
			//과제 문제 리스트
			avo.setStdNo(stdNo);
			List<AssignmentSubVO> asList =assignmentService.listCodeSub(avo).getReturnList();
			request.setAttribute("assignmentSubList", asList);
			request.setAttribute("studentVO", studentVO);
			request.setAttribute("orgInfoVO", orgInfoVO);
			
			//gpt정보
			request.setAttribute("gptUrl", GPT_URL);
			request.setAttribute("gptVer", GPT_VERSION);
			request.setAttribute("gptKey", GPT_KEY);
			
			return "home/lecture/bookmark/view_coding_practice_pop";
		} else { //-- 로컬 콘텐츠의 경우
			vo = bookmarkService.viewContents(vo).getReturnVO();
			String rootUnitCd = StringUtil.split(vo.getUnitPath(),">")[0];
			List<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, rootUnitCd).getReturnList();
			request.setAttribute("contentsList", contentsList);

			return "home/lecture/bookmark/local_contents_view_pop";
		}
	}
	
	/**
	 * 학습창 문의목록
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listLecQstn")
	public String listLecQstn(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String userNo = UserBroker.getUserNo(request);
		String unitCd = (String) commandMap.get("unitCd");

		
		//--문의하기 목록
		BrdQnaQstnVO qnaVO = new BrdQnaQstnVO();
		qnaVO.setOrgCd(orgCd);
		qnaVO.setUserNo(userNo);
		qnaVO.setCrsCreCd(crsCreCd);
		qnaVO.setLecYn("Y");
		qnaVO.setUnitCd(unitCd);

		String userType = StringUtil.nvl(UserBroker.getUserType(request),"") ;
		if(userType.contains("TEACHER")) qnaVO.setViewMode("admin");
		ProcessResultListVO<BrdQnaQstnVO> qnaList = brdQnaService.qnaList(qnaVO);

		request.setAttribute("qnaQstnList", qnaList.getReturnList());
		
		return "home/lecture/bookmark/qna_list_div";
	}
	
	
	/**
	 * 페어코딩 (강사)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewPairCodingPop")
	public String viewCodingPractice(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String stdNo = vo.getStdNo();
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(crsCreCd);
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(stdNo);
		studentVO = studentService.viewStudentInfo(studentVO).getReturnVO();
		
		AssignmentVO avo = new AssignmentVO();
		avo.setCrsCreCd(ccvo.getCrsCreCd());
		avo.setAsmtSn(vo.getAsmtSn());
		avo.setStdNo(stdNo);
		avo = assignmentService.viewAssignment(avo).getReturnVO();
		request.setAttribute("returnAssignmentVO", avo);
		
		AssignmentSubVO asvo = new AssignmentSubVO();
		asvo.setCrsCreCd(crsCreCd);
		asvo.setAsmtSn(vo.getAsmtSn());
		asvo.setAsmtSubSn(vo.getAsmtSubSn());
		request.setAttribute("assignmentSubVO", assignmentService.viewSub(asvo).getReturnVO());
		
		AssignmentSubConstVO ascvo = new AssignmentSubConstVO();
		ascvo.setCrsCreCd(crsCreCd);
		ascvo.setAsmtSn(vo.getAsmtSn());
		ascvo.setAsmtSubSn(vo.getAsmtSubSn());
		request.setAttribute("listSubConst", assignmentService.listSubConst(ascvo).getReturnList());
		
		request.setAttribute("studentVO", studentVO);
		
		return "home/lecture/teacher/view_pair_coding_pop";
	}


	/**
	 * 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSubContents")
	public String listSubContents(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		vo = bookmarkService.viewContents(vo).getReturnVO();
		vo.setStdNo(UserBroker.getStudentNo(request));
		ProcessResultListVO<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, vo.getUnitCd());

		return JsonUtil.responseJson(response, contentsList);
	}

	/**
	 * 콘텐츠 타이틀
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewContentsTop")
	public String viewContentsTop(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(vo.getSbjCd());
		onsbjVO = subjectService.viewOnline(onsbjVO).getReturnVO();
		request.setAttribute("subjectVO", onsbjVO);

		return "home/lecture/bookmark/contents_top_pop";
	}

	/**
	 * 콘텐츠 메뉴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewContentsMenu")
	public String viewContentsMenu(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = bookmarkService.viewContents(vo).getReturnVO();
		String rootUnitCd = StringUtil.split(vo.getUnitPath(),">")[0];

		List<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, rootUnitCd).getReturnList();
		request.setAttribute("contentsList", contentsList);

		return "home/lecture/bookmark/contents_menu_pop";
	}

	/**
	 * 콘텐츠 바디
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewContentsMain")
	public String viewContentsMain(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("bookmarkVO", vo);

		String returnUrl = "home/lecture/bookmark/contents_viewer_redirect";
		String ext = vo.getUnitFileExt();

		if(Constants.MEDIA_FILE_MP3.contains(ext)) {
			returnUrl = "home/lecture/bookmark/contents_viewer_mp3";
		} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
			returnUrl = "home/lecture/bookmark/contents_viewer_mp4";
		} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
			returnUrl = "home/lecture/bookmark/contents_viewer_mms";
		}

		return returnUrl;
	}

	/**
	 * Olc 콘텐츠 바디
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewOlcContentsMain")
	public String viewOlcContentsMain(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		String cartrgCd = request.getParameter("cartrgCd");
		String cntsCd = request.getParameter("cntsCd");
		OlcCntsVO ocVO = new OlcCntsVO();
		ocVO.setOrgCd(orgCd);
		ocVO.setCartrgCd(cartrgCd);
		ocVO.setCntsCd(cntsCd);

		ocVO = olcCntsService.view(ocVO).getReturnVO();

		//-- 콘텐츠 목록 가져오기
//		List<OlcCntsVO> resultList = olcCntsService.list(ocVO).getReturnList();
//		int pageCount = resultList.size();
//		double ratio = 0;
//		//-- 콘텐츠 1개당 진도율을 구한다.
//		double pageRatio = Math.round((1 / pageCount * 100), 0 );
//		double gapRatio = 100 - (pageRatio * pageCount);
//		int lineCnt = 0;
//		for(OlcCntsVO socVO : resultList) {
//			if(ocVO.getCntsCd().equals(socVO.getCntsCd())) {
//				if(lineCnt == 0) {
//					ratio = pageRatio + gapRatio;
//				} else {
//					ratio = pageRatio;
//				}
//			}
//		}
//		request.setAttribute("pageRatio", ratio);

		String returnUrl = "home/lecture/bookmark/olc_contents_viewer_create_pop";
		if(!"create".equals(ocVO.getCntsDiv())) {
			String ext = FileUtil.getFileExtention(ocVO.getCntsCts());
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				returnUrl = "home/lecture/bookmark/olc_contents_viewer_mp3_pop";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				returnUrl = "home/lecture/bookmark/olc_contents_viewer_mp4_pop";
			} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
				returnUrl = "home/lecture/bookmark/olc_contents_viewer_mms_pop";
			} else {
				returnUrl = "home/lecture/bookmark/olc_contents_viewer_html_pop";
			}
		}
		request.setAttribute("olcCntsVO", ocVO);
		return returnUrl;
	}

	/**
	 * Olc 콘텐츠 바디
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewOlcPageMain")
	public String viewOlcPageMain(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String cntsCd = request.getParameter("cntsCd");
		String pageCd = request.getParameter("pageCd");

		ClibShareOlcPageVO cspcVO = new ClibShareOlcPageVO();
		cspcVO.setOrgCd(orgCd);
		cspcVO.setCntsCd(cntsCd);
		cspcVO.setPageCd(pageCd);

		String returnUrl = "home/lecture/bookmark/olc_page_viewer_create_pop";
		cspcVO = clibShareOlcPageService.view(cspcVO).getReturnVO();
		cspcVO.setPageCts(cspcVO.getPageCts().replaceAll("ClibMediaCntsManage", "BookmarkLecture"));
		cspcVO.setPageCts(cspcVO.getPageCts().replaceAll("ClibMediaCntsHome", "BookmarkLecture"));

		/*
		if(!"create".equals(cspcVO.getPageDiv())) {
			String ext = FileUtil.getFileExtention(cspcVO.getFilePath());

			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				forward = "olc_page_viewer_mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				forward = "olc_page_viewer_mp4";
			} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
				forward = "olc_page_viewer_mms";
			} else {
				forward = "olc_page_viewer_html";
			}
		}
		*/
		request.setAttribute("clibShareOlcPageVO", cspcVO);
		return returnUrl;
	}

	/**
	 * 콘텐츠 라이브러리 : 미리보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/preview")
	public String preview( BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		ClibMediaCntsVO clibMediaCntsVO = new ClibMediaCntsVO();
		clibMediaCntsVO.setCntsCd(request.getParameter("clibMediaCntsVO.cntsCd"));
		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);

		clibMediaCntsVO.setOrgCd(orgCd);
		//clibMediaCntsVO.setUserNo(userNo);
		//-- 미디어 콘텐츠 정보를 가져온다.
		clibMediaCntsVO = clibMediaCntsService.view(clibMediaCntsVO).getReturnVO();

		request.setAttribute("uldStsCd", clibMediaCntsVO.getUldStsCd());
		request.setAttribute("playerDiv", clibMediaCntsVO.getPlayerDiv());

		String ext = FileUtil.getFileExtention(clibMediaCntsVO.getFileNm());
		String fileExt = "none";
		if(Constants.MEDIA_FILE_MP3.contains(ext)) {
			fileExt = "mp3";
		} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
			fileExt = "mp4";
		}
		request.setAttribute("filePath", "/contents"+clibMediaCntsVO.getFilePath());
		request.setAttribute("fileName", clibMediaCntsVO.getFileNm());
		request.setAttribute("fileExt", fileExt);
		request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
		request.setAttribute("bookmarkVO", vo);
		return "home/lecture/bookmark/media_cnts_preview_pop";
	}

	/**
	 * 콘텐츠 바닥
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewContentsBottom")
	public String viewContentsBottom(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "home/lecture/bookmark/contents_bottom_pop";
	}

	/**
	 * 북마크 정보 조회 : 페이지 첫 입장 시
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/indexViewBookmark")
	public String indexViewBookmark(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String classUserType = UserBroker.getClassUserType(request);
		String aaa = request.getParameter("bookmarkJson");
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + aaa);

		JSONObject jsonObject = JSONObject.fromObject( aaa );
		BookmarkVO bookmarkVO = (BookmarkVO) JSONObject.toBean( jsonObject, BookmarkVO.class );

		bookmarkVO.setRegIp(request.getRemoteAddr());
		if("STU".equals(classUserType)) {
			return JsonUtil
				.responseJson(response, bookmarkService.viewBookmarkHrdLog(bookmarkVO));
		} else {
			return JsonUtil
				.responseJson(response, new ProcessResultVO<BookmarkVO>().setReturnVO(bookmarkVO));
		}
	}

	/**
	 * 북마크 정보 등록
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/indexAddBookmark")
	public String indexAddBookmark(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String aaa = request.getParameter("bookmarkJson");
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + aaa);

		JSONObject jsonObject = JSONObject.fromObject( aaa );
		BookmarkVO bookmarkVO = (BookmarkVO) JSONObject.toBean( jsonObject, BookmarkVO.class );

		bookmarkVO.setRegIp(request.getRemoteAddr());
		String returnResult = JsonUtil.responseJson(response, bookmarkService.editBookmark(bookmarkVO));
		return returnResult;
	}
	
	/**
	 * 북마크 정보 등록
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addBookmarkDetail")
	public String addBookmarkDetail(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String classUserType = UserBroker.getClassUserType(request);
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setModNo(UserBroker.getUserNo(request));
		
		if("STU".equals(classUserType)) {
			return JsonUtil
				.responseJson(response, bookmarkService.addBookmarkDetail(vo));
		} else {
			return JsonUtil
				.responseJson(response, new ProcessResultVO<BookmarkVO>().setReturnVO(vo));
		}
	}
	
	/**
	 * 북마크 정보 등록
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editDetailStudyYn")
	public String editDetailStudyYn(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String classUserType = UserBroker.getClassUserType(request);
		vo.setModNo(UserBroker.getUserNo(request));
		
		if("STU".equals(classUserType)) {
			return JsonUtil
					.responseJson(response, bookmarkService.editDetailStudyYn(vo));
		} else {
			return JsonUtil
					.responseJson(response, new ProcessResultVO<BookmarkVO>().setReturnVO(vo));
		}
	}
	
	/**
	 * 수강중인 개설과정 세션의 개설과정 정보와 비교 (중복수강 방지) 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addRedisCrs")
	public String addRedisCrs(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String classUserType = StringUtil.nvl(request.getParameter("classUserType"),UserBroker.getClassUserType(request));
		String userId 		= StringUtil.nvl(request.getParameter("userId"),UserBroker.getUserId(request));
		String sessionId 	= request.getSession().getId()+StringUtil.nvl(vo.getCrsCreCd(),UserBroker.getCreCrsCd(request));

		boolean check = true;
		
		// 테스트 계정 필터링 제외
		/*if(userId.equals("mediadmin") || userId.equals("medistu1") || userId.equals("medistu2") || userId.equals("medistu3") ||
		   userId.equals("meditch1") || userId.equals("meditch2") || userId.equals("meditch3") ) {
			check = false;
		}*/

		
		if("STU".equals(classUserType)) {
			if (Constants.REDIS_CHECK_YN.equals("Y") && check == true && !userId.equals("")) {
				String sid = RedisUtil.getValue(Constants.REDIS_NAMESPACE+":SID:"+userId+"_CRSCRECD");
				if (sid != null && !sessionId.equals(sid)) {
					vo.setGubun("N");
				}
			}
			return JsonUtil.responseJson(response, new ProcessResultVO<BookmarkVO>().setReturnVO(vo));
		} else {
			return JsonUtil.responseJson(response, new ProcessResultVO<BookmarkVO>().setReturnVO(vo));
		}
	}

	/**
	 * 수강생 진도 현황
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listStdPrgrRatioMain")
	public String listStdPrgrRatioMain(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		request.setAttribute("createCourseVO", createCourseVO);


		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));

		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(crsCreCd);
		eduResultVO.setUserNm(vo.getUserNm());
		eduResultVO.setSearchFrom(vo.getSearchFrom()); //-- 미만으로 사용
		eduResultVO.setSearchTo(vo.getSearchTo()); //-- 이상으로 사용
		eduResultVO.setSortKey(vo.getSortKey());

		eduResultVO.setCurPage(curPage);
		eduResultVO.setListScale(listScale);
		eduResultVO.setPageScale(10);
		ProcessResultListVO<EduResultVO> resultListVO = eduResultService.listResultPaging(eduResultVO);

		request.setAttribute("studentList", resultListVO.getReturnList());
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("curPage", curPage);
		request.setAttribute("listScale", listScale);
		request.setAttribute("userNm", vo.getUserNm());
		request.setAttribute("searchFrom", vo.getSearchFrom());
		request.setAttribute("searchTo", vo.getSearchTo());
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "home/lecture/bookmark/student_prgr_list";
	}

	/**
	 * 수강생 진도 현황 (교육부용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listStdPrgrRatioMoe")
	public String listStdPrgrRatioMoe(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		request.setAttribute("createCourseVO", createCourseVO);

		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));

		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(crsCreCd);
		eduResultVO.setUserNm(vo.getUserNm());
		eduResultVO.setSearchFrom(vo.getSearchFrom()); //-- 미만으로 사용
		eduResultVO.setSearchTo(vo.getSearchTo()); //-- 이상으로 사용
		eduResultVO.setGradeCd(vo.getGradeCd());
		eduResultVO.setBanCd(vo.getBanCd());
		eduResultVO.setCurPage(curPage);
		eduResultVO.setListScale(listScale);
		eduResultVO.setPageScale(10);

		ProcessResultListVO<EduResultVO> resultListVO = eduResultService.listResultPaging(eduResultVO);

		request.setAttribute("studentList", resultListVO.getReturnList());
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("curPage", curPage);
		request.setAttribute("listScale", listScale);
		request.setAttribute("userNm", vo.getUserNm());
		request.setAttribute("searchFrom", vo.getSearchFrom());
		request.setAttribute("searchTo", vo.getSearchTo());
		request.setAttribute("gradeCd", vo.getGradeCd());
		request.setAttribute("banCd", vo.getBanCd());
		return "home/lecture/bookmark/student_prgr_list_moe";
	}

	/**
	 * 교재 목차 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listStdPrgrRatioDetailMoe")
	public String listStdPrgrRatioDetailMoe(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateOnlineSubjectVO onsbjVO = new CreateOnlineSubjectVO();
		onsbjVO.setCrsCreCd(vo.getCrsCreCd());

		//-- 온라인 과정 목록 가져오기
		List<CreateOnlineSubjectVO> sbjList = createCourseSubjectService.listOnlineSubject(onsbjVO).getReturnList();
		request.setAttribute("sbjList", sbjList);
		if(StringUtil.isNull(vo.getSbjCd())) vo.setSbjCd(sbjList.get(0).getSbjCd());

		List<BookmarkVO> bookmarkList = bookmarkService.listBookmark(vo).getReturnList();
		request.setAttribute("bookmarkList", bookmarkList);
		request.setAttribute("userNm", vo.getUserNm());
		return "home/lecture/bookmark/student_prgr_list_dtl_moe";
	}

	/**
	 * 교내 학습관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainClassStudy")
	public String mainClassStudy(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		CreateOnlineSubjectVO onsbjVO = new CreateOnlineSubjectVO();
		onsbjVO.setCrsCreCd(crsCreCd);
		//-- 온라인 과정 목록 가져오기
		List<CreateOnlineSubjectVO> sbjList = createCourseSubjectService.listOnlineSubject(onsbjVO).getReturnList();
		request.setAttribute("sbjList", sbjList);

		//-- 선택된 괌고이 없는 경우 첫번째 과목을 과목 코드로 셋팅
		if(ValidationUtils.isEmpty(vo.getSbjCd())) {
			CreateOnlineSubjectVO cosVO = sbjList.get(0);
			vo.setSbjCd(cosVO.getSbjCd());
		}

		CreateOnlineSubjectVO cosVO = new CreateOnlineSubjectVO();
		cosVO.setCrsCreCd(crsCreCd);
		cosVO.setSbjCd(vo.getSbjCd());
		cosVO = createCourseSubjectService.viewOnlineSubject(cosVO).getReturnVO();
		request.setAttribute("sbjVO", cosVO);

		List<BookmarkVO> contentsList = bookmarkService.listCntsForClass(vo).getReturnList();
		request.setAttribute("contentsList", contentsList);

		return "home/lecture/bookmark/class_study_main";
	}

	/**
	 * 교내 학습관리 학습자 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listClassStudy")
	public String listClassStudy(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		BookmarkVO sbVO = new BookmarkVO();
		sbVO.setCrsCreCd(crsCreCd);
		sbVO.setSbjCd(vo.getSbjCd());
		sbVO.setUnitCd(vo.getUnitCd());
		sbVO = bookmarkService.viewContents(sbVO).getReturnVO();
		vo.setCritTm(sbVO.getCritTm());

		request.setAttribute("bookmarkVO", vo);

		String curDate = DateTimeUtil.getDateTime();
		curDate = DateTimeUtil.getDateText(1, curDate.substring(0, 8), ".");
		request.setAttribute("curDate", curDate);

		List<SysCodeVO> codeList = codeMemService.getSysCodeList("SCHL_STUDY_DIV_CD");
		request.setAttribute("codeList", codeList);

		//-- 학습자 목록을 가져온다.
		List<BookmarkVO> studentList = bookmarkService.listStdBookmark(vo).getReturnList();
		request.setAttribute("studentList", studentList);
		request.setAttribute("searchFrom", vo.getSearchFrom());
		request.setAttribute("searchTo", vo.getSearchTo());

		return "home/lecture/bookmark/class_study_list";
	}

	/**
	 * 교내 학습 등록
	 *
	 * @return  ProcessResultVO
	 */
	/*@RequestMapping(value="/addClassStudy")
	public String addClassStudy(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);

		vo.setCrsCreCd(crsCreCd);
		vo.setAprvUserNo(UserBroker.getUserNo(request));

		return JsonUtil
			.responseJson(response, bookmarkService.addClassBookmark(vo));
	}*/

	/**
	 * 교내 학습 취소
	 *
	 * @return  ProcessResultVO
	 */
	/*@RequestMapping(value="/delClassStudy")
	public String delClassStudy(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);

		vo.setCrsCreCd(crsCreCd);

		return JsonUtil
			.responseJson(response, bookmarkService.delClassBookmark(vo));
	}*/


	/**
	 * 수료증 출력 (리포트용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCertificationMulti")
	public String printCertificationMulti(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String crsCreCd = UserBroker.getCreCrsCd(request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		//-- 리포트 경로를 가져온다.
		String reportUrl = configService.getValue("REPORTSVR", "REPORTURL");
		request.setAttribute("reportUrl", reportUrl);

		String moeCrsCd = configService.getValue("MOECOURSE", "COURSE_CD");

		String reportFile = "";
		if(moeCrsCd.equals(createCourseVO.getCrsCd())) {
			//-- 교육부 과정
			reportFile = "certiCardMulti10";
		} else if ("Y".equals(createCourseVO.getUeinsYn())) {
			//-- 고용보험 환급 과정인 경우
			if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20110301")) {
				reportFile = "certiCardMulti08";
			} else {
				if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20090301")) {
					reportFile = "certiCardMulti05";
				} else {
					reportFile = "certiCardMulti02";
				}
			}
		} else if(courseVO.getCrsCtgrPath().contains("040000000")) {
			//-- 사이버 과정의 경우
			if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20110301")) {
				reportFile = "certiCardMulti09";
			} else {
				if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20090301")) {
					reportFile = "certiCardMulti06";
				} else {
					reportFile = "certiCardMulti03";
				}
			}
		} else {
			if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20110301")) {
				reportFile = "certiCardMulti07";
			} else {
				if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20090301")) {
					reportFile = "certiCardMulti04";
				} else {
					reportFile = "certiCardMulti01";
				}
			}
		}
		request.setAttribute("reportFile", reportFile);
		request.setAttribute("crsCreCd", crsCreCd);

		return "mng/student/student/print_certification_report_multi";
	}

	/**
	 * 교재 목차 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/indexGetTimeStamp")
	public String indexGetTimeStamp(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		long now = System.currentTimeMillis()/600;
		//Timestamp dateTime = new Timestamp(now);

		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		resultVO.setResult(1);
		resultVO.setMessage(Long.toString(now));

		return JsonUtil
			.responseJson(response, resultVO);
	}

	/**
	 * 교재 목차 목록 조회 (Json 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewLastStudyDttm")
	public String viewLastStudyDttm(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String stdNo = UserBroker.getStudentNo(request);

		vo.setStdNo(stdNo);

		return JsonUtil
			.responseJson(response, bookmarkService.selectLastStudyDttm(vo));
	}
	
	/**
	 * 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkDayLimit")
	public String checkDayLimit(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		resultVO = bookmarkService.checkDayLimit(vo);
		return JsonUtil.responseJson(response, resultVO);
	}
	
	
	/**
	 * 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getSbjBookmarkCnt")
	public String getSbjBookmarkCnt(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		resultVO = bookmarkService.selectSbjBookmarkCnt(vo);
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 진행단계평가 및 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getTotalBookmarkInfo")
	public String getTotalBookmarkInfo(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//심사용 예외아이디 여부 확인
		UsrLoginVO usrloginVo = new UsrLoginVO();
		String userId = UserBroker.getUserId(request);
		usrloginVo.setUserId(userId);
		String idCheck = usrUserInfoService.selectExceptionIdCheck(usrloginVo);
		
		//국비지원(산인공 연계 과정) 확인 여부
		CourseVO courseVo = new CourseVO();
		String crsCreCd = vo.getCrsCreCd();
		courseVo.setCrsCreCd(crsCreCd);
		ProcessResultVO<CourseVO> resultCourseVO = courseService.selectCrsSvcTypeCre(courseVo);
		CourseVO checkVO = resultCourseVO.getReturnVO();
		
		//북마크 모든 정보 가져 옴
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		resultVO = bookmarkService.selectBookmarkInfo(vo);
		
		BookmarkVO returnVO = resultVO.getReturnVO();
		//8차시 배수 확인
		int lec8Count = returnVO.getSbjBookmarkCnt() % 8;
		
		/* returnValueCheck
		 000 : 입과 mOTP
		 001 : 진도 mOTP
		 002 : 그냥 수강 가능 
		 003 : 일일 학습시간 초과 알림
		 004 : 진행단계 평가 응시 후 수강 가능 알림
		*/
		
		if(!(checkVO.getCrsSvcType().equals("R") && checkVO.getCrsOperMthd().equals("OF"))) {
			//국비지원(산인공 연계 과정)이며 온라인 과정인 경우
			returnVO.setReturnValueCheck("002");
		}else{
			//1. 입과이면
			if(returnVO.getSbjBookmarkCnt() == 0) {
				//심사용 예외아이디 여부 확인
				if(idCheck.equals("Y")) {
					returnVO.setReturnValueCheck("002");
				}else {
					returnVO.setReturnValueCheck("000");
				}
			}else {
				//2. 일일 학습시간 초과
				if(!returnVO.getTodayStudyYn().equals("Y")) {
					returnVO.setReturnValueCheck("003");
				}else {
					//진행단계평가  없는 경우 (미공개 포함)
					if(returnVO.getStareLecCount() == 0) {
						if(lec8Count == 0) {
							if(idCheck.equals("Y")) {
								returnVO.setReturnValueCheck("002");
							}else {
								returnVO.setReturnValueCheck("001");
							}
						}else {
							returnVO.setReturnValueCheck("002");
						}
					}else {
						//진행단계평가  있는 경우
						if(returnVO.getStareLecCount()> returnVO.getPrgrLecCount()) {
							//응시 가능 진도 수 확인
							if(lec8Count == 0) {
								if(idCheck.equals("Y")) {
									returnVO.setReturnValueCheck("002");
								}else {
									returnVO.setReturnValueCheck("001");
								}
							}else {
								returnVO.setReturnValueCheck("002");
							}
						}else {
							//진행단계평가  응시 여부 확인
							if(returnVO.getStareSemiExam() == 0) {	
								returnVO.setReturnValueCheck("004");
							}else {
								if(lec8Count == 0) {
									if(idCheck.equals("Y")) {
										returnVO.setReturnValueCheck("002");
									}else {
										returnVO.setReturnValueCheck("001");
									}
								}else {
									returnVO.setReturnValueCheck("002");
								}
							}
						}
					}
				}
			}
		}
		
		return JsonUtil.responseJson(response, returnVO);
	}
	
	/**
	 * 진행단계평가 및 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getBookmarkInfo")
	public String getBookmarkInfo(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		resultVO = bookmarkService.selectBookmarkInfo(vo);
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 학습자의 교재 목차 목록 조회 (단순 목록 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listBookmarkLogPop")
	public String listBookmarkLog(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setStdNo(UserBroker.getStudentNo(request));
		vo.setUserNo(UserBroker.getUserNo(request));

		List<BookmarkVO> unitLogList = bookmarkService.listBookmarkLog(vo).getReturnList();
		request.setAttribute("unitLogList", unitLogList);
				
		return "home/lecture/bookmark/bookmark_log_list_pop";
	}
	
	/**
	 * 코딩실습 결과
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/codingTestResultPop")
	public String codingTestResultPop(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		vo = assignmentService.selectTestResult(vo).getReturnVO();
		request.setAttribute("assignmentSendVO", vo);
		request.setAttribute("gptResult", vo.getGptResult());
		
		
		return "home/lecture/bookmark/coding_test_result";
	}
	
	/**
	 * 코딩 실습 도움요청(학생)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/reqHelp")
	public String reqHelp(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = UserBroker.getUserId(request);
		
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		
		StudentVO svo = new StudentVO();
		svo.setStdNo(vo.getStdNo());
		svo = studentService.viewStudentInfo(svo).getReturnVO();
		
		if (Constants.REDIS_CHECK_YN.equals("Y")) {
			int expireTime = 60 * 60; // 1hour
			RedisUtil.appendValue(Constants.REDIS_NAMESPACE+":SID:"+vo.getCrsCreCd(),
					vo.getSbjCd() + "_" + vo.getUnitCd() + "_" +vo.getAsmtSubSn() + "_"  +vo.getStdNo() + "_" + DateTimeUtil.getDateTime() + ",", expireTime);
		}
		resultVO.setMessage("도움을 요청하였습니다.");
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 도움요청 리스트(강사)
	 * @return
	 */
	@RequestMapping(value="/helpListPop")
	public String helpListPop(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(crsCreCd);
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		request.setAttribute("createCourseVO", ccvo);
		
		vo.setCrsCreCd(crsCreCd);
		StudentVO svo = new StudentVO();
		List<Map<String, Object>> helpList = new ArrayList<Map<String, Object>>();
		
		if (Constants.REDIS_CHECK_YN.equals("Y")) {
			String sid = RedisUtil.getValue(Constants.REDIS_NAMESPACE+":SID:"+crsCreCd);
			if(sid != null) {
				String[] idList = sid.split(",");
				for (int i=0; i<idList.length; i++) {
					String helpInfo = idList[i];
					String[] hList = helpInfo.split("_");
					ContentsVO cvo = contentsService.viewCreContents(hList[0], crsCreCd, hList[1]).getReturnVO();
					svo.setStdNo(hList[3]);
					svo = studentService.viewStudentInfo(svo).getReturnVO();
					
					Map<String, Object> reqHelp = new HashMap<String, Object>();
					reqHelp.put("unitNm", cvo.getUnitNm());
					reqHelp.put("asmtSn", cvo.getAsmtSn());
					reqHelp.put("asmtSubSn", hList[2]);
					reqHelp.put("userNm", svo.getUserNm());
					reqHelp.put("userId", svo.getUserId());
					reqHelp.put("stdNo", svo.getStdNo());
					reqHelp.put("reqTime", hList[4]);
					reqHelp.put("ideUrl", svo.getIdeUrl());
					helpList.add(reqHelp);
				}
			}
		}
		request.setAttribute("helpList", helpList);
		
		return "home/lecture/teacher/paircoding_list_pop";
	}
	
	/**
	 * Redis 데이터 조회
	 * @return
	 */
	@RequestMapping(value="/callRedis")
	public String callRedis(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
			ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
			String sid = "";
			if (Constants.REDIS_CHECK_YN.equals("Y")) {
				sid = RedisUtil.getValue(Constants.REDIS_NAMESPACE+":SID:"+vo.getCrsCreCd());
			}
			if(sid == null || sid == "") {
				resultVO.setResult(-1);
			} else {
				resultVO.setResult(1);
			}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * xrcloudCallback 콘텐츠 북마크 콜백용.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/xrcloudCallback")
	public String xrcloudCallback(@RequestBody HashMap<String, Object> map, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//{"infraUserId":"EN0000000427_CE00000314_NSC0000056_CNT000000672","roomId":"4c435491-d13a-45ec-b8f1-3390657b2b44","roomAccessType":"join","roomAccessTime":"2023-12-28T03:41:03.000Z"}
		//{"infraUserId":"EN0000000427_CE00000314_NSC0000056_CNT000000672","roomId":"4c435491-d13a-45ec-b8f1-3390657b2b44","roomAccessType":"exit","roomAccessTime":"2023-12-28T03:41:25.955Z"}
		
		org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject(map);
        
		String infraUserId = jsonObject.get("infraUserId").toString();
		String roomId = jsonObject.get("roomId").toString();
		String roomAccessType = jsonObject.get("roomAccessType").toString();
		String roomAccessTime = jsonObject.get("roomAccessTime").toString();
		
		String getConvertedDate = DateTimeUtil.getConvertedDate(roomAccessTime);
		
		String[] userKey = StringUtil.split(infraUserId,"_");
		
		String stdNo = userKey[0];
		String crsCreCd = userKey[1];
		String sbjCd = userKey[2];
		String unitCd = userKey[3];
		
		BookmarkVO iBookmarkVO = new BookmarkVO();
		iBookmarkVO.setCrsCreCd(crsCreCd);
		iBookmarkVO.setStdNo(stdNo);
		iBookmarkVO.setSbjCd(sbjCd);
		iBookmarkVO.setUnitCd(unitCd);
		
		int totalConnTime = 0;
		
		// 1. 기존의 bookmark 정보를 가져온다.
		BookmarkVO bookmarkVO = bookmarkService.viewBookmark(iBookmarkVO).getReturnVO();
		
		// 2. 콘텐츠 정보를 가져온다.
		ContentsVO contentsVO = contentsService.viewCreContents(iBookmarkVO.getSbjCd(), iBookmarkVO.getCrsCreCd(),iBookmarkVO.getUnitCd()).getReturnVO();
		
		
		// 3 bookmark 정보체크 후 등록
		if(bookmarkVO == null){ 
			
			// 3.1 콘텐츠 기준 진도시간을 가져온다.
			if(contentsVO.getCritTm() == 0){
				iBookmarkVO.setPrgrRatio(100);
			} else {
				iBookmarkVO.setPrgrRatio(0);
			}
			
			iBookmarkVO.setConnTotTm(0);
			iBookmarkVO.setSeekTime("0");
			iBookmarkVO.setRegNo("xrcloud");
			iBookmarkVO.setModNo("xrcloud");
			iBookmarkVO.setConnTm(0);
			iBookmarkVO.setRegDttm(getConvertedDate);
			iBookmarkVO.setModDttm(getConvertedDate);
			iBookmarkVO.setStudyBlockInfo(map.toString());
			iBookmarkVO.setConnCnt(1);
			
			bookmarkService.addBookmark(iBookmarkVO);
		} else { // 4. bookmark 정보체크 후 수정

			// 4.1 출석시간 계산
			int learnSec = Integer.parseInt(DateTimeUtil.getLearnSec(bookmarkVO.getRegDttm(),getConvertedDate));
			
			// 4.2 기준시간 세팅
			int critTm = contentsVO.getCritTm() * 60;
			
			// 4.3 진도율 계산 및 세팅
			double dPrgrRatio = (learnSec / (double) critTm) * 100;
			int prgrRatio = (int) dPrgrRatio;
			if(prgrRatio > 100) prgrRatio = 100;
			iBookmarkVO.setPrgrRatio(prgrRatio);
			
			// 4.4 학습시간 세팅
			iBookmarkVO.setConnTm(learnSec);
			
			// 4.5 총학습시간 세팅
			totalConnTime = bookmarkVO.getConnTotTm() + learnSec;
			iBookmarkVO.setConnTotTm(totalConnTime);
			
			iBookmarkVO.setRegNo("xrcloud");
			iBookmarkVO.setModNo("xrcloud");
			iBookmarkVO.setModDttm(getConvertedDate);
			iBookmarkVO.setStudyBlockInfo(map.toString());
			iBookmarkVO.setConnCnt(bookmarkVO.getConnCnt() + 1);
	
			bookmarkService.editBookmark(iBookmarkVO);
		}
        response.setStatus(200);
		return "ok";
	}
}
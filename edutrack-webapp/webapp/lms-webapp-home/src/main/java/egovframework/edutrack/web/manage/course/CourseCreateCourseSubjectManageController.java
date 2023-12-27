package egovframework.edutrack.web.manage.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOfflineSubjectVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.crstch.service.CrsTchService;
import egovframework.edutrack.modules.course.crstch.service.CrsTchVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/createCourse/subject")
public class CourseCreateCourseSubjectManageController extends GenericController {

	@Autowired @Qualifier("createCourseSubjectService") 
	private CreateCourseSubjectService	createCourseSubjectService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;

	@Autowired @Qualifier("courseService")
	private CourseService				courseService;
	
	@Autowired @Qualifier("contentsService")
	private ContentsService				contentsService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService				sysCodeMemService;

	@Autowired @Qualifier("subjectService")
	private SubjectService		subjectService;
	
	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	createCourseTeacherService;
	
	@Autowired @Qualifier("crsTchService")
	private CrsTchService			crsTchService;

	/**
	 * 개설 과정 과목 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/subjectMain")
	public String subjectMain( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 개설과정에 대한 정보
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 과정에 대한 정보 검색
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		
		
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("courseVO", courseVO);
		request.setAttribute("vo", vo);
		return "mng/course/createcoursesubject/subject_main";
	}

	/**
	 * 개설 과정 온라인 과목 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainOnlineSubject")
	public String mainOnlineSubject(CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("vo", vo);

		return "mjsp/course/createcoursesubject/online_subject_main";
	}

	/**
	 * 회차 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlineSubject")
	public String listOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 학습방법 코드 목록
		List<SysCodeVO> studyMthdList = sysCodeMemService.getSysCodeList("STUDY_MTHD");
		request.setAttribute("studyMthdList", studyMthdList);

		List<CreateOnlineSubjectVO> onlineList = createCourseSubjectService.listOnlineSubject(vo).getReturnList();
		
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		//회차 강사 정보 조회
		TeacherVO tvo = new TeacherVO();
		tvo.setCrsCreCd(vo.getCrsCreCd());
		List<TeacherVO> crsCreTeacher = createCourseTeacherService.listTeacher(tvo).getReturnList();
		if(!crsCreTeacher.isEmpty()) {
			tvo = createCourseTeacherService.viewTeacher(crsCreTeacher.get(0)).getReturnVO();
			request.setAttribute("teacher", tvo);
		}
		
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("onlineSubjectList", onlineList);
		return "mng/course/createcoursesubject/online_subject_list_div";
		//return JsonUtil
		//	.responseJson(response, createCourseSubjectService.listOnlineSubject(onlineSubjectVO));
	}


	/**
	 * 온라인 과목 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchFormOnlineSubjectPop")
	public String searchFormOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		SubjectCategoryVO scVO = new SubjectCategoryVO();
		scVO.setSbjCtgrTypeCd("ON");
		scVO.setOrgCd(orgCd);

		List<SubjectCategoryVO> ctgrList = subjectService.listCategory(scVO).getReturnList();

		request.setAttribute("ctgrList", ctgrList);
		request.setAttribute("vo", vo);

		return "mng/course/createcoursesubject/online_subject_search_pop";
	}

	/**
	 * 과목 검색 목록 조회
	 * 오프라인 과목을 분리하지 않고 온라인 과목테이블에 sbjType으로 구분하여 공통 사용한다.
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlineSearch")
	public String listOnlineSearch( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(vo.getCrsCreCd());
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		
		if(ccvo.getCreTypeCd().equals("ON")) {
			vo.setSbjType("ON");
		} else if (ccvo.getCreTypeCd().equals("OF")) {
			vo.setSbjType("OFF");
		}
		
		//-- 학습방법 코드 목록
		List<SysCodeVO> studyMthdList = sysCodeMemService.getSysCodeList("STUDY_MTHD");
		request.setAttribute("studyMthdList", studyMthdList);

		request.setAttribute("sbjList", createCourseSubjectService.listOnlineSearch(vo).getReturnList());
		return "mng/course/createcoursesubject/online_subject_search_list_div";
	}

	/**
	 * 개설 과정 온라인 과목 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormOnlineSubject")
	public String addFormOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/course/createcoursesubject/online_subject_write_pop";
	}

	/**
	 * 회차 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOnlineSubject")
	public String addOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson(response, createCourseSubjectService.addOnlineSubject(vo));
	}

	/**
	 * 개설 과정 온라인 과목 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormOnlineSubject")
	public String editFormOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = createCourseSubjectService.viewOnlineSubject(vo).getReturnVO();

		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/course/createcoursesubject/online_subject_write_pop";
	}

	/**
	 * 개설 과정 온라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editOnlineSubject")
	public String editOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CreateOnlineSubjectVO> resultVO = createCourseSubjectService.editOnlineSubject(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 온라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteOnlineSubject")
	public String deleteOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ContentsVO cvo = new ContentsVO();
		cvo.setCrsCreCd(vo.getCrsCreCd());
		cvo.setSbjCd(vo.getSbjCd());
		contentsService.deleteAllCreateContents(cvo);

		ProcessResultVO<CreateOnlineSubjectVO> resultVO = createCourseSubjectService.deleteOnlineSubject(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 온라인 과목 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortFormOnlineSubject")
	public String sortFormOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		commonVOProcessing(vo, request);
		

		//-- 등록된 과목의 목록을 가져온다.
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(vo).getReturnList();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("vo", vo);

		return "mng/course/createcoursesubject/online_subject_sort_pop";
	}


	/**
	 * 온라인 과목 순서 변경
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/sortOnlineSubject")
	public String sortOnlineSubject( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = createCourseSubjectService.sortOnlineSubject(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}



	/**
	 * 개설 과정 오프라인 과목 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainOfflineSubject")
	public String mainOfflineSubject( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "mng/course/createcoursesubject/offline_subject_main";
	}

	/**
	 * 개설 과정 오프라인 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOfflineSubject")
	public String listOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("LEC_KIND_CD");
		request.setAttribute("eduMthdList", codeList);

		List<CreateOfflineSubjectVO> offlineSubjectList = createCourseSubjectService.listOfflineSubject(vo).getReturnList();

		request.setAttribute("offlineSubjectList", offlineSubjectList);
		return "mng/course/createcoursesubject/offline_subject_list_div";
		//return JsonUtil
		//	.responseJson(response, createCourseSubjectService.listOfflineSubject(offlineSubjectVO));
	}

	/**
	 * 오프라인 과목 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchFormOfflineSubjectPop")
	public String searchFormOfflineSubjectPop( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		SubjectCategoryVO scVO = new SubjectCategoryVO();
		scVO.setSbjCtgrTypeCd("OF");
		scVO.setOrgCd(orgCd);

		List<SubjectCategoryVO> ctgrList = subjectService.listCategory(scVO).getReturnList();
		request.setAttribute("ctgrList", ctgrList);
		request.setAttribute("vo", vo);

		return "mng/course/createcoursesubject/offline_subject_search_pop";
	}

	/**
	 * 개설 과정 오프라인 과목 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormOfflineSubject")
	public String addFormOfflineSubject( Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {		

		request.setAttribute("gubun", "A");
		return "mng/course/createcoursesubject/offline_subject_write_pop";
	}

	/**
	 * 온라인 과목 검색 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOfflineSearch")
	public String listOfflineSearch( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("LEC_KIND_CD");
		request.setAttribute("codeList", codeList);

		request.setAttribute("sbjList", createCourseSubjectService.listOfflineSearch(vo).getReturnList());
		return "mng/course/createcoursesubject/offline_subject_search_list_div";
	}

	/**
	 * 개설 과정 오프라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOfflineSubject")
	public String addOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CreateOfflineSubjectVO> resultVO = createCourseSubjectService.addOfflineSubject(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 오프라인 과목 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormOfflineSubject")
	public String editFormOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception  {
		commonVOProcessing(vo, request);
		

		vo = createCourseSubjectService.viewOfflineSubject(vo).getReturnVO();

		request.setAttribute("offlineSubjectVO", vo);
		request.setAttribute("gubun", "E");
		return "mng/course/createcoursesubject/offline_subject_write_pop";
	}

	/**
	 * 개설 과정 오프라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editOfflineSubject")
	public String editOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CreateOfflineSubjectVO> resultVO = createCourseSubjectService.editOfflineSubject(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 오프라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteOfflineSubject")
	public String deleteOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		ProcessResultVO<CreateOfflineSubjectVO> resultVO = createCourseSubjectService.deleteOfflineSubject(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortFormOfflineSubject")
	public String sortFormOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 등록된 과목의 목록을 가져온다.
		List<CreateOfflineSubjectVO> subjectList = createCourseSubjectService.listOfflineSubject(vo).getReturnList();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("vo", vo);

		return "mng/course/createcoursesubject/offline_subject_sort_pop";
	}


	/**
	 * 오프라인 과목 순서 변경
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/sortOfflineSubject")
	public String sortOfflineSubject( CreateOfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = createCourseSubjectService.sortOfflineSubject(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교재 목차 조회
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/listContentsCreate")
	public String listSubjectContents( CreateOnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//회차 정보
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		request.setAttribute("createCourseVO", createCourseVO);
		vo = createCourseSubjectService.viewOnlineSubject(vo).getReturnVO();
		//회차 과목 정보
		request.setAttribute("createOnlineSubjectVO", vo);
		request.setAttribute("sbjType", vo.getSbjType());
		//회차 강사 정보 조회
		TeacherVO tvo = new TeacherVO();
		tvo.setCrsCreCd(vo.getCrsCreCd());
		List<TeacherVO> crsCreTeacher = createCourseTeacherService.listTeacher(tvo).getReturnList();
		for(int i=0; i<crsCreTeacher.size(); i++) {
			if(crsCreTeacher.get(i).getTchType().equals("TEACHER") && crsCreTeacher.get(i).getTchOdr() == 1) {
				request.setAttribute("teacher1", crsCreTeacher.get(i));
				continue;
			} else if (crsCreTeacher.get(i).getTchType().equals("TEACHER") && crsCreTeacher.get(i).getTchOdr() == 2) {
				request.setAttribute("teacher2", crsCreTeacher.get(i));
				continue;
			} else if (crsCreTeacher.get(i).getTchType().equals("TUTOR") && crsCreTeacher.get(i).getTchOdr() == 3) {
				request.setAttribute("tutor", crsCreTeacher.get(i));
				continue;
			} else {
				request.setAttribute("tutor", crsCreTeacher.get(i));
			}
		}
		
		//강사 전체 목록 조회
		tvo.setTchType("TEACHER");
		List<UsrUserInfoVO> TeacherList = createCourseTeacherService.listAllSearch(tvo).getReturnList();
		request.setAttribute("TeacherList", TeacherList);
		
		//튜터 정보 조회
		tvo.setTchType("TUTOR");
		List<UsrUserInfoVO> TutorList = createCourseTeacherService.listAllSearch(tvo).getReturnList();
		request.setAttribute("TutorList", TutorList);
		
		//교재 목차
		ContentsVO cntVO = new ContentsVO();
		cntVO.setCrsCreCd(vo.getCrsCreCd());
		cntVO.setSbjCd(vo.getSbjCd());
		ProcessResultListVO<ContentsVO> result = contentsService.listCreateContents(cntVO);
		request.setAttribute("contentsList", result.getReturnList());

		return "mng/course/createcoursesubject/contents_list_div";
	}
	
	/**
	 * 교재 정보 일괄 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCreateContentsList")
	public String editContentsList( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		
		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}
		//교재 정보 업데이트
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		for(int i=0; i<vo.getSubList().size(); i++) {
			if(!vo.getSubList().get(i).getClassStartTime().equals("")) {
				String classStartDttm = StringUtil.ReplaceAll(vo.getSubList().get(i).getClassStartTime(),".","").replaceAll(":", "")+"01";
				vo.getSubList().get(i).setClassStartTime(classStartDttm);
			}
			if(!vo.getSubList().get(i).getClassEndTime().equals("")) {
				String classEndDttm = StringUtil.ReplaceAll(vo.getSubList().get(i).getClassEndTime(),".","").replaceAll(":", "")+"59";
				vo.getSubList().get(i).setClassEndTime(classEndDttm);
			}
			resultVO = contentsService.editCreateContents(vo.getSubList().get(i));
		}
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교재 컨텐츠 수정 팝업(컨텐츠)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormContentsPop")
	public String editFormContentsPop( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String mediaPlayer = Constants.MEDIAPLAYER;
		request.setAttribute("mediaPlayer", mediaPlayer);
		
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String unitCd = StringUtil.nvl(request.getParameter("unitCd"));
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"));
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		resultVO = contentsService.viewCreateContents(crsCreCd, sbjCd, unitCd);
		
		request.setAttribute("vo", resultVO.getReturnVO());
		
		CreateOnlineSubjectVO cosVO = new CreateOnlineSubjectVO();
		cosVO.setSbjCd(sbjCd);
		cosVO.setCrsCreCd(crsCreCd);
		request.setAttribute("createOnlineSubjectVO", createCourseSubjectService.viewOnlineSubject(cosVO).getReturnVO());
		
		request.setAttribute("jstree", "Y");	
		
		request.setAttribute("xrcloud_api_key", Constants.XRCLOUD_API_KEY);	
		request.setAttribute("xrcloud_project_id", Constants.XRCLOUD_PROJECT_ID);	
		request.setAttribute("product_host_url", Constants.PRODUCT_HOST_URL);	
		
		return "mng/course/createcoursesubject/cre_contents_edit_pop";
	}
	
	/**
	 * 교재 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCreateContents")
	public String editCreateContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}

		ProcessResultVO<ContentsVO> resultVO = contentsService.editCreateContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교재 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCreateContents")
	public String deleteContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ContentsVO> resultVO = contentsService.deleteCreateContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	
	/**
	 * 회차 강사 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTeacher")
	public String addTeacher(TeacherVO vo, Map commandMap, ModelMap model, @RequestParam("sbjCd") String sbjCd, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<TeacherVO> resultVO = new ProcessResultVO<TeacherVO>();
		createCourseTeacherService.deleteTeacherAll(vo);
		List<TeacherVO> tchList = vo.getTeacherList();
		for(int i=0; i<tchList.size(); i++) {
			tchList.get(i).setRegNo(vo.getRegNo());
			tchList.get(i).setModNo(vo.getModNo());
			resultVO = createCourseTeacherService.addTeacher(tchList.get(i));
		}
		
		//목차 강사 일괄 세팅
		 ContentsVO cntVO = new ContentsVO(); cntVO.setCrsCreCd(vo.getCrsCreCd());
		 cntVO.setSbjCd(sbjCd);
		 ProcessResultListVO<ContentsVO> result =contentsService.listCreateContents(cntVO);
		 if (tchList.get(0).getTchType().equals("TEACHER")&&tchList.get(0).getTchOdr() == 0) {
			 cntVO.setTeacherNo(tchList.get(0).getUserNo());
			 for (int i=0; i<result.getReturnList().size(); i++) {
				 List<ContentsVO> contentsList =  result.getReturnList();
				 contentsList.get(i).setTeacherNo(tchList.get(0).getUserNo());
				 contentsService.editCreateContents(contentsList.get(i));
			 }
		 }
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 단일 목차 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCreateContents")
	public String addContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}

		ProcessResultVO<ContentsVO> resultVO = contentsService.addCreateContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 목차 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortFormCreCntsPop")
	public String sortFormCreCntsPop( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//---- 과목 분류 목록 조회
		ProcessResultListVO<ContentsVO> resultListVO = contentsService.listCreateContentsSort(vo);
		request.setAttribute("cntsList", resultListVO.getReturnList());
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/course/createcoursesubject/cre_cnts_sort_pop";
	}
	
	/**
	 * 하위 컨텐츠 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreCntsSub")
	public String listCreCntsSub( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<ContentsVO> resultVO = contentsService.listCreateContentsSub(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 컨텐츠 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortCreCnts")
	public String sortCreCnts( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = contentsService.CreateContentsSort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.category.sort.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.contents.category.sort.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}
	
}

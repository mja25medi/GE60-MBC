package egovframework.edutrack.web.manage.course;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOflnSbjService;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOflnSbjVO;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjService;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjVO;
import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/courseSubject")
public class CourseCourseSubjectManageController extends GenericController{

	@Autowired @Qualifier("courseService")
	private CourseService		courseService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;

	@Autowired @Qualifier("crsOnlnSbjService")
	private CrsOnlnSbjService	crsOnlnSbjService;

	@Autowired @Qualifier("crsOflnSbjService")
	private CrsOflnSbjService	crsOflnSbjService;

	@Autowired @Qualifier("subjectService")
	private SubjectService		subjectService;

	/**
	 * 과정 과목 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/main")
	public String main( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 과정정보
		CourseVO crsVO = new CourseVO();
		crsVO.setCrsCd(vo.getCrsCd());
		crsVO = courseService.view(crsVO).getReturnVO();

		request.setAttribute("courseVO", crsVO);
		request.setAttribute("vo", vo);
		return "mng/course/crssbj/sbj_main";
	}

	/**
	 * 과정 온라인 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlnSbj")
	public String listOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 학습방법 코드 목록
		List<SysCodeVO> studyMthdList = sysCodeMemService.getSysCodeList("STUDY_MTHD");
		request.setAttribute("studyMthdList", studyMthdList);
		request.setAttribute("crsOnlnSbjList", crsOnlnSbjService.list(vo).getReturnList());
		return "mng/course/crssbj/online_list_div";
	}

	/**
	 * 과정 과목 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchFormOnlnSbjPop")
	public String searchFormOnlnSbjPop( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("crsOnlnSbjVO", vo);
		String orgCd = UserBroker.getUserOrgCd(request);

		SubjectCategoryVO scVO = new SubjectCategoryVO();
		scVO.setSbjCtgrTypeCd("ON");
		scVO.setOrgCd(orgCd);

		List<SubjectCategoryVO> ctgrList = subjectService.listCategory(scVO).getReturnList();

		request.setAttribute("ctgrList", ctgrList);
		return "mng/course/crssbj/online_search_pop";
	}

	/**
	 * 과정 과목 검색 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlnSearch")
	public String listOnlnSearch( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//-- 과정의 정보를 가져온다.
		CourseVO cvo = new CourseVO();
		cvo.setCrsCd(vo.getCrsCd());
		ProcessResultVO<CourseVO> resultVO = courseService.view(cvo);
		if(resultVO.getReturnVO().getCrsOperMthd().equals("ON")) {
			vo.setSbjType("ON");
		} else if(resultVO.getReturnVO().getCrsOperMthd().equals("OF")) {
			vo.setSbjType("OF");
		}
		

		//-- 학습방법 코드 목록
		List<SysCodeVO> studyMthdList = sysCodeMemService.getSysCodeList("STUDY_MTHD");
		request.setAttribute("studyMthdList", studyMthdList);

		request.setAttribute("sbjList", crsOnlnSbjService.listSearch(vo).getReturnList());
		return "mng/course/crssbj/online_search_list";
	}


	/**
	 * 과정마스터 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOnlnSbj")
	public String addOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CrsOnlnSbjVO> resultVO = crsOnlnSbjService.add(vo);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 온라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editOnlnSbj")
	public String editOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = crsOnlnSbjService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 온라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/delOnlnSbj")
	public String delOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = crsOnlnSbjService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 온라인 과목 순서 변경 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sortFormOnlnSbj")
	public String sortFormOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("sbjList", crsOnlnSbjService.list(vo).getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/crssbj/online_sort";
	}

	/**
	 * 온라인 과목 순서 변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortOnlnSbj")
	public String sortOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = crsOnlnSbjService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 오프라인 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOflnSbj")
	public String listOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("LEC_KIND_CD");
		request.setAttribute("eduMthdList", codeList);

		request.setAttribute("crsOflnSbjList", crsOflnSbjService.list(vo).getReturnList());
		return "mng/course/crssbj/offline_list_div";
	}

	/**
	 * 오프라인 과목 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchFormOflnSbjPop")
	public String searchFormOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("crsOflnSbjVO", vo);
		String orgCd = UserBroker.getUserOrgCd(request);

		SubjectCategoryVO scVO = new SubjectCategoryVO();
		scVO.setSbjCtgrTypeCd("OF");
		scVO.setOrgCd(orgCd);

		List<SubjectCategoryVO> ctgrList = subjectService.listCategory(scVO).getReturnList();

		request.setAttribute("ctgrList", ctgrList);
		return "mng/course/crssbj/offline_search";
	}

	/**
	 * 오프라인 과목 검색 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOflnSearch")
	public String listOflnSearch( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("LEC_KIND_CD");
		request.setAttribute("codeList", codeList);

		request.setAttribute("sbjList", crsOflnSbjService.listSearch(vo).getReturnList());
		return "mng/course/crssbj/offline_search_list";
	}

	/**
	 * 오프라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOflnSbj")
	public String addOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CrsOflnSbjVO> resultVO = crsOflnSbjService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editOflnSbj")
	public String editOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = crsOflnSbjService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/delOflnSbj")
	public String delOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = crsOflnSbjService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 순서 변경 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sortFormOflnSbj")
	public String sortFormOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("sbjList", crsOflnSbjService.list(vo).getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/crssbj/offline_sort";
	}

	/**
	 * 오프라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortOflnSbj")
	public String sortOflnSbj( CrsOflnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = crsOflnSbjService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개강좌 온라인 과목 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/openCourseOnlnSbjPop")
	public String openCourseOnlnSbjPop( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		String orgCd = UserBroker.getUserOrgCd(request);

		SubjectCategoryVO scVO = new SubjectCategoryVO();
		scVO.setSbjCtgrTypeCd("ON");
		scVO.setOrgCd(orgCd);

		List<SubjectCategoryVO> ctgrList = subjectService.listCategory(scVO).getReturnList();

		request.setAttribute("ctgrList", ctgrList);
		return "mng/course/crssbj/open_course_online_search_pop";
	}

	/**
	 * 공개과정 온라인 과목 검색 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/openCourselistOnlnSearch")
	public String openCourselistOnlnSearch( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//-- 학습방법 코드 목록
		List<SysCodeVO> studyMthdList = sysCodeMemService.getSysCodeList("STUDY_MTHD");
		request.setAttribute("studyMthdList", studyMthdList);

		request.setAttribute("sbjList", crsOnlnSbjService.openCourseListSearch(vo).getReturnList());
		return "mng/course/crssbj/open_course_online_search_list";
	}

	/**
	 * 온라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/openCourseAddOnlnSbj")
	public String openCourseAddOnlnSbj( CrsOnlnSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CrsOnlnSbjVO> resultVO = crsOnlnSbjService.openCourseAdd(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
}

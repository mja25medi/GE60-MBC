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
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOfflineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.course.oflnsbjtch.service.OflnSbjTchService;
import egovframework.edutrack.modules.course.oflnsbjtch.service.OflnSbjTchVO;
import egovframework.edutrack.modules.course.oflnsbjtchtm.service.OflnSbjTchTmService;
import egovframework.edutrack.modules.course.oflnsbjtchtm.service.OflnSbjTchTmVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/ofln/sbjTch")
public class OflnSbjTchManageController extends GenericController {

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService		createCourseSubjectService;

	@Autowired @Qualifier("oflnSbjTchService")
	private OflnSbjTchService				oflnSbjTchService;

	@Autowired @Qualifier("oflnSbjTchTmService")
	private OflnSbjTchTmService			oflnSbjTchTmService;

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService		createCourseTeacherService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService				creCrsDeclsService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService					sysCodeMemService;

	/**
	 * 개설 과정 오프라인 과목 강사 관리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		request.setAttribute("oflnSbjTchVO", vo);
		return "mng/course/oflnsbjtch/main";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 오프라인 과목 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listSbj")
	public String listSbj( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 개설 과정의 과정 목록을 조회한다.
		CreateOfflineSubjectVO oflnSbjVO = new CreateOfflineSubjectVO();
		oflnSbjVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOfflineSubjectVO> sbjList = createCourseSubjectService.listOfflineSubject(oflnSbjVO).getReturnList();
		request.setAttribute("sbjList", sbjList);

		request.setAttribute("oflnSbjTchVO", vo);
		return  "mng/course/oflnsbjtch/list_sbj";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강사 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listTch")
	public String listTch( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정의 강사 목록을 조회한다.
		List<OflnSbjTchVO> tchList = oflnSbjTchService.list(vo).getReturnList();
		request.setAttribute("tchList", tchList);

		request.setAttribute("oflnSbjTchVO", vo);
		return "mng/course/oflnsbjtch/list_tch";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강사 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormTch")
	public String addFormTch( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 등록된 강사의 목록을 가져온다.
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setCrsCreCd(vo.getCrsCreCd());
		teacherVO.setTchType("TEACHER");
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(teacherVO).getReturnList();
		request.setAttribute("teacherList", teacherList);

		//-- 등록된 분반의 목록을 가져온다.
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> declsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("declsList", declsList);

		List<SysCodeVO> tchDivList = sysCodeMemService.getSysCodeList("TCH_DIV_CD");
		List<SysCodeVO> lecKindList = sysCodeMemService.getSysCodeList("LEC_KIND_CD");
		List<SysCodeVO> tchLvlList = sysCodeMemService.getSysCodeList("TCH_LVL_CD");
		List<SysCodeVO> tchPosList = sysCodeMemService.getSysCodeList("TCH_POS_CD");
		request.setAttribute("tchDivList", tchDivList);
		request.setAttribute("lecKindList", lecKindList);
		request.setAttribute("tchLvlList", tchLvlList);
		request.setAttribute("tchPosList", tchPosList);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/course/oflnsbjtch/write_tch";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강사 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTch")
	public String addTch( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		return JsonUtil
			.responseJson(response, oflnSbjTchService.add(vo));
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강사 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormTch")
	public String editFormTch( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = oflnSbjTchService.view(vo).getReturnVO();

		List<SysCodeVO> tchDivList = sysCodeMemService.getSysCodeList("TCH_DIV_CD");
		List<SysCodeVO> lecKindList = sysCodeMemService.getSysCodeList("LEC_KIND_CD");
		List<SysCodeVO> tchLvlList = sysCodeMemService.getSysCodeList("TCH_LVL_CD");
		List<SysCodeVO> tchPosList = sysCodeMemService.getSysCodeList("TCH_POS_CD");
		request.setAttribute("tchDivList", tchDivList);
		request.setAttribute("lecKindList", lecKindList);
		request.setAttribute("tchLvlList", tchLvlList);
		request.setAttribute("tchPosList", tchPosList);

		request.setAttribute("gubun", "E");
		request.setAttribute("oflnSbjTchVO", vo);
		
		return "mng/course/oflnsbjtch/write_tch";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강사 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editTch")
	public String editTch( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		return JsonUtil
			.responseJson( response, oflnSbjTchService.edit(vo));
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강사 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeTch")
	public String removeTch( OflnSbjTchVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
			.responseJson( response, oflnSbjTchService.remove(vo));
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강의 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listTime")
	public String listTime( OflnSbjTchTmVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		//-- 개설 과정 강사의 강의 목록을 조회한다.
		List<OflnSbjTchTmVO> timeList = oflnSbjTchTmService.list(vo).getReturnList();
		request.setAttribute("timeList", timeList);

		request.setAttribute("oflnSbjTchTmVO", vo);
		return "mng/course/oflnsbjtch/list_time";
	}


	/**
	 * 개설 과정 오프라인 과목 관리 - 강의 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormTime")
	public String addFormTime( OflnSbjTchTmVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("CLASS_VIEW_CD");
		request.setAttribute("codeList", codeList);

		List<String> hourList = new ArrayList<String>();
		for(int i = 0 ; i < 24; i++) {
			String hour = "";
			if(i < 10) hour = "0"+i;
			else hour = Integer.toString(i);
			hourList.add(hour);
		}
		request.setAttribute("hourList", hourList);

		List<String> minList = new ArrayList<String>();
		for(int i = 0 ; i < 60; i++) {
			String min = "";
			if(i < 10) min = "0"+i;
			else min = Integer.toString(i);
			minList.add(min);
		}
		request.setAttribute("minList", minList);

		vo.setViewYn("N");//-- 기본은 모니터링 허용안함.

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/course/oflnsbjtch/write_time";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강의 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addTime")
	public String addTime( OflnSbjTchTmVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setStartDt(StringUtil.ReplaceAll(vo.getStartDt(),".",""));
		vo.setEndDt(StringUtil.ReplaceAll(vo.getEndDt(),".",""));

		return JsonUtil
			.responseJson( response, oflnSbjTchTmService.add(vo));
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강의 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormTime")
	public String editFormTime( OflnSbjTchTmVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("CLASS_VIEW_CD");
		request.setAttribute("codeList", codeList);

		List<String> hourList = new ArrayList<String>();
		for(int i = 0 ; i < 24; i++) {
			String hour = "";
			if(i < 10) hour = "0"+i;
			else hour = Integer.toString(i);
			hourList.add(hour);
		}
		request.setAttribute("hourList", hourList);

		List<String> minList = new ArrayList<String>();
		for(int i = 0 ; i < 60; i++) {
			String min = "";
			if(i < 10) min = "0"+i;
			else min = Integer.toString(i);
			minList.add(min);
		}
		request.setAttribute("minList", minList);

		vo = oflnSbjTchTmService.view(vo).getReturnVO();


		request.setAttribute("gubun", "E");
		request.setAttribute("oflnSbjTchTmVO", vo);
		return "mng/course/oflnsbjtch/write_time";
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강의 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editTime")
	public String editTime( OflnSbjTchTmVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setStartDt(StringUtil.ReplaceAll(vo.getStartDt(),".",""));
		vo.setEndDt(StringUtil.ReplaceAll(vo.getEndDt(),".",""));

		return JsonUtil
			.responseJson( response, oflnSbjTchTmService.edit(vo));
	}

	/**
	 * 개설 과정 오프라인 과목 관리 - 강의 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeTime")
	public String removeTime( OflnSbjTchTmVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setStartDt(StringUtil.ReplaceAll(vo.getStartDt(),".",""));
		vo.setEndDt(StringUtil.ReplaceAll(vo.getEndDt(),".",""));

		return JsonUtil
			.responseJson( response, oflnSbjTchTmService.remove(vo));
	}
}

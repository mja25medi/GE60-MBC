package egovframework.edutrack.web.manage.stats;

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
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusService;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduTeamStatusVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/stats/eduCourse")
public class StatsEduCourseManageController extends GenericController {

	@Autowired @Qualifier("logEduCourseStatusService")
	private LogEduCourseStatusService		logEduCourseStatusService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			sysCodeMemService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService				sysCfgService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;
	
	@Autowired @Qualifier("courseService")
	private CourseService			courseService;
	
	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService 	usrDeptInfoService;
	
	@Autowired @Qualifier("subjectService")
	private SubjectService 	subjectService;
	
	@Autowired @Qualifier("eduResultService")
	private EduResultService			eduResultService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService		logPrivateInfoService;



	/**
	 * 분류별 과정 운영 현황
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseStatusMain")
	public String courseStatusMain(LogEduCourseStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CourseVO courseVO = new CourseVO();
		courseVO.setSortKey("CRS_YEAR_TERM_DESC");
		courseVO.setCrsCd(vo.getCrsCd());
		List<CourseVO> courseList = courseService.list(courseVO).getReturnList();
		request.setAttribute("courseList", courseList);
		
		UsrDeptInfoVO udiVO = new UsrDeptInfoVO();
		udiVO.setDeptCd(vo.getDeptCd());
		List<UsrDeptInfoVO> deptList = usrDeptInfoService.list(udiVO).getReturnList();
		request.setAttribute("deptList", deptList);
		
		OnlineSubjectVO osVO = new OnlineSubjectVO();
		osVO.setSbjCd(vo.getSbjCd());
		List<OnlineSubjectVO> subjectList =  subjectService.listOnline(osVO).getReturnList();
		request.setAttribute("paging", "Y");
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("vo", vo);
		return "mng/stats/educourse/status_main";
	}

	/**
	 * 분류별 과정 운영 현황
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseStatus")
	public String listCourseStatus(LogEduStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		LogEduCourseStatusVO eduCourseStatusVO = vo.getLogEduCourseStatusVO();

		String orgCd = UserBroker.getUserOrgCd(request);
		String mngType = UserBroker.getMngType(request);
		String userNo = UserBroker.getUserNo(request);
		eduCourseStatusVO.setOrgCd(orgCd);
		
		ProcessResultListVO<LogEduCourseStatusVO> resultList;

		if (mngType.contains("DEPTMNG")) {
			String deptCd = logEduCourseStatusService.getDeptCd(userNo);
			eduCourseStatusVO.setDeptCd(deptCd);
			resultList = logEduCourseStatusService.listCourseStatusDeptMngPageing(eduCourseStatusVO,vo.getCurPage(),Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
		}else {
			resultList = logEduCourseStatusService.listCourseStatusPageing(eduCourseStatusVO,vo.getCurPage(),Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
		}
			
	//	ProcessResultListVO<LogEduCourseStatusVO> resultList = logEduCourseStatusService.listCourseStatus(eduCourseStatusVO);
		ProcessResultListVO<LogEduCourseStatusVO> deptList = logEduCourseStatusService.deptStatusList(eduCourseStatusVO);
		
		request.setAttribute("eduCourseStatusList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("eduCourseStatusVO", eduCourseStatusVO);
		request.setAttribute("deptList", deptList.getReturnList());
		request.setAttribute("vo", vo);

		return "mng/stats/educourse/status_list_div";
	}

	/**
	 * 과정 총괄 실적표 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response-
	 * @return
	 */
	@RequestMapping(value="/courseResultMain")
	public String courseResultMain(LogEduStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		LogEduTeamStatusVO eduTeamStatusVO = vo.getLogEduTeamStatusVO();
		LogEduCourseStatusVO eduCourseStatusVO = vo.getLogEduCourseStatusVO();

		String curYear = DateTimeUtil.getYear();
		//-- 시작년도 가져오기
		SysCfgVO configVO = new SysCfgVO();
		configVO.setCfgCtgrCd("SYSTEM");
		configVO.setCfgCd("START_YEAR");
		configVO = sysCfgService.viewCfg(configVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10); i >= Integer.parseInt(configVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("curYear", curYear);
		request.setAttribute("yearList", yearList);
		request.setAttribute("eduTeamStatusVO", eduTeamStatusVO);
		request.setAttribute("eduCourseStatusVO", eduCourseStatusVO);

		return "mng/stats/educourse/result_main";
	}

	/**
	 * 과정 총괄 실적표 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseResult")
	public String listCourseResult(LogEduStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		LogEduTeamStatusVO eduTeamStatusVO = vo.getLogEduTeamStatusVO();
		LogEduCourseStatusVO eduCourseStatusVO = vo.getLogEduCourseStatusVO();
		String orgCd = UserBroker.getUserOrgCd(request);
		eduCourseStatusVO.setCreYear(eduTeamStatusVO.getCreYear());
		eduCourseStatusVO.setOrgCd(orgCd);
		List<LogEduCourseStatusVO> eduCourseStatusList = logEduCourseStatusService.listCourseResult(eduCourseStatusVO).getReturnList();

		request.setAttribute("eduCourseStatusList", eduCourseStatusList);
		request.setAttribute("eduCourseStatusVO", eduCourseStatusVO);
		request.setAttribute("eduTeamStatusVO", eduTeamStatusVO);
		request.setAttribute("eduCourseStatusVO", eduCourseStatusVO);
		return "mng/stats/educourse/result_list_div";
	}

	/**
	 * 분류별 과정 운영 현황 엑셀
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listExcelDownload")
	public String listExcelDownload(LogEduCourseStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgNm = UserBroker.getUserOrgNm(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		/*String condition = getMessage(request, "common.title.search.condition")+": "+vo.getCreYear()+getMessage(request, "common.title.year");
		if(ValidationUtils.isNotEmpty(vo.getCreMonth())) condition += " "+vo.getCreMonth()+getMessage(request, "common.title.month");
		if(ValidationUtils.isNotEmpty(vo.getSearchValue())) {
			condition += " (";
			if("crsNm".equals(vo.getSearchKey())) condition += getMessage(request, "course.title.course.name");
			else condition += getMessage(request, "course.title.createcourse.name");
			condition += ")";
		}
		condition +="  , "+getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");
*/
		String condition = getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");
		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add(getMessage(request, "course.title.createcourse.status.manage"));
		titleList.add(getMessage(request, "course.title.createcourse.creterm"));
		titleList.add(getMessage(request, "course.title.createcourse.name"));
		titleList.add(getMessage(request, "course.title.createcourse.duration.edu"));
		titleList.add(getMessage(request, "user.title.user.dept.dept.name"));
		titleList.add(getMessage(request, "log.title.course.status.enrollcnt"));
		titleList.add(getMessage(request, "log.title.course.status.completecnt"));
		titleList.add(getMessage(request, "log.title.course.status.noncompletecnt"));
		titleList.add(getMessage(request, "log.title.course.status.completepercent"));
		titleList.add(condition);

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("통계/모니터링 : 과정 운영 현황");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=EduCourseStatusList.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		logEduCourseStatusService.listExcelDownload(vo, orgNm, titleList, response.getOutputStream());

		return null;
	}

	/**
	 * 과정 총괄 실적표 엑셀다운
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listExcelDownloadResult")
	public String listExcelDownloadResult(LogEduCourseStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgNm = UserBroker.getUserOrgNm(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		String condition = getMessage(request, "common.title.search.condition")+": "+vo.getCreYear()+getMessage(request, "common.title.year");
		if(ValidationUtils.isNotEmpty(vo.getSearchValue())) {
			condition += " (";
			if("crsNm".equals(vo.getSearchKey())) condition += getMessage(request, "course.title.course.name");
			else condition += getMessage(request, "course.title.createcourse.name");
			condition += ")";
		}
		condition +="  , "+getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");

		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add(getMessage(request, "course.title.createcourse.result"));
		titleList.add(getMessage(request, "course.title.createcourse.creyear"));
		titleList.add(getMessage(request, "course.title.course.name"));
		titleList.add(getMessage(request, "course.title.course.edumthd"));
		titleList.add(getMessage(request, "course.title.course.crstype"));
		titleList.add(getMessage(request, "log.title.course.result.plan"));
		titleList.add(getMessage(request, "log.title.course.result.performance"));
		titleList.add(getMessage(request, "log.title.course.result.ratio"));
		titleList.add(getMessage(request, "log.title.course.result.yearcnt"));
		titleList.add(getMessage(request, "log.title.course.result.proceeds"));
		titleList.add(getMessage(request, "log.title.course.result.cnt"));
		titleList.add(getMessage(request, "log.title.course.result.user"));
		titleList.add(getMessage(request, "log.title.course.status.totalcnt"));
		titleList.add(getMessage(request, "course.title.createcourse.method.online"));
		titleList.add(getMessage(request, "course.title.createcourse.method.offline"));
		titleList.add(getMessage(request, "course.title.createcourse.method.blended"));
		titleList.add(getMessage(request, "course.title.createcourse.type.regular"));
		titleList.add(getMessage(request, "course.title.createcourse.type.sangsi"));
		titleList.add(condition);

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("통계/모니터링 : 과정 총괄 현황");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=EduCourseResultList.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		logEduCourseStatusService.listExcelDownloadResult(vo, orgNm, titleList, response.getOutputStream());

		return null;
	}
	
	/**
	 * 상세정보 엑셀 다운로드
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listExcelDownloadStdResult")
	public String listExcelDownloadStdResult(LogEduCourseStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		String condition = getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");

		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add("학습관리 상세 정보");
		titleList.add("번호");
		titleList.add(getMessage(request, "course.title.createcourse.creterm"));
		titleList.add(getMessage(request, "course.title.course.name"));
		titleList.add("기업");
		titleList.add(getMessage(request, "course.title.createcourse.duration.study"));
		titleList.add(getMessage(request, "user.title.login.userid"));
		titleList.add(getMessage(request, "user.title.userinfo.name"));
		titleList.add(getMessage(request, "student.title.student.decls"));
		titleList.add(getMessage(request, "student.title.result.study.ratio"));
		titleList.add(getMessage(request, "student.title.result.ratio.exam"));
		titleList.add("진행단계평가");
		titleList.add(getMessage(request, "student.title.result.ratio.asmt"));
		titleList.add(getMessage(request, "student.title.result.totalscore"));
		titleList.add("수료여부");
		titleList.add(condition);

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc(vo.getCrsNm()+"기수 :학습관리 - 상세정보 리스트 엑셀 출력");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);
		
		//-- 개인 정보 조회 로그를 저장한다.
		LogPrivateInfoInqLogVO privateInfoLogVO = new LogPrivateInfoInqLogVO();
		privateInfoLogVO.setOrgCd(UserBroker.getUserOrgCd(request));
		privateInfoLogVO.setMenuCd(UserBroker.getMenuCode(request));
		privateInfoLogVO.setDivCd("EXCEL");
		privateInfoLogVO.setUserNo(UserBroker.getUserNo(request));
		privateInfoLogVO.setUserNm(UserBroker.getUserName(request));
		privateInfoLogVO.setInqCts(vo.getCrsNm()+"\n"+vo.toString());
		logPrivateInfoService.add(privateInfoLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=EduCourseStdResultList.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		logEduCourseStatusService.listStdResultExcelDownload(vo, titleList, response.getOutputStream());

		return null;
	}
}

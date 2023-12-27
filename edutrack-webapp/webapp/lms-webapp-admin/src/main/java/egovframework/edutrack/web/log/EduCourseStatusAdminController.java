package egovframework.edutrack.web.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.course.service.impl.CourseMapper;
import egovframework.edutrack.modules.log.connect.service.ConnectLogVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusService;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduStatusVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduTeamStatusVO;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogService;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/eduCourseStatus")
public class EduCourseStatusAdminController  extends GenericController {
	
	/** service */
	@Autowired @Qualifier("logEduCourseStatusService")
	private LogEduCourseStatusService		logEduCourseStatusService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService			sysCfgService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService orgOrgInfoService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService		logPrnLogService;
	
	@Resource(name="courseMapper")
	private CourseMapper 		courseMapper;
	

	/**
	 * 분류별 과정 운영 현황
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
		@RequestMapping(value="/mainCourseStatusMain")
		public String mainCourseStatusMain(LogEduCourseStatusVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request) throws Exception {
			commonVOProcessing(vo, request);

			String curYear = DateTimeUtil.getYear();
			//-- 시작년도 가져오기
			SysCfgVO sysCfgVO = new SysCfgVO();
			sysCfgVO.setCfgCtgrCd("SYSTEM");
			sysCfgVO.setCfgCd("START_YEAR");
			sysCfgVO = sysCfgService.viewCfg(sysCfgVO);
			List<String> yearList = new ArrayList<String>();
			for(int i= Integer.parseInt(curYear,10); i >= Integer.parseInt(sysCfgVO.getCfgVal(),10); i--) {
				yearList.add(Integer.toString(i));
			}
			//-- 현재의 년도를 Attribute에 세팅한다.
			request.setAttribute("curYear", curYear);
			request.setAttribute("yearList", yearList);

			OrgOrgInfoVO OrgOrgInfoVO = new OrgOrgInfoVO();
			OrgOrgInfoVO.setUseYn("Y");
			List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(OrgOrgInfoVO).getReturnList();
			request.setAttribute("orgInfoList", orgInfoList);
			request.setAttribute("eduCourseStatusVO", vo);

			return "log/educourse/status_main";
		}

		/**
		 * 분류별 과정 운영 현황
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value="/listCourseStatus")
		public String listCourseStatusMain(LogEduStatusVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request,HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			LogEduTeamStatusVO eduTeamStatusVO = vo.getLogEduTeamStatusVO();
			LogEduCourseStatusVO eduCourseStatusVO = vo.getLogEduCourseStatusVO();

			eduCourseStatusVO.setCreYear(eduTeamStatusVO.getCreYear());

			List<LogEduCourseStatusVO> eduCourseStatusList = logEduCourseStatusService.listCourseStatus(eduCourseStatusVO).getReturnList();
			request.setAttribute("eduCourseStatusList", eduCourseStatusList);
			request.setAttribute("eduCourseStatusVO", eduCourseStatusVO);
			request.setAttribute("vo", vo);

			return "log/educourse/status_list_div";
		}
		
		@ResponseBody
		@RequestMapping(value="/listCourse")
		public ProcessResultListVO<CourseVO> listCourse(CourseVO vo, HttpServletRequest request,HttpServletResponse response) throws Exception {

			ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<>();
			try {
				List<CourseVO> crsList = courseMapper.listForCourseStatus(vo);
				resultList.setReturnList(crsList);
				resultList.setResult(1);
			} catch(Exception e) {
				e.printStackTrace();
				resultList.setResult(-1);
			}
			return resultList;
		}
		
		/**
		 * 과정 총괄 실적표 메인
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response-
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value="/mainCourseResultMain")
		public String mainCourseResultMain(LogEduCourseStatusVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request,HttpServletResponse response) throws Exception {

			commonVOProcessing(vo, request);

			String curYear = DateTimeUtil.getYear();
			//-- 시작년도 가져오기
			SysCfgVO sysCfgVO = new SysCfgVO();
			sysCfgVO.setCfgCtgrCd("SYSTEM");
			sysCfgVO.setCfgCd("START_YEAR");
			sysCfgVO = sysCfgService.viewCfg(sysCfgVO);
			List<String> yearList = new ArrayList<String>();
			for(int i= Integer.parseInt(curYear,10); i >= Integer.parseInt(sysCfgVO.getCfgVal(),10); i--) {
				yearList.add(Integer.toString(i));
			}
			//-- 현재의 년도를 Attribute에 세팅한다.
			request.setAttribute("curYear", curYear);
			request.setAttribute("yearList", yearList);

			OrgOrgInfoVO OrgOrgInfoVO = new OrgOrgInfoVO();
			OrgOrgInfoVO.setUseYn("Y");
			List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(OrgOrgInfoVO).getReturnList();
			request.setAttribute("orgInfoList", orgInfoList);
			request.setAttribute("eduCourseStatusVO", vo);
			return "log/educourse/result_main";
		}

		/**
		 * 과정 총괄 실적표 목록
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value="/listCourseResult")
		public String listCourseResult(LogEduStatusVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request,HttpServletResponse response) throws Exception {

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
			return "log/educourse/result_list_div";
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

			String condition = getMessage(request, "common.title.search.condition")+": "+vo.getCreYear()+getMessage(request, "common.title.year");
			if(ValidationUtils.isNotEmpty(vo.getSearchValue())) {
				condition += " (";
				if("crsNm".equals(vo.getSearchKey())) condition += getMessage(request, "course.title.course.name");
				else condition += getMessage(request, "course.title.createcourse.name");
				condition += ")";
			}
			condition +="  , "+getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");

			ArrayList<String> titleList = new ArrayList<String>();
			titleList.add(getMessage(request, "course.title.createcourse.status"));
			titleList.add(getMessage(request, "course.title.createcourse.creyear"));
			titleList.add(getMessage(request, "course.title.createcourse.creterm"));
			titleList.add(getMessage(request, "course.title.course.name"));
			titleList.add(getMessage(request, "course.title.createcourse.name"));
			titleList.add(getMessage(request, "course.title.createcourse.duration.edu"));
			titleList.add(getMessage(request, "log.title.course.status.enrollcnt"));
			titleList.add(getMessage(request, "log.title.course.status.completecnt"));
			titleList.add(getMessage(request, "log.title.course.status.noncompletecnt"));
			titleList.add(getMessage(request, "log.title.course.status.totalcnt"));
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

			logEduCourseStatusService.listExcelDownload(vo, "", titleList, response.getOutputStream());


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



	}

	
	
	
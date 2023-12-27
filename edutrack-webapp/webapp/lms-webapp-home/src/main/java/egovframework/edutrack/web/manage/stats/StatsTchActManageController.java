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

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusDetailVO;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusService;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/stats/tchAct")
public class StatsTchActManageController extends GenericController {

	@Autowired @Qualifier("logTchActStatusService")
	private LogTchActStatusService 	logTchActStatusService;

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService 	logPrivateInfoService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;
    /**
	 * 강사활동내역 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/teacherMain")
	public String teacherMain(LogTchActStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("paging", "Y");
		
		return "mng/stats/tchact/teacher_main";
	}

    /**
	 * 강사활동내역 강사 목록 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/teacherList")
	public String teacherList(LogTchActStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<LogTchActStatusVO> resultList = logTchActStatusService.listPageing(vo);
		request.setAttribute("tchActStatusList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);

		return "mng/stats/tchact/teacher_list_div";
	}

    /**
	 * 강사활동내역, 과정별 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseMain")
	public String courseMain(LogTchActStatusDetailVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		UsrUserInfoVO uidto = new UsrUserInfoVO();
		uidto.setUserNo(vo.getUserNo());
		uidto = usrUserInfoService.view(uidto);
		request.setAttribute("userInfoVO", uidto);

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);

		String TitleName = "";
		if("end".equals(vo.getSearchDuration())){
			TitleName = getMessage(request, "student.title.course.his");
		} else if("ing".equals(vo.getSearchDuration())){
			TitleName = getMessage(request, "student.title.course.ing");
		} else if("bef".equals(vo.getSearchDuration())){
			TitleName = getMessage(request, "student.title.course.bef");
		}
		request.setAttribute("TitleName", TitleName);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);

		return "mng/stats/tchact/course_main";
	}

    /**
	 * 강사활동내역 강사 목록 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseList")
	public String courseList(LogTchActStatusDetailVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<LogTchActStatusDetailVO> resultList = logTchActStatusService.listCoursePageing(vo);
		request.setAttribute("tchActStatusDetailList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/stats/tchact/course_list_div";
	}


	/**
	 * 강사 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewTchPop")
	public String viewTch (LogTchActStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		try {
			vo = logTchActStatusService.view(vo).getReturnVO();

			//-- 개인 정보 조회 로그
			LogPrivateInfoInqLogVO pildto = new LogPrivateInfoInqLogVO();
			pildto.setOrgCd(orgCd);
			pildto.setMenuCd(UserBroker.getMenuCode(request));
			pildto.setUserNo(UserBroker.getUserNo(request));
			pildto.setUserNm(UserBroker.getUserName(request));
			pildto.setDivCd("PROF_VIEW");
			pildto.setInqCts(vo.getUserNo());
			logPrivateInfoService.add(pildto);

		} catch (Exception e) {

		}
		request.setAttribute("vo", vo);
		return "mng/stats/tchact/teacher_view_pop";
	}

	/**
	 * 강사활동내역 엑셀다운
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listExcelDownload")
	public String listExcelDownload(LogTchActStatusVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgNm = UserBroker.getUserOrgNm(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add(getMessage(request, "teacher.title.tchactstatus"));
		titleList.add(getMessage(request, "common.title.no"));
		titleList.add(getMessage(request, "user.title.userinfo.name"));
		titleList.add(getMessage(request, "user.title.userinfo.userid"));
		titleList.add(getMessage(request, "user.title.userinfo.email"));
		titleList.add(getMessage(request, "user.title.userinfo.mobileno"));
		titleList.add(getMessage(request, "student.title.course.his"));
		titleList.add(getMessage(request, "student.title.course.ing"));
		titleList.add(getMessage(request, "student.title.course.bef"));

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogDTO = new LogPrnLogVO();
		printLogDTO.setUserNo(UserBroker.getUserNo(request));
		printLogDTO.setUserNm(UserBroker.getUserName(request));
		printLogDTO.setPrnDoc("통계/모니터링 : 강사 활동 내역");
		printLogDTO.setParam(vo.toString());
		logPrnLogService.add(printLogDTO);
		response.setHeader("Content-Disposition", "attachment;filename=teacherList.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");

		logTchActStatusService.listExcelDownload(vo, orgNm, titleList, response.getOutputStream());

		return null;
	}

	/**
	 * 강사활동내역 접속통계
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseStatistics")
	public String courseStatistics(LogTchActStatusDetailVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);

		ProcessResultListVO<LogTchActStatusDetailVO> resultList = logTchActStatusService.listCourse(vo);
		request.setAttribute("tchActStatusDetailList", resultList.getReturnList());
		request.setAttribute("vo", vo);

		return "mng/stats/tchact/course_statistics";
	}


	/**
	 * 강사활동내역 접속통계 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseStatisticsList")
	public String courseStatisticsList(LogTchActStatusDetailVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		String startDt = request.getParameter("startDt");
		String endDt = request.getParameter("endDt");
		String type = request.getParameter("type");
		String crsCreCd = request.getParameter("crsCreCd");
		String crsCreNm = request.getParameter("crsCreNm");
		String[] crsCreCdArray = crsCreCd.split(";");
		String[] crsCreNmArray = crsCreNm.split(";");

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		if(StringUtil.isNull(startDt)){
			startDt = nowDate;
		}
		if(StringUtil.isNull(endDt)){
			endDt = nowDate;
		}
		vo.setStartDt(startDt);
		vo.setEndDt(endDt);
		vo.setViewType(type);

		List<LogTchActStatusDetailVO> connectLogList1 = null;
		List<LogTchActStatusDetailVO> connectLogList2 = null;
		List<LogTchActStatusDetailVO> connectLogList3 = null;
		List<LogTchActStatusDetailVO> connectLogList4 = null;
		List<LogTchActStatusDetailVO> connectLogList5 = null;
		String logCrsCreNm1 = "";
		String logCrsCreNm2 = "";
		String logCrsCreNm3 = "";
		String logCrsCreNm4 = "";
		String logCrsCreNm5 = "";

		if(StringUtil.isNotNull(crsCreCd)){
			for(int i=0; i< crsCreCdArray.length; i++){
				vo.setCrsCreCd(crsCreCdArray[i]);

				if(i==0){
					connectLogList1 = logTchActStatusService.listCourseStatistics(vo).getReturnList();
					logCrsCreNm1 = crsCreNmArray[i];
				}
				if(i==1){
					connectLogList2 = logTchActStatusService.listCourseStatistics(vo).getReturnList();
					logCrsCreNm2 = crsCreNmArray[i];
				}
				if(i==2){
					connectLogList3 = logTchActStatusService.listCourseStatistics(vo).getReturnList();
					logCrsCreNm3 = crsCreNmArray[i];
				}
				if(i==3){
					connectLogList4 = logTchActStatusService.listCourseStatistics(vo).getReturnList();
					logCrsCreNm4 = crsCreNmArray[i];
				}
				if(i==4){
					connectLogList5 = logTchActStatusService.listCourseStatistics(vo).getReturnList();
					logCrsCreNm5 = crsCreNmArray[i];
				}
			}
		}

		request.setAttribute("connectLogList1",connectLogList1);
		request.setAttribute("connectLogList2",connectLogList2);
		request.setAttribute("connectLogList3",connectLogList3);
		request.setAttribute("connectLogList4",connectLogList4);
		request.setAttribute("connectLogList5",connectLogList5);

		request.setAttribute("logCrsCreNm1",logCrsCreNm1);
		request.setAttribute("logCrsCreNm2",logCrsCreNm2);
		request.setAttribute("logCrsCreNm3",logCrsCreNm3);
		request.setAttribute("logCrsCreNm4",logCrsCreNm4);
		request.setAttribute("logCrsCreNm5",logCrsCreNm5);

		request.setAttribute("TYPE",type);

		return "mng/stats/tchact/course_statistics_list";
	}

}

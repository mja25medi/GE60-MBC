package egovframework.edutrack.web.manage.stats;

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
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogService;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogVO;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogService;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/stats/conn")
public class StatsConnManageController extends GenericController {
	
    /** service */
	@Autowired @Qualifier("logSysConnLogService")
	private LogSysConnLogService 		logSysConnLogService;
	
	@Autowired @Qualifier("logSysLoginLogService")
	private LogSysLoginLogService		logSysLoginLogService;
	
	@Autowired @Qualifier("logSysUserLogService")
	private LogSysUserLogService		logSysUserLogService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 			orgOrgInfoService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			sysCodeMemService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 홈페이지 메인 페이지 접속 로그 메인 
	 * @param LogSysConnLogVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/stats/main_conn.jsp"
	 * @throws Exception
	 */	
	@RequestMapping(value="/connMain")
	public String connMain(LogSysConnLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String curDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("curDate", curDate);

		//STATUS_TYPE
		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("STATUS_TYPE");
		request.setAttribute("codeList", codeList);

		return "mng/stats/conn/conn_main";
	}

	/**
     * @Method Name : list
     * @Method 설명 : 홈페이지 메인 페이지 접속 로그 목록 조회 
	 * @param LogSysConnLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/listConn")
	public String listConn(LogSysConnLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<LogSysConnLogVO> result = logSysConnLogService.listLog(vo);
		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : viewAutoDate
     * @Method 설명 : 자동으로 날짜 가져오기 
	 * @param LogSysConnLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/viewAutoDate")
	public String viewAutoDate(LogSysConnLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		LogSysConnLogVO result = logSysConnLogService.viewAutoDate(vo);
		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * @Method Name : mainLogin
	 * @Method 설명 : 
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/loginMain")
	public String loginMain(LogSysLoginLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String curDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("curDate", curDate);

		//STATUS_TYPE
		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("STATUS_TYPE");
		request.setAttribute("codeList", codeList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/stats/user/login_main";
	}
	
	/**
     * @Method Name : listLogin
     * @Method 설명 : 홈페이지 메인 페이지 로그인 로그 목록 조회 
	 * @param LogSysLoginLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/listLogin")
	public String listConn(LogSysLoginLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<LogSysLoginLogVO> result = logSysLoginLogService.listLog(vo);
		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * @Method Name : mainUser
	 * @Method 설명 : 
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/userMain")
	public String userMain(LogSysUserLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String curDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("curDate", curDate);

		//STATUS_TYPE
		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("REG_TYPE");
		request.setAttribute("codeList", codeList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/stats/user/user_main";
	}
	
	/**
     * @Method Name : listUser
     * @Method 설명 : 사용자 통계 데이터 조회 
	 * @param LogSysLoginLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/listUser")
	public String listUser(LogSysUserLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<LogSysUserLogVO> result = logSysUserLogService.listLog(vo);
		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : connListExcelDownload
     * @Method 설명 : 홈페이지 접속 통계 엑셀 출력 
	 * @param LogSysConnLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/connListExcelDownload")
	public String connListExcelDownload(LogSysConnLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(!"".equals(vo.getStartDt())){
			response.setHeader("Content-Disposition", "attachment;filename=connectLogList"
					+vo.getDateType()+"_"+vo.getStartDt()+"_"+vo.getEndDt()+".xls;");
		} else {
			response.setHeader("Content-Disposition", "attachment;filename=connectLogList_total.xls;");
		}
		response.setHeader("Content-Disposition", "attachment;filename=connectLogList"
				+vo.getDateType()+"_"+vo.getStartDt()+"_"+vo.getEndDt()+".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		logSysConnLogService.listExcelDownload(vo, response.getOutputStream());
		return null;
	}
	
	/**
     * @Method Name : loginListExcelDownload
     * @Method 설명 : 홈페이지 메인 페이지 로그인 통계 엑셀 출력 
	 * @param LogSysLoginLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/loginListExcelDownload")
	public String loginListExcelDownload(LogSysLoginLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(!"".equals(vo.getStartDt())){
			response.setHeader("Content-Disposition", "attachment;filename=connectLogList"
					+vo.getDateType()+"_"+vo.getStartDt()+"_"+vo.getEndDt()+".xls;");
		} else {
			response.setHeader("Content-Disposition", "attachment;filename=connectLogList_total.xls;");
		}
		response.setHeader("Content-Disposition", "attachment;filename=connectLogList"
				+vo.getDateType()+"_"+vo.getStartDt()+"_"+vo.getEndDt()+".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		logSysLoginLogService.listExcelDownload(vo, response.getOutputStream());
		return null;
	}
	
	/**
     * @Method Name : UserListExcelDownload
     * @Method 설명 : 홈페이지 메인 페이지 로그인 통계 엑셀 출력 
	 * @param LogSysUserLogVO 
	 * @param commandMap
	 * @param model
	 * @return  Json
	 * @throws Exception
	 */	
	@RequestMapping(value="/userListExcelDownload")
	public String userListExcelDownload(LogSysUserLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(!"".equals(vo.getStartDt())){
			response.setHeader("Content-Disposition", "attachment;filename=userLogList"
					+vo.getDateType()+"_"+vo.getStartDt()+"_"+vo.getEndDt()+".xls;");
		} else {
			response.setHeader("Content-Disposition", "attachment;filename=userLogList_total.xls;");
		}
		response.setHeader("Content-Disposition", "attachment;filename=userLogList"
				+vo.getDateType()+"_"+vo.getStartDt()+"_"+vo.getEndDt()+".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		logSysUserLogService.listExcelDownload(vo, response.getOutputStream());
		return null;
	}
	
}

package egovframework.edutrack.web.log;

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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogService;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/loginLog")
public class LoginLogAdminController  extends GenericController {
	
	/** service */
	@Autowired @Qualifier("logSysLoginLogService")
	private LogSysLoginLogService 	logSysLoginLogService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService orgOrgInfoService;
	
	@Autowired @Qualifier("sysCodeService")
	private SysCodeService	sysCodeService;



	/**
	 * 로그인 로그 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/main")
	public String main(LogSysLoginLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request) throws Exception {

		String curDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("curDate", curDate);

		//--교육기관 목록
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setUseYn("Y"); //-- 사용여부 Y 인것만 가져올 수 있도록
		ProcessResultListVO<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(orgInfoVO);
		request.setAttribute("orgInfoList", orgInfoList.getReturnList());

		//STATUS_TYPE
		ProcessResultListVO<SysCodeVO> codeList = sysCodeService.listCode("STATUS_TYPE");
		request.setAttribute("codeList", codeList.getReturnList());

		return "log/loginLog/login_log_main";
	}

	/**
	 * 로그인 로그 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list(LogSysLoginLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<LogSysLoginLogVO> result = logSysLoginLogService.listLog(vo);

		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 로그인 로그 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/viewAutoDate")
	public String viewAutoDate(LogSysLoginLogVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		LogSysLoginLogVO result = logSysLoginLogService.viewAutoDate(vo);
		
		return JsonUtil.responseJson(response, result);
	}

	
}

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
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusService;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/orgStatus")
public class OrgStatusAdminController  extends GenericController {
	
	/** service */
	@Autowired @Qualifier("logOrgStatusService")
	private LogOrgStatusService		logOrgStatusService;
	

		/**
		 * 교육기관 현황 메인
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
/*		@RequestMapping(value="/main")
		public String main(LogOrgStatusVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) {
			commonVOProcessing(vo, request);

			return "mng/stats/educourse/status_main";
		}
*/
		/**
		 * 교육기관 현황 목록
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/main")
		public String main(LogOrgStatusVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request) {
			commonVOProcessing(vo, request);

			vo.setUseYn("Y");

			ProcessResultListVO<LogOrgStatusVO> resultList = null;
			try {
				resultList = logOrgStatusService.listPageing(vo, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			request.setAttribute("orgStatusList",resultList.getReturnList());
			request.setAttribute("pageInfo",resultList.getPageInfo());
			return "log/orgstatus/status_list_div";
		}
	
  
	
}

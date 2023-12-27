package egovframework.edutrack.web.log;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.exception.service.LogExceptionService;
import egovframework.edutrack.modules.log.exception.service.LogExceptionVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/log/exception")
public class LogExceptionAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("logExceptionService")
	private LogExceptionService 		logExceptionService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 오류 로그 페이지를 보여 준다. 
	 * @param LogExceptionVO 
	 * @param commandMap
	 * @param model
	 * @return  "/log/exception/list_exception.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listExceptionMain")
	public String main(LogExceptionVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String paramOriginDt =
			StringUtil.nvl(vo.getOriginDt(), DateTimeUtil.getDate()).replaceAll("\\.", "");
		String paramSearchFrom =
				StringUtil.nvl(vo.getSearchFrom(), DateTimeUtil.getDate()).replaceAll("\\.", "");
		String paramSearchTo =
				StringUtil.nvl(vo.getSearchTo(), DateTimeUtil.getDate()).replaceAll("\\.", "");

		String paramExceptionDiv =
			(vo.getExceptionDiv() != null
					&& vo.getExceptionDiv().equals(LogExceptionService.EXCEPTION_DIV_SERVICE) )
						?	LogExceptionService.EXCEPTION_DIV_SERVICE
						:	LogExceptionService.EXCEPTION_DIV_ACTION;

		LogExceptionVO svo = new LogExceptionVO();
		svo.setExceptionDiv(paramExceptionDiv);
		svo.setSearchFrom(paramSearchFrom);
		svo.setSearchTo(paramSearchTo);
		
		List<LogExceptionVO> exceptionList = logExceptionService.list(svo).getReturnList();

		int exceptionTotalCount = 0;

		for (LogExceptionVO logExceptionVO : exceptionList) {
			logExceptionVO.setStackTrace(logExceptionVO.getStackTrace().replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll("\n", "<br>"));
			exceptionTotalCount += logExceptionVO.getOriginCnt();
		}

		vo.setExceptionDiv(paramExceptionDiv);
		vo.setOriginDt(DateTimeUtil.getDateText(1, paramOriginDt, "."));
		vo.setSearchFrom(DateTimeUtil.getDateText(1, paramSearchFrom, "."));
		vo.setSearchTo(DateTimeUtil.getDateText(1, paramSearchTo, "."));
		request.setAttribute("exceptionTotalCount", exceptionTotalCount);
		request.setAttribute("exceptionList", exceptionList);		
		request.setAttribute("vo", vo);
		
		return "log/exception/list_exception";
	}	
	
	
}

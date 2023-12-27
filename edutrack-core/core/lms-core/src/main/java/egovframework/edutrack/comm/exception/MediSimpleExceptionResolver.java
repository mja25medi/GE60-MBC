package egovframework.edutrack.comm.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.exception.service.LogExceptionService;
import egovframework.edutrack.modules.log.exception.service.LogExceptionVO;
import egovframework.edutrack.modules.log.exception.service.impl.LogExceptionServiceImpl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

public class MediSimpleExceptionResolver extends SimpleMappingExceptionResolver {
    
	/** service */
    @Resource(name="logExceptionService")
	private LogExceptionService 		logExceptionService;
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		try {
			/* 20161125 주석처리함 - hy
			 * 
			 *  
			ex.printStackTrace(); //-- 모든 오류 로그 찍어주자.
			
			String exceptionDiv = "";
			if(handler.getClass().getSuperclass().equals(EgovAbstractServiceImpl.class)) {
				exceptionDiv = LogExceptionService.EXCEPTION_DIV_SERVICE;
			} else if(handler.getClass().getSuperclass().equals(GenericController.class)) {
				exceptionDiv = LogExceptionService.EXCEPTION_DIV_ACTION;
			}
			
			StringWriter writer = new StringWriter();
			try {
				ex.printStackTrace(new PrintWriter(writer));
			} catch (Exception e) {
				logger.error("Error! An error occurred while trying to extract exception information.");
				e.printStackTrace();
			} finally {
				writer.close();
			}
			
			String methodNm = handler.getClass().getName();
			String exceptionNm = ex.getClass().getName();
			
			//-- 설정상 DB 로그를 남기는 것으로 설정이 되어 있을 경우에만 디비에 남김.
			if("use".equals(Constants.EXCEPTION_DBLOG_USE) && !"".equals(exceptionDiv)) {
				LogExceptionVO levo = new LogExceptionVO();
				levo.setExceptionDiv(exceptionDiv);
				levo.setOriginDt(DateTimeUtil.getCurrentString("yyyyMMdd"));
				levo.setMethodNm(methodNm);
				levo.setExceptionNm(exceptionNm);
				levo.setStackTrace(writer.toString());
				levo.setOriginCnt(1);
		    	try {
		    		logExceptionDao.insert(levo);
		    	} catch (Exception e) {
		    		logExceptionDao.update(levo);
		    	}
			}
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
			//-- 여기서는 오류가 나도 처리 하지 말자.
		}		
				
		String viewName = determineViewName(ex, request);
		if(viewName != null) {
			if(logger.isDebugEnabled()) {
				logger.debug("Called AJAX : "+request.getHeader("AJAX"));
			}
			
			if(request.getHeader("AJAX") != null && request.getHeader("AJAX").equals("true")) {
				Integer statusCode = determineStatusCode(request, viewName);
				if(statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
			}
			return getModelAndView(viewName, ex, request);
		} else {
			return null;
		}		
	}
}

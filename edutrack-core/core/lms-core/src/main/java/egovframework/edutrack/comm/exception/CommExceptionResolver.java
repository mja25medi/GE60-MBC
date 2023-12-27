package egovframework.edutrack.comm.exception;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.exception.service.LogExceptionService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

public class CommExceptionResolver implements HandlerExceptionResolver {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
    /** service */
	@Resource(name="logExceptionService")
	private LogExceptionService 		logExceptionService;
	
	/** The default name of the exception attribute: "exception". */
	public static final String DEFAULT_EXCEPTION_ATTRIBUTE = "exception";

	private Properties exceptionMappings;

	private Class<?>[] excludedExceptions;

	private String defaultErrorView;

	private Integer defaultStatusCode;

	private Map<String, Integer> statusCodes = new HashMap<String, Integer>();

	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;


	/**
	 * Set the mappings between exception class names and error view names.
	 * The exception class name can be a substring, with no wildcard support at present.
	 * A value of "ServletException" would match {@code javax.servlet.ServletException}
	 * and subclasses, for example.
	 * <p><b>NB:</b> Consider carefully how
	 * specific the pattern is, and whether to include package information (which isn't mandatory).
	 * For example, "Exception" will match nearly anything, and will probably hide other rules.
	 * "java.lang.Exception" would be correct if "Exception" was meant to define a rule for all
	 * checked exceptions. With more unusual exception names such as "BaseBusinessException"
	 * there's no need to use a FQN.
	 * @param mappings exception patterns (can also be fully qualified class names) as keys,
	 * and error view names as values
	 */
	public void setExceptionMappings(Properties mappings) {
		this.exceptionMappings = mappings;
	}

	/**
	 * Set one or more exceptions to be excluded from the exception mappings.
	 * Excluded exceptions are checked first and if one of them equals the actual
	 * exception, the exception will remain unresolved.
	 * @param excludedExceptions one or more excluded exception types
	 */
	public void setExcludedExceptions(Class<?>... excludedExceptions) {
		this.excludedExceptions = excludedExceptions;
	}

	/**
	 * Set the name of the default error view.
	 * This view will be returned if no specific mapping was found.
	 * <p>Default is none.
	 */
	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	/**
	 * Set the HTTP status code that this exception resolver will apply for a given
	 * resolved error view. Keys are view names; values are status codes.
	 * <p>Note that this error code will only get applied in case of a top-level request.
	 * It will not be set for an include request, since the HTTP status cannot be modified
	 * from within an include.
	 * <p>If not specified, the default status code will be applied.
	 * @see #setDefaultStatusCode(int)
	 */
	public void setStatusCodes(Properties statusCodes) {
		for (Enumeration<?> enumeration = statusCodes.propertyNames(); enumeration.hasMoreElements();) {
			String viewName = (String) enumeration.nextElement();
			Integer statusCode = new Integer(statusCodes.getProperty(viewName));
			this.statusCodes.put(viewName, statusCode);
		}
	}

	/**
	 * An alternative to {@link #setStatusCodes(Properties)} for use with
	 * Java-based configuration.
	 */
	public void addStatusCode(String viewName, int statusCode) {
		this.statusCodes.put(viewName, statusCode);
	}

	/**
	 * Returns the HTTP status codes provided via {@link #setStatusCodes(Properties)}.
	 * Keys are view names; values are status codes.
	 */
	public Map<String, Integer> getStatusCodesAsMap() {
		return Collections.unmodifiableMap(statusCodes);
	}

	/**
	 * Set the default HTTP status code that this exception resolver will apply
	 * if it resolves an error view and if there is no status code mapping defined.
	 * <p>Note that this error code will only get applied in case of a top-level request.
	 * It will not be set for an include request, since the HTTP status cannot be modified
	 * from within an include.
	 * <p>If not specified, no status code will be applied, either leaving this to the
	 * controller or view, or keeping the servlet engine's default of 200 (OK).
	 * @param defaultStatusCode HTTP status code value, for example 500
	 * ({@link HttpServletResponse#SC_INTERNAL_SERVER_ERROR}) or 404 ({@link HttpServletResponse#SC_NOT_FOUND})
	 * @see #setStatusCodes(Properties)
	 */
	public void setDefaultStatusCode(int defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}

	/**
	 * Set the name of the model attribute as which the exception should be exposed.
	 * Default is "exception".
	 * <p>This can be either set to a different attribute name or to {@code null}
	 * for not exposing an exception attribute at all.
	 * @see #DEFAULT_EXCEPTION_ATTRIBUTE
	 */
	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) {
		request.setAttribute("exception", exception);
		
		try {
			exception.printStackTrace(); //-- 모든 오류 로그 찍어주자.
			
			String exceptionDiv = "";
			if(obj.getClass().getSuperclass().equals(EgovAbstractServiceImpl.class)) {
				exceptionDiv = LogExceptionService.EXCEPTION_DIV_SERVICE;
			} else if(obj.getClass().getSuperclass().equals(GenericController.class)) {
				exceptionDiv = LogExceptionService.EXCEPTION_DIV_ACTION;
			}
			
			String methodNm = obj.getClass().getName();
			String exceptionNm = exception.getClass().getName();
			
			//-- 설정상 DB 로그를 남기는 것으로 설정이 되어 있을 경우에만 디비에 남김.
			logExceptionService.add(exceptionDiv, methodNm, exceptionNm, exception);
			
		} catch (Exception e) {
			e.printStackTrace();
			//-- 여기서는 오류가 나도 처리 하지 말자.
		}
		
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(exception, request);
		if (viewName != null) {
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			if(request.getHeader("AJAX") != null && request.getHeader("AJAX").equals("true")) {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
			}
			return getModelAndView(viewName, exception, request);
		} else {
			return null;
		}
		
	}
	
	/**
	 * Determine the view name for the given exception, first checking against the
	 * {@link #setExcludedExceptions(Class[]) "excludedExecptions"}, then searching the
	 * {@link #setExceptionMappings "exceptionMappings"}, and finally using the
	 * {@link #setDefaultErrorView "defaultErrorView"} as a fallback.
	 * @param ex the exception that got thrown during handler execution
	 * @param request current HTTP request (useful for obtaining metadata)
	 * @return the resolved view name, or {@code null} if excluded or none found
	 */
	protected String determineViewName(Exception ex, HttpServletRequest request) {
		String viewName = null;
		if (this.excludedExceptions != null) {
			for (Class<?> excludedEx : this.excludedExceptions) {
				if (excludedEx.equals(ex.getClass())) {
					return null;
				}
			}
		}
		// Check for specific exception mappings.
		if (this.exceptionMappings != null) {
			viewName = findMatchingViewName(this.exceptionMappings, ex);
		}
		// Return default error view else, if defined.
		if (viewName == null && this.defaultErrorView != null) {
			if (log.isDebugEnabled()) {
				log.debug("Resolving to default view '" + this.defaultErrorView + "' for exception of type [" +
						ex.getClass().getName() + "]");
			}
			viewName = this.defaultErrorView;
		}
		return viewName;
	}

	/**
	 * Find a matching view name in the given exception mappings.
	 * @param exceptionMappings mappings between exception class names and error view names
	 * @param ex the exception that got thrown during handler execution
	 * @return the view name, or {@code null} if none found
	 * @see #setExceptionMappings
	 */
	protected String findMatchingViewName(Properties exceptionMappings, Exception ex) {
		String viewName = null;
		String dominantMapping = null;
		int deepest = Integer.MAX_VALUE;
		for (Enumeration<?> names = exceptionMappings.propertyNames(); names.hasMoreElements();) {
			String exceptionMapping = (String) names.nextElement();
			int depth = getDepth(exceptionMapping, ex);
			if (depth >= 0 && (depth < deepest || (depth == deepest &&
					dominantMapping != null && exceptionMapping.length() > dominantMapping.length()))) {
				deepest = depth;
				dominantMapping = exceptionMapping;
				viewName = exceptionMappings.getProperty(exceptionMapping);
			}
		}
		if (viewName != null && log.isDebugEnabled()) {
			log.debug("Resolving to view '" + viewName + "' for exception of type [" + ex.getClass().getName() +
					"], based on exception mapping [" + dominantMapping + "]");
		}
		return viewName;
	}

	/**
	 * Return the depth to the superclass matching.
	 * <p>0 means ex matches exactly. Returns -1 if there's no match.
	 * Otherwise, returns depth. Lowest depth wins.
	 */
	protected int getDepth(String exceptionMapping, Exception ex) {
		return getDepth(exceptionMapping, ex.getClass(), 0);
	}

	private int getDepth(String exceptionMapping, Class<?> exceptionClass, int depth) {
		if (exceptionClass.getName().contains(exceptionMapping)) {
			// Found it!
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if (exceptionClass.equals(Throwable.class)) {
			return -1;
		}
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(), depth + 1);
	}

	/**
	 * Determine the HTTP status code to apply for the given error view.
	 * <p>The default implementation returns the status code for the given view name (specified through the
	 * {@link #setStatusCodes(Properties) statusCodes} property), or falls back to the
	 * {@link #setDefaultStatusCode defaultStatusCode} if there is no match.
	 * <p>Override this in a custom subclass to customize this behavior.
	 * @param request current HTTP request
	 * @param viewName the name of the error view
	 * @return the HTTP status code to use, or {@code null} for the servlet container's default
	 * (200 in case of a standard error view)
	 * @see #setDefaultStatusCode
	 * @see #applyStatusCodeIfPossible
	 */
	protected Integer determineStatusCode(HttpServletRequest request, String viewName) {
		if (this.statusCodes.containsKey(viewName)) {
			return this.statusCodes.get(viewName);
		}
		return this.defaultStatusCode;
	}

	/**
	 * Apply the specified HTTP status code to the given response, if possible (that is,
	 * if not executing within an include request).
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @param statusCode the status code to apply
	 * @see #determineStatusCode
	 * @see #setDefaultStatusCode
	 * @see HttpServletResponse#setStatus
	 */
	protected void applyStatusCodeIfPossible(HttpServletRequest request, HttpServletResponse response, int statusCode) {
		if (!WebUtils.isIncludeRequest(request)) {
			if (log.isDebugEnabled()) {
				log.debug("Applying HTTP status code " + statusCode);
			}
			response.setStatus(statusCode);
			request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, statusCode);
		}
	}

	/**
	 * Return a ModelAndView for the given request, view name and exception.
	 * <p>The default implementation delegates to {@link #getModelAndView(String, Exception)}.
	 * @param viewName the name of the error view
	 * @param ex the exception that got thrown during handler execution
	 * @param request current HTTP request (useful for obtaining metadata)
	 * @return the ModelAndView instance
	 */
	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		return getModelAndView(viewName, ex);
	}

	/**
	 * Return a ModelAndView for the given view name and exception.
	 * <p>The default implementation adds the specified exception attribute.
	 * Can be overridden in subclasses.
	 * @param viewName the name of the error view
	 * @param ex the exception that got thrown during handler execution
	 * @return the ModelAndView instance
	 * @see #setExceptionAttribute
	 */
	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		ModelAndView mv = new ModelAndView(viewName);
		if (this.exceptionAttribute != null) {
			if (log.isDebugEnabled()) {
				log.debug("Exposing Exception as model attribute '" + this.exceptionAttribute + "'");
			}
			mv.addObject(this.exceptionAttribute, ex);
		}
		return mv;
	}
}

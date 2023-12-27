package egovframework.edutrack.comm.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.nhncorp.lucy.security.xss.XssFilter;

public class XSSRequest extends HttpServletRequestWrapper {

	private static final String LUCY_CONFIG_FILE = "lucy-xss-superset.xml";
	private static String BAD_TAG_INFO = "<!-- Not Allowed Tag Filtered -->";
	private static String BAD_ATT_INFO = "<!-- Not Allowed Attribute Filtered -->";

	/** lucy xss 처리 filter **/
	private XssFilter XSSFilter = XssFilter.getInstance(LUCY_CONFIG_FILE);
	/** logger **/
	private Logger logger = Logger.getLogger(getClass());

	public XSSRequest(ServletRequest request) {
		super((HttpServletRequest) request);
	}
//	@Override
//	public Object getAttribute(String name) {
//		Object value = super.getAttribute(name);
//		return this.reflectionObject(value);
//	}

	@Override
	public String getParameter(String name) {
		String parameter = null;
		String[] vals = getParameterMap().get(name);

		if (vals != null && vals.length > 0) {
			parameter = vals[0];
		}

		return parameter;
	}

	@Override
	public String[] getParameterValues(String name) {
		return getParameterMap().get(name);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(getParameterMap().keySet());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String,String[]> getParameterMap() {
		Map<String, String[]> res = new HashMap<String, String[]>();
		Map<String, String[]> orgQueryString = super.getParameterMap();
		if(orgQueryString!=null) {
			for (String key : (Set<String>) orgQueryString.keySet()) {
				String[] values = orgQueryString.get(key);
				String[] replaceValues = new String[values.length];
				for (int i=0; i < values.length; i++) {
					replaceValues[i] = this.doFilter(values[i]);
					logger.debug(replaceValues[i]);
				}
				res.put(key, replaceValues);
			}
		}
		return res;
	}

	/**
	 *
	 * @param param 대상 문자열
	 * @return
	 */
	private String doFilter(String param) {
		if(StringUtils.isBlank(param)) {
			return param;
		}
		//-- 추가 2015.12.17 서도영 변수값에 javascript:로 넘어오는 변수는 비워서 리턴한다.
		if(param.toLowerCase().startsWith("javascript:")) {
			return "";
		}
		if(param.toLowerCase().startsWith("\" ")) {
			return "";
		}
		if(param.toLowerCase().startsWith("' ")) {
			return "";
		}
		if(param.toLowerCase().startsWith("\";")) {
			return "";
		}
		if(param.toLowerCase().startsWith("';")) {
			return "";
		}
		param = XSSFilter.doFilter(param);
		param = param.replace(BAD_TAG_INFO, "");
		param = param.replace(BAD_ATT_INFO, "");
		return param;
	}
}

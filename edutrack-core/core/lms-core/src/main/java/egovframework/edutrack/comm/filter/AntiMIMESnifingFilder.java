package egovframework.edutrack.comm.filter;

import java.io.IOException;
import java.util.logging.LogRecord;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AntiMIMESnifingFilder implements Filter{

	@Override
	public void destroy() { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	    // Protection against Type 1 Reflected XSS attacks
		res.addHeader("X-XSS-Protection", "1; mode=block");
	    // Disabling browsers to perform risky mime sniffing
		res.addHeader("X-Content-Type-Options", "nosniff");
		chain.doFilter(req,res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }


	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

}


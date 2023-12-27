package egovframework.edutrack.web.decorator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.AccessDeniedException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;



@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/decorator")
public class DecoratorController extends GenericController {

	/**
     * @Method Name : decorator
     * @Method 설명 : sitemesh 탬플릿 조회
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(HttpServletRequest request , HttpServletResponse response) throws Throwable {

		String tplType = UserBroker.getUserLayoutTplType(request);
	
		
		return UserBroker.getPrefixHomeUrl(request)  + "decorators/"+tplType+"_decorator";
	}
	/**
     * @Method Name : decorator
     * @Method 설명 : sitemesh 탬플릿 조회
	 * @throws Exception
	 */
	@RequestMapping(value="/sub")
	public String sub(HttpServletRequest request , HttpServletResponse response) throws Throwable {

		String tplType = UserBroker.getUserLayoutTplType(request);
	
		
		return UserBroker.getPrefixHomeUrl(request)  + "decorators/sub_decorator";
	}
	/**
     * @Method Name : decorator
     * @Method 설명 : sitemesh 탬플릿 조회
	 * @throws Exception
	 */
	@RequestMapping(value="/lec")
	public String lec(HttpServletRequest request , HttpServletResponse response) throws Throwable {

		String tplType = UserBroker.getUserLayoutTplType(request);
	
		
		return UserBroker.getPrefixHomeUrl(request)  + "decorators/lec_decorator";
	}
	/**
     * @Method Name : decorator
     * @Method 설명 : sitemesh 탬플릿 조회
	 * @throws Exception
	 */
	@RequestMapping(value="/pop")
	public String pop(HttpServletRequest request , HttpServletResponse response) throws Throwable {

		String tplType = UserBroker.getUserLayoutTplType(request);
	
		
		return UserBroker.getPrefixHomeUrl(request)  + "decorators/pop_decorator";
	}
	/**
     * @Method Name : decorator
     * @Method 설명 : sitemesh 탬플릿 조회
	 * @throws Exception
	 */
	@RequestMapping(value="/ifm")
	public String ifm(HttpServletRequest request , HttpServletResponse response) throws Throwable {

		String tplType = UserBroker.getUserLayoutTplType(request);
	
		
		return UserBroker.getPrefixHomeUrl(request)  + "decorators/ifm_decorator";
	}

}

package egovframework.edutrack.comm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.edutrack.comm.util.web.LocaleUtil;

@Controller
public class LocaleController {

	@RequestMapping(value = "/changeLocale")
	public String changeLocale(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String locale) {
    	HttpSession session = request.getSession();
    	// step. 파라메터에 따라서  Locale을 새로 설정한다.        
    	LocaleUtil.setLocale(request, locale);
     
        // step. 해당 컨트롤러에게 요청을 보낸 주소로 돌아간다.
        String redirectURL = "redirect:" + request.getHeader("referer");
        return redirectURL;
    }
	
}

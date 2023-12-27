package egovframework.edutrack.comm.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;

@Controller
@SessionAttributes("i18n")
public class MessageController extends GenericController {
	
	/**
     * @Method Name : getJsMessage
     * @Method 설명 : javascript 에서 사용하는 메시지 관련 처리 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "/jsp/mng/board/bbs/main_info.jsp
	 * @throws Exception
	 */
	@RequestMapping("/i18n/getJsMessage")
	@ResponseBody
	public String mainInfo(
			@RequestParam(value="messageKey", defaultValue="", required=false) String messageKey,
			@RequestParam(value="args1", defaultValue="", required=false) String args1,
			@RequestParam(value="args2", defaultValue="", required=false) String args2,
			@RequestParam(value="args3", defaultValue="", required=false) String args3,
			Model model, HttpServletRequest request, HttpServletResponse response ) throws Throwable {
		
		int argsCnt = 0;
		if(ValidationUtils.isNotEmpty(args1)) argsCnt++;
		if(ValidationUtils.isNotEmpty(args2)) argsCnt++;
		if(ValidationUtils.isNotEmpty(args3)) argsCnt++;
		
		String[] args = null;
		
		if(argsCnt > 0) {
			args = new String[argsCnt];
			int lineNum = 0;
			if(ValidationUtils.isNotEmpty(args1)) {
				args[lineNum] = args1;
				lineNum++;
			}
			if(ValidationUtils.isNotEmpty(args2)) {
				args[lineNum] = args2;
				lineNum++;
			}
			if(ValidationUtils.isNotEmpty(args3)) {
				args[lineNum] = args3;
				lineNum++;
			}
		}
		
		String message = getMessage(request, messageKey, args);
		
//		try {
//			PrintWriter writer = response.getWriter();
//			writer.print(message);
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return message;
	}

}

package egovframework.edutrack.comm.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.UserBroker;

@Controller
@RequestMapping("/js/*")
public class JavascriptController {

	private static final String	HTTP_CONTENT_LENGTH_HEADER	= "Content-Length";


	private static final LinkedHashMap<String, String> JS_CONTEXT_VALUES = new LinkedHashMap<String, String>() {
		private static final long	serialVersionUID	= -8334888591453796082L;
		{
			put("$M"						, "{}"											);
			put("Context"					, "{}"											);
			put("CONTEXT_ROOT"				, "'" + Constants.CONTEXT_ROOT			+ "'"	);
			put("CONTEXT_PATH"				, "'" + Constants.CONTEXT_ROOT			+ "'"	);
			put("Context.URL"				, "'" + Constants.HOST_URL				+ "'"	);
			put("Context.ROOT"				, "'" + Constants.CONTEXT_ROOT			+ "'"	);
			put("Context.CONTEXT_URL"		, "'" + Constants.HOST_CONTEXT_URL		+ "'"	);
			put("Context.DOMAIN"			, "'" + getDomain()						+ "'"	);
			put("Context.PORT"				, "'" + getPort()						+ "'"	);
			put("Context.FILE_UPLOAD"		, "'" + Constants.CONTEXT_FILE_UPLOAD	+ "'"	);
			put("Context.FILE_DELETE"		, "'" + Constants.CONTEXT_FILE_DELETE	+ "'"	);
			put("Context.FILE_DELETES"		, "'" + Constants.CONTEXT_FILE_DELETES	+ "'"	);
			put("Context.FILE_VIEW"			, "'" + Constants.CONTEXT_FILE_VIEW		+ "'"	);
			put("Context.FILE_THUMB"		, "'" + Constants.CONTEXT_FILE_THUMB	+ "'"	);
			put("Context.FILE_JSONVIEW"		, "'" + Constants.CONTEXT_FILE_JSONVIEW	+ "'"	);
			put("Context.FILE_DOWNLOAD"		, "'" + Constants.CONTEXT_FILE_DOWNLOAD	+ "'"	);
			put("Context.FLASH"				, "'" + Constants.CONTEXT_FLASH			+ "'"	);
			put("Context.IMG_PATH"			, "'" + Constants.CONTEXT_IMG_PATH		+ "'"	);
			put("Context.FRAME_IMG_PATH"	, "'" + Constants.FRAME_IMAGE_PATH		+ "'"	);
			put("Context.IMAGE_PATH_BUTTON"	, "'" + Constants.IMAGE_PATH_BUTTON		+ "'"	);
			put("Context.IMAGE_PATH_ICON"	, "'" + Constants.IMAGE_PATH_ICON		+ "'"	);

			put("lmsFileDownload"			, "Context.FILE_DOWNLOAD"						);
			put("lmsFileView"				, "Context.FILE_VIEW"							);
			put("lmsTemplatePath"			, "Context.IMG_PATH"							);
			put("lmsImgPath"				, "Context.FRAME_IMG_PATH"						);
			put("commonFlashPath"			, "Context.FLASH"								);
			put("lmsIconPath"				, "Context.IMAGE_PATH_ICON"						);
			put("buttonImgPath"				, "Context.IMAGE_PATH_BUTTON"					);
			put("calendarImgPath"			, "Context.FRAME_IMG_PATH + '/calendar/'"		);
			put("contextMenuImgPath"		, "Context.FRAME_IMG_PATH + '/contextmenu/'"	);
			put("popupBoxImgPath"			, "Context.FRAME_IMG_PATH + '/popupbox/'"		);
			put("treeImgPath"				, "Context.FRAME_IMG_PATH + '/tree/'"			);
			put("popWinImgPath"				, "Context.FRAME_IMG_PATH + '/popwindow/'"		);
			put("menuImgPath"				, "Context.FRAME_IMG_PATH + '/menu/'"			);
			put("emoticonImgPath"			, "Context.FRAME_IMG_PATH + '/emoticon/'"		);
			put("commonImgPath"				, "Context.FRAME_IMG_PATH + '/common/'"			);
		}

		private String getDomain() {
			return Constants.HOST_URL.replaceAll("http://", "").replaceAll(":8080", "");
		}

		private String getPort() {
			if(Constants.HOST_URL.indexOf(":", 5) > 0) {
				return Constants.HOST_URL.substring(Constants.HOST_URL.indexOf(":", 5)+1);
			} else {
				return "";
			}
		}
	};


	@RequestMapping("/js/Context.js")
	public void commonConfigJs(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder script = new StringBuilder();
		script.append("/** Javascript Context Value **/\n");

		Set<String> keySet = JS_CONTEXT_VALUES.keySet();

		for (String key : keySet) {
			script.append(varGenerate(key));
		}
		script.append(" var localeKey = '"+UserBroker.getLocaleKey(request)+"'");
		this.responseWrite(response, script);
	}

	/**
	 * 선언문을 생성한다. Context. 이 없으면 var 선언문을 넣는다.
	 * @param key
	 * @return
	 */
	private String varGenerate(String key) {
		if(JS_CONTEXT_VALUES.get(key) == null) return "";

		String var = key + "=" + JS_CONTEXT_VALUES.get(key) + ";\n";
		if ( key.indexOf("Context.") < 0) {
			var = "var " + var;
		}
		return var;

	}

	/**
	 * Response에서 writer를 구해서 문자열을 출력한다.
	 * @param response 응답 인스턴스
	 * @param string 브라우져로 보내고자 하는 문자열
	 */
	private void responseWrite(HttpServletResponse response, StringBuilder string) {
		 response.setContentType("text/javascript;charset=utf-8");
		 response.setHeader(HTTP_CONTENT_LENGTH_HEADER, Integer.toString(string.length()));

		try {
			PrintWriter writer = response.getWriter();
			writer.print(string);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



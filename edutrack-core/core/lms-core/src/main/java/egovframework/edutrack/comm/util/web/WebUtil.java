package egovframework.edutrack.comm.util.web;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import org.apache.commons.lang.StringUtils;
import java.net.URLConnection;
import egovframework.edutrack.Constants;
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import egovframework.edutrack.Constants;

public class WebUtil {

	public static void setResponseToDownload(HttpServletRequest request,
			HttpServletResponse response, String fileName) throws Exception {
		String disposition = getDisposition(fileName, getBrowser(request));

		response.setHeader("Content-disposition", disposition);
		response.setHeader("Content-Transfer-Encoding", "binary");

		response.setContentType("application/octet-stream");
		// response.setContentLength((int) file.length());

		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
	}

	/**
	 * 해더에서 브라우져 정보를 구한다.
	 *
	 * @param request
	 * @return "MSIE" or "Chrome" or "Opera" or "Firefox"
	 */
	public static String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) { return "Opera"; }
		return "Firefox";
	}

	/**
	 * 해더에 필요한 Content-disposition 문자열을 생성한다.
	 *
	 * @param filename : 파일 이름
	 * @param browser : 브라우져 정보 getBrowser() 를 사용
	 * @return : Content-disposition 에 해당되는 문자열
	 * @throws Exception 지원되지 않는 브라우져일 경우 발생한다.
	 */
	public static String getDisposition(String filename, String browser) throws Exception {
		String dispositionPrefix = "attachment;filename=";
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename =

			"\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new RuntimeException("Not supported browser");
		}

		return dispositionPrefix + encodedFilename;
	}

	/**
	 * 시스템이 운영환경인지 여부를 반환한다.
	 * requestUri에 localhost이거나 local.edutrack.go.kr이 있을 경우 개발환경으로 판단하고 false를 반환한다.
	 * @param request
	 * @return
	 */
	public static boolean isProduct(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		return requestUrl.indexOf("localhost") < 0 && requestUrl.indexOf("local.edutrack.co.kr") < 0;
	}

	
}

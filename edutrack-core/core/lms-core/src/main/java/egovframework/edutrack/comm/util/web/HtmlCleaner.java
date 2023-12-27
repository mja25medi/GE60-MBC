package egovframework.edutrack.comm.util.web;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class HtmlCleaner {

	/**
	 * 정규식 검사 패턴 인터페이스
	 * @author SungKook
	 */
	private static interface Patterns {

		// 자바스크립트 태그와 스타일 태그사이의 모든 문자
		public static final Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
		public static final Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL);

		// HTML/XML tags
		public static final Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		//public static final Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");

		// entity references
		public static final Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");

		// repeated whitespace
		public static final Pattern WHITESPACE = Pattern.compile("\\s\\s+");

	}


	/**
	 * {@literal <script *> Blah~ </script>} 및 {@literal <style *> Blah~ </style>} 태그를 제거
	 * @param s
	 * @return
	 */
	public static String cleanScript(String s) {
		if (StringUtils.isEmpty(s)) {
			return s;
		}

		s = Patterns.SCRIPTS.matcher(s).replaceAll("");
		s = Patterns.STYLE.matcher(s).replaceAll("");

		return s;
	}

	/**
	 * {@literal script, style}를 포함한 일반 {@literal html, xml, xhtml}태그들까지 제거
	 * @param s
	 * @return
	 */
	public static String cleanTag(String s) {
		s = cleanScript(s);

		if (StringUtils.isEmpty(s)) {
			return s;
		}

		s = Patterns.TAGS.matcher(s).replaceAll("");

		return s;
	}

	/**
	 * 모든 태그 제거와 엔티티명칭 패턴 및 반복 공백 제거.
	 * @param s
	 * @return
	 */
	public static String cleanAllTag(String s) {
		s = cleanTag(s);

		if (StringUtils.isEmpty(s)) {
			return s;
		}

		s = Patterns.ENTITY_REFS.matcher(s).replaceAll("");
		s = Patterns.WHITESPACE.matcher(s).replaceAll("");

		return s;
	}
	/**
	 * 모든 태그 제거와 엔티티명칭 패턴 제거. (&amp; &nbsp;)
	 * @param s
	 * @return
	 */
	public static String cleanEntityRefs(String s) {
		s = cleanTag(s);

		if (StringUtils.isEmpty(s)) {
			return s;
		}

		s = Patterns.ENTITY_REFS.matcher(s).replaceAll("");

		return s;
	}

	/**
	 * 줄바꿈, 탭 등을 포함한 모든 중복되는 공백을 파라매터 문자로 치환(원하는 공백 or 줄바꿈등)
	 * @param string
	 * @return
	 */
	public static String cleanWhitespace(String string, String replaceChar) {
		if (StringUtils.isEmpty(string)) {
			return string;
		}

		return Patterns.WHITESPACE.matcher(string).replaceAll(replaceChar);
	}

	/**
	 * 줄바꿈, 탭 등을 포함한 모든 중복되는 공백을 " "로 치환
	 * @param string
	 * @return
	 */
	public static String cleanWhitespace(String string) {
		return cleanWhitespace(string, " ");
	}

	/**
     * description: 필터링 하고자 하는 문자가 있을때 문자 치환을 하고자 하는 경우
     * @param pStr
     * @return
     */
    public static String chkXssReplace(String pStr) {
    	pStr = StringUtil.nvl(pStr);
    	String result       = pStr;
    	String upperTmpStr =  pStr.toUpperCase();

		//HTML 주석 구문은 변환하지 않는다.
		/*if(upperTmpStr.indexOf("--") != -1){
			result = xssReplace(result,"--","－－");
		}*/
		if(upperTmpStr.indexOf("\"") != -1)			{		result = xssReplace(result,"\"","　”　");					}
        if(upperTmpStr.indexOf("'") != -1)			{		result = xssReplace(result,"'","　’　");					}
		if(upperTmpStr.indexOf("SCRIPT") != -1)		{		result = xssReplace(result,"<SCRIPT","< ＳＣＲＩＰＴ");		}
		if(upperTmpStr.indexOf("SCRIPT") != -1)		{		result = xssReplace(result,"SCRIPT:"," Ｓ Ｃ Ｒ Ｉ Ｐ Ｔ  :");		}
		if(upperTmpStr.indexOf("SCRIPT") != -1)		{		result = xssReplace(result,"</SCRIPT","< / ＳＣＲＩＰＴ");		}
        if(upperTmpStr.indexOf("&#0000") != -1)		{		result = xssReplace(result,"&#0000","＆＃0000");		}
		if(upperTmpStr.indexOf("&{") != -1)			{		result = xssReplace(result,"&{","＆｛");				}
		if(upperTmpStr.indexOf("IFRAME") != -1)		{		result = xssReplace(result,"IFRAME","ＩＦＲＡＭＥ");	}
		if(upperTmpStr.indexOf("HTTP") != -1)		{		result = xssReplace(result,"HTTP://","ＨＴＴＰ ://");		}
		if(upperTmpStr.indexOf("HTTPS") != -1)		{		result = xssReplace(result,"HTTPS://","ＨＴＴＰＳ ://");		}
		if(upperTmpStr.indexOf("MAILTO") != -1)		{		result = xssReplace(result,"MAILTO://","ＭＡＩＬＴＯ ://");		}
		if(upperTmpStr.indexOf("FTP") != -1)		{		result = xssReplace(result,"FTP://","ＦＴＰ ://");		}
		if(upperTmpStr.indexOf("HTTP-EQUIV") != -1)	{		result = xssReplace(result,"HTTP-EQUIV","ＨＴＴＰ－ＥＱＵＩＶ");		}

		if(upperTmpStr.indexOf("&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116") != -1){
			result = xssReplace(result,"&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116","ＳＣＲＩＰＴ");
		}

		if(upperTmpStr.indexOf("&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74") != -1){
			result = xssReplace(result,"&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74","ＳＣＲＩＰＴ");
		}

		/**
		 * 위 비교 치환의 대상 문자열은 시스템 상황에 따라 추가 제거하여 사용한다.
		 * RequestManager 에서 시스템 전체에 영향을 미치는 경우는 아래의 내용을 참고
		 * 		ex) 그냥 SCRIPT 비교는 사용하고 있는 서블릿의 파라미터로 사용되고 있으므로 <SCRIPT 로 조회
		 *     		controller.study.ToronServlet.performToronRecom => box.put("p_process","StudyPostScriptView");
		 * 			위 예제와 같이 대상 문자열은 다른 용도로 사용되고 있는지 확인이 필요하다
		 * 시스템 전체에 적용 하지 않고 필요한 부분에서만 호출 하여 사용하는 경우는 위 내용을 무시하고 상황에 맞게 추가 사용 가능
		 */



		return result;

    }

    /**
     * description: 필터링 하고자 하는 문자가 있을때 문자 치환을 하고자 하는 경우 <br>
     * 웹에디터 사용으로 스크립트 와 같은 위험한것만 막는 경우
     * @param pStr
     * @return
     */
    public static String chkXssReplaceSimple(String pStr) {
    	pStr = StringUtil.nvl(pStr);
    	String result       = pStr;
    	String upperTmpStr =  pStr.toUpperCase();

		if(upperTmpStr.indexOf("SCRIPT") != -1)		{		result = xssReplace(result,"<SCRIPT","< ＳＣＲＩＰＴ");		}
		if(upperTmpStr.indexOf("SCRIPT") != -1)		{		result = xssReplace(result,"SCRIPT:"," Ｓ Ｃ Ｒ Ｉ Ｐ Ｔ  :");		}
		if(upperTmpStr.indexOf("SCRIPT") != -1)		{		result = xssReplace(result,"</SCRIPT","< / ＳＣＲＩＰＴ");		}
        if(upperTmpStr.indexOf("&#0000") != -1)		{		result = xssReplace(result,"&#0000","＆＃0000");		}
		if(upperTmpStr.indexOf("IFRAME") != -1)		{		result = xssReplace(result,"IFRAME","ＩＦＲＡＭＥ");	}
		if(upperTmpStr.indexOf("MAILTO") != -1)		{		result = xssReplace(result,"MAILTO://","ＭＡＩＬＴＯ ://");		}
		if(upperTmpStr.indexOf("FTP") != -1)		{		result = xssReplace(result,"FTP://","ＦＴＰ ://");		}
		if(upperTmpStr.indexOf("HTTP-EQUIV") != -1)	{		result = xssReplace(result,"HTTP-EQUIV","ＨＴＴＰ－ＥＱＵＩＶ");		}

		if(upperTmpStr.indexOf("&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116") != -1){
			result = xssReplace(result,"&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116","ＳＣＲＩＰＴ");
		}

		if(upperTmpStr.indexOf("&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74") != -1){
			result = xssReplace(result,"&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74","ＳＣＲＩＰＴ");
		}

		/**
		 * 위 비교 치환의 대상 문자열은 시스템 상황에 따라 추가 제거하여 사용한다.
		 * RequestManager 에서 시스템 전체에 영향을 미치는 경우는 아래의 내용을 참고
		 * 		ex) 그냥 SCRIPT 비교는 사용하고 있는 서블릿의 파라미터로 사용되고 있으므로 <SCRIPT 로 조회
		 *     		controller.study.ToronServlet.performToronRecom => box.put("p_process","StudyPostScriptView");
		 * 			위 예제와 같이 대상 문자열은 다른 용도로 사용되고 있는지 확인이 필요하다
		 * 시스템 전체에 적용 하지 않고 필요한 부분에서만 호출 하여 사용하는 경우는 위 내용을 무시하고 상황에 맞게 추가 사용 가능
		 */



		return result;

    }

    /**
     * description: 대소문자 구분없이 해당 문자열 변환
     * @param strTarget
     * @param strSearch
     * @param strReplace
     * @return
     */
    public static String xssReplace(String strTarget, String strSearch, String strReplace) {
        String strCheck = new String(strTarget);
		String strTmpCheck = new String(strTarget.toLowerCase());
		StringBuffer strBuf = new StringBuffer();

		while(strCheck.length() != 0)
		{
			int begin = strTmpCheck.indexOf(strSearch.toLowerCase());

			if(begin == -1) {
				strBuf.append(strCheck);
				break;
			} else {
				int end = begin + strSearch.length();
				strBuf.append(strCheck.substring(0, begin));
				strBuf.append(strReplace);
				strCheck = strCheck.substring(end);
				strTmpCheck = strTmpCheck.substring(end);

			}
		}
		return new String(strBuf);
	}
}
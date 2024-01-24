package egovframework.edutrack.comm.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import egovframework.edutrack.Constants;


/*******************************************************************************
 * 프로그램 : UserBroker.java
 * 모 듈 명 :
 * 설    명 :
 * 테 이 블 :
 * 작 성 자 : MediopiaTech
 * 작 성 일 : 2014. 6. 28.
 ******************************************************************************/
public class UserBroker {

	private static UserBroker userBrokerins = null;
	/**
	 *
	 */
	public UserBroker() {
		super();
	}

	public static UserBroker getInstance(){
		if(userBrokerins == null) {
			userBrokerins = new UserBroker();
		}

		return userBrokerins;
	}

	/**
	 * req를 해석해서 현재 로그인한 고객 UserId를 반환한다.
	 *
	 * @return 로그인한 고객 UserId, 로그인 하지 않았다면 null을 반환
	 */
	public static final String getUserId(HttpServletRequest request) {
		return getSessionValue(request, Constants.LOGIN_USERID);
	}

	/**
	 * 관리자 타입을 가져온다.
	 * @param request
	 */
	public static final String getAdmType(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_ADMTYPE);
	}

	/**
	 * 사이트 관리자 타입을 가져온다.
	 * @param request
	 */
	public static final String getMngType(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_MNGTYPE);
	}

	/**
	 * 사용자 타입을 가져온다.
	 * @param request
	 */
	public static final String getUserType(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_USERTYPE);
	}

	/**
	 * 사용자 번호를 가져온다.
	 * @param request
	 */
	public static final String getUserNo(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_USERNO);
	}


	/**
	 * 사용자 이름을 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getUserName(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_USERNAME);
	}

	/**
	 * 세션의 메뉴코드를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getMenuCode(HttpServletRequest request){
		return getSessionValue(request, Constants.CUR_MENU_CODE);
	}

	/**
	 * 세션의 메뉴명을 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getMenuName(HttpServletRequest request){
		return getSessionValue(request, Constants.CUR_MENU_NAME);
	}

	/**
	 * 세션의 메뉴명을 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getMenuLocation(HttpServletRequest request){
		return getSessionValue(request, Constants.MENU_LOCATION);
	}

	/**
	 * 로그인 여부를 반환한다. (USERNO로 판단한다.)
	 * @param request
	 * @return true : 로그인, false : 비로그인.
	 */
	public static final boolean isLogin(HttpServletRequest request) {
		return StringUtil.isNotNull(getSessionValue(request, Constants.LOGIN_USERNO));
	}

	/**
	 * 언어키를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getLocaleKey(HttpServletRequest request){
		String localeKey = getSessionValue(request, Constants.SYSTEM_LOCALEKEY);
		if(ValidationUtils.isEmpty(localeKey)) localeKey = Constants.LANG_DEFAULT;
		return localeKey;
	}

	/**
	 * 세션의 강의실 권한 그룹 코드를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getClassUserType(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_CLASSUSERTYPE);
	}


	/**
	 * 회원의 사이트 코드를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserOrgCd(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_ORGCD);
	}

	/**
	 * 회원의 사이트명을 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserOrgNm(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_ORGNM);
	}
	/**
	 * 회원의 사이트명을 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserDomainNm(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_DOMAINNM);
	}

	/**
	 * 회원의 사이트 메인 로고를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserTopLogo(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_TOP_LOGO);
	}

	/**
	 * 회원의 사이트 서브 로고1를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserSubLogo1(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_SUB_LOGO1);
	}

	/**
	 * 회원의 사이트 서브 로고 1 URL을 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserSubLogo1Url(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_SUB_LOGO1_URL);
	}

	/**
	 * 회원의 사이트 서브 로고2를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserSubLogo2(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_SUB_LOGO2);
	}

	/**
	 * 회원의 사이트 서브 로고2 URL 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserSubLogo2Url(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_SUB_LOGO2_URL);
	}

	/**
	 * 회원의 사이트 관리자 로고를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserAdmLogo(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_ADM_LOGO);
	}

	/**
	 * 회원의 사이트 홈페이지 Footer 를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserWwwFooter(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_WWW_FOOTER);
	}

	/**
	 * 회원의 사이트 관리자 Footer 를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserAdmFooter(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_ADM_FOOTER);
	}

	/**
	 * 회원의 사이트 레이아웃 템플릿 코드를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserLayoutTpl(HttpServletRequest request){
		String layoutTpl = getSessionValue(request, Constants.SAAS_LAYOUT_TPL);
		if(ValidationUtils.isEmpty(layoutTpl)) layoutTpl = "center";
		return layoutTpl;
	}

	/**
	 * 회원의 사이트 색상 템플릿 코드를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserColorTpl(HttpServletRequest request){
		String colorTpl = getSessionValue(request, Constants.SAAS_COLOR_TPL);
		if(ValidationUtils.isEmpty(colorTpl)) colorTpl = "001";
		return colorTpl;
	}
	
	/**
	 * 회원의 사이트 Prefix URL를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getPrefixHomeUrl(HttpServletRequest request){

		String returnUrl="tpl/";
		String colorTpl = getUserColorTpl(request);
		if(ValidationUtils.isEmpty(colorTpl)) colorTpl = "001";
		
		String layoutAuth = getLayoutAuth(request);
		
		if(layoutAuth.equals("home")||layoutAuth.equals("lec")) {
			returnUrl += layoutAuth + "/" + colorTpl + "/";
		} else if (layoutAuth.equals("mng")) {
			returnUrl += layoutAuth + "/";
		} else if (layoutAuth.equals("adm")) {
			returnUrl += layoutAuth + "/";
		}
		
		return returnUrl;

	}
	/**
	 * 회원의 사이트 Prefix URL를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getHomeUrl(HttpServletRequest request){
		return "home/";
	}

	private static final String getSessionValue(HttpServletRequest request, String key) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(key);
		return StringUtil.nvl(value);
	}

	/**
	 * 개설 과정 명 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getCreCrsNm(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_CRSCRENM);
	}

	/**
	 * 개설 과정 코드를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getCreCrsCd(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_CRSCRECD);
	}
	
	/**
	 * 과정 코드를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getSbjCd(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_SBJCD);
	}
	
	/**
	 * 개설 과정 코드를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getCrsCd(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_CRSCD);
	}

	/**
	 * 사용자의 수강 번호를 가져온다.
	 * @param request
	 * @return
	 */
	public static final String getStudentNo(HttpServletRequest request){
		return getSessionValue(request, Constants.LOGIN_STUDENTNO);
	}

    /**
	 * User Device Set
	 * @param request
	 * @param value
	 */
	public static final void setUserDevice(HttpServletRequest request, String value) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USER_DEVICE, value);
	}

	/**
	 * User Device Get
	 * @param request
	 * @return
	 */
	public static final String getUserDevice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(Constants.USER_DEVICE);
		return StringUtil.nvl(value);
	}

	/**
	 * Mobile App Type Set
	 * @param request
	 * @param value
	 */
	public static final void setAppType(HttpServletRequest request, String value) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USER_APP_TYPE, value);
	}

	/**
	 * Mobile App Type Get
	 * @param request
	 * @return
	 */
	public static final String getAppType(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(Constants.USER_APP_TYPE);
		return StringUtil.nvl(value);
	}

	/**
	 * 사용자 OS Set
	 * @param request
	 * @param value
	 */
	public static final void setUserOS(HttpServletRequest request, String value) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USER_OS, value);
	}

	/**
	 * 사용자 OS Get
	 * @param request
	 * @return
	 */
	public static final String getUserOS(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(Constants.USER_OS);
		return StringUtil.nvl(value);
	}

	/**
	 * 사용자 브라우져 Set
	 * @param request
	 * @param value
	 */
	public static final void setUserBrowser(HttpServletRequest request, String value) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USER_BROWSER, value);
	}

	/**
	 * 사용자 브라우져 Get
	 * @param request
	 * @return
	 */
	public static final String getUserBrowser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute(Constants.USER_BROWSER);
		return StringUtil.nvl(value);
	}

	/**
	 * 모바일 device 유형  Set
	 * @param request
	 * @param value
	 */
	public static final void setMobileType(HttpServletRequest request, String value) {
		HttpSession session = request.getSession();
		session.setAttribute("MOBILE_TYPE", value);
	}

	/**
	 * 모바일 device 유형 Get
	 * @param request
	 * @return
	 */
	public static final String getMobileType(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute("MOBILE_TYPE");
		return StringUtil.nvl(value);
	}
	
	/**
	 * 회원의 사이트 회원가입 사용여부를 반환한다
	 * @param request
	 * @return
	 */
	public static final String getUserMbrAplcUseYn(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_MBR_APLC_USE);
	}
	
	/**
	 * 회원의 사이트 통합회원 사용여부 Set
	 * @param request
	 * @param value
	 */
	public static final void setItgrtMbrUseYn(HttpServletRequest request, String value) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SAAS_ITGRT_MBR_USE_YN, value);
	}
	
	/**
	 * 회원의 사이트 통합회원 사용여부를 반환한다
	 * @param request
	 * @return
	 */
	public static final String getItgrtMbrUseYn(HttpServletRequest request){
		return getSessionValue(request, Constants.SAAS_ITGRT_MBR_USE_YN);
	}
	
	/**
	 * 회원의 사이트 통합회원 사용여부를 반환한다
	 * @param request
	 * @return
	 */
	public static final String getContentsAuthCd(HttpServletRequest request){
		return getSessionValue(request, Constants.CONTENTS_AUTH_CD);
	}

	/**
	 * 회원의 사이트 색상 템플릿 코드를 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getUserLayoutTplType(HttpServletRequest request){
		String tplType = getSessionValue(request, Constants.SAAS_LAYOUT_TPL_TYPE);
		if(ValidationUtils.isEmpty(tplType)) tplType = "sub";
		return tplType;
	}
	/**
	 * 레이아웃 탬플릿 권한을 반환한다.
	 * @param request
	 * @return
	 */
	public static final String getLayoutAuth(HttpServletRequest request){
		String tplType = getSessionValue(request, Constants.SAAS_LAYOUT_AUTH);
		if(ValidationUtils.isEmpty(tplType)) tplType = "home";
		if("lec".equals(tplType)) tplType = "home";
		return tplType;
	}

}
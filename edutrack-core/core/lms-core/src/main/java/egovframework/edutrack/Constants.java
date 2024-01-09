package egovframework.edutrack;

import org.apache.commons.configuration.Configuration;

import egovframework.edutrack.comm.config.ConfigurationFactory;

public class Constants {

	// Framework의 Confguration에서 사용하는 변수 정의
	public static final int		FRAMEWORK		= 0;
	public static final int		WEBCONTEXT 		= 1;

	// Config 로드
	public static Configuration	framework				= ConfigurationFactory.getInstance().getConfiguration(Constants.FRAMEWORK);
	public static Configuration	webContext				= ConfigurationFactory.getInstance().getConfiguration(Constants.WEBCONTEXT);

	// System Name
	public final static String	SYSTEM_NAME				= webContext.getString("edutrack.name");

	// LMS Data path 환경
	public final static String	LMS_DATA_CONTEXT		= webContext.getString("edutrack.context");
	public final static String	LMS_IMAGE_SET			= framework.getString("framework.imageset");
	public final static String	LMS_TEMPLATE			= framework.getString("framework.template");
	public final static String	LMS_CONTEXT				= framework.getString("framework.lmsdata.context");

	// langueag 관련 설정
	public final static String	LANG_SUPPORT			= framework.getString("framework.language.support");
	public final static String	LANG_DEFAULT			= framework.getString("framework.language.default");
	public final static String	LANG_SITE				= framework.getString("framework.language.site");
	public final static String  LANG_MULTIUSE			= framework.getString("framework.language.multiuse");

	// 메시지 사용 여부 관련 설정
	public final static String	MSG_SMS					= framework.getString("framework.message.sms");
	public final static String	MSG_EMAIL				= framework.getString("framework.message.email");
	public final static String	MSG_NOTE				= framework.getString("framework.message.note");

	// 문자 발송 에이전트 설정
	public final static String	MSG_SMS_AGENT					= framework.getString("framework.sms.agent");
	
	// 메시지 표시 관련 설정
	public final static String	MSG_SMS_ADDR			= framework.getString("framework.message.sms.addr");
	public final static String	MSG_SMS_NAME			= framework.getString("framework.message.sms.name");
	public final static String	MSG_EMAIL_ADDR			= framework.getString("framework.message.email.addr");
	public final static String	MSG_EMAIL_NAME			= framework.getString("framework.message.email.name");

	// exception db log 관련 설정
	public final static String  EXCEPTION_DBLOG_USE		= framework.getString("framework.dblog.exception");
	
	// Mysql 버전별 쿼리 분기용 설정
	public final static String  DATABASE_DB_TYPE		= framework.getString("framework.database.db_type");
	public final static String  DATABASE_DB_VERSION		= framework.getString("framework.database.db_version");

	// Ajax Message 출력 관련 설정
	public final static String  AJAX_MESSAGE_USE		= framework.getString("framework.ajax.message");

	//* javascript와 연동될 동일한 환경값 설정 **//
	/** 웹어플리케이션 컨텍스트 경로<br>{@code /uniedu}**/
	public final static String	CONTEXT_ROOT			= webContext.getString("edutrack.context");

	/** 웹어플리케이션 이미지 루트 경로<br>{@code /uniedu/img}**/
	public final static String	CONTEXT_IMG_PATH		= CONTEXT_ROOT + framework.getString("framework.path.imageroot");

	/** 웹어플리케이션 프레임워크의 이미지 경로<br>{@code /uniedu/img/framework}**/
	public final static String	FRAME_IMAGE_PATH		= CONTEXT_ROOT + framework.getString("framework.path.image");

	/** 웹어플리케이션 프레임워크의 버튼 이미지 경로<br>{@code /uniedu/img/framework/button/}**/
	public final static String	IMAGE_PATH_BUTTON		= FRAME_IMAGE_PATH + "/button/";

	/** 웹어플리케이션 프레임워크의 아이콘 이미지 경로<br>{@code /uniedu/img/framework/icon/}**/
	public final static String	IMAGE_PATH_ICON			= FRAME_IMAGE_PATH + "/icon/";

	/** 웹어플리케이션 도메인 설정 **/
	public final static String PRODUCT_DOMAIN			= framework.getString("framework.product.domain");
	public final static String PRODUCT_SUB_DOMAIN_TUTOR	= framework.getString("framework.product.sub.domain.tutor");
	public final static String PRODUCT_SUB_DOMAIN_ADMIN	= framework.getString("framework.product.sub.domain.admin");

	public final static String ADMIN_DOMAIN				= framework.getString("edutrack.url.admin");
	
	public final static String MGR_SITE_DOMAIN			= framework.getString("framework.manage.site.org.domain");
	public final static String MGR_SITE_CD				= framework.getString("framework.manage.site.org.code");

	/** 서버 호스트 URL <br>{@code http://dev.edutrack.go.kr} **/
	public final static String	HOST_URL				= webContext.getString("edutrack.url");

	/** 서버 호스트 FileServer URL <br>{@code http://file.edutrack.go.kr} **/
	public final static String	FILESERVER_URL			= framework.getString("framework.fileserver.url");

	/** 서버 호스트 URL <br>{@code http://dev.edutrack.go.kr} **/
	public final static String	ADMIN_PORT				= framework.getString("framework.product.admin.port");

	/** 서버 호스트 URL <br>{@code http://www.edutrack.go.kr} **/
	public final static String	PRODUCT_HOST_URL		= webContext.getString("edutrack.product");

	/** 서브도메인 서비스 : domain, 가상디렉토리 서비스 : context <br>{@code subdomain} **/
	public final static String	PRODUCT_SERVICE_TYPE		= framework.getString("framework.product.service_type");
	
	
	/** 서버 호스트 URL과 컨텍스트 URL을 합친 경로 <br>{@code http://dev.edutrack.go.kr/} **/
	public final static String	HOST_CONTEXT_URL		= HOST_URL + CONTEXT_ROOT;

	/** 파일 보기 컨트롤러 경로 <br>{@code /app/file/view/} **/
	public final static String	CONTEXT_FILE_VIEW		= CONTEXT_ROOT + "/app/file/view/";

	/** 파일 JSON 정보 조회 컨트롤러 경로 <br>{@code /app/file/jsonview/} **/
	public final static String	CONTEXT_FILE_JSONVIEW	= CONTEXT_ROOT + "/app/file/jsonview/";

	/** 파일 썸네일 보기 경로 <br>{@code /app/file/thumb/} **/
	public final static String	CONTEXT_FILE_THUMB		= CONTEXT_ROOT + "/app/file/thumb/";

	/** 파일 다운로드 경로 <br>{@code /app/file/download/} **/
	public final static String	CONTEXT_FILE_DOWNLOAD	= CONTEXT_ROOT + "/app/file/download/";

	/** 파일 업로드 경로 <br>{@code /app/file/ajaxupload/} **/
	public final static String	CONTEXT_FILE_UPLOAD		= CONTEXT_ROOT + "/app/file/ajaxupload/";

	/** 파일 삭제 요청 경로 <br>{@code /app/file/delete/} **/
	public final static String	CONTEXT_FILE_DELETE		= CONTEXT_ROOT + "/app/file/delete/";

	/** 파일 일괄삭제 요청 경로 <br>{@code /app/file/deletes/} **/
	public final static String	CONTEXT_FILE_DELETES	= CONTEXT_ROOT + "/app/file/deletes/";

	/** 플래쉬 라이브러리 요청 경로 <br>{@code /libs} **/
	public final static String	CONTEXT_FLASH			= CONTEXT_ROOT + "/libs";

	/** 다음 에디터 HTML 요청 경로 <br>{@code /libs/daumeditor/daumeditor.jsp} **/
	public final static String	EDITOR_URL				= CONTEXT_ROOT + "/libs/daumeditor/daumeditor.jsp";
	/** 다음 에디터 본문 정렬 기본 값 <br>{@code "C" - 가운데정렬} **/
	public final static String	EDITOR_IMAGE_ALIGN		= "C";		// center

	// 서버 OS 구분
	public final static String	SERVER_TYPE				= framework.getString("framework.system.server_type");
	public final static String	SERVERTYPE_WINDOWS		= "windows";
	public final static String	SERVERTYPE_UNIX			= "unix";

	// 시스템 기본 인코딩
	public static final String	ENCODING_SYSTEM			= framework.getString("framework.encoding.system");
	// 주소 Redirect Encoding
	public static final String	ENCODING_REDIRECT		= framework.getString("framework.encoding.redirect");
	// Message Encoding
	public static final String	ENCODING_MESSAGE		= framework.getString("framework.encoding.message");

	// 업로드 파일 저장 경로
	public static final String	FILE_STORAGE_PATH		= framework.getString("framework.filestorage.path");
	// 콘텐츠 파일 저장 경로
	public static final String	CONTENTS_STORAGE_PATH	= framework.getString("framework.contentstorage.path");

	// 파일 업로드 제한 용량
	public static final int		MAX_POST_SIZE			= framework.getInt("framework.fileupload.max_post_size");
	// 콘텐츠 업로드 제한 용량
	public static final int		MAX_CONTENTS_POST_SIZE	= framework.getInt("framework.fileupload.contents.max_post_size");

	// 페이지당 목록수, 페이지 네비게이션 표시수, 사진게시판 표시수
	public static final int		LIST_SCALE				= framework.getInt("framework.list.scale");
	public static final int		LIST_PAGE_SCALE			= framework.getInt("framework.list.page_scale");
	public static final int		LIST_PHOTO_BOARD_SCALE	= framework.getInt("framework.list.photo_scale");

	// 권한체크 여부
	// public static final String AUTH_CHECK_YN = config.getString("framework.auth.checkYn");

	// 로그인 관련 설정
	public static final String	LOGIN_OK				= "LOGIN";
	public static final String	LOGIN_USERID			= "USERID";
	public static final String	LOGIN_USERNO			= "USERNO";
	public static final String	LOGIN_USERNAME			= "USERNAME";
	public static final String	LOGIN_USERTYPE			= "USERTYPE";
	public static final String 	LOGIN_USERSTS			= "USERSTS";													//사용자 승인 여부
	public static final String	LOGIN_ADMTYPE			= "ADMTYPE";
	public static final String	LOGIN_MNGTYPE			= "MNGTYPE";
	public static final String 	LOGIN_DEPTCD			= "DEPTCD";

	// ASP 관련 설정
	public static final String  SAAS_ORGNM				= "ORGNM";
	public static final String  SAAS_DOMAINNM			= "DOMAINNM";
	public static final String  SAAS_ORGCD				= "ORGCD";
	public static final String  SAAS_LAYOUT_TPL			= "LAYOUTTPL";
	public static final String  SAAS_LAYOUT_TPL_TYPE	= "TYLTYPE";
	public static final String  SAAS_LAYOUT_AUTH		= "LAYOUTAUTH";
	public static final String  SAAS_COLOR_TPL			= "COLORTPL";
	public static final String  SAAS_TOP_LOGO			= "TOPLOGOSN";
	public static final String  SAAS_SUB_LOGO1			= "SUBLOGO1SN";
	public static final String  SAAS_SUB_LOGO1_URL		= "SUBLOGO1URL";
	public static final String  SAAS_SUB_LOGO2			= "SUBLOGO2SN";
	public static final String  SAAS_SUB_LOGO2_URL		= "SUBLOGO2URL";
	public static final String  SAAS_ADM_LOGO			= "ADMLOGOSN";
	public static final String  SAAS_SUB_IMAGE			= "SUBIMGSN";
	public static final String  SAAS_SUB_TXTIMG			= "SUBTXTIMGSN";
	public static final String  SAAS_SUB_TEXT			= "SUBTXTSTR";
	public static final String  SAAS_LEC_IMAGE			= "LECIMGSN";
	public static final String  SAAS_LEC_TXTIMG			= "LECTXTIMGSN";
	public static final String  SAAS_LEC_IMGNM			= "LECTXTIMGNM";
	public static final String  SAAS_LEC_TEXT			= "LECTXTSTR";
	public static final String  SAAS_LEC_FILEURL		= "LECCOLORSTR";
	public static final String  SAAS_LEC_CONNURL		= "LECCONNURL";
	public static final String  SAAS_LEC_CONNTRGT		= "LECCONNTRGT";
	public static final String  SAAS_WWW_FOOTER			= "WWWFOOTER";
	public static final String  SAAS_ADM_FOOTER			= "ADMFOOTER";
	public static final String  SAAS_MBR_APLC_USE			= "MBRAPLCUSE";
	public static final String  SAAS_ITGRT_MBR_USE_YN	= "ITGRTMBRUSEYN";
	public static final String  CONTENTS_AUTH_CD	= "CONTENTSAUTHCD";

	// 과정 관련 설정.
	public static final String	LOGIN_STUDENTNO			= "STUDENTNO";
	public static final String	LOGIN_CRSCRECD			= "CRSCRECD";													// 강의 아이디
	public static final String	LOGIN_SBJCD				= "SBJCD";														// 강의 아이디
	public static final String	LOGIN_CRSCD				= "CRSCD";														// 강의 아이디
	public static final String	LOGIN_CRSCRENM			= "CRSCRENM";													// 강의 아이디
	public static final String	LOGIN_CREYEAR			= "CREYEAR";													// 강의 아이디
	public static final String	LOGIN_CRETERM			= "CRETERM";													// 강의 아이디
	public static final String	LOGIN_CLASSUSERTYPE		= "CLASSUSERTYPE";												// 강의실 권한 그룹
	public static final String	LOGIN_CRSMTHD			= "CRSMTHD";
	public static final String	LOGIN_CRSTYPE			= "CRSTYPE";
	public static final String  LOGIN_CRSDURATION		= "CRSDURATION";


	// 시스템의 언어키
	public static final String  SYSTEM_LOCALEKEY			= "LOCALEKEY";													// 언어셋
	
	// 메뉴 관련 변수명 정의
	public static final String	MENU_LOCATION			= "LOCATION";													// 현제 페이지
	public static final String	CUR_MENU_NAME			= "MENUNAME";													// 메뉴 명
	public static final String	CUR_MENU_CODE			= "MENUCODE";													// 메뉴 코드
	public static final String	CUR_MENU_TITLE			= "MENUTITLE";													// 메뉴 타이틀 이미지
	public static final String	CUR_MENU_CHRG_DEPT		= "MENUCHRGDEPT";												// 메뉴의 담당자 부서
	public static final String	CUR_MENU_CHRG_NAME		= "MENUCHRGNAME";												// 메뉴의 담당자 명
	public static final String	CUR_MENU_CHRG_PHONE		= "MENUCHRGPHONE";												// 메뉴의 담당자 면락처
	public static final String	ROT_MENU_CODE			= "ROOTMENUCODE";												// 최상위 메뉴 코드
	public static final String	ROT_MENU_NAME			= "ROOTMENUNAME";												// 최상위 메뉴 명
	public static final String	ROT_MENU_GROUP			= "ROOTMENUGROUP";												// 최상위 메뉴 그룹
	public static final String	TEMP_CUR_MENU_CODE		= "TEMPMENUCODE";												// 임시 메뉴 코드(관리자에서 미리보기시 사용)
	public static final String	TEMP_CUR_MENU_NAME		= "TEMPMENUNAME";												// 임시 메뉴 명(관리자에서 미리보기시 사용)
	public static final String	TEMP_MENU_LOCATION		= "TEMPLOCATION";												// 임시 페이지(관리자에서 미리보기시 사용)


	/** 컴퓨터의 이름 정보를 시스템 설정에서 가져와서 반환한다. **/
	public static final String	HOST_NAME				= (SERVER_TYPE.equals(SERVERTYPE_UNIX))
																? System.getenv("HOSTNAME")
																: System.getenv("COMPUTERNAME");

	public static final String	FILE_BLOCKED_EXT		= framework.getString("FILE.BLOCKED_EXT");

	/** DB에 저장 항목의 구분자 **/
	public static final String	SEPERATER_DB			= "|";
	/** 파라매터 항목의 구분자 **/
	public static final String	SEPERATER_PARAM			= "!@!";

	// KISA key.dat 관련 설정
	public static final String KISAKEYFILE = framework.getString("kisa.keyfile.path");

	// User Agent Set parameter
	public static final String USER_DEVICE			= "USER_DEVICE";	// Device Type
	public static final String USER_APP_TYPE		= "APP_TYPE";		// Mobice App Type
	public static final String USER_OS				= "USER_OS";		// 사용자 OS
	public static final String USER_BROWSER			= "USER_BROWSER";	// 사용자 브라우져

	// Client(사용자) 장치 구분
	public static final String DEVICE_PC			= "PC";
	public static final String DEVICE_MOBILE		= "MOBILE";

	// Client OS 구분
	public static final String OS_TYPE_WINDOWS[]	= framework.getStringArray("framework.mobile.os.windows");
	public static final String OS_TYPE_ANDROID[]	= framework.getStringArray("framework.mobile.os.android");
	public static final String OS_TYPE_IOS[]		= framework.getStringArray("framework.mobile.os.ios");
	public static final String OS_WINDOWS			= "windows";
	public static final String OS_ANDROID			= "android";
	public static final String OS_IOS				= "ios";

	// Client Browser Type
	public static final String BROWSER_TYPE[]		= framework.getStringArray("framework.browser_type");
	public static final String BROWSER_IE			= "MSIE";
	public static final String BROWSER_FIREFOX		= "Firefox";
	public static final String BROWSER_SAFARI		= "Safari";
	public static final String BROWSER_OPERA		= "Opera";
	public static final String BROWSER_CHROME		= "Chrome";

	// Mobile App Type
	public static final String APP_TYPE[]			= framework.getStringArray("framework.mobile.app_type");
	public static final String APP_ANDROID			= "Android-App";	// Android App
	public static final String APP_IOS				= "iOS-App";		// iOS(iPhone, iPod, iPad) App

	// Mobile Device 인식 문자열
	public static final String MOBILE_DEVICE[]		= framework.getStringArray("framework.mobile.device_type");
	public static final String MOBILE_DEVICE_TAB[]	= framework.getStringArray("framework.mobile.tablet_type");

	// Mobile Device 유형
	public static final String MOBILE_PHONE			= "phone";
	public static final String MOBILE_TABLET		= "tablet";

	//-- 미디어 서버 관련 설정
	public static final String WOWZA_USE 			= framework.getString("framework.wowza.use");
	public static final String WOWZA_URL_RTMP		= framework.getString("framework.wowza.url.rtmp");
	public static final String WOWZA_URL_RTSP 		= framework.getString("framework.wowza.url.rtsp");
	public static final String WOWZA_URL_HTTP 		= framework.getString("framework.wowza.url.http");

	public static final String MEDIA_USE 			= framework.getString("framework.media.use");
	public static final String MEDIA_URL			= framework.getString("framework.media.url");

	public static final String MEDIA_FILE_MP3		= ".mp3,.ogg,.fla";
	public static final String MEDIA_FILE_MP4		= ".mp4,.m4v,.flv";
	public static final String MEDIA_FILE_MMS		= ".wmv,.wma";

	//-- Mediaplayer config
	public static final String MEDIAPLAYER			= framework.getString("framework.media.player");    //-- 시스템에서 사용하는 Media Player (flowplayer)
	public static final String FLOWPLAYER_KEY		= framework.getString("framework.flowplayer.key"); //-- flowplayer 설정 시에만 사용
	public static final String KOLLUS_API_URL		= framework.getString("framework.kollus.api.url"); //-- kollus 설정 시에만 사용
	public static final String KOLLUS_PLAYER_URL	= framework.getString("framework.kollus.player.url"); //-- kollus 설정 시에만 사용

	//-- Webeditor 관련 설정
	public static final String WEBEDITOR_YSEYN		= framework.getString("framework.webeditor.useyn");
	public static final String WEBEDITOR_NAME		= framework.getString("framework.webeditor.name");

	//-- thumb file width size
	public static final int THUMB_FIX_WIDTH      	= 200;

	//-- 콘텐츠 평가 관련 설정
	public static final String CNTS_SATIS_USEYN		= framework.getString("framework.contents.satis.useyn");
	public static final String CNTS_MGR_USEYN		= framework.getString("framework.contents.manager.useyn");

	//-- Mail 서버 관련 설정
	public static final String MAIL_AGENT 			= framework.getString("framework.mail.agent");
	public static final String JAVAMAIL_HOST		= framework.getString("framework.mail.javamail.host");
	public static final String JAVAMAIL_USER		= framework.getString("framework.mail.javamail.user");
	public static final String JAVAMAIL_PASS		= framework.getString("framework.mail.javamail.pass");
	public static final String THUNDER_AUTO_APIURL 	= framework.getString("framework.mail.thundermail.automail.apiurl");
	public static final String THUNDER_AUTO_MAILID 	= framework.getString("framework.mail.thundermail.automail.mailid");
	public static final String THUNDER_MASS_APIURL 	= framework.getString("framework.mail.thundermail.massmail.apiurl");
	public static final String THUNDER_MASS_USERCD 	= framework.getString("framework.mail.thundermail.massmail.usercode");
	
	//-- 다이퀘스트 검색엔진 설정
	public static final String SEARCH_IP 		= framework.getString("framework.search.ip");
	public static final String SEARCH_PORT		= framework.getString("framework.search.port");
	
	//-- 통합검색 sso  설정
	public static final String SSO_ORG 		= framework.getString("framework.sso.org");
	public static final String SSO_FRONT_DOMAIN 		= framework.getString("framework.sso.front.domain");
	public static final String SSO_MANAGE_DOMAIN 		= framework.getString("framework.sso.manage.domain");
	public static final String SSO_FRONT_LOGIN_CHECK_URL 		= framework.getString("framework.sso.front.login.check.url");
	public static final String SSO_FRONT_LOGIN_URL 		= framework.getString("framework.sso.front.login.url");
	public static final String SSO_FRONT_LOGOUT_URL 		= framework.getString("framework.sso.front.logout.url");
	public static final String SSO_FRONT_USER_SEARCH_URL 		= framework.getString("framework.sso.front.user.search.url");
	public static final String SSO_FRONT_JOIN_URL 		= framework.getString("framework.sso.front.join.url");
	public static final String SSO_FRONT_IDPW_SEARCH_URL 		= framework.getString("framework.sso.front.idpw.search.url");
	public static final String SSO_MANAGE_LOGIN_CHECK_URL 		= framework.getString("framework.sso.manage.login.check.url");
	public static final String SSO_MANAGE_LOGIN_URL 		= framework.getString("framework.sso.manage.login.url");
	public static final String SSO_MANAGE_LOGOUT_URL 		= framework.getString("framework.sso.manage.logout.url");
	public static final String SSO_MANAGE_USER_SEARCH_URL 		= framework.getString("framework.sso.manage.user.search.url");
	public static final String SSO_MANAGE_USER_LIST_URL 		= framework.getString("framework.sso.manage.user.list.url");
	public static final String SSO_MANAGE_HEALTH_CHECK_URL		= framework.getString("framework.sso.manage.health.check.url");
	
	public static final String KEY_SNS_JUSO = framework.getString("framework.juso.site.key");
	public static final String JUSO_SITE_DOMAIN = framework.getString("framework.juso.site.domain");
	
	//이니시스 정보
	public static final String INICIS_SERVICE_PC_MID = framework.getString("framework.inicis.service.pc.mid"); 
	public static final String INICIS_SERVICE_MOBILE_MID = framework.getString("framework.inicis.service.mobile.mid"); 
	public static final String INICIS_TEST_MID = framework.getString("framework.inicis.test.mid"); 
	public static final String INICIS_SERVICE_SIGNKEY = framework.getString("framework.inicis.service.signKey"); 
	public static final String INICIS_TEST_SIGNKEY = framework.getString("framework.inicis.test.signKey");
	public static final String INICIS_SERVICE_APIKEY = framework.getString("framework.inicis.service.apiKey"); 
	public static final String INICIS_TEST_APIKEY = framework.getString("framework.inicis.test.apiKey");
	public static final String INICIS_TEST_YN = framework.getString("framework.inicis.testYn"); 
	
	//레디스 정보
	public static final String REDIS_SERVER = framework.getString("framework.redis.server"); 
	public static final String REDIS_PORT = framework.getString("framework.redis.port"); 
	public static final String REDIS_CHECK_YN	= framework.getString("framework.redis.check_yn");
	public static final String REDIS_NAMESPACE	 = framework.getString("framework.redis.namespace");
	public static final String REDIS_PASSWORD	 = framework.getString("framework.redis.password");
	
	
	
	//알림톡 템플릿 코드 설정 후 발급 교체 되어야 함
	public final static String ATALK_SENDER_KEY				= "0c804d712f5e9ff2551def1220d8f9cfb0938534";
	
	public static final String CHECKPLUS_SITE_CODE = framework.getString("framework.checkplus.site.code");
	public static final String CHECKPLUS_SITE_PASSWORD = framework.getString("framework.checkplus.site.password");
	
	public static final String IPIN_SITE_CODE = framework.getString("framework.ipin.site.code");
	public static final String IPIN_SITE_PASSWORD = framework.getString("framework.ipin.site.password");

	public static final String	MENU_VIEW_AUTH			= "VIEWAUTH";													// 메뉴 및 버튼 사용권한
	public static final String	MENU_CRE_AUTH			= "CREAUTH";													// 메뉴 및 버튼 쓰기권한
	
	// 메타버스(빌리버) 모듈 연동 정보
	public static final String XRCLOUD_API_KEY = framework.getString("framework.xrcloud.apiKey");
	public static final String XRCLOUD_PROJECT_ID = framework.getString("framework.xrcloud.project.id");
	
	// 아바타 편집 URL
	public static final String AVATAR_EDIT_URL = framework.getString("framework.avatar.edit.url");
	
	// HRD API 연동 정보
	public static final String HRD_API_DOMAIN = framework.getString("framework.hrd.api.domain");
	public static final String HRD_API_KEY = framework.getString("framework.hrd.api.key");
	public static final String HRD_API_USER_ID = framework.getString("framework.hrd.api.user_id");
	public static final String HRD_API_ATTEND_URL = framework.getString("framework.hrd.api.attend_url");
	public static final String HRD_API_CLASS_URL = framework.getString("framework.hrd.api.class_url");
	public static final String HRD_API_COURSE_URL = framework.getString("framework.hrd.api.course_url");
	public static final String HRD_API_SCORE_URL = framework.getString("framework.hrd.api.score_url");
	public static final String HRD_API_LOGIN_URL = framework.getString("framework.hrd.api.login_url");
	public static final String HRD_API_USER_URL = framework.getString("framework.hrd.api.user_url");
	public static final String HRD_API_USE_YN = framework.getString("framework.hrd.api.use_yn");
	public static final String HRD_API_CONTENT_TYPE = "application/json";
	public static final String HRD_API_METHOD = "POST";
	// 수강정보
	public static final String HRD_API_CD_ATTEND_HIST = "01";
	// 수업정보
	public static final String HRD_API_CD_CLASS_HIST = "02";
	// 과정정보
	public static final String HRD_API_CD_COURSE_HIST = "03";
	// 성적이력
	public static final String HRD_API_CD_SCORE_HIST = "04";
	// 회원정보
	public static final String HRD_API_CD_USER_HIST = "05";
	// 회원로그인
	public static final String HRD_API_CD_USER_LOGIN_HIST = "06";
	// 콘텐츠
	public static final String HRD_API_CD_CONTENTS_HIST = "07";
	// 수강종료(콘텐츠)
	public static final String HRD_API_CD_ATTEND_RSLT_CT_HIST = "08";
	// 성적이력(콘텐츠)
	public static final String HRD_API_CD_SCORE_CT_HIST = "09";
	

}

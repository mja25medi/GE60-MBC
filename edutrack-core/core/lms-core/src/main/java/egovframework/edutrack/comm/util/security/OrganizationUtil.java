package egovframework.edutrack.comm.util.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.AccessDeniedException;
import egovframework.edutrack.comm.exception.BadRequestUrlException;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.LocaleUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteService;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoService;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;

public class OrganizationUtil {

	private static Log log = LogFactory.getLog(SecurityUtil.class);

	public OrganizationUtil() {

	}

	public static void organiztionCheckRunner(HttpServletRequest request, HttpServletResponse response)  {
		OrganizationUtil.organizationCheck(request, response);
		
		// 사용자 메뉴 조회
		try {
			String Action = request.getRequestURI();
			
			if(Action.startsWith("/home")) {
				String userType = UserBroker.getUserType(request);
				String orgCd = UserBroker.getUserOrgCd(request);
				
				SysMenuMemService sysMenuMemService = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext())
						.getBean(SysMenuMemService.class);
				
				SysCfgService sysCfgService = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext())
						.getBean(SysCfgService.class);
				
				EtcRelatedSiteService etcRelatedSiteService = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext())
						.getBean(EtcRelatedSiteService.class);
		
				SysCodeMemService sysCodeMemService = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext())
						.getBean(SysCodeMemService.class);
				
				OrgImgInfoService orgImgInfoService = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext())
						.getBean(OrgImgInfoService.class);
				
				//-- 로그인 하지 않은 경우 게스트로 취급.
				if("".equals(userType))  userType = "GUEST";
				
				// 권한별 메뉴 조회
				OrgMenuVO orgMenuVO = sysMenuMemService.getOrgHomeMenuList(orgCd, userType);
				request.setAttribute("orgMenuVO", orgMenuVO);
				
				// 언어 목록 조회
				List<SysCodeVO> langList = sysCodeMemService.getSysCodeList("LANG_CD");
				String LOCALEKEY = UserBroker.getLocaleKey(request);
				
				for(SysCodeVO codeVO : langList) {
					for(SysCodeLangVO codeLangVO : codeVO.getCodeLangList()) {
						if(LOCALEKEY.equals(codeLangVO.getLangCd())) codeVO.setCodeNm(codeLangVO.getCodeNm());
					}
				}
				
				request.setAttribute("langList", langList);
				
				SysCfgCtgrVO ccVO = new SysCfgCtgrVO();
				ccVO.setCfgCtgrCd("MENUNO");
				
				List<SysCfgVO> configList = sysCfgService.listConfig(ccVO).getReturnList();
				for(SysCfgVO cfgVO : configList) {
					if("JOININ".equals(cfgVO.getCfgCd())) request.setAttribute("joininMcd", cfgVO.getCfgVal());
					else if("FINDIDPW".equals(cfgVO.getCfgCd())) request.setAttribute("findidMcd", cfgVO.getCfgVal());
					else if("EDITMYINFO".equals(cfgVO.getCfgCd())) request.setAttribute("editMyinfoMcd", cfgVO.getCfgVal());
					else if("MYPAGE".equals(cfgVO.getCfgCd())) request.setAttribute("myPageMcd", cfgVO.getCfgVal());
					else if("SEARCHFULL".equals(cfgVO.getCfgCd())) request.setAttribute("searchFullMcd", cfgVO.getCfgVal());
					else if("VIEWKNOW".equals(cfgVO.getCfgCd())) request.setAttribute("viewKnowMcd", cfgVO.getCfgVal());
					else if("OPENAPPLY".equals(cfgVO.getCfgCd())) request.setAttribute("openApplyMcd", cfgVO.getCfgVal());
					else if("NOTICE".equals(cfgVO.getCfgCd())) request.setAttribute("noticeMcd", cfgVO.getCfgVal());
					else if("QNA".equals(cfgVO.getCfgCd())) request.setAttribute("qnaMcd", cfgVO.getCfgVal());
					else if("NEWS".equals(cfgVO.getCfgCd())) request.setAttribute("newsMcd", cfgVO.getCfgVal());
					else if("PHOTO".equals(cfgVO.getCfgCd())) request.setAttribute("photoMcd", cfgVO.getCfgVal());
					else if("VIEWSERIES".equals(cfgVO.getCfgCd())) request.setAttribute("viewSeriesMcd", cfgVO.getCfgVal());
					else if("VIEWARCH".equals(cfgVO.getCfgCd())) request.setAttribute("viewArchiveMcd", cfgVO.getCfgVal());
					else if("JUNIORSCH".equals(cfgVO.getCfgCd())) request.setAttribute("viewJuniorKnowMcd", cfgVO.getCfgVal());
				}
				
				// FAMILY SITE 목록 조회
				EtcRelatedSiteCtgrVO rscVO = new EtcRelatedSiteCtgrVO();
				rscVO.setOrgCd(orgCd);
				List<EtcRelatedSiteCtgrVO> siteCtgrList = etcRelatedSiteService.listSiteAll(rscVO).getReturnList();
				request.setAttribute("siteCtgrList", siteCtgrList);
				
				// 메인 이미지 가져오기
				if(request.getSession().getAttribute("orgImgList") == null) {
					OrgImgInfoVO orgImgInfoVO = new OrgImgInfoVO();
					orgImgInfoVO.setOrgCd(orgCd);
					orgImgInfoVO.setImgTypeCd("MAINIMG");
					List<OrgImgInfoVO> orgImgList = orgImgInfoService.list(orgImgInfoVO).getReturnList();
					request.getSession().setAttribute("orgImgList", orgImgList);
				}
			}
		} catch (Exception e) {
			// TODO: 
		}
	}

	public static void organizationCheck(HttpServletRequest request, HttpServletResponse response) {
		String localPort = Integer.toString(request.getLocalPort());
		String Action = request.getRequestURI();
		if(!Constants.ADMIN_PORT.equals(localPort)) {
			OrgOrgInfoService orgOrgInfoService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(OrgOrgInfoService.class);
			String serverName = "";
			OrgOrgInfoVO orgOrgInfoVO  = new OrgOrgInfoVO();
			try {
				
				orgOrgInfoVO = new OrgOrgInfoVO();
				//-- 관리자 페이지가 아닌 경우만 ASP 관련 설정 받기 (서브도메인 용)
				serverName = request.getServerName();
				orgOrgInfoVO.setDomainNm(serverName);
				orgOrgInfoVO.setProductServiceType(Constants.PRODUCT_SERVICE_TYPE);
				// 서브도메인 (subdomain), 가상디렉토리(context) 타입에 따라 처리
				if(Constants.PRODUCT_SERVICE_TYPE.equals("context")) {
					// -- 가상디렉토리로 변경 
					serverName = request.getContextPath();
					// '/kedx/ko' 또는 '/kedx'를 처리하기 위한 로직
					// 총 2차 가상디렉토리 까지만 처리 되도록 구현하였음
					String[] serverArr = StringUtil.split(serverName,"/");
					String[] contextArr = { new String(orgOrgInfoVO.getDomainNm())}; 
					if(serverArr != null || serverArr.length > 1 ) {
						contextArr  = new String[1];
						contextArr[0] =	serverArr[1];
						if(serverArr.length >=3) {
							contextArr  = new String[2];
							contextArr[0] =	serverArr[1];
							contextArr[1] =	serverArr[1] + "/" + serverArr[2];
						}
					} 
					orgOrgInfoVO.setContextArr(contextArr);
				}
				
				try {
					orgOrgInfoVO = orgOrgInfoService.viewByDomain(orgOrgInfoVO);

				} catch (DataRetrievalFailureException e) {	
					//-- 검색된 기관 정보가 없는 경우.
					throw new BadRequestUrlException("common.message.organization.badurl");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (EmptyResultDataAccessException ex) {
				throw new BadRequestUrlException("common.message.organization.badurl");
			}
			HttpSession session = request.getSession();
	
			if(Constants.PRODUCT_SERVICE_TYPE.equals("context")) {
				session.setAttribute("CONTEXT_ROOT", orgOrgInfoVO.getOrgDomain());
			}
		
			//-- 기존에 셋팅된 사이트 정보가 없을 경우에만 다시 셋팅하도록 함.
			if(ValidationUtils.isEmpty(UserBroker.getUserOrgCd(request)) ||  orgOrgInfoVO.getOrgCd().equals(UserBroker.getUserOrgCd(request))==false) {

				OrgImgInfoService orgImgInfoService = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext())
						.getBean(OrgImgInfoService.class);


				try {

					if((!"Y".equals(orgOrgInfoVO.getUseYn()) || "Y".equals(orgOrgInfoVO.getSiteUsageCd())) && Action.startsWith("/home")) {
						throw new AccessDeniedException("common.message.organization.nouse");
					} else {
						session.setAttribute(Constants.SAAS_ORGNM, orgOrgInfoVO.getOrgNm());
						session.setAttribute(Constants.SAAS_DOMAINNM, orgOrgInfoVO.getDomainNm());
						session.setAttribute(Constants.SAAS_ORGCD, orgOrgInfoVO.getOrgCd());
						session.setAttribute(Constants.SAAS_LAYOUT_TPL, StringUtil.nvl(orgOrgInfoVO.getLayoutTplCd(),"center"));
						session.setAttribute(Constants.SAAS_COLOR_TPL, StringUtil.nvl(orgOrgInfoVO.getColorTplCd(),"001"));
						session.setAttribute(Constants.SYSTEM_LOCALEKEY , orgOrgInfoVO.getDfltLangCd());
						session.setAttribute(Constants.SAAS_ITGRT_MBR_USE_YN , StringUtil.nvl(orgOrgInfoVO.getItgrtMbrUseYn(),"N"));
						session.setAttribute(Constants.CONTENTS_AUTH_CD , StringUtil.nvl(orgOrgInfoVO.getConAuthCd(),"N"));

						
						//설정된 locale로 언어세팅
						LocaleUtil.setLocale(request, UserBroker.getLocaleKey(request));
						
						if(ValidationUtils.isNotEmpty(orgOrgInfoVO.getTopLogoFileSn()))
							session.setAttribute(Constants.SAAS_TOP_LOGO, orgOrgInfoVO.getTopLogoFileSn().toString());
						else
							session.setAttribute(Constants.SAAS_TOP_LOGO, "");

						if(ValidationUtils.isNotEmpty(orgOrgInfoVO.getSubLogo1FileSn())) {
							session.setAttribute(Constants.SAAS_SUB_LOGO1, orgOrgInfoVO.getSubLogo1FileSn().toString());
							session.setAttribute(Constants.SAAS_SUB_LOGO1_URL, orgOrgInfoVO.getSubLogo1Url());
						} else {
							session.setAttribute(Constants.SAAS_SUB_LOGO1, "");
							session.setAttribute(Constants.SAAS_SUB_LOGO1_URL, "");
						}

						if(ValidationUtils.isNotEmpty(orgOrgInfoVO.getSubLogo2FileSn())) {
							session.setAttribute(Constants.SAAS_SUB_LOGO2, orgOrgInfoVO.getSubLogo2FileSn().toString());
							session.setAttribute(Constants.SAAS_SUB_LOGO2_URL, orgOrgInfoVO.getSubLogo2Url());
						} else {
							session.setAttribute(Constants.SAAS_SUB_LOGO2, "");
							session.setAttribute(Constants.SAAS_SUB_LOGO2_URL, "");
						}

						if(ValidationUtils.isNotEmpty(orgOrgInfoVO.getAdmLogoFileSn()))
							session.setAttribute(Constants.SAAS_ADM_LOGO, orgOrgInfoVO.getAdmLogoFileSn().toString());
						else
							session.setAttribute(Constants.SAAS_ADM_LOGO, "");
						session.setAttribute(Constants.SAAS_WWW_FOOTER, orgOrgInfoVO.getWwwFooter());
						session.setAttribute(Constants.SAAS_ADM_FOOTER, orgOrgInfoVO.getAdmFooter());
						session.setAttribute(Constants.SAAS_MBR_APLC_USE, orgOrgInfoVO.getMbrAplcUseYn());
						session.setAttribute(Constants.SAAS_ITGRT_MBR_USE_YN, orgOrgInfoVO.getItgrtMbrUseYn());

						//-- 사이트 이미지 관련 설정
						OrgImgInfoVO oiivo = new OrgImgInfoVO();
						oiivo.setOrgCd(orgOrgInfoVO.getOrgCd());
						List<OrgImgInfoVO> imgList = null;
						try {
							imgList = orgImgInfoService.list(oiivo).getReturnList();
						} catch (Exception e) {
							imgList = new ArrayList<OrgImgInfoVO>();
						}

						for(OrgImgInfoVO ioiivo : imgList) {
							if("SUBIMG".equals(ioiivo.getImgTypeCd())) {
								session.setAttribute(Constants.SAAS_SUB_IMAGE, ioiivo.getBkgrFileSn().toString());
//								session.setAttribute(Constants.SAAS_SUB_TXTIMG, ioiivo.getDescFileSn().toString());
								session.setAttribute(Constants.SAAS_SUB_TEXT, ioiivo.getDescImgNm());
							} else if("LECIMG".equals(ioiivo.getImgTypeCd())) {
								session.setAttribute(Constants.SAAS_LEC_IMAGE, ioiivo.getBkgrFileSn().toString());
//								session.setAttribute(Constants.SAAS_LEC_TXTIMG, ioiivo.getDescFileSn().toString());
								session.setAttribute(Constants.SAAS_LEC_IMGNM, ioiivo.getBkgrImgNm().toString());
								session.setAttribute(Constants.SAAS_LEC_TEXT, ioiivo.getDescImgNm());
								session.setAttribute(Constants.SAAS_LEC_FILEURL, ioiivo.getBkgrFileUrl());
								session.setAttribute(Constants.SAAS_LEC_CONNURL, ioiivo.getConnUrl());
								session.setAttribute(Constants.SAAS_LEC_CONNTRGT, ioiivo.getConnTrgt());
							}
						}
					}
					
				} catch (EmptyResultDataAccessException ex) {
					throw new BadRequestUrlException("common.message.organization.badurl");
				}
			}
		}
	}		
}

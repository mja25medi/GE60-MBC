package egovframework.edutrack.web.home.org;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.main.service.MainVO;
import egovframework.edutrack.modules.org.creaplc.service.OrgCreAplcInfoService;
import egovframework.edutrack.modules.org.creaplc.service.OrgCreAplcInfoVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@RequestMapping(value="/home/org/openApply")
public class OrgOpenApplyHomeController extends GenericController {

	/** service */
	@Autowired @Qualifier("orgCreAplcInfoService")
	private OrgCreAplcInfoService 	orgCreAplcInfoService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;
	
	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgInfoService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 공공활용개설신청 메인
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgCreAplcInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String tplCd = UserBroker.getUserColorTpl(request);	// 기관 템플릿 코드 조회
		String orgCd = UserBroker.getUserOrgCd(request);	// 기관 템플릿 코드 조회
		
		OrgPageVO pageVO = new OrgPageVO();
		pageVO.setOrgCd(orgCd);

		// 개인정보 수집 이용동의
		pageVO.setPageCd("PAGE011");
		OrgPageVO pageVO1 = orgPageService.view(pageVO);
		request.setAttribute("pageVO1", pageVO1);

		// 개인정보수집 및 이용에 대한 안내
		pageVO.setPageCd("PAGE012");
		OrgPageVO pageVO2 = orgPageService.view(pageVO);
		request.setAttribute("pageVO2", pageVO2);
		
		String productDomain = Constants.PRODUCT_DOMAIN;
		request.setAttribute("productDomain", productDomain);
		
		return "home/org/open/main_apply";
	}	
	
	/**
     * @Method Name : joinAddApply	권한체크 넘김 : join
     * @Method 설명 : 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/joinAddApply")
	public String joinAddApply(OrgCreAplcInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		/*개설신청 VO*/
		String certKey  = UUID.randomUUID().toString();
		String userNo = UserBroker.getUserNo(request);
		
		vo.setEmailCertYn("N");
		vo.setEmailCertKey(certKey);
		/*END 개설신청 VO*/
		
		/*메일발송 VO*/
		LogMsgLogVO msgVo = new LogMsgLogVO();
		msgVo.setSendMenuCd(UserBroker.getMenuCode(request));
		msgVo.setOrgCd(orgCd);
		/*END 메일발송 VO*/
		
		try {
			orgCreAplcInfoService.add(vo, msgVo);
			result.setResult(1);
		} catch (Exception e) {
			result.setResult(-1);
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : joinEditEmailCertYn	권한체크 넘김 : join
     * @Method 설명 : 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/joinEditEmailCertYn")
	public String joinEditEmailCertYn(OrgCreAplcInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String tplCd = UserBroker.getUserColorTpl(request);	// 기관 템플릿 코드 조회
		String orgCd = UserBroker.getUserOrgCd(request);	// 기관 템플릿 코드 조회
		
		OrgCreAplcInfoVO vo2 = new OrgCreAplcInfoVO();
		vo2 = orgCreAplcInfoService.view(vo);
		int result = 0;
		if(vo2 != null){
			result = orgCreAplcInfoService.editEmailCertYn(vo);
			request.setAttribute("result", 1);
		}else{
			request.setAttribute("result", 0);
		}
		return "home/org/open/cert_yn";
	}
}

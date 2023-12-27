package egovframework.edutrack.web.manage.etc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteService;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/etc/relatedSite")
public class RelatedSiteManageController extends GenericController {
	
	@Autowired @Qualifier("etcRelatedSiteService")
	private EtcRelatedSiteService	relatedSiteService;

	
	/**
     * @Method Name : main
     * @Method 설명 : 관련 사이트 메인
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/etc/relatedsite/main";
	}

	/**
	 * 관련 사이트 분류 목록 조회
	 */
	@RequestMapping(value="/listCtgr")
	public String listCtgr(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<EtcRelatedSiteCtgrVO> resultList = relatedSiteService.listCtgr(vo);
		request.setAttribute("itemList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/etc/relatedsite/list_ctgr";
	}

	/**
	 * 관련 사이트 목록 조회(JSON)
	 */
	@RequestMapping(value="/listSite")
	public String listSite(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultListVO<EtcRelatedSiteVO> resultList = relatedSiteService.listSite(vo);
		request.setAttribute("itemList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/etc/relatedsite/list_site";
	}

	/**
	 * 관련 사이트 분류 등록 폼
	 */
	@RequestMapping(value="/addCtgrPop")
	public String addCtgrPop(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/etc/relatedsite/write_ctgr_pop";
	}

	/**
	 * 관련 사이트 분류 등록(AJAX)
	 */
	
	@RequestMapping(value="/addCtgr")
	public String addCtgr(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.addCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
		
		
	}


	/**
	 * 관련 사이트 분류 수정 폼
	 */
	@RequestMapping(value="/editCtgrPop")
	public String editCtgrPop(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = relatedSiteService.viewCtgr(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/etc/relatedsite/write_ctgr_pop";
	}

	/**
	 * 관련 사이트 분류 수정
	 */
	
	@RequestMapping(value="/editCtgr")
	public String editCtgr(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		
		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.editCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);

	}

	/**
	 * 관련 사이트 분류 삭제
	 */
	
	@RequestMapping(value="/deleteCtgr")
	public String deleteCtgr(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.removeCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * 관련 사이트 분류 순서를 변경한다. 
	 */
	@RequestMapping(value="/sortCtgr")
	public String sortCtgr(EtcRelatedSiteCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.sortCrgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	

	/**
	 * 관련 사이트 등록 폼
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addSitePop")
	public String addSitePop(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		// 카테고리 정보 조회
		EtcRelatedSiteCtgrVO ctgrVo = new EtcRelatedSiteCtgrVO();
		ctgrVo.setOrgCd(orgCd);
		ctgrVo.setCtgrCd(vo.getCtgrCd());
		ctgrVo = relatedSiteService.viewCtgr(ctgrVo);
		
		request.setAttribute("ctgrVo", ctgrVo);
		request.setAttribute("gubun", "A");
		return "mng/etc/relatedsite/write_site_pop";
	}


	/**
	 * 관련 사이트 등록
	 */
	@RequestMapping(value="/addSite")
	public String addSite(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.addSite(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 관련 사이트 수정 폼
	 */
	@RequestMapping(value="/editSitePop")
	public String editSitePop(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo = relatedSiteService.viewSite(vo);

		// 카테고리 정보 조회
		EtcRelatedSiteCtgrVO ctgrVo = new EtcRelatedSiteCtgrVO();
		ctgrVo.setOrgCd(orgCd);
		ctgrVo.setCtgrCd(vo.getCtgrCd());
		ctgrVo = relatedSiteService.viewCtgr(ctgrVo);
		
		request.setAttribute("ctgrVo", ctgrVo);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/etc/relatedsite/write_site_pop";
	}

	/**
	 * 관련 사이트 수정
	 */
	@RequestMapping(value="/editSite")
	public String editSite(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.editSite(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 관련 사이트 삭제
	 */
	@RequestMapping(value="/deleteSite")
	public String deleteSite(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.removeSite(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
//
//	/**
//	 * 배경이미지 순서변경
//	 */
//	@RequestMapping(value="/main")
//	public String main(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		commonVOProcessing(vo, request);
//		EtcRelatedSiteVO vo = vo.getEtcRelatedSiteVO();
//
//		String orgCd = UserBroker.getUserOrgCd(request);
//		vo.setOrgCd(orgCd);
//
//		ProcessResultVO<?> result = relatedSiteService.sortSite(vo);
//		if(result.getReturnResult() > 0) {
//			result.setReturnMessage(getMessage(request, "etc", "etc.message.relatedsite.site.sort.success"));
//		} else {
//			result.setReturnMessage(getMessage(request, "etc", "etc.message.relatedsite.site.sort.failed"));
//		}
//		return JsonUtil.responseJson(response, result);
//	}
//

	/**
	 * 관련 사이트 분류 순서변경
	 */
	@RequestMapping(value="/sortSite")
	public String sortSite(EtcRelatedSiteVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<EtcRelatedSiteCtgrVO> result = new ProcessResultVO<EtcRelatedSiteCtgrVO>();
		try {
			relatedSiteService.sortSite(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.site.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	
	
}

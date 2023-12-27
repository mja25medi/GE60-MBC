package egovframework.edutrack.web.manage.etc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrService;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrVO;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/etc/bnnr")
public class BnnrManageController extends GenericController {

	@Autowired @Qualifier("etcBnnrService")
	private EtcBnnrService	etcBnnrService; // 인터페이스 선언부

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	/**
	 * 베너관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/etc/bnnr/bnnr_main";
	}

	/**
	 * 베너 목록 조회
	 * @param request
	 * @return Ajax Json
	 */

	@RequestMapping(value="/list")
	public String list(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<EtcBnnrVO> resultList = etcBnnrService.listPageing(vo, vo.getCurPage());

		request.setAttribute("bannerList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/etc/bnnr/bnnr_list_div";
	}

	/**
	 * 베너 정보 입력 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPop")
	public String addPop(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
        List<SysCodeVO> bnnrTrgtList = sysCodeMemService.getSysCodeList("BNNR_TRGT", UserBroker.getLocaleKey(request));
		request.setAttribute("bnnrTrgtList", bnnrTrgtList);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/etc/bnnr/bnnr_write_pop";
	}


	/**
	 * 베너 정보 수정 폼
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/editPop")
	public String editPop(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
        List<SysCodeVO> bnnrTrgtList = sysCodeMemService.getSysCodeList("BNNR_TRGT", UserBroker.getLocaleKey(request));
		request.setAttribute("bnnrTrgtList", bnnrTrgtList);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = etcBnnrService.view(vo);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "mng/etc/bnnr/bnnr_write_pop";

	}

	/**
	 * 베너 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response`	`
	 * @return json
	 */
	@RequestMapping(value="/add")
	public String add(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<EtcBnnrVO> result = etcBnnrService.add(vo);
		if(result.getResult() > 0) {
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.banner.add.success"));
		} else {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.banner.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 베너 수정
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/edit")
	public String edit(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 저장
		ProcessResultVO<EtcBnnrVO> result =etcBnnrService.edit(vo); //서비스 딴으로 넘길 변수
		if(result.getResult() > 0) {
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.banner.edit.success"));
		} else {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.banner.edit.failed"));
		}
		return JsonUtil.responseJson(response, result); //
	}

	/**
	 * 베너 정보 순서 변경 폼
	 *
	 * @return
	 */
	@RequestMapping(value="/sortPop")
	public String sortPop(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<EtcBnnrVO> list = etcBnnrService.list(vo).getReturnList();
		request.setAttribute("bannerList", list);
		return "mng/etc/bnnr/bnnr_sort_pop";
	}

	/**
	 * 베너 순서변경
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sort")
	public String sort(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 저장
		ProcessResultVO<EtcBnnrVO> result = new ProcessResultVO<EtcBnnrVO>();
		try {
			etcBnnrService.sort(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.banner.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.banner.sort.failed"));
		}

		return JsonUtil.responseJson(response, result);

	}
	/**
	 * 베너 삭제
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/delete")
	public String delete(EtcBnnrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<EtcBnnrVO> result = new ProcessResultVO<EtcBnnrVO>();
		try {
			etcBnnrService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.banner.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.banner.delete.failed"));
		}


		return JsonUtil.responseJson(response, result); //
	}
}

package egovframework.edutrack.web.manage.olc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsTreeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrVO;
import egovframework.edutrack.modules.olc.sharectgr.service.OlcShareCtgrService;
import egovframework.edutrack.modules.olc.sharectgr.service.OlcShareCtgrVO;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelService;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/olc/share")
public class OlcShareManageController extends GenericController{

	@Autowired @Qualifier("olcShareCtgrService")
	private OlcShareCtgrService 	olcShareCtgrService;

	@Autowired @Qualifier("olcShareRelService")
	private OlcShareRelService 	olcShareRelService;

    /**
	 * OLC 공유 분류 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		return "mng/olc/share/share_main.jsp";
	}

	/**
	 * 분류 목록 (JsTree)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCtgrJsTree")
	public String listCtgrJsTree( OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String id = StringUtil.nvl(request.getParameter("id"));

		//-- 최상외 접속시 리턴
		if("#".equals(id)) {
			//회원의 정보를 가져와 최상위 node에 입력
			JsTreeVO treeVO = JsTreeUtil.getJsTreeVO("root", "ROOT", "root", 1, new OlcCtgrVO());
			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals("root")) id = "";
			OlcShareCtgrVO oscVO = new OlcShareCtgrVO();
			oscVO.setOrgCd(orgCd);
			oscVO.setParCtgrCd(id);
			oscVO.setCtgrDivCd(vo.getCtgrDivCd());
			List<OlcShareCtgrVO> listCtgr = olcShareCtgrService.listSub(oscVO).getReturnList();

			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			String firstCd = "";
			String lastCd = "";
			int i = 0;
			for(OlcShareCtgrVO VO : listCtgr) {
				if(i == 0) {
					firstCd = VO.getCtgrCd();
				}
				i++;
				lastCd = VO.getCtgrCd();
			}
			for(OlcShareCtgrVO VO : listCtgr) {
				String rel = "contents";
				if(VO.getSubCnt() > 0) rel = "category";
				VO.setFirstCd(firstCd);
				VO.setLastCd(lastCd);
				JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(VO.getCtgrCd(), VO.getCtgrNm(), rel, VO.getSubCnt(), VO);
				treeList.add(treeVO);
			}
			return JsonUtil.responseJson(response, treeList);
		}
	}

	/**
	 * OLC 공유 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr( OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String parCtgrCd = vo.getCtgrCd();

		vo.setCtgrCd(""); //-- 등로이므로 ctgrCd를 비운다.
		vo.setOrgCd(orgCd);

		//-- 등록은 무조건 하위 분류 등록으로 본다.
		if("root".equals(parCtgrCd) || "#".equals(parCtgrCd)) {
			vo.setParCtgrCd("");
		} else {
			vo.setParCtgrCd(parCtgrCd);
		}
		ProcessResultVO<JsTreeVO> resultVO = new ProcessResultVO<JsTreeVO>();
		OlcShareCtgrVO oscVO =  olcShareCtgrService.add(vo).getReturnVO();
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.add.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.add.failed"));
			resultVO.setResult(-1);
		}
		JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(oscVO.getCtgrCd(), oscVO.getCtgrNm(), "contents", 1, oscVO);
		return JsonUtil.responseJson(response, treeVO);
	}

	/**
	 * OLC 공유 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr( OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 기존에 등록되어 있는 분류정보를 가져와 분류명을 다시 셋팅하여 저장한다.
		OlcShareCtgrVO noscVO = new OlcShareCtgrVO();
		noscVO.setOrgCd(orgCd);
		noscVO.setCtgrCd(vo.getCtgrCd());
		noscVO.setCtgrDivCd(vo.getCtgrDivCd());
		noscVO = olcShareCtgrService.view(noscVO).getReturnVO();
		noscVO.setCtgrNm(vo.getCtgrNm()); //-- 새로 입력된 분류명으로 변경

		ProcessResultVO<OlcShareCtgrVO> result = olcShareCtgrService.edit(noscVO);
		noscVO = result.getReturnVO();
		ProcessResultVO<JsTreeVO> resultVO = new ProcessResultVO<JsTreeVO>();
		if(result.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.edit.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.edit.failed"));
			resultVO.setResult(-1);
		}
		String rel = "contents";
		if(noscVO.getSubCnt() > 0) rel = "category";
		JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(noscVO.getCtgrCd(), noscVO.getCtgrNm(), rel, 1, noscVO);
		resultVO.setReturnVO(treeVO);
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC 공유 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCtgr")
	public String deleteCtgr( OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OlcShareCtgrVO> resultVO = olcShareCtgrService.delete(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * Ctgr 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveUpCtgr")
	public String moveUpCtgr( OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = olcShareCtgrService.move(vo, "up");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * Ctgr 순서 아래로
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@RequestMapping(value="/moveDownCtgr")
	public String moveDownCtgr(  OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = olcShareCtgrService.move(vo, "down");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


    /**
	 * OLC 공유 분류 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCartrgRel")
	public String listCartrgRel( OlcShareRelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OlcShareRelVO> resultList = olcShareRelService.listPageingByCtgr(vo, vo.getCurPage());
		
		request.setAttribute("olcShareRelList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		
		return "mng/olc/share/cartrg_rel_list_div";
	}


    /**
	 * OLC 공유 분류 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCtgrRel")
	public String listCtgrRel( OlcShareRelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OlcShareRelVO> resultList = olcShareRelService.listByCartrg(vo);
	
		request.setAttribute("olcShareRelVO", vo);
		request.setAttribute("olcShareRelList", resultList.getReturnList());
		return "mng/olc/share/ctgr_rel_list_div";
	}

	/**
	 * 공유 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addShare")
	public String addShare( OlcShareRelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response){
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultVO<OlcShareRelVO> resultVO = null;
		try {
			resultVO = olcShareRelService.insert(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "olc.message.share.add.success"));
			} else {
				resultVO.setMessage(getMessage(request, "olc.message.share.add.failed"));
			}
		} catch (Exception e) {
			resultVO = new ProcessResultVO<OlcShareRelVO>();
			resultVO.setResult(-1);
			resultVO.setMessage(getMessage(request, "olc.message.share.add.failed.dup"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공유 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteShare")
	public String deleteShare( OlcShareRelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OlcShareRelVO> resultVO = olcShareRelService.delete(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.share.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.share.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * OLC 공유 분류 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCartrgRelCntsMng")
	public String listCartrgRelCntsMng( OlcShareRelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OlcShareRelVO> resultList = olcShareRelService.listByCtgr(vo);

		request.setAttribute("olcShareRelList", resultList.getReturnList());
		
		return "mng/olc/share/cartrg_rel_list_cntsmng_div";
	}
}

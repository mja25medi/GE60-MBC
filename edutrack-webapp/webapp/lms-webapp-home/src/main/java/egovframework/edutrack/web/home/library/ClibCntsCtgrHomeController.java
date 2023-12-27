package egovframework.edutrack.web.home.library;

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
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsTreeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/library/clibCntsCtgr")
public class ClibCntsCtgrHomeController extends GenericController {

	@Autowired @Qualifier("clibCntsCtgrService")
	private ClibCntsCtgrService 			clibCntsCtgrService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 			clibShareCntsCtgrService;
	/**
	 * 분류 목록 (JsTree)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCtgrJsTree")
	public String listCtgrJsTree( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));

		//-- 최상외 접속시 리턴
		if("#".equals(id)) {
			//회원의 정보를 가져와 최상위 node에 입력
			JsTreeVO treeVO = JsTreeUtil.getJsTreeVO("root", userNm, "root", 1, new ClibCntsCtgrVO());
			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals("root")) id = "";
			ClibCntsCtgrVO cccVO = new ClibCntsCtgrVO();
			cccVO.setOrgCd(orgCd);
			cccVO.setUserNo(userNo);
			cccVO.setParCtgrCd(id);
			List<ClibCntsCtgrVO> listCtgr = clibCntsCtgrService.listSub(cccVO).getReturnList();

			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			String firstCd = "";
			String lastCd = "";
			int i = 0;
			for(ClibCntsCtgrVO VO : listCtgr) {
				if(i == 0) {
					firstCd = VO.getCtgrCd();
				}
				i++;
				lastCd = VO.getCtgrCd();
			}
			for(ClibCntsCtgrVO VO : listCtgr) {
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
	 * OLC 분류 목록 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageCtrg")
	public String manageCtrg(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		List<ClibCntsCtgrVO> listCtgr = clibCntsCtgrService.listSub(vo).getReturnList();
		request.setAttribute("ctgrSubList", listCtgr);
		request.setAttribute("vo", vo);

		return "home/library/cnts/olc/olc_ctrg_manage_pop";
	}

	/**
	 * OLC 분류 목록 추가폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCtrgForm")
	public String addCtrgForm(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo.setGubun("A");
		request.setAttribute("vo", vo);
		return "home/library/cnts/olc/olc_ctrg_write_pop";
	}


	/**
	 * OLC 분류 목록 추가
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCtrg")
	public String addCtrg(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setCtgrCd(""); //-- 등로이므로 ctgrCd를 비운다.
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibCntsCtgrVO> resultVO = clibCntsCtgrService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.add.success"));

		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.add.failed"));

		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC 분류 목록 수정폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public String editCtrgForm(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibCntsCtgrVO> resultVO = clibCntsCtgrService.view(vo);

		vo = resultVO.getReturnVO();
		request.setAttribute("clibCntsCtgrVO", vo);
	   	request.setAttribute("gubun", "E");



		return "home/library/cnts/olc/olc_ctrg_write_pop";
	}

	/**
	 * OLC 분류 목록 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCtrg")
	public String editCtrg(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibCntsCtgrVO> resultVO =  clibCntsCtgrService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.edit.success"));

		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.edit.failed"));

		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 분류 목록 (JsTree)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShareCtgrJsTree")
	public String listShareCtgrJsTree(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));

		//-- 최상외 접속시 리턴
		if("#".equals(id)) {
			//회원의 정보를 가져와 최상위 node에 입력
			JsTreeVO treeVO = JsTreeUtil.getJsTreeVO("root", userNm, "root", 1, new ClibCntsCtgrVO());
			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals("root")) id = "";
			ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
			ccscVO.setOrgCd(orgCd);
			ccscVO.setParCtgrCd(id);
			List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.listSub(ccscVO).getReturnList();

			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			String firstCd = "";
			String lastCd = "";
			int i = 0;
			for(ClibShareCntsCtgrVO VO : listCtgr) {
				if(i == 0) {
					firstCd = VO.getCtgrCd();
				}
				i++;
				lastCd = VO.getCtgrCd();
			}
			for(ClibShareCntsCtgrVO VO : listCtgr) {
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
	 * 콘텐츠 라이브러리 : 콘텐츠 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCtgr")
	public String deleteCtgr(ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibCntsCtgrVO> resultVO = clibCntsCtgrService.delete(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

}

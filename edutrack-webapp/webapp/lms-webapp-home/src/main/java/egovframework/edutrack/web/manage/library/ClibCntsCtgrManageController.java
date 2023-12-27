package egovframework.edutrack.web.manage.library;

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
@RequestMapping(value="/mng/library/clibCntsCtgr")
public class ClibCntsCtgrManageController extends GenericController {

	@Autowired @Qualifier("clibCntsCtgrService")
	private ClibCntsCtgrService 			clibCntsCtgrService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 			clibShareCntsCtgrService;

	/**
	 * 콘텐츠 라이브러니 : 콘텐츠 분류 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listJsTree")
	public String listJsTree( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));
		String listType = StringUtil.nvl(request.getParameter("listType"));

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
			String ctgrNm = "";
			for(ClibCntsCtgrVO VO : listCtgr) {
				String rel = "contents";
				if(VO.getSubCnt() > 0) rel = "category";
				VO.setFirstCd(firstCd);
				VO.setLastCd(lastCd);

				if(listType.equals("MEDIA")){
					ctgrNm = VO.getCtgrNm() + "(" + VO.getMediaCnt() + ")";
				} else if(listType.equals("OLC")){
					ctgrNm = VO.getCtgrNm() + "(" + VO.getOlcCnt() + ")";
				} else {
					ctgrNm = VO.getCtgrNm();
				}

				JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(VO.getCtgrCd(), ctgrNm, rel, VO.getSubCnt(), VO);
				treeList.add(treeVO);
			}
			return JsonUtil.responseJson(response, treeList);
		}
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String parCtgrCd = vo.getCtgrCd();
		vo.setCtgrCd(""); //-- 등록이므로 ctgrCd를 비운다.
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		//-- 등록은 무조건 하위 분류 등록으로 본다.
		if("root".equals(parCtgrCd) || "#".equals(parCtgrCd)) {
			vo.setParCtgrCd("");
		} else {
			vo.setParCtgrCd(parCtgrCd);
		}
		ProcessResultVO<JsTreeVO> resultVO = new ProcessResultVO<JsTreeVO>();
		ClibCntsCtgrVO cccVO =  clibCntsCtgrService.add(vo).getReturnVO();
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.add.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.add.failed"));
			resultVO.setResult(-1);
		}
		JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(cccVO.getCtgrCd(), cccVO.getCtgrNm(), "contents", 1, cccVO);
		return JsonUtil.responseJson(response, treeVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		//-- 기존에 등록되어 있는 분류정보를 가져와 분류명을 다시 셋팅하여 저장한다.
		ClibCntsCtgrVO ncccVO = new ClibCntsCtgrVO();
		ncccVO.setOrgCd(orgCd);
		ncccVO.setUserNo(userNo);
		ncccVO.setCtgrCd(vo.getCtgrCd());
		ncccVO = clibCntsCtgrService.view(ncccVO).getReturnVO();
		ncccVO.setCtgrNm(vo.getCtgrNm()); //-- 새로 입력된 분류명으로 변경

		ProcessResultVO<ClibCntsCtgrVO> result = clibCntsCtgrService.edit(ncccVO);
		ncccVO = result.getReturnVO();
		ProcessResultVO<JsTreeVO> resultVO = new ProcessResultVO<JsTreeVO>();
		if(result.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.edit.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.edit.failed"));
			resultVO.setResult(-1);
		}
		String rel = "contents";
		if(ncccVO.getSubCnt() > 0) rel = "category";
		JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(ncccVO.getCtgrCd(), ncccVO.getCtgrNm(), rel, 1, ncccVO);
		resultVO.setReturnVO(treeVO);
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCtgr")
	public String deleteCtgr( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 분류 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveUpCtgr")
	public String moveUpCtgr( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = clibCntsCtgrService.move(vo, "up");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 분류 순서 아래로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveDownCtgr")
	public String moveDownCtgr( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = clibCntsCtgrService.move(vo, "down");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.category.sort.failed"));
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
	 * 분류 목록 (JsTree)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShareCtgrJsTree")
	public String listShareCtgrJsTree( ClibCntsCtgrVO vo, Map commandMap, ModelMap model,
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

}

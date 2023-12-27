package egovframework.edutrack.web.home.olc;

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

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsTreeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgService;
import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgVO;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgVO;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsVO;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrService;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/olc/cartrg")
public class OlcCartrgHomeController extends GenericController{

	@Autowired @Qualifier("olcCtgrService")
	private OlcCtgrService 	olcCtgrService;

	@Autowired @Qualifier("olcCartrgService")
	private OlcCartrgService 	olcCartrgService;

	@Autowired @Qualifier("olcCntsService")
	private OlcCntsService 	olcCntsService;

	@Autowired @Qualifier("bkgImgService")
	private BkgImgService bkgImgService;

	/**
	 * OLC 카트리지 목록 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCartrg")
	public String listCartrg( OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		if("root".equals(vo.getCtgrCd())){
			vo.setCtgrCd("");
		}
		ProcessResultListVO<OlcCartrgVO> resultList = olcCartrgService.list(vo);

    	request.setAttribute("olcCartrgList", resultList.getReturnList());
    	request.setAttribute("vo", vo);
		
		String returnUrl = "home/olc/cartrg/cartrg_list_div";
		
		return returnUrl;
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
	public String manageCtrg(OlcCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		List<OlcCtgrVO> resultList = olcCtgrService.listSub(vo).getReturnList();
		request.setAttribute("ctgrSubList", resultList);
		request.setAttribute("olcCartrgVO", vo);

		return "home/olc/cartrg/ctrg_manage_pop";
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
	public String addCtrgForm(OlcCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		request.setAttribute("olcCartrgVO", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);

		return "home/olc/cartrg/ctrg_write_pop";
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
	public String addCtrg(OlcCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setCtgrCd(""); //-- 등로이므로 ctgrCd를 비운다.
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<OlcCtgrVO> resultVO =  olcCtgrService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.add.success"));

		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.add.failed"));

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
	@RequestMapping(value="/editCtrgForm")
	public String editCtrgForm(OlcCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<OlcCtgrVO> resultVO = olcCtgrService.view(vo);

		vo = resultVO.getReturnVO();
		
		request.setAttribute("olcCartrgVO", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);

		return "home/olc/cartrg/ctrg_write_pop";
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
	public String editCtrg(OlcCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<OlcCtgrVO> resultVO =  olcCtgrService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.category.edit.success"));

		} else {
			resultVO.setMessage(getMessage(request, "olc.message.category.edit.failed"));

		}
		return JsonUtil.responseJson(response, resultVO);
	}

	 /**
	 * OLC 카트리지 등록폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCartrgForm")
	public String addCartrgForm(OlcCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setUseYn("Y"); //-- 초기 사용으로 셋팅

		if(ValidationUtils.isNotEmpty(vo.getCtgrCd()) && !"root".equals(vo.getCtgrCd())) {
			//-- 분류를 선택한 경우 분류명을 검색하여 셋팅해 준다.
			OlcCtgrVO ocVO = new OlcCtgrVO();
			ocVO.setOrgCd(orgCd);
			ocVO.setUserNo(userNo);
			ocVO.setCtgrCd(vo.getCtgrCd());
			ocVO = olcCtgrService.view(ocVO).getReturnVO();
			vo.setCtgrNm(ocVO.getCtgrNm());
		}
		request.setAttribute("olcCartrgVO", vo);
		request.setAttribute("gubun", "A");


		return "home/olc/cartrg/cartrg_write_pop";
	}

	/**
	 * OLC 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCartrg")
	public String addCartrg(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		if("Y".equals(vo.getOpenYn())) {
			//-- 공개여부 공개일 경우 공개 분류 연결 프로세스 ....
		}
		ProcessResultVO<OlcCartrgVO> resultVO = olcCartrgService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * OLC 카트리지 수정폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCartrgForm")
	public String editCartrgForm(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = olcCartrgService.view(vo).getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");

		return "home/olc/cartrg/cartrg_write_pop";
	}

	/**
	 * OLC 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCartrg")
	public String editCartrg(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<OlcCartrgVO> resultVO = olcCartrgService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * OLC 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCartrg")
	public String deleteCartrg(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		//-- 공개 분류가 있는 경우의 처리

		ProcessResultVO<?> resultVO = olcCartrgService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveUpOLC")
	public String moveUpOLC(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCartrgService.move(vo, "up");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC 순서 아래로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveDownOLC")
	public String moveDownOLC(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCartrgService.move(vo, "down");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.failed"));
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
	public String listCtgrJsTree(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));

		//-- 최상외 접속시 리턴
		if("#".equals(id)) {
			//회원의 정보를 가져와 최상위 node에 입력
			JsTreeVO treeVO = JsTreeUtil.getJsTreeVO("root", userNm, "root", 1, new OlcCtgrVO());
			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals("root")) id = "";
			OlcCtgrVO ocVO = new OlcCtgrVO();
			ocVO.setOrgCd(orgCd);
			ocVO.setUserNo(userNo);
			ocVO.setParCtgrCd(id);
			List<OlcCtgrVO> listCtgr = olcCtgrService.listSub(ocVO).getReturnList();

			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			String firstCd = "";
			String lastCd = "";
			int i = 0;
			for(OlcCtgrVO VO : listCtgr) {
				if(i == 0) {
					firstCd = VO.getCtgrCd();
				}
				i++;
				lastCd = VO.getCtgrCd();
			}
			for(OlcCtgrVO VO : listCtgr) {
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
	 * OLC Contents 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cntsMain")
	public String cntsMain(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		OlcCartrgVO ocVO = new OlcCartrgVO();
		ocVO.setOrgCd(orgCd);
		ocVO.setUserNo(userNo);
		ocVO.setCartrgCd(vo.getCartrgCd());

		ocVO = olcCartrgService.view(ocVO).getReturnVO();

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		request.setAttribute("olcCartrgVO", ocVO);
		request.setAttribute("vo", vo);


		return "home/olc/cartrg/cnts_main_pop";
	}

	/**
	 * OLC 카트리지 미리보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/previewMain")
	public String previewMain(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		//-- 카테고리 정보를 가져온다.
		OlcCartrgVO ocVO = new OlcCartrgVO();
		ocVO.setOrgCd(orgCd);
		ocVO.setUserNo(vo.getUserNo());
		ocVO.setCartrgCd(vo.getCartrgCd());

		if("Y".equals(request.getParameter("hitsup"))){
			ocVO = olcCartrgService.view(ocVO,true).getReturnVO();
		} else {
			ocVO = olcCartrgService.view(ocVO).getReturnVO();
		}

		//-- 콘텐츠 목록을 가져온다.
		vo.setOrgCd(orgCd);
		List<OlcCntsVO> resultList = olcCntsService.list(vo).getReturnList();

		request.setAttribute("olcCartrgVO", ocVO);
		request.setAttribute("olcCntsList", resultList);

		return "home/olc/cartrg/preview_main_pop";
	}

	/**
	 * OLC 콘텐츠 단위 페이지 미리보기
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/viewCnts")
	public String viewCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		if(ValidationUtils.isEmpty(vo.getUserNo()))
			vo.setUserNo(userNo);

		vo = olcCntsService.view(vo).getReturnVO();

		String returnUrl = "home/olc/cartrg/cnts_view_create_pop";
		if(!"create".equals(vo.getCntsDiv())) {
			String ext = FileUtil.getFileExtention(vo.getCntsFilePath());
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				returnUrl = "home/olc/cartrg/cnts_view_mp3_pop";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				returnUrl = "home/olc/cartrg/cnts_view_mp4_pop";
			} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
				returnUrl = "home/olc/cartrg/cnts_view_mms_pop";
			} else {
				returnUrl = "home/olc/cartrg/cnts_view_html_pop";
			}
		}
		request.setAttribute("vo", vo);


		return returnUrl;

	}

	 /**
	 * OLC Contents 의 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCnts")
	public String listCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		List<OlcCntsVO> resultList = olcCntsService.list(vo).getReturnList();
		request.setAttribute("olcCntsList", resultList);
		request.setAttribute("vo", vo);
		return "home/olc/cartrg/cnts_list_div";
	}

	  /**
	 * OLC Contents 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCntsForm")
	public String addCntsForm(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		String returnUrl = "cnts_write_link";
		if("create".equals(vo.getCntsDiv())) {
			returnUrl = this.getEditorType();
		}

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);

		return "home/olc/cartrg/cnts_write_link_pop";
	}

	/**
	 * OLC Cnts 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCnts")
	public String addCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		// 스크립트, 스타일 태그 제거 제거 해야 할지 고민하자.
		vo.setCntsCts(HtmlCleaner.cleanScript(vo.getCntsCts()) );

		ProcessResultVO<OlcCntsVO> resultVO = olcCntsService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.contents.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.contents.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * OLC Contents 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCntsForm")
	public String editCntsForm(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = olcCntsService.view(vo).getReturnVO();

		String returnUrl = "home/olc/cartrg/cnts_write_link_pop";
		if("create".equals(vo.getCntsDiv())) {
			returnUrl = this.getEditorType();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");


		return returnUrl;
	}

	/**
	 * OLC Cnts 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCnts")
	public String editCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		// 스크립트, 스타일 태그 제거 제거 해야 할지 고민하자.
		vo.setCntsCts(HtmlCleaner.cleanScript(vo.getCntsCts()) );

		ProcessResultVO<OlcCntsVO> resultVO = olcCntsService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC Cnts 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCnts")
	public String deleteCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCntsService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * Contents 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveUpCnts")
	public String moveUpCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCntsService.move(vo, "up");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * Contents 순서 아래로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveDownCnts")
	public String moveDownCnts(OlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCntsService.move(vo, "down");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 컨텐츠공유 요청
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/cntsShareCdChange")
	public String cntsShareCdChange(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCartrgService.ShareCdChange(vo, "cnts");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 지식공유 요청
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/knowShareCdChange")
	public String knowShareCdChange(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = olcCartrgService.ShareCdChange(vo, "know");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartredge.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}



	 /**
	 * OLC 카트리지 디자인 수정폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCartrgDesignForm")
	public String editCartrgDesignForm(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = olcCartrgService.view(vo).getReturnVO();

		vo.getBkgImgJson();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");


		return "home/olc/cartrg/cartrg_edit_design_pop";
	}

	/**
	 * OLC 디자인 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCartrgDesign")
	public String editCartrgDesign(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<OlcCartrgVO> resultVO = olcCartrgService.editDesign(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "olc.message.cartridge.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	private String getEditorType() {
		String forwardUrl = "home/olc/cartrg/cnts_write_create_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) forwardUrl = "home/olc/cartrg/cnts_write_daumeditor_pop";
			else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) forwardUrl = "home/olc/cartrg/cnts_write_summernote_pop";
		}
		return forwardUrl;
	}

	/**
	 * OLC 카트리지 디자인 수정폼 - 배경이미지 선택
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCartrgDesignBkgImgForm")
	public String editCartrgDesignBkgImgForm(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		BkgImgVO bkgImgVO = new BkgImgVO();
		bkgImgVO.setOrgCd(orgCd);
		ProcessResultListVO<BkgImgVO> bkgImgList = bkgImgService.list(bkgImgVO);
		request.setAttribute("bkgImgList", bkgImgList.getReturnList());
		request.setAttribute("vo", vo);

		return "home/olc/cartrg/cartrg_edit_design_bkimg";
	}


	/**
	 * OLC 카트리지 수정폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewAppl")
	public String viewAppl(OlcCartrgVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = olcCartrgService.view(vo).getReturnVO();
		request.setAttribute("vo", vo);
		return "home/olc/cartrg/cartrg_view_appl_pop";
	}

}

package egovframework.edutrack.web.manage.library;

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
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcCntsService;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcCntsVO;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcPageService;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcPageVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoService;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/library/clibOlcCnts")
public class ClibOlcCntsManageController extends GenericController {

	@Autowired @Qualifier("clibOlcCntsService")
	private ClibOlcCntsService 			clibOlcCntsService;

	@Autowired @Qualifier("clibOlcPageService")
	private ClibOlcPageService 			clibOlcPageService;

	@Autowired @Qualifier("clibCntsCtgrService")
	private ClibCntsCtgrService 			clibCntsCtgrService;

	@Autowired @Qualifier("orgImgInfoService")
	private OrgImgInfoService orgImgInfoService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;

	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;

	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService 	 orgCodeService;

    /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");		
		return "mng/library/cnts/olc/olc_cnts_main";
	}

    /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 목록 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibOlcCntsVO> resultList = clibOlcCntsService.listPageing(vo);

		request.setAttribute("clibOlcCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("clibOlcCntsVO", vo);
		return "mng/library/cnts/olc/olc_cnts_list_div";
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 등록폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addForm")
	public String addForm( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setUseYn("Y"); //-- 초기 사용으로 셋팅

		if(ValidationUtils.isNotEmpty(vo.getCtgrCd()) && !"root".equals(vo.getCtgrCd())) {
			//-- 분류를 선택한 경우 분류명을 검색하여 셋팅해 준다.
			ClibCntsCtgrVO cccVO = new ClibCntsCtgrVO();
			cccVO.setOrgCd(orgCd);
			cccVO.setUserNo(userNo);
			cccVO.setCtgrCd(vo.getCtgrCd());
			cccVO = clibCntsCtgrService.view(cccVO).getReturnVO();
			vo.setCtgrNm(cccVO.getCtgrNm());
		}

		//-- 분류 목록을 가져온다.
		ClibCntsCtgrVO cccVO = new ClibCntsCtgrVO();
		cccVO.setOrgCd(orgCd);
		cccVO.setUserNo(userNo);
		List<ClibCntsCtgrVO> ctgrList = clibCntsCtgrService.list(cccVO).getReturnList();
		request.setAttribute("ctgrList", ctgrList);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/library/cnts/olc/olc_cnts_write_div";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibOlcCntsVO> resultVO = clibOlcCntsService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 수정폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editForm")
	public String editForm( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = clibOlcCntsService.view(vo).getReturnVO();

		if(ValidationUtils.isNotEmpty(vo.getCtgrCd()) && !"root".equals(vo.getCtgrCd())) {
			//-- 분류를 선택한 경우 분류명을 검색하여 셋팅해 준다.
			ClibCntsCtgrVO cccVO = new ClibCntsCtgrVO();
			cccVO.setOrgCd(orgCd);
			cccVO.setUserNo(userNo);
			cccVO.setCtgrCd(vo.getCtgrCd());
			cccVO = clibCntsCtgrService.view(cccVO).getReturnVO();
			vo.setCtgrNm(cccVO.getCtgrNm());
		}

		//-- 분류 목록을 가져온다.
		ClibCntsCtgrVO cccVO = new ClibCntsCtgrVO();
		cccVO.setOrgCd(orgCd);
		cccVO.setUserNo(userNo);
		List<ClibCntsCtgrVO> ctgrList = clibCntsCtgrService.list(cccVO).getReturnList();
		request.setAttribute("ctgrList", ctgrList);

		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		
		return "mng/library/cnts/olc/olc_cnts_write_div";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibOlcCntsVO> resultVO = clibOlcCntsService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC 콘텐츠 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveUpCnt")
	public String moveUpCnt( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = clibOlcCntsService.move(vo, "up");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * OLC 콘텐츠 순서 아래로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveDownCnt")
	public String moveDownCnt( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<?> resultVO = clibOlcCntsService.move(vo, "down");
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 수정폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editDesignForm")
	public String editDesignForm( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = clibOlcCntsService.view(vo).getReturnVO();

		request.setAttribute("gubun", "E");
		request.setAttribute("clibOlcCntsVO", vo);
		return "mng/library/cnts/olc/olc_cnts_write_design_div";
	}


	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 디자인 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editDesign")
	public String editDesign( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultVO<ClibOlcCntsVO> resultVO = clibOlcCntsService.editDesign(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/remove")
	public String remove( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		request.setAttribute("curPage", "1");

		ProcessResultVO<?> resultVO = clibOlcCntsService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * 콘텐츠 라이브러리 : 배경 이미지 선택 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchBkgrImg")
	public String searchBkgrImg( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = clibOlcCntsService.view(vo).getReturnVO();

//		BkgImgVO bkgImgVO = new BkgImgVO();
		OrgImgInfoVO bkgImgVO = new OrgImgInfoVO();
		bkgImgVO.setOrgCd(orgCd);
		ProcessResultListVO<OrgImgInfoVO> resultList = orgImgInfoService.listPageing(bkgImgVO, vo.getCurPage(), 8);
		request.setAttribute("bkgImgList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		request.setAttribute("clibOlcCntsVO", vo);
		return "mng/library/cnts/olc/bkgr_img_list_pop";
	}


    /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainPagePop")
	public String mainPagePop( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		//-- OLC 정보
		vo = clibOlcCntsService.view(vo).getReturnVO();

		request.setAttribute("vo", vo);
		return "mng/library/cnts/olc/olc_page_main_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지의 목록 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listPage")
	public String listPage( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<ClibOlcPageVO> resultList = clibOlcPageService.list(vo);

		request.setAttribute("clibOlcPageList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("clibOlcPageVO", vo);
		request.setAttribute("vo", vo);
		return "mng/library/cnts/olc/olc_page_list_div";
	}


	 /**
	 * OLC 콘텐츠 미리보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/previewPop")
	public String previewMain( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		//-- OLC 정보를 가져온다
		vo.setOrgCd(orgCd);
		vo = clibOlcCntsService.view(vo).getReturnVO();

		//-- 페이지 목록을 가져온다.
		ClibOlcPageVO clibOlcPageVO = new ClibOlcPageVO();
		clibOlcPageVO.setOrgCd(orgCd);
		clibOlcPageVO.setCntsCd(vo.getCntsCd());
		List<ClibOlcPageVO> resultList = clibOlcPageService.list(clibOlcPageVO).getReturnList();

		request.setAttribute("clibOlcCntsVO", vo);
		request.setAttribute("clibOlcPageList", resultList);
		return "mng/library/cnts/olc/preview_main_pop";
	}

    /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 단위 페이지 미리보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewPagePop")
	public String viewPagePop( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "mng/library/cnts/olc/olc_page_view_create_pop";
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = clibOlcPageService.view(vo).getReturnVO();
		
		vo.setPageCts(vo.getPageCts().replaceAll("ClibMediaCntsHome", "ClibMediaCntsManage"));
		/*
		if(!"create".equals(clibOlcPageVO.getPageDiv()) ){
			String ext = FileUtil.getFileExtention(clibOlcPageVO.getFilePath());

			System.out.println("ext : " + ext);

			if(Constants.MEDIA_FILE_MP3.contains(ext)) {

				returnUrl = "olc_page_view_mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {

				returnUrl = "olc_page_view_mp4";
			} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {

				returnUrl = "olc_page_view_mms";
			} else {
				returnUrl = "olc_page_view_html";
			}
		}
 		*/
		request.setAttribute("vo", vo );
		return returnUrl;
	}

    /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPageFormPop")
	public String addPageFormPop( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		vo.setPageDiv("create"); //-- 초기 셋팅

		String returnUrl = "mng/library/cnts/olc/olc_page_write_link_pop";
/*		if("create".equals(clibOlcPageVO.getPageDiv())) {
			returnUrl = this.getEditorType();
		}*/

		returnUrl = this.getEditorType();
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");	
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return returnUrl;
	}

	 /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPage")
	public String addPage( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ClibOlcPageVO> resultVO = clibOlcPageService.add(vo);

		ClibOlcPageVO pVO = new ClibOlcPageVO();
		if(resultVO.getResult() > 0) {
			pVO.setPageCd(resultVO.getReturnVO().getPageCd());
			pVO.setReturnMessage(getMessage(request, "library.message.contents.olc.page.add.success"));
			pVO.setReturnResult(resultVO.getResult());
		} else {
			pVO.setPageCd("");
			pVO.setReturnMessage(getMessage(request, "library.message.contents.olc.page.add.failed"));
			pVO.setReturnResult(resultVO.getResult());
		}

		return JsonUtil.responseJson(response, pVO);
	}

	 /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPageFormPop")
	public String editPageForm( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo = clibOlcPageService.view(vo).getReturnVO();

		vo.setPageCts(vo.getPageCts().replaceAll("ClibMediaCntsHome", "ClibMediaCntsManage"));

		String returnUrl = "olc_page_write_link";
/*		if("create".equals(clibOlcPageVO.getPageDiv())) {
			returnUrl = this.getEditorType();
		}*/

		returnUrl = this.getEditorType();

		request.setAttribute("paging", "Y");
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return returnUrl;
	}

	 /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPage")
	public String editPage( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String pageCd = vo.getPageCd();
		ProcessResultVO<ClibOlcPageVO> resultVO = clibOlcPageService.edit(vo);

		ClibOlcPageVO pVO = new ClibOlcPageVO();
		if(resultVO.getResult() > 0) {
			pVO.setPageCd(pageCd);
			pVO.setReturnMessage(getMessage(request, "library.message.contents.olc.page.edit.success"));
			pVO.setReturnResult(resultVO.getResult());
		} else {
			pVO.setPageCd("");
			pVO.setReturnMessage(getMessage(request, "library.message.contents.olc.page.edit.failed"));
			pVO.setReturnResult(resultVO.getResult());
		}

		return JsonUtil.responseJson(response, pVO);
	}


	 /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deletePage")
	public String deletePage( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String pageCd = "";
		ProcessResultVO<ClibOlcPageVO> resultVO= clibOlcPageService.remove(vo);

		if(resultVO.getResult() > 0) {
			vo.setPageCd(pageCd);
			vo.setReturnMessage(getMessage(request, "library.message.contents.olc.page.delete.success"));
			vo.setReturnResult(resultVO.getResult());
		} else {
			vo.setPageCd("");
			vo.setReturnMessage(getMessage(request, "library.message.contents.olc.page.parent..failed"));
			vo.setReturnResult(resultVO.getResult());
		}

		return JsonUtil.responseJson(response, vo);
	}

	/**
	 * Contents 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveUpPage")
	public String moveUpPage( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultVO<?> resultVO = clibOlcPageService.move(vo,"up");

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.olc.page.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.olc.page.sort.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * Contents 순서 위로
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/moveDownPage")
	public String moveDownPage( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultVO<?> resultVO = clibOlcPageService.move(vo,"down");

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.olc.page.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.olc.page.sort.failed"));
		}


		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * Contents 순서변경
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sort")
	public String sort( ClibOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = clibOlcPageService.sort(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.olc.page.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.olc.page.sort.failed"));
		}


		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addShareFormPop")
	public String addShareFormPop( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo = clibOlcCntsService.view(vo).getReturnVO();

		//-- 공유 분류 를 가져온다.
		ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
		ccscVO.setOrgCd(orgCd);
		ccscVO.setParCtgrCd("");
		List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
		request.setAttribute("ctgrList", listCtgr);

		//CCL코드
		List<OrgCodeVO> cclCode = orgCodeService.listCode(orgCd,"CCL_CD").getReturnList();
		request.setAttribute("cclCode", cclCode);

		request.setAttribute("vo", vo);
		return "mng/library/cnts/olc/olc_cnts_share_pop";
	}


	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 등록 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addShare")
	public String addShare( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		String[] shareCd = request.getParameterValues("shareCd");
		//관리자는 공유시 바로 공유승인
		vo.setShareStsCd("03");

		ProcessResultVO<ClibOlcCntsVO> resultVO = clibOlcCntsService.addShare(vo, shareCd);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.share.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.share.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShare")
	public String listShare( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		ClibShareOlcCntsVO csocVO = new ClibShareOlcCntsVO();
		csocVO.setOrgCd(orgCd);
		csocVO.setOriginCntsCd(vo.getCntsCd());

		List<ClibShareOlcCntsVO> cntsList = clibShareOlcCntsService.listByOrigin(csocVO).getReturnList();
		
		request.setAttribute("cntsList", cntsList);

		return "mng/library/cnts/olc/olc_cnts_share_list_div";
	}


	 /**
	* 콘텐츠 라이브러리 : 콘텐츠공유 분류 목록
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	*/
	@RequestMapping(value="/listCtgr")
	public String listCtgr( ClibOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String divCd = request.getParameter("divCd");
	
		//-- 공유 분류 를 가져온다.
		ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
		ccscVO.setOrgCd(orgCd);
		ccscVO.setParCtgrCd("");
		ccscVO.setDivCd(divCd);
		List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
		request.setAttribute("ctgrList", listCtgr);
		request.setAttribute("divCd", divCd);
	
		return "mng/library/cnts/olc/olc_cnts_share_ctgr_div";
	}

	/**
	 * 에디터의 유형을 반환한다.
	 * @return
	 */
	private String getEditorType() {
		String forwardUrl = "mng/library/cnts/olc/olc_page_write_create_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) forwardUrl = "mng/library/cnts/olc/olc_page_write_daumeditor_pop";
			else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) forwardUrl = "mng/library/cnts/olc/olc_page_write_summernote_pop";
		}
		return forwardUrl;
	}
}

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
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/library/clibShareCnts")
public class ClibShareCntsManageController extends GenericController {

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 			clibShareMediaCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgOrgInfoService;

	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;

	@Autowired @Qualifier("clibShareOlcPageService")
	private ClibShareOlcPageService 		clibShareOlcPageService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;



	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageMain")
	public String mainManage( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");
		return "mng/library/share/manage/share_manage_main";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리 목록 - 미디어
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listManageShareMedia")
	public String listManageShareMedia( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = clibShareMediaCntsService.listManagePageing(vo,vo.getCurPage(), vo.getListScale());

		request.setAttribute("clibShareMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("clibShareMediaCntsVO", vo);
		return "mng/library/share/manage/share_manage_media_list_div";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리 목록 - OLC
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listManageShareOlc")
	public String listManageShareOlc( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setCtgrCd(request.getParameter("clibShareOlcCntsVO.ctgrCd"));
		vo.setUseYn(request.getParameter("clibShareOlcCntsVO.useYn"));
		vo.setSearchKey(request.getParameter("clibShareOlcCntsVO.searchKey").toUpperCase());
		vo.setSearchValue(request.getParameter("clibShareOlcCntsVO.searchValue"));
		vo.setSearchCd(request.getParameter("clibShareOlcCntsVO.searchCd"));

		ProcessResultListVO<ClibShareOlcCntsVO> resultList = clibShareOlcCntsService.listManagePageing(vo);

		
		request.setAttribute("clibShareOlcCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("clibShareOlcCntsVO", vo);
		
		return "mng/library/share/manage/share_manage_olc_list_div";
	}




	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리 폼 - 미디어
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageShareMediaForm")
	public String manageShareMediaForm( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = clibShareMediaCntsService.view(vo).getReturnVO();

		//-- 공유 분류 를 가져온다.
		ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
		ccscVO.setOrgCd(orgCd);
		ccscVO.setParCtgrCd("");
		ccscVO.setDivCd(vo.getShareDivCd());
		List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
		request.setAttribute("ctgrList", listCtgr);

		request.setAttribute("vo", vo);
		return "mng/library/share/manage/share_manage_media_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리 - 미디어
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageShareMedia")
	public String manageShareMedia( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<ClibShareMediaCntsVO> resultVO = clibShareMediaCntsService.updateShareCd(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.manage.change.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.manage.change.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);

	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리 폼 - OLC
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageShareOlcForm")
	public String manageShareOlcForm( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setCntsCd(request.getParameter("clibOlcCntsVO.cntsCd"));

		vo = clibShareOlcCntsService.view(vo).getReturnVO();

		//-- 공유 분류 를 가져온다.
		ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
		ccscVO.setOrgCd(orgCd);
		ccscVO.setParCtgrCd("");
		ccscVO.setDivCd(vo.getShareDivCd());
		List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
		request.setAttribute("ctgrList", listCtgr);

		request.setAttribute("vo", vo);
		return "mng/library/share/manage/share_manage_olc_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유신청관리 - OLC
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageShareOlc")
	public String manageShareOlc( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setReqSn(Integer.parseInt(StringUtil.nvl(request.getParameter("clibShareOlcCntsVO.reqSn"),"0")));
		vo.setShareReqStsCd(request.getParameter("clibShareOlcCntsVO.shareReqStsCd"));

		ProcessResultVO<ClibShareOlcCntsVO> resultVO = clibShareOlcCntsService.updateShareCd(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.manage.change.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.manage.change.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


    /**
	 * 콘텐츠 라이브러리 : 온라인 과목 > 콘텐츠 관리에서 사용되는 미디어 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listMediaCntsForCntsMgr")
	public String listMediaCntsForCntsMgr( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String sbjType = request.getParameter("sbjType");
		vo.setOrgCd(orgCd);
		vo.setShareDivCd("CNTS");
		
		
		ProcessResultListVO<ClibShareMediaCntsVO> resultList = clibShareMediaCntsService.list(vo);

		request.setAttribute("clibShareMediaCntsList", resultList.getReturnList());
		request.setAttribute("sbjType", sbjType);
		return"mng/library/share/media_cnts_list_for_cnts_mgr_div";
	}

    /**
	 * 콘텐츠 라이브러리 : 온라인 과목 > 콘텐츠 관리에서 사용되는 미디어 콘텐츠 미리보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/previewMediaCnts")
	public String previewMediaCnts( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		vo.setOrgCd(orgCd);
		//-- 미디어 콘텐츠 정보를 가져온다.
		vo = clibShareMediaCntsService.view(vo).getReturnVO();

		request.setAttribute("playerDiv", vo.getPlayerDiv());

		String ext = FileUtil.getFileExtention(vo.getFileNm());
		String fileExt = "none";
		if(Constants.MEDIA_FILE_MP3.contains(ext)) {
			fileExt = "mp3";
		} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
			fileExt = "mp4";
		}
		request.setAttribute("filePath", "/contents"+vo.getFilePath());
		request.setAttribute("fileName", vo.getFileNm());
		request.setAttribute("fileExt", fileExt);
		request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
		return "mng/library/share/media_cnts_preview_pop";
	}

    /**
	 * 콘텐츠 라이브러리 : 온라인 과목 > 콘텐츠 관리에서 사용되는 Olc 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listOlcCntsForCntsMgr")
	public String listOlcCntsForCntsMgr( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setShareDivCd("CNTS");
		vo.setCtgrCd(request.getParameter("clibShareOlcCntsVO.ctgrCd"));
		vo.setUseYn(request.getParameter("clibShareOlcCntsVO.useYn"));
		vo.setSearchValue(request.getParameter("clibShareOlcCntsVO.searchValue").toUpperCase());

		ProcessResultListVO<ClibShareOlcCntsVO> resultList = clibShareOlcCntsService.list(vo);

		request.setAttribute("clibShareOlcCntsList", resultList.getReturnList());
		return "mng/library/share/olc_cnts_list_for_cnts_mgr_div";
	}

	/**
	 * 콘텐츠 라이브러리 : OLC페이지제작 사용되는 미디어 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listMediaCntsForOlc")
	public String listMediaCntsForOlc( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = clibShareMediaCntsService.listOlc(vo);

		request.setAttribute("clibShareMediaCntsList", resultList.getReturnList());
		
		return "mng/library/share/media_cnts_list_select";
	}





    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cntsMain")
	public String cntsMain( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("jstree", "Y");	
		return "mng/library/share/cnts/share_cnts_main";
	}


	 /**
	 * 콘텐츠 라이브러리 : 콘텐츠공유관리 > 미디어 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShareMediaCnts")
	public String listShareMediaCnts( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setShareDivCd("CNTS");
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = clibShareMediaCntsService.listPageing(vo);

		
		request.setAttribute("clibShareMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/library/share/cnts/media_cnts_list_div";
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠공유관리>  Olc 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShareOlcCnts")
	public String listShareOlcCnts( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setCtgrCd(request.getParameter("ctgrCd"));
		vo.setUseYn(request.getParameter("useYn"));
		vo.setSearchKey(request.getParameter("searchKey"));
		vo.setSearchValue(request.getParameter("searchValue").toUpperCase());
		vo.setSearchCd(request.getParameter("searchCd"));
		vo.setShareDivCd("CNTS");

		ProcessResultListVO<ClibShareOlcCntsVO> resultList = clibShareOlcCntsService.listPageing(vo);
		
		request.setAttribute("clibShareOlcCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/library/share/cnts/olc_cnts_list_div";
	}


    /**
	 * 콘텐츠 라이브러리 : 지식공유 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/knowMain")
	public String knowMain( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("clibShareMediaCntsVO", vo);
		return "mng/library/share/know/share_cnts_main";
	}


	 /**
	 * 콘텐츠 라이브러리 : 지식공유관리 > 미디어 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShareMediaKnow")
	public String listShareMediaKnow( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setShareDivCd("KNOW");
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = clibShareMediaCntsService.listPageing(vo);

		request.setAttribute("clibShareMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "mng/library/share/know/media_cnts_list_div";
	}

    /**
	 * 콘텐츠 라이브러리 : 지식공유관리>  Olc 콘텐츠 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listShareOlcKnow")
	public String listShareOlcKnow( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setCtgrCd(request.getParameter("clibShareOlcCntsVO.ctgrCd"));
		vo.setUseYn(request.getParameter("clibShareOlcCntsVO.useYn"));
		vo.setSearchKey(request.getParameter("clibShareOlcCntsVO.searchKey"));
		vo.setSearchValue(request.getParameter("clibShareOlcCntsVO.searchValue").toUpperCase());
		vo.setSearchCd(request.getParameter("clibShareOlcCntsVO.searchCd"));
		vo.setShareDivCd("KNOW");

		ProcessResultListVO<ClibShareOlcCntsVO> resultList = clibShareOlcCntsService.listPageing(vo);

		request.setAttribute("clibShareOlcCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/library/share/know/olc_cnts_list_div";
	}


	/**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 공유 삭제 / 해제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeMediaCnts")
	public String removeMediaCnts( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = clibShareMediaCntsService.delete(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 공유 삭제 / 해제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeShareMediaCnts")
	public String removeShareMediaCnts( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = clibShareMediaCntsService.updateNoShare(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 공유 삭제 / 해제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeShareOlcCnts")
	public String removeShareOlcCnts( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setCntsCd(request.getParameter("cntsCd"));

		ProcessResultVO<?> resultVO = clibShareOlcCntsService.updateNoShare(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 공유 거절
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/rejectShareMediaCnts")
	public String rejectShareMediaCnts( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = clibShareMediaCntsService.rejectShare(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 공유 거절
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/rejectShareOlcCnts")
	public String rejectShareOlcCnts( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setCntsCd(request.getParameter("cntsCd"));

		ProcessResultVO<?> resultVO = clibShareOlcCntsService.rejectShare(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리  : 미리보기 폼- 공유 미디어
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/previewMediaPop")
	public String previewMediaPop( ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		vo.setOrgCd(orgCd);
		//-- 미디어 콘텐츠 정보를 가져온다.
		vo = clibShareMediaCntsService.view(vo).getReturnVO();

		request.setAttribute("uldStsCd", "complete");
		request.setAttribute("playerDiv", vo.getPlayerDiv());
		if("VOD".equals(vo.getCntsTypeCd())) {
			String ext = FileUtil.getFileExtention(vo.getFileNm());
			String fileExt = "none";
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				fileExt = "mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				fileExt = "mp4";
			}
			request.setAttribute("filePath", "/contents"+vo.getFilePath());
			request.setAttribute("fileName", vo.getFileNm());
			request.setAttribute("fileExt", fileExt);
			request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
		}else if("CDN".equals(vo.getCntsTypeCd())) {
		
			String ext = FileUtil.getFileExtention(vo.getFileNm());
			String filePath = "";
			ext = FileUtil.getFileExtention(vo.getFilePath());
			filePath = vo.getFilePath();

			String fileExt = "none";
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				fileExt = "mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				fileExt = "mp4";
			}
			request.setAttribute("filePath", filePath);
			request.setAttribute("fileName", vo.getFileNm());
			request.setAttribute("fileExt", fileExt);
			request.setAttribute("cntsTypeCd", vo.getCntsTypeCd());
			request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
			request.setAttribute("flowplayer", "Y");
		}else if("MLINK".equals(vo.getCntsTypeCd())) {
			if(vo != null) {
				request.setAttribute("filePath", vo.getFilePath());
				request.setAttribute("fileName", vo.getFileNm());
			}
			request.setAttribute("orgInfoVO", orgInfoVO);
		}
		return "mng/library/share/media/media_cnts_preview_pop";
	}

	/**
	 * OLC 콘텐츠 미리보기 - 공유 OLC
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/previewOlcMain")
	public String previewOlcMain( ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		//-- OLC 정보를 가져온다
		vo.setOrgCd(orgCd);
		vo = clibShareOlcCntsService.view(vo).getReturnVO();

		//-- 페이지 목록을 가져온다.
		ClibShareOlcPageVO clibShareOlcPageVO = new ClibShareOlcPageVO();
		clibShareOlcPageVO.setOrgCd(orgCd);
		clibShareOlcPageVO.setCntsCd(vo.getCntsCd());
		List<ClibShareOlcPageVO> resultList = clibShareOlcPageService.list(clibShareOlcPageVO).getReturnList();

		request.setAttribute("clibShareOlcCntsVO", vo);
		request.setAttribute("clibShareOlcPageList", resultList);
		return "mjsp/library/share/olc/preview_main_pop";
	}

	 /**
	 * 콘텐츠 라이브러리 : OLC 콘텐츠 페이지 단위  공유 페이지 미리보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public String viewPage( ClibShareOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "mng/library/share/olc/olc_page_view_create_pop";
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = clibShareOlcPageService.view(vo).getReturnVO();

		vo.setPageCts(vo.getPageCts().replaceAll("ClibMediaCntsHome", "ClibShareCntsManage"));

		request.setAttribute("clibShareOlcPageVO", vo);
		return returnUrl;
	}
}

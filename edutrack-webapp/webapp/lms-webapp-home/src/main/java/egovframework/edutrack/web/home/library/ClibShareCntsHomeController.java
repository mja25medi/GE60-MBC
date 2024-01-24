package egovframework.edutrack.web.home.library;

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
@RequestMapping(value="/home/library/clibShareCnts")
public class ClibShareCntsHomeController extends GenericController {

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 			clibShareMediaCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgInfoService;

	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;

	@Autowired @Qualifier("clibShareOlcPageService")
	private ClibShareOlcPageService 		clibShareOlcPageService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 			clibShareCntsCtgrService;

	 /**
	 * 콘텐츠 라이브러리 : 지식공유 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/knowMain")
	public String knowMain(ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
		ccscVO.setOrgCd(orgCd);
		ccscVO.setParCtgrCd("");
		ccscVO.setDivCd("KNOW");
		List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.listSub(ccscVO).getReturnList();
		request.setAttribute("listCtgr", listCtgr);

	   	request.setAttribute("vo", vo);
		return "home/library/share/know/share_cnts_main";
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
	public String listShareMediaKnow(ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setShareDivCd("KNOW");
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = clibShareMediaCntsService.listPageing(vo);

		request.setAttribute("clibShareMediaCntsList", resultList.getReturnList());
	   	request.setAttribute("pageInfo", resultList.getPageInfo());

		return "home/library/share/know/media_cnts_list_div";
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
	public String listShareOlcKnow(ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
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
	   	request.setAttribute("vo", vo);

		return "home/library/share/know/olc_cnts_list_div";
	}

	/**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 공유 삭제 / 해제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeMediaCnts")
	public String removeMediaCnts(ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
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
	public String removeShareMediaCnts(ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
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
	 * 콘텐츠 라이브러리 : Olc 콘텐츠 공유 삭제 / 해제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeOlcCnts")
	public String removeOlcCnts(ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String cntsCd = request.getParameter("cntsCd");

		vo.setOrgCd(orgCd);
		vo.setCntsCd(cntsCd);
		ProcessResultVO<?> resultVO = clibShareOlcCntsService.delete(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 콘텐츠 라이브러리 : Olc 콘텐츠 공유 해제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeShareOlcCnts")
	public String removeShareOlcCnts(ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String cntsCd = request.getParameter("cntsCd");

		vo.setOrgCd(orgCd);
		vo.setCntsCd(cntsCd);
		ProcessResultVO<?> resultVO = clibShareOlcCntsService.updateNoShare(vo);

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
	public String previewMediaPop(ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);

		vo.setOrgCd(orgCd);
		//-- 미디어 콘텐츠 정보를 가져온다.
		vo = clibShareMediaCntsService.view(vo).getReturnVO();

		request.setAttribute("uldStsCd", "complete");
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
		return "home/library/share/media/media_cnts_preview_pop";
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
	public String previewOlcMain(ClibShareOlcCntsVO vo, Map commandMap, ModelMap model,
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

	   	request.setAttribute("vo", vo);
	   	request.setAttribute("clibShareOlcPageList", resultList);

		return "home/library/share/olc/preview_main_pop";
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
	public String viewPage(ClibShareOlcPageVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String returnUrl = "home/library/share/olc/olc_page_view_create_pop";
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = clibShareOlcPageService.view(vo).getReturnVO();

		vo.setPageCts(vo.getPageCts().replaceAll("ClibMediaCntsHome", "ClibShareCntsManage"));

	   	request.setAttribute("vo", vo);

		return returnUrl;
	}

}

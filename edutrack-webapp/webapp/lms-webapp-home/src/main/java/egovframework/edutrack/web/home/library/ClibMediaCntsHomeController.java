package egovframework.edutrack.web.home.library;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UrlConnectUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsService;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/library/clibMediaCnts")
public class ClibMediaCntsHomeController extends GenericController {
	@Autowired @Qualifier("clibMediaCntsService")
	private ClibMediaCntsService 			clibMediaCntsService;

	@Autowired @Qualifier("clibCntsCtgrService")
	private ClibCntsCtgrService 			clibCntsCtgrService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 		clibShareMediaCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgInfoService;

	  /**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCnts")
	public String listCnts(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		if(StringUtil.isNotNull(vo.getSearchValue())){
			vo.setSearchValue(vo.getSearchValue().toUpperCase());
		}

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);
		request.setAttribute("kollusUseYn", orgInfoVO.getKollusUseYn());


		if("root".equals(vo.getCtgrCd())){
			vo.setCtgrCd("");
		}
		vo.setPageScale(Constants.LIST_PAGE_SCALE);
		ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listPageing(vo);

		request.setAttribute("clibMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("clibMediaCntsVO", vo);


		return "/home/library/cnts/media/media_cnts_main";
	}

    /**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 목록 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setSearchValue(vo.getSearchValue().toUpperCase());
		vo.setPageScale(Constants.LIST_PAGE_SCALE);
		
		ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listPageing(vo);

		request.setAttribute("clibMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "/home/library/cnts/media/media_cnts_list_div";
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
	public String addForm(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo.setUseYn("Y"); //-- 초기 사용으로 셋팅
		vo.setUldStsCd("NO"); //-- 초기 사용으로 셋팅

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

		request.setAttribute("gubun", "A");
		request.setAttribute("clibMediaCntsVO", vo);

		return "home/library/cnts/media/media_cnts_write_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		if(ValidationUtils.isNotEmpty(vo.getUldFileKey())) {
			vo.setUldStsCd("upload");
		}

		//-- 등록시 파일 경로에 ORG_CD를 붙여서 저장이 되도록 수정함.
		vo.setFilePath("/"+orgCd+vo.getFilePath());

		ProcessResultVO<ClibMediaCntsVO> resultVO = clibMediaCntsService.add(vo);
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
	public String editForm(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = clibMediaCntsService.view(vo).getReturnVO();

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
	   	request.setAttribute("clibMediaCntsVO", vo);
		return "home/library/cnts/media/media_cnts_write_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ClibMediaCntsVO cmcVO = clibMediaCntsService.view(vo).getReturnVO();
		//-- 기존 정보로 덥어 쓰기
		vo.setChanlKey(cmcVO.getChanlKey());
		vo.setChanlNm(cmcVO.getChanlNm());
		vo.setMediaCntsKey(cmcVO.getMediaCntsKey());
		vo.setProfileKey(cmcVO.getProfileKey());
		vo.setHits(cmcVO.getHits());
		vo.setUldStsCd(cmcVO.getUldStsCd()); //-- 상태값은 자동 변경된 값만 사용함.

		ProcessResultVO<ClibMediaCntsVO> resultVO = clibMediaCntsService.edit(vo);
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
	public String remove(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		vo = clibMediaCntsService.view(vo).getReturnVO();

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);

		int error = 0;
		if(vo.getSharedCnt() == 0) {
			//-- 공유가 안되어 있을 경우만 파일 삭제, 공유가 되어 잇는 경우는 파일은 삭제 하이 않음
			if("common".equals(vo.getPlayerDiv())) {
				try {
					//-- 일반 파일일 경우 파일 및 포더 삭제
					FileUtil.delDirectory(Constants.CONTENTS_STORAGE_PATH + "\\" + orgCd+vo.getFilePath());
				} catch (Exception e) {
					error = 200;
				}
			}
		}

		ProcessResultVO<ClibMediaCntsVO> resultVO = null;
		if(error == 0) {
			resultVO = clibMediaCntsService.delete(vo);
		} else {
			resultVO = new ProcessResultVO<ClibMediaCntsVO>();
			resultVO.setResult(-1);
		}
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "library.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

    /**
	 * 콘텐츠 라이브러리 : 미디어 파일 업로드 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addUploadForm")
	public String addUploadForm(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);

		String currentDate = DateTimeUtil.getCurrentString();
		String filePath = "/mediacnts/"+userNo+"/"+currentDate;
		request.setAttribute("filePath", filePath);
	   	request.setAttribute("clibMediaCntsVO", vo);

		return "home/library/cnts/media/common_upload";
	}

	/*
	 * @RequestMapping(value="/preview") public String preview(ClibMediaCntsVO vo,
	 * Map commandMap, ModelMap model, HttpServletRequest request,
	 * HttpServletResponse response) throws Exception { commonVOProcessing(vo,
	 * request);
	 * 
	 * String orgCd = UserBroker.getUserOrgCd(request); String userNo =
	 * UserBroker.getUserNo(request);
	 * 
	 * //-- 기관 정보를 가져온다. OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
	 * orgInfoVO.setOrgCd(orgCd); orgInfoVO = orgInfoService.view(orgInfoVO);
	 * 
	 * ClibShareMediaCntsVO VO = new ClibShareMediaCntsVO(); VO.setOrgCd(orgCd);
	 * //VO.setUserNo(userNo); //-- 미디어 콘텐츠 정보를 가져온다. //vo =
	 * clibMediaCntsService.view(vo).getReturnVO(); VO =
	 * clibShareMediaCntsService.view(VO).getReturnVO();
	 * 
	 * request.setAttribute("uldStsCd", vo.getUldStsCd());
	 * request.setAttribute("playerDiv", vo.getPlayerDiv());
	 * 
	 * if("kollus".equals(vo.getPlayerDiv())) { //-- Player가 콜루스 인 경우 //-- 미디어 토큰을
	 * 받아 온다. String mediaToken =
	 * KollusMediaTokenUtil.getKollusMediaToken(orgInfoVO.getKollusKeyCd(),
	 * vo.getMediaCntsKey(), "", ""); request.setAttribute("playerUrl",
	 * Constants.KOLLUS_PLAYER_URL); request.setAttribute("mediaToken", mediaToken);
	 * } else { String ext = FileUtil.getFileExtention(vo.getFileNm()); String
	 * fileExt = "none"; if(Constants.MEDIA_FILE_MP3.contains(ext)) { fileExt =
	 * "mp3"; } else if(Constants.MEDIA_FILE_MP4.contains(ext)) { fileExt = "mp4"; }
	 * request.setAttribute("filePath", "/contents"+vo.getFilePath());
	 * request.setAttribute("fileName", vo.getFileNm());
	 * request.setAttribute("fileExt", fileExt);
	 * request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY); } return
	 * "home/library/cnts/media/media_cnts_preview_pop"; }
	 */
	
	/**
	 * 콘텐츠 라이브러리 : 미리보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/preview")
	public String preview(ClibShareMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);

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
		return "home/library/cnts/media/media_cnts_preview_pop";
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addShareForm")
	public String addShareForm(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		vo = clibMediaCntsService.view(vo).getReturnVO();

		//-- 공유 분류 를 가져온다.
		ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
		ccscVO.setOrgCd(orgCd);
		ccscVO.setParCtgrCd("");
		List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
		request.setAttribute("ctgrList", listCtgr);

	   	request.setAttribute("clibMediaCntsVO", vo);

		return "home/library/cnts/media/media_cnts_share_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 등록 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addShare")
	public String addShare(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		String[] shareCd = request.getParameterValues("shareCd");
		//사용자는 공유시 공유신청
		vo.setShareStsCd("01");

		ProcessResultVO<ClibMediaCntsVO> resultVO = clibMediaCntsService.addShare(vo,shareCd);
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
	public String listShare(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		ClibShareMediaCntsVO csmcVO = new ClibShareMediaCntsVO();
		csmcVO.setOrgCd(orgCd);
		csmcVO.setOriginCntsCd(vo.getCntsCd());
		List<ClibShareMediaCntsVO> cntsList = clibShareMediaCntsService.listByOrigin(csmcVO).getReturnList();
		request.setAttribute("cntsList", cntsList);

		return "/home/library/cnts/media/media_cnts_share_list_div";
	}

	/**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 목록 리스트 - OLC콘텐츠 추가용
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listOlc")
	public String listOlc(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listOlc(vo);

		request.setAttribute("courseList", resultList.getReturnList());
	   	request.setAttribute("pageInfo", resultList.getPageInfo());

		return "home/library/cnts/media/media_cnts_list_select";
	}


}

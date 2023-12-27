package egovframework.edutrack.web.manage.library;

import java.util.Iterator;
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
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UrlConnectUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.kollus.service.KollusResultVO;
import egovframework.edutrack.modules.kollus.util.KollusMediaTokenUtil;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsService;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/library/clibMediaCnts")
public class ClibMediaCntsManageController extends GenericController {

	@Autowired @Qualifier("clibMediaCntsService")
	private ClibMediaCntsService 			clibMediaCntsService;

	@Autowired @Qualifier("clibCntsCtgrService")
	private ClibCntsCtgrService 			clibCntsCtgrService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 		clibShareMediaCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgOrgInfoService;

	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService 	 orgCodeService;

    /**
	 * 콘텐츠 라이브러리 : 미디어 콘텐츠 관리 메인 페이지
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value="/main")
	public String main( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		request.setAttribute("kollusUseYn", orgInfoVO.getKollusUseYn());
		request.setAttribute("kollusPlayerUrl", Constants.KOLLUS_PLAYER_URL);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");
		return "mng/library/cnts/media/media_cnts_main";
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
	public String list( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		//vo.setUserNo(userNo);		// 전체 조회를 위해 사용자 번호 주석 
		vo.setSearchValue(vo.getSearchValue().toUpperCase());

		ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listPageing(vo);

		request.setAttribute("clibMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/library/cnts/media/media_cnts_list_div";
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
	public String addForm( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		//vo.setUserNo(userNo);
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
		//cccVO.setUserNo(userNo);
		List<ClibCntsCtgrVO> ctgrList = clibCntsCtgrService.list(cccVO).getReturnList();
		request.setAttribute("ctgrList", ctgrList);
		
		// 경로 가져오기
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		request.setAttribute("orgInfoVO", orgInfoVO);
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/library/cnts/media/media_cnts_write";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		if(ValidationUtils.isNotEmpty(vo.getUldFileKey())) {
			vo.setUldStsCd("upload");
		}
		
		if ("VOD".equals(vo.getCntsType())) {
			//-- 등록시 파일 경로에 ORG_CD를 붙여서 저장이 되도록 수정함. 
			//vo.setFilePath("/"+orgCd+vo.getFilePath());
			
			String filePath = vo.getFilePath();
			filePath = filePath.substring(0, filePath.length()-1);
			vo.setFilePath("/"+orgCd+filePath);
		}else {
			//cntsType 이 vod가 아니면 주소라서 그냥 저장
			String filePath = vo.getFilePath();
			if(filePath.charAt(0) == ',') {
				filePath = filePath.substring(1);
				vo.setFilePath(filePath);				
			}
			vo.setUldStsCd("complete");
			vo.setPlayerDiv("common");
		}

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
	public String editForm( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
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
		
		
		// 경로 가져오기
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
				
		String cntsPath = vo.getFilePath();
		cntsPath += "/"+vo.getFileNm();
		vo.setCntsFilePath(cntsPath);
				
		request.setAttribute("orgInfoVO", orgInfoVO);
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/library/cnts/media/media_cnts_write";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
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
	public String remove( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		//vo.setUserNo(userNo);

		vo = clibMediaCntsService.view(vo).getReturnVO();

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		int error = 0;
		if(vo.getSharedCnt() == 0) {
			//-- 공유가 안되어 있을 경우만 파일 삭제, 공유가 되어 잇는 경우는 파일은 삭제 하이 않음
			if("kollus".equals(vo.getPlayerDiv())) {
				//-- 콜루스 사용여부에 따라 파일을 삭제한다.
				String args[] = new String[1];
				args[0] = "access_token="+orgInfoVO.getKollusTokenCd();
				String connectUrl = Constants.KOLLUS_API_URL+"/0/media/library/delete/"+vo.getUldFileKey();
				KollusResultVO krVO = getKollusResult(UrlConnectUtil.getUrlConnect(connectUrl, args));
				error = krVO.getError();
				error = 0; // kollus 에서 오류가 발생 하여도 삭제 되도록함.
			} else if("common".equals(vo.getPlayerDiv())) {
				try {
					//-- 일반 파일일 경우 파일 및 포더 삭제
					FileUtil.delDirectory(Constants.CONTENTS_STORAGE_PATH + "\\" + orgCd + vo.getFilePath());
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
	@RequestMapping(value="/addUploadPop")
	public String addUploadPop( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		String fowardUrl = "mng/library/cnts/media/kollus_upload_pop";
		if("Y".equals(orgInfoVO.getKollusUseYn())) {
			request.setAttribute("kollusUseYn", orgInfoVO.getKollusUseYn());
			request.setAttribute("kollusTokenCd", orgInfoVO.getKollusTokenCd());
			request.setAttribute("kollusCtgrCd", orgInfoVO.getKollusCtgrCd());
		} else {
			String currentDate = DateTimeUtil.getCurrentString();
			String filePath = "/mediacnts/"+userNo+"/"+currentDate;
			request.setAttribute("filePath", filePath);
			fowardUrl = "mng/library/cnts/media/common_upload_pop";
		}

		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		
		
		return fowardUrl;
	}

    /**
	 * 콘텐츠 라이브러리 : 미리보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/preview")
	public String preview( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		//-- 미디어 콘텐츠 정보를 가져온다.
		vo = clibMediaCntsService.view(vo).getReturnVO();

		request.setAttribute("uldStsCd", vo.getUldStsCd());
		request.setAttribute("playerDiv", vo.getPlayerDiv());
		if("VOD".equals(vo.getCntsType())) {
			if("kollus".equals(vo.getPlayerDiv())) {
				//-- Player가 콜루스 인 경우
				//-- 미디어 토큰을 받아 온다.
				String mediaToken = KollusMediaTokenUtil.getKollusMediaToken(orgInfoVO.getKollusKeyCd(),
						vo.getMediaCntsKey(), "", "");
				request.setAttribute("playerUrl", Constants.KOLLUS_PLAYER_URL);
				request.setAttribute("mediaToken", mediaToken);
			} else {
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
			}
		}else if("CDN".equals(vo.getCntsType())) {
			
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
			request.setAttribute("cntsTypeCd", vo.getCntsType());
			request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
			request.setAttribute("flowplayer", "Y");
		}
		return "mng/library/cnts/media/media_cnts_preview_pop";
	}

	/**
	 * 콜루스 API 통하여 가져온 JsonString 갑을 VO에 담아 반환한다.
	 * @param resultJsonString
	 * @return
	 */
	private KollusResultVO getKollusResult(String resultJsonString) {
		KollusResultVO krVO = new KollusResultVO();
		JSONObject json = (JSONObject) JSONSerializer.toJSON(resultJsonString);
	    int error = json.getInt( "error" );
	    String message = "";
	    if(error !=0) {
	    	message = json.getString("message");
	    }
	    try {
		    JSONObject result = json.getJSONObject("result");
		    krVO.setError(error);
		    krVO.setMessage(message);
		    if(error == 0) {
	    		Iterator iter = result.keys();
	    		while(iter.hasNext()){
	    			String key = (String)iter.next();
	    			String value = result.getString(key);
	    			krVO.addResult(key, value);
	    		}
		    }
    	} catch (Exception e) {
    		System.out.println("::::::::::::::::::::::::::>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+message);
    	}
		return krVO;
	}

    /**
	 * 콘텐츠 라이브러리 : 콜루스 API 통하여 UPLOAD 관련 정보를 받아 온다.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getUploadInfo")
	public String getUploadInfo( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		String isAudioUpload = StringUtil.nvl(request.getParameter("is_audio_upload"),"0");

		// 교육기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		request.setAttribute("kollusUseYn", orgInfoVO.getKollusUseYn());

		//-- 콜루스 사용여부에 따라 파일 업도르 정보 가져온다.
		String args[] = new String[3];
		args[0] = "category_key="+orgInfoVO.getKollusCtgrCd();
		args[1] = "access_token="+orgInfoVO.getKollusTokenCd();
		args[2] = "is_audio_upload="+isAudioUpload;
		String connectUrl = Constants.KOLLUS_API_URL+"/0/media_auth/upload/create_url";
		KollusResultVO krVO = getKollusResult(UrlConnectUtil.getUrlConnect(connectUrl, args));

		request.setAttribute("vo", vo);
		return JsonUtil.responseJson(response, krVO);
	}

    /**
	 * 콘텐츠 라이브러리 : KOLLUS에 업로드된 파일이 트렌스 코딩이 완료되면 Kollus에서 보내주는 Callback
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/indexUploadCallback")
	public String indexUploadCallback( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception  {
		commonVOProcessing(vo, request);
		//String content_provider_key = request.getParameter("content_provider_key");
		//String filename = request.getParameter("filename");
		String upload_file_key = request.getParameter("upload_file_key");
		String media_content_key = request.getParameter("media_content_key");
		String channel_key = request.getParameter("channel_key");
		String channel_name = request.getParameter("channel_name");
		String profile_key = request.getParameter("profile_key");

		//upload file key를 이용하여 콘텐츠 정보를 가져온다.
		try {
			ClibMediaCntsVO clibMediaCntsVO = new ClibMediaCntsVO();
			clibMediaCntsVO.setUldFileKey(upload_file_key);
			clibMediaCntsVO = clibMediaCntsService.viewByUploadFileKey(clibMediaCntsVO).getReturnVO();

			if(!"CH".equals(clibMediaCntsVO.getUldStsCd())) {
				clibMediaCntsVO.setMediaCntsKey(media_content_key);
				clibMediaCntsVO.setChanlKey(channel_key);
				clibMediaCntsVO.setChanlNm(channel_name);
				clibMediaCntsVO.setProfileKey(profile_key);
				clibMediaCntsVO.setUldStsCd("complete");

				//-- 정보를 수정한다.
				clibMediaCntsService.edit(clibMediaCntsVO);
			}
		} catch (Exception e) {
			//-- 저장된 정보가 없을 경우 처리 로직 필요
			//-- 저장된 콘텐츠 정보가 없거나 수정이 안되었을 경우 400 리턴함.
			log.debug(":::::::::::::::::::::: =========================== Kollus challen upload callback error =================> "+e.getMessage());
			response.setStatus(500);
			return null;
		}
		response.setStatus(200);
		return "mng/library/cnts/media/kollus_upload_callback";
	}

    /**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addSharePop")
	public String addSharePop( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
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

		//CCL코드
		List<OrgCodeVO> cclCode = orgCodeService.listCode(orgCd,"CCL_CD").getReturnList();
		request.setAttribute("cclCode", cclCode);

		request.setAttribute("vo", vo);
		return "mng/library/cnts/media/media_cnts_share_pop";
	}

	/**
	 * 콘텐츠 라이브러리 : 콘텐츠 공유 등록 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addShare")
	public String addShare( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		String[] shareCd = request.getParameterValues("shareCd");
		//관리자는 공유시 바로 공유승인
		vo.setShareStsCd("03");

		ProcessResultVO<ClibMediaCntsVO> resultVO = clibMediaCntsService.addShare(vo, shareCd);

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
	public String listShare( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		ClibShareMediaCntsVO csmcVO = new ClibShareMediaCntsVO();
		csmcVO.setOrgCd(orgCd);
		csmcVO.setOriginCntsCd(vo.getCntsCd());
		List<ClibShareMediaCntsVO> cntsList = clibShareMediaCntsService.listByOrigin(csmcVO).getReturnList();
		request.setAttribute("cntsList", cntsList);

		return "mng/library/cnts/media/media_cnts_share_list_div";
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
	public String listOlc( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);

		ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listOlc(vo);

		request.setAttribute("clibMediaCntsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/library/cnts/media/media_cnts_list_select";
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
	public String listCtgr( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
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

		return "mng/library/cnts/media/media_cnts_share_ctgr_div";
	}

}

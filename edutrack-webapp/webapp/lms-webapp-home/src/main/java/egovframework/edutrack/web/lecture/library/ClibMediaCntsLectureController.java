package egovframework.edutrack.web.lecture.library;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.kollus.util.KollusMediaTokenUtil;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsService;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/clibMediaCnts")
public class ClibMediaCntsLectureController extends GenericController {
	@Autowired @Qualifier("clibMediaCntsService")
	private ClibMediaCntsService 			clibMediaCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgInfoService;

	/**
	 * 콘텐츠 라이브러리 : 미리보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/preview")
	public String preview(ClibMediaCntsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		//-- 기관 정보를 가져온다.
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);

		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		//-- 미디어 콘텐츠 정보를 가져온다.
		vo = clibMediaCntsService.view(vo).getReturnVO();

		request.setAttribute("uldStsCd", vo.getUldStsCd());
		request.setAttribute("playerDiv", vo.getPlayerDiv());

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
		return "home/lecture/bookmark/media_cnts_preview_pop";
	}
}

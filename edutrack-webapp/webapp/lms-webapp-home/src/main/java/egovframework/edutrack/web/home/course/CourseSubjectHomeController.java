package egovframework.edutrack.web.home.course;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
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
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;



/**
 * 과정 컨트롤러 - subject 기준
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/course/subject")
public class CourseSubjectHomeController extends GenericController {

	@Autowired @Qualifier("contentsService")
	private ContentsService 				contentsService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService 	createCourseSubjectService;

	@Autowired @Qualifier("subjectService")
	private SubjectService	subjectService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgInfoService;
	
	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;
	
	@Autowired @Qualifier("clibShareOlcPageService")
	private ClibShareOlcPageService 		clibShareOlcPageService;

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 		clibShareMediaCntsService;

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService			bookmarkService;
	
	/**
	 * [HRD] 과정(online subject) 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listOnineMain")
	public String listOnineSbjMain( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setUseYn("");
		vo.setOrgCd(orgCd);

		//과정(subject) 분류 조회
		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setOrgCd(orgCd);
		subjectCategoryVO.setSbjCtgrTypeCd("ON");//온라인과목
		ProcessResultListVO<SubjectCategoryVO> sbjCtgrList = subjectService.listCategory(subjectCategoryVO);
		request.setAttribute("sbjCtgrList", sbjCtgrList.getReturnList());

		String refundYn = StringUtil.nvl(vo.getRefundYn());
		if(!"Y".equals(refundYn) && !"N".equals(refundYn)) {
			vo.setRefundYn("");
		}
		
		//과정(subject) 리스트 페이징 조회
		ProcessResultListVO<OnlineSubjectVO> onSbjList = subjectService.listOnlinePageing(vo, vo.getCurPage(), vo.getListScale(), true);
		request.setAttribute("onSbjList", onSbjList.getReturnList());
	   	request.setAttribute("pageInfo", onSbjList.getPageInfo());
	   	request.setAttribute("vo", vo);
	   	request.setAttribute("paging", "Y");
	   	
	   	returnUrl = "home/course/subject/list_online_subject";

		return returnUrl;
	}
	
	/**
	 * [HRD] 과정(online subject) 정보 보기 - subject 기준
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCourseMain")
	public String readCourseMain( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//과정(subject) 조회
		ProcessResultVO<OnlineSubjectVO> onSbjResultVO = subjectService.viewOnline(vo);
		request.setAttribute("onSbjVO", onSbjResultVO.getReturnVO());
		
		if(onSbjResultVO.getReturnVO() == null) {
			setAlertMessage(request, "과정 정보가 존재하지 않습니다.");
			return "redirect:"+
				new URLBuilder("/home/course/subject/listOnineMain")
					.addParameter("bbsCd", vo.getSbjCtgrCd())
					.addParameter("refundYn", vo.getRefundYn())
					.addParameter("curPage", vo.getCurPage())
					.addParameter("searchKey", vo.getSearchKey())
					.addParameter("searchValue", vo.getSearchValue())
					.toString();
		}
		
		//강의내용 조회(cnts)
		ContentsVO contentsVO = new ContentsVO();
		contentsVO = contentsService.listContentsTree(vo.getSbjCd(), "");
		request.setAttribute("cntsList", contentsVO.getSubList());
		
		
		returnUrl="home/course/subject/view_online_subject";

		return returnUrl;
	}

	/**
	 * 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFirstContents")
	public String getFirstContents(OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ContentsVO contentsVO = new ContentsVO();
		contentsVO = contentsService.listContentsTree(vo.getSbjCd(), "");

		return JsonUtil.responseJson(response, contentsVO.getSubList().get(0));
	}
	
	/**
	 * 북마크 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSubContents")
	public String listSubContents(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		vo = bookmarkService.viewContents(vo).getReturnVO();
		ProcessResultListVO<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, vo.getUnitCd());

		return JsonUtil.responseJson(response, contentsList);
	}

	/**
	 * 콘텐츠 프레임 창
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewContents")
	public String viewContents(ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(vo.getSbjCd());
		onsbjVO = subjectService.viewOnline(onsbjVO).getReturnVO();
		request.setAttribute("subjectVO", onsbjVO);

		//-- 콘텐츠 정보를 가져온다.
		ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

		//-- 콘텐츠 정보를 Bookmark 정보와 병합.
		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("contentsVO", vo);

		if("OLCCNTS".equals(contentsVO.getCntsTypeCd())) { //-- OLC 콘텐츠의 경우
			//-- 카트리지 정보 가져오기
			//-- OLC공유 콘텐츠 정보 가져오기
			ClibShareOlcCntsVO csocVO = new ClibShareOlcCntsVO();
			csocVO.setOrgCd(orgCd);
			csocVO.setCntsCd(vo.getUnitFilePath());
			csocVO = clibShareOlcCntsService.view(csocVO).getReturnVO();
			request.setAttribute("clibOlcCntsVO", csocVO);

			//-- OLC공유 페이지 정보 가져오기
			ClibShareOlcPageVO cspcVO = new ClibShareOlcPageVO();
			cspcVO.setOrgCd(orgCd);
			cspcVO.setCntsCd(csocVO.getCntsCd());
			List<ClibShareOlcPageVO> resultList = clibShareOlcPageService.list(cspcVO).getReturnList();

			request.setAttribute("olcPageList", resultList);

			return "home/course/subject/olc_page_main_pop";
		} else if("VOD".equals(contentsVO.getCntsTypeCd())) { //-- 미디어 콘텐츠의 경우
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();

			request.setAttribute("playerDiv", clibShareMediaCntsVO.getPlayerDiv());

			String ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFileNm());
			String fileExt = "none";
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				fileExt = "mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				fileExt = "mp4";
			}
			request.setAttribute("filePath", "/contents"+clibShareMediaCntsVO.getFilePath());
			request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
			request.setAttribute("fileExt", fileExt);
			request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
			return "home/course/subject/media_contents_view_pop";

		} else if("CDN".equals(contentsVO.getCntsTypeCd())) { //-- CDN 콘텐츠의 경우
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();
			if(clibShareMediaCntsVO != null) {

				request.setAttribute("playerDiv", clibShareMediaCntsVO.getPlayerDiv());
				
				String ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFileNm());
				String filePath = "";
				if(clibShareMediaCntsVO.getCntsTypeCd().equals("CDN")) {
					ext = FileUtil.getFileExtention(clibShareMediaCntsVO.getFilePath());
					filePath = clibShareMediaCntsVO.getFilePath();
				}
	
				String fileExt = "none";
				if(Constants.MEDIA_FILE_MP3.contains(ext)) {
					fileExt = "mp3";
				} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
					fileExt = "mp4";
				}
				request.setAttribute("filePath", filePath);
				request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
				request.setAttribute("fileExt", fileExt);
				request.setAttribute("cntsTypeCd", clibShareMediaCntsVO.getCntsTypeCd());
				request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
				request.setAttribute("flowplayer", "Y");
			}
			request.setAttribute("orgInfoVO", orgInfoVO);
			
			return "home/course/subject/media_contents_view_pop";

		} else if("MLINK".equals(contentsVO.getCntsTypeCd())) { //-- 링크타입일 경우
			
			//-- 기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

			ClibShareMediaCntsVO clibShareMediaCntsVO = new ClibShareMediaCntsVO();
			clibShareMediaCntsVO.setOrgCd(orgCd);
			clibShareMediaCntsVO.setCntsCd(contentsVO.getUnitFilePath());

			//-- 미디어 콘텐츠 정보를 가져온다.
			clibShareMediaCntsVO = clibShareMediaCntsService.view(clibShareMediaCntsVO).getReturnVO();
			if(clibShareMediaCntsVO != null) {
				request.setAttribute("filePath", clibShareMediaCntsVO.getFilePath());
				request.setAttribute("fileName", clibShareMediaCntsVO.getFileNm());
			}
			request.setAttribute("orgInfoVO", orgInfoVO);
			
			return "home/course/subject/mlink_contents_view_pop";
			
		} else { //-- 로컬 콘텐츠의 경우
			contentsVO = contentsService.listContentsTree(vo.getSbjCd(), "");
			request.setAttribute("contentsList", contentsVO.getSubList());

			return "home/course/subject/local_contents_view_pop";
		}
	}
}
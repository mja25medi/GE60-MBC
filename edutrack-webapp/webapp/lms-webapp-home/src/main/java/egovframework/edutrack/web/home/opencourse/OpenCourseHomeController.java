package egovframework.edutrack.web.home.opencourse;

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
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgVO;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsVO;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsVO;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjService;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/openCourse")
public class OpenCourseHomeController extends GenericController{

	@Autowired @Qualifier("openCrsSbjService")
	private OpenCrsSbjService		openCrsSbjService;

	@Autowired @Qualifier("openCrsService")
	private OpenCrsService 		openCrsService;

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService		bookmarkService;

	@Autowired @Qualifier("subjectService")
	private SubjectService			subjectService;

	@Autowired @Qualifier("olcCartrgService")
	private OlcCartrgService 		olcCartrgService;

	@Autowired @Qualifier("olcCntsService")
	private OlcCntsService 		olcCntsService;

	@Autowired @Qualifier("contentsService")
	private ContentsService 		contentsService;

	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService 				sysMenuMemService;

	/**
	 * 공개과정 과정 목록 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 과정 목록
		ProcessResultListVO<OpenCrsVO> resultList = openCrsService.listCourse(vo);
		request.setAttribute("openCrsList", resultList.getReturnList());
		request.setAttribute("vo", vo);

		return "home/opencourse/course_main";
	}

	/**
	 * 공개강좌 과목별 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainCntsMain")
	public String mainCntsMain(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		OpenCrsSbjVO openCrsSbjVO = new OpenCrsSbjVO();
		openCrsSbjVO.setCrsCd(vo.getCrsCd());

		//과정에 속한 과목목록을 조회한다.
		ProcessResultListVO<OpenCrsSbjVO> resultList = openCrsSbjService.list(openCrsSbjVO);
		request.setAttribute("openCrsSbjList", resultList.getReturnList());
		request.setAttribute("vo", vo);

		return "home/opencourse/contents_main";
	}

	/**
	 * 공개강좌 과목의 학습과정 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCnts")
	public String listCnts(OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setCrsCd(vo.getCrsCd());
		bookmarkVO.setSbjCd(vo.getSbjCd());

		//과목 콘텐츠 목록을 조회한다.
		List<BookmarkVO> contentsList = bookmarkService.openCourseListBookmark(bookmarkVO).getReturnList();
		request.setAttribute("contentsList", contentsList);

		return "home/opencourse/contents_list_div";
	}

	/**
	 * 공개강좌 콘텐츠 프레임 창
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewCntsPop")
	public String viewCntsPop(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(vo.getSbjCd());
		onsbjVO = subjectService.viewOnline(onsbjVO).getReturnVO();
		request.setAttribute("subjectVO", onsbjVO);

		ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("vo", vo);

		if("Y".equals(vo.getOlcYn())) {
			//-- 카트리지 정보 가져오기
			OlcCartrgVO ocgVO = new OlcCartrgVO();
			ocgVO.setOrgCd(orgCd);
			ocgVO.setCartrgCd(vo.getUnitFilePath());
			ocgVO = olcCartrgService.view(ocgVO).getReturnVO();
			request.setAttribute("olcCartrgVO", ocgVO);

			//-- 콘텐츠 목록 가져오기
			OlcCntsVO ocVO = new OlcCntsVO();
			ocVO.setOrgCd(orgCd);
			ocVO.setCartrgCd(ocgVO.getCartrgCd());
			List<OlcCntsVO> resultList = olcCntsService.list(ocVO).getReturnList();
			request.setAttribute("olcContentsList", resultList);

			return "home/opencourse/olc_contents_main_pop";
		} else {

			//-- 메뉴 가져오기
			vo = bookmarkService.viewContents(vo).getReturnVO();
			String rootUnitCd = StringUtil.split(vo.getUnitPath(),">")[0];

			List<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, rootUnitCd).getReturnList();
			request.setAttribute("contentsList", contentsList);
			return "home/opencourse/contents_frame_pop";
		}
	}

	/**
	 * 콘텐츠 바디
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewCnts")
	public String viewCnts(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	
		ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("vo", vo);

		String returnUrl = "home/opencourse/contents_viewer_redirect";
		String ext = vo.getUnitFileExt();

		if(Constants.MEDIA_FILE_MP3.contains(ext)) {
			returnUrl = "home/opencourse/contents_viewer_mp3";
		} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
			returnUrl = "home/opencourse/contents_viewer_mp4";
		} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
			returnUrl = "home/opencourse/contents_viewer_mms";
		}
		return returnUrl;
	}

	/**
	 * Olc 콘텐츠 바디
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewOlc")
	public String viewOlc(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String cartrgCd = request.getParameter("cartrgCd");
		String cntsCd = request.getParameter("cntsCd");
		OlcCntsVO ocVO = new OlcCntsVO();
		ocVO.setOrgCd(orgCd);
		ocVO.setCartrgCd(cartrgCd);
		ocVO.setCntsCd(cntsCd);

		ocVO = olcCntsService.view(ocVO).getReturnVO();

		//-- 콘텐츠 목록 가져오기
//		List<OlcCntsVO> resultList = olcCntsService.list(ocVO).getReturnList();
//		int pageCount = resultList.size();
//		double ratio = 0;
//		//-- 콘텐츠 1개당 진도율을 구한다.
//		double pageRatio = Math.round((1 / pageCount * 100), 0 );
//		double gapRatio = 100 - (pageRatio * pageCount);
//		int lineCnt = 0;
//		for(OlcCntsVO socVO : resultList) {
//			if(ocVO.getCntsCd().equals(socVO.getCntsCd())) {
//				if(lineCnt == 0) {
//					ratio = pageRatio + gapRatio;
//				} else {
//					ratio = pageRatio;
//				}
//			}
//		}
//		request.setAttribute("pageRatio", ratio);

		String returnUrl = "home/opencourse/olc_contents_viewer_create_pop";
		if(!"create".equals(ocVO.getCntsDiv())) {
			String ext = FileUtil.getFileExtention(ocVO.getCntsCts());
			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				returnUrl = "home/opencourse/olc_contents_viewer_mp3_pop";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				returnUrl = "home/opencourse/olc_contents_viewer_mp4_pop";
			} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
				returnUrl = "home/pencourse/olc_contents_viewer_mms_pop";
			} else {
				returnUrl = "home/opencourse/olc_contents_viewer_html_pop";
			}
		}
		request.setAttribute("olcCntsVO", ocVO);
		return returnUrl;
	}

}

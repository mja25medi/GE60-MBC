
package egovframework.edutrack.web.lecture.contents;

import java.util.ArrayList;
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
import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.qna.service.BrdQnaService;
import egovframework.edutrack.modules.course.contents.service.ContentsFileVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.lecture.main.service.MainLectureService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrService;
import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsService;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;

/**
 * 학습교재 엑센 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/cnts")
public class ContentsLectureController
		extends GenericController {

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService			bookmarkService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService	createCourseSubjectService;

	@Autowired @Qualifier("subjectService")
	private SubjectService				subjectService;

	@Autowired @Qualifier("contentsService")
	private ContentsService			contentsService;

	@Autowired @Qualifier("eduResultService")
	private EduResultService			eduResultService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;

	@Autowired @Qualifier("studentService")
	private StudentService				studentService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService				codeMemService;

	@Autowired @Qualifier("courseService")
	private CourseService				courseService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService				configService;

	@Autowired @Qualifier("olcCartrgService")
	private OlcCartrgService 			olcCartrgService;

	@Autowired @Qualifier("olcCntsService")
	private OlcCntsService 			olcCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgInfoService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 		clibShareMediaCntsService;

	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;

	@Autowired @Qualifier("clibShareOlcPageService")
	private ClibShareOlcPageService 		clibShareOlcPageService;

	@Autowired @Qualifier("clibMediaCntsService")
	private ClibMediaCntsService 			clibMediaCntsService;
	
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;
	
	@Autowired @Qualifier("mainLectureService")
	private MainLectureService			mainLectureService;
	
	@Autowired @Qualifier("brdQnaService")
	private BrdQnaService brdQnaService;
	
	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	createCourseTeacherService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgOrgInfoService;
	
	@Autowired @Qualifier("assignmentService")
	private AssignmentService 			assignmentService;
	
	@Autowired @Qualifier("clibCntsCtgrService")
	private ClibCntsCtgrService 			clibCntsCtgrService;
	
	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService 	 orgCodeService;
	
	/**
	 * 교재 정보 일괄 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCreateContentsList")
	public String editContentsList( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		
		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}
		//교재 정보 업데이트
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		for(int i=0; i<vo.getSubList().size(); i++) {
			if(!vo.getSubList().get(i).getClassStartTime().equals("")) {
				String classStartDttm = StringUtil.ReplaceAll(vo.getSubList().get(i).getClassStartTime(),"-","").replaceAll(":", "")+"01";
				vo.getSubList().get(i).setClassStartTime(classStartDttm);
			}
			if(!vo.getSubList().get(i).getClassEndTime().equals("")) {
				String classEndDttm = StringUtil.ReplaceAll(vo.getSubList().get(i).getClassEndTime(),"-","").replaceAll(":", "")+"59";
				vo.getSubList().get(i).setClassEndTime(classEndDttm);
			}
			resultVO = contentsService.editCreateContents(vo.getSubList().get(i));
		}
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교재 컨텐츠 수정 팝업(컨텐츠)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormContentsPop")
	public String editFormContentsPop( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String mediaPlayer = Constants.MEDIAPLAYER;
		request.setAttribute("mediaPlayer", mediaPlayer);
		
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String unitCd = StringUtil.nvl(request.getParameter("unitCd"));
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"));
		ProcessResultVO<ContentsVO> resultVO = new ProcessResultVO<ContentsVO>();
		resultVO = contentsService.viewCreateContents(crsCreCd, sbjCd, unitCd);
		
		CreateOnlineSubjectVO cosVO = new CreateOnlineSubjectVO();
		cosVO.setSbjCd(sbjCd);
		cosVO.setCrsCreCd(crsCreCd);
		request.setAttribute("createOnlineSubjectVO", createCourseSubjectService.viewOnlineSubject(cosVO).getReturnVO());
		
		request.setAttribute("vo", resultVO.getReturnVO());
		request.setAttribute("jstree", "Y");	
		
		request.setAttribute("xrcloud_api_key", Constants.XRCLOUD_API_KEY);	
		request.setAttribute("xrcloud_project_id", Constants.XRCLOUD_PROJECT_ID);	
		request.setAttribute("product_host_url", Constants.PRODUCT_HOST_URL);	
		
		return "home/lecture/contents/cre_contents_edit_pop";
	}
	
	/**
	 * 교재 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addContents")
	public String addContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}

		ProcessResultVO<ContentsVO> resultVO = contentsService.addContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교재 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCreateContents")
	public String editCreateContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}

		ProcessResultVO<ContentsVO> resultVO = contentsService.editCreateContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교재 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCreateContents")
	public String deleteContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ContentsVO> resultVO = contentsService.deleteCreateContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 콘텐츠 관리 왼쪽 영역 호출 클래스
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/leftWorkArea")
	public String leftWorkArea( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String workType = StringUtil.nvl(request.getParameter("workType"),"file");
		String orgCd = UserBroker.getUserOrgCd(request);
		String crsCreCd = request.getParameter("crsCreCd");
		String sbjType = request.getParameter("sbjType");
		
		String forwardUrl = "home/lecture/contents/left_file_tree_div";
		if("othersbj".equals(workType)) {
			forwardUrl = "home/lecture/contents/left_other_sbj_div";
		} else if ("olccnts".equals(workType)) {
			ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
			ccscVO.setOrgCd(orgCd);
			ccscVO.setParCtgrCd("");
			ccscVO.setDivCd("CNTS");

			List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
			request.setAttribute("ctgrList", listCtgr);

			forwardUrl = "home/lecture/contents/left_olc_cnts_div";
		} else if ("VOD".equals(workType) || "CDN".equals(workType) || "MLINK".equals(workType)) {
			//-- 공유 분류 를 가져온다.
			ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
			ccscVO.setOrgCd(orgCd);
			ccscVO.setParCtgrCd("");
			ccscVO.setDivCd("CNTS");

			List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
			request.setAttribute("ctgrList", listCtgr);
			request.setAttribute("sbjType", sbjType);

			// 교육기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);
			request.setAttribute("kollusUseYn", orgInfoVO.getKollusUseYn());
			request.setAttribute("cntsTypeCd", workType);

			forwardUrl = "home/lecture/contents/left_media_cnts_div";
		} else if ("CODING_T".equals(workType)) {
			// 교육기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);
			request.setAttribute("cntsTypeCd", workType);
			request.setAttribute("crsCreCd", crsCreCd);

			forwardUrl = "home/lecture/contents/left_code_asmt_div";
		}   
		return forwardUrl;
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
		return "home/lecture/library/share/olc/tch_preview_main_pop";
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
		return "home/lecture/library/share/media/tch_media_cnts_preview_pop";
	}
	
	/**
	 * 공개강좌 콘텐츠 프레임 창
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewCntsMain")
	public String viewCntsMain( BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(vo.getSbjCd());
		onsbjVO = subjectService.viewOnline(onsbjVO).getReturnVO();
		request.setAttribute("subjectVO", onsbjVO);

		ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("bookmarkVO", vo);
		if("OLCCNTS".equals(contentsVO.getCntsTypeCd())) { //-- OLC 콘텐츠의 경우
						//return mapping.findForward("olc_contents_main");
			// 읽기 화면으로
			return "redirect:"+(
					new URLBuilder("/lec/cnts/previewOlcMain")
						.addParameter("clibShareOlcCntsVO.cntsCd", vo.getUnitFilePath())
						.toString()
					);
		} else if("MEDIA".equals(contentsVO.getCntsTypeCd())){
			return "redirect:"+(
					new URLBuilder("/lec/cnts/previewMedia")
						.addParameter("clibShareMediaCntsVO.cntsCd", vo.getUnitFilePath())
						.toString()
					);
		} else {
				//-- 메뉴 가져오기
			vo = bookmarkService.viewContents(vo).getReturnVO();
			String rootUnitCd = StringUtil.split(vo.getUnitPath(),">")[0];

			List<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, rootUnitCd).getReturnList();
			request.setAttribute("contentsList", contentsList);

			return "home/lecture/contents/contents_frame_pop";
		}
	}
	
	/**
	 * 디렉토리 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listFile")
	public String listFile( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String 	sbjCd = StringUtil.nvl(request.getParameter("sbjCd"),"");
		String 	filePath = StringUtil.nvl(request.getParameter("filePath"),"");
		String	id = StringUtil.nvl(request.getParameter("id"),"");
		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 최상외 접속시 리턴
		if("".equals(filePath)) {
			JsTreeVO treeVO = new JsTreeVO();
			treeVO.setData(sbjCd);
			treeVO.setState(1);
			treeVO.addAttr("id", "00");
			treeVO.addAttr("rel","drive");
			treeVO.addAttr("fileDir", sbjCd);
			treeVO.addAttr("fileType", "R");
			treeVO.addAttr("fileName", sbjCd);

			return JsonUtil.responseJson(response, treeVO);
		} else {
			try {
				List<ContentsFileVO> fileList = contentsService.listFile(filePath, id, orgCd).getReturnList();
				List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
				for(ContentsFileVO VO : fileList) {
					JsTreeVO treeVO = new JsTreeVO();
					String rel = "";
					int subCnt = 0;
					String fileExt = "";
					if("D".equals(VO.getFileType())) {
						rel = "folder";
						subCnt = 1;
					} else {
						fileExt = FileUtil.getFileExtention(VO.getFileName());
						if(".html".equals(fileExt) || ".htm".equals(fileExt)) rel = "html";
						else if(".mp4".equals(fileExt) || ".mp3".equals(fileExt) || ".wma".equals(fileExt) || ".wmv".equals(fileExt)) rel = "media";
						else if(".zip".equals(fileExt)) rel = "zip";
						else rel = "file";
					}
					treeVO.setData(VO.getFileName());
					treeVO.setState(subCnt);
					treeVO.addAttr("id", VO.getFileId());
					treeVO.addAttr("rel",rel);
					treeVO.addAttr(VO);
					treeList.add(treeVO);
				}
				return JsonUtil.responseJson(response, treeList);
			}catch (Exception e) {
				return JsonUtil.responseJson(response, null);
			}
		}

	}
	
	/**
	 * 파일 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormFilePop")
	public String addFormFilePop( Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response){
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileId = StringUtil.nvl(request.getParameter("fileId"));
		request.setAttribute("sbjCd", sbjCd);
		request.setAttribute("filePath", filePath);
		request.setAttribute("fileId", fileId);
		request.setAttribute("fileupload", "Y");

		return "home/lecture/contents/file_upload_pop";
	}
	
	
	/**
	 * 폴더 생성 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormDirPop")
	public String addFormDirPop(ContentsFileVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("vo", vo);

		return "home/lecture/contents/dir_write_pop";
	}

	/**
	 * 폴더 생성
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addDir")
	public String addDir( ContentsFileVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = contentsService.addDir(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.create.folder.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.create.folder.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 파일, 폴더 명 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/renameFormFilePop")
	public String renameFormFilePop( ContentsFileVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);

		vo.setOrgCd(orgCd);

		//2014.11.17 파일과 폴더의 이름바꾸기 기준이 틀려 구분함.
		if(StringUtil.isNull(vo.getFileName())){
			vo.setGubun("FOLDER");
		}else{
			vo.setGubun("FILE");
		}
		request.setAttribute("vo", vo);

		return "home/lecture/contents/file_rename_pop";
	}
	
	/**
	 * 파일, 디렉토리 삭제
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteFile")
	public String deleteFile(  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String fileDir = StringUtil.nvl(request.getParameter("fileDir"));
		String orgCd = UserBroker.getUserOrgCd(request);

		ProcessResultVO<?> resultVO = contentsService.deleteFile(sbjCd, fileDir, orgCd);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.file.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.file.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 압축 파일 풀기
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/unzipFile")
	public String unzipFile( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String fileDir = StringUtil.nvl(request.getParameter("fileDir"));
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String orgCd = UserBroker.getUserOrgCd(request);

		ProcessResultVO<?> resultVO = contentsService.unzipFile(sbjCd, fileDir, fileName, orgCd);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.unzip.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.unzip.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 파일 붙여넣기
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pasteFile")
	public String pasteFile( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String tarFileDir = StringUtil.nvl(request.getParameter("tarFileDir"));
		String cutFileDir = StringUtil.nvl(request.getParameter("cutFileDir"));
		String cutFileName = StringUtil.nvl(request.getParameter("cutFileName"));
		String cutType =  StringUtil.nvl(request.getParameter("cutType"));
		String orgCd = UserBroker.getUserOrgCd(request);

		ProcessResultVO<?> resultVO = contentsService.pasteFile(sbjCd, tarFileDir, cutFileDir, cutFileName, cutType, orgCd);
		if(resultVO.getRetrunCnt() != null && resultVO.getRetrunCnt().length > 0){
			resultVO.setMessage(getMessage(request, "course.message.contents.copy.file.dup"));
		}else{
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "course.message.contents.copy.file.success"));
			} else {
				resultVO.setMessage(getMessage(request, "course.message.contents.copy.file.failed"));
			}
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 과목 검색 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listSearchCourseForm")
	public String listSearchCourseForm(OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response){

		String sbjNm = StringUtil.nvl(request.getParameter("sbjNm"));
		request.setAttribute("sbjNm", sbjNm);
		request.setAttribute("vo", vo);

		return "home/lecture/contents/course_search_pop";
	}
	
	/**
	 * 교재 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listContentsJsonList")
	public String listContentsJsonList( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String id = StringUtil.nvl(request.getParameter("id"));

		//-- 최상외 접속시 리턴
		if("".equals(id)) {
			//-- 과목명을 가져온다.
			OnlineSubjectVO osVO = new OnlineSubjectVO();
			osVO.setSbjCd(sbjCd);
			osVO = subjectService.viewOnline(osVO).getReturnVO();

			JsTreeVO treeVO = new JsTreeVO();
			treeVO.setData(osVO.getSbjNm());
			treeVO.setState(1);
			treeVO.addAttr("id", "CNTSROOT");
			treeVO.addAttr("rel","root");
			treeVO.addAttr(new ContentsVO());

			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals("CNTSROOT")) id = "";
			List<ContentsVO> contentsList = contentsService.listContentsList(sbjCd, id).getReturnList();
			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			for(ContentsVO VO : contentsList) {
				JsTreeVO treeVO = new JsTreeVO();
				String quiz = "";
				String rel = "lesson";
				int subCnt = 0;
				if(VO.getQuizCnt() > 0 ) quiz = " [Quiz]";
				if("C".equals(VO.getUnitType())) {
					rel = "chepter";
					subCnt = 1;
				}
				treeVO.setData(VO.getUnitNm()+quiz);
				treeVO.setState(subCnt);
				treeVO.addAttr("id", VO.getUnitCd());
				treeVO.addAttr("rel",rel);
				treeVO.addAttr(VO);
				treeList.add(treeVO);
			}
			return JsonUtil.responseJson(response, treeList);
		}
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
		return "home/lecture/library/share/olc_cnts_list_for_cnts_mgr_div";
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
		return"home/lecture/library/share/media_cnts_list_for_cnts_mgr_div";
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

			String returnUrl = "home/lecture/library/share/olc/olc_page_view_create_pop";
			String orgCd = UserBroker.getUserOrgCd(request);
			vo.setOrgCd(orgCd);

			vo = clibShareOlcPageService.view(vo).getReturnVO();

			vo.setPageCts(vo.getPageCts().replaceAll("ClibMediaCntsHome", "ClibShareCntsManage"));

			request.setAttribute("clibShareOlcPageVO", vo);
			return returnUrl;
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
		public String viewCnts( BookmarkVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

			BeanUtils.copyProperties(contentsVO, vo);
			request.setAttribute("bookmarkVO", vo);
			String foward = "home/lecture/contents/contents_viewer_redirect";
			String ext = vo.getUnitFileExt();

			if(Constants.MEDIA_FILE_MP3.contains(ext)) {
				foward = "home/lecture/contents/contents_viewer_mp3";
			} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
				foward = "home/lecture/contents/contents_viewer_mp4";
			} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
				foward = "contents_viewer_mms";
			}
			return foward;
		}
		
		/**
		 * 과목 목록 조회(검색)
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/listSearchSubject")
		public String listSearchSubject( Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			String orgCd = UserBroker.getUserOrgCd(request);
			String sbjNm = StringUtil.nvl(request.getParameter("sbjNm"));
			OnlineSubjectVO osVO = new OnlineSubjectVO();
			osVO.setSbjNm(sbjNm);
			osVO.setOrgCd(orgCd);

			List<OnlineSubjectVO> subjectList = subjectService.listOnline(osVO).getReturnList();
			request.setAttribute("subjectList", subjectList);
			return "home/lecture/contents/course_search_list_div";
		}
		
		/**
		 * 파일, 폴더명 변경
		 *
		 * @return  ProcessResultVO
		 * @throws Exception  
		 */
		@RequestMapping(value="/renameFile")
		public String renameFile( ContentsFileVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			String orgCd = UserBroker.getUserOrgCd(request);
			vo.setOrgCd(orgCd);

			ProcessResultVO<?> resultVO = contentsService.renameFile(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "course.message.contents.rename.file.success"));
			} else {
				resultVO.setMessage(getMessage(request, "course.message.contents.rename.file.failed"));
			}
			return JsonUtil.responseJson(response, resultVO);
		}
		
		/**
		 * 과제 목록 조회(카운트) 
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/listAsmt")
		public String listAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			ProcessResultListVO<AssignmentVO> resultList = assignmentService.listAssignmentCount(vo);
			request.setAttribute("assignmentListVO", resultList.getReturnList());
			return "home/lecture/assignment/teacher/code_asmt_list_div";
		}
		
		/**
		 * 단일 목차 등록
		 *
		 * @return  ProcessResultVO
		 */
		@RequestMapping(value="/addCreateContents")
		public String addCreateContents( ContentsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request,	HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			if("Y".equals(vo.getOlcYn())) {
				vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
			}

			ProcessResultVO<ContentsVO> resultVO = contentsService.addCreateContents(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "course.message.contents.add.success"));
			} else {
				resultVO.setMessage(getMessage(request, "course.message.contents.add.failed"));
			}
			return JsonUtil.responseJson(response, resultVO);
		}
		
		
		/**
		 * 미디어콘텐츠관리 팝업
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/shareContentsManagePop")
		public String shareContentsManagePop( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);

			ClibMediaCntsVO cvo = new ClibMediaCntsVO();
			vo.setOrgCd(orgCd);
			vo.setUserNo(userNo);		//본인이 등록한 내용만 확인 가능

			ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listPageing(vo);

			request.setAttribute("clibMediaCntsList", resultList.getReturnList());
			request.setAttribute("paging", "Y");
			request.setAttribute("pageInfo", resultList.getPageInfo());
			request.setAttribute("vo", vo);
			return "home/lecture/contents/cre_share_contents_pop";
		}
		
		/**
		 * 미디어콘텐츠관리 목록
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/shareContentsList")
		public String shareContentsList( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);

			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);
			vo.setOrgCd(orgCd);
			vo.setUserNo(userNo);		// 본인이 등록한 내용만 보이도록 작업

			ProcessResultListVO<ClibMediaCntsVO> resultList = clibMediaCntsService.listPageing(vo);

			request.setAttribute("clibMediaCntsList", resultList.getReturnList());
			request.setAttribute("pageInfo", resultList.getPageInfo());
			return "home/lecture/contents/cre_share_contents_list";
		}
		
		/**
		 * 미디어콘텐츠관리 등록 팝업
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/addShareContentsPop")
		public String addShareContentsPop( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);
			
			ClibCntsCtgrVO ctvo = new ClibCntsCtgrVO();
			ctvo.setCtgrCd(vo.getCtgrCd()); 	
			ctvo.setOrgCd(orgCd);
			
			ProcessResultVO<ClibCntsCtgrVO> resultVO = clibCntsCtgrService.view(ctvo);
			if(resultVO.getReturnVO() == null) {
				//등록된 분류 정보가 없으면 등록
				ctvo.setParCtgrCd("");	//강사가 등록하는건 무조건 최상위로 등록
				ctvo.setUserNo(userNo);
				ctvo.setCtgrNm(vo.getCtgrNm());
				try {
					clibCntsCtgrService.addWithCrsCreCd(ctvo);
				} catch (Exception ex) {
					setAlertMessage(request, (getMessage(request, "library.message.contents.category.add.failed")));
				}
			}

			vo.setOrgCd(orgCd);
			vo.setUseYn("Y"); //-- 초기 사용으로 셋팅
			vo.setUldStsCd("NO"); //-- 초기 사용으로 셋팅

			// 경로 가져오기
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);

			request.setAttribute("orgInfoVO", orgInfoVO);
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);
			return "home/lecture/contents/cre_share_contents_add_pop";
		}
		

		/**
		 * 미디어 콘텐츠 등록
		 *
		 * @return  ProcessResultVO
		 */
		@RequestMapping(value="/addShareContents")
		public String addShareContents( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request,	HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);

			vo.setOrgCd(orgCd);
			vo.setUserNo(userNo);
			vo.setUseYn("Y");
			
			//콘텐츠 등록
			if(ValidationUtils.isNotEmpty(vo.getUldFileKey())) {
				vo.setUldStsCd("upload");
			}
			
			if ("VOD".equals(vo.getCntsType())) {
				//-- 등록시 파일 경로에 ORG_CD를 붙여서 저장이 되도록 수정함. 
				//vo.setFilePath("/"+orgCd+vo.getFilePath());
				String filePath = vo.getFilePath();
				if(Character.toString(filePath.charAt(filePath.length() - 1)).equals("/")) {
					filePath = filePath.substring(0, filePath.length()-1);
				}
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
			
			//공유 분류 등록
			ClibShareCntsCtgrVO shareCtgrVo = new ClibShareCntsCtgrVO();
			shareCtgrVo.setCtgrCd(vo.getCtgrCd()); 
			shareCtgrVo.setOrgCd(orgCd);
			
			ProcessResultVO<ClibShareCntsCtgrVO> shareCtgrReturnVO = clibShareCntsCtgrService.view(shareCtgrVo);
			if(shareCtgrReturnVO.getReturnVO() == null) {
				//등록된 분류 정보가 없으면 등록
				shareCtgrVo.setParCtgrCd("");
				shareCtgrVo.setUseYn("Y");
				shareCtgrVo.setRegNo(userNo);
				shareCtgrVo.setModNo(userNo);
				shareCtgrVo.setDivCd("CNTS");
				shareCtgrVo.setCtgrNm(vo.getCtgrNm());
				
				ClibShareCntsCtgrVO shareCtgrVO = clibShareCntsCtgrService.addWithCrsCreCd(shareCtgrVo).getReturnVO();
				if(shareCtgrVO == null) {
					resultVO.setMessage(getMessage(request, "library.message.manage.share.category.add.failed"));
				} 
			}
			
			//콘텐츠 공유 자동으로 되도록 작업
			//CCL코드
			List<OrgCodeVO> cclCode = orgCodeService.listCode(orgCd,"CCL_CD").getReturnList();
			vo.setCclCd(cclCode.get(0).getCodeCd());	//CCL코드 첫번째 것으로 저장
			vo.setShareDivCd("CNTS");	//공유구분 : 콘텐츠공유
			vo.setShareStsCd("03"); //강사 공유시 바로 공유승인
			vo.setCtgrCd(shareCtgrVo.getCtgrCd());
			String[] shareCd = new String[1];
			shareCd[0] = "CNTS";
			
			ProcessResultVO<ClibMediaCntsVO> shareResultVO = clibMediaCntsService.addShare(vo, shareCd);
			if(shareResultVO.getResult() < 0) {
				resultVO.setMessage(getMessage(request, "library.message.contents.share.failed"));
			} 
			return JsonUtil.responseJson(response, resultVO);
		}
		
		/**
		 * 미디어콘텐츠관리 (수정)
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/editShareContentsPop")
		public String editShareContentsPop( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);

			vo.setOrgCd(orgCd);
			vo.setUserNo(userNo);

			vo = clibMediaCntsService.view(vo).getReturnVO();
			
			// 경로 가져오기
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);

			request.setAttribute("orgInfoVO", orgInfoVO);
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);
			return "home/lecture/contents/cre_share_contents_add_pop";
		}
		
		/**
		 * 미디어 콘텐츠 수정
		 *
		 * @return  ProcessResultVO
		 */
		@RequestMapping(value="/editShareContents")
		public String editShareContents( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request,	HttpServletResponse response) throws Exception {
			commonVOProcessing(vo, request);
			
			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);

			vo.setOrgCd(orgCd);
			vo.setUserNo(userNo);
			
			ClibMediaCntsVO cmcVO = clibMediaCntsService.view(vo).getReturnVO();
			//-- 기존 정보로 덮어 쓰기
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
		@RequestMapping(value="/removeShareContents")
		public String remove( ClibMediaCntsVO vo, Map commandMap, ModelMap model,
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
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);

			int error = 0;
			if(vo.getSharedCnt() == 0) {
				//-- 공유가 안되어 있을 경우만 파일 삭제, 공유가 되어 잇는 경우는 파일은 삭제 하지 않음
				if("common".equals(vo.getPlayerDiv())) {
					try {
						//-- 일반 파일일 경우 파일 및 폴더 삭제
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

			String currentDate = DateTimeUtil.getCurrentString();
			String filePath = "/mediacnts/"+userNo+"/"+currentDate;
			request.setAttribute("filePath", filePath);

			request.setAttribute("vo", vo);
			request.setAttribute("fileupload", "Y");
			
			
			return "home/lecture/contents/common_upload_pop";
		}
		

		

}
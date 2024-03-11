package egovframework.edutrack.web.manage.course;

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
import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsFileVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrService;
import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageService;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/contents")
public class CourseContentsManageController extends GenericController {

	@Autowired @Qualifier("contentsService")
	private ContentsService				contentsService;

	@Autowired @Qualifier("subjectService")
	private SubjectService 				subjectService;

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService				bookmarkService;

	@Autowired @Qualifier("clibShareCntsCtgrService")
	private ClibShareCntsCtgrService 		clibShareCntsCtgrService;

	@Autowired @Qualifier("olcCartrgService")
	private OlcCartrgService 				olcCartrgService;

	@Autowired @Qualifier("olcCntsService")
	private OlcCntsService 				olcCntsService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 				orgOrgInfoService;

	@Autowired @Qualifier("clibShareMediaCntsService")
	private ClibShareMediaCntsService 		clibShareMediaCntsService;

	@Autowired @Qualifier("clibShareOlcCntsService")
	private ClibShareOlcCntsService 		clibShareOlcCntsService;

	@Autowired @Qualifier("clibShareOlcPageService")
	private ClibShareOlcPageService 		clibShareOlcPageService;
	
	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService createCourseSubjectService;
	
	@Autowired @Qualifier("assignmentService")
	private AssignmentService 			assignmentService;

	/**
	 * 교재 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/contentsMain")
	public String mainContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String mediaPlayer = Constants.MEDIAPLAYER;
		request.setAttribute("mediaPlayer", mediaPlayer);

		OnlineSubjectVO osVO = new OnlineSubjectVO();
		osVO.setSbjCd(vo.getSbjCd());
		osVO = subjectService.viewOnline(osVO).getReturnVO();
		request.setAttribute("osVO", osVO);
		request.setAttribute("vo", vo);
		request.setAttribute("jstree", "Y");	
		
		request.setAttribute("xrcloud_api_key", Constants.XRCLOUD_API_KEY);	
		request.setAttribute("xrcloud_project_id", Constants.XRCLOUD_PROJECT_ID);	
		request.setAttribute("product_host_url", Constants.PRODUCT_HOST_URL);	
		
		return "mng/course/contents/contents_main";
	}

	/**
	 * 교재 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listContents")
	public String listContents( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String parUnitCd = StringUtil.nvl(request.getParameter("parUnitCd"));
		return JsonUtil
			.responseJson(response, contentsService.listContentsTree(sbjCd, parUnitCd));
	}
	/**
	 * 회차별 교재 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listContentsJson")
	public String listContentsJson( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ProcessResultListVO<ContentsVO> result = contentsService.listContents(vo);
		request.setAttribute("contentsList", result.getReturnList());
		
		return "mng/course/contents/contents_list_div";
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
	 * 교재 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormContents")
	public String editFormContents( Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String unitCd = StringUtil.nvl(request.getParameter("unitCd"));

		return JsonUtil
			.responseJson(response, contentsService.viewContents(sbjCd, unitCd));
	}


	/**
	 * 교재 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editContents")
	public String editContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("Y".equals(vo.getOlcYn())) {
			vo.setPrgrChkType("PAGE"); // OLC 콘텐츠의 경우 무조건 PAGE 체크로 변경한다.
		}

		ProcessResultVO<ContentsVO> resultVO = contentsService.editContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage("차시를 저장하였습니다.");
		} else {
			resultVO.setMessage("차시를 저장하지 못하였습니다.");
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 교재 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteContents")
	public String deleteContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ContentsVO> resultVO = contentsService.deleteContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 교재 순차 조정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/moveContents")
	public String moveContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = contentsService.moveContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 교재 순차 조정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sortContents")
	public String sortContents( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = contentsService.sortContents(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 다른 과목 교제 복사
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/copyOtherContents")
	public String copyOtherContents(  Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		String sourceSbjCd = StringUtil.nvl(request.getParameter("sourceSbjCd"));
		String sourceUnitCd = StringUtil.nvl(request.getParameter("sourceUnitCd"));
		String targetSbjCd = StringUtil.nvl(request.getParameter("targetSbjCd"));
		String targetUnitCd = StringUtil.nvl(request.getParameter("targetUnitCd"));
		String targetUnitLvl = StringUtil.nvl(request.getParameter("targetUnitLvl"),"0");

		ContentsVO sourceContentsVO = new ContentsVO();
		ContentsVO targetContentsVO = new ContentsVO();

		sourceContentsVO.setSbjCd(sourceSbjCd);
		sourceContentsVO.setUnitCd(sourceUnitCd);
		targetContentsVO.setSbjCd(targetSbjCd);
		targetContentsVO.setUnitCd(targetUnitCd);
		targetContentsVO.setUnitLvl(Integer.parseInt(targetUnitLvl));

		ProcessResultVO<?> resultVO = contentsService.copyOtherContents(sourceContentsVO, targetContentsVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.contents.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.contents.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 디렉토리 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listDir")
	public String listDir( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String orgCd = UserBroker.getUserOrgCd(request);
		return JsonUtil
			.responseJson(response, contentsService.listDir(sbjCd,orgCd));
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

		return "mng/course/contents/file_upload_pop";
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

		return "mng/course/contents/file_rename_pop";
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

		return "mng/course/contents/dir_write_pop";
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

		return "mng/course/contents/course_search_pop";
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
		return "mng/course/contents/course_search_list_div";
	}

	/**
	 * 콘텐츠 미리 보기
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewContents")
	public String viewContents( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String unitCd = StringUtil.nvl(request.getParameter("unitCd"));

		//-- select subject info
		OnlineSubjectVO onlineSubjectVO = new OnlineSubjectVO();
		onlineSubjectVO.setSbjCd(sbjCd);
		onlineSubjectVO = subjectService.viewOnline(onlineSubjectVO).getReturnVO();
		request.setAttribute("onlineSubjectVO", onlineSubjectVO);

		//-- select contents info
		ContentsVO contentsVO = contentsService.viewContents(sbjCd, unitCd).getReturnVO();
		request.setAttribute("contentsVO", contentsVO);

		return "mng/course/contents/contents_preview_pop";
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
					new URLBuilder("/mng/library/clibShareCnts/previewOlcMain")
						.addParameter("clibShareOlcCntsVO.cntsCd", vo.getUnitFilePath())
						.toString()
					);
		} else if("MEDIA".equals(contentsVO.getCntsTypeCd())){
			return "redirect:"+(
					new URLBuilder("/mng/library/clibShareCnts/previewMedia")
						.addParameter("clibShareMediaCntsVO.cntsCd", vo.getUnitFilePath())
						.toString()
					);
		} else {
				//-- 메뉴 가져오기
			vo = bookmarkService.viewContents(vo).getReturnVO();
			String rootUnitCd = StringUtil.split(vo.getUnitPath(),">")[0];

			List<BookmarkVO> contentsList = bookmarkService.listBookmarkSub(vo, rootUnitCd).getReturnList();
			request.setAttribute("contentsList", contentsList);

			return "mng/course/contents/contents_frame_pop";
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
	public String viewCnts( BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ContentsVO contentsVO = contentsService.viewContents(vo.getSbjCd(), vo.getUnitCd()).getReturnVO();

		BeanUtils.copyProperties(contentsVO, vo);
		request.setAttribute("bookmarkVO", vo);
		String foward = "mng/course/contents/contents_viewer_redirect";
		String ext = vo.getUnitFileExt();

		if(Constants.MEDIA_FILE_MP3.contains(ext)) {
			foward = "mng/course/contents/contents_viewer_mp3";
		} else if(Constants.MEDIA_FILE_MP4.contains(ext)) {
			foward = "mng/course/contents/contents_viewer_mp4";
		} else if(Constants.MEDIA_FILE_MMS.contains(ext)) {
			foward = "contents_viewer_mms";
		}
		return foward;
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
		
		String forwardUrl = "mng/course/contents/left_file_tree_div";
		if("othersbj".equals(workType)) {
			forwardUrl = "mng/course/contents/left_other_sbj_div";
		} else if ("olccnts".equals(workType)) {
			ClibShareCntsCtgrVO ccscVO = new ClibShareCntsCtgrVO();
			ccscVO.setOrgCd(orgCd);
			ccscVO.setParCtgrCd("");
			ccscVO.setDivCd("CNTS");

			List<ClibShareCntsCtgrVO> listCtgr = clibShareCntsCtgrService.list(ccscVO).getReturnList();
			request.setAttribute("ctgrList", listCtgr);

			forwardUrl = "mng/course/contents/left_olc_cnts_div";
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

			forwardUrl = "mng/course/contents/left_media_cnts_div";
		}  else if ("CODING_T".equals(workType)) {
			// 교육기관 정보를 가져온다.
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);
			request.setAttribute("cntsTypeCd", workType);
			request.setAttribute("crsCreCd", crsCreCd);

			forwardUrl = "mng/course/contents/left_code_asmt_div";
		}  
		
		return forwardUrl;
	}

	/**
	 * 컨텐츠 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortFormCntsPop")
	public String sortFormCntsPop( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//---- 과목 분류 목록 조회
		ProcessResultListVO<ContentsVO> resultListVO = contentsService.listContentsSort(vo);
		request.setAttribute("cntsList", resultListVO.getReturnList());
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/course/contents/cnts_sort_pop";
	}

	/**
	 * 하위 컨텐츠 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCntsSub")
	public String listCntsSub( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<ContentsVO> resultVO = contentsService.listContentsSub(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 컨텐츠 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortCnts")
	public String sortCnts( ContentsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = contentsService.contentsSort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.category.sort.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.subject.category.sort.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 과목 목록 조회(검색)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listContentsForCrm")
	public String listContentsForCrm(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<BookmarkVO> contentsList = bookmarkService.listBookmark(vo).getReturnList();
		request.setAttribute("contentsList", contentsList);
		
		return "mng/student/student/list_contents_for_crm";
	}
	
	/**
	 * 과제 목록 조회(카운트) 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCntAsmt")
	public String listCntAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<AssignmentVO> resultList = assignmentService.listAssignmentCount(vo);
		request.setAttribute("assignmentListVO", resultList.getReturnList());
		return "mng/lecture/assignment/code_asmt_list_div";
	}
}

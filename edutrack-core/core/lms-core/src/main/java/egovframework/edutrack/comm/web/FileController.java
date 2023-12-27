package egovframework.edutrack.comm.web;

import java.io.File;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;


/**
 * 파일에 대한 접근을 담당하는 컨트롤러.
 * @author SungKook
 */
@Controller
@SessionAttributes("file")
public class FileController {


	private static final String	PROCESS_RESULT				= "result";
	private static final String	PROCESS_RESULT_MESSAGE		= "result_message";
	private static final String	PROCESS_RESULT_FAILED		= "failed";
	private static final String	PROCESS_RESULT_SUCCESS		= "success";

	private static final String	NO_OWNER					= "NO_OWNER";

	private static final int	FILE_NOT_FOUND_IN_PERSIST	= -1;

	private static final String	FILE_DOWNLOAD_VIEW			= "fileDownloadView";

	private static final String	FILE_MIMETYPE_VIEW			= "fileMimetypeView";

	private final Log			logger						= LogFactory.getLog(getClass());

	@Autowired
	private SysFileService service;

	@RequestMapping("/file/ajaxupload")
	@ResponseBody
	public void ajaxUpload(
			@RequestParam("Filedata") MultipartFile multipartFile,
			@RequestParam(value="repository", defaultValue="GENERAL", required=false) String repository,
			@RequestParam(value="organization", defaultValue="", required=false) String organization,
			@RequestParam(value="type", defaultValue="file", required=false) String type,
			Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Throwable {

//		if(StringUtil.isNull(UserBroker.getUserNo(request))) {
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요한 요청입니다.");
//			return;
//		}

		SysFileVO sysFileVO = null;

		if (multipartFile.getSize() == 0) {
			logger.debug(">>>>> 첨부파일이 없는 요청");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "첨부파일이 누락된 요청입니다.");
			return;
		}

		if (isBlockedFileExtention(multipartFile.getOriginalFilename())) {
			logger.debug(">>>>> 허용되지 않는 확장자 파일 업로드 : " + multipartFile.getOriginalFilename());
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "허용되지 않는 파일 형식입니다.");
			return;
		}

		try {
			sysFileVO = service.upload(multipartFile, repository, organization, type, UserBroker.getUserNo(request));	// 파일을 저장 처리.
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Error! Failed file upload.");
			throw e;
		}

		JsonUtil.responseJson(response, SysFileVOUtil.getJsonData(sysFileVO));
	}

	/**
	 * JQuery Fileupload 용으로 제작
	 * 2015.4.22
	 * @param request
	 * @param response
	 * @throws Throwable
	 */
	@RequestMapping(value="/file/upload", headers="content-type=multipart/*", method=RequestMethod.POST)
	@ResponseBody
	public void fileUpload(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		//MultipartHttpServletRequest request = (MultipartHttpServletRequest)req; 

		logger.debug("--- file upload start ---");
		Iterator<String> itr = request.getFileNames();
		MultipartFile multipartFile;

		SysFileVO sysFileVO = null;

		String repository = StringUtil.nvl(request.getParameter("repository"),"GENERAL");
		String organization = StringUtil.nvl(request.getParameter("organization"),"");
		String type = StringUtil.nvl(request.getParameter("type"),"file");

		while (itr.hasNext()) {
			multipartFile = request.getFile(itr.next());
			logger.debug("Upload File ::==>"+multipartFile.getOriginalFilename());

			if (multipartFile.getSize() == 0) {
				logger.debug(">>>>> 첨부파일이 없는 요청");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "첨부파일이 누락된 요청입니다.");
				return;
			}

			if (isBlockedFileExtention(multipartFile.getOriginalFilename())) {
				logger.debug(">>>>> 허용되지 않는 확장자 파일 업로드 : " + multipartFile.getOriginalFilename());
				response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "허용되지 않는 파일 형식입니다.");
				return;
			}

			try {
				sysFileVO = service.upload(multipartFile, repository, organization, type, UserBroker.getUserNo(request));	// 파일을 저장 처리.
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Error! Failed file upload.");
				throw e;
			}
		}
		JsonUtil.responseJson(response, SysFileVOUtil.getJsonData(sysFileVO));
	}

	/**
	 * SysFileVO의 정보를 Json으로 응답
	 * @param fileSn "!@!" 구분자로 구분된 첨부파일 번호 목록
	 * @param daumeditor 다음에디터 사용 여부 (반환하는 자료 구조가 바뀐다.)
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @throws Throwable
	 */
	@RequestMapping("/file/jsonview")
	public void jsonView(
			@RequestParam(value = "fileSns", defaultValue="") String fileSn,
			@RequestParam(value = "daumeditor", defaultValue="false", required=false) Boolean daumeditor,
			Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Throwable {

		List<SysFileVO> listSysFileVO = convertStringToArray(fileSn, "!@!");

		for (int i = 0; i < listSysFileVO.size(); i++) {
			SysFileVO sysFileVO = listSysFileVO.get(i);
			if(sysFileVO.getFileSn() == null || sysFileVO.getFileSn() == 0) continue;
			sysFileVO = this.searchSysFileVO(sysFileVO.getFileSn(), false);
			listSysFileVO.set(i, sysFileVO);
		}

		JsonUtil.responseWrite(response, SysFileVOUtil.getJson(listSysFileVO, daumeditor));
	}

	/**
	 * 문자열 스트링으로 넘어온 값을 {@code List<SysFileVO>} 형태로 변환해서 반환한다.
	 * @param source 구분자로 구분된 문자열
	 * @param delimiter 구분자 문자열
	 * @return
	 */
	// 구분자를 임의로 바꿔서 사용할 필요가 있을 때 public으로 변경해서 사용할 것.
	private static List<SysFileVO> convertStringToArray(String source, String delimiter) {
		String[] strArray = source.split(delimiter);
		List<SysFileVO> listSysFileVO = new ArrayList<SysFileVO>();
		for (String str : strArray) {
			try {
				Integer intValue = Integer.parseInt(str);
				listSysFileVO.add(new SysFileVO(intValue)); // Integer 파싱에 실패한 값은 저장되지 않는다.
			} catch (NumberFormatException ignore) {}
		}
		return listSysFileVO;
	}

	/**
	 * SysFileVO의 ID를 받아서 다운로드 view로 전달.
	 * @param fileSn SysFileVO.fileSn
	 * @return 다운로드뷰
	 */
	@RequestMapping("/file/download/{fileSn}")
	public ModelAndView download(@PathVariable Integer fileSn) {
		return sysFileVOToModelAndView(fileSn, FILE_DOWNLOAD_VIEW);
	}

	/**
	 * SysFileVO의 ID를 받아서 Mime타입 view로 전달.
	 * @param fileSn SysFileVO.fileSn
	 * @return 마임타입뷰
	 */
	@RequestMapping("/file/view/{fileSn}")
	public ModelAndView view(@PathVariable Integer fileSn) {
		return sysFileVOToModelAndView(fileSn, FILE_MIMETYPE_VIEW);
	}

	/**
	 * SysFileVO의 ID를 받아서 Mime타입 view로 전달. 조회수 증가 없음.
	 * @param fileSn SysFileVO.fileSn
	 * @return 마임타입뷰
	 */
	@RequestMapping("/file/view2/{fileSn}")
	public ModelAndView view2(@PathVariable Integer fileSn) {
		return sysFileVOToModelAndView(fileSn, FILE_MIMETYPE_VIEW, false);
	}

	/**
	 * 썸네일이 있는지 파악하고 있을 경우 썸네일을 없을 경우 원본을 전달.
	 * @param fileSn
	 * @return
	 */
	@RequestMapping("/file/thumb/{fileSn}")
	public ModelAndView thumb(@PathVariable("fileSn") Integer fileSn) {
		ModelAndView mav = new ModelAndView();
		SysFileVO sysFileVO = searchSysFileVO(fileSn, false);
		String filePath = Constants.FILE_STORAGE_PATH;
		if(sysFileVO != null) {
			if(ValidationUtils.isNotEmpty(sysFileVO.getOrgCd())) filePath += File.separator + sysFileVO.getOrgCd();
			filePath += sysFileVO.getSaveFilePath();
			File file = new File(filePath);
			File thumb = getThumbnailName(file);

			if (thumb.isFile() && thumb.canRead()) {
				mav.addObject("downloadFile", thumb);
				mav.addObject("mime-type", "image/jpeg");
				mav.addObject("fileName", "thumb_" + sysFileVO.getFileNm());
			} else {
				mav.addObject("downloadFile", file);
				mav.addObject("mime-type", sysFileVO.getMimeType());
				mav.addObject("fileName", sysFileVO.getFileNm());
			}
			
		} else {
			filePath = File.separator + "noImg.png";
			File file = new File(filePath);
			File thumb = getThumbnailName(file);
			mav.addObject("downloadFile", thumb);
			mav.addObject("mime-type", "image/jpeg");
			mav.addObject("fileName", "thumb_no_image");
		}
		mav.setViewName(FILE_MIMETYPE_VIEW);
		
		return mav;

		
	}

	/**
	 * SysFileVO객체를 구해서 FileDownload,와 FileMimeview에 맞게
	 * ModelAndView를 만들어서 반환.
	 */
	private ModelAndView sysFileVOToModelAndView(Integer fileSn, String viewName) {
		return this.sysFileVOToModelAndView(fileSn, viewName, true);
	}
	private ModelAndView sysFileVOToModelAndView(Integer fileSn, String viewName, boolean isIncrementHits) {
		ModelAndView mav = new ModelAndView();
		SysFileVO sysFileVO = searchSysFileVO(fileSn, isIncrementHits);	// download와 view는 조회수를 증가시킨다.

		if(sysFileVO != null) {
			String filePath = Constants.FILE_STORAGE_PATH;
			/*2016-12-16 arothy*/
			if("contents".equals(sysFileVO.getFileType())){
				filePath = Constants.CONTENTS_STORAGE_PATH;
			}
			if(ValidationUtils.isNotEmpty(sysFileVO.getOrgCd())) filePath += File.separator + sysFileVO.getOrgCd();
			filePath += sysFileVO.getSaveFilePath();
			File file = new File(filePath);
			mav.addObject("downloadFile", file);
			mav.addObject("fileName", sysFileVO.getFileNm());
			mav.addObject("mime-type", sysFileVO.getMimeType());
		}

		mav.setViewName(viewName);
		return mav;
	}

	/**
	 * 해당 파일을 삭제하는 컨트롤러.
	 * @param fileSn
	 * @return
	 */
	@RequestMapping("/file/delete/{fileSn}")
	public void delete(@PathVariable("fileSn") Integer fileSn, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 권한 제어 : ROLE_ADMIN이거나 파일의 uploader만 삭제 가능.
		logger.debug("/file/delete를 통한 파일 삭제.. : SysFileVO(" + fileSn + ")");

		try {
			deleteFileProcess(fileSn, request, response);
		} catch (AccessDeniedException ex) {
			this.responseJson(response, PROCESS_RESULT_FAILED, ex.getMessage());
		}

		this.responseJson(response, PROCESS_RESULT_SUCCESS, fileSn.toString());
	}


	/**
	 * 실제로 낱개단위 파일을 삭제한다. (파일정보를 조회해서 권한이 없을 경우 예외를 발생)
	 * @param fileSn
	 * @param request
	 * @param response
	 * @throws AccessDeniedException 관리자 or 소유자가 아닐 경우
	 */
	private void deleteFileProcess(Integer fileSn, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SysFileVO sysFileVO = this.searchSysFileVO(fileSn, false);

		// DB에 파일 정보가 있을 경우에만..
		if(sysFileVO.getFileSn() != FILE_NOT_FOUND_IN_PERSIST) {
			// 접근 권한이 없으면 익셉션..
			if(!isOwnerOrAdminGroup(request, sysFileVO.getRegNo())) {
				//throw new AccessDeniedException("해당 파일의 삭제 권한이 없습니다.");
			}
			service.removeFile(this.searchSysFileVO(fileSn, false));
		}
	}

	/**
	 * 여러개의 파일을 지우고자 할경우 파라매터로 배열을 받아온다.
	 * @param files 파일의 id목록. 구분자로 {@code "!@!"} 를 사용한다.
     * @param response 결과를 전송할 response
	 * @return
	 */
	@RequestMapping("/file/deletes")
	public void deleteAll(@RequestParam("files") String files, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.debug("/file/delete를 통한 다수 파일 삭제.. : " + files );

		String[] fileLists = files.split("!@!");
		Integer fileSn = null;

		// 파일과 DB의 트랜잭션을 위해 단일 서비스를 반복해서 실행.
		for (String varfileSn : fileLists) {
			try {
				fileSn = Integer.parseInt(varfileSn);
				// 단일파일 삭제 컨트롤러를 반복 실행
				this.deleteFileProcess(fileSn, request, response);
			} catch (Exception e) {}	// 실패해도 계속 진행한다.
		}

		this.responseJson(response, PROCESS_RESULT_SUCCESS, "ALL");
	}

	private void responseJson(HttpServletResponse response, String success, String message) {
		Hashtable<String, Object> json = new Hashtable<String, Object>();
		json.put(PROCESS_RESULT, success);
		json.put(PROCESS_RESULT_MESSAGE, message);
		JsonUtil.responseJson(response, json);
	}

	private boolean isOwnerOrAdminGroup(HttpServletRequest request, String ownerNo) {
		String userNo = UserBroker.getUserNo(request);
		// 비어있는 userNo와 동일취급되는걸 막기 위해 별도의 상수값 지정.
		ownerNo = StringUtil.nvl(ownerNo, NO_OWNER);
		if(ownerNo.equals(userNo)) return true;
		return isAdminGroup(request);
	}

	private boolean isAdminGroup(HttpServletRequest request) {
		String userType = UserBroker.getAdmType(request);
		if (userType.equals("SYSADM") 			// 시스템관리자
				|| userType.equals("HOMEADM") 	// 홈페이지관리자
				|| userType.equals("PDSADM")	// 자료마당관리자
				|| userType.equals("ZINEADM"))	// 웹진관리자
			return true;
		userType = UserBroker.getUserType(request);
		if (userType.equals("ADMIN"))
			return true;
		return false;
	}

	private File getThumbnailName(File file) {
		String src = file.getAbsolutePath();
		String ext = StringUtil.getExt(src);
		String dest = src.substring(0, src.lastIndexOf(ext)) + "_thumb.jpg";
		return new File(dest);
	}

	@SuppressWarnings("serial")
	private SysFileVO searchSysFileVO(Integer fileSn, boolean isIncrementHits) {
		SysFileVO fileVO = new SysFileVO(fileSn);
		try {
			fileVO = service.getFile(fileVO, isIncrementHits);
		} catch (Exception e) {
			return new SysFileVO() {{
				setFileSn(FILE_NOT_FOUND_IN_PERSIST);
				setFilePath("/common");
				setFileSaveNm("noimage.jpg");
				setMimeType("image/jpeg");
			}};
		}
		return fileVO;
	}

	/**
	 * 파일 확장자정보를 받아서 차단대상 확장자 목록에 있을 경우 true를 반환한다.
	 * @param originalFilename
	 * @return
	 */
	private boolean isBlockedFileExtention(String originalFilename) {
		String ext = StringUtil.getExtNoneDot(originalFilename).toLowerCase();

		if(Constants.FILE_BLOCKED_EXT.toLowerCase().indexOf(ext) > -1) {
			return true;
		}

		return false;
	}
}

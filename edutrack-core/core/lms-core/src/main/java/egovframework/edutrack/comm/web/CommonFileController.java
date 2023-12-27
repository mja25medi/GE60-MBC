package egovframework.edutrack.comm.web;

import java.io.File;
import java.util.Iterator;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.RandomStringUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.system.file.service.SysFileVO;


/**
 * 파일에 대한 접근을 담당하는 컨트롤러.
 * @author SungKook
 */
@Controller
@SessionAttributes("file")
public class CommonFileController {

	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * uploadify 일반 파일 (콘텐츠, Excel 파일등) 업로드용
	 * @param multipartFile
	 * @param directory
	 * @param type
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @throws Throwable
	 */
	@RequestMapping("/uplodify/ajaxupload")
	public void ajaxUploadExcel(
			@RequestParam("Filedata") MultipartFile multipartFile,
			@RequestParam(value="fileDir", defaultValue="GENERAL", required=false) String directory,
			@RequestParam(value="type", defaultValue="excel", required=false) String type,
			Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Throwable {

		SysFileVO sysFileVO = null;

		if (multipartFile.getSize() == 0) {
			logger.debug(">>>>> 첨부파일이 없는 요청");
			throw new Exception("첨부파일이 없는 요청입니다.");
		}

		try {
			sysFileVO = upload(multipartFile, directory, type);	// 파일을 저장 처리.
		} catch (Exception e) {
			throw new Exception("파일 처리가 정상적으로 되지 않았습니다.", e);
		}

		responseJson(response, sysFileVO);
	}

	/**
	 * jquery 일반 파일 (콘텐츠, Excel 파일등) 업로드용
	 * 2015.4.22
	 * @param request
	 * @param response
	 * @throws Throwable
	 */
	@RequestMapping(value="/jquery/upload",method=RequestMethod.POST)
	@ResponseBody
	public void fileUploadExcel(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Throwable {

		logger.debug("--- file upload start ---");
		Iterator<String> itr = request.getFileNames();
		MultipartFile multipartFile;

		SysFileVO sysFileVO = null;

		String targetdir = StringUtil.nvl(request.getParameter("fileDir"),"");
		String organization = StringUtil.nvl(request.getParameter("organization"),"");
		String type = StringUtil.nvl(request.getParameter("type"),"excel");

		//String directory = "/"+organization+"/excelfile/"+targetdir;
		String directory = "/"+organization+targetdir;

		while (itr.hasNext()) {
			multipartFile = request.getFile(itr.next());
			logger.debug("Upload File ::==>"+multipartFile.getOriginalFilename());

			if (multipartFile.getSize() == 0) {
				logger.debug(">>>>> 첨부파일이 없는 요청");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "첨부파일이 누락된 요청입니다.");
				return;
			}

			try {
				sysFileVO = upload(multipartFile, directory, type);	// 파일을 저장 처리.
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Error! Failed file upload.");
				throw e;
			}
		}
		responseJson(response, sysFileVO);
	}

	/**
	 * 멀티파트 파일 업로드 처리 서비스
	 * @param multipartFile 첨부 파일 정보
	 * @param repository 파일이 저장될 repository명
     * @param fileType 파일 타입 정보
	 * @return
	 * @throws Throwable
	 */
	private SysFileVO upload(MultipartFile multipartFile, String directory, String fileType) throws Throwable {

		// = this.addFile(multipartFile, repository, fileType);

		String saveFullPath = "";
		if("contents".equals(fileType)) {
			saveFullPath = Constants.CONTENTS_STORAGE_PATH + directory;
		} else if("ftp".equals(fileType)) {
			saveFullPath = Constants.CONTENTS_STORAGE_PATH + directory;
		} else {
			saveFullPath = Constants.FILE_STORAGE_PATH + "/Z99999" + directory;
		}

		String fileName = multipartFile.getOriginalFilename();
		String fileExt  = StringUtil.getExtNoneDot(fileName).toLowerCase();
		String saveFileName = "";
		if("contents".equals(fileType)) {
			//---- 콘텐츠 파일인 경우 파일 원 이름으로 저장함.
			saveFileName = fileName;
		} else {
			//---- 콘테츠 파일이 아닌 경우는 파일 리네임.
			saveFileName = RandomStringUtil.getRandomMD5() + "." + fileExt;
		}
		Long fileSize = multipartFile.getSize();

		// 파일을 실제 저장소로 이전.
		preparePath(saveFullPath);	// 경로가 없으면 만든다.
		
		String name =saveFileName;
		if(name != null && !"".equals(name)) {
			name = name.replaceAll("/", "");
			name = name.replaceAll("\\\\", ""); 
			name = name.replaceAll("[.]{2}", "");    
			name = name.replaceAll("&", "");    
			name = name.replaceAll("%", "");     
		} 
		
//		File saveFile = new File(saveFullPath + File.separator + saveFileName);
		File saveFile = new File(saveFullPath + File.separator + name);
		multipartFile.transferTo(saveFile); // 파일저장소 경로로 복사

		SysFileVO sysFileVO = new SysFileVO();
		sysFileVO.setFileNm(fileName);
		sysFileVO.setFileSaveNm(saveFileName);
		sysFileVO.setFileExt(fileExt);
		sysFileVO.setFileSize(fileSize);
		sysFileVO.setFilePath(saveFullPath);
		// 파일이름으로 파일의 mime을 가져온다. (파일을 뽑은 뒤에는 파일에서 추출할 수 있는건지 확인.)
		String newFileName = sysFileVO.getFileNm();
		if(newFileName != null && !"".equals(newFileName)) {
			newFileName = newFileName.replaceAll("/", "");
			newFileName = newFileName.replaceAll("\\\\", ""); 
			newFileName = newFileName.replaceAll("[.]{2}", "");    
			newFileName = newFileName.replaceAll("&", "");    
			newFileName = newFileName.replaceAll("%", "");     
		} 
		
		sysFileVO.setMimeType(new MimetypesFileTypeMap().getContentType(newFileName));
		sysFileVO.setFileType(fileType);

		return sysFileVO;
	}

	/**
	 * 대상 경로가 없을 경우 생성.
	 * @param path
	 */
	private void preparePath(String path) {
		File savePath = new File(path);
		if (!savePath.exists())
			savePath.mkdirs();
	}

	/**
	 * 파일 삭제
	 * @param response
	 * @param json
	 */
	public void fileDelete(String fileName, String filePath) {
		File file = new File(filePath + "/" + fileName);
		if(file.exists() && file.canWrite())
			file.delete();	// 파일의 삭제가 가능하다면 삭제.
	}

	private void responseJson(HttpServletResponse response, Object json) {
		String strJson = JsonUtil.getJsonString(json);
		if(logger.isDebugEnabled())
			logger.debug("JSON-->" + strJson);
		JsonUtil.responseWrite(response, strJson);
	}

}

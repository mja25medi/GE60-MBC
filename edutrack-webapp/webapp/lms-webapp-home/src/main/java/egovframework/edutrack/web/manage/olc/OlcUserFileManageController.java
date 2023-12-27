package egovframework.edutrack.web.manage.olc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.JsTreeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.file.service.SysFileVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/olc/userFile")
public class OlcUserFileManageController extends GenericController{

	/**
	 * 파일 관리 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mainPop")
	public String mainPop( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		return "mng/olc/userfile/file_main_pop";
	}

	/**
	 * 디렉토리 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listDir")
	public String listDir( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileNm = StringUtil.nvl(request.getParameter("fileNm"));

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		String userFilePath = "";

		//-- 최상외 접속시 리턴
		if("#".equals(id)) {
			//-- 최상위 노드가 없으면 생성한다.
			FileUtil.setDirectory(contentsFilePath+"/"+userNo);
			SysFileVO fileVO = new SysFileVO();
			fileVO.setFilePath("");
			fileVO.setFileNm(userNo);
			JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(userNo, userNm+"("+userId+")", "root", 1, fileVO);
			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals(userNo)) id = "";
			List<SysFileVO> listSub = this.listFile(contentsFilePath+"/"+filePath+"/"+fileNm, "dir").getReturnList();
			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			for(SysFileVO VO : listSub) {
				id = (VO.getFilePath() +"/" + VO.getFileNm()).replace(contentsFilePath+"/", "").replace("/", "-").replace(" ", "");
				VO.setFilePath(VO.getFilePath().replace(contentsFilePath+"/", ""));
				String rel = "folder";
				JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(id, VO.getFileNm(), rel, 1, VO);
				treeList.add(treeVO);
			}
			return JsonUtil.responseJson(response, treeList);
		}
	}

	/**
	 * 디렉토리 생성
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/createDir")
	public String createDir(  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileName = "NewFolder";
		File	 file		=	null;
		SysFileVO fileVO = new SysFileVO();
		fileVO.setFilePath(filePath);
		for(int i=0; i < 100; i++) {
			String lfileName = fileName + Integer.toString(i);
			file = new File(contentsFilePath+"/"+filePath+"/"+lfileName);
			if(!file.isDirectory()) {
				FileUtil.setDirectory(contentsFilePath+"/"+filePath+"/"+lfileName);
				fileVO.setFileNm(lfileName);
				break;
			}

		}
		String id = (fileVO.getFilePath() +"/" + fileVO.getFileNm()).replace(contentsFilePath+"/", "").replace("/", "-").replace(" ", "");
		fileVO.setFilePath(fileVO.getFilePath().replace(contentsFilePath+"/", ""));
		JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(id, fileVO.getFileNm(), "folder", 1, fileVO);
		return JsonUtil.responseJson(response, treeVO);
	}

	/**
	 * 디렉토리 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/renameDir")
	public String renameDir( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileName = StringUtil.nvl(request.getParameter("fileNm"));
		String newFileName = StringUtil.nvl(request.getParameter("newFileNm"));

		ProcessResultVO<JsTreeVO> resultVO = new ProcessResultVO<JsTreeVO>();

		File sourceFile = new File(contentsFilePath+"/"+filePath+"/"+fileName);
		File targetFile = new File(contentsFilePath+"/"+filePath+"/"+newFileName);

		if(!targetFile.exists()) {
			if(!sourceFile.renameTo(targetFile)) {
				resultVO.setMessage(getMessage(request, "olc.message.contents.renamedir.failed"));
				resultVO.setResult(-1);
			} else {
				resultVO.setResult(1);
			}
		}
		SysFileVO fileVO = new SysFileVO();
		String id = (filePath +"/" + fileName).replace(contentsFilePath+"/", "").replace("/", "-").replace(" ", "");
		fileVO.setFilePath(filePath);
		fileVO.setFileNm(newFileName);
		JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(id, fileVO.getFileNm(), "folder", 1, fileVO);
		resultVO.setReturnVO(treeVO);
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 디렉토리 삭제
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/removeDir")
	public String removeDir( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileName = StringUtil.nvl(request.getParameter("fileNm"));

		FileUtil.delDirectory(contentsFilePath+"/"+filePath+"/"+fileName);
		SysFileVO fileVO = new SysFileVO();

		return JsonUtil.responseJson(response, fileVO);
	}

	/**
	 * 파일 목록 조회 (컨텐츠 등록)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listFileCnts")
	public String listFileCnts( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileNm = StringUtil.nvl(request.getParameter("fileNm"));

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		List<SysFileVO> fileList = this.listFile(contentsFilePath+"/"+filePath+"/"+fileNm, "file").getReturnList();
		request.setAttribute("fileList", fileList);
		request.setAttribute("filePath", filePath);
		request.setAttribute("fileNm", fileNm);

		return "mng/olc/userfile/file_list_cnts_div";
	}

	/**
	 * 파일 목록 조회 (컨텐츠 등록)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listFileManage")
	public String listFileManage( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);
		String id = StringUtil.nvl(request.getParameter("id"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileNm = StringUtil.nvl(request.getParameter("fileNm"));

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		List<SysFileVO> fileList = this.listFile(contentsFilePath+"/"+filePath+"/"+fileNm, "file").getReturnList();
		request.setAttribute("fileList", fileList);
		request.setAttribute("filePath", filePath);
		request.setAttribute("fileNm", fileNm);

		return "mng/olc/userfile/file_list_manage_div";
	}

	/**
	 * 압축 파일 풀기
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/unzipFile")
	public String unzipFile( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileName = StringUtil.nvl(request.getParameter("fileNm"));

		String sourcePath = contentsFilePath + "/" + filePath + "/"+ fileName;
		String targetPath = contentsFilePath + "/" + filePath + "/";
		FileUtil.unZipFile(sourcePath, targetPath);

		SysFileVO fileVO = new SysFileVO();
		return JsonUtil.responseJson(response, fileVO);
	}

	/**
	 * 파일 삭제
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/removeFile")
	public String removeFile( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		String userId = UserBroker.getUserId(request);
		String userNm = UserBroker.getUserName(request);

		String contentsFilePath = Constants.CONTENTS_STORAGE_PATH+"/usercnts";

		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileName = StringUtil.nvl(request.getParameter("fileNm"));

		String[] fileList = StringUtil.split(fileName, ",");
		for(int i =0; i < fileList.length; i++) {
			String fileNm = fileList[i];
			FileUtil.delFile(contentsFilePath+"/"+filePath+"/"+fileNm);
		}
		SysFileVO fileVO = new SysFileVO();

		return JsonUtil.responseJson(response, fileVO);
	}

	/**
	 * 입력된 parent path의 하위 폴더 또는 파일을 목록으로 가져온다.
	 * @param parentPath
	 * @param listType
	 * @return ProcessResultListVO<FileVO>
	 */
	@RequestMapping(value="/listFile")
	private ProcessResultListVO<SysFileVO> listFile(String filePath, String listType) {
		File file =	new File(filePath);
		File[] list = file.listFiles();

		FileUtil.sort(list);

		List<SysFileVO> subList = new ArrayList<SysFileVO>();
		int dirRow = 0;
		//---- 디렉토리 담기
		if("file".equals(listType)) {
			for(File file2 : list) {
				if(file2.isFile()) {
					SysFileVO fileVO = new SysFileVO();
					fileVO.setFileNm(file2.getName());
					fileVO.setFilePath(filePath);
					fileVO.setFileExt(FileUtil.getFileExtention(file2.getName()));
					fileVO.setFileType("D");
					subList.add(fileVO);
				}
			}
		} else if("dir".equals(listType)) {
			for(File file2 : list) {
				if(file2.isDirectory()) {
					SysFileVO fileVO = new SysFileVO();
					fileVO.setFileNm(file2.getName());
					fileVO.setFilePath(filePath);
					fileVO.setFileExt(FileUtil.getFileExtention(file2.getName()));
					fileVO.setFileType("F");
					subList.add(fileVO);
				}
			}
		}
		ProcessResultListVO<SysFileVO> result = new ProcessResultListVO<SysFileVO>();
		result.setResult(1);
		result.setReturnList(subList);
		return result;
	}
}

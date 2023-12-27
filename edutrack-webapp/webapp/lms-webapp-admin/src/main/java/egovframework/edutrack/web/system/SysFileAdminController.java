package egovframework.edutrack.web.system;

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
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.file.service.SysFileRepoLangVO;
import egovframework.edutrack.modules.system.file.service.SysFileRepoService;
import egovframework.edutrack.modules.system.file.service.SysFileRepoVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/system/file")
public class SysFileAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("sysFileRepoService")
	private SysFileRepoService 	sysFileRepoService;
	
	@Autowired @Qualifier("sysFileService")
	private SysFileService 		sysFileService;
	
	/**
     * @Method Name : mainRepo
     * @Method 설명 : 파일 관리 리파지토리 메인 화면으로 이동한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/main_repo.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/repoMain")
	public String repoMain(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");
	
		return "system/file/main_repo";
	}
	
	/**
     * @Method Name : listRepo
     * @Method 설명 : 파일 리파지토리 리스트 화면으로 이동한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/list_repo.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listRepo")
	public String listRepo(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<SysFileRepoVO> resultList = sysFileRepoService.listPageing(vo, vo.getPageIndex());
		request.setAttribute("repoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "system/file/list_repo";
	}
	
	/**
     * @Method Name : addFromRepo
     * @Method 설명 : 파일 리파지토리 등록 화면으로 이동한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/write_repo.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormRepoPop")
	public String addFormRepoPop(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "system/file/write_repo";
	}
	
	/**
     * @Method Name : addRepo
     * @Method 설명 : 파일 리파지토리를 등록한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addRepo")
	public String addRepo(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysFileRepoVO> result = new ProcessResultVO<SysFileRepoVO>();
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysFileRepoLangVO> repoLangList = new ArrayList<SysFileRepoLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysFileRepoLangVO sfrlvo = new SysFileRepoLangVO();
			sfrlvo.setRepoCd(vo.getRepoCd());
			sfrlvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				sfrlvo.setRepoNm(vo.getRepoNm());
			} else {
				sfrlvo.setRepoNm(request.getParameter("repoNm_"+langList[i]));
			}
			repoLangList.add(sfrlvo);
		}
		vo.setFileRepoLangList(repoLangList);
	
		try {
			sysFileRepoService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.file.add.repository.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.file.add.repository.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : edotFromRepo
     * @Method 설명 : 파일 리파지토리 수정 화면으로 이동한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/write_repo.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormRepoPop")
	public String editFormRepoPop(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		vo = sysFileRepoService.view(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "system/file/write_repo";
	}
	
	/**
     * @Method Name : editRepo
     * @Method 설명 : 파일 리파지토리를 등록한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editRepo")
	public String editRepo(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysFileRepoVO> result = new ProcessResultVO<SysFileRepoVO>();
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysFileRepoLangVO> repoLangList = new ArrayList<SysFileRepoLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysFileRepoLangVO sfrlvo = new SysFileRepoLangVO();
			sfrlvo.setRepoCd(vo.getRepoCd());
			sfrlvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				sfrlvo.setRepoNm(vo.getRepoNm());
			} else {
				sfrlvo.setRepoNm(request.getParameter("repoNm_"+langList[i]));
			}
			repoLangList.add(sfrlvo);
		}
		vo.setFileRepoLangList(repoLangList);
	
		try {
			sysFileRepoService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.file.edit.repository.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.file.edit.repository.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : removeRepo
     * @Method 설명 : 파일 리파지토리를 삭제 한다. 
	 * @param SysFileRepoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeRepo")
	public String removeRepo(SysFileRepoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysFileRepoVO> result = new ProcessResultVO<SysFileRepoVO>();
		try {
			sysFileRepoService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.file.delete.repository.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.file.delete.repository.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : mainFile
     * @Method 설명 : 파일 관리 메인 화면으로 이동한다. 
	 * @param SysFileVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/main_file.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/fileMain")
	public String fileMain(SysFileVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		SysFileRepoVO sfrvo = new SysFileRepoVO();
		sfrvo.setRepoCd(vo.getRepoCd());
		sfrvo = sysFileRepoService.view(sfrvo);
		request.setAttribute("repoVo", sfrvo);
		request.setAttribute("vo", vo);
		request.setAttribute("nyromodal", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("paging", "Y");
		
		
		return "system/file/main_file";
	}
	
	/**
     * @Method Name : listFile
     * @Method 설명 : 파일 리스트 화면으로 이동한다. 
	 * @param SysFileVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/list_file.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listFile")
	public String listFile(SysFileVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<SysFileVO> resultList = sysFileService.listPageing(vo, vo.getPageIndex());
		request.setAttribute("fileList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "system/file/list_file";
	}
	
	/**
     * @Method Name : viewFile
     * @Method 설명 : 파일 정보 상세 조회 화면으로 이동한다. 
	 * @param SysFileVO 
	 * @param commandMap
	 * @param model
	 * @return  "/system/file/view_file.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewFilePop")
	public String viewFilePop(SysFileVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = sysFileService.getFile(vo, false);
		request.setAttribute("vo", vo);
		return "system/file/view_file";
	}	
}

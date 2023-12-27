package egovframework.edutrack.web.manage.org;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.ide.service.OrgIdeInfoService;
import egovframework.edutrack.modules.org.ide.service.OrgIdeInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/ideInfo")
public class OrgIdeInfoManageController extends GenericController {
	
	@Autowired @Qualifier("orgIdeInfoService")
	private OrgIdeInfoService 		orgIdeInfoService;
	
	@RequestMapping(value="main")
	public String main(OrgIdeInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/org/ideinfo/ide_main";
	}
	
	@RequestMapping(value="/listIde")
	public String listIde(OrgIdeInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

    	ProcessResultListVO<OrgIdeInfoVO> resultList = orgIdeInfoService.listPageing(vo, vo.getPageIndex());
    	request.setAttribute("ideInfoList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/org/ideinfo/list_ide";
	}
	
	@RequestMapping(value="/addFormIdePop")
	public String addFormIdePop(OrgIdeInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUseYn("Y");
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/org/ideinfo/write_ide";
	}
	
	@RequestMapping(value="/addIde")
	public String addInfo(OrgIdeInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgIdeInfoVO> result = new ProcessResultVO<OrgIdeInfoVO>();
		try {
			orgIdeInfoService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.ideinfo.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.ideinfo.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	@RequestMapping(value = "/editUse")
	@ResponseBody
	public AbstractResult editExcept(OrgIdeInfoVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		AbstractResult result = new AbstractResult();
		try {
			orgIdeInfoService.editUseYn(vo);
			result.setResult(1);
			result.setMessage("상태를 변경 했습니다.");
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("상태 변경에 실패 했습니다.");
		}
		return result;
	}

	@RequestMapping(value="/removeIde")
	public String removeInfo(OrgIdeInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<OrgIdeInfoVO> result = new ProcessResultVO<OrgIdeInfoVO>();
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		try {
			orgIdeInfoService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.ideinfo.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.ideinfo.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * 사용자 등록 샘플 엑셀 다운로드
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping(value="/sampleExcelIdeAdd")
	public String sampleExcelUserAdd(OrgIdeInfoVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		response.setHeader("Content-Disposition", "attachment;filename=add_ide_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		orgIdeInfoService.sampleExcelDownloadForIdeAdd(response.getOutputStream(), orgCd);
		
		return null;
	}
	
	/**
	 * 사용자 엑셀 등록 팝
	 * @param usrUserInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addIdeExcelPop")
	public String addUserExcelPop(OrgIdeInfoVO vo, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "A");
		return "mng/org/ideinfo/add_ide_excel_pop";
	}
	
	/**
	 * 사용자 엑셀 업로드
	 * @param usrUserInfoVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ideExcelUpload")
	@ResponseBody
	public ProcessResultListVO<OrgIdeInfoVO> userExcelUpload(OrgIdeInfoVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		ProcessResultListVO<OrgIdeInfoVO> result = new ProcessResultListVO<>();
		try {
			orgIdeInfoService.excelUploadIdeAdd(vo, vo.getFileName(), vo.getFilePath(), orgCd);
			result.setResult(1);
			result.setMessage("IDE 등록에 성공했습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("IDE 등록에 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 IDE 등록에 실패했습니다.");
		}
		return result;
	}

}

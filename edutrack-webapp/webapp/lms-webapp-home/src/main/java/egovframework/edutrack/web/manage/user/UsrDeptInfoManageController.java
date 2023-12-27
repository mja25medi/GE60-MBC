package egovframework.edutrack.web.manage.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/user/deptInfo")
public class UsrDeptInfoManageController extends GenericController {

	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService usrDeptInfoService;

	@Autowired @Qualifier("sysCodeService")
	private SysCodeService 	 sysCodeService;

	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService 	 orgCodeService;

	/**
	 * 소속 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deptInfoMain")
	public String deptInfoMain(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");

		return "mng/user/dept/user_dept_main";
	}

	/**
	 * 소속 관리 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listDeptInfo")
	public String listDeptInfo(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setPageScale(10);
		ProcessResultListVO<UsrDeptInfoVO> resultList = usrDeptInfoService.listPageing(vo);

		request.setAttribute("userDeptInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/user/dept/user_dept_list_div";
	}

	/**
	 * 소속 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormUserDeptPop")
	public String addFormUserDept (UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setUseYn("Y");
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//기업 관리자 리스트
		List<UsrUserInfoVO> userList = usrDeptInfoService.listSearch(vo).getReturnList();
		request.setAttribute("userList", userList);
		
		//기업 종류
		List<SysCodeVO> deptTypeCode = sysCodeService.listCode("DEPT_TYPE_CD").getReturnList();
		request.setAttribute("deptTypeCode", deptTypeCode);
		
		//업종 코드
		List<SysCodeVO> bsnsTypeCode = sysCodeService.listCode("BSNS_TYPE_CD").getReturnList();
		request.setAttribute("bsnsTypeCode", bsnsTypeCode);
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeService.listCode("LOCAL_PHONE_CODE").getReturnList();
		request.setAttribute("localPhoneCdList", localPhoneCdList);		
		
		vo.setUseYn("");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/user/dept/user_dept_write";
	}
	

	/**
	 * 소속 등록(AJAX)
	 *
	 * @return ProcessResultDTO
	 */
	@RequestMapping(value="/addUserDept")
	public String addUserDept(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<UsrDeptInfoVO> resultDTO = new ProcessResultVO<UsrDeptInfoVO>();
		
		resultDTO.setResult(usrDeptInfoService.add(vo));
		if(resultDTO.getResult() > 0) {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.add.success"));
		} else {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.add.failed"));
		}
		return JsonUtil.responseJson(response, resultDTO);
	}

	/**
	 * 소속 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormDeptEPop")
	public String editFormDeptEPop (UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//기업 관리자 리스트
		List<UsrUserInfoVO> userList = usrDeptInfoService.listSearch(vo).getReturnList();
		request.setAttribute("userList", userList);
		
		//기업 종류
		List<SysCodeVO> deptTypeCode = sysCodeService.listCode("DEPT_TYPE_CD").getReturnList();
		request.setAttribute("deptTypeCode", deptTypeCode);
		
		//업종 코드
		List<SysCodeVO> bsnsTypeCode = sysCodeService.listCode("BSNS_TYPE_CD").getReturnList();
		request.setAttribute("bsnsTypeCode", bsnsTypeCode);
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeService.listCode("LOCAL_PHONE_CODE").getReturnList();
		request.setAttribute("localPhoneCdList", localPhoneCdList);
		
		vo = usrDeptInfoService.view(vo);
		String plainPhoneNo = StringUtil.nvl(vo.getPhoneno()).replaceAll("-", "");
		vo.setPhoneno(hyphenPhoneNo(plainPhoneNo));

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "mng/user/dept/user_dept_write";
	}
	
	private String hyphenPhoneNo(String phoneNo) {
		if(phoneNo == null || phoneNo.length() < 9) return phoneNo;
		
		StringBuffer sb = new StringBuffer(phoneNo);
		
		int firstIndex = 0;
		int secondIndex = 0;
		if(phoneNo.startsWith("02")) {
			firstIndex = 2;
			secondIndex = phoneNo.length() == 10 ? 6 : 5;
		} else {
			firstIndex = 3;
			secondIndex = phoneNo.length() == 10 ? 6 : 7;
		}
		sb.insert(firstIndex, "-");
		sb.insert(secondIndex+1, "-");
		
		return sb.toString();
	}

	/**
	 * 소속 수정(AJAX)
	 *
	 * @return ProcessResultDTO
	 */
	@RequestMapping(value="/editUserDept")
	public String editUserDept(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<UsrDeptInfoVO> resultDTO = new ProcessResultVO<UsrDeptInfoVO>();
		
		resultDTO.setResult(usrDeptInfoService.edit(vo));
		if(resultDTO.getResult() > 0) {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.edit.success"));
		} else {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultDTO);
	}

	/**
	 * 소속 삭제(AJAX)
	 *
	 * @return ProcessResultDTO
	 */
	@RequestMapping(value="/removeUserDept")
	public String removeUserDept(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultDTO = new ProcessResultVO<UsrDeptInfoVO>();
		
		resultDTO.setResult(usrDeptInfoService.remove(vo));
		if(resultDTO.getResult() > 0) {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.delete.success"));
		} else {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultDTO);
	}
	
	
	/**
     * @Method 설명 : 기업코드 중복체크
	 */
	@RequestMapping(value="/deptCdCheck")
	public String deptCdCheck(UsrDeptInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String result = usrDeptInfoService.deptCdCheck(vo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("isUseable", result);
		
		return JsonUtil.responseJson(response, map);
	}
	
	/**
     * @Method 설명 : 도로명주소 팝업 관련
	 */	
	@RequestMapping("/jusoDeptPopup")
    public String jusoDeptPopup(UsrDeptInfoVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setCharacterEncoding("UTF-8"); // 한글깨지면 주석제거
		
		String confmKey = Constants.KEY_SNS_JUSO; // 검색API 승인키
		String domain = Constants.JUSO_SITE_DOMAIN; // 인터넷망
		
		String resultType = "4"; // 검색결과 화면 출력유(1 : 도로명, 2 : 도로명+지번, 3 : 도로명+상세건물명, 4 : 도로명+지번+상세건물명)
		
		request.setAttribute("confmKey", confmKey);
		request.setAttribute("domain", domain);
		request.setAttribute("resultType", resultType);
		
        return "mng/user/dept/address_dept_road_popup";

    }
	/**
	 * 기업 등록 엑셀 팝
	 * @param UsrDeptInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addUserDeptExcelPop")
	public String addUserDeptExcelPop (UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");

		return "mng/user/dept/user_dept_write_excel_pop";
	}

	/**
	 * 기업 등록 샘플 엑셀 파일 다운로드
	 * @param UsrDeptInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sampleExcelDept")
	public String sampleExcelDept(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response)  {

		response.setHeader("Content-Disposition", "attachment;filename=add_dept_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			usrDeptInfoService.sampleExcelDownload(response.getOutputStream());
		} catch (Exception e) {

		}
		return null;
	}
	
	/**
	 * 엑셀 파일을 통한 기업 등록
	 * @param UsrDeptInfoVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deptExcelUpload")
	@ResponseBody
	public ProcessResultListVO<UsrDeptInfoVO> deptExcelUpload(UsrDeptInfoVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		ProcessResultListVO<UsrDeptInfoVO> result = new ProcessResultListVO<>();
		
		try {
			result = usrDeptInfoService.excelUploadDeptAdd(vo, vo.getFileName(), vo.getFilePath(), orgCd);
			result.setMessage("기업 등록에 성공했습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("기업 등록에 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 기업 등록에 실패했습니다.");
		}
		return result;
	}

	/**
	 * 소속관리 엑셀 업로드
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/excelUploadCheck")
	public String excelUploadCheck(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String orgCd = UserBroker.getUserOrgCd(request);
		//지역
		//List<SysCodeVO> areaCode = sysCodeService.listCode("AREA_CD").getReturnList();
		List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd, "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);

		/*//소속 타입
		List<SysCodeVO> deptTypeCode = sysCodeService.listCode("DEPT_TYPE_CD").getReturnList();
		request.setAttribute("deptTypeCode", deptTypeCode);*/

		ProcessResultListVO<UsrDeptInfoVO> resultList = null;
		resultList = usrDeptInfoService.excelUploadValidationCheck(fileName, filePath, orgCd);
		//request.setAttribute("errMsg", resultList.getReturnMessage());
		request.setAttribute("deptList", resultList.getReturnList());
		request.setAttribute("vo", vo);


		return "mng/user/dept/user_dept_write_excel_validate";
	}


	/**
	 * 소속관리 엑셀 업로드 , 단건 체크
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/deptUploadCheck")
	public String deptUploadCheck(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String errorCode = "";
		String regex = "^[0-9,-]*$";
		String orgCd = UserBroker.getUserOrgCd(request);
		boolean areaCd = false;
		if(ValidationUtils.isEmpty(vo.getDeptNm())) {
			errorCode += "|EMPTYDEPTNM";
		} else {
			if(ValidationUtils.isEmpty(vo.getDeptAddr())) {
				errorCode += "|EMPTYDEPTADDR";
			}
			if(vo.getDeptAddr().length() > 500) {
				errorCode += "|TYPEDEPTADDR";
			}
			if(ValidationUtils.isEmpty(vo.getAreaCd())) {
				errorCode += "|EMPTYAREACD";
			}
			if(ValidationUtils.isEmpty(vo.getPhoneno())) {
				errorCode += "|"+"EMPTYPHONENO";
			}
			/*if(ValidationUtils.isEmpty(userDeptInfoDTO.getFaxNo())) {
				errorCode += "|"+"EMPTYFAXNO";
			}*/
			if(!vo.getPhoneno().matches(regex)){
				errorCode += "|"+"TYPEPHONENO";
			}
			if(vo.getPhoneno().length() > 14) {
				errorCode += "|TYPEPHONENO";
			}
			if(ValidationUtils.isNotEmpty(vo.getFaxNo()) && !vo.getFaxNo().matches(regex)){
				errorCode += "|"+"TYPEFAXNO";
			}
			if(ValidationUtils.isNotEmpty(vo.getFaxNo()) &&  vo.getFaxNo().length() > 14) {
				errorCode += "|TYPEFAXNO";
			}
			List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd, "AREA_CD").getReturnList();
			for(OrgCodeVO sdto : areaCode) {
				if(sdto.getCodeCd().equals(vo.getAreaCd()) ){
					areaCd = true;
				}
			}
			if(!areaCd){
				errorCode += "|TYPEAREACD";
			}

		}
		vo.setErrorCode(errorCode);

		return JsonUtil.responseJson(response, vo);
	}

	/**
	 * 소속관리 엑셀 업로드 , 최종 업로드
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/deptUpload")
	public String deptUpload(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String[] chk = request.getParameterValues("chk");
		String[] lineNo = request.getParameterValues("lineNo");
		String[] deptNm = request.getParameterValues("deptNm");
		String[] deptAddr = request.getParameterValues("deptAddr");
		String[] areaCd = request.getParameterValues("areaCd");
		String[] phoneno = request.getParameterValues("phoneno");
		String[] faxNo = request.getParameterValues("faxNo");

		List<UsrDeptInfoVO> deptList = new ArrayList<UsrDeptInfoVO>();
		for(int i=0 ; i < deptNm.length; i++) {
			for(int j=0; j < chk.length; j++) {
				if(lineNo[i].equals(chk[j])) {
					UsrDeptInfoVO dpdto = new UsrDeptInfoVO();
					dpdto.setOrgCd(orgCd);
					dpdto.setDeptNm(deptNm[i]);
					dpdto.setDeptAddr(deptAddr[i]);
					dpdto.setAreaCd(areaCd[i]);
					dpdto.setPhoneno(phoneno[i]);
					dpdto.setFaxNo(faxNo[i]);
					dpdto.setUseYn("Y");
					deptList.add(dpdto);
				}
			}
		}

		ProcessResultVO<UsrDeptInfoVO> resultDTO = new ProcessResultVO<UsrDeptInfoVO>();

		try {
			usrDeptInfoService.addDeptBatch(deptList);
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.add.success"));
			resultDTO.setResult(1);
		} catch (Exception e) {
			resultDTO.setMessage(getMessage(request, "user.message.user.dept.add.failed"));
			resultDTO.setResult(-1);
		}

		return JsonUtil.responseJson(response, resultDTO);
	}

	/**
	 * 소속 관리 메인 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainDeptInfoPop")
	public String mainDeptInfoPop(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		String orgCd = UserBroker.getUserOrgCd(request);
		//지역
		//List<SysCodeVO> areaCode = sysCodeService.listCode("AREA_CD").getReturnList();
		List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd,"AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);

		/*//소속 타입
		List<SysCodeVO> deptTypeCode = sysCodeService.listCode("DEPT_TYPE_CD").getReturnList();
		request.setAttribute("deptTypeCode", deptTypeCode);*/
		
		request.setAttribute("vo", vo);

		return "mng/user/dept/user_dept_main_pop";
	}
	
	/**
	 * 소속 관리 메인 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listDeptPop")
	public String listDeptPop(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setPageScale(10);
		ProcessResultListVO<UsrDeptInfoVO> resultList = usrDeptInfoService.list(vo);

		request.setAttribute("userDeptInfoList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		
		return "mng/user/dept/user_dept_list_pop";
	}
	
	/**
	 * 소속 관리 메인 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listDeptJson")
	public String listDeptJson(UsrDeptInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		return JsonUtil
				.responseJson(response, usrDeptInfoService.list(vo));
	}

}

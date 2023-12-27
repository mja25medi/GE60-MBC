package egovframework.edutrack.web.manage.org;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.code.impl.ReserveStatusCode;
import egovframework.edutrack.comm.code.impl.SysCodeDto;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.org.fac.service.OrgFacCtgrVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacInfoVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacResVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacService;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("/mng/org/fac")
public class OrgFacManageController extends GenericController {

    /** service */
	@Autowired @Qualifier("orgFacService")
	private OrgFacService orgFacService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;
	
	@Autowired @Qualifier("bookmarkService")
	private BookmarkService bookmarkService;
	
	
	@RequestMapping("/listFacMain")
	public String listFacMain(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		OrgFacCtgrVO ctgrVO = new OrgFacCtgrVO();
		ctgrVO.setOrgCd(orgCd);
		List<OrgFacCtgrVO> ctgrList = orgFacService.listFacCtgr(ctgrVO);

		request.setAttribute("ctgrList", ctgrList);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/org/fac/fac_main";
	}

	@RequestMapping("/listFac")
	public String listFac(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<OrgFacInfoVO> resultList = orgFacService.listPageingFac(vo);
		request.setAttribute("facList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		vo.setCnt(resultList.getPageInfo().getTotalRecordCount());
		request.setAttribute("vo", vo);
		
		return "mng/org/fac/fac_list_div";
	}
	
	@RequestMapping("/itemUpdate")
	public String itemUpdate(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultVO<OrgFacInfoVO> result = new ProcessResultVO<>();
		try {
			String facCdArray = StringUtil.nvl(vo.getFacCdArray());
			for(String facCd : facCdArray.split(",")) {
				OrgFacInfoVO infoVO = new OrgFacInfoVO();
				infoVO.setFacCd(facCd);
				infoVO.setUseYn(vo.getUseYn());
				infoVO.setModNo(vo.getModNo());
				orgFacService.updateFacUse(infoVO);
			}
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("시설 사용정보 수정 도중 오류가 발생했습니다.");
		}
		return JsonUtil.responseJson(response, result);
	}
	
	@RequestMapping("/addFacFormPop")
	public String addFacFormPop(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		OrgFacCtgrVO ctgrVO = new OrgFacCtgrVO();
		ctgrVO.setOrgCd(orgCd);
		ctgrVO.setUseYn("Y");
		List<OrgFacCtgrVO> ctgrList = orgFacService.listFacCtgr(ctgrVO);
		request.setAttribute("ctgrList", ctgrList);
		
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "mng/org/fac/add_fac_form";
	}

	@RequestMapping("/addFac")
	public String addFac(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		ProcessResultVO<OrgFacInfoVO> result = new ProcessResultVO<OrgFacInfoVO>();
		try {
			orgFacService.addFac(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("시설 정보 등록 중 오류가 발생했습니다.");
		}
		return JsonUtil.responseJson(response, result);
	}

	@RequestMapping("/editFacFormPop")
	public String editFacFormPop(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		OrgFacCtgrVO ctgrVO = new OrgFacCtgrVO();
		ctgrVO.setOrgCd(orgCd);
		List<OrgFacCtgrVO> ctgrList = orgFacService.listFacCtgr(ctgrVO);
		request.setAttribute("ctgrList", ctgrList);
		
		request.setAttribute("vo", orgFacService.viewFac(vo));
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		
		return "mng/org/fac/add_fac_form";
	}
	
	@RequestMapping("/editFac")
	public String facEdit(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgFacInfoVO> result = new ProcessResultVO<>();
		try {
			orgFacService.updateFac(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("시설 정보 수정 중 오류가 발생했습니다.");
		}
		return JsonUtil.responseJson(response, result);
	}

	@RequestMapping("/deleteFac")
	public String deleteFac(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgFacInfoVO> result = new ProcessResultVO<OrgFacInfoVO>();
		try {
			orgFacService.deleteFac(vo);
			result.setResult(1);
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("시설 정보 삭제 중 오류가 발생했습니다.");
		}
		return JsonUtil.responseJson(response, result);
	}
	
	@RequestMapping("/mngFacCtgrFormPop")
	public String mngFacCtgrFormPop(OrgFacCtgrVO vo, HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		List<OrgFacCtgrVO> ctgrList = orgFacService.listFacCtgr(vo);
		request.setAttribute("ctgrList", ctgrList);
		
		return "mng/org/fac/mng_fac_ctgr_form";
	}
	
	@ResponseBody
	@RequestMapping("/viewFacCtgr")
	public ProcessResultVO<OrgFacCtgrVO> viewFacCtgr(OrgFacCtgrVO vo, HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgFacCtgrVO> result = new ProcessResultVO<>();
		try {
			OrgFacCtgrVO orgFacCtgr = orgFacService.viewFacCtgr(vo);
			result.setReturnVO(orgFacCtgr);
			result.setResultSuccess();
		} catch(MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultSuccess();
		} catch(Exception e) {
			result.setMessage("시설 분류 조회 중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/addFacCtgr")
	public ProcessResultVO<OrgFacCtgrVO> addFacCtgr(OrgFacCtgrVO vo, HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgFacCtgrVO> result = new ProcessResultVO<>();
		try {
			orgFacService.addFacCtgr(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch(Exception e) {
			result.setMessage("시설 분류 등록 중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/editFacCtgr")
	public ProcessResultVO<OrgFacCtgrVO> editFacCtgr(OrgFacCtgrVO vo, HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgFacCtgrVO> result = new ProcessResultVO<>();
		try {
			orgFacService.updateFacCtgr(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch(Exception e) {
			result.setMessage("시설 분류 수정 중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteFacCtgr")
	public ProcessResultVO<OrgFacCtgrVO> deleteFacCtgr(OrgFacCtgrVO vo, HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgFacCtgrVO> result = new ProcessResultVO<>();
		try {
			orgFacService.deleteFacCtgr(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch(MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultSuccess();
		} catch(Exception e) {
			result.setMessage("시설 분류 삭제 중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@RequestMapping("/listResMain")
	public String listResMain(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(vo.getWorkStartDate() == null && vo.getWorkEndDate() == null) {
			LocalDate today = LocalDate.now();
			LocalDate startDt = today.withDayOfMonth(1);
			LocalDate endDt = today.withDayOfMonth(today.lengthOfMonth());
			vo.setWorkStartDate(String.valueOf(startDt));
			vo.setWorkEndDate(String.valueOf(endDt));
		}
		
		//시설 상태 코드 조회
		List<SysCodeDto> stsCodeList = Arrays.stream(ReserveStatusCode.values())
										.filter(ReserveStatusCode::hasValue)
										.map(SysCodeDto::new)
										.collect(Collectors.toList());
		
		request.setAttribute("stsCodeList", stsCodeList);
		
		//시설 분류
		OrgFacInfoVO facVo = new OrgFacInfoVO();
		facVo.setOrgCd(vo.getOrgCd());
		ProcessResultListVO<OrgFacInfoVO> faclist = new ProcessResultListVO<>();
		faclist = orgFacService.listFac(facVo);
		request.setAttribute("faclist", faclist.getReturnList());
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/org/fac/fac_res_main";
	}

	@RequestMapping("/listRes")
	public String listRes(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		String tmpStartDt = null;
		String tmpEndDt = null;
		tmpStartDt = vo.getWorkStartDate().toString();
		tmpEndDt = vo.getWorkEndDate().toString();
		
		if(tmpStartDt==null && tmpEndDt==null || tmpStartDt=="" && tmpEndDt=="") {
			//오늘 날짜 기준으로 1달의 시작일자와 끝일자를 알아온다.
			LocalDate today = LocalDate.now();
			LocalDate startDt = today.withDayOfMonth(1);
			LocalDate endDt = today.withDayOfMonth(today.lengthOfMonth());
			vo.setWorkStartDate(String.valueOf(startDt));
			vo.setWorkEndDate(String.valueOf(endDt));
		}
		
		//시설 대관 조회
		ProcessResultListVO<OrgFacResVO> resultList = orgFacService.listPageingRes(vo);
		request.setAttribute("facList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		vo.setCnt(resultList.getPageInfo().getTotalRecordCount());
		request.setAttribute("vo", vo);
		
		return "mng/org/fac/fac_res_list_div";
	}	

	@RequestMapping("/editRes")
	public String editRes(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<OrgFacInfoVO> result = new ProcessResultVO<>();
		try {
			//시설 예약 정보업데이트.
			orgFacService.updateRes(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	@RequestMapping("/viewResInfoPop")
	public String viewResInfoPop(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		vo = orgFacService.viewRes(vo);
		
		List<SysCodeDto> stsCodeList = Arrays.stream(ReserveStatusCode.values())
										.filter(ReserveStatusCode::hasValue)
										.map(SysCodeDto::new)
										.collect(Collectors.toList());
										
		request.setAttribute("stsCodeList", stsCodeList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		
		return "mng/org/fac/fac_res_info_view";
	}
	
	
	/**
	 * QR코드 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/printQR")
	public String printCert(OrgFacResVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String msie6 = "N";
		String userBr = request.getHeader("User-Agent");
		if(userBr.indexOf("MSIE 6") > 0 || userBr.indexOf("MSIE 7") > 0) msie6 = "Y";
		request.setAttribute("msie6", msie6);
		
		String imgName = "qrFac.png";
		request.setAttribute("imgName", imgName);
		request.setAttribute("vo", vo);
		return "mng/org/fac/print_qr_pop";
	}
	
	/**
	 * QR코드 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sortFacOdr")
	public ProcessResultVO<OrgFacInfoVO> sortFacOdr(OrgFacInfoVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<OrgFacInfoVO> result = new ProcessResultVO<>();
		try {
			orgFacService.updateFacInfoOdr(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResultFailed();
			result.setMessage("순서 변경 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	
	@RequestMapping("/resExcelDownload")
	public String resExcelDownload(OrgFacResVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment;filename=FacResList.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		orgFacService.resListExcelDownload(vo, response.getOutputStream());
		
		return null;
	}
}

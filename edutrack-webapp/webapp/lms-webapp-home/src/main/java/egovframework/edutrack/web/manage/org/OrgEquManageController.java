package egovframework.edutrack.web.manage.org;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.code.CodeType;
import egovframework.edutrack.comm.code.impl.RentStatusCode;
import egovframework.edutrack.comm.code.impl.SysCodeDto;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.equ.service.OrgEquItemVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquService;
import egovframework.edutrack.modules.org.equ.service.OrgEquVO;

@Controller
@RequestMapping("/mng/org/equ")
public class OrgEquManageController extends GenericController {

	@Autowired @Qualifier("orgEquService")
	private OrgEquService orgEquService;
	
	
	@RequestMapping("/listEquMain")
	public String listEquMain(OrgEquVO vo, HttpServletRequest request) throws Exception {
		
		request.setAttribute("paging", "Y");
		
		return "mng/org/equ/equ_main";
	}
	
	@RequestMapping("/listEqu")
	public String listEqu(OrgEquVO vo, HttpServletRequest request) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<OrgEquVO> resultList = orgEquService.listPagingEquipment(vo);
		request.setAttribute("equList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "mng/org/equ/equ_list_div";
	}
	
	@ResponseBody
	@RequestMapping("/equUseUpdate")
	public ProcessResultVO<OrgEquVO> equUseUpdate(OrgEquVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String equCdArray = StringUtil.nvl(vo.getEquCdArray());
		ProcessResultVO<OrgEquVO> result = new ProcessResultVO<>();
		try {
			for(String equCd : equCdArray.split(",")) {
				OrgEquVO equVO = new OrgEquVO();
				equVO.setEquCd(equCd);
				equVO.setUseYn(vo.getUseYn());
				equVO.setModNo(vo.getModNo());
				orgEquService.updateEquipmentUse(equVO);
			}
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("예약 정보 수정 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/addEquFormPop")
	public String addEquFormPop(OrgEquVO vo, HttpServletRequest request) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/org/equ/add_equ_form";
	}
	
	@ResponseBody
	@RequestMapping("/addEqu")
	public ProcessResultVO<OrgEquVO> addEqu(OrgEquVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquVO> result = new ProcessResultVO<>();
		try {
			orgEquService.addEquipment(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("장비 정보 등록 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/editEquFormPop")
	public String editEquFormPop(OrgEquVO vo, HttpServletRequest request) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		OrgEquVO equInfo = orgEquService.viewEquipment(vo);
		
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", equInfo);
		
		return "mng/org/equ/add_equ_form";
	}
	
	@ResponseBody
	@RequestMapping("/editEqu")
	public ProcessResultVO<OrgEquVO> editEqu(OrgEquVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquVO> result = new ProcessResultVO<>();
		try {
			orgEquService.updateEquipment(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("장비 정보 수정 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteEqu")
	public ProcessResultVO<OrgEquVO> deleteEqu(OrgEquVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquVO> result = new ProcessResultVO<>();
		try {
			orgEquService.deleteEquipment(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("장비 정보 삭제 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/listItemMain")
	public String listItemMain(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		
		OrgEquVO equInfo = new OrgEquVO();
		equInfo.setEquCd(vo.getEquCd());
		equInfo = orgEquService.viewEquipment(equInfo);
		
		request.setAttribute("equInfo", equInfo);
		request.setAttribute("paging", "Y");
		
		return "mng/org/equ/equ_item_main";
	}
	
	@RequestMapping("/listItem")
	public String listItem(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		
		ProcessResultListVO<OrgEquItemVO> resultList = orgEquService.listPagingItem(vo);
		request.setAttribute("itemList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "mng/org/equ/equ_item_list_div";
	}
	
	@ResponseBody
	@RequestMapping("/equItemUseUpdate")
	public ProcessResultVO<OrgEquItemVO> equItemUseUpdate(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String itemCdArray = StringUtil.nvl(vo.getItemCdArray());
		ProcessResultVO<OrgEquItemVO> result = new ProcessResultVO<>();
		try {
			for(String itemCd : itemCdArray.split(",")) {
				OrgEquItemVO itemVO = new OrgEquItemVO();
				itemVO.setItemCd(itemCd);
				itemVO.setUseYn(vo.getUseYn());
				itemVO.setModNo(vo.getModNo());
				orgEquService.updateItemUse(itemVO);
			}
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("예약 정보 수정 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/addItemFormPop")
	public String addItemFormPop(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/org/equ/add_equ_item_form";
	}
	
	@ResponseBody
	@RequestMapping("/addItem")
	public ProcessResultVO<OrgEquItemVO> addItem(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquItemVO> result = new ProcessResultVO<>();
		try {
			orgEquService.addItem(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("상품 정보 등록 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/editItemFormPop")
	public String editItemFormPop(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		
		OrgEquItemVO itemInfo = orgEquService.viewItem(vo);
		
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", itemInfo);
		
		return "mng/org/equ/add_equ_item_form";
	}
	
	@ResponseBody
	@RequestMapping("/editItem")
	public ProcessResultVO<OrgEquItemVO> editItem(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquItemVO> result = new ProcessResultVO<>();
		try {
			orgEquService.updateItem(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("상품 정보 수정 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteItem")
	public ProcessResultVO<OrgEquItemVO> deleteItem(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<OrgEquItemVO> result = new ProcessResultVO<>();
		try {
			orgEquService.deleteItem(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(MediopiaDefineException e) {
			result.setResultFailed();
			result.setMessage(e.getMessage());
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("상품 정보 삭제 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/listRentMain")
	public String listRentMain(OrgEquRentVO vo, HttpServletRequest request) throws Exception {

		List<SysCodeDto> rentStsCdList = Arrays.stream(RentStatusCode.values())
				.filter(CodeType::hasValue)
				.map(SysCodeDto::new)
				.collect(Collectors.toList());
		
		request.setAttribute("rentStsCdList", rentStsCdList);
		
		LocalDate today = LocalDate.now();
		LocalDate startDt = today.withDayOfMonth(1);
		LocalDate endDt = today.withDayOfMonth(today.lengthOfMonth());
		request.setAttribute("startDt", String.valueOf(startDt));
		request.setAttribute("endDt", String.valueOf(endDt));
		
		request.setAttribute("paging", "Y");
		
		return "mng/org/equ/equ_rent_main";
	}
	
	@RequestMapping("/listRent")
	public String listRent(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		
		ProcessResultListVO<OrgEquRentVO> resultList = orgEquService.listPagingRent(vo);
		request.setAttribute("rentInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "mng/org/equ/equ_rent_list_div";
	}
	
	@RequestMapping("/viewRentInfoPop")
	public String viewRentInfoPop(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		
		OrgEquRentVO rentInfo = orgEquService.viewRent(vo);
		request.setAttribute("rentInfo", rentInfo);
		
		OrgEquItemVO itemVO = new OrgEquItemVO();
		itemVO.setEquCd(rentInfo.getEquCd());
		itemVO.setRentCd(rentInfo.getRentCd());
		itemVO.setRentStartDttm(rentInfo.getRentStartDttm());
		itemVO.setRentEndDttm(rentInfo.getRentEndDttm());
		itemVO.setUseYn("Y");
		
		List<OrgEquItemVO> itemList = orgEquService.validListItem(itemVO);
		request.setAttribute("itemList", itemList);
		
		List<SysCodeDto> rentStsCdList = Arrays.stream(RentStatusCode.values())
										.filter(CodeType::hasValue)
										.map(SysCodeDto::new)
										.collect(Collectors.toList());
		
		request.setAttribute("rentStsCdList", rentStsCdList);
		
		return "mng/org/equ/equ_rent_info_view";
	}
	
	@ResponseBody
	@RequestMapping("/editRent")
	public ProcessResultVO<OrgEquRentVO> editRent(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String itemCds = StringUtil.nvl(vo.getItemCds());
		vo.transDetailList(itemCds.split(","));
		
		ProcessResultVO<OrgEquRentVO> result = new ProcessResultVO<>();
		try {
			orgEquService.updateRent(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("예약 정보 수정 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delRent")
	public ProcessResultVO<OrgEquRentVO> delRent(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquRentVO> result = new ProcessResultVO<>();
		try {
			orgEquService.deleteRent(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("예약 정보 삭제 도중 오류가 발생했습니다.");
		}
		return result;
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
	public String printCert(OrgEquRentVO vo, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String msie6 = "N";
		String userBr = request.getHeader("User-Agent");
		if(userBr.indexOf("MSIE 6") > 0 || userBr.indexOf("MSIE 7") > 0) msie6 = "Y";
		request.setAttribute("msie6", msie6);
		
		String imgName = "qrEqu.png";
		request.setAttribute("imgName", imgName);
		request.setAttribute("vo", vo);
		return "mng/org/fac/print_qr_pop";	//시설 QR출력과 같은 jsp 사용
	}
	
	/**
	 * QR코드 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sortEquOdr")
	public ProcessResultVO<OrgEquVO> sortEquOdr(OrgEquVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquVO> result = new ProcessResultVO<>();
		try {
			orgEquService.updateEquOdr(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch (Exception e) {
			result.setResultFailed();
			result.setMessage("순서 변경 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	
	@RequestMapping("/addItemExcelPop")
	public String addItemExcelPop(OrgEquItemVO vo, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "A");
		return "mng/org/equ/add_item_excel_pop";
	}
	
	@RequestMapping("/sampleExcelItemAdd")
	public String sampleExcelUserAdd(OrgEquItemVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		commonVOProcessing(vo, request);
		
		response.setHeader("Content-Disposition", "attachment;filename=add_item_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		orgEquService.sampleExcelDownload(response.getOutputStream());
		
		return null;
	}
	
	@RequestMapping("/itemExcelUpload")
	@ResponseBody
	public ProcessResultListVO<OrgEquItemVO> itemExcelUpload(OrgEquItemVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String equCd = vo.getEquCd();
		ProcessResultListVO<OrgEquItemVO> result = new ProcessResultListVO<>();
		try {
			orgEquService.excelUploadItemAdd(vo, vo.getFileName(), vo.getFilePath(), equCd);
			result.setResult(1);
			result.setMessage("상품 등록에 성공했습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("상품 등록에 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 상품 등록에 실패했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/rentExcelDownload")
	public String rentExcelDownload(OrgEquRentVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment;filename=EquRentList.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		orgEquService.rentListExcelDownload(vo, response.getOutputStream());
		
		return null;
	}

}

package egovframework.edutrack.web.home.org;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.exception.AjaxIllegalFormatException;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.equ.service.OrgEquRentVO;
import egovframework.edutrack.modules.org.equ.service.OrgEquService;
import egovframework.edutrack.modules.org.equ.service.OrgEquVO;

@Controller
@RequestMapping(value="/home/org/equ")
public class OrgEquHomeController extends GenericController {

	@Autowired @Qualifier("orgEquService")
	private OrgEquService orgEquService;
	
	@RequestMapping(value="/listEquMain")
	public String equListMain(OrgEquVO vo, HttpServletRequest request) {
		
		return "home/org/equ/equ_main";
	}
	
	@RequestMapping(value="/listEqu")
	public String equList(OrgEquVO vo, HttpServletRequest request) {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUseYn("Y");
		vo.setListScale(2);
		
		boolean fileIn = true;
		ProcessResultListVO<OrgEquVO> result = orgEquService.listPagingEquipment(vo, fileIn); 
		request.setAttribute("equList", result.getReturnList());
		request.setAttribute("pageInfo", result.getPageInfo());
		
		return "home/org/equ/equ_list_div";
	}
	
	@RequestMapping(value="/rentCalMain")
	public String rentCalMain(OrgEquVO vo, HttpServletRequest request) throws Exception {
		OrgEquVO equInfo = orgEquService.viewEquipment(vo);
		
		OrgEquRentVO rentVO = new OrgEquRentVO();
		rentVO.setEquCd(equInfo.getEquCd());
		rentVO.setRentStartDttm(DateTimeUtil.getDateTime());
		
		List<OrgEquRentVO> rentInfoList = orgEquService.listRent(rentVO).stream()
											.filter(OrgEquRentVO::isValidRent)
											.peek(OrgEquRentVO::parseRentDttmForCalendar)
											.limit(100)
											.collect(toList());
		
		request.setAttribute("rentInfoList", rentInfoList);
		request.setAttribute("equInfo", equInfo);
		
		return "home/org/equ/equ_rent_cal_main";
	}
	
	
	@RequestMapping(value="/rentCurrMain")
	public String rentListMain(OrgEquVO vo, HttpServletRequest request) throws Exception {
		OrgEquRentVO rentVO = new OrgEquRentVO();
		rentVO.setRentStartDttm(DateTimeUtil.getDateTime());
		
		Map<String, List<OrgEquRentVO>> rentInfoListMap = orgEquService.listRent(rentVO).stream()
											.filter(OrgEquRentVO::isValidRent)
											.peek(OrgEquRentVO::parseRentDttmForCalendar)
											.limit(100)
											.collect(groupingBy(OrgEquRentVO::getEquCd));
		
		request.setAttribute("rentInfoListMap", rentInfoListMap);
		
		return "home/org/equ/equ_rent_curr_main";
	}
	
	@RequestMapping(value="/addRentFormPop")
	public String addRentFormPop(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		OrgEquVO equVO = new OrgEquVO();
		equVO.setEquCd(vo.getEquCd());
		
		OrgEquVO equInfo = orgEquService.viewEquipment(equVO);
		request.setAttribute("equInfo", equInfo);
		
		return "home/org/equ/add_equ_rent_form";
	}
	
	@ResponseBody
	@RequestMapping(value="/addRent")
	public ProcessResultVO<OrgEquRentVO> addRent(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		vo.setUserNo(UserBroker.getUserNo(request));

		ProcessResultVO<OrgEquRentVO> result = new ProcessResultVO<>();
		try {
			checkRentFormat(vo);
			orgEquService.addRent(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch(MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultFailed();
		} catch (Exception e) {
			result.setMessage("예약 등록 도중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/chkEnableItemCnt")
	public ProcessResultVO<Integer> chkEnableItemCnt(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		ProcessResultVO<Integer> result = new ProcessResultVO<>();
		try {
			int enableCnt = orgEquService.chkEnableItemCnt(vo);
			if(enableCnt > 0) {
				result.setMessage("예약이 가능합니다.");
				result.setReturnVO(enableCnt);
				result.setResultSuccess();			
			} else {
				result.setMessage("해당 기간에 대여 가능한 장비가 없습니다.");
				result.setResultFailed();			
			}			
		} catch(MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultFailed();
		}
		return result;
	}
	
	@RequestMapping(value="/listRentMain")
	public String listRentMain(OrgEquRentVO vo, HttpServletRequest request) {
		if(ValidationUtils.isEmpty(UserBroker.getUserNo(request))) {
			setAlertMessage(request, "로그인이 필요한 서비스입니다.");
			return "redirect:/home/main/goMenuPage?mcd=HM04001000";			
		}
		return "home/org/equ/equ_rent_main";
	}
	
	@RequestMapping(value="/listRent")
	public String listRent(OrgEquRentVO vo, HttpServletRequest request) {
		vo.setUserNo(UserBroker.getUserNo(request));
		
		ProcessResultListVO<OrgEquRentVO> result = orgEquService.listPagingRent(vo);
		request.setAttribute("rentInfoList", result.getReturnList());
		request.setAttribute("pageInfo", result.getPageInfo());
		
		return "home/org/equ/equ_rent_list_div";
	}
	
	@RequestMapping(value="/viewRentInfoPop")
	public String viewRentInfoPop(OrgEquRentVO vo, HttpServletRequest request) throws Exception {
		OrgEquRentVO rentInfo = orgEquService.viewRent(vo);
		request.setAttribute("rentInfo", rentInfo);
		
		return "home/org/equ/equ_rent_info_view";
	}
	
	@ResponseBody
	@RequestMapping(value="/cancelRentRequest")
	public ProcessResultVO<OrgEquRentVO> cancelRentRequest(OrgEquRentVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgEquRentVO> result = new ProcessResultVO<>();
		try {
			orgEquService.cancelRent(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch (MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultFailed();
		} catch (Exception e) {
			result.setMessage("대여 신청 취소 도중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	private void checkRentFormat(OrgEquRentVO vo) throws AjaxIllegalFormatException {
		if(ValidationUtils.isEmpty(vo.getRentStartDttm()) || ValidationUtils.isEmpty(vo.getRentEndDttm())) throw new AjaxIllegalFormatException("대여 기간을 선택해주세요.");
		if(ValidationUtils.isEmpty(vo.getRentCnt())) throw new AjaxIllegalFormatException("장비대여수를 입력해주세요.");
		if(ValidationUtils.isEmpty(vo.getRentDesc())) throw new AjaxIllegalFormatException("대여 목적을 입력해주세요.");
	}
}

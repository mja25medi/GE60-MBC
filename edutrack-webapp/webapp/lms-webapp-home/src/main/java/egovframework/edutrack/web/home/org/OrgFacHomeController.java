package egovframework.edutrack.web.home.org;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
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

import egovframework.edutrack.comm.code.impl.ReserveStatusCode;
import egovframework.edutrack.comm.code.impl.SysCodeDto;
import egovframework.edutrack.comm.exception.AjaxIllegalFormatException;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.fac.service.OrgFacCtgrVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacInfoVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacResVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacService;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.system.code.service.SysCodeService;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/org/fac")
public class OrgFacHomeController extends GenericController {
	
    /** service */
	@Autowired @Qualifier("orgFacService")
	private OrgFacService orgFacService;

	@Autowired @Qualifier("sysCodeService")
	private SysCodeService sysCodeService;
	
	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;
	

	@RequestMapping(value="/listFacMain")
	public String listFacMain(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		OrgFacCtgrVO ctgrVO = new OrgFacCtgrVO();
		ctgrVO.setOrgCd(orgCd);
		ctgrVO.setUseYn("Y");
		List<OrgFacCtgrVO> ctgrList = orgFacService.listFacCtgr(ctgrVO);
		request.setAttribute("ctgrList", ctgrList);
		
		return "home/org/fac/fac_main";
	}
	
	@RequestMapping(value="/listFac")
	public String listFac(OrgFacInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		vo.setListScale(2);
		vo.setUseYn("Y");
		
		ProcessResultListVO<OrgFacInfoVO> resultList = orgFacService.listInfoPageing(vo,true);
		List<OrgFacInfoVO> facInfoList = resultList.getReturnList();
		request.setAttribute("facInfoList", facInfoList);
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		
		return "home/org/fac/fac_list_div";
	}
	
	@RequestMapping(value="/resCalMain")
	public String resCalMain(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		OrgFacInfoVO facInfoVO = new OrgFacInfoVO();
		facInfoVO.setFacCd(vo.getFacCd());
		
		OrgFacInfoVO facInfo = orgFacService.viewFac(facInfoVO);
		request.setAttribute("facInfo", facInfo);
		
		vo.setWorkStartDate(DateTimeUtil.getDateTime());
		List<OrgFacResVO> resInfoList = orgFacService.listRes(vo).stream()
											.filter(OrgFacResVO::isValidRes)
											.limit(100)
											.collect(toList());
		
		request.setAttribute("resInfoList", resInfoList);

		return "home/org/fac/fac_res_cal_main";
	}
	
	@RequestMapping(value="/resCurrMain")
	public String resCurrMain(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		vo.setWorkStartDate(DateTimeUtil.getDateTime());
		
		Map<String, List<OrgFacResVO>> resInfoListMap = orgFacService.listRes(vo).stream()
											.filter(OrgFacResVO::isValidRes)
											.limit(100)
											.collect(groupingBy(OrgFacResVO::getFacCd));
		
		request.setAttribute("resInfoListMap", resInfoListMap);

		return "home/org/fac/fac_res_curr_main";
	}

	@RequestMapping(value="/addResFormPop")
	public String addResFormPop(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		OrgFacInfoVO infoVO = new OrgFacInfoVO();
		infoVO.setFacCd(vo.getFacCd());
		infoVO.setOrgCd(UserBroker.getUserOrgCd(request));
		OrgFacInfoVO facInfo = orgFacService.viewFac(infoVO);
		 
		request.setAttribute("vo", vo);
		request.setAttribute("facInfo", facInfo);
		
		return "home/org/fac/add_fac_res_form";
	}
	
	@ResponseBody
	@RequestMapping(value="/addRes")
	public ProcessResultVO<OrgFacResVO> addRes(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		ProcessResultVO<OrgFacResVO> result = new ProcessResultVO<>();
		try {
			checkResFormat(vo);
			orgFacService.addRes(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch (MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultFailed();
		} catch (Exception e) {
			result.setMessage("시설 대관 예약 도중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/chkResTime")
	public ProcessResultVO<OrgFacResVO> chkResTime(OrgFacResVO vo, HttpServletRequest request) throws Exception {
		ProcessResultVO<OrgFacResVO> result = new ProcessResultVO<>();
		try {
			orgFacService.validateReserveTime(vo);
			result.setMessage("예약 가능한 시간대 입니다.");
			result.setResultSuccess();
		} catch(MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultFailed();			
		} catch(Exception e) {
			result.setMessage("예약 가능 시간 조회 중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	@RequestMapping(value="/listResMain")
	public String listResMain(OrgFacResVO vo, HttpServletRequest request) throws Exception {
		if(ValidationUtils.isEmpty(UserBroker.getUserNo(request))) {
			setAlertMessage(request, "로그인이 필요한 서비스입니다.");
			return "redirect:/home/main/goMenuPage?mcd=HM04001000";			
		}
		return "home/org/fac/fac_res_main";
	}

	@RequestMapping(value="/listRes")
	public String listRes(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(UserBroker.getUserNo(request));

		ProcessResultListVO<OrgFacResVO> resultList = orgFacService.listPageingRes(vo);
		List<SysCodeDto> resCodeList = Arrays.stream(ReserveStatusCode.values())
											.filter(ReserveStatusCode::hasValue)
											.map(SysCodeDto::new)
											.collect(toList());
		
		request.setAttribute("resInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		request.setAttribute("resCodeList", resCodeList);
		
		return "home/org/fac/fac_res_list_div";
	}

	@RequestMapping(value="/viewResInfoPop")
	public String viewResInfoPop(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo = orgFacService.viewRes(vo);
		request.setAttribute("resInfo", vo);
		
		return "home/org/fac/fac_res_info_view";
	}

	@ResponseBody
	@RequestMapping(value="/cancelResWait")
	public ProcessResultVO<OrgFacResVO> cancelResWait(OrgFacResVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		ProcessResultVO<OrgFacResVO> result = new ProcessResultVO<>();
		try {
			orgFacService.cancelRes(vo);
			result.setMessage("정상 처리 되었습니다.");
			result.setResultSuccess();
		} catch (MediopiaDefineException e) {
			result.setMessage(e.getMessage());
			result.setResultFailed();
		} catch (Exception e) {
			result.setMessage("예약 취소 도중 오류가 발생했습니다.");
			result.setResultFailed();
		}
		return result;
	}
	
	private void checkResFormat(OrgFacResVO vo) throws AjaxIllegalFormatException {
		if(ValidationUtils.isEmpty(vo.getUserNo())) throw new AjaxIllegalFormatException("세션이 만료되었습니다.");
		if(ValidationUtils.isEmpty(vo.getResDt())) throw new AjaxIllegalFormatException("예약 날짜를 선택해주세요.");
		if(ValidationUtils.isEmpty(vo.getResStm()) || ValidationUtils.isEmpty(vo.getResEtm())) throw new AjaxIllegalFormatException("시간을 선택해주세요.");
		if(ValidationUtils.isEmpty(vo.getEventDesc())) throw new AjaxIllegalFormatException("사용목적을 입력해주세요.");
		if(ValidationUtils.isEmpty(vo.getAttCnt())) throw new AjaxIllegalFormatException("사용인원을 입력해주세요.");
		if(vo.getAttCnt() <= 0) throw new AjaxIllegalFormatException("사용인원은 0보다 커야합니다.");
	}
}

package egovframework.edutrack.web.manage.course;

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

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshService;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrService;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultService;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankService;
import egovframework.edutrack.modules.course.research.service.ResearchBankVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/creCrs/resh")
public class CreCrsReshManageController extends GenericController {

	@Autowired @Qualifier("creCrsReshService")
	private CreCrsReshService		creCrsReshService;

	@Autowired @Qualifier("researchBankService")
	private ResearchBankService	researchBankService;

	@Autowired @Qualifier("crsReshResultService")
	private CrsReshResultService	crsReshResultService;

	@Autowired @Qualifier("crsReshAnsrService")
	private CrsReshAnsrService		crsReshAnsrService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService 			logPrivateInfoService;

	/**
	 * 개설 과정 설문 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		return "mng/course/crecrsresh/resh_main";
	}

	/**
	 * 개설 과정 설문 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<CreCrsReshVO> creCrsReshList = creCrsReshService.list(vo).getReturnList();

		request.setAttribute("creCrsReshVO", vo);
		request.setAttribute("creCrsReshList", creCrsReshList);
		return "mng/course/crecrsresh/resh_list_div";
	}

	/**
	 * 개설 과정 설문 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPop")
	public String addPop( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		ResearchBankVO researchBankVO = new ResearchBankVO();
		researchBankVO.setOrgCd(orgCd);
		researchBankVO.setRegYn("");
		//문제등록이 0건인 설문은 조회가 안되도록함
		researchBankVO.setSearchCnt("Y");

		List<ResearchBankVO> researchBankList = researchBankService.listResearchPreclusive(researchBankVO).getReturnList();
		request.setAttribute("researchBankList", researchBankList);

		vo.setUseYn("Y");
		vo.setRegYn("Y");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/course/crecrsresh/resh_write_pop";
	}

	/**
	 * 개설 과정 설문 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CreCrsReshVO> resultVO = creCrsReshService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.resh.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.resh.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 설문 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPop")
	public String editPop( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = creCrsReshService.view(vo).getReturnVO();
		if(ValidationUtils.isNotEmpty(vo.getStartDttm()))
			vo.setStartDttm(DateTimeUtil.getDateText(1, vo.getStartDttm().substring(0,8),"."));
		if(ValidationUtils.isNotEmpty(vo.getEndDttm()))
			vo.setEndDttm(DateTimeUtil.getDateText(1, vo.getEndDttm().substring(0,8),"."));

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/course/crecrsresh/resh_write_pop";
	}

	/**
	 * 개설 과정 설문 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<CreCrsReshVO> resultVO = creCrsReshService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.resh.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.resh.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 설문 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/remove")
	public String remove( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<CreCrsReshVO> resultVO = creCrsReshService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.resh.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.resh.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 설문 결과 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rsltPop")
	public String rsltPop( CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 설문 정보 검색
		vo = creCrsReshService.view(vo).getReturnVO();

		CrsReshResultVO crsReshResultVO = new CrsReshResultVO();
		crsReshResultVO.setCrsCreCd(vo.getCrsCreCd());
		crsReshResultVO.setReshSn(vo.getReshSn());

		List<CrsReshResultVO> crsReshResultList = crsReshResultService.list(crsReshResultVO).getReturnList();

		request.setAttribute("crsReshResultVO", crsReshResultVO);
		request.setAttribute("vo", vo);
		request.setAttribute("crsReshResultList", crsReshResultList);
		return "mng/course/crecrsresh/resh_result_pop";
	}

	/**
	 * 개설 과정 설문 결과 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainOpinionPop")
	public String mainOpinionPop( CrsReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		//crsReshAnsrVO.setCrsCreCd(crsCreCd);

		//-- 설문 문제 정보 검색
		ResearchBankItemVO reshBankItemVO = new ResearchBankItemVO();
		reshBankItemVO.setReshSn(vo.getReshSn());
		reshBankItemVO.setReshItemSn(vo.getReshItemSn());
		reshBankItemVO = researchBankService.viewItem(reshBankItemVO).getReturnVO();
		request.setAttribute("reshBankItemVO", reshBankItemVO);

		List<CrsReshAnsrVO> crsReshResultList = crsReshAnsrService.listOpinion(vo).getReturnList();

		request.setAttribute("vo", vo);
		request.setAttribute("crsReshAnsrList", crsReshResultList);
		return "mng/course/crecrsresh/resh_result_opinion";
	}

	/**
	 * 개설과정관리/설문결과 다운로드
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/reshExcelDownload")
	public String reshExcelDownload( CrsReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String excelHeader = StringUtil.nvl(request.getParameter("excelHeader"),"");
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"),"");
		Integer reshSn = Integer.parseInt(StringUtil.nvl(request.getParameter("reshSn"),"0"));
		String orgCd = UserBroker.getUserOrgCd(request);
		String printMode = StringUtil.nvl(request.getParameter("printMode"),"EXCEL");
		String fileNamePrefix = "Resh_Result";
		String sheetName = getMessage(request, "course.title.reshbank.answer.result");

		vo.setCrsCreCd(crsCreCd);
		vo.setReshSn(reshSn);

		//-- 개설 과정 설문정보를 가져온다
		CreCrsReshVO creCrsReshVO = new CreCrsReshVO();
		creCrsReshVO.setCrsCreCd(crsCreCd);
		creCrsReshVO.setReshSn(reshSn);
		creCrsReshVO = creCrsReshService.view(creCrsReshVO).getReturnVO();

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO logPrnLogVO = new LogPrnLogVO();
		logPrnLogVO.setUserNo(UserBroker.getUserNo(request));
		logPrnLogVO.setUserNm(UserBroker.getUserName(request));
		logPrnLogVO.setPrnDoc("PRINT EXCEL : Research Result ("+orgCd+" "+creCrsReshVO.getCrsCreCd()+")");
		logPrnLogVO.setParam(creCrsReshVO.toString());
		logPrnLogService.add(logPrnLogVO);

		//-- 개인정보 출력로그 저장
		LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
		pilVO.setOrgCd(orgCd);
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("ENRL_EXCEL");
		pilVO.setInqCts("EXCEL DOWNLOAD : Research Result ("+orgCd+" "+creCrsReshVO.getCrsCreCd()+")");
		logPrivateInfoService.add(pilVO);

		String labelName = sheetName+" : "+creCrsReshVO.getCrsCreNm()+" ["+getMessage(request, "course.title.reshbank.answer.result")+"]";
		String condition = getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");

		HashMap<String, String> titleMap = new HashMap<String, String>();
		titleMap.put("no", getMessage(request, "common.title.no")); // 0 - 순번
		titleMap.put("course_name", getMessage(request, "course.title.course.name")); // 1 - 과정명
		titleMap.put("resh_title", getMessage(request, "course.title.resh.title")); // 2 - 설문제목
		titleMap.put("duration", getMessage(request, "course.title.resh.duration")); // 3 - 설문기간
		titleMap.put("user_id", getMessage(request, "course.title.reshbank.answer.userid")); // 4 - 응답자ID
		titleMap.put("user_name", getMessage(request, "course.title.reshbank.answer.username")); // 5 - 응답자명
		titleMap.put("question", getMessage(request, "course.title.exambank.question")); // 6 - 문항1

		response.setHeader("Content-Disposition", "attachment;filename="+fileNamePrefix+".xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");

		crsReshAnsrService.listExcelDownload(creCrsReshVO, sheetName, labelName, condition, excelHeader, titleMap, request, response.getOutputStream());
		response.getOutputStream().flush();

		return null;
	}
}

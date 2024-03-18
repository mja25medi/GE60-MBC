package egovframework.edutrack.web.lecture.course;

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

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
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
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/creCrsResh")
public class CreCrsReshLectureController extends GenericController {

	@Autowired @Qualifier("creCrsReshService")
	private CreCrsReshService		creCrsReshService;

	@Autowired @Qualifier("researchBankService")
	private ResearchBankService	researchBankService;

	@Autowired @Qualifier("crsReshAnsrService")
	private CrsReshAnsrService		crsReshAnsrService;

	@Autowired @Qualifier("crsReshResultService")
	private CrsReshResultService	crsReshResultService;

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
	@RequestMapping(value="/tchMain")
	public String main(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		List<CreCrsReshVO> creCrsReshList = creCrsReshService.list(vo).getReturnList();

		ResearchBankVO researchBankVO = new ResearchBankVO();
		researchBankVO.setCrsCreCd(crsCreCd);
		researchBankVO.setOrgCd(orgCd);
		researchBankVO.setRegYn("Y");
		//문제등록이 0건인 설문은 조회가 안되도록함
		researchBankVO.setSearchCnt("Y");

		//2015.11.18 김현욱 수정 (REDMINE KNOTZ_NG_192) 기등록된 설문정보를 삭제하고 조회함.
		List<ResearchBankVO> researchBankList = researchBankService.listResearchPreclusive(researchBankVO).getReturnList();
		request.setAttribute("researchBankList", researchBankList);

		request.setAttribute("creCrsReshVO", vo);
		request.setAttribute("creCrsReshList", creCrsReshList);
	
		return "home/lecture/crecrsresh/teacher/resh_tch_main";
	}


	/**
	 * 개설 과정 설문 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormMain")
	public String addFormMain(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		List<CreCrsReshVO> creCrsReshList = creCrsReshService.list(vo).getReturnList();
		request.setAttribute("creCrsReshList", creCrsReshList);

		ResearchBankVO researchBankVO = new ResearchBankVO();
		researchBankVO.setCrsCreCd(crsCreCd);
		researchBankVO.setOrgCd(orgCd);
		researchBankVO.setRegYn("Y");
		//문제등록이 0건인 설문은 조회가 안되도록함
		researchBankVO.setSearchCnt("Y");

		//2015.11.18 김현욱 수정 (REDMINE KNOTZ_NG_192) 기등록된 설문정보를 삭제하고 조회함.
		/*List<ResearchBankVO> researchBankList = researchBankService.listResearch(researchBankVO).getReturnList();*/
		List<ResearchBankVO> researchBankList = researchBankService.listResearchPreclusive(researchBankVO).getReturnList();
		request.setAttribute("researchBankList", researchBankList);


		vo.setUseYn("Y");
		vo.setRegYn("Y");

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");

		return "home/lecture/crecrsresh/resh_write";
	}

	/**
	 * 개설 과정 설문 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		//return JsonUtil.responseJsonWithResult(request, response, creCrsReshService.add(creCrsReshVO));
		try {
			creCrsReshService.add(vo);
		} catch (Exception e) {

			ResearchBankVO researchBankVO = new ResearchBankVO();
			List<ResearchBankVO> researchBankList = researchBankService.listResearch(researchBankVO).getReturnList();
			request.setAttribute("researchBankList", researchBankList);

			if(ValidationUtils.isNotEmpty(vo.getStartDttm()))
				vo.setStartDttm(DateTimeUtil.getDateText(1, vo.getStartDttm().substring(0,8),"."));
			if(ValidationUtils.isNotEmpty(vo.getEndDttm()))
				vo.setEndDttm(DateTimeUtil.getDateText(1, vo.getEndDttm().substring(0,8),"."));

			vo.setUseYn("Y");
			vo.setRegYn("Y");
			request.setAttribute("creCrsReshVO", vo);
			request.setAttribute("gubun", "A");

			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.research.add.failed"));
			return "home/lecture/crecrsresh/write";	// 다시 편집 화면으로
		}

		setAlertMessage(request, getMessage(request, "lecture.message.research.add.success"));

		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/creCrsResh/main")
				.toString();
	}

	/**
	 * 개설 과정 설문 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editForm")
	public String editForm(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = creCrsReshService.view(vo).getReturnVO();
		if(ValidationUtils.isNotEmpty(vo.getStartDttm()))
			vo.setStartDttm(DateTimeUtil.getDateText(1, vo.getStartDttm().substring(0,8),"."));
		if(ValidationUtils.isNotEmpty(vo.getEndDttm()))
			vo.setEndDttm(DateTimeUtil.getDateText(1, vo.getEndDttm().substring(0,8),"."));

		request.setAttribute("creCrsReshVO", vo);
		request.setAttribute("gubun", "E");

		return "home/lecture/crecrsresh/resh_write";
	}

	/**
	 * 개설 과정 설문 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response){
		commonVOProcessing(vo, request);

		//return JsonUtil.responseJsonWithResult(request, response, creCrsReshService.edit(creCrsReshVO));
		try {
			creCrsReshService.edit(vo);
		} catch (Exception e) {
			if(ValidationUtils.isNotEmpty(vo.getStartDttm()))
				vo.setStartDttm(DateTimeUtil.getDateText(1, vo.getStartDttm().substring(0,8),"."));
			if(ValidationUtils.isNotEmpty(vo.getEndDttm()))
				vo.setEndDttm(DateTimeUtil.getDateText(1, vo.getEndDttm().substring(0,8),"."));
			request.setAttribute("creCrsReshVO", vo);
			request.setAttribute("gubun", "E");

			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.research.edit.failed"));
			return "home/lecture/crecrsresh/write";	// 다시 편집 화면으로
		}

		setAlertMessage(request, getMessage(request, "lecture.message.research.edit.success"));

		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/creCrsResh/main")
				.toString();
	}

	/**
	 * 개설 과정 설문 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/remove")
	public String remove(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		//return JsonUtil.responseJsonWithResult(request, response, creCrsReshService.remove(creCrsReshVO));
		try {
			creCrsReshService.remove(vo);
		} catch (Exception e) {
			if(ValidationUtils.isNotEmpty(vo.getStartDttm()))
				vo.setStartDttm(DateTimeUtil.getDateText(1, vo.getStartDttm().substring(0,8),"."));
			if(ValidationUtils.isNotEmpty(vo.getEndDttm()))
				vo.setEndDttm(DateTimeUtil.getDateText(1, vo.getEndDttm().substring(0,8),"."));

			request.setAttribute("gubun", "E");
			e.printStackTrace();

			CrsReshAnsrVO crsReshAnsrVO = new CrsReshAnsrVO();
			crsReshAnsrVO.setCrsCreCd(vo.getCrsCreCd());
			crsReshAnsrVO.setReshSn(vo.getReshSn());
			ProcessResultVO<CrsReshAnsrVO> listCount = crsReshAnsrService.listCount(crsReshAnsrVO);
			String[] args = {listCount.getReturnVO().getReshCnt()+""};

			setAlertMessage(request, getMessage(request, "lecture.message.research.delete.failed", args));
			return "redirect:"+ new URLBuilder("/lec/creCrsResh/main")
						.toString();
			//return mapping.findForward("write");	// 다시 편집 화면으로
		}

		setAlertMessage(request, getMessage(request, "lecture.message.research.delete.success"));

		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/creCrsResh/main")
				.toString();
	}

	/**
	 * 개설 과정 설문 결과 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rsltMain")
	public String rsltMain(CrsReshResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		CreCrsReshVO creCrsReshVO = new CreCrsReshVO();
		creCrsReshVO.setCrsCreCd(crsCreCd);
		creCrsReshVO.setReshSn(vo.getReshSn());
		//-- 설문 정보 검색
		creCrsReshVO = creCrsReshService.view(creCrsReshVO).getReturnVO();

		List<CrsReshResultVO> crsReshResultList = crsReshResultService.list(vo).getReturnList();
		request.setAttribute("vo", vo);
		request.setAttribute("creCrsReshVO", creCrsReshVO);
		request.setAttribute("crsReshResultList", crsReshResultList);


		return "home/lecture/crecrsresh/teacher/resh_result";
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
	public String mainOpinionPop(CrsReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		//-- 설문 문제 정보 검색
		ResearchBankItemVO reshBankItemVO = new ResearchBankItemVO();
		reshBankItemVO.setReshSn(vo.getReshSn());
		reshBankItemVO.setReshItemSn(vo.getReshItemSn());
		reshBankItemVO = researchBankService.viewItem(reshBankItemVO).getReturnVO();
		request.setAttribute("reshBankItemVO", reshBankItemVO);

		List<CrsReshAnsrVO> crsReshResultList = crsReshAnsrService.listOpinion(vo).getReturnList();

		request.setAttribute("crsReshAnsrVO", vo);
		request.setAttribute("crsReshResultList", crsReshResultList);

		return "home/lecture/crecrsresh/resh_opinion";
	}

	/**
	 * 학습자의 설문 목록 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/stdMain")
	public String stdMain(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String stdNo = UserBroker.getStudentNo(request);
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);
		vo.setUseYn("Y");
		vo.setRegYn("Y"); //-- 등록 완료된 설문 목록 셋팅
		vo.setStdNo(stdNo);

		List<CreCrsReshVO> creCrsReshList = creCrsReshService.list(vo).getReturnList();


		request.setAttribute("creCrsReshVO", vo);
		request.setAttribute("creCrsReshList", creCrsReshList);

		return "home/lecture/crecrsresh/resh_std_main";
	}

	/**
	 * 학습자의 설문 참여창
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/joinMain")
	public String joinMain(CreCrsReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String stdNo = UserBroker.getStudentNo(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);
		//-- 설문 정보 검색
		vo = creCrsReshService.view(vo).getReturnVO();

		ResearchBankVO reshBankVO = new ResearchBankVO();
		reshBankVO.setReshSn(vo.getReshSn());

		CrsReshAnsrVO crsReshAnsrVO = new CrsReshAnsrVO();
		crsReshAnsrVO.setCrsCreCd(crsCreCd);
		crsReshAnsrVO.setReshSn(vo.getReshSn());
		crsReshAnsrVO.setStdNo(stdNo);

		List<ResearchBankItemVO> reshBankItemList = researchBankService.listItem(reshBankVO).getReturnList();
		request.setAttribute("reshBankItemList", reshBankItemList);

		request.setAttribute("vo", vo);
		request.setAttribute("crsReshAnsrVO", crsReshAnsrVO);
		request.setAttribute("textareaResize", "none");

		return "home/lecture/crecrsresh/resh_join_main";
	}

	/**
	 * 개설 과정 설문 참여
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/join")
	public String join(CrsReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		String stdNo = UserBroker.getStudentNo(request);
		vo.setStdNo(stdNo);

		try {
			crsReshAnsrService.add(vo);
			setAlertMessage(request, getMessage(request, "lecture.message.research.join.success"));
			return "redirect:"+ new URLBuilder("/lec/creCrsResh/stdMain")
						.toString();
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.research.join.failed"));
			return "redirect:"+ new URLBuilder("/lec/creCrsResh/joinMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("reshSn", vo.getReshSn())
						.toString();
		}

	}


	/**
	 * 개설과정관리/설문결과 다운로드
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/reshExcelDownload")
	public String reshExcelDownload(CrsReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String excelHeader = StringUtil.nvl(request.getParameter("excelHeader"),"");
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"),"");
		Integer reshSn = Integer.parseInt(StringUtil.nvl(request.getParameter("reshSn"),"0"));

		String orgCd = UserBroker.getUserOrgCd(request);

		String printMode = StringUtil.nvl(request.getParameter("printMode"),"EXCEL");

		String fileNamePrefix = "Resh_Result";
		String sheetName = getMessage(request, "course.title.reshbank.answer.result");

		//-- 개설 과정 설문정보를 가져온다
		CreCrsReshVO creCrsReshVO = new CreCrsReshVO();
		creCrsReshVO.setCrsCreCd(crsCreCd);
		creCrsReshVO.setReshSn(reshSn);
		creCrsReshVO = creCrsReshService.view(creCrsReshVO).getReturnVO();

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("PRINT EXCEL : 과정 개설 관리 > 설문관리 ("+orgCd+" "+creCrsReshVO.getCrsCreNm()+"과정 "+")");
		printLogVO.setParam(creCrsReshVO.toString());
		logPrnLogService.add(printLogVO);

		//-- 개인정보 출력로그 저장
		/*PrivateInfoLogVO pilVO = new PrivateInfoLogVO();
		pilVO.setOrgCd(orgCd);
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("ENRL_EXCEL");
		pilVO.setInqCts("EXCEL DOWNLOAD : 과정 개설 관리 > 설문관리 ("+orgCd+" "+creCrsReshVO.getCrsCreNm()+"과정 "+")");
		privateInfoLogService.add(pilVO);*/

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

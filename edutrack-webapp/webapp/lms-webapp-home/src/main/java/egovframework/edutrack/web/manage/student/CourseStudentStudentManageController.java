package egovframework.edutrack.web.manage.student;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.student.service.StudentExcelService;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/std")
public class CourseStudentStudentManageController extends GenericController {

	@Autowired @Qualifier("studentService")
	private StudentService					studentService;

	@Autowired @Qualifier("studentExcelService")
	private StudentExcelService			studentExcelService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService 			logPrivateInfoService;

	/**
	 * [HRD] 회차관리 > 수강생 관리 - 메인
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="/courseStdPayMain")
	public String courseStdPayMain( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCreCd = request.getParameter("crsCreCd");
		vo.setCrsCd(crsCreCd);
		
		request.setAttribute("paging", "Y");
		model.addAttribute("crsCreCd", crsCreCd);
		
		return "mng/student/student/course_student_payment_main";
	}
	
	/**
	 * [HRD] 회차관리 > 수강생 관리 - 리스트 페이징
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listCourseStdPay")
	public String listCourseStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//기업, 기수, 과정 정보로 수강생+결제 리스트, 통계 조회
		ProcessResultListVO<StudentVO> resultListVO = studentService.listStudentPaymentInfoManagePageing(vo);
		
		model.addAttribute("stdPayList", resultListVO.getReturnList());//리스트
		model.addAttribute("stdPayStatusVO", resultListVO.getReturnVO());//상단 통계
		model.addAttribute("pageInfo", resultListVO.getPageInfo());//상단 통계
		

		return "mng/student/student/course_student_payment_list";
	}
	
	/**
	 * 수강 신청 삭제 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCourseStudent")
	public String deleteCourseStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.deleteCourseStudent(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 회차관리>수강생관리>엑셀다운로드
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/excelDownloadListStd")
	public String excelDownloadListStd ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		response.setHeader("Content-Disposition", "attachment;filename=stu_list.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		
		ProcessResultVO resultVO = new ProcessResultVO<>();
		
		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("PRINT EXCEL : 수강생관리 엑셀다운로드");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		//-- 개인정보 출력로그 저장
		LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
		pilVO.setOrgCd(UserBroker.getUserOrgCd(request));
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("EXCEL");
		pilVO.setInqCts("EXCEL DOWNLOAD : 수강생관리 엑셀다운로드");
		logPrivateInfoService.add(pilVO);
		
		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet("수강생관리");

			//-- 컬럼 제목줄
			XSSFRow xssRow = sheet.createRow((short)rowNum);

			POIExcelUtil.createTitleCell("이름", xssRow, 0);
			POIExcelUtil.createTitleCell("아이디", xssRow, 1);
			POIExcelUtil.createTitleCell("수강신청일", xssRow, 2);
			POIExcelUtil.createTitleCell("결제방법", xssRow, 3);
			POIExcelUtil.createTitleCell("결제금액", xssRow, 4);
			POIExcelUtil.createTitleCell("IDE", xssRow, 5);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 1500);	
			
			List<StudentVO> stuList = studentService.listStudentPaymentInfoManage(vo).getReturnList();
			
			String paymMthdNm = "";

			for(StudentVO stuVO : stuList) {
				rowNum++;
				xssRow = sheet.createRow((short)rowNum);
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getUserNm()), xssRow, 0, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getUserId()), xssRow, 1, "center");
				POIExcelUtil.createContentCell(DateTimeUtil.getDateType(0, StringUtil.nvl(stuVO.getEnrlAplcDttm())), xssRow, 2, "center");

				if("".equals(stuVO.getPaymMthdNm()) || null==stuVO.getPaymMthdNm()) {
					paymMthdNm = "무료수강";
				}else {
					paymMthdNm = stuVO.getPaymMthdNm();
				}

				POIExcelUtil.createContentCell(StringUtil.nvl(paymMthdNm), xssRow, 3, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getPaymPrice()), xssRow, 4, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getIdeUrl()), xssRow, 5, "center");
			}
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			} finally {
				if(os != null) {
					os.close();
				}
			}
		} catch(IOException e1) {
			log.error("수강생관리 엑셀다운로드 오류 : IOException");
			resultVO.setResultFailed();
			resultVO.setMessage("수강생관리 엑셀다운로드 오류, IOException");
		} catch (Exception e) {
			log.error("수강생관리 엑셀다운로드 오류");
			resultVO.setResultFailed();
			resultVO.setMessage("수강생관리 엑셀다운로드 오류");
		}
		
		return null;
	}
	
	/**
	 * [HRD] 회차관리>IDE URL>엑셀업로드 폼
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addStdIdeExcelPop")
	public String addStdIdeExcelPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("fileupload", "Y");
		String crsCreCd = request.getParameter("crsCreCd");
		request.setAttribute("crsCreCd", crsCreCd);
		
		return "mng/student/student/student_ide_write_excel_pop";
	}
	
	/**
	 * [HRD] 회차관리>IDE URL>엑셀업로드 - SAMPLE 다운로드
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/sampleExcelStdIde")
	public void sampleExcelStdIde ( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		String orgCd = UserBroker.getUserOrgCd(request);
		
		response.setHeader("Content-Disposition", "attachment;filename=stu_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet("IDE_URL업로드");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			pageRow1.setHeight((short)2000);
			String info = "IDE 일괄 등록\n"
							+ "* 샘플 데이터와 같은 형식으로 샘플 파일에 작성하여 업로드해야 정상적으로 등록됩니다.\n"
							+ "* 아이디와 IDE URL은 중복되지 않도록 주의하여 주십시오.\n"
							;
			POIExcelUtil.createMergeCell(info, pageRow1, 0, 1, "left");
			
			//-- 컬럼 제목줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);

			POIExcelUtil.createTitleCell("아이디", titleRow, 0);
			POIExcelUtil.createTitleCell("IDE URL", titleRow, 1);
			
			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 4000);
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			} finally {
				if(os != null) {
					os.close();
				}
			}
		} catch (Exception e) {
			log.error("IOException occurred");
		}
	}
	
	/**
	 * [HRD] 회차관리>IDE URL>엑셀업로드 - 수강생 등록
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value="/uploadExcelStdIde")
	public String uploadExcelStdIde ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		
		String type =  StringUtil.nvl(request.getParameter("type"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String orgCd = StringUtil.nvl(UserBroker.getUserOrgCd(request), "ORG0000001");
		String regIp = request.getRemoteAddr();
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"));
		vo.setOrgCd(orgCd);
		vo.setRegIp(regIp);
		vo.setCrsCreCd(crsCreCd);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>();
		try {
			studentExcelService.addStudentIdeUrlExcel(vo, fileName, filePath);
			resultVO.setResultSuccess();
			resultVO.setMessage("IDE URL 등록에 성공하였습니다.");
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			log.error(e.getMessage());
			resultVO.setMessage("IDE URL 등록에 실패하였습니다. 반복될 경우, 담당자에게 문의바랍니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	
}
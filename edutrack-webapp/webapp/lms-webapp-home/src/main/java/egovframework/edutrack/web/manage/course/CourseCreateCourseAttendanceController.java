package egovframework.edutrack.web.manage.course;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.attendance.service.AttendanceService;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/createCourse")
public class CourseCreateCourseAttendanceController extends GenericController {
	
	@Autowired @Qualifier("courseService")
	private CourseService			courseService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;
	
	@Autowired @Qualifier("attendanceService")
	private AttendanceService 		attendanceService;
	
	@Autowired @Qualifier("studentService")
	private StudentService 		studentService;
	
	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;


	/**
	 * 출결 관리 메인
	 */
	@RequestMapping(value="/attendanceMain")
	public String mainCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setUserNo(UserBroker.getUserNo(request));
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");
		return "mng/course/createcourse/attendance_main";
	}
	/**
	 * 회차 목록
	 */
	@RequestMapping(value="/attendanceCourseList")
	public String attendanceCourseList( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("crsOperType", vo.getCrsOperType());
		
		vo.setMngType(UserBroker.getMngType(request));
		if (vo.getMngType().contains("TCHMGR")) {
			vo.setUserNo(UserBroker.getUserNo(request));
		}
		
		ProcessResultListVO<CreateCourseVO> createCourseList = createCourseService.listCreateCoursePageing(vo, vo.getCurPage(), vo.getListScale(), true);
		request.setAttribute("pageInfo", createCourseList.getPageInfo());
		request.setAttribute("vo", vo);
		request.setAttribute("createCourseList", createCourseList.getReturnList());
		return "mng/course/createcourse/attendance_create_course_list_div";
	}
	
	/**
	 * 출석부 표
	 */
	@RequestMapping(value="/listAttend", method = { RequestMethod.GET, RequestMethod.POST })
	public String listAttend( AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(value="searchDate", required=false) String searchDate) throws Exception {
		commonVOProcessing(vo, request);
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(vo.getCrsCreCd());
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
	
		String[] dataArr = new String[5];
		String searchPeriod = "";
		SimpleDateFormat period = new SimpleDateFormat("yyyy.MM.dd");
		// 1. 검색이 없을경우 오늘 기준으로 이번 주의 월화수목금을 세팅
		if(searchDate == null || searchDate=="") {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	 		Calendar c = Calendar.getInstance();
	 		searchDate = formatter.format(c.getTime());
	 		
	 		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	 		searchPeriod = period.format(c.getTime()).toString();
	 		for (int i=0; i<5; i++) {
	 			formatter.format(c.getTime());
	 			dataArr[i] = formatter.format(c.getTime()).toString();
	 			c.add(Calendar.DATE, 1);
	 			System.out.println(formatter.format(c.getTime()));
	 		}
	 		searchPeriod += "~"+ period.format(c.getTime()).toString();
		} else {
			// 2. 검색이 있을경우 검색어 기준으로 그 주의 월화수목금을 아래 변수에 세팅
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			
			Calendar c = Calendar.getInstance();
			
			int y = Integer.parseInt(searchDate.substring(0,4));		
			int m = Integer.parseInt(searchDate.substring(5,7))-1;		
			int d = Integer.parseInt(searchDate.substring(8,10));
			
			c.set(Calendar.YEAR, y);
			c.set(Calendar.MONTH, m); 
			c.set(Calendar.DATE, d);
			
			int wk = Integer.parseInt(getWeek(c));
			c.set(Calendar.WEEK_OF_MONTH, wk);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			searchPeriod = period.format(c.getTime()).toString();
			for (int i=0; i<5; i++) {
	 			dataArr[i] = formatter.format(c.getTime()).toString();
	 			c.add(Calendar.DATE, 1);
	 			System.out.println(formatter.format(c.getTime()));
	 		}
			searchPeriod += "~"+ period.format(c.getTime()).toString();
		}
		vo.setClsAttendDttm(dataArr);
		ProcessResultListVO<AttendanceVO> resultList = attendanceService.listAttendance(vo);
		request.setAttribute("searchPeriod", searchPeriod);
		request.setAttribute("searchDate", searchDate);
		request.setAttribute("attendPeriod", dataArr);
		request.setAttribute("createCourseVO", ccvo);
		request.setAttribute("attendList", resultList.getReturnList());

		return "mng/course/createcourse/attend_list";
	}
	
	//주말 체크
	  private static boolean isWeekend(LocalDateTime date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
	  
	 public static String getWeek(Calendar c) {
		 String week = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
		 return week;
	 }
	 
	/**
	 * 유저상세 팝업
	 */
	@RequestMapping(value="/attendUserPop", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendUserPop( AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<AttendanceVO> resultList = attendanceService.listPeriod(vo);
		request.setAttribute("periodList", resultList.getReturnList());
		
		List<AttendanceVO> logList = new ArrayList<AttendanceVO>();
		
		StudentVO svo = new StudentVO();
		svo.setCrsCreCd(vo.getCrsCreCd());
		List<StudentVO> stuList = studentService.listStudentPaymentInfoManage(svo).getReturnList();
		request.setAttribute("stuList", stuList);
		
		//입력일이 없을 경우 교육 첫날 조회
		if(vo.getAttendDttm() == null || vo.getAttendDttm() == "") {
			vo.setAttendDttm(resultList.getReturnList().get(0).getAttendDttm());
			
			//변경 로그 조회
			logList = attendanceService.selectUpdateLog(vo).getReturnList();
			
			vo = attendanceService.viewAttend(vo).getReturnVO();
		} else {
			vo.setAttendDttm(vo.getAttendDttm().replaceAll("-", ""));
			
			//변경 로그 조회
			logList = attendanceService.selectUpdateLog(vo).getReturnList();
			
			vo = attendanceService.viewAttend(vo).getReturnVO();
		}
		
		request.setAttribute("logList", logList);
		request.setAttribute("vo", vo);
		
		return "mng/course/createcourse/attend_user_pop";
	}
	
	/**
	 * 유저 출석 상태 변경
	 */
	@RequestMapping(value="/updateAttendStat")
	public String updateAttendStat( AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setModNo(UserBroker.getUserNo(request));
		vo.setAttendDttm(vo.getAttendDttm().replaceAll("-", ""));
		ProcessResultVO<AttendanceVO> resultVO = attendanceService.updateAttendStat(vo);
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	
	/**
	 * 엑셀 다운로드
	 */
	@RequestMapping(value="/excelDownloadAttendList")
	public String excelDownloadAttendList( AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(vo.getCrsCreCd());
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		
		//전체 기간 배열
		List<AttendanceVO> pList = attendanceService.listPeriod(vo).getReturnList();
		List<String> tArray = new ArrayList<>();
		for(int i=0; i<pList.size(); i++) {
			tArray.add(pList.get(i).getAttendDttm());
		}
		
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("엑셀출력 : 시간표 > 출석부 출력 ("+ccvo.getCrsCreNm()+"과정 "+ccvo.getCreTerm()+")");
		printLogVO.setParam(vo.toString());
		//printLogService.addPrintLog(printLogVO);	=> 기존
		logPrnLogService.add(printLogVO);
		
		response.setHeader("Content-Disposition", "attachment;filename=AttendTable_"+vo.getCrsCreCd()+".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");
		
		// 단 건 호출시 vo 에 attendDttm 세팅 
		this.excelDownloadAttendList(vo, ccvo, response.getOutputStream());
		
		return null;
	}
	
	
	public void excelDownloadAttendList(AttendanceVO vo, CreateCourseVO ccvo, ServletOutputStream outputStream)
			throws ServiceProcessException {
		
		
		
		try {
			
			WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
			
			// 1. 회차별 출석기간의 월요일만 구해온다 (시트구성용)
			List<AttendanceVO> listPeriodMonDay = attendanceService.listPeriodMonDay(vo).getReturnList();
			
			for(int x=0; x < listPeriodMonDay.size(); x++){
				AttendanceVO avo = new AttendanceVO();
				avo = listPeriodMonDay.get(x);
				
				int rowNum = 0;
				
				// 2. 월요일에 해당하는 날짜로 월~금 뮨자열 출력
				String periodDateStr = attendanceService.periodDateStr(avo.getAttendDttm());
				
				WritableSheet sheet = workbook.createSheet((x+1)+"주", x);
				sheet.addCell(ExcelUtil.createText(0, rowNum, "center","훈련기관명")); //1열
				sheet.mergeCells(1, rowNum, 8, rowNum); //-- 병합
				sheet.mergeCells(9, rowNum, 16, rowNum);
				sheet.mergeCells(17, rowNum, 24, rowNum); 
				sheet.mergeCells(25, rowNum, 32, rowNum);
				sheet.mergeCells(33, rowNum, 47, rowNum);
				sheet.addCell(ExcelUtil.createText(1, rowNum, "center", "(사)스마트인재개발원"));
				sheet.addCell(ExcelUtil.createText(9, rowNum, "center", "훈련과정명"));
				sheet.addCell(ExcelUtil.createText(17, rowNum, "center", ccvo.getCrsCreNm()+"("+ccvo.getCreTerm()+") 회차 "));
				sheet.addCell(ExcelUtil.createText(25, rowNum, "center", "훈련기간"));
				sheet.addCell(ExcelUtil.createText(33, rowNum, "center", ccvo.getEnrlStartDttm() + "~" + ccvo.getEnrlEndDttm()));
				
				rowNum++;
				sheet.addCell(ExcelUtil.createText(0, rowNum, "center","날짜"));
				sheet.mergeCells(1, rowNum, 8, rowNum); //-- 병합
				sheet.mergeCells(9, rowNum, 16, rowNum);
				sheet.mergeCells(17, rowNum, 24, rowNum); 
				sheet.mergeCells(25, rowNum, 32, rowNum);
				sheet.mergeCells(33, rowNum, 40, rowNum);
				sheet.addCell(ExcelUtil.createText(1, rowNum, "center",periodDateStr.split("_")[0]+"(월)"));
				sheet.addCell(ExcelUtil.createText(9, rowNum, "center",periodDateStr.split("_")[1]+"(화)"));
				sheet.addCell(ExcelUtil.createText(17, rowNum, "center",periodDateStr.split("_")[2]+"(수)"));
				sheet.addCell(ExcelUtil.createText(25, rowNum, "center",periodDateStr.split("_")[3]+"(목)"));
				sheet.addCell(ExcelUtil.createText(33, rowNum, "center",periodDateStr.split("_")[4]+"(금)"));
				
				for(int i=41; i<48; i++ ) {
					sheet.mergeCells(i, 1, i, 3);
				}
				
				sheet.addCell(ExcelUtil.createText(41, rowNum, "center", "소정출석일"));
				sheet.addCell(ExcelUtil.createText(42, rowNum, "center", "실제출석일"));
				sheet.addCell(ExcelUtil.createText(43, rowNum, "center", "결석"));
				sheet.addCell(ExcelUtil.createText(44, rowNum, "center", "지각"));
				sheet.addCell(ExcelUtil.createText(45, rowNum, "center", "조퇴"));
				sheet.addCell(ExcelUtil.createText(46, rowNum, "center", "외출"));
				sheet.addCell(ExcelUtil.createText(47, rowNum, "center", "확인"));
				
				rowNum++;
				sheet.addCell(ExcelUtil.createText(0, rowNum, "center", "결재"));
				sheet.mergeCells(1, rowNum, 8, rowNum); //-- 병합
				sheet.mergeCells(9, rowNum, 16, rowNum);
				sheet.mergeCells(17, rowNum, 24, rowNum);
				sheet.mergeCells(25, rowNum, 32, rowNum);
				sheet.mergeCells(33, rowNum, 40, rowNum);
				sheet.addCell(ExcelUtil.createText(1, rowNum, "center", ""));
				sheet.addCell(ExcelUtil.createText(9, rowNum, "center", ""));
				sheet.addCell(ExcelUtil.createText(17, rowNum, "center", ""));
				sheet.addCell(ExcelUtil.createText(25, rowNum, "center", ""));
				sheet.addCell(ExcelUtil.createText(33, rowNum, "center", ""));
				
				rowNum++;
				sheet.addCell(ExcelUtil.createText(0, rowNum, "center", "성명"));
				
				int col = 1;
				while(col<40) {
					for (int j=1; j<9; j++) {
						sheet.addCell(ExcelUtil.createText(col, rowNum, "center", Integer.toString(j)));
						sheet.setColumnView(col, 5);
						col++;
					}
				}
				
				rowNum++;
				//데이터 시작
				String[] dataArr = new String[5];
				dataArr[0] = periodDateStr.split("_")[0];
				dataArr[1] = periodDateStr.split("_")[1];
				dataArr[2] = periodDateStr.split("_")[2];
				dataArr[3] = periodDateStr.split("_")[3];
				dataArr[4] = periodDateStr.split("_")[4];
				
				vo.setClsAttendDttm(dataArr);
				ProcessResultListVO<AttendanceVO> resultList = attendanceService.listAttendance(vo);
				
				for( int xx=0; xx<resultList.getReturnList().size(); xx++ ){
					AttendanceVO result = resultList.getReturnList().get(xx);
		
					sheet.addCell(ExcelUtil.createText(0,3+(xx+1),"center",result.getUserNm()));
					
					sheet.addCell(ExcelUtil.createText(1,3+(xx+1),"center",this.changeCol(result.getCol01())));
					sheet.addCell(ExcelUtil.createText(2,3+(xx+1),"center",this.changeCol(result.getCol02())));
					sheet.addCell(ExcelUtil.createText(3,3+(xx+1),"center",this.changeCol(result.getCol03())));
					sheet.addCell(ExcelUtil.createText(4,3+(xx+1),"center",this.changeCol(result.getCol04())));
					sheet.addCell(ExcelUtil.createText(5,3+(xx+1),"center",this.changeCol(result.getCol05())));
					sheet.addCell(ExcelUtil.createText(6,3+(xx+1),"center",this.changeCol(result.getCol06())));
					sheet.addCell(ExcelUtil.createText(7,3+(xx+1),"center",this.changeCol(result.getCol07())));
					sheet.addCell(ExcelUtil.createText(8,3+(xx+1),"center",this.changeCol(result.getCol08())));
					
					sheet.addCell(ExcelUtil.createText(9,3+(xx+1),"center",this.changeCol(result.getCol11())));
					sheet.addCell(ExcelUtil.createText(10,3+(xx+1),"center",this.changeCol(result.getCol12())));
					sheet.addCell(ExcelUtil.createText(11,3+(xx+1),"center",this.changeCol(result.getCol13())));
					sheet.addCell(ExcelUtil.createText(12,3+(xx+1),"center",this.changeCol(result.getCol14())));
					sheet.addCell(ExcelUtil.createText(13,3+(xx+1),"center",this.changeCol(result.getCol15())));
					sheet.addCell(ExcelUtil.createText(14,3+(xx+1),"center",this.changeCol(result.getCol16())));
					sheet.addCell(ExcelUtil.createText(15,3+(xx+1),"center",this.changeCol(result.getCol17())));
					sheet.addCell(ExcelUtil.createText(16,3+(xx+1),"center",this.changeCol(result.getCol18())));
					
					sheet.addCell(ExcelUtil.createText(17,3+(xx+1),"center",this.changeCol(result.getCol21())));
					sheet.addCell(ExcelUtil.createText(18,3+(xx+1),"center",this.changeCol(result.getCol22())));
					sheet.addCell(ExcelUtil.createText(19,3+(xx+1),"center",this.changeCol(result.getCol23())));
					sheet.addCell(ExcelUtil.createText(20,3+(xx+1),"center",this.changeCol(result.getCol24())));
					sheet.addCell(ExcelUtil.createText(21,3+(xx+1),"center",this.changeCol(result.getCol25())));
					sheet.addCell(ExcelUtil.createText(22,3+(xx+1),"center",this.changeCol(result.getCol26())));
					sheet.addCell(ExcelUtil.createText(23,3+(xx+1),"center",this.changeCol(result.getCol27())));
					sheet.addCell(ExcelUtil.createText(24,3+(xx+1),"center",this.changeCol(result.getCol28())));
					
					sheet.addCell(ExcelUtil.createText(25,3+(xx+1),"center",this.changeCol(result.getCol31())));
					sheet.addCell(ExcelUtil.createText(26,3+(xx+1),"center",this.changeCol(result.getCol32())));
					sheet.addCell(ExcelUtil.createText(27,3+(xx+1),"center",this.changeCol(result.getCol33())));
					sheet.addCell(ExcelUtil.createText(28,3+(xx+1),"center",this.changeCol(result.getCol34())));
					sheet.addCell(ExcelUtil.createText(29,3+(xx+1),"center",this.changeCol(result.getCol35())));
					sheet.addCell(ExcelUtil.createText(30,3+(xx+1),"center",this.changeCol(result.getCol36())));
					sheet.addCell(ExcelUtil.createText(31,3+(xx+1),"center",this.changeCol(result.getCol37())));
					sheet.addCell(ExcelUtil.createText(32,3+(xx+1),"center",this.changeCol(result.getCol38())));
					
					sheet.addCell(ExcelUtil.createText(33,3+(xx+1),"center",this.changeCol(result.getCol41())));
					sheet.addCell(ExcelUtil.createText(34,3+(xx+1),"center",this.changeCol(result.getCol42())));
					sheet.addCell(ExcelUtil.createText(35,3+(xx+1),"center",this.changeCol(result.getCol43())));
					sheet.addCell(ExcelUtil.createText(36,3+(xx+1),"center",this.changeCol(result.getCol44())));
					sheet.addCell(ExcelUtil.createText(37,3+(xx+1),"center",this.changeCol(result.getCol45())));
					sheet.addCell(ExcelUtil.createText(38,3+(xx+1),"center",this.changeCol(result.getCol46())));
					sheet.addCell(ExcelUtil.createText(39,3+(xx+1),"center",this.changeCol(result.getCol47())));
					sheet.addCell(ExcelUtil.createText(40,3+(xx+1),"center",this.changeCol(result.getCol48())));
					
					sheet.addCell(ExcelUtil.createText(41,3+(xx+1),"center",result.getTotalDay().toString()));
					sheet.addCell(ExcelUtil.createText(42,3+(xx+1),"center",result.getRealDay().toString()));
					sheet.addCell(ExcelUtil.createText(43,3+(xx+1),"center",result.getAbsentDay().toString()));
					sheet.addCell(ExcelUtil.createText(44,3+(xx+1),"center",result.getLateDay().toString()));
					sheet.addCell(ExcelUtil.createText(45,3+(xx+1),"center",result.getLeftDay().toString()));
					sheet.addCell(ExcelUtil.createText(46,3+(xx+1),"center",result.getOutDay().toString()));
					sheet.addCell(ExcelUtil.createText(47,3+(xx+1),"center",""));
				}
				
			}
			
			
			workbook.write();
			workbook.close();
					
		} catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
		
	}
	
	public String changeCol(String col) {
		
		String result = "";
		
		if(col.equals("S")){
			result = "○";
		 } else if (col.equals("F")){
			 result = "×";
		 } else if (col.equals("T")){
			 result = "◎";
		 } else if (col.equals("L")){
			 result = "▲";
		 } else if (col.equals("O")){
			 result = "◇";
		 } else {
			 result = "-";
		 }
		 return result;
	 }
	
}

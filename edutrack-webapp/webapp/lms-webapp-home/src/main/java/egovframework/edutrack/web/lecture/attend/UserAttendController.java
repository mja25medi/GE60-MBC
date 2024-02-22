package egovframework.edutrack.web.lecture.attend;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.attendance.service.AttendanceService;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/attend")
public class UserAttendController extends GenericController {

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService	usrUserInfoService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;
	
	@Autowired @Qualifier("attendanceService")
	private AttendanceService	attendanceService;
	
	@Resource
	private EduResultService eduResultService;
	
	/**
	 * 출석하기(입실)
	 * @throws Exception 
	 */
	@RequestMapping(value="/enterClass")
	public String enterClass(AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(value="qrNum", defaultValue="", required=false) String qrNum) throws Exception {
		
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		String crsCreCd = UserBroker.getCreCrsCd(request);
		uuivo.setUserNo(userNo);
		uuivo = usrUserInfoService.view(uuivo);
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(crsCreCd);
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		request.setAttribute("createCourseVO", ccvo);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(DateTimeUtil.getCurrentDate());
		
		vo.setAttendDttm(today+"000001");
		vo.setUserNo(userNo);
		
		vo.setUserNm(uuivo.getUserNm());
		vo.setRegNo(userNo);
		vo.setCrsCreCd(crsCreCd);
		
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		
		
		//18시 이후일 시 입실 불가
		SimpleDateFormat timeFmt = new SimpleDateFormat("HHmmss");
		String currentTime = timeFmt.format(DateTimeUtil.getCurrentDate());
		int compare = currentTime.compareTo("180000");
		if(compare>0) {
			resultVO.setMessage("현재 입실 시간이 아닙니다.");
			resultVO.setResult(-1);
			return JsonUtil.responseJson(response, resultVO);
		}
		//qr번호 체크
		if(!qrNum.equals("")){
			if(qrNum.equals(ccvo.getCrsCreQrInNo())) {
				resultVO = attendanceService.enterClass(vo);
			} else {
				resultVO.setMessage("입실 번호가 일치하지 않습니다.");
				resultVO.setResult(-1);
				return JsonUtil.responseJson(response, resultVO);
			}
		} else {
			resultVO = attendanceService.enterClass(vo);
			
			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(crsCreCd);
			eduResultVO.setStdNo(stdNo);

			BookmarkVO iBookmarkVO = new BookmarkVO();
			iBookmarkVO.setScoreCategory("BOOKMARK");
			iBookmarkVO.setScoreSaveType("START");

			eduResultService.addEduResultSp(eduResultVO, iBookmarkVO);
		}
		
		return "home/lecture/attend/view_qr_pop";
	}
	
	/**
	 * 퇴실하기
	 * @throws Exception 
	 */
	@RequestMapping(value="/quitClass")
	public String quitClass(AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(value="qrNum", defaultValue="", required=false) String qrNum) throws Exception {
		
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setUserNo(userNo);
		uuivo = usrUserInfoService.view(uuivo);
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(crsCreCd);
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		request.setAttribute("createCourseVO", ccvo);
		
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		
		//18시 이전일 시 퇴실 불가
		SimpleDateFormat timeFmt = new SimpleDateFormat("HHmmss");
		String currentTime = timeFmt.format(DateTimeUtil.getCurrentDate());
		int compare = currentTime.compareTo("180000");
		if(compare<0) {
			resultVO.setMessage("18시 이전에는 퇴실이 불가능합니다.");
			resultVO.setResult(-1);
			return JsonUtil.responseJson(response, resultVO);
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(DateTimeUtil.getCurrentDate());
	
		vo.setAttendDttm(today+"000001");
		vo.setUserNo(userNo);
		vo.setUserNm(uuivo.getUserNm());
		vo.setModNo(userNo);
		
		
		//qr번호 체크
		if(!qrNum.equals("")){
			if(qrNum.equals(ccvo.getCrsCreQrOutNo())) {
				resultVO = attendanceService.quitClass(vo);
			} else {
				resultVO.setMessage("퇴실 번호가 일치하지 않습니다.");
				resultVO.setResult(-1);
				return JsonUtil.responseJson(response, resultVO);
			}
		} else {
			resultVO = attendanceService.quitClass(vo);
			
			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(crsCreCd);
			eduResultVO.setStdNo(stdNo);

			BookmarkVO iBookmarkVO = new BookmarkVO();
			iBookmarkVO.setScoreCategory("BOOKMARK");
			iBookmarkVO.setScoreSaveType("START");

			eduResultService.addEduResultSp(eduResultVO, iBookmarkVO);
		}
		
		return "home/lecture/attend/view_qr_pop";
	}
	
	/**
	 * qr스캐너 페이지
	 *
	 */
	@RequestMapping(value="/qrReader")
	public String qrReader(@RequestParam(value="gubun") String gubun, HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setAttribute("gubun", gubun);
		return "home/lecture/attend/qr_reader";
	}
	
	/**
	 * 강의실 외출/조퇴 popup
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/classOutingCheckPop")
	public String classOutingCheck(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "home/lecture/attend/class_outing_check_pop";
	}
	
	/**
	 * 조퇴하기
	 *
	 */
	@RequestMapping(value="/leftClass")
	public String leftClass(AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(value="leftTime") int leftTime) throws Exception {
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setUserNo(userNo);
		uuivo = usrUserInfoService.view(uuivo);
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(crsCreCd);
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		request.setAttribute("createCourseVO", ccvo);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(DateTimeUtil.getCurrentDate());
	
		vo.setAttendDttm(today+"000001");
		vo.setUserNo(userNo);
		vo.setUserNm(uuivo.getUserNm());
		vo.setModNo(userNo);
		vo.setCrsCreCd(crsCreCd);
		
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		if(attendanceService.viewAttend(vo).getReturnVO().getEnterFlag()==null) {
			resultVO.setMessage("입실 이후 조퇴가 가능합니다.");
			resultVO.setResult(-1);
			return JsonUtil.responseJson(response, resultVO);
		}
		
		resultVO = attendanceService.leftClass(vo, leftTime);
		
		//-- 자동 성적 처리 :
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(crsCreCd);
		eduResultVO.setStdNo(stdNo);

		BookmarkVO iBookmarkVO = new BookmarkVO();
		iBookmarkVO.setScoreCategory("BOOKMARK");
		iBookmarkVO.setScoreSaveType("START");

		eduResultService.addEduResultSp(eduResultVO, iBookmarkVO);
		
		return "home/lecture/attend/view_qr_pop";
	}
	
	/**
	 * 외출하기
	 *
	 */
	@RequestMapping(value="/outClass")
	public String outClass(AttendanceVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(value="outTime") int outTime, @RequestParam(value="inTime") int inTime) throws Exception {
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setUserNo(userNo);
		uuivo = usrUserInfoService.view(uuivo);
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(crsCreCd);
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		request.setAttribute("createCourseVO", ccvo);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String today = formatter.format(DateTimeUtil.getCurrentDate());
	
		vo.setAttendDttm(today+"000001");
		vo.setUserNo(userNo);
		vo.setUserNm(uuivo.getUserNm());
		vo.setModNo(userNo);
		vo.setCrsCreCd(crsCreCd);
		
		ProcessResultVO<AttendanceVO> resultVO = new ProcessResultVO<AttendanceVO>();
		if(attendanceService.viewAttend(vo).getReturnVO().getEnterFlag()==null) {
			resultVO.setMessage("입실 이후 외출이 가능합니다.");
			resultVO.setResult(-1);
			return JsonUtil.responseJson(response, resultVO);
		}
		
		attendanceService.outClass(vo, outTime, inTime);
		
		return "home/lecture/attend/view_qr_pop";
	}
	
}

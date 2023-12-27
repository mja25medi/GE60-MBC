package egovframework.edutrack.web.manage.lecture;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/bookmark")
public class LectureBookmarkManageController extends GenericController {

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService 			bookmarkService;

	@Autowired @Qualifier("studentService")
	private StudentService 			studentService;

	@ResponseBody
	@RequestMapping(value="/updateAttendSts")
	public ProcessResultVO<BookmarkVO> updateAttendSts(BookmarkVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		vo.checkStudyByAttendSts();
		
		ProcessResultVO<BookmarkVO> result = new ProcessResultVO<>();
		try {
			bookmarkService.updateAttendSts(vo);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("출결 처리 도중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateAllAttendSts")
	public ProcessResultVO<BookmarkVO> updateAllAttendSts(BookmarkVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		StudentVO studentVO = new StudentVO();
		studentVO.setCrsCreCd(vo.getCrsCreCd());
		List<StudentVO> stdList = studentService.listStudentForMng(studentVO);
		
		List<BookmarkVO> voList = new ArrayList<>();
		for(StudentVO student : stdList) {
			BookmarkVO bookmark = new BookmarkVO();
			bookmark.setStdNo(student.getStdNo());
			bookmark.setCrsCreCd(student.getCrsCreCd());
			bookmark.setAttendStsCd(vo.getAttendStsCd());
			bookmark.setUnitCd(vo.getUnitCd());
			bookmark.setSbjCd(vo.getSbjCd());
			bookmark.setRegNo(vo.getRegNo());
			bookmark.setModNo(vo.getModNo());
			bookmark.checkStudyByAttendSts();
			voList.add(bookmark);
		}
		
		ProcessResultVO<BookmarkVO> result = new ProcessResultVO<>();
		try {
			bookmarkService.updateAttendStsList(voList);
			result.setResultSuccess();
			result.setMessage("정상 처리 되었습니다.");
		} catch(Exception e) {
			result.setResultFailed();
			result.setMessage("출결 처리 도중 오류가 발생했습니다.");
		}
		return result;
	}

}	
package egovframework.edutrack.web.lecture.student;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;

@Controller
@RequestMapping(value="/lec/std")
public class StdLectureController extends GenericController {

	@Autowired @Qualifier("studentService")
	private StudentService			studentService;	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;

	@RequestMapping(value = "/stdMngMain")
	public String stdManageMain(StudentVO vo, HttpServletRequest request) throws Exception {
		request.setAttribute("paging", "Y");
		return "home/lecture/user/teacher/std_manage_main";
	}
	
	@RequestMapping(value = "/listStd")
	public String listStd(StudentVO vo, HttpServletRequest request) throws Exception {
		CreateCourseVO ccVO = new CreateCourseVO();
		ccVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		CreateCourseVO creCourse = createCourseService.viewCreateCourse(ccVO).getReturnVO();
		request.setAttribute("creCourseVO", creCourse);
		
		ProcessResultListVO<StudentVO> result = studentService.listStudentPageingForMng(vo);
		request.setAttribute("stdList", result.getReturnList());
		request.setAttribute("pageInfo", result.getPageInfo());
		
		return "home/lecture/user/teacher/std_manage_list_div";
	}
}
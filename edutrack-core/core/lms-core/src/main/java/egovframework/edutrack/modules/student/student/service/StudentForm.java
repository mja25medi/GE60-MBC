package egovframework.edutrack.modules.student.student.service;

import java.util.List;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;


/**
 * Xdoclet을 위한 태그
 * @struts:form name="studentForm"
 */
@SuppressWarnings("serial")
public class StudentForm{
	/**
	 * 과정 VO
	 */
	private CourseVO courseVO;
	
	/**
	 * 과정 리스트
	 */
	private List<CourseVO> courseList;
	
	/**
	 * 과정 개설 VO
	 */
	private CreateCourseVO createCourseVO;


	/**
	 * 과정 개설 리스트
	 */
	private List<CreateCourseVO> createCourseList;
	
	/**
	 * 수강 신청 VO
	 */
	private StudentVO studentVO;


	/**
	 * 수강 신청 리스트
	 */
	private List<StudentVO> studentList;

	
	
	private StudentExcelVO studentExcelVO;
	private List<StudentExcelVO> studentExcelList;
	
	private StudentResultVO studentResultVO;
	private List<StudentResultVO> studentResultList;

	/**
	 * 생성자
	 */
	public StudentForm() {
		studentVO = new StudentVO();
		studentExcelVO = new StudentExcelVO();
		createCourseVO = new CreateCourseVO();
		courseVO = new CourseVO();
		studentResultVO = new StudentResultVO();
	}

	/**
	 * @return the courseVO
	 */
	public CourseVO getCourseVO() {
		return courseVO;
	}

	/**
	 * @param courseVO the courseVO to set
	 */
	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	/**
	 * @return the courseList
	 */
	public List<CourseVO> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<CourseVO> courseList) {
		this.courseList = courseList;
	}

	/**
	 * @return the createCourseVO
	 */
	public CreateCourseVO getCreateCourseVO() {
		return createCourseVO;
	}

	/**
	 * @param createCourseVO the createCourseVO to set
	 */
	public void setCreateCourseVO(CreateCourseVO createCourseVO) {
		this.createCourseVO = createCourseVO;
	}

	/**
	 * @return the createCourseyList
	 */
	public List<CreateCourseVO> getCreateCourseList() {
		return createCourseList;
	}

	/**
	 * @param createCourseyList the createCourseyList to set
	 */
	public void setCreateCourseList(List<CreateCourseVO> createCourseList) {
		this.createCourseList = createCourseList;
	}

	/**
	 * @return the studentVO
	 */
	public StudentVO getStudentVO() {
		return studentVO;
	}

	/**
	 * @param studentVO the studentVO to set
	 */
	public void setStudentVO(StudentVO studentVO) {
		this.studentVO = studentVO;
	}

	/**
	 * @return the studentList
	 */
	public List<StudentVO> getStudentList() {
		return studentList;
	}

	/**
	 * @param studentList the studentList to set
	 */
	public void setStudentList(List<StudentVO> studentList) {
		this.studentList = studentList;
	}


	public StudentExcelVO getStudentExcelVO() {
		return studentExcelVO;
	}

	public void setStudentExcelVO(StudentExcelVO studentExcelVO) {
		this.studentExcelVO = studentExcelVO;
	}

	public List<StudentExcelVO> getStudentExcelList() {
		return studentExcelList;
	}

	public void setStudentExcelList(List<StudentExcelVO> studentExcelList) {
		this.studentExcelList = studentExcelList;
	}

	public StudentResultVO getStudentResultVO() {
		return studentResultVO;
	}

	public void setStudentResultVO(StudentResultVO studentResultVO) {
		this.studentResultVO = studentResultVO;
	}

	public List<StudentResultVO> getStudentResultList() {
		return studentResultList;
	}

	public void setStudentResultList(List<StudentResultVO> studentResultList) {
		this.studentResultList = studentResultList;
	}


}
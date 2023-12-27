package egovframework.edutrack.modules.course.createcourseteacher.service;


import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


public interface CreateCourseTeacherService {

	/**
	 * 개설 과정 강사 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	public ProcessResultListVO<TeacherVO> listTeacher(TeacherVO iTeacherVO) throws Exception;
	
	/**
	 * 개설 과정 강사 정보 조회
	 * 
	 * @return ProcessReslutVO
	 */
	public ProcessResultVO<TeacherVO> viewTeacher(TeacherVO iTeacherVO) throws Exception;

	/**
	 * 개설 과정 강사 등록
	 *  
	 * @reurn ProcessResultVO
	 */
	public ProcessResultVO<TeacherVO> addTeacher(TeacherVO iTeacherVO) throws Exception;
	
	/**
	 * 개설 과정 강사 
	 *  
	 * @reurn ProcessResultVO
	 */
	public	ProcessResultVO<TeacherVO> editTeacher(TeacherVO iTeacherVO) throws Exception;

	/**
	 * 과정 강사의 순서를 변경하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return
	 */
	public ProcessResultVO<?> sortTeacher(TeacherVO VO) throws Exception;
	
	/**
	 * 개설 과정 강사 삭제
	 *  
	 * @reurn ProcessResultVO
	 */
	public ProcessResultVO<TeacherVO> deleteTeacher(TeacherVO iTeacherVO) throws Exception;
	
	/**
	 * 개설 과정 강사 인지 확인
	 *  
	 * @reurn ProcessResultVO
	 */
	public	ProcessResultVO<TeacherVO> isTeacher(TeacherVO iTeacherVO) throws Exception;
	
	/**
	 * 강사/튜터의 권한이 있는 사용자중 과정 튜터/강사로 등록이 되어 있지 않은 
	 * 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	public ProcessResultListVO<UsrUserInfoVO> listSearch(TeacherVO VO) throws Exception;
	
	/**
	 * 회차 강사 및 튜터 수정
	 */
	public ProcessResultVO<TeacherVO> updateCrsCreTeacher(TeacherVO teacherVO);

	/**
	 * 강사 튜터 권한이 있는 모든 사용자 조회
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	public ProcessResultListVO<UsrUserInfoVO> listAllSearch(TeacherVO VO) throws Exception;

	public ProcessResultVO<TeacherVO> deleteTeacherAll(TeacherVO vo);

}
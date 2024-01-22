package egovframework.edutrack.modules.course.createcourseteacher.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("createCourseTeacherMapper")
public interface CreateCourseTeacherMapper {


	/**
	 * 개설 과정 강사 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<TeacherVO> listTeacher(TeacherVO iTeacherVO)   ;

	/**
	 * 개설 과정 강사 정보 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public TeacherVO selectTeacher(TeacherVO iTeacherVO)   ;

	/**
	 * 개설 과정 강사인지 확인
	 *
	 * @return ProcessReslutListVO
	 */
	public TeacherVO isTeacher(TeacherVO iTeacherVO)   ;


	/**
	 * 개설 과정 강사 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertTeacher(TeacherVO iTeacherVO)   ;

	/**
	 * 개설 과정 강사 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateTeacher(TeacherVO iTeacherVO)   ;

    /**
     * 개설 과정 강사 정보의 복수의 레코드를 Update하고 결과를 반환한다.
     * @param List<WordDictCtgrVO>
     */
	public int updateBatch(List<TeacherVO> itemArray)    ;

	/**
	 * 개설 과정 강사 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteTeacher(TeacherVO iTeacherVO)   ;

	/**
	 * 개설 과정 강사 모두 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteTeacherAll(TeacherVO iTeacherVO)   ;


	/**
	 * 과정에 등록된 강사/튜터의 정보를
	 * 개설과정으로 카피한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCopy(TeacherVO iTeacherVO)    ;

	/**
	 * 사용자 정보중 강사/튜터인 사용자의 목록중
	 * 과정 강사/튜터가 아닌 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @reurn ProcessResultVO
	 */
	
	public List<UsrUserInfoVO> listSearch(TeacherVO VO)   ;

	/**
	 * 회차 강사, 튜터 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCrsCreTeacher(TeacherVO teacherVO);

	public List<UsrUserInfoVO> listAllSearch(TeacherVO VO)   ;

}

package egovframework.edutrack.modules.course.course.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 과정 정보의 DB관련 처리를 하는 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 정보
 * </pre>
 *
 * @author MediopiaTech
 */
@Mapper("courseMapper")
public interface CourseMapper {


	/**
	 * 과정 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> list(CourseVO VO)  throws DataAccessException ;

	/**
	 * 과정 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> listPageing(CourseVO VO)
			throws DataAccessException ;
	/**
	 * 과정 목록을 조회  수를 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public int count(CourseVO VO)
			throws DataAccessException ;
	
	@SuppressWarnings("unchecked")
	public int countCourse(CourseVO VO)
			throws DataAccessException ;

	/**
	 * 과정 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> listForEnroll(CourseVO VO) throws DataAccessException ;

	
	/**
	 * 수강신청내역 조회
	 */
	public List<CourseVO> listUserStdCourse(CourseVO VO) throws DataAccessException;

	/**
	 * 과정 정보의 단일행 정보를 검색하여 반환한다.
	 *
	 * @param CourseCretermVO
	 * @return
	 */
	public CourseVO select(CourseVO VO) throws DataAccessException ;

	/**
	 * 과정 정보를 등록합니다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CourseVO VO) throws DataAccessException ;

	/**
	 * 과정 코드 조회
	 * 2013.12.10 고용노동 연수원용으로 전환.
	 * @param
	 * @return ProcessResultVO
	 */
	public String selectCrsCd() throws DataAccessException ;

	/**
	 * 과정 정보 수정
	 * @param CourseCretermVO
	 * @reurn ProcessResultVO
	 */
	public int update(CourseVO VO) throws DataAccessException ;

	/**
	 * 과정 정보 삭제
	 * @param CourseCretermVO
	 * @reurn ProcessResultVO
	 */
	public int delete(CourseVO VO) throws DataAccessException ;


	/**
	 * 과정명으로 중복확인
	 * @param CourseCretermVO
	 * @return
	 * @throws DataAccessException
	 */
	public CourseVO isDupulicateByCrsNm(CourseVO VO) throws DataAccessException ;

	/**
	 * 과정 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> listForEnrollPaging(CourseVO VO) throws DataAccessException ;

	/**
	 * 과정 목록수 반환
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int listForEnrollPagingCount(CourseVO VO) throws DataAccessException ;

	
	/**
	 * 과정 목록을 조회하여 반환한다. - 온라인
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> listSubInfo(CourseVO VO)
			throws DataAccessException ;

	/**
	 * 과정 목록을 조회하여 반환한다. - 오프라인
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> listSubInfoOffline(CourseVO VO)
			throws DataAccessException ;

	@SuppressWarnings("unchecked")
	public List<CourseVO> listUserCourse(String userNo)
			throws DataAccessException ;
	
	@SuppressWarnings("unchecked")
	public List<CourseVO> listStudentPaymentCourseByUserNo(CourseVO VO);
	
	public List<CourseVO> listForCourseStatus(CourseVO vo);
	
	
	/**
	 * 과정분류 목록을 조회하여 반환한다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CourseVO> listCourse(CourseVO VO)  throws DataAccessException ;

	@SuppressWarnings("unchecked")
	public List<SysFileVO> fileListCourse(CourseVO VO) throws DataAccessException ;;
}

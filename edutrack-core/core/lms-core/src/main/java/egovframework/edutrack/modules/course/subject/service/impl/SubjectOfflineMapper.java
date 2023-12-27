package egovframework.edutrack.modules.course.subject.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("subjectOfflineMapper")
public interface SubjectOfflineMapper {

	/**
	 * Select Key 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectKey()  throws DataAccessException ;
	/**
	 * 오프라인 과목 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OfflineSubjectVO> list(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;

	/**
	 * 오프라인 과목 페이징 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OfflineSubjectVO> listPageing(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;

	/**
	 * 오프라인 과목 페이징 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int count(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;

	
	/**
	 * 오프라인 과목 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public OfflineSubjectVO select(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;

	/**
	 * 오프라인 과목 목록 조회 (개설과정에 속하지 않은 과목만 검색 함)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OfflineSubjectVO> listSearch(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;

	/**
	 * 오프라인 과목 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;

	/**
	 * 오프라인 과목 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(OfflineSubjectVO iOfflineSubjectVO)  throws DataAccessException ;


	/**
	 * 오프라인 과목 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(OfflineSubjectVO iOfflineSubjectVO)   throws DataAccessException ;
}

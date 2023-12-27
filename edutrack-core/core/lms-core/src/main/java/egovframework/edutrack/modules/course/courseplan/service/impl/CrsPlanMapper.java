package egovframework.edutrack.modules.course.courseplan.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.courseplan.service.CrsPlanVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 년간 과정 계획 정보의 DB관련 처리를 하는 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 연간 과정 계획
 * </pre>
 *
 * @author MediopiaTech
 */
@Mapper("crsPlanMapper")
public interface CrsPlanMapper {


	/**
	 * 년간 과정 계획 정보의 단일행 정보를 검색하여 반환한다.
	 *
	 * @param CrsPlanVO
	 * @return
	 */
	public CrsPlanVO select(CrsPlanVO VO)  throws DataAccessException ;

	/**
	 * 년간 과정 계획 정보를 Insert하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CrsPlanVO VO)  throws DataAccessException ;

	/**
	 * 년간 과정 계획 정보의 단일 레코드를 Update하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @reurn ProcessResultVO
	 */
	public int update(CrsPlanVO VO)  throws DataAccessException ;

	/**
	 * 년간 과정 계획 정보의 단일 레코드를 Update 또는 Insert 하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @reurn ProcessResultVO
	 */
	public int marge(CrsPlanVO VO)  throws DataAccessException ;

	/**
	 * 년간 과정 계획 단일 항목 정보를 삭제 하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @reurn ProcessResultVO
	 */
	public int delete(CrsPlanVO VO)  throws DataAccessException ;

	/**
	 * 과정의 모든 과정 계획 정보를 삭제 하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(CrsPlanVO VO)  throws DataAccessException ;

	/**
	 * 과정 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
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
	public List<CourseVO> listPageing(CourseVO VO)
			throws DataAccessException ;

	/**
	 * 과정 목록수를 반환
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	public int count(CourseVO VO)
			throws DataAccessException ;	
}

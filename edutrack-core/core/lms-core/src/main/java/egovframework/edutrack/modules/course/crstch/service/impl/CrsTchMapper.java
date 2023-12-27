package egovframework.edutrack.modules.course.crstch.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.crstch.service.CrsTchVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 과정 강사 강사 정보의 DB관련 처리를 하는 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 강사 - 과정 강사 강사 정보
 * </pre>
 *
 * @author MediopiaTech
 */
@Mapper("crsTchMapper")
public interface CrsTchMapper {


	/**
	 * 과정 강사 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsTchVO> list(CrsTchVO VO)  throws DataAccessException ;

	/**
	 * 과정 강사 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<CrsTchVO> listPageing(CrsTchVO VO)
			throws DataAccessException ;

	/**
	 * 과정 강사 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public int count(CrsTchVO VO)
			throws DataAccessException ;

	/**
	 * 과정 강사 정보의 단일행 정보를 검색하여 반환한다.
	 *
	 * @param CrsTchVO
	 * @return
	 */
	public CrsTchVO select(CrsTchVO VO)  throws DataAccessException ;

	/**
	 * 과정 강사 정보를 Insert하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CrsTchVO VO)  throws DataAccessException ;

	/**
	 * 과정 강사 정보의 단일 레코드를 Update하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @reurn ProcessResultVO
	 */
	public int update(CrsTchVO VO)  throws DataAccessException ;

    /**
     * 과정 강사 정보의 복수의 레코드를 Update하고 결과를 반환한다.
     * @param List<WordDictCtgrVO>
     */
	public int updateBatch(List<CrsTchVO> itemArray)  throws DataAccessException ;


	/**
	 * 과정 강사 정보를 삭제하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @reurn ProcessResultVO
	 */
	public int delete(CrsTchVO VO)  throws DataAccessException ;

	/**
	 * 과정의 모든 강사 정보를 삭제하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(CrsTchVO VO)  throws DataAccessException ;

	/**
	 * 사용자 정보중 강사/튜터인 사용자의 목록중
	 * 과정 강사/튜터가 아닌 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @reurn ProcessResultVO
	 */
	@SuppressWarnings("unchecked")
	public List<UsrUserInfoVO> listSearch(CrsTchVO VO)  throws DataAccessException ;


}

package egovframework.edutrack.modules.course.oflnsbjtchtm.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.oflnsbjtchtm.service.OflnSbjTchTmVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("oflnSbjTchTmMapper")
public interface OflnSbjTchTmMapper  {


	/**
	 * 오프라인 과목 강사의 전체 목록을 조회하여 반환한다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OflnSbjTchTmVO> list(OflnSbjTchTmVO VO)  throws DataAccessException ;
	
	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<OflnSbjTchTmVO> listPageing(OflnSbjTchTmVO VO) 
			throws DataAccessException ;

	/**
	 * 오프라인 과목 강사의 페이징 목록수 반환
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public int count(OflnSbjTchTmVO VO) 
			throws DataAccessException ;
	
	/**
	 * 오프라인 과목 강사의 단일 항목을 조회하여 반환한다.
	 * @param OflnSbjTchTmVO
	 * @return
	 */
	public OflnSbjTchTmVO select(OflnSbjTchTmVO VO)  throws DataAccessException ;
	
	/**
	 * 오프라인 과목 강사를 등록하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @reurn ProcessResultVO
	 */
	public int insert(OflnSbjTchTmVO VO)  throws DataAccessException ;
	
	/**
	 * 오프라인 과목 강사 정보를 수정하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @reurn ProcessResultVO
	 */
	public int update(OflnSbjTchTmVO VO)  throws DataAccessException ;
	
	/**
	 * 오프라인 과목 강사 정보를 삭제하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @reurn ProcessResultVO
	 */
	public int delete(OflnSbjTchTmVO VO)  throws DataAccessException ;
	
	/**
	 * 오프라인 과목 강사 정보를 삭제하고 결과를 반환한다.
	 * @param OflnSbjTchTmVO
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(OflnSbjTchTmVO VO)  throws DataAccessException ;

}

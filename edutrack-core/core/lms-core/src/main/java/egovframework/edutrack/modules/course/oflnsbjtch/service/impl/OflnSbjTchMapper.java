package egovframework.edutrack.modules.course.oflnsbjtch.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.oflnsbjtch.service.OflnSbjTchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("oflnSbjTchMapper")
public interface OflnSbjTchMapper {

	
	/**
	 * 오프라인 과목 강사의 전체 목록을 조회하여 반환한다.
	 * @param VO
	 * @return
	 */
	
	public List<OflnSbjTchVO> list(OflnSbjTchVO VO)   ;
	
	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	
	public List<OflnSbjTchVO> listPageing(OflnSbjTchVO VO) 
			 ;

	/**
	 * 오프라인 과목 강사의 페이징 목록수 반환
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	
	public int count(OflnSbjTchVO VO) 
			 ;
	
	/**
	 * 오프라인 과목 강사의 단일 항목을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @return
	 */
	public OflnSbjTchVO select(OflnSbjTchVO VO) ;
	
	/**
	 * 오프라인 과목 강사를 등록하고 결과를 반환한다.
	 * @param OflnSbjTchVO
	 * @reurn ProcessResultVO
	 */
	public int insert(OflnSbjTchVO VO)   ;
	
	/**
	 * 오프라인 과목 강사 정보를 수정하고 결과를 반환한다.
	 * @param OflnSbjTchVO
	 * @reurn ProcessResultVO
	 */
	public int update(OflnSbjTchVO VO)   ;
	
	/**
	 * 오프라인 과목 강사 정보를 삭제하고 결과를 반환한다.
	 * @param OflnSbjTchVO
	 * @reurn ProcessResultVO
	 */
	public int delete(OflnSbjTchVO VO)   ;
	
	
	/**
	 * 오프라인 과목 강사의 전체 목록을 조회하여 반환한다.
	 * 과정평가를 위한 목록
	 * @param VO
	 * @return
	 */
	
	public List<OflnSbjTchVO> listForRate(OflnSbjTchVO VO)   ;

}

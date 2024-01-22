package egovframework.edutrack.modules.course.decls.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 개설 개설 과정 분반 분반의 DB관련 처리를 하는 클래스
 *
 * <pre>
 * <b>업  무 :</b> 개설 과정 분반 - 개설 개설 과정 분반 분반
 * </pre>
 *
 * @author MediopiaTech
 */
@Mapper("creCrsDeclsMapper")
public interface CreCrsDeclsMapper {

	/**
	 * 개설 과정 분반 목록을 조회하여 반환한다.
	 * : 전체 목록 조회
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<CreCrsDeclsVO> list(CreCrsDeclsVO VO)   ;

	/**
	 * 개설 과정 분반 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	
	public List<CreCrsDeclsVO> listPageing(CreCrsDeclsVO VO)
			;

	/**
	 * 개설 과정 분반 목록을 조회하여 반환한다.
	 * : 페이징 정보 포함(pageInfo)
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @
	 */
	
	public int count(CreCrsDeclsVO VO)
			;
	
	/**
	 * 개설 과정 분반 정보의 단일행 정보를 검색하여 반환한다.
	 *
	 * @param CreCrsDeclsVO
	 * @return
	 */
	public CreCrsDeclsVO select(CreCrsDeclsVO VO)   ;

	/**
	 * 개설 과정 분반 정보를 등록합니다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(CreCrsDeclsVO VO)   ;

	/**
	 * 개설 과정 분반 정보 삭제
	 * @param CreCrsDeclsVO
	 * @reurn ProcessResultVO
	 */
	public int delete(CreCrsDeclsVO VO)    ;

	/**
	 * 개설 과정 분반 정보 삭제
	 * @param CreCrsDeclsVO
	 * @reurn ProcessResultVO
	 */
	public int deleteAll(CreCrsDeclsVO VO)   ;

	/**
	 * 등록된 분반의 카운트를 반환한다.
	 * @param VO
	 * @return
	 */
	public int getCount(CreCrsDeclsVO VO)    ;

	/**
	 * 삭제되는 분반수강생으로 1분만으로 변경
	 * @param CreCrsDeclsVO
	 * @reurn ProcessResultVO
	 */
	public int declsUpdate(CreCrsDeclsVO VO)   ;

}

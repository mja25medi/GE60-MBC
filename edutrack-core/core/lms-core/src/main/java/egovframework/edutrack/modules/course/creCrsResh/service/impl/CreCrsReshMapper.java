package egovframework.edutrack.modules.course.creCrsResh.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("creCrsReshMapper")
public interface CreCrsReshMapper {

	/**
	 * 개설 과정 설문 목록 조회
	 * @param CreCrsReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CreCrsReshVO> list(CreCrsReshVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 페이징 목록 조회
	 * @param CreCrsReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CreCrsReshVO> listPageing(CreCrsReshVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 페이징 목록수 반환 
	 * @param CreCrsReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(CreCrsReshVO VO)  throws DataAccessException ;
	
	/**
	 * 개설 과정 설문 갯수 반환
	 * @param CreCrsReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int itemCount(CreCrsReshVO VO)  throws DataAccessException ;
	
	/**
	 * 개설 과정 설문 답변 갯수 반환
	 * @param CreCrsReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int ansrCount(CreCrsReshVO VO)  throws DataAccessException ;
	
	/**
	 * 개설 과정 설문 정보 조회
	 * @param VO
	 * @return
	 */
	public CreCrsReshVO select(CreCrsReshVO VO  )   throws DataAccessException ;


	/**
	 * 개설 과정 설문 등록
	 * @param VO
	 * @return
	 */
	public int insert(CreCrsReshVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 수정
	 * @param VO
	 * @return
	 */
	public int update(CreCrsReshVO VO)   throws DataAccessException ;

	/**
	 * 개설 과정 설문 삭제
	 * @param VO
	 * @return
	 */
	public int delete(CreCrsReshVO VO )  throws DataAccessException ;

}

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
	
	public List<CreCrsReshVO> list(CreCrsReshVO VO)   ;

	/**
	 * 개설 과정 설문 페이징 목록 조회
	 * @param CreCrsReshVO
	 * @return
	 */
	
	public List<CreCrsReshVO> listPageing(CreCrsReshVO VO)   ;

	/**
	 * 개설 과정 설문 페이징 목록수 반환 
	 * @param CreCrsReshVO
	 * @return
	 */
	
	public int count(CreCrsReshVO VO)   ;
	
	/**
	 * 개설 과정 설문 갯수 반환
	 * @param CreCrsReshVO
	 * @return
	 */
	
	public int itemCount(CreCrsReshVO VO)   ;
	
	/**
	 * 개설 과정 설문 답변 갯수 반환
	 * @param CreCrsReshVO
	 * @return
	 */
	
	public int ansrCount(CreCrsReshVO VO)   ;
	
	/**
	 * 개설 과정 설문 정보 조회
	 * @param VO
	 * @return
	 */
	public CreCrsReshVO select(CreCrsReshVO VO  )    ;


	/**
	 * 개설 과정 설문 등록
	 * @param VO
	 * @return
	 */
	public int insert(CreCrsReshVO VO)   ;

	/**
	 * 개설 과정 설문 수정
	 * @param VO
	 * @return
	 */
	public int update(CreCrsReshVO VO)    ;

	/**
	 * 개설 과정 설문 삭제
	 * @param VO
	 * @return
	 */
	public int delete(CreCrsReshVO VO )   ;

	/**
	 * 개설 과정 설문 수강생별 답변 삭제
	 * @param VO
	 * @return
	 */
	public void deleteAnswer(CreCrsReshVO ccrvo);

}

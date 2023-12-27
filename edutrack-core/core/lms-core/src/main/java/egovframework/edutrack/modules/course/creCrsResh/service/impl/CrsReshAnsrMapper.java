package egovframework.edutrack.modules.course.creCrsResh.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("crsReshAnsrMapper")
public interface CrsReshAnsrMapper {

	/**
	 * 개설 과정 설문 사용자 응답 목록 조회
	 * @param CrsReshAnsrVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsReshAnsrVO> list(CrsReshAnsrVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 응답 페이징 목록 조회
	 * @param CrsReshAnsrVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsReshAnsrVO> listPageing(CrsReshAnsrVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 응답 페이징 목록수 반환
	 * @param CrsReshAnsrVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(CrsReshAnsrVO VO)  throws DataAccessException ;
	
	/**
	 * 개설 과정 설문 사용자 응답 정보 조회
	 * @param VO
	 * @return
	 */
	public CrsReshAnsrVO select(CrsReshAnsrVO VO  )  throws DataAccessException ;


	/**
	 * 개설 과정 설문 사용자 응답 등록
	 * @param VO
	 * @return
	 */
	public int insert(CrsReshAnsrVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 응답 수정
	 * @param VO
	 * @return
	 */
	public int update(CrsReshAnsrVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 응답 삭제
	 * @param VO
	 * @return
	 */
	public int delete(CrsReshAnsrVO VO )  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 기타 응답 목록 조회
	 * @param CrsReshAnsrVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsReshAnsrVO> listOpinion(CrsReshAnsrVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 기타 응답 목록 조회-엑셀다운로드용
	 * @param CrsReshAnsrVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsReshAnsrVO> listExcel(CrsReshAnsrVO VO)  throws DataAccessException ;
	

	/**
	 * 개설 과정 설문 사용자 응답 목록 조회 (설문번호 없이)
	 * @param CrsReshAnsrVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsReshAnsrVO> listNoReshSn(CrsReshAnsrVO VO)  throws DataAccessException ;

	/**
	 * 개설 과정 설문 사용자 응답건수
	 * @param CrsReshAnsrVO
	 * @return
	 */
	public CrsReshAnsrVO listCount(CrsReshAnsrVO VO)  throws DataAccessException ;

}

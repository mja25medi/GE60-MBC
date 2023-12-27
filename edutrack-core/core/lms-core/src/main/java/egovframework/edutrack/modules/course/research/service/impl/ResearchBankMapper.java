package egovframework.edutrack.modules.course.research.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.research.service.ResearchBankVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("researchBankMapper")
public interface ResearchBankMapper {

	/**
	 * Select Key 조회
	 * @param VO
	 * @return
	 */
	public int selectKey()  throws DataAccessException ;
	/**
	 * 설문 은행 : 설문 목록 조회
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchBankVO> list(ResearchBankVO VO)   throws DataAccessException ;

	/**
	 * 설문 은행 : 설문 목록 조회
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchBankVO> listPageing(ResearchBankVO VO)  throws DataAccessException ;

	/**
	 * 설문 은행 : 설문 목록수 반환
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(ResearchBankVO VO)  throws DataAccessException ;
	
	/**
	 * 설문 은행 : 설문 목록 조회(설문명 검색)
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchBankVO> searchList(ResearchBankVO VO)  throws DataAccessException ;

	/**
	 * 설문 은행 : 설문 정보 조회
	 * @param VO
	 * @return
	 */
	public ResearchBankVO select(ResearchBankVO VO  )  throws DataAccessException ;

	/**
	 * 설문 은행 : 설문 정보 등록
	 * @param VO
	 * @return
	 */
	public int insert(ResearchBankVO VO)   throws DataAccessException ;

	/**
	 * 설문 은행 : 설문 정보 수정
	 * @param VO
	 * @return
	 */
	public int update(ResearchBankVO VO)  throws DataAccessException ;

	/**
	 * 설문 은행 : 설문 정보 삭제
	 * @param VO
	 * @return
	 */
	public int delete(ResearchBankVO VO )  throws DataAccessException ;


	/**
	 * 강의실 설문등록 폼에서 설문은행 목록 조회시 기등록된 설문은 제외하다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchBankVO> listResearchPreclusive(ResearchBankVO VO)  throws DataAccessException ;
}
package egovframework.edutrack.modules.course.research.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("researchBankItemMapper")
public interface ResearchBankItemMapper {

	/**
	 *  select Key 정보 조회
	 */
	public int selectKey()   ;
	/**
	 * 설문 문제 목록 조회
	 */
	
	public List<ResearchBankItemVO> list(ResearchBankVO iResearchBankVO )    ;

	/**
	 * 설문 문제 목록 조회
	 */
	
	public List<ResearchBankItemVO> list(ResearchBankItemVO iResearchBankVO )    ;

	/**
	 *  설문 문제 정보 조회
	 */
	public ResearchBankItemVO select(ResearchBankItemVO  iResearchBankItemVO )   ;

	/**
	 *  설문 문제 정보 등록
	 */
	public int insert(ResearchBankItemVO iResearchBankItemVO)   ;

	/**
	 *  설문 문제 정보 수정
	 */
	public int update(ResearchBankItemVO iResearchBankItemVO)   ;

	/**
	 *  설문 문제 정보 삭제
	 */
	public int delete(ResearchBankItemVO iResearchBankItemVO )   ;

	/**
	 * 설문 문제 레코드를 일괄 Update하고 결과를 반환한다.
	 * @param ResearchBankVO
	 * @return ProcessResultVO<ResearchBankVO>
	 */
	public int updateBatch(List<ResearchBankItemVO> itemArray)    ;


}

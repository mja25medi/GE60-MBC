package egovframework.edutrack.modules.course.creCrsResh.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("crsReshResultMapper")
public interface CrsReshResultMapper {

	/**
	 * 개설 과정 설문 결과 목록 조회
	 * @param CrsReshAnsrVO
	 * @return
	 */
	
	public List<CrsReshResultVO> list(CrsReshResultVO VO)   ;



}

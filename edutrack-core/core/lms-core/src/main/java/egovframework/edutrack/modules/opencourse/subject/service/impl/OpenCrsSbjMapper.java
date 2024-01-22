package egovframework.edutrack.modules.opencourse.subject.service.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("openCrsSbjMapper")
public interface OpenCrsSbjMapper  {

	/**
     * 공개강좌 연결과목 목록
     *
     * @return ProcessReslutListDTO
     */
	
	public List<OpenCrsSbjVO> list(OpenCrsSbjVO vo) ;

    /**
     * 공개강좌 연결과목 정보
     *
     * @return ProcessResultDTO
     */
	public OpenCrsSbjVO select(OpenCrsSbjVO vo) ;


    /**
     * 공개강좌 연결과목 정보 등록
     *
     * @reurn ProcessResultDTO
     */
	public int insert(OpenCrsSbjVO vo) ;

    /**
     * 공개강좌 연결과목 정보 수정
     *
     * @reurn ProcessResultDTO
     */
	public int update(OpenCrsSbjVO vo) ;

    /**
     * 공개강좌 연결과목 정보 일괄 수정
     * @param codeArray
     */
	public int updateBatch(List<OpenCrsSbjVO> itemArray) ;

    /**
     * 공개강좌 연결과목 정보 삭제
     *
     * @reurn ProcessResultDTO
     */
	public int delete(OpenCrsSbjVO vo) ;

    /**
     * 공개강좌 연결과목 정보 전체 삭제
     *
     * @reurn ProcessResultDTO
     */
	public int deleteAll(OpenCrsSbjVO vo) ;
}

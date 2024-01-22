package egovframework.edutrack.modules.course.subject.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("subjectCategoryMapper")
public interface SubjectCategoryMapper {

	/**
	 * Select Key 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectKey()   ;
	
	/**
	 * 과목 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<SubjectCategoryVO> list(SubjectCategoryVO iSubjectCategoryVO)    ;
	
	/**
	 * 과목 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<SubjectCategoryVO> listVer5(SubjectCategoryVO iSubjectCategoryVO)    ;

	/**
	 * 과목 분류 목록 조회 (하위 분류 목록이 있는 과목 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<SubjectCategoryVO> listSort(SubjectCategoryVO iSubjectCategoryVO)   ;

	/**
	 * 하위 과목 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<SubjectCategoryVO> listSub(SubjectCategoryVO iSubjectCategoryVO)   ;

	/**
	 * 과목 분류 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public SubjectCategoryVO select(SubjectCategoryVO iSubjectCategoryVO)   ;
	/**
	 * 과목 분류 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(SubjectCategoryVO iSubjectCategoryVO)   ;


	/**
	 * 과목 분류 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(SubjectCategoryVO iSubjectCategoryVO)   ;

	/**
	 * 과목 분류 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(SubjectCategoryVO iSubjectCategoryVO)   ;

	/**
	 * 과목 분류 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<SubjectCategoryVO> categoryArray)   ;
	
	/**
	 * 과목 분류 목록 조회 - 수강신청
	 * @param iSubjectCategoryVO
	 * @return
	 * @
	 */
	
	public List<SubjectCategoryVO> listForEnroll(SubjectCategoryVO iSubjectCategoryVO)    ;
	
}

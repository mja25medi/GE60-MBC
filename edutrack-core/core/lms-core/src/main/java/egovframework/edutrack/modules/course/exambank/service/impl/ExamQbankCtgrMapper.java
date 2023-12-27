package egovframework.edutrack.modules.course.exambank.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.exambank.service.ExamQbankCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("examQbankCtgrMapper")
public interface ExamQbankCtgrMapper {

	/**
	 * 시험 문제은행 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ExamQbankCtgrVO> list(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 하위 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ExamQbankCtgrVO> subList(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;
	
	/**
	 * 선택한 분류의 하위분류들의 ctgrcd 리스트 반환
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<String> selectUpperQbankCtgr(String ctgrCd)  throws DataAccessException ;
	
	/**
	 * 시험 문제은행 분류 정보 보회
	 *
	 * @return ProcessResultVO
	 */
	public ExamQbankCtgrVO select(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 분류 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 분류 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public ExamQbankCtgrVO selectCd()  throws DataAccessException ;


	/**
	 * 시험 문제은행 분류 코드 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;
	
	/**
	 * 하위분류들의 이름 같이수정 분류 코드 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateSubNames(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;

	/**
	 * 시험 문제은행 분류 코드 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(ExamQbankCtgrVO iExamQbankCategoryVO)  throws DataAccessException ;
}

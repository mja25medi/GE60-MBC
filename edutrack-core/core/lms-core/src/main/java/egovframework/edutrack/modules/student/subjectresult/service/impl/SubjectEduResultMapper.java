package egovframework.edutrack.modules.student.subjectresult.service.impl;

import java.util.List;

import egovframework.edutrack.modules.student.subjectresult.service.SubjectEduResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("subjectEduResultMapper")
public interface SubjectEduResultMapper {

	/**
	 * 학습 결과 목록 조회 (전체)
	 * 
	 * @return ProcessReslutListVO
	 */
	public List<SubjectEduResultVO> list(
			SubjectEduResultVO dto) throws Exception;

	/**
	 * 학습 결과 목록 조회 (페이징)
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<SubjectEduResultVO> listPageing(
			SubjectEduResultVO dto, int curPage, int listScale, int pageScale) throws Exception;
	
	/**
	 * 학습 결과 목록수 반환
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int count(
			SubjectEduResultVO dto) throws Exception;
	

	/**
	 * 학습 결과 정보 조회
	 *  
	 * @return ProcessResultVO
	 */
	public SubjectEduResultVO select(
			SubjectEduResultVO dto) throws Exception;

	/**
	 * 학습 결과 점수 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int merge(
			SubjectEduResultVO dto) throws Exception;
	
	/**
	 * 학습자 온라인 과목 점수 삭제
	 *  
	 * @reurn ProcessResultVO
	 */
	public int deleteOnlnSbjResult(SubjectEduResultVO iSubjectEduResultVO) throws Exception;
	
	/**
	 * 학습자 오프라인 과목 점수 삭제
	 *  
	 * @reurn ProcessResultVO
	 */
	public int deleteOflnSbjResult(SubjectEduResultVO iSubjectEduResultVO) throws Exception;

	/**
	 * 학습 결과 점수 저장 batch
	 * @param codeArray
	 */
	public int mergeBatch(List<SubjectEduResultVO> itemArray) throws Exception;
	
	
	/**
	 * 수강생 교육결과 저장 (프로시저 이용) batch
	 * @param SubjectEduResultVO 
	 */
	public int autoInserteduRslt(
			SubjectEduResultVO dto) throws Exception;
	
	/**
	 * 수강생 교육결과 저장 (프로시저 이용) batch
	 * @param SubjectEduResultVO 
	 */
	public int autoInserteduRsltBatch(List<SubjectEduResultVO> itemArray) throws Exception;


}
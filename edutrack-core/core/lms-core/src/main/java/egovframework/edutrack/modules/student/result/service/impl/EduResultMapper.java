package egovframework.edutrack.modules.student.result.service.impl;


import java.util.List;

import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("eduResultMapper")
public interface EduResultMapper {

	/**
	 * 학습결과 목록 조회 (전체)
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<EduResultVO> listResult(EduResultVO iEduResultVO) throws Exception;

	/**
	 * 학습결과 목록 조회 (페이징)
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<EduResultVO> listResultPaging(EduResultVO iEduResultVO) throws Exception;
	
	/**
	 * 학습결과 목록수 반환
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int count(EduResultVO iEduResultVO) throws Exception;
	

	/**
	 * 학습결과 정보 조회
	 *  
	 * @return 
	 */
	public EduResultVO selectResult(EduResultVO iEduResultVO) throws Exception;
	
	/**
	 * crm 학습성적 정보 조회(페이징)
	 *  
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<EduResultVO> listCrmEduResultPaging(EduResultVO iEduResultVO) throws Exception;
	
	/**
	 * crm 학습성적 정보 수 반환
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int countCrmEduResult(EduResultVO iEduResultVO) throws Exception;
	

	/**
	 * 학습결과 등록
	 *  
	 * @reurn 
	 */
	public int insertResult(EduResultVO iEduResultVO) throws Exception;
	

	/**
	 * 학습결과 수정
	 */
	public int updateResult(EduResultVO iEduResultVO) throws Exception;
	
	/**
	 * 수강생 수료 처리를 위한 수강생 목록 조회
	 * 수강생의 성적 및 나이순으로 오더를 해온다.
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<EduResultVO> listStudentScore(EduResultVO iEduResultVO) throws Exception;
	
	/**
	 * 프로시저를 이용한 자동 점수 등록
	 * 
	 * 
	 * @param EduResultVO 
	 * @return 
	 */
	public int insertEduResultSp(EduResultVO iEduResultVO);
	
	/**
	 * 프로시저를 이용한 자동 점수 등록(과정전체)
	 * 
	 * 
	 * @param EduResultVO 
	 * @reurn 
	 */
	public int insertEduResultCrsSp(EduResultVO iEduResultVO) throws Exception;
	
	
	/**
     * 수강생 교육결과 저장 (프로시저 이용)
     *
     * @reurn 
     */
	public int autoInserteduRslt(EduResultVO dto) throws Exception;

	
}
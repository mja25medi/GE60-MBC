package egovframework.edutrack.modules.course.category.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



public interface CourseCategoryService {

	/**
	 * 과정 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<CourseCategoryVO> listCategory(
			String orgCd, String parCrsCtgrCd) throws Exception;

	/**
	 * 과정 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<CourseCategoryVO> listCategory(
			String orgCd, String parCrsCtgrCd, String useYn) throws Exception;
	
	/**
	 * 과정 분류 목록 조회 (하위 과정 분류 있는 목록만)
	 * 
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public abstract ProcessResultListVO<CourseCategoryVO> listCategorySort(
			String orgCd, String parCrsCtgrCd) throws Exception;

	/**
	 * 하위 과정 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<CourseCategoryVO> listCategorySub(
			String orgCd, String parCrsCtgrCd) throws Exception;

	/**
	 * 과정 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<CourseCategoryVO> viewCategory(String crsCtgrCd) throws Exception;

	/**
	 * 과정 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<CourseCategoryVO> addCategory(
			CourseCategoryVO iCourseCategoryVO) throws Exception;

	/**
	 * 과정 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<CourseCategoryVO> editCategory(
			CourseCategoryVO iCourseCategoryVO) throws Exception;

	/**
	 * 과정 분류 삭제
	 * @param crsCtgrCd 삭제 대상 코드값
	 * @return
	 */
	public ProcessResultVO<?> deleteCategory(String crsCtgrCd) throws Exception;

	/**
	 * 과정 분류 정렬 순서 변경
	 * @param iSystemCodeVO
	 * @return
	 */
	public ProcessResultVO<?> sortCategory(CourseCategoryVO iCourseCategoryVO) throws Exception;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	public ProcessResultVO<CourseCategoryVO> transactionRollbackMethod(
			CourseCategoryVO iCourseCategoryVO) throws Exception;

}
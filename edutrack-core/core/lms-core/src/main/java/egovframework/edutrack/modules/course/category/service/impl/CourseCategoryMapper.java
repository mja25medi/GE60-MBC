package egovframework.edutrack.modules.course.category.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper("courseCategoryMapper")
public interface CourseCategoryMapper {


	/**
	 * 과정 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<CourseCategoryVO> listCategory(CourseCategoryVO iCourseCategoryVO) ;
	
	/**
	 * 과정 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<CourseCategoryVO> listCategoryVer5(CourseCategoryVO iCourseCategoryVO) ;

	/**
	 * 과정 분류 목록 조회 (하위 분류 목록이 있는 과정 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	public List<CourseCategoryVO> listCategorySort(CourseCategoryVO iCourseCategoryVO) ;
	
	/**
	 * 과정 분류 목록 조회 (하위 분류 목록이 있는 과정 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	public List<CourseCategoryVO> listCategorySortVer5(CourseCategoryVO iCourseCategoryVO) ;
	/**
	 * 하위 과정 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public List<CourseCategoryVO> listCategorySub(CourseCategoryVO iCourseCategoryVO) ;
	/**
	 * 과정 분류 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public CourseCategoryVO selectCategory(String crsCtgrCd) ;

	/**
	 * 과정 분류 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCategory(CourseCategoryVO iCourseCategoryVO) ;
	/**
	 * 과정 분류 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectCategoryCd() ;

	/**
	 * 과정 분류 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCategory(CourseCategoryVO courseCategoryVO) ;
	/**
	 * 과정 분류 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteCategory(String crsCtgrCd) ;

	/**
	 * 과정 분류 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCategoryBatch(List<CourseCategoryVO> categoryArray) ;


}

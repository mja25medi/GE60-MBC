package egovframework.edutrack.modules.course.category.service.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper("courseCategoryMapper")
public interface CourseCategoryMapper {


	/**
	 * 과정 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<CourseCategoryVO> listCategory(CourseCategoryVO iCourseCategoryVO) throws Exception;
	
	/**
	 * 과정 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<CourseCategoryVO> listCategoryVer5(CourseCategoryVO iCourseCategoryVO) throws Exception;

	/**
	 * 과정 분류 목록 조회 (하위 분류 목록이 있는 과정 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<CourseCategoryVO> listCategorySort(CourseCategoryVO iCourseCategoryVO) throws Exception;
	
	/**
	 * 과정 분류 목록 조회 (하위 분류 목록이 있는 과정 분류)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<CourseCategoryVO> listCategorySortVer5(CourseCategoryVO iCourseCategoryVO) throws Exception;
	/**
	 * 하위 과정 분류 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<CourseCategoryVO> listCategorySub(CourseCategoryVO iCourseCategoryVO) throws Exception;
	/**
	 * 과정 분류 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public CourseCategoryVO selectCategory(String crsCtgrCd) throws Exception;

	/**
	 * 과정 분류 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCategory(CourseCategoryVO iCourseCategoryVO) throws Exception;
	/**
	 * 과정 분류 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectCategoryCd() throws Exception;

	/**
	 * 과정 분류 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCategory(CourseCategoryVO courseCategoryVO) throws Exception;
	/**
	 * 과정 분류 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteCategory(String crsCtgrCd) throws Exception;

	/**
	 * 과정 분류 순서 변경
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCategoryBatch(List<CourseCategoryVO> categoryArray) throws Exception;


}

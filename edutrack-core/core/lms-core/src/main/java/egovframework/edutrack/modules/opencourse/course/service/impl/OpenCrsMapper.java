package egovframework.edutrack.modules.opencourse.course.service.impl;

import java.util.List;

import egovframework.edutrack.modules.opencourse.course.service.OpenCrsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("openCrsMapper")
public interface OpenCrsMapper {

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public String selectKey()  throws Exception;

	/**
	 * 공개강좌 목록
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OpenCrsVO> list(OpenCrsVO vo) throws Exception;

	/**
     * 공개강좌 페이징 목록
     *
     * @return ProcessReslutListVO
     */
	@SuppressWarnings("unchecked")
	public List<OpenCrsVO> listPageing(OpenCrsVO vo) throws Exception;
	
	/**
     * 공개강좌 페이징 목록수를 반환
     *
     * @return ProcessReslutListVO
     */
	@SuppressWarnings("unchecked")
	public int count(OpenCrsVO vo) throws Exception;
	

	/**
	 * 공개강좌 정보
	 *
	 * @return ProcessResultVO
	 */
	public OpenCrsVO select(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 정보 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(OpenCrsVO vo) throws Exception;

	/**
	 * 공개강좌 정보 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(OpenCrsVO vo) throws Exception;

    /**
     * 공개강좌 정보 일괄 수정
     * @param codeArray
     */
	public int updateBatch(List<OpenCrsVO> itemArray) throws Exception;

	/**
	 * 공개강좌 정보 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(OpenCrsVO vo) throws Exception;

	/**
	 * 홈페이지 공개강좌 목록
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OpenCrsVO> listCourse(OpenCrsVO vo) throws Exception;

	/**
     * 홈페이지 공개강좌 페이징 목록
     *
     * @return ProcessReslutListVO
     */
	@SuppressWarnings("unchecked")
	public List<OpenCrsVO> listCoursePageing(OpenCrsVO vo) throws Exception;

	/**
     * 홈페이지 공개강좌 페이징 목록수 반환
     *
     * @return ProcessReslutListVO
     */
	@SuppressWarnings("unchecked")
	public int listCoursePageingCount(OpenCrsVO vo) throws Exception;
	
	/**
	 * 과목 사용중인 공개강좌 목록
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OpenCrsVO> listSubInfo(OpenCrsVO vo) throws Exception;



}
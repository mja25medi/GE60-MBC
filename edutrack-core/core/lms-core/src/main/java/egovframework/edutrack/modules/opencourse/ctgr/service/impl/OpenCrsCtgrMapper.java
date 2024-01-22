package egovframework.edutrack.modules.opencourse.ctgr.service.impl;

import java.util.List;

import egovframework.edutrack.modules.opencourse.ctgr.service.OpenCrsCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("openCrsCtgrMapper")
public interface OpenCrsCtgrMapper {

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public String selectKey()  ;
	/**
	 * 공개강좌 분류 목록
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<OpenCrsCtgrVO> list(OpenCrsCtgrVO vo) ;

	/**
	 * 공개강좌 분류 정보
	 *
	 * @return ProcessResultVO
	 */
	public OpenCrsCtgrVO select(OpenCrsCtgrVO vo) ;

	/**
	 * 공개강좌 분류 정보 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(OpenCrsCtgrVO vo) ;

	/**
	 * 공개강좌 분류 정보 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int update(OpenCrsCtgrVO vo) ;

    /**
     * 공개강좌 분류 정보 일괄 수정
     * @param codeArray
     */
	public int updateBatch(List<OpenCrsCtgrVO> itemArray) ;

	/**
	 * 공개강좌 분류 정보 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int delete(OpenCrsCtgrVO vo) ;

}
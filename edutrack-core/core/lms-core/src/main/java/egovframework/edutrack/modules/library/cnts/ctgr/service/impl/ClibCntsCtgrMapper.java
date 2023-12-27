package egovframework.edutrack.modules.library.cnts.ctgr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.cnts.ctgr.service.ClibCntsCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("clibCntsCtgrMapper")
public interface ClibCntsCtgrMapper {

	/**
	 * 콘텐츠  분류의 분류를 목록으로 가져온다.
	 * @param CntsCtgrVO.orgCd
	 * @param CntsCtgrVO.userNo
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibCntsCtgrVO> list(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 콘텐츠  분류의 하위 분류를 목록으로 가져온다.
	 * @param CntsCtgrVO.orgCd
	 * @param CntsCtgrVO.userNo
	 * @param CntsCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibCntsCtgrVO> listSub(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 분류 정보를 조회한다.
	 * @param CntsCtgrVO.orgCd
	 * @param CntsCtgrVO.userNo
	 * @param CntsCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibCntsCtgrVO select(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int insert(ClibCntsCtgrVO vo)throws Exception;

	/**
	 * 분류 코드 조회를 생성해서 반환한다.
	 * @param CntsCtgrVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibCntsCtgrVO selectCtgrCd() throws Exception;

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int update(ClibCntsCtgrVO vo) throws Exception;
	
	/**
	 * 분류 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibCntsCtgrVO> itemArray) throws Exception;


	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param CntsCtgrVO.orgCd
	 * @param CntsCtgrVO.userNo
	 * @param CntsCtgrVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public int delete(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param CntsCtgrVO.orgCd
	 * @param CntsCtgrVO.userNo
	 * @param CntsCtgrVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteSub(ClibCntsCtgrVO vo) throws Exception;

}

package egovframework.edutrack.modules.olc.sharectgr.service.impl;

import java.util.List;

import egovframework.edutrack.modules.olc.sharectgr.service.OlcShareCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("olcShareCtgrMapper")
public interface OlcShareCtgrMapper {


	/**
	 * OLC 공유 분류를 TREE 형 목록으로 조회
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (선택적으로 입력, 최상위 분류부터 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OlcShareCtgrVO> listTree(OlcShareCtgrVO dto) throws Exception;

	/**
	 * OLC 공유 분류의 하위 분류를 목록으로 가져온다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OlcShareCtgrVO> listSub(OlcShareCtgrVO dto) throws Exception;

	/**
	 * OLC 공유 분류의 전체 목록으로 가져온다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<OlcShareCtgrVO> listSubAll(OlcShareCtgrVO dto) throws Exception;

	/**
	 *  공유 분류 정보를 조회한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public OlcShareCtgrVO select(OlcShareCtgrVO dto) throws Exception;

	/**
	 *  공유 분류를 등록하고 결과를 반환한다.
	 * @param OlcShareCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int insert(OlcShareCtgrVO dto) throws Exception;

	/**
	 *  공유 분류 코드 조회를 생성해서 반환한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @return ProcessResultVO
	 */
	public OlcShareCtgrVO selectCtgrCd() throws Exception;

	/**
	 *  공유 분류를 수정하고 결과를 반환한다.
	 * @param OlcShareCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int update(OlcShareCtgrVO dto) throws Exception;

	/**
	 *  공유 분류 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<OlcShareCtgrVO> itemArray) throws Exception;


	/**
	 *  공유 분류를 삭제하고 결과를 반환한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public int delete(OlcShareCtgrVO dto) throws Exception;

	/**
	 *  공유 하위 분류를 삭제하고 결과를 반환한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteSub(OlcShareCtgrVO dto) throws Exception;

}

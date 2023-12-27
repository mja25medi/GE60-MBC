package egovframework.edutrack.modules.olc.sharectgr.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OlcShareCtgrService {

	/**
	 * OLC 분류를 TREE 형 목록으로 조회
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (선택적으로 입력, 최상위 분류부터 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareCtgrVO> listTree(
			OlcShareCtgrVO vo) throws Exception;

	/**
	 * OLC 분류의 하위 분류를 목록으로 가져온다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareCtgrVO> listSub(
			OlcShareCtgrVO vo) throws Exception;

	/**
	 * 분류 정보를 조회한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareCtgrVO> view(OlcShareCtgrVO vo) throws Exception;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param OlcShareCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareCtgrVO> add(OlcShareCtgrVO vo) throws Exception;

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param OlcShareCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareCtgrVO> edit(OlcShareCtgrVO vo) throws Exception;

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareCtgrVO> delete(OlcShareCtgrVO vo) throws Exception;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareCtgrVO> deleteSub(
			OlcShareCtgrVO vo) throws Exception;

	/**
	 * OLC 분류 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(OlcShareCtgrVO vo,
			String moveType) throws Exception;

	/**
	 * OLC 공유 분류의 전체 목록으로 가져온다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareCtgrVO> listSubAll(
			OlcShareCtgrVO vo) throws Exception;

}
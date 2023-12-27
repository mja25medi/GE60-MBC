package egovframework.edutrack.modules.olc.olcctgr.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OlcCtgrService {

	/**
	 * OLC 분류를 TREE 형 목록으로 조회
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd (선택적으로 입력, 최상위 분류부터 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcCtgrVO> listTree(OlcCtgrVO vo) throws Exception;

	/**
	 * OLC 분류의 하위 분류를 목록으로 가져온다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcCtgrVO> listSub(OlcCtgrVO vo) throws Exception;

	/**
	 * 분류 정보를 조회한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcCtgrVO> view(OlcCtgrVO vo) throws Exception;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param OlcCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcCtgrVO> add(OlcCtgrVO vo) throws Exception;

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param OlcCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcCtgrVO> edit(OlcCtgrVO vo) throws Exception;

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcCtgrVO> delete(OlcCtgrVO vo) throws Exception;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcCtgrVO> deleteSub(OlcCtgrVO vo) throws Exception;

	/**
	 * OLC 분류 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(OlcCtgrVO vo, String moveType) throws Exception;
}
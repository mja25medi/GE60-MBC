package egovframework.edutrack.modules.library.cnts.ctgr.service;


import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibCntsCtgrService {

	/**
	 * 콘텐츠 분류를 목록으로 가져온다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibCntsCtgrVO> list(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 콘텐츠  분류의 하위 분류를 목록으로 가져온다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibCntsCtgrVO> listSub(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 분류 정보를 조회한다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsCtgrVO> view(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsCtgrVO> add(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsCtgrVO> edit(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsCtgrVO> delete(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param ClibCntsCtgrVO.orgCd
	 * @param ClibCntsCtgrVO.userNo
	 * @param ClibCntsCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsCtgrVO> deleteSub(ClibCntsCtgrVO vo) throws Exception;

	/**
	 * 콘텐츠  분류 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibCntsCtgrVO vo, String moveType) throws Exception;

}
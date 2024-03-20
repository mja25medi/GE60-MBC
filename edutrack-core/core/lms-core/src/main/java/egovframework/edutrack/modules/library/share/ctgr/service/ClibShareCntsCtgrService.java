package egovframework.edutrack.modules.library.share.ctgr.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibShareCntsCtgrService {

	/**
	 * 콘텐츠 분류를 트리 형태의 목록으로 가져온다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareCntsCtgrVO> list(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 콘텐츠  분류의 하위 분류를 목록으로 가져온다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareCntsCtgrVO> listSub(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 분류 정보를 조회한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareCntsCtgrVO> view(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareCntsCtgrVO> add(
			ClibShareCntsCtgrVO vo) throws Exception;
	
	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * (강사가 등록 시 개설과정코드로 등록)
	 * @param ClibShareCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareCntsCtgrVO> addWithCrsCreCd(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareCntsCtgrVO> edit(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareCntsCtgrVO> delete(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.parCtgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareCntsCtgrVO> deleteSub(
			ClibShareCntsCtgrVO vo) throws Exception;

	/**
	 * 콘텐츠  분류 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibShareCntsCtgrVO vo,
			String moveType) throws Exception;

}
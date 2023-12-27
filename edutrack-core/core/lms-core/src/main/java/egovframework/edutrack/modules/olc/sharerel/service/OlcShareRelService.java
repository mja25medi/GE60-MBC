package egovframework.edutrack.modules.olc.sharerel.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OlcShareRelService {

	/**
	 * OLC 공유 분류 관계 정보를 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listByCartrg(
			OlcShareRelVO vo) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCartrg(
			OlcShareRelVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCartrg(
			OlcShareRelVO vo, int curPage, int listScale) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCartrg(
			OlcShareRelVO vo, int curPage) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listByCtgr(
			OlcShareRelVO vo) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCtgr(
			OlcShareRelVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCtgr(
			OlcShareRelVO vo, int curPage, int listScale) throws Exception;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCtgr(
			OlcShareRelVO vo, int curPage) throws Exception;

	/**
	 * 공유 분류 관계 정보를 조회한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareRelVO> select(OlcShareRelVO vo) throws Exception;

	/**
	 * 공유 분류 관계 정보를 등록하고 결과를 반환한다.
	 * @param OlcShareRelVO
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareRelVO> insert(OlcShareRelVO vo) throws Exception;

	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareRelVO> delete(OlcShareRelVO vo) throws Exception;

	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * 분류 삭제시 연결된 카트리지 정보
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareRelVO> deleteCtgr(
			OlcShareRelVO vo) throws Exception;

	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * 분류 삭제시 연결된 카트리지 정보
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public abstract ProcessResultVO<OlcShareRelVO> deleteCartrg(
			OlcShareRelVO vo) throws Exception;


	/**
	 * OLC 지식공유 분류 관계 정보를 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listByCtgrKnow(
			OlcShareRelVO vo) throws Exception;

	/**
	 * OLC 지식공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<OlcShareRelVO> listPageingByCtgrKnow(
			OlcShareRelVO vo) throws Exception;



}
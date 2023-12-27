package egovframework.edutrack.modules.etc.event.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface EtcEventService {

	/**
	 * 이벤트 전체 목록을 조회한다.
	 * @param EtcEventVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcEventVO> list(EtcEventVO vo)
			throws Exception;

	/**
	 * 이벤트 페이징 목록을 조회한다.
	 * @param EtcEventVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcEventVO> listPageing(EtcEventVO vo,
			int pageIndex, int listScale, int pageScale) throws Exception;

	/**
	 * 이벤트 페이징 목록을 조회한다.
	 * @param EtcEventVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcEventVO> listPageing(EtcEventVO vo,
			int pageIndex, int listScale) throws Exception;

	/**
	 * 이벤트 페이징 목록을 조회한다.
	 * @param EtcEventVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcEventVO> listPageing(EtcEventVO vo,
			int pageIndex) throws Exception;

	/**
	 * 이벤트 상세 정보를 조회한다.
	 * @param EtcEventVO
	 * @return EtcEventVO
	 * @throws Exception
	 */
	public abstract EtcEventVO view(EtcEventVO vo) throws Exception;

	/**
	 * 이벤트 정보를 등록한다.
	 * @param EtcEventVO
	 * @return String
	 * @throws Exception
	 */
	public abstract ProcessResultVO add(EtcEventVO vo) throws Exception;

	/**
	 * 이벤트 정보를 수정한다.
	 * @param EtcEventVO
	 * @return int
	 * @throws Exception
	 */
	public abstract ProcessResultVO edit(EtcEventVO vo) throws Exception;

	/**
	 * 이벤트 정렬 순서를 변경한다.
	 * @param EtcEventVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sort(EtcEventVO vo) throws Exception;

	/**
	 * 이벤트 정보를 삭제 한다.
	 * @param EtcEventVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(EtcEventVO vo) throws Exception;

}
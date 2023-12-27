package egovframework.edutrack.modules.etc.banner.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface EtcBnnrService {

	/**
	 * 베너 전체 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcBnnrVO> list(EtcBnnrVO vo)
			throws Exception;

	/**
	 * 베너 페이징 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcBnnrVO> listPageing(EtcBnnrVO vo,
			int pageIndex, int listScale, int pageScale) throws Exception;

	/**
	 * 베너 페이징 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcBnnrVO> listPageing(EtcBnnrVO vo,
			int pageIndex, int listScale) throws Exception;

	/**
	 * 베너 페이징 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcBnnrVO> listPageing(EtcBnnrVO vo,
			int pageIndex) throws Exception;

	/**
	 * 베너 상세 정보를 조회한다.
	 * @param EtcBnnrVO
	 * @return EtcBnnrVO
	 * @throws Exception
	 */
	public abstract EtcBnnrVO view(EtcBnnrVO vo) throws Exception;

	/**
	 * 베너 정보를 등록한다.
	 * @param EtcBnnrVO
	 * @return String
	 * @throws Exception
	 */
	public abstract ProcessResultVO add(EtcBnnrVO vo) throws Exception;

	/**
	 * 베너 정보를 수정한다.
	 * @param EtcBnnrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract ProcessResultVO edit(EtcBnnrVO vo) throws Exception;

	/**
	 * 베너 정렬 순서를 변경한다.
	 * @param EtcBnnrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sort(EtcBnnrVO vo) throws Exception;

	/**
	 * 베너 정보를 삭제 한다.
	 * @param EtcBnnrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(EtcBnnrVO vo) throws Exception;

}
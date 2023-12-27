package egovframework.edutrack.modules.system.tpl.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface SysTplService {

	/**
	 *  시스템 템플릿 전체 목록을 조회한다.
	 * @param SysTplVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysTplVO> list(SysTplVO vo)
			throws Exception;

	/**
	 * 시스템 템플릿 페이징 목록을 조회한다.
	 * @param SysTplVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysTplVO> listPageing(SysTplVO vo,
			int pageIndex, int listScale, int pageScale) throws Exception;

	/**
	 * 시스템 템플릿 페이징 목록을 조회한다.
	 * @param SysTplVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysTplVO> listPageing(SysTplVO vo,
			int pageIndex, int listScale) throws Exception;

	/**
	 * 시스템 템플릿 페이징 목록을 조회한다.
	 * @param SysTplVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysTplVO> listPageing(SysTplVO vo,
			int pageIndex) throws Exception;

	/**
	 * 시스템 템플릿 상세 정보를 조회한다.
	 * @param SysTplVO
	 * @return SysTplVO
	 * @throws Exception
	 */
	public abstract SysTplVO view(SysTplVO vo) throws Exception;

	/**
	 * 시스템 템플릿 정보를 등록한다.
	 * @param SysTplVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(SysTplVO vo) throws Exception;

	/**
	 * 시스템 템플릿 정보를 수정한다.
	 * @param SysTplVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(SysTplVO vo) throws Exception;

	/**
	 * 시스템 템플릿의 순서를 변경한다.
	 * @param SysTplVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sort(SysTplVO vo) throws Exception;

	/**
	 * 시스템 템플릿 정보를 삭제 한다.
	 * @param SysTplVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(SysTplVO vo) throws Exception;

}
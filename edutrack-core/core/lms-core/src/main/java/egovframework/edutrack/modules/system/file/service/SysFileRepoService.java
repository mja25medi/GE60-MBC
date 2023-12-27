package egovframework.edutrack.modules.system.file.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface SysFileRepoService {

	/**
	 *  파일 리파지토리 전체 목록을 조회한다.
	 * @param SysFileRepoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysFileRepoVO> list(SysFileRepoVO vo)
			throws Exception;

	/**
	 * 파일 리파지토리 페이징 목록을 조회한다.
	 * @param SysFileRepoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysFileRepoVO> listPageing(
			SysFileRepoVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 파일 리파지토리 페이징 목록을 조회한다.
	 * @param SysFileRepoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysFileRepoVO> listPageing(
			SysFileRepoVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 파일 리파지토리 페이징 목록을 조회한다.
	 * @param SysFileRepoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysFileRepoVO> listPageing(
			SysFileRepoVO vo, int pageIndex) throws Exception;

	/**
	 * 파일 리파지토리 상세 정보를 조회한다.
	 * @param SysFileRepoVO
	 * @return SysFileRepoVO
	 * @throws Exception
	 */
	public abstract SysFileRepoVO view(SysFileRepoVO vo) throws Exception;

	/**
	 * 파일 리파지토리 정보를 등록한다.
	 * @param SysFileRepoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(SysFileRepoVO vo) throws Exception;

	/**
	 * 파일 리파지토리 정보를 수정한다.
	 * @param SysFileRepoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(SysFileRepoVO vo) throws Exception;

	/**
	 * 파일 리파지토리 정보를 삭제 한다.
	 * @param SysFileRepoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(SysFileRepoVO vo) throws Exception;

}
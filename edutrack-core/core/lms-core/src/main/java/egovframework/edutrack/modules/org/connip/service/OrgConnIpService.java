package egovframework.edutrack.modules.org.connip.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgConnIpService {

	/**
	 * 접속 IP 전체 목록을 조회한다.
	 * @param OrgConnIpVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgConnIpVO> list(OrgConnIpVO vo)
			throws Exception;

	/**
	 * 접속 IP 페이징 목록을 조회한다.
	 * @param OrgConnIpVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgConnIpVO> listPageing(
			OrgConnIpVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 접속 IP 페이징 목록을 조회한다.
	 * @param OrgConnIpVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgConnIpVO> listPageing(
			OrgConnIpVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 접속 IP 페이징 목록을 조회한다.
	 * @param OrgConnIpVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgConnIpVO> listPageing(
			OrgConnIpVO vo, int pageIndex) throws Exception;

	/**
	 * 접속 IP 상세 정보를 조회한다.
	 * @param OrgConnIpVO
	 * @return OrgConnIpVO
	 * @throws Exception
	 */
	public abstract OrgConnIpVO view(OrgConnIpVO vo) throws Exception;

	/**
	 * 접속 IP 정보를 등록한다.
	 * @param OrgConnIpVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgConnIpVO vo) throws Exception;

	/**
	 * 접속 IP 정보를 삭제 한다.
	 * @param OrgConnIpVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgConnIpVO vo) throws Exception;

}
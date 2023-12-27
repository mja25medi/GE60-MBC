package egovframework.edutrack.modules.org.domain.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgDomainService {

	/**
	 *  기관 도메인 전체 목록을 조회한다.
	 * @param OrgDomainVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgDomainVO> list(OrgDomainVO vo)
			throws Exception;

	/**
	 * 기관 도메인 페이징 목록을 조회한다.
	 * @param OrgDomainVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgDomainVO> listPageing(
			OrgDomainVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 기관 도메인 페이징 목록을 조회한다.
	 * @param OrgDomainVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgDomainVO> listPageing(
			OrgDomainVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 기관 도메인 페이징 목록을 조회한다.
	 * @param OrgDomainVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgDomainVO> listPageing(
			OrgDomainVO vo, int pageIndex) throws Exception;

	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgDomainVO
	 * @return OrgDomainVO
	 * @throws Exception
	 */
	public abstract OrgDomainVO view(OrgDomainVO vo) throws Exception;
	
	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgDomainVO
	 * @return OrgDomainVO
	 * @throws Exception
	 */
	public abstract OrgDomainVO viewByTypeCd(OrgDomainVO vo) throws Exception;
	

	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgDomainVO
	 * @return OrgDomainVO
	 * @throws Exception
	 */
	public abstract OrgDomainVO viewByServiceTypeCd(OrgDomainVO vo) throws Exception;


	/**
	 * 기관 도메인 정보를 등록한다.
	 * @param OrgDomainVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgDomainVO vo) throws Exception;

	/**
	 * 기관 도메인 정보를 수정한다.
	 * @param OrgDomainVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgDomainVO vo) throws Exception;

	/**
	 * 기관 도메인 정보를 삭제 한다.
	 * @param OrgDomainVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgDomainVO vo) throws Exception;
	
	/**
	 * 기관의 대표 도메인을 변경 한다.
	 * @param OrgDomainVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int change(OrgDomainVO vo) throws Exception;
	
}
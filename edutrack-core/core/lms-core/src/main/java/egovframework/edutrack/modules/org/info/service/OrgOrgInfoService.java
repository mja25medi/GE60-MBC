package egovframework.edutrack.modules.org.info.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgOrgInfoService {

	/**
	 *  기관 정보 전체 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgOrgInfoVO> list(OrgOrgInfoVO vo)
			throws Exception;

	/**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgOrgInfoVO> listPageing(
			OrgOrgInfoVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgOrgInfoVO> listPageing(
			OrgOrgInfoVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgOrgInfoVO> listPageing(
			OrgOrgInfoVO vo, int pageIndex) throws Exception;

	/**
	 * 기관 정보 상세 정보를 조회한다.
	 * @param OrgOrgInfoVO
	 * @return OrgOrgInfoVO
	 * @throws Exception
	 */
	public abstract OrgOrgInfoVO view(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 도메인으로 기관의 정보를 검색한다.
	 * @param OrgOrgInfoVO
	 * @return OrgOrgInfoVO
	 * @throws Exception
	 */
	public abstract OrgOrgInfoVO viewByDomain(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 등록한다.
	 * @param OrgOrgInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 기관 기본 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editInfo(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 기관 템플릿 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editTemplate(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 기관 템플릿 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editDesign(OrgOrgInfoVO vo) throws Exception;
	
	/**
	 * 기관 메타 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editMeta(OrgOrgInfoVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 삭제 한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgOrgInfoVO vo) throws Exception;
	
	/**
	 *  기관의 산업인력공단 API 사용여부를 조회한다.
	 * @param OrgOrgInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String getHrdApiUseYn(String orgCd);

}
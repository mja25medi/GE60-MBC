package egovframework.edutrack.modules.org.image.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgImgInfoService {

	/**
	 *  기관 이미지 전체 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgImgInfoVO> list(OrgImgInfoVO vo)
			throws Exception;

	/**
	 * 기관 이미지 페이징 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgImgInfoVO> listPageing(
			OrgImgInfoVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 기관 이미지 페이징 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgImgInfoVO> listPageing(
			OrgImgInfoVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 기관 이미지 페이징 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgImgInfoVO> listPageing(
			OrgImgInfoVO vo, int pageIndex) throws Exception;

	/**
	 * 기관 이미지 상세 정보를 조회한다.
	 * @param OrgImgInfoVO
	 * @return OrgImgInfoVO
	 * @throws Exception
	 */
	public abstract OrgImgInfoVO view(OrgImgInfoVO vo) throws Exception;

	/**
	 * 기관 이미지 정보를 등록한다.
	 * @param OrgImgInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgImgInfoVO vo) throws Exception;

	/**
	 * 기관 이미지 정보를 수정한다.
	 * @param OrgImgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgImgInfoVO vo) throws Exception;

	/**
	 * 기관 이미지 순서를 변경한다.
	 * @param OrgImgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sort(OrgImgInfoVO vo) throws Exception;
	
	/**
	 * 기관 이미지 정보를 삭제 한다.
	 * @param OrgImgInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgImgInfoVO vo) throws Exception;

}
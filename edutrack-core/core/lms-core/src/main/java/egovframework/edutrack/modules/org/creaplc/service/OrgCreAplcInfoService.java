package egovframework.edutrack.modules.org.creaplc.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;


public interface OrgCreAplcInfoService {

	/**
	 * 사이트 개설신청 목록을 조회한다.
	 * @param OrgCreAplcInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCreAplcInfoVO> list(OrgCreAplcInfoVO vo)
			throws Exception;
	
	/**
	 * 사이트 개설신청 페이징 목록을 조회한다.
	 * @param OrgCreAplcInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCreAplcInfoVO> listPageing(
			OrgCreAplcInfoVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 사이트 개설신청 페이징 목록을 조회한다.
	 * @param OrgCreAplcInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCreAplcInfoVO> listPageing(
			OrgCreAplcInfoVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 사이트 개설신청 페이징 목록을 조회한다.
	 * @param OrgCreAplcInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCreAplcInfoVO> listPageing(
			OrgCreAplcInfoVO vo, int pageIndex) throws Exception;

	/**
	 * 사이트 개설신청 상세 정보를 조회한다.
	 * @param OrgCreAplcInfoVO
	 * @return OrgCreAplcInfoVO
	 * @throws Exception
	 */
	public abstract OrgCreAplcInfoVO view(OrgCreAplcInfoVO vo) throws Exception;

	/**
	 * 사이트 개설신청 정보를 등록한다.
	 * @param OrgCreAplcInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgCreAplcInfoVO vo) throws Exception;
	public abstract int add(OrgCreAplcInfoVO vo, LogMsgLogVO msgVo) throws Exception;
	
	/**
	 * 사이트 개설신청 정보를 수정한다.
	 * @param OrgCreAplcInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int edit(OrgCreAplcInfoVO vo) throws Exception;

	/**
	 * 사이트 개설신청 정보를 삭제 한다.
	 * @param OrgCreAplcInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgCreAplcInfoVO vo) throws Exception;

	public abstract int editEmailCertYn(OrgCreAplcInfoVO vo) throws Exception;
}
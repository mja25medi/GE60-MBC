package egovframework.edutrack.modules.log.privateinfo.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface LogPrivateInfoService {
	
	
	/**
	 * 개인정보 조회 이력 목록을 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @return
	 */
	public abstract ProcessResultListVO<LogPrivateInfoInqLogVO> list(LogPrivateInfoInqLogVO vo) throws Exception;

	/**
	 * 개인정보 조회 이력 페이징 목록을 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public abstract ProcessResultListVO<LogPrivateInfoInqLogVO> listPageing(LogPrivateInfoInqLogVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 개인정보 조회 이력 단일 항목 정보를 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @return
	 */
	public abstract ProcessResultVO<LogPrivateInfoInqLogVO> view(LogPrivateInfoInqLogVO vo) throws Exception;

	/**
	 * 개인정보 조회 이력 정보를 저장하고 결과를 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @return
	 */
	public abstract int add(LogPrivateInfoInqLogVO vo) throws Exception;

}
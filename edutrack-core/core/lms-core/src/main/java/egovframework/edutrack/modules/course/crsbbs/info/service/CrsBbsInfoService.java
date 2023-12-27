package egovframework.edutrack.modules.course.crsbbs.info.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface CrsBbsInfoService {

	/**
	 * 과정 게시판 정보의 목록을 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultListVO<CrsBbsInfoVO> list(CrsBbsInfoVO VO) throws Exception;

	/**
	 * 과정 게시판 정보의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public abstract ProcessResultListVO<CrsBbsInfoVO> listPageing(
			CrsBbsInfoVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 과정 게시판 정보의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @return
	 */
	public abstract ProcessResultListVO<CrsBbsInfoVO> listPageing(
			CrsBbsInfoVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 과정 게시판 정보의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @return
	 */
	public abstract ProcessResultListVO<CrsBbsInfoVO> listPageing(
			CrsBbsInfoVO VO, int curPage) throws Exception;

	/**
	 * 과정 게시판의 정보를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<CrsBbsInfoVO> select(CrsBbsInfoVO VO) throws Exception;

	/**
	 * 과정 게시판의 정보를 등록하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<CrsBbsInfoVO> insert(CrsBbsInfoVO VO) throws Exception;

	/**
	 * 과정 게시판의 정보를 수정하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<CrsBbsInfoVO> update(CrsBbsInfoVO VO) throws Exception;

	/**
	 * 과정 게시판의 정보를 삭제하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<CrsBbsInfoVO> remove(CrsBbsInfoVO VO) throws Exception;

}
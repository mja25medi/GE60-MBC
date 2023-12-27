package egovframework.edutrack.modules.olc.olccnts.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OlcCntsService {

	/**
	 * OLC CNTS 의 모든 목록을 조회하여 반환한다.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	public abstract ProcessResultListVO<OlcCntsVO> list(OlcCntsVO vo) throws Exception;

	/**
	 * OLC CNTS 의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	public abstract ProcessResultListVO<OlcCntsVO> listPageing(
			OlcCntsVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * OLC의 CNTS 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	public abstract ProcessResultListVO<OlcCntsVO> listPageing(
			OlcCntsVO vo, int curPage, int listScale) throws Exception;

	/**
	 * OLC의 CNTS 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	public abstract ProcessResultListVO<OlcCntsVO> listPageing(
			OlcCntsVO vo, int curPage) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cntsCd
	 * @return
	 */
	public abstract ProcessResultVO<OlcCntsVO> view(OlcCntsVO vo) throws Exception;

	/**
	 * OLC CNTS 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	public abstract ProcessResultVO<OlcCntsVO> add(OlcCntsVO vo) throws Exception;

	/**
	 * OLC CNTS 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	public abstract ProcessResultVO<OlcCntsVO> edit(OlcCntsVO vo) throws Exception;

	/**
	 * OLC CNTS 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	public abstract ProcessResultVO<?> remove(OlcCntsVO vo) throws Exception;

	/**
	 * OLC CNTS 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(OlcCntsVO vo, String moveType) throws Exception;

}
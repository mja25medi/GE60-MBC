package egovframework.edutrack.modules.library.cnts.olc.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibOlcPageService {

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 전체 목록을 조회하여 반환한다.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultListVO<ClibOlcPageVO> list(ClibOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultListVO<ClibOlcPageVO> listPageing(
			ClibOlcPageVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultListVO<ClibOlcPageVO> listPageing(
			ClibOlcPageVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultListVO<ClibOlcPageVO> listPageing(
			ClibOlcPageVO vo, int curPage) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cntsCd
	 * @return
	 */
	public abstract ProcessResultVO<ClibOlcPageVO> view(ClibOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultVO<ClibOlcPageVO> add(ClibOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultVO<ClibOlcPageVO> edit(ClibOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public abstract ProcessResultVO<ClibOlcPageVO> remove(ClibOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보의 순서를 변경하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibOlcPageVO vo, String moveType) throws Exception;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보의 순서를 변경하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> sort(ClibOlcPageVO vo) throws Exception;

}
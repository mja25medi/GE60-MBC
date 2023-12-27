package egovframework.edutrack.modules.library.cnts.olc.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibOlcCntsService {

	/**
	 * 콘텐츠 라리브러리 : OLC의 모든 목록을 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.ctgrCd
	 * @return ProcessResultListVO<ClibOlcCntsVO>
	 */
	public abstract ProcessResultListVO<ClibOlcCntsVO> list(ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcCntsVO>
	 */
	public abstract ProcessResultListVO<ClibOlcCntsVO> listPageing(
			ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.cartrgCd
	 * @return
	 */
	public abstract ProcessResultVO<ClibOlcCntsVO> view(ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.cartrgCd
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return
	 */
	public abstract ProcessResultVO<ClibOlcCntsVO> view(ClibOlcCntsVO vo,
			boolean hitsup) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public abstract ProcessResultVO<ClibOlcCntsVO> add(ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public abstract ProcessResultVO<ClibOlcCntsVO> edit(ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 디자인 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public abstract ProcessResultVO<ClibOlcCntsVO> editDesign(ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public abstract ProcessResultVO<?> remove(ClibOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보의 순서를 변경하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibOlcCntsVO vo, String moveType) throws Exception;


	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠를 공유 콘텐츠로 복사한다.
	 * @param shareCd
	 * @param ClibOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibOlcCntsVO> addShare(
			ClibOlcCntsVO vo, String[] shareCd) throws Exception;

}
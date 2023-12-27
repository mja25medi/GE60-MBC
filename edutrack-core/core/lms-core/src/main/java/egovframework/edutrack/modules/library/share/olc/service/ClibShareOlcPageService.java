package egovframework.edutrack.modules.library.share.olc.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibShareOlcPageService {

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 전체 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> list(
			ClibShareOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listPageing(
			ClibShareOlcPageVO vo, int curPage) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listPageing(
			ClibShareOlcPageVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listPageing(
			ClibShareOlcPageVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcPageVO> view(
			ClibShareOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibShareOlcPageVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcPageVO> add(
			ClibShareOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibShareOlcPageVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcPageVO> edit(
			ClibShareOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcPageVO> delete(
			ClibShareOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 순서를 변경하고 결과를 반환한다.
	 * @param ClibShareOlcPageVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibShareOlcPageVO vo,
			String moveType) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 전체 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listByOrigin(
			ClibShareOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listByOriginPageing(
			ClibShareOlcPageVO vo, int curPage) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listByOriginPageing(
			ClibShareOlcPageVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcPageVO> listByOriginPageing(
			ClibShareOlcPageVO vo, int curPage, int listScale, int pageScale) throws Exception;
}

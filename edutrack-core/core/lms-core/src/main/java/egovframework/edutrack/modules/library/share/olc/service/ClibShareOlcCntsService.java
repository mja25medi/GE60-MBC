package egovframework.edutrack.modules.library.share.olc.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface ClibShareOlcCntsService {

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유 승인완료 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> list(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유 승인완료  페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listPageing(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listManage(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listManagePageing(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> view(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> add(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> edit(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> delete(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 순서를 변경하고 결과를 반환한다.
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibShareOlcCntsVO vo,
			String moveType) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listByOrigin(
			ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listByOriginPageing(
			ClibShareOlcCntsVO vo, int curPage) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listByOriginPageing(
			ClibShareOlcCntsVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareOlcCntsVO> listByOriginPageing(
			ClibShareOlcCntsVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유해제
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> updateNoShare(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유거절
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> rejectShare(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유신청관리
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareOlcCntsVO> updateShareCd(ClibShareOlcCntsVO vo) throws Exception;

}

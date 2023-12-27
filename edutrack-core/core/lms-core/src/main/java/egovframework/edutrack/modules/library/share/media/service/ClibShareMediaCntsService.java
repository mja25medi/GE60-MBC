package egovframework.edutrack.modules.library.share.media.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibShareMediaCntsService {

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> list(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listPageing(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listManage(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listManagePageing(
			ClibShareMediaCntsVO vo, int curPage) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listManagePageing(
			ClibShareMediaCntsVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listManagePageing(
			ClibShareMediaCntsVO vo, int curPage, int listScale, int pageScale) throws Exception;


	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> view(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> add(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> edit(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> delete(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibShareMediaCntsVO vo,
			String moveType) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listByOrigin(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listByOriginPageing(
			ClibShareMediaCntsVO vo, int curPage) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listByOriginPageing(
			ClibShareMediaCntsVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listByOriginPageing(
			ClibShareMediaCntsVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠  OLC제작용 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibShareMediaCntsVO> listOlc(
			ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유해제
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> updateNoShare(ClibShareMediaCntsVO clibShareMediaCntsVO) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유거절
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> rejectShare(ClibShareMediaCntsVO clibShareMediaCntsVO) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유신청관리
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibShareMediaCntsVO> updateShareCd(ClibShareMediaCntsVO clibShareMediaCntsVO) throws Exception;

}
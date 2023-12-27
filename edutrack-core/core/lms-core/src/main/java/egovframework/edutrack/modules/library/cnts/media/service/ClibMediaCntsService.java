package egovframework.edutrack.modules.library.cnts.media.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibMediaCntsService {

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibMediaCntsVO> list(
			ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibMediaCntsVO> listPageing(
			ClibMediaCntsVO vo, int curPage) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibMediaCntsVO> listPageing(
			ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibMediaCntsVO> view(ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 업로드 파일키를 이용하여 미디어 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibMediaCntsVO> viewByUploadFileKey(
			ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibMediaCntsVO> add(ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibMediaCntsVO> edit(ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibMediaCntsVO> delete(
			ClibMediaCntsVO vo) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<?> move(ClibMediaCntsVO vo,
			String moveType) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠를 공유 콘텐츠로 복사한다.
	 * @param shareCd
	 * @param ClibMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibMediaCntsVO> addShare(
			ClibMediaCntsVO vo, String[] shareCd) throws Exception ;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 OLC제작용 콘텐츠를 가져온다
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibMediaCntsVO> listOlc(
			ClibMediaCntsVO vo) throws Exception ;

}
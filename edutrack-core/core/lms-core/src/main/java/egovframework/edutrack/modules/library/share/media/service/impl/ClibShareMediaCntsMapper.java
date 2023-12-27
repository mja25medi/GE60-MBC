package egovframework.edutrack.modules.library.share.media.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("clibShareMediaCntsMapper")
public interface ClibShareMediaCntsMapper{

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> list(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> listPageing(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 페이징 목록수 반환
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public int listPageingCount(ClibShareMediaCntsVO vo) throws Exception;
	
	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> listManage(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> listManagePageing(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록수 반환
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public int listManagePageingCount(ClibShareMediaCntsVO vo) throws Exception;

	
	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일 항목 정보를 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibShareMediaCntsVO select(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 OLC제작용 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> listOlc(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일 항목 정보를 등록하고 결과를 반환한다.
	 * @param ClibShareMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int insert(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠의 콘텐츠키를 생성하여 반환한다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibShareMediaCntsVO selectCntsCd() throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일 항목 정보를 수정하고 결과를 반환한다.
	 * @param ClibShareMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int update(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 다중 항목 정보를 수정하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibShareMediaCntsVO> itemArray) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 항목 정보를 삭제하고 결과를 반환한다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.cntsCd
	 * @reurn ProcessResultVO
	 */
	public int delete(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> listByOrigin(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public List<ClibShareMediaCntsVO> listByOriginPageing(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	public int listByOriginPageingCount(ClibShareMediaCntsVO vo) throws Exception;
	
	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유해제
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public int updateNoShare(ClibShareMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유해제
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	public int updateShareMedia(ClibShareMediaCntsVO vo) throws Exception;




	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠의 콘텐츠키를 생성하여 반환한다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @return ProcessResultVO
	 */
//	public ClibShareMediaCntsVO selectCntsCd() throws Exception;

}

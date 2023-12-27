package egovframework.edutrack.modules.library.share.olc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("clibShareOlcCntsMapper")
public interface ClibShareOlcCntsMapper{

    /**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 공유 승인완료 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibShareOlcCntsVO> list(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 공유 승인완료 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibShareOlcCntsVO> listPageing(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 공유 승인완료 페이징 목록수 반환
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int listPageingCount(ClibShareOlcCntsVO vo) throws Exception;
	

    /**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibShareOlcCntsVO> listManage(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibShareOlcCntsVO> listManagePageing(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 페이징 목록수 반환.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int listManagePageingCount(ClibShareOlcCntsVO vo) throws Exception;


	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 단일 항목 정보를 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibShareOlcCntsVO select(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠의 콘텐츠키를 생성하여 반환한다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibShareOlcCntsVO selectCntsCd() throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 단일 항목 정보를 등록하고 결과를 반환한다.
	 * @param ClibShareOlcCntsVO
	 * @reurn ProcessResultVO
	 */
	public int insert(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 단일 항목 정보를 수정하고 결과를 반환한다.
	 * @param ClibShareMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int update(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 단일 항목 정보를 수정하고 결과를 반환한다.
	 * @param ClibShareMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibShareOlcCntsVO> itemArray) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 콘텐츠 항목 정보를 삭제하고 결과를 반환한다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.cntsCd
	 * @reurn ProcessResultVO
	 */
	public int delete(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 Olc 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibShareOlcCntsVO> listByOrigin(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 Olc콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibShareOlcCntsVO> listByOriginPageing(
			ClibShareOlcCntsVO vo) throws Exception;
	
	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 Olc콘텐츠 페이징 목록수 반환
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int listByOriginPageingCount(
			ClibShareOlcCntsVO vo) throws Exception;
	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유해제
	 * @param ClibShareOlcCntsVO
	 * @reurn ProcessResultVO
	 */
	public int updateNoShare(ClibShareOlcCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유해제
	 * @param ClibShareOlcCntsVO
	 * @reurn ProcessResultVO
	 */
	public int updateShareOlc(ClibShareOlcCntsVO vo) throws Exception;

	

}

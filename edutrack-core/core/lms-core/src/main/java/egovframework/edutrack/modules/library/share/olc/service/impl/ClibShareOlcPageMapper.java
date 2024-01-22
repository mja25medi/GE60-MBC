package egovframework.edutrack.modules.library.share.olc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("clibShareOlcPageMapper")
public interface ClibShareOlcPageMapper{

    /**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 전체 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	
	public List<ClibShareOlcPageVO> list(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	
	public List<ClibShareOlcPageVO> listPageing(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 페이징 목록수 반환
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	
	public int count(ClibShareOlcPageVO vo) ;
	
	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 단일 항목 정보를 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibShareOlcPageVO select(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지의 콘텐츠키를 생성하여 반환한다.
	 * @param method.orgCd
	 * @return ProcessResultVO
	 */
	public ClibShareOlcPageVO selectPageCd() ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 단일 항목 정보를 등록하고 결과를 반환한다.
	 * @param ClibShareOlcPageVO
	 * @reurn ProcessResultVO
	 */
	public int insert(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 단일 항목 정보를 수정하고 결과를 반환한다.
	 * @param ClibShareOlcPageVO
	 * @reurn ProcessResultVO
	 */
	public int update(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 단일 항목 정보를 수정하고 결과를 반환한다.
	 * @param ClibShareOlcPageVO
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibShareOlcPageVO> itemArray) ;

	/**
	 * 콘텐츠 라리브러리 : 공유 Olc 페이지 항목 정보를 삭제하고 결과를 반환한다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.cntsCd
	 * @reurn ProcessResultVO
	 */
	public int delete(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 Olc 페이지 전체 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	
	public List<ClibShareOlcPageVO> listByOrigin(ClibShareOlcPageVO vo) ;

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠 코드의 공유 Olc페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	
	public List<ClibShareOlcPageVO> listByOriginPageing(
			ClibShareOlcPageVO vo) ;


}

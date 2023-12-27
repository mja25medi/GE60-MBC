package egovframework.edutrack.modules.library.cnts.olc.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcCntsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("clibOlcCntsMapper")
public interface ClibOlcCntsMapper {

	/**
	 * 콘텐츠 라리브러리 : OLC의 모든 목록을 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.ctgrCd
	 * @return ProcessResultListVO<ClibOlcCntsVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ClibOlcCntsVO> list(ClibOlcCntsVO vo) throws Exception;

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
	@SuppressWarnings("unchecked")
	public List<ClibOlcCntsVO> listPageing(ClibOlcCntsVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC의 페이징된 목록수 반환
	 * 페이징 정보 포함.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcCntsVO>
	 */
	@SuppressWarnings("unchecked")
	public int count(ClibOlcCntsVO vo) throws DataAccessException;
	
	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.cartrgCd
	 * @return
	 */
	public ClibOlcCntsVO select(ClibOlcCntsVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public int insert(ClibOlcCntsVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠의 콘텐츠키를 생성하여 반환한다.
	 * @param ClibMediaCntsVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibOlcCntsVO selectCntsCd() throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public int update(ClibOlcCntsVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibOlcCntsVO> olcArray) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param ClibOlcCntsVO.cntsCd
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	public int delete(ClibOlcCntsVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : 해당글의 조회수를 1 증가시킨다.
	 *
	 * @param BbsAtclVO
	 */
	public void hitsup(ClibOlcCntsVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘탠츠를 공유 콘텐츠로 복사한다.
	 * @param ClibOlcCntsVO
	 * @reurn ProcessResultVO
	 */
	public int insertShare(ClibOlcCntsVO vo);

}

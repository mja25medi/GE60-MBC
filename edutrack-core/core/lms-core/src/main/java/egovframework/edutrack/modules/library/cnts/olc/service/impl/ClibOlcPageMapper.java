package egovframework.edutrack.modules.library.cnts.olc.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcPageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("clibOlcPageMapper")
public interface ClibOlcPageMapper {

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 모든 목록을 조회하여 반환한다.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ClibOlcPageVO> list(ClibOlcPageVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ClibOlcPageVO> listPageing(ClibOlcPageVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지의 페이징된 목록수 반환
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@SuppressWarnings("unchecked")
	public int count(ClibOlcPageVO vo) throws DataAccessException;
	
	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param ClibOlcPageVO.cntsCd
	 * @return
	 */
	public ClibOlcPageVO select(ClibOlcPageVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public int insert(ClibOlcPageVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지의 콘텐츠키를 생성하여 반환한다.
	 * @param ClibMediaCntsVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibOlcPageVO selectPageCd() throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public int update(ClibOlcPageVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibOlcPageVO> olcArray) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param ClibOlcPageVO.cartrgCd
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public int delete(ClibOlcPageVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠 페이지 정보 단일 레코드를 Delete하고 결과를 반환한다. - 콘텐츠 삭제시 해당 페이지 삭제
	 * @param ClibOlcPageVO.cartrgCd
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	public int deleteByCnts(ClibOlcPageVO vo) throws DataAccessException;

	/**
	 * 콘텐츠 라리브러리 : OLC 페이지를 공유 페이지로 복사한다.
	 * @param ClibOlcPageVO
	 * @reurn ProcessResultVO
	 */
	public int insertShare(ClibOlcPageVO vo) throws Exception;

}

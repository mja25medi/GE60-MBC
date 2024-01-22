package egovframework.edutrack.modules.olc.olccart.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("olcCartrgMapper")
public interface OlcCartrgMapper {

	/**
	 * OLC의 모든 목록을 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	
	public abstract List<OlcCartrgVO> list(OlcCartrgVO vo) ;

	/**
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	
	public abstract List<OlcCartrgVO> listPageing(
			OlcCartrgVO vo, int curPage, int listScale, int pageScale)
			;

	/**
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	
	public abstract int count(
			OlcCartrgVO vo)
			;
	
	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.cartrgCd
	 * @return
	 */
	public abstract OlcCartrgVO select(OlcCartrgVO vo)
			;
	/**
	 * cartrgCd값를 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.cartrgCd
	 * @return
	 */
	public abstract String selectKey()
			;

	/**
	 * OLC 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int insert(OlcCartrgVO vo)
			;

	/**
	 * OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int update(OlcCartrgVO vo)
			;

	/**
	 * OLC 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int updateBatch(List<OlcCartrgVO> olcArray) ;

	/**
	 * OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int delete(OlcCartrgVO vo)
			;

	/**
	 * OLC 공유정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int updateShare(OlcCartrgVO vo)
			;

	/**
	 * OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int updateShareCd(OlcCartrgVO vo)
			;

	/**
	 * 해당글의 조회수를 1 증가시킨다.
	 *
	 * @param BbsAtclVO
	 */
	public abstract void hitsup(OlcCartrgVO vo) ;


	/**
	 * OLC 정보 단일 레코드를 Update하고 결과를 반환한다. - 디자인 수정
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int updateDesing(OlcCartrgVO vo)
			;

	/**
	 * OLC 공유정보 단일 레코드를 Update하고 결과를 반환한다. - 콘텐츠공유
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int updateShareContents(OlcCartrgVO vo)
			;

	/**
	 * OLC 공유정보 단일 레코드를 Update하고 결과를 반환한다. - 지식공유
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public abstract int updateShareKnow(OlcCartrgVO vo)
			;

}
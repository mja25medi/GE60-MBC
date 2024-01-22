package egovframework.edutrack.modules.olc.olccnts.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("olcCntsMapper")
public interface OlcCntsMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public String selectKey()  ;

	/**
	 * OLC CNTS의 모든 목록을 조회하여 반환한다.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	
	public List<OlcCntsVO> list(OlcCntsVO vo) ;

	/**
	 * OLC CNTS의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	
	public List<OlcCntsVO> listPageing(OlcCntsVO vo) ;

	/**
	 * OLC CNTS의 페이징된 목록수 반환
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	
	public int count(OlcCntsVO vo) ;
	
	/**
	 * OLC CNTS 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param OlcCntsVO.cntsCd
	 * @return
	 */
	public OlcCntsVO select(OlcCntsVO vo) ;


	/**
	 * OLC CNTS 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	public int insert(OlcCntsVO vo) ;

	/**
	 * OLC CNTS 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	public int update(OlcCntsVO vo) ;

	/**
	 * OLC CNTS 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<OlcCntsVO> olcArray) ;

	/**
	 * OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param OlcCntsVO.cartrgCd
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	public int delete(OlcCntsVO vo) ;

}

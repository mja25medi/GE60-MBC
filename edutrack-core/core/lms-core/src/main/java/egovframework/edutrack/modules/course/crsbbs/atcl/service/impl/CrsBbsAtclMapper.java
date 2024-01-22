package egovframework.edutrack.modules.course.crsbbs.atcl.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("crsBbsAtclMapper")
public interface CrsBbsAtclMapper {
	
	/**
	 * select Key를 조회한다.
	 * @param VO
	 * @return
	 */
	public int selectKey()  ;
	/**
	 * 과정 게시판 게시물의 목록을 반환한다.
	 * @param VO
	 * @return
	 */
	
	public List<CrsBbsAtclVO> list(CrsBbsAtclVO VO)  ;
	
	/**
	 * 과정 게시판 게시물의 페이징 목록을 반환한다.
	 * @param VO
	 * @return
	 */
	
	public List<CrsBbsAtclVO> listPageing(CrsBbsAtclVO VO)  ;
	
	/**
	 * 과정 게시판 게시물의 페이징 목록수 반환
	 * @param VO
	 * @return
	 */
	
	public int count(CrsBbsAtclVO VO)  ;
	
	/**
	 * 과정 게시판 게시물 정보를 반환한다.
	 * @param VO
	 * @return
	 */
	public CrsBbsAtclVO select(CrsBbsAtclVO VO)  ;
	
	/**
	 * 과정 게시판의 게시물을 Insert 하고 결과를 리턴한다.
	 * @param VO
	 * @return
	 */
	public int insert(CrsBbsAtclVO VO)  ;

	/**
	 * 과정 게시판의 게시물을 Update 하고 결과를 리턴한다.
	 * @param VO
	 * @return
	 */
	public int update(CrsBbsAtclVO VO)  ;
	
	/**
	 * 과정 게시판의 게시물을 Update 하고 결과를 리턴한다.
	 * @param VO
	 * @return
	 */
	public int delete(CrsBbsAtclVO VO)  ;
	
	/**
	 * 과정 게시판 게시물의 조회수를 1 증가시킨다.
	 *
	 * @param BbsAtclVO
	 */
	public void hitsup(CrsBbsAtclVO VO)  ;
}

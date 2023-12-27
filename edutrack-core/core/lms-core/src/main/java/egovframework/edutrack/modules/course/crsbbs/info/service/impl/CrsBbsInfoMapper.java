package egovframework.edutrack.modules.course.crsbbs.info.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.course.crsbbs.info.service.CrsBbsInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("crsBbsInfoMapper")
public interface CrsBbsInfoMapper {


	/**
	 * 과정 게시판 정보 목록을 가져온다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsBbsInfoVO> list(CrsBbsInfoVO VO) throws DataAccessException ;
	
	/**
	 * 과정 게시판 정보 페이징 목록을 가져온다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrsBbsInfoVO> listPageing(CrsBbsInfoVO VO)  throws DataAccessException ;

	/**
	 * 과정 게시판 정보 페이징 목록을 가져온다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(CrsBbsInfoVO VO)  throws DataAccessException ;
	
    /**
	 * 게시판의 종류를 가져온다
	 * @parma BbsVO
	 * 
	 */
	public CrsBbsInfoVO select(CrsBbsInfoVO VO) throws DataAccessException ;
	
	

	/**
	 * 과정 게시판의 정보를 Insert 하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public int insert(CrsBbsInfoVO VO) throws DataAccessException ;
	
	/**
	 * 과정 게시판의 정보를 Update 하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public int update(CrsBbsInfoVO VO) throws DataAccessException ;
	
	/**
	 * 과정 게시판의 정보를 Delete하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public int delete(CrsBbsInfoVO VO) throws DataAccessException ;
	
	/**
	 * 과정 게시판의 전체 정보를 Delete하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public int deleteAll(CrsBbsInfoVO VO) throws DataAccessException ;

}

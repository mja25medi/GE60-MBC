package egovframework.edutrack.modules.log.privateinfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *
 */
@Mapper("logPrivateInfoInqLogMapper")
public interface LogPrivateInfoInqLogMapper {

	/**
	 * 개인정보 조회 이력 목록을 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @return
	 */
	
	public List<LogPrivateInfoInqLogVO> list(
			LogPrivateInfoInqLogVO vo) ;

	/**
	 * 개인정보 조회 이력 페이징 목록을 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<LogPrivateInfoInqLogVO> listPageing(
			LogPrivateInfoInqLogVO vo) ;

	/**
	 * 개인정보 조회 이력 페이징 목록수 반환.
	 * @param LogPrivateInfoInqLogVO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public int count(
			LogPrivateInfoInqLogVO vo) ;
	
	/**
	 * 개인정보 조회 이력 단일 항목 정보를 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @return
	 */
	
	public LogPrivateInfoInqLogVO select(
			LogPrivateInfoInqLogVO vo) ;

	/**
	 * 개인정보 조회 이력 정보를 저장하고 결과를 반환한다.
	 * @param LogPrivateInfoInqLogVO
	 * @return
	 */
	public int insert(LogPrivateInfoInqLogVO vo) ;



}

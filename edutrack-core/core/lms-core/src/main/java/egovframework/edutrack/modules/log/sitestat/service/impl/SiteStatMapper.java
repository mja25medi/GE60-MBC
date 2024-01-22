package egovframework.edutrack.modules.log.sitestat.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.sitestat.service.SiteStatVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 관리자 접속 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("siteStatMapper")
public interface SiteStatMapper  {

	/**
	 * 사이트 운영 현황 목록 조회
	 * @param  SiteStatVO 
	 * @return List
	 * @
	 */
	
	public List<SiteStatVO> list(SiteStatVO vo) ;
	
	/**
	 * 사이트 운영 현황 수
	 * @param  SiteStatVO 
	 * @return int
	 * @
	 */
	public int count(SiteStatVO vo) ;
	
    /**
	 * 사이트 운영 현황 목록 페이징
	 * @param  SiteStatVO 
	 * @return List
	 * @
	 */
	
	public List<SiteStatVO> listPageing(SiteStatVO vo) ;
}

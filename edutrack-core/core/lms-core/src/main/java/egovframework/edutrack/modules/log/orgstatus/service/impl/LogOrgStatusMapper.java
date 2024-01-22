package egovframework.edutrack.modules.log.orgstatus.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 기관 상태 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logOrgStatusMapper")
public interface LogOrgStatusMapper {

	/**
	 * 기관 상태 로그의 전체 목록을 조회한다. 
	 * @param  LogOrgStatusVO 
	 * @return List
	 * @
	 */
	
	public List<LogOrgStatusVO> list(LogOrgStatusVO vo) ;
	
    /**
	 * 기관 상태 로그의 검색된 수를 카운트 한다. 
	 * @param  LogOrgStatusVO 
	 * @return int
	 * @
	 */
	public int count(LogOrgStatusVO vo) ;
	
    /**
	 * 기관 상태 로그의 페이징 목록을 조회한다. 
	 * @param  LogOrgStatusVO 
	 * @return List
	 * @
	 */
	
	public List<LogOrgStatusVO> listPageing(LogOrgStatusVO vo) ;
	
    /**
	 * 시스템 총 현황 검색
	 * @param  LogOrgStatusVO 
	 * @return LogOrgStatusVO
	 * @
	 */
	public LogOrgStatusVO selectTotalStatus(LogOrgStatusVO vo) ;
	
    /**
	 * 사이트별 메인페이지 현황
	 * @param  LogOrgStatusVO 
	 * @return LogOrgStatusVO
	 * @
	 */
	public LogOrgStatusVO selectOrgStatus(LogOrgStatusVO vo) ;
	
	/**
	 * 사이트별 카운트 현황
	 * @param  LogOrgStatusVO 
	 * @return LogOrgStatusVO
	 * @
	 */
	public LogOrgStatusVO count1(LogOrgStatusVO vo) ;
}

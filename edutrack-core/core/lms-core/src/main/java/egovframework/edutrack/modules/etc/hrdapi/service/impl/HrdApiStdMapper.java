package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.util.List;

import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiSyncVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("hrdApiStdMapper")
public interface HrdApiStdMapper {
	/**
	 * api 회원 정보의 목록을 조회한다. 
	 * @param  HrdApiSyncVO 
	 * @return List
	 * @
	 */
	
	public List<EgovMap> listStdInfo(HrdApiStdVO vo)  ;
	
	/**
	 * api 년도 회원정보 건수 조회
	 * @param  HrdApiUserInfoVO 
	 * @return int
	 * @
	 */
	public int countStdInfo(HrdApiStdVO vo) ;

	/**
	 * api 회원 정보의 페이징 목록을 조회한다. 
	 * @param  HrdApiUserInfoVO 
	 * @return List
	 * @
	 */
	
	public List<EgovMap> listStdInfoPageing(HrdApiStdVO vo)  ;
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @
	 */
	public int insertHrdApiStd(HrdApiStdVO vo) ;
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @
	 */
	public int updateHrdApiStd(HrdApiStdVO vo) ;
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @
	 */
	public EgovMap countStdApiStatus(HrdApiStdVO vo) ;
}

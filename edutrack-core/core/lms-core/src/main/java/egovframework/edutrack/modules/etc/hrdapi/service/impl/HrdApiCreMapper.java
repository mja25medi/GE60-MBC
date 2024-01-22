package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("hrdApiCreMapper")
public interface HrdApiCreMapper {
	/**
	 * api 수업 정보의 목록을 조회한다. 
	 * @param  HrdApiCreVO 
	 * @return List
	 * @
	 */
	
	public List<EgovMap> listCreInfo(HrdApiCreVO vo)  ;
	
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @
	 */
	public List<EgovMap> listCreInfoApi(HrdApiCreVO infoVO) ; 
	
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @
	 */
	public HrdApiCreVO selectRecentOne(HrdApiCreVO infoVO) ; 
	
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @
	 */
	public int countCreInfo(HrdApiCreVO vo) ;
	
	/**
	 * api용 수업정보 조회 페이징
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @
	 */
	public List<EgovMap> listCreInfoPageing(HrdApiCreVO vo) ;
	
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiCreVO 
	 * @return int
	 * @
	 */
	public int updateHrdApiCre(HrdApiCreVO vo) ;
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @
	 */
	public List<HrdApiCreVO> listCreInfoData(HrdApiCreVO infoVO) ; 
	
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiCreVO 
	 * @return int
	 * @
	 */
	public int insertHrdApiCre(HrdApiCreVO vo) ;
	
}

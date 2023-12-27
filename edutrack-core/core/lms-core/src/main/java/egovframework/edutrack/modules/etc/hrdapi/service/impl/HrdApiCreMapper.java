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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> listCreInfo(HrdApiCreVO vo) throws Exception ;
	
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public List<EgovMap> listCreInfoApi(HrdApiCreVO infoVO) throws Exception; 
	
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @throws Exception
	 */
	public HrdApiCreVO selectRecentOne(HrdApiCreVO infoVO) throws Exception; 
	
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public int countCreInfo(HrdApiCreVO vo) throws Exception;
	
	/**
	 * api용 수업정보 조회 페이징
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public List<EgovMap> listCreInfoPageing(HrdApiCreVO vo) throws Exception;
	
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiCreVO 
	 * @return int
	 * @throws Exception
	 */
	public int updateHrdApiCre(HrdApiCreVO vo) throws Exception;
	
	/**
	 * api용 수업정보 조회
	 * @param  HrdApiCreVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public List<HrdApiCreVO> listCreInfoData(HrdApiCreVO infoVO) throws Exception; 
	
	
	/**
	 * api 년도 기수별  전송 수정
	 * @param  HrdApiCreVO 
	 * @return int
	 * @throws Exception
	 */
	public int insertHrdApiCre(HrdApiCreVO vo) throws Exception;
	
}

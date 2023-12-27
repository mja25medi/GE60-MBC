package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface HrdApiCreService { 
	/**
	 * api용 수강정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listCreInfo(HrdApiCreVO vo) throws Exception;
	
	/**
	 * api용 수업정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listCreInfoApi(HrdApiCreVO vo) throws Exception;
	
	
	/**
	 * api 수업 정보 페이징 목록 조회
	 * @param HrdApiCreVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listCreInfoPageing(HrdApiCreVO vo) throws Exception;
	
	
	/**
	 * 수강정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callCreInfoHrdApi(String jsonString, List<EgovMap> egovList, HrdApiCreVO vo) throws Exception;
}

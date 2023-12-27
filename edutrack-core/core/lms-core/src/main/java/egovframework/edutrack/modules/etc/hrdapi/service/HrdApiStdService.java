package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface HrdApiStdService { 
	/**
	 * api용 수강정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listStdInfo(HrdApiStdVO vo) throws Exception;
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiUserInfoVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listStdInfoPageing(HrdApiStdVO vo) throws Exception;
	
	/**
	 * 수강정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callStdInfoHrdApi(String jsonString, List<EgovMap> egovList,HrdApiStdVO vo) throws Exception;
	
	/**
	 * api용 수강정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract EgovMap countStdApiStatus(HrdApiStdVO vo) throws Exception;
}

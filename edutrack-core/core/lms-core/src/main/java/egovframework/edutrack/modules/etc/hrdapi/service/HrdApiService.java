package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface HrdApiService {

	/**
	 * hrd api call
	 * @param HrdApiVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callHrdApi(HrdApiVO vo) throws Exception;

	/**
	 * hrd restTemplate api call
	 * @param HrdApiVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callRestTemplateHrdApi(HrdApiVO vo) throws Exception;
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callUserInfoHrdApi(List<EgovMap> egovList,HrdApiSyncVO vo) throws Exception ;
	
	/**
	 *  api 정보 보내기 call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callHrdApiSend(List<EgovMap> egovList,HrdApiSyncVO vo) throws Exception ;
	
	
	/**
	 * api 전송 데이터 싱크
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public abstract int mergeUserInfoHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserInfoHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api 회원 로그인 정보 전송 데이터 싱크
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public abstract int mergeUserLoginHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api용 회원 로그인 정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserLoginHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api 전송 데이터 리스트 조회
	 * @param  HrdApiSyncVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EgovMap> listHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiSyncVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listPageingHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiSyncVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listPageingUserInfo(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserInfo(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api 회원 로그인 정보 페이징 목록 조회
	 * @param HrdApiSyncVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listPageingUserLogin(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * api용 회원 로그인 정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserLogin(HrdApiSyncVO vo) throws Exception;

	/**
	 * api 년도 기수별  전송 등록 - 컨텐츠
	 * @param hasvo
	 * @return int
	 */
	public abstract int mergeCntsHrdApiSync(HrdApiSyncVO hasvo);

	/**
	 * api용 컨텐츠 조회
	 * @param vo
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public abstract List<EgovMap> listCntsHrdApiSync(HrdApiSyncVO vo) throws Exception;
	
	/**
	 * otp용 진도차시 또는 평가횟수 평가타입 평가구분코드 구하기
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	public abstract HrdOtpVO getClassTmeEval(HrdOtpVO vo); 
}
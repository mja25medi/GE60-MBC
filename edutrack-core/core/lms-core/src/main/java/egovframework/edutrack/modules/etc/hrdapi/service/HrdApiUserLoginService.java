package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface HrdApiUserLoginService {

	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract EgovMap selectUserLogin(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserLogin(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api 전송 데이터 리스트 조회
	 * @param  HrdApiUserLoginVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EgovMap> listUserLoginResult(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiUserLoginVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listPageingUserLogin(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api용 회원정보 개수 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int countUserLogin(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api용 회원정보 등록
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int insertUserLogin(HrdApiUserLoginVO vo) throws Exception;

	/**
	 * api용 회원정보 수정
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int updateUserLogin(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api용 회원정보 삭제
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int deleteUserLogin(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserLoginHrdApiSync(HrdApiUserLoginVO vo) throws Exception;
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callUserLoginHrdApi(List<EgovMap> egovList,HrdApiUserLoginVO haulvo) throws Exception ;
	

}
package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface HrdApiUserInfoService {

	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract EgovMap selectUserInfo(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserInfo(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api 전송 데이터 리스트 조회
	 * @param  HrdApiUserInfoVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EgovMap> listUserInfoResult(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiUserInfoVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EgovMap> listPageingUserInfo(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api용 회원정보 개수 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int countUserInfo(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api용 회원정보 등록
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int insertUserInfo(HrdApiUserInfoVO vo) throws Exception;

	/**
	 * api용 회원정보 수정
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int updateUserInfo(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api용 회원정보 삭제
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract int deleteUserInfo(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> listUserInfoHrdApiSync(HrdApiUserInfoVO vo) throws Exception;
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<HrdApiVO> callUserInfoHrdApi(List<EgovMap> egovList,HrdApiUserInfoVO hauivo) throws Exception ;
		

}
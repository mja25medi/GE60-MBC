package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.util.List;

import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("hrdApiUserLoginMapper")
public interface HrdApiUserLoginMapper {

		/**
		 * api 년도 기수별  전송  조회
		 * @param  HrdApiUserLoginVO 
		 * @return EgovMap
		 * @
		 */
		public EgovMap selectUserLogin(HrdApiUserLoginVO vo) ; 
	
		/**
		 * api 년도 기수별 전송 리스트 조회
		 * @param  HrdApiUserLoginVO 
		 * @return List<EgovMap>
		 * @
		 */
		public List<EgovMap> listUserLogin(HrdApiUserLoginVO vo) ; 

		/**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiUserLoginVO 
		 * @return List
		 * @
		 */
		
		public List<EgovMap> listPageingUserLogin(HrdApiUserLoginVO vo)  ;
		
		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @
		 */
		public int countUserLogin(HrdApiUserLoginVO vo) ;
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @
		 */
		public int insertUserLogin(HrdApiUserLoginVO vo) ; 
		
		/**
		 * api 년도 기수별  전송 수정
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @
		 */
		public int updateUserLogin(HrdApiUserLoginVO vo) ; 
		
		/**
		 * api 년도 기수별  전송 삭제
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @
		 */
		public int deleteUserLogin(HrdApiUserLoginVO vo) ; 
		
		
		/**
		 * api용 회원정보 조회
		 * @param  HrdApiUserLoginVO 
		 * @return List<EgovMap>
		 * @
		 */
		public List<EgovMap> listUserLoginHrdApiSync(HrdApiUserLoginVO infoVO) ; 
		

}


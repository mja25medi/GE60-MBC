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
		 * @throws Exception
		 */
		public EgovMap selectUserLogin(HrdApiUserLoginVO vo) throws Exception; 
	
		/**
		 * api 년도 기수별 전송 리스트 조회
		 * @param  HrdApiUserLoginVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listUserLogin(HrdApiUserLoginVO vo) throws Exception; 

		/**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiUserLoginVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listPageingUserLogin(HrdApiUserLoginVO vo) throws Exception ;
		
		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @throws Exception
		 */
		public int countUserLogin(HrdApiUserLoginVO vo) throws Exception;
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @throws Exception
		 */
		public int insertUserLogin(HrdApiUserLoginVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 수정
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @throws Exception
		 */
		public int updateUserLogin(HrdApiUserLoginVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 삭제
		 * @param  HrdApiUserLoginVO 
		 * @return int
		 * @throws Exception
		 */
		public int deleteUserLogin(HrdApiUserLoginVO vo) throws Exception; 
		
		
		/**
		 * api용 회원정보 조회
		 * @param  HrdApiUserLoginVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listUserLoginHrdApiSync(HrdApiUserLoginVO infoVO) throws Exception; 
		

}


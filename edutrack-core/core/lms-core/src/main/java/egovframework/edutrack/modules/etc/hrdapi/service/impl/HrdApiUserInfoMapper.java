package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.util.List;

import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("hrdApiUserInfoMapper")
public interface HrdApiUserInfoMapper {

		/**
		 * api 년도 기수별  전송  조회
		 * @param  HrdApiUserInfoVO 
		 * @return EgovMap
		 * @throws Exception
		 */
		public EgovMap selectUserInfo(HrdApiUserInfoVO vo) throws Exception; 
	
		/**
		 * api 년도 기수별 전송 리스트 조회
		 * @param  HrdApiUserInfoVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listUserInfo(HrdApiUserInfoVO vo) throws Exception; 

		/**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiUserInfoVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listPageingUserInfo(HrdApiUserInfoVO vo) throws Exception ;
		
		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiUserInfoVO 
		 * @return int
		 * @throws Exception
		 */
		public int countUserInfo(HrdApiUserInfoVO vo) throws Exception;
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiUserInfoVO 
		 * @return int
		 * @throws Exception
		 */
		public int insertUserInfo(HrdApiUserInfoVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 수정
		 * @param  HrdApiUserInfoVO 
		 * @return int
		 * @throws Exception
		 */
		public int updateUserInfo(HrdApiUserInfoVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 삭제
		 * @param  HrdApiUserInfoVO 
		 * @return int
		 * @throws Exception
		 */
		public int deleteUserInfo(HrdApiUserInfoVO vo) throws Exception; 
		
		
		/**
		 * api용 회원정보 조회
		 * @param  HrdApiUserInfoVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listUserInfoHrdApiSync(HrdApiUserInfoVO infoVO) throws Exception; 
		

}


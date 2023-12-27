package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.util.List;

import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiSyncVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdOtpVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("hrdApiSyncMapper")
public interface HrdApiSyncMapper {

		/**
		 * api 년도 기수별 전송 리스트 조회
		 * @param  HrdApiSyncVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listHrdApiSync(HrdApiSyncVO vo) throws Exception; 

		/**
		 * api 년도 기수별  전송  조회
		 * @param  HrdApiSyncVO 
		 * @return EgovMap
		 * @throws Exception
		 */
		public EgovMap selectHrdApiSync(HrdApiSyncVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int insertHrdApiSync(HrdApiSyncVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 수정
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int updateHrdApiSync(HrdApiSyncVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 삭제
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int deleteHrdApiSync(HrdApiSyncVO vo) throws Exception; 
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int mergeUserInfoHrdApiSync(HrdApiSyncVO vo) throws Exception; 
		
		/**
		 * api용 회원정보 조회
		 * @param  HrdApiSyncVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listUserInfoHrdApiSync(HrdApiSyncVO infoVO) throws Exception; 
		
		/**
		 * api 년도 기수별 회원 로그인 정보 전송 등록
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int mergeUserLoginHrdApiSync(HrdApiSyncVO vo) throws Exception; 
		
		/**
		 * api용 회원 로그인 정보 조회
		 * @param  HrdApiSyncVO 
		 * @return List<EgovMap>
		 * @throws Exception
		 */
		public List<EgovMap> listUserLoginHrdApiSync(HrdApiSyncVO infoVO) throws Exception; 
		
		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int countHrdApiSync(HrdApiSyncVO vo) throws Exception;
		
	    /**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listPageingHrdApiSync(HrdApiSyncVO vo) throws Exception ;

		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int countUserInfo(HrdApiSyncVO vo) throws Exception;
		
		/**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listPageingUserInfo(HrdApiSyncVO vo) throws Exception ;
		
		/**
		 * api 회원 정보의 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listUserInfo(HrdApiSyncVO vo) throws Exception ;

		/**
		 * api 년도 회원 로그인 정보 건수 조회
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @throws Exception
		 */
		public int countUserLogin(HrdApiSyncVO vo) throws Exception;
		
		/**
		 * api 회원 로그인 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listPageingUserLogin(HrdApiSyncVO vo) throws Exception ;
		
		/**
		 * api 회원 로그인 정보의 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMap> listUserLogin(HrdApiSyncVO vo) throws Exception ;
		
		/**
		 * api 년도 기수별  전송 등록 - 컨텐츠
		 * @param vo
		 * @return
		 */
		public int mergeCntsHrdApiSync(HrdApiSyncVO vo);
		
		/**
		 * api용 컨텐츠 조회
		 * @param vo
		 * @return
		 * @throws Exception
		 */
		public List<EgovMap> listCntsHrdApiSync(HrdApiSyncVO vo) ;
		
		
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @throws Exception
		 */
		public HrdOtpVO getClassTmeUnit(HrdOtpVO vo) ;
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @throws Exception
		 */
		public HrdOtpVO getClassTmeExam(HrdOtpVO vo) ;
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @throws Exception
		 */
		public HrdOtpVO getClassTmeAsmt(HrdOtpVO vo) ;
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @throws Exception
		 */
		public HrdOtpVO getClassTmeSemiExam(HrdOtpVO vo) ;
		
		
}


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
		 * @
		 */
		public List<EgovMap> listHrdApiSync(HrdApiSyncVO vo) ; 

		/**
		 * api 년도 기수별  전송  조회
		 * @param  HrdApiSyncVO 
		 * @return EgovMap
		 * @
		 */
		public EgovMap selectHrdApiSync(HrdApiSyncVO vo) ; 
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int insertHrdApiSync(HrdApiSyncVO vo) ; 
		
		/**
		 * api 년도 기수별  전송 수정
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int updateHrdApiSync(HrdApiSyncVO vo) ; 
		
		/**
		 * api 년도 기수별  전송 삭제
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int deleteHrdApiSync(HrdApiSyncVO vo) ; 
		
		/**
		 * api 년도 기수별  전송 등록
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int mergeUserInfoHrdApiSync(HrdApiSyncVO vo) ; 
		
		/**
		 * api용 회원정보 조회
		 * @param  HrdApiSyncVO 
		 * @return List<EgovMap>
		 * @
		 */
		public List<EgovMap> listUserInfoHrdApiSync(HrdApiSyncVO infoVO) ; 
		
		/**
		 * api 년도 기수별 회원 로그인 정보 전송 등록
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int mergeUserLoginHrdApiSync(HrdApiSyncVO vo) ; 
		
		/**
		 * api용 회원 로그인 정보 조회
		 * @param  HrdApiSyncVO 
		 * @return List<EgovMap>
		 * @
		 */
		public List<EgovMap> listUserLoginHrdApiSync(HrdApiSyncVO infoVO) ; 
		
		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int countHrdApiSync(HrdApiSyncVO vo) ;
		
	    /**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @
		 */
		
		public List<EgovMap> listPageingHrdApiSync(HrdApiSyncVO vo)  ;

		/**
		 * api 년도 회원정보 건수 조회
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int countUserInfo(HrdApiSyncVO vo) ;
		
		/**
		 * api 회원 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @
		 */
		
		public List<EgovMap> listPageingUserInfo(HrdApiSyncVO vo)  ;
		
		/**
		 * api 회원 정보의 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @
		 */
		
		public List<EgovMap> listUserInfo(HrdApiSyncVO vo)  ;

		/**
		 * api 년도 회원 로그인 정보 건수 조회
		 * @param  HrdApiSyncVO 
		 * @return int
		 * @
		 */
		public int countUserLogin(HrdApiSyncVO vo) ;
		
		/**
		 * api 회원 로그인 정보의 페이징 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @
		 */
		
		public List<EgovMap> listPageingUserLogin(HrdApiSyncVO vo)  ;
		
		/**
		 * api 회원 로그인 정보의 목록을 조회한다. 
		 * @param  HrdApiSyncVO 
		 * @return List
		 * @
		 */
		
		public List<EgovMap> listUserLogin(HrdApiSyncVO vo)  ;
		
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
		 * @
		 */
		public List<EgovMap> listCntsHrdApiSync(HrdApiSyncVO vo) ;
		
		
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @
		 */
		public HrdOtpVO getClassTmeUnit(HrdOtpVO vo) ;
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @
		 */
		public HrdOtpVO getClassTmeExam(HrdOtpVO vo) ;
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @
		 */
		public HrdOtpVO getClassTmeAsmt(HrdOtpVO vo) ;
		/**
		 * otp용 진도차시 또는 평가횟수
		 * @param vo
		 * @return
		 * @
		 */
		public HrdOtpVO getClassTmeSemiExam(HrdOtpVO vo) ;
		
		
}


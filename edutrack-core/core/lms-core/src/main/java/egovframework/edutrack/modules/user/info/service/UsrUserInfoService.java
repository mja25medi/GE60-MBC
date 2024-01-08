package egovframework.edutrack.modules.user.info.service;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.annotation.HrdApiUsrUserInfo;
import egovframework.edutrack.comm.annotation.HrdApiUsrUserInfo.Type;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface UsrUserInfoService {

	/**
	 * 사용자 정보 전체 목록 조회
	 * @param UsrUserInfoVO vo
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<UsrUserInfoVO> list(UsrUserInfoVO vo)
			throws Exception;
	
	
	/**
	 * 사용자 상담 목록 조회
	 * @param UsrUserInfoVO vo
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<UsrUserInfoVO> listConsultPaging(UsrUserInfoVO vo) throws Exception;

	/**
	 * 사용자 정보 페이징 목록 조회
	 * @param UsrUserInfoVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<UsrUserInfoVO> listPageing(
			UsrUserInfoVO vo)
			throws Exception;


	/**
	 * 사용자 정보 정보 조회
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	public abstract UsrUserInfoVO view(UsrUserInfoVO vo) throws Exception;

	/**
	 * 사용자 정보를 가져온다. 로그인시에 사용
	 * - 사용자 상태가 U인 사용자만 가져온다.
	 * - 입력한 패스워드를 암호화 하여 리턴한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	public abstract UsrUserInfoVO viewForLogin(UsrUserInfoVO vo)
			throws Exception;
	
	/**
	 * 사용자 정보를 가져온다. 로그인시에 사용
	 * - 사용자 상태가 U인 사용자만 가져온다.
	 * - 입력한 패스워드를 암호화 하여 리턴한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	public abstract UsrUserInfoVO viewForLoginCheck(UsrUserInfoVO vo)
			throws Exception;
	
	/**
	 * 사용자 정보를 가져온다. 로그인시에 사용
	 * - 사용자 상태가 U인 사용자만 가져온다.
	 * - 입력한 패스워드를 암호화 하여 리턴한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	public abstract UsrUserInfoVO viewForSsoLogin(UsrUserInfoVO vo)
			throws Exception;

	/**
	 * 사용자 정보 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.CREATE)
	public abstract int add(UsrUserInfoVO vo, String userInfoChgDivCd)
			throws Exception;
	
	/**
	 * 사용자 상담 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  AbstractResult
	 */
	public abstract AbstractResult addConsult(UsrUserInfoVO vo) throws Exception;
	
	/**
	 * SSO 사용자 정보 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.CREATE)
	public abstract int ssoAdd(UsrUserInfoVO vo, String userInfoChgDivCd)
			throws Exception;

	/**
	 * 사용자 정보 수정
	 * @param UsrUserInfoVO vo
	 * @param int
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.UPDATE)
	public abstract int edit(UsrUserInfoVO vo, String userInfoChgDivCd)
			throws Exception;

	/**
	 * 사용자 정보 삭제
	 * @param UsrUserInfoVO vo
	 * @return
	 */
	@HrdApiUsrUserInfo(Type.DELETE)
	public abstract int remove(UsrUserInfoVO vo) throws Exception;

	/**
	 * 일반 사용자 일괄 등록
	 * @param List<UsrUserInfoVO> userList
	 * @return
	 */
	public abstract int addBatch(List<UsrUserInfoVO> userList) throws Exception;

	/**
	 * 사용자 이름과 이메일을 가지고 사용자의 ID를 검색한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	public abstract UsrUserInfoVO selectSearchId(UsrUserInfoVO vo)
			throws Exception;
	
	/**
	 * 사용자 ID와 PW를 가지고 사용자의 PW를 검색한다.
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	public abstract UsrUserInfoVO selectSearchPass(UsrUserInfoVO vo)
			throws Exception;
	

	/**
	 * 사용자의 권한을 삭제후 등록 한다.
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.UPDATE)
	public abstract int editAuth(UsrUserInfoVO vo, String userInfoChgDivCd)
			throws Exception;

	/**
	 * 회원탈퇴 처리
	 * @param vo
	 * @return
	 */
	@HrdApiUsrUserInfo(Type.DELETE)
	public abstract int joinOutMember(UsrUserInfoVO vo) throws Exception;

	/**
	 * DUP_INFO키를 이용하여 중복확인.
	 * @param vo
	 * @return
	 */
	public abstract int viewDupulicate(UsrUserInfoVO vo) throws Exception;

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	public abstract void sampleExcelDownload(HashMap<String, String> titles,
			OutputStream os, String orgCd) throws Exception;

	/**
	 * Upload 사용자 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public abstract ProcessResultListVO<UsrUserInfoVO> excelUploadValidationCheck(
			String fileName, String filePath, String orgCd,
			List<SysCodeVO> moblieCode) throws Exception;

	/**
	 * 사용자 명단 다운로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public abstract void listExcelDownload(UsrUserInfoVO userInfoDTO,
			String sheetName, String labelName, String condition,
			String excelHeader, ArrayList<String> titleList,
			HttpServletRequest request, OutputStream os,
			HashMap<String, String> titles) throws Exception;

    /**
	 * 사용자의 권한 목록을 조회 한다.
	 * @param UsrUserAuthGrpVO vo
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<UsrUserAuthGrpVO> listUserAuthGrp(
			UsrUserAuthGrpVO vo) throws Exception;

	
	/**
	 * SNS 를 통하여 사용자 등록
	 * @param UsrUserInfoVO vo
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.CREATE)
	public abstract String addUserSns(UsrUserInfoVO vo, String userInfoChgDivCd) throws Exception;
	
	/**
	 * 사용자 정보 등록
	 * @param UsrUserInfoVO vo
	 * @param userInfoChgDivCd
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.UPDATE)
	public abstract String updateSnsDiv(UsrUserInfoVO vo, String userInfoChgDivCd)
			throws Exception;
	
	/**
	 * 사용자 정보 수정
	 * @param UsrUserInfoVO vo
	 * @param int
	 * @return  ProcessResultDTO
	 */
	@HrdApiUsrUserInfo(Type.UPDATE)
	public abstract int editSso(UsrUserInfoVO vo, String userInfoChgDivCd)
			throws Exception;
	
	/**
	 * 사용자 등록 샘플 엑셀 파일 다운로드
	 * @param outputStream
	 * @param String
	 */
	public abstract void sampleExcelDownloadForUserAdd(OutputStream os, String orgCd) throws Exception;
	
	/**
	 * 엑셀 파일을 통한 유저 등록
	 * @param UsrUserInfoVO
	 * @param String
	 * @param String
	 * @param String
	 * @return ProcessResultListVO<UsrUserInfoVO>
	 */
	public abstract ProcessResultListVO<UsrUserInfoVO> excelUploadUserAdd(UsrUserInfoVO vo ,String fileName, String filePath, String orgCd) throws Exception;
	
	/**
	 * 사용자 정보 수정
	 * @param UsrUserInfoVO vo
	 * @return AbstractResult
	 */
	@HrdApiUsrUserInfo(Type.UPDATE)
	public abstract int editUserInfo(UsrUserInfoVO vo) throws Exception;
	
	/**
	 * 사용자 아바타정보 수정
	 * @param UsrUserInfoVO vo
	 * @return AbstractResult
	 */
	public abstract int editUserAvatar(UsrUserInfoVO vo) throws Exception;
	
	/**
	 * 이메일 중복 체크
	 * @param UsrUserInfoVO
	 * @return String
	 */
	public abstract String emailCheck(UsrUserInfoVO vo) throws Exception;
	

	/**
	 * 심사용 예외아이디 여부 체크
	 * @param UsrLoginVO vo
	 * @param int
	 * @return  ProcessResultVO
	 */
	public abstract String selectExceptionIdCheck(UsrLoginVO vo) throws Exception;
	
	/**
	 * api용 회원정보 조회
	 * @param UsrUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> selectUserInfoApi(UsrUserInfoVO vo) throws Exception ;
	
	public abstract int initVerify(UsrUserInfoVO vo) throws Exception;


	public abstract int oauthCheckId(Map<String, Object> paramMap) throws Exception;


	public abstract UsrUserInfoVO oauthLogin(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 강사 ide 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<UsrUserInfoVO> editTeacherUrl(UsrUserInfoVO vo) throws Exception;
	
	public abstract ProcessResultVO<UsrUserInfoVO> addTeacherIdeUrlExcel(UsrUserInfoVO vo, String fileName, String filePath);
}
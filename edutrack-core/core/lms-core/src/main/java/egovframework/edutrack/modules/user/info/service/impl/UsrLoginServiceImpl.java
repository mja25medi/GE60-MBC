package egovframework.edutrack.modules.user.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.security.KISASeed;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.userupdate.service.UserInfoLog;
import egovframework.edutrack.modules.log.userupdate.service.UserInfoLog.LogType;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoChgHstyVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>사용자 - 사용자의 로그인 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("usrLoginService")
public class UsrLoginServiceImpl 
	extends EgovAbstractServiceImpl implements UsrLoginService {

	@Resource(name="usrLoginMapper")
    private UsrLoginMapper 			usrLoginMapper;
    
    @Resource(name="usrUserInfoChgHstyMapper")
    private UsrUserInfoChgHstyMapper 	usrUserInfoChgHstyMapper;
    
	/**
	 * 사용자 아이디 중복 체크
	 * @param UsrLoginVO
	 * @return  ProcessResultDTO
	 */
	@Override
	public String userIdCheck(UsrLoginVO vo) throws Exception {
		return (String)usrLoginMapper.selectIdCheck(vo);
	}
	
	/**
	 * SSO 사용자 아이디 중복 체크
	 * @param UsrLoginVO
	 * @return  ProcessResultDTO
	 */
	@Override
	public String ssoUserIdCheck(UsrUserInfoVO vo) throws Exception {
		return (String)usrLoginMapper.selectSsoIdCheck(vo);
	}

	/**
	 * 사용자의 로그인 정보 조회
	 * @param UsrLoginVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrLoginVO select(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.select(vo);
	}
	/**
	 * 사용자 로그인 등록
	 * @param UsrLoginVO vo
	 * @return  String
	 */
	@Override
	public int add(UsrLoginVO vo) throws Exception {
		return (int)usrLoginMapper.insert(vo);
	}

	/**
	 * 사용자 로그인 수정
	 * @param UsrLoginVO vo
	 * @return  int
	 */
	@Override
	public int edit(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.update(vo);
	}

	/**
	 * 사용자 로그인 삭제
	 * @param UsrLoginVO vo
	 * @return int
	 */
	@Override
	public int remove(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.delete(vo);
	}

	/**
	 * 사용자의 마지막 접속 정보 수정
	 * - 접속횟수 및 마지막 접속일자 수정
	 * - 접속실패에 대한 정보 초기화
	 * @param UsrLoginVO vo
	 * @return  int
	 */
	@Override
	public int editLastLogin(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.updateLoginCount(vo);
	}

	/**
	 * 사용자의 로그인 실패에 대한 기록
	 * - 접속실패에 대한 정보 수정
	 * @param UsrLoginVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public UsrLoginVO editFailLogin(UsrLoginVO vo) throws Exception {
		usrLoginMapper.updateFailInfo(vo);
		return usrLoginMapper.select(vo);
	}

	/**
	 * 사용자의 암호를 변경한다.
	 * @param UsrLoginVO vo
	 * @return  int
	 */
	@Override
	public int editPass(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.updatePswd(vo);
	}

	/**
	 * 사용자의 사용상태를 변경한다.
	 * @param UsrLoginVO vo
	 * @return  int
	 */
	@Override
	public int editUserSts(UsrLoginVO vo, String userInfoChgDivCd) throws Exception {

		UsrUserInfoChgHstyVO uuichv = new UsrUserInfoChgHstyVO();
		uuichv.setUserNo(vo.getUserNo());
		uuichv.setRegNo(vo.getRegNo());
		uuichv.setUserInfoChgDivCd(userInfoChgDivCd); //-- 관리자 수정
		uuichv.setUserInfoCts(JsonUtil.getJsonString(vo));
		usrUserInfoChgHstyMapper.insert(uuichv);

		return usrLoginMapper.updateStatus(vo);
	}

	/**
	 * 비밀번호 초기화를 위한 비밀번호를 받아온다.
	 * 임의의 숫자 영문 조합으로 반환한다. (8자)
	 * @return
	 */
	@Override
	public String getNewPass() {
		return StringUtil.generateKeyString(8).toLowerCase();
	}

	/**
	 * 사용자의 비밀번호의 암호화 처리
	 * @param UsrLoginVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public int editEncriptPass(UsrLoginVO vo) throws Exception {
		if(ValidationUtils.isNotEmpty(vo.getUserPass()))
			vo.setUserPass(
//					KISASeed.seed256HashEncryption(vo.getUserPass())
					vo.getUserPass()
					);
		return usrLoginMapper.updatePswd(vo);
	}


	/**
	 * 사용자의 비밀번호 변경일자 연장 처리
	 * @param UsrLoginVO vo
	 * @return  ProcessResultDTO
	 */
	@Override
	public int editPassChgDate(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.updatePassDate(vo);
	}
	
	/**
	 * 사용자 아이디 중복 체크
	 * @param UsrLoginVO
	 * @return  ProcessResultDTO
	 */
	@Override
	public String snsDivCheck(UsrLoginVO vo) throws Exception {
		return (String)usrLoginMapper.selectIdCheck(vo);
	}

	/**
	 * 예외용 아이디 정보 업데이트
	 * @param UsrLoginVO vo
	 * @return int
	 */
	@UserInfoLog(LogType.UPDATE)
	@Override
	public int editExceptId(UsrLoginVO vo) throws Exception {
		return usrLoginMapper.updateExceptYn(vo);
	}
}

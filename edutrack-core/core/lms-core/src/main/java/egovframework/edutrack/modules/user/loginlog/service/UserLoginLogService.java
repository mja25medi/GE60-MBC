package egovframework.edutrack.modules.user.loginlog.service;

import egovframework.edutrack.comm.service.ProcessResultVO;

public interface UserLoginLogService {

	/**
	 * 로그인 로그 저장
	 * @param iPrintLogVO
	 * @return
	 * @throws Exception 
	 */
	public abstract ProcessResultVO<UserLoginLogVO> add(UserLoginLogVO idto) throws Exception;

}
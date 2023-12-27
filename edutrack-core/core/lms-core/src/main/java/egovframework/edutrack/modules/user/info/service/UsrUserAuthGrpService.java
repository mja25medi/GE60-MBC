package egovframework.edutrack.modules.user.info.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface UsrUserAuthGrpService {

	/**
	 * 사용자 권한 목록 조회
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO
	 * @throws Exception 
	 */
	public abstract ProcessResultListVO<UsrUserAuthGrpVO> list(UsrUserAuthGrpVO vo) throws Exception;

}
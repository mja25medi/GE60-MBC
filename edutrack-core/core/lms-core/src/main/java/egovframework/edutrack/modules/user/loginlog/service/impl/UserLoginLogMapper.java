package egovframework.edutrack.modules.user.loginlog.service.impl;

import egovframework.edutrack.modules.user.loginlog.service.UserLoginLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userLoginLogMapper")
public interface  UserLoginLogMapper {

	/**
	 * 사용자 로그인 정보 등록
	 * @param userLoginLogVO
	 * @return 
	 */
	public int insert(UserLoginLogVO vo) throws Exception;

	/**
	 * 사용자 로그 삭제
	 * @param userVO
	 * @return 
	 */
	public int delete(String loginId) throws Exception;

	/**
	 * 사용자 로그 삭제
	 * @param userVO
	 * @return 
	 */
	public int deleteAuto() throws Exception;

}
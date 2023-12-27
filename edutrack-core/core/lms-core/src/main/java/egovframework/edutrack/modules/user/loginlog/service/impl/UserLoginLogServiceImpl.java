package egovframework.edutrack.modules.user.loginlog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.user.loginlog.service.UserLoginLogService;
import egovframework.edutrack.modules.user.loginlog.service.UserLoginLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("userLoginLogService")
public class UserLoginLogServiceImpl extends EgovAbstractServiceImpl implements UserLoginLogService {
	
	/** Mapper */
	@Resource(name="userLoginLogMapper")
	private UserLoginLogMapper userLoginLogMapper;
	
	/**
	 * 로그인 로그 저장
	 * @param iPrintLogVO
	 * @return
	 * @throws Exception 
	 */
	public	ProcessResultVO<UserLoginLogVO> add(UserLoginLogVO vo) throws Exception  {
		ProcessResultVO<UserLoginLogVO> resultVO = new ProcessResultVO<UserLoginLogVO>(); 
		try {
			userLoginLogMapper.deleteAuto();
			userLoginLogMapper.insert(vo);
			resultVO.setResult(1);
			resultVO.setReturnVO(vo);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
}
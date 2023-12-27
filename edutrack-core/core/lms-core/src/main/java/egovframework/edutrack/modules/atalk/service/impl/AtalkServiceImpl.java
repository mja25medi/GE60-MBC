package egovframework.edutrack.modules.atalk.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.modules.atalk.AtalkVO;
import egovframework.edutrack.modules.atalk.service.AtalkService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("atalkService")
public class AtalkServiceImpl 
	extends EgovAbstractServiceImpl implements AtalkService {
	
	@Resource(name="atalkMapper")
	private AtalkMapper 		atalkMapper;
	
	/**
	 * 알림톡을 등록한다.
	 * @param AtalkVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String addAtalk(AtalkVO vo) throws Exception {
		vo.setTranSenderKey(Constants.ATALK_SENDER_KEY);
		int insert = atalkMapper.insert(vo);
		String result = Integer.toString(insert);
		return result;
	}
	
}

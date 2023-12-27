package egovframework.edutrack.modules.atalk.service;

import egovframework.edutrack.modules.atalk.AtalkVO;

public interface AtalkService {

	/**
	 * 알림톡 발송 문건을 등록한다.
	 * @param AtalkVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String addAtalk(AtalkVO vo) throws Exception;

}
package egovframework.edutrack.modules.course.creCrsResh.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface CrsReshResultService {

	/**
	 * 개설 과정 설문 결과 목록 조회
	 */
	public abstract ProcessResultListVO<CrsReshResultVO> list(
			CrsReshResultVO VO) throws Exception;

}
package egovframework.edutrack.modules.student.sync.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface EaiSyncService {

	/**
	 * Eai 연동 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EaiNkoreducompsendVO> list(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception;

	/**
	 * Eai 연동 목록 조회 (페이징)
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EaiNkoreducompsendVO> list(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * Eai 연동 초기화
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<EaiNkoreducompsendVO> editReset(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception;

}
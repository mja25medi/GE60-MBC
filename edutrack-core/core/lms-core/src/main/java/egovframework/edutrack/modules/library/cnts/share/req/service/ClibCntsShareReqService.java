package egovframework.edutrack.modules.library.cnts.share.req.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ClibCntsShareReqService {


	/**
	 * 콘텐츠 공유요청 목록으로 가져온다.
	 * @param ClibCntsShareReqVO.cntsCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultListVO<ClibCntsShareReqVO> list(ClibCntsShareReqVO vo) throws Exception;


	/**
	 *  콘텐츠 공유요청 정보를 조회한다.
	 * @param ClibCntsShareReqVO.cntsCd
	 * @return ProcessReslutListVO
	 */
	public abstract ProcessResultVO<ClibCntsShareReqVO> view(ClibCntsShareReqVO vo) throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsShareReqVO> add(ClibCntsShareReqVO vo) throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsShareReqVO> edit(ClibCntsShareReqVO vo) throws Exception;


	/**
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ClibCntsShareReqVO> delete(ClibCntsShareReqVO vo) throws Exception;

}

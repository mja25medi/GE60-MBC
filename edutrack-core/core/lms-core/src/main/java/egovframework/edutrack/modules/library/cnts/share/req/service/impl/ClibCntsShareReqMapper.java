package egovframework.edutrack.modules.library.cnts.share.req.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("clibCntsShareReqMapper")
public interface ClibCntsShareReqMapper {

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	/**
	 * 콘텐츠 공유요청 목록으로 가져온다.
	 * @param ClibCntsShareReqVO.cntsCd
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibCntsShareReqVO> list(ClibCntsShareReqVO vo)  throws Exception;

	/**
	 *  콘텐츠 공유요청 정보를 조회한다.
	 * @param ClibCntsShareReqVO.cntsCd
	 * @return ProcessReslutListVO
	 */
	public ClibCntsShareReqVO select(ClibCntsShareReqVO vo)  throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public int insert(ClibCntsShareReqVO vo)  throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 수정하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public int update(ClibCntsShareReqVO vo)  throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 삭제하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public int delete(ClibCntsShareReqVO vo)  throws Exception;
	
	/**
	 * 콘텐츠 공유요청 정보를 삭제하고 결과를 반환한다. - OLC콘텐츠 삭제시 삭제
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public int deleteCnts(ClibCntsShareReqVO vo)  throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 삭제하고 결과를 반환한다. - OLC콘텐츠 삭제시 삭제
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public int rejectCnts(ClibCntsShareReqVO vo)  throws Exception;

	/**
	 * 콘텐츠 공유요청 정보를 수정하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	public int updateShareCd(ClibCntsShareReqVO vo)  throws Exception;
}

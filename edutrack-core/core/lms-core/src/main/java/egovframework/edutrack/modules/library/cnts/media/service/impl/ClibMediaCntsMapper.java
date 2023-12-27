package egovframework.edutrack.modules.library.cnts.media.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("clibMediaCntsMapper")
public interface ClibMediaCntsMapper{

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibMediaCntsVO> list(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibMediaCntsVO> listPageing(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public int count(ClibMediaCntsVO vo) throws Exception;
	
	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일 항목 정보를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibMediaCntsVO select(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 OLC제작용 콘텐츠를 가져온다
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<ClibMediaCntsVO> listOlc(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 업로드 파일키를 이용하여 미디어 콘텐츠 단일 항목 정보를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibMediaCntsVO selectByUploadFileKey(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일 항목 정보를 등록하고 결과를 반환한다.
	 * @param ClibMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int insert(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠의 콘텐츠키를 생성하여 반환한다.
	 * @param ClibMediaCntsVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibMediaCntsVO selectCntsCd() throws Exception;
	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일 항목 정보를 수정하고 결과를 반환한다.
	 * @param ClibMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int update(ClibMediaCntsVO vo) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 다중 항목 정보를 수정하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibMediaCntsVO> itemArray) throws Exception;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 항목 정보를 삭제하고 결과를 반환한다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public int delete(ClibMediaCntsVO vo) throws Exception;


	/**
	 * 콘텐츠 라리브러리 : 미디어 콘탠츠를 공유 콘텐츠로 복사한다.
	 * @param ClibMediaCntsVO
	 * @reurn ProcessResultVO
	 */
	public int insertShare(ClibMediaCntsVO vo) throws Exception;

}

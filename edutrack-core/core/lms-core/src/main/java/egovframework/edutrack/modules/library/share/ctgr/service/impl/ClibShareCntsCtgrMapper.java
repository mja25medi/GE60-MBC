package egovframework.edutrack.modules.library.share.ctgr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.library.share.ctgr.service.ClibShareCntsCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("clibShareCntsCtgrMapper")
public interface ClibShareCntsCtgrMapper {


	/**
	 * 콘텐츠  분류의 분류를 목록으로 가져온다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @return ProcessReslutListVO
	 */
	
	public List<ClibShareCntsCtgrVO> list(ClibShareCntsCtgrVO vo)  ;


	/**
	 * 콘텐츠  분류의 하위 분류를 목록으로 가져온다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<ClibShareCntsCtgrVO> listSub(ClibShareCntsCtgrVO vo) ;
	/**
	 * 분류 정보를 조회한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public ClibShareCntsCtgrVO select(ClibShareCntsCtgrVO vo) ;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int insert(ClibShareCntsCtgrVO vo) ;

	/**
	 * 분류 코드 조회를 생성해서 반환한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @return ProcessResultVO
	 */
	public ClibShareCntsCtgrVO selectCtgrCd() ;

	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param ClibShareCntsCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int update(ClibShareCntsCtgrVO vo)  ;

	/**
	 * 분류 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<ClibShareCntsCtgrVO> itemArray) ;

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public int delete(ClibShareCntsCtgrVO vo) ;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param ClibCntsShareCtgrVO.orgCd
	 * @param ClibCntsShareCtgrVO.userNo
	 * @param ClibCntsShareCtgrVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteSub(ClibShareCntsCtgrVO vo)  ;
}

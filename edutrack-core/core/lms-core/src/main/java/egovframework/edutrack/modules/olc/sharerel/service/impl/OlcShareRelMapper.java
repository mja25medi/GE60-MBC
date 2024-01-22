package egovframework.edutrack.modules.olc.sharerel.service.impl;

import java.util.List;

import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("olcShareRelMapper")
public interface OlcShareRelMapper {


	/**
	 * OLC 공유 분류 관계 정보를 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcShareRelVO> listByCartrg(OlcShareRelVO vo)  ;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 카트리지 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcShareRelVO> listPageingByCartrg(OlcShareRelVO vo)  ;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록수를 반환
	 * 카트리지 기준
	 */
	
	public int count(OlcShareRelVO vo)  ;
	
	/**
	 * OLC 공유 분류 관계 정보를 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcShareRelVO> listByCtgr(OlcShareRelVO vo)  ;

	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcShareRelVO> listPageingByCtgr(OlcShareRelVO vo)  ;
	
	/**
	 * OLC 공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public int listPageingByCtgrCount(OlcShareRelVO vo)  ;
	
	/**
	 * 공유 분류 관계 정보를 조회한다.
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public OlcShareRelVO select(OlcShareRelVO vo)  ;


	/**
	 * 공유 분류 관계 정보를 등록하고 결과를 반환한다.
	 * @param OlcShareRelVO
	 * @reurn ProcessResultVO
	 */
	public int insert(OlcShareRelVO vo)  ;
	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public int delete(OlcShareRelVO vo)  ;

	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * 분류 삭제시 연결된 카트리지 정보
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteCtgr(OlcShareRelVO vo)  ;

	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * 분류 삭제시 연결된 카트리지 정보
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteCartrg(OlcShareRelVO vo) ;


	/**
	 * 공유 분류 관계 정보를 삭제하고 결과를 반환한다.
	 * OLC수정시 공유 체크할 경우
	 * @param OlcShareRelVO.orgCd
	 * @param OlcShareRelVO.userNo
	 * @param OlcShareRelVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteCartrgCheck(OlcShareRelVO vo) ;

	/**
	 * OLC 지식공유 분류 관계 정보를 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcShareRelVO> listByCtgrKnow(OlcShareRelVO vo) ;

	/**
	 * OLC 지식공유 분류 관계 정보를 페이징 목록으로 가져온다.
	 * 분류 기준
	 * @param OlcShareCtgrVO.orgCd
	 * @param OlcShareCtgrVO.userNo
	 * @param OlcShareCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcShareRelVO> listPageingByCtgrKnow(OlcShareRelVO vo)  ;

}

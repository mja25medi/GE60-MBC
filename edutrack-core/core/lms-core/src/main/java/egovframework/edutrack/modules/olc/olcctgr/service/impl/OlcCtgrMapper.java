package egovframework.edutrack.modules.olc.olcctgr.service.impl;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("olcCtgrMapper")
public interface OlcCtgrMapper {

	/**
	 * OLC 분류를 TREE 형 목록으로 조회
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd (선택적으로 입력, 최상위 분류부터 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcCtgrVO> listTree(OlcCtgrVO vo) ;

	/**
	 * OLC 분류의 하위 분류를 목록으로 가져온다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd (최상위 분류를 조회시는 입력이 없어야 한다.)
	 * @return ProcessReslutListVO
	 */
	
	public List<OlcCtgrVO> listSub(OlcCtgrVO vo)  ;

	/**
	 * 분류 정보를 조회한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.ctgrCd
	 * @return ProcessResultVO
	 */
	public OlcCtgrVO select(OlcCtgrVO vo)  ;

	/**
	 * 분류를 등록하고 결과를 반환한다.
	 * @param OlcCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int insert(OlcCtgrVO vo)  ;

	/**
	 * 분류 코드 조회를 생성해서 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @return ProcessResultVO
	 */
	public OlcCtgrVO selectCtgrCd() ;
	/**
	 * 분류를 수정하고 결과를 반환한다.
	 * @param OlcCtgrVO
	 * @reurn ProcessResultVO
	 */
	public int update(OlcCtgrVO vo) ;

	/**
	 * 분류 정보의 다중 목록을 Update하고 결과를 반환한다.
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateBatch(List<OlcCtgrVO> itemArray) ;

	/**
	 * 분류를 삭제하고 결과를 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.ctgrCd
	 * @reurn ProcessResultVO
	 */
	public int delete(OlcCtgrVO vo) ;

	/**
	 * 하위 분류를 삭제하고 결과를 반환한다.
	 * @param OlcCtgrVO.orgCd
	 * @param OlcCtgrVO.userNo
	 * @param OlcCtgrVO.parCtgrCd
	 * @reurn ProcessResultVO
	 */
	public int deleteSub(OlcCtgrVO vo) ;
}

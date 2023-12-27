package egovframework.edutrack.modules.org.code.service;

import java.util.List;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface OrgCodeService {

	/**
	 *  코드 분류 전체 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCodeCtgrVO> listCtgr(OrgCodeCtgrVO vo)
			throws Exception;

	/**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCodeCtgrVO> listCtgrPageing(
			OrgCodeCtgrVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCodeCtgrVO> listCtgrPageing(
			OrgCodeCtgrVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCodeCtgrVO> listCtgrPageing(
			OrgCodeCtgrVO vo, int pageIndex) throws Exception;

	/**
	 * 코드 분류 상세 정보를 조회한다.
	 * @param OrgCodeCtgrVO
	 * @return OrgCodeCtgrVO
	 * @throws Exception
	 */
	public abstract OrgCodeCtgrVO viewCtgr(OrgCodeCtgrVO vo) throws Exception;

	/**
	 * 코드 분류 정보를 등록한다.
	 * @param OrgCodeCtgrVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addCtgr(OrgCodeCtgrVO vo) throws Exception;

	/**
	 * 코드 분류 정보를 수정한다.
	 * @param OrgCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCtgr(OrgCodeCtgrVO vo) throws Exception;

	/**
	 * 코드 분류 정보를 삭제 한다.
	 * @param OrgCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCtgr(String orgCd, String codeCtgrCd)
			throws Exception;

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param OrgCodeCtgrVO
	 * @return ProcessResultListDTO<OrgCodeVO>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgCodeVO> listCode(String orgCd,
			String codeCtgrCd) throws Exception;

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListDTO<OrgCodeVO>
	 * @throws Exception 
	 */
	public abstract ProcessResultListVO<OrgCodeVO> listCode(String orgCd,
			String codeCtgrCd, boolean use) throws Exception;

	/**
	 * 코드의 정보를 조회한다.
	 * @param cdCtgrCd
	 * @param cdCd
	 * @return OrgCodeVO
	 */
	public abstract OrgCodeVO viewCode(String orgCd, String codeCtgrCd,
			String codeCd) throws Exception;
	/**
	 * 코드의 정보를 조회한다.
	 * @param cdCtgrCd
	 * @param cdCd
	 * @return OrgCodeVO
	 */
	public abstract OrgCodeVO viewCode(OrgCodeVO orgCodeVO) throws Exception;

	/**
	 * 코드 정보를 등록한다.
	 * @param OrgCodeVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addCode(OrgCodeVO vo) throws Exception;

	/**
	 * 코드 정보를 수정한다.
	 * @param OrgCodeVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCode(OrgCodeVO vo) throws Exception;

	/**
	 * 코드 정보를 삭제한다.
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCode(String orgCd, String codeCtgrCd,
			String codeCd) throws Exception;

	/**
	 * 코드 정보를 수정한다.
	 * @param OrgCodeVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortCode(OrgCodeVO vo) throws Exception;
	
	/**
	 * 코드 정보의 페이지 목록를 반환한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListDTO<OrgCodeVO>
	 * @throws Exception 
	 */
	public abstract ProcessResultListVO<OrgCodeVO> listCodePageing(OrgCodeVO vo) throws Exception;
	
	/**
	 * 시스템 코드 언어 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract List<OrgCodeLangVO> langList(OrgCodeLangVO dto) throws Exception;
}
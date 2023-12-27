package egovframework.edutrack.modules.system.code.service;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface SysCodeService {

	/**
	 *  코드 분류 전체 목록을 조회한다.
	 * @param SysCodeCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysCodeCtgrVO> listCtgr(SysCodeCtgrVO vo)
			throws Exception;

	/**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param SysCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysCodeCtgrVO> listCtgrPageing(
			SysCodeCtgrVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param SysCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysCodeCtgrVO> listCtgrPageing(
			SysCodeCtgrVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param SysCodeCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysCodeCtgrVO> listCtgrPageing(
			SysCodeCtgrVO vo, int pageIndex) throws Exception;

	/**
	 * 코드 분류 상세 정보를 조회한다.
	 * @param SysCodeCtgrVO
	 * @return SysCodeCtgrVO
	 * @throws Exception
	 */
	public abstract SysCodeCtgrVO viewCtgr(SysCodeCtgrVO vo) throws Exception;

	/**
	 * 코드 분류 정보를 등록한다.
	 * @param SysCodeCtgrVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addCtgr(SysCodeCtgrVO vo) throws Exception;

	/**
	 * 코드 분류 정보를 수정한다.
	 * @param SysCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCtgr(SysCodeCtgrVO vo) throws Exception;

	/**
	 * 코드 분류 정보를 삭제 한다.
	 * @param SysCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCtgr(String codeCtgrCd) throws Exception;

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param SysCodeCtgrVO
	 * @return ProcessResultListDTO<SysCodeVO>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysCodeVO> listCode(String codeCtgrCd)
			throws Exception;

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListDTO<SysCodeVO>
	 * @throws Exception 
	 */
	public abstract ProcessResultListVO<SysCodeVO> listCode(String codeCtgrCd,
			boolean use) throws Exception;

	/**
	 * 메뉴가 변경되었음을 DB에 저장한다.
	 */
	public abstract void setCodeChanged() throws Exception;

	/**
	 * 메뉴의 버젼값을 DB와 비교한다.
	 * @return true:변경됨, false:변경되지 않음
	 * @throws Exception  
	 */
	public abstract int selectCodeVersion() throws Exception;

	/**
	 * DB에서 코드를 읽어와 리턴한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListVO<SysCodeVO>
	 */
	public abstract ProcessResultListVO<SysCodeVO> listCodeByDB(
			String codeCtgrCd) throws Exception;

	/**
	 * 코드의 정보를 조회한다.
	 * @param cdCtgrCd
	 * @param cdCd
	 * @return SysCodeVO
	 */
	public abstract SysCodeVO viewCode(String codeCtgrCd, String codeCd)
			throws Exception;

	/**
	 * 코드 정보를 등록한다.
	 * @param SysCodeVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addCode(SysCodeVO vo) throws Exception;

	/**
	 * 코드 정보를 수정한다.
	 * @param SysCodeVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCode(SysCodeVO vo) throws Exception;

	/**
	 * 코드 정보를 삭제한다.
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCode(String codeCtgrCd, String codeCd)
			throws Exception;

	/**
	 * 코드의 순서를 변경한다.
	 * @param SysCodeVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortCode(SysCodeVO vo) throws Exception;
	
	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR )
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	public abstract List<SysCodeVO> listSelectCd(String codeCtgrCd, String codeCd) throws Exception ;

	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR의 CODE_OPTN코드 조회 )
	 * @param codeCtgrCd
	 * @param codeOptn
	 * @return
	 */
	public abstract List<SysCodeVO> listSelectOptn(String codeCtgrCd, String codeOptn) throws Exception;

}
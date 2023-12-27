package egovframework.edutrack.comm.service;

import java.util.List;

import egovframework.edutrack.modules.system.code.service.SysCodeVO;

public interface SysCodeMemService {

	/**
	 * 시스템 코드 리스트를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	public abstract List<SysCodeVO> getSysCodeList(String codeCtgrCd)
			throws Exception;
	/**
	 * 시스템 코드 리스트(언어)를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	public abstract List<SysCodeVO> getSysCodeList(String codeCtgrCd,String locale)
			throws Exception;

	/**
	 * 메모리에서 시스템 코드 정보를 검색하여 반환한다.
	 *
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	public abstract SysCodeVO getCode(String codeCtgrCd, String codeCd)
			throws Exception;
	/**
	 * 메모리에서 시스템 코드 정보(언어)를 검색하여 반환한다.
	 *
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	public abstract SysCodeVO getCode(String codeCtgrCd, String codeCd,String locale)
			throws Exception;
}
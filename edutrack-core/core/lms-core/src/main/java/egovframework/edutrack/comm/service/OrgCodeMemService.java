package egovframework.edutrack.comm.service;

import java.util.List;

import egovframework.edutrack.modules.org.code.service.OrgCodeVO;

public interface OrgCodeMemService {

	/**
	 * 기관코드 리스트를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	public abstract List<OrgCodeVO> getOrgCodeList(String orgCd, String codeCtgrCd) throws Exception;

}
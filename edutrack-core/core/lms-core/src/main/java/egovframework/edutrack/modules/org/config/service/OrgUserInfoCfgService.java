package egovframework.edutrack.modules.org.config.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgUserInfoCfgService {

	/**
	 * 교육기관 설정 값 전체 목록을 조회한다.
	 * @param OrgUserInfoCfgVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgUserInfoCfgVO> list(
			OrgUserInfoCfgVO vo) throws Exception;

	/**
	 * 교육기관 설정 값 상세 정보를 조회한다.
	 * @param OrgUserInfoCfgVO
	 * @return OrgUserInfoCfgVO
	 * @throws Exception
	 */
	public abstract OrgUserInfoCfgVO view(OrgUserInfoCfgVO vo) throws Exception;

	/**
	 * 교육기관 설정 값 정보를 등록한다.
	 * @param OrgUserInfoCfgVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgUserInfoCfgVO vo) throws Exception;

	/**
	 * 교육기관 설정 값 정보를 수정한다.
	 * @param OrgUserInfoCfgVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgUserInfoCfgVO vo) throws Exception;

	/**
	 * 교육기관 설정 값의 순서를 변경한다.
	 * @param OrgUserInfoCfgVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sort(OrgUserInfoCfgVO vo) throws Exception;
	
	/**
	 * 교육기관 설정 값 정보를 삭제 한다.
	 * @param OrgUserInfoCfgVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgUserInfoCfgVO vo) throws Exception;

	/**
	 * 교육기관 설정 값 정보를 삭제 한다.
	 * @param OrgUserInfoCfgVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeAll(OrgUserInfoCfgVO vo) throws Exception;
	
	public abstract ProcessResultListVO<OrgUserInfoCfgVO> listForJoin(
			OrgUserInfoCfgVO vo) throws Exception;

}
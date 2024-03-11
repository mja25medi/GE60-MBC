package egovframework.edutrack.modules.org.crscert.service;


public interface OrgCertService {

	/**
	 * 기관 수료증 상세 정보
	 * @param OrgCertVO
	 * @return OrgCertVO
	 * @throws Exception
	 */
	public abstract OrgCertVO view(OrgCertVO vo) throws Exception;

	/**
	 * 기관 수료증 정보를 등록한다.
	 * @param OrgCertVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgCertVO vo) throws Exception;

	/**
	 * 기관 수료증 정보를 수정한다.
	 * @param OrgCertVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgCertVO vo) throws Exception;

	/**
	 * 기관 수료증 정보를 삭제 한다.
	 * @param OrgCertVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgCertVO vo) throws Exception;

}
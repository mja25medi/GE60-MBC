package egovframework.edutrack.modules.org.crscert.service;


public interface OrgCrsCertService {

	/**
	 * 기관 수료증 상세 정보
	 * @param OrgCrsCertVO
	 * @return OrgCrsCertVO
	 * @throws Exception
	 */
	public abstract OrgCrsCertVO view(OrgCrsCertVO vo) throws Exception;

	/**
	 * 기관 수료증 정보를 등록한다.
	 * @param OrgCrsCertVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgCrsCertVO vo) throws Exception;

	/**
	 * 기관 수료증 정보를 수정한다.
	 * @param OrgCrsCertVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgCrsCertVO vo) throws Exception;

	/**
	 * 기관 수료증 정보를 삭제 한다.
	 * @param OrgCrsCertVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgCrsCertVO vo) throws Exception;

}
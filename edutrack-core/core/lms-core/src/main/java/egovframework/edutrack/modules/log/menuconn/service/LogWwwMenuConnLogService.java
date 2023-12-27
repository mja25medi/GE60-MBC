package egovframework.edutrack.modules.log.menuconn.service;


public interface LogWwwMenuConnLogService {

	/**
	 * 홈페이지 메뉴별 접속 로그 정보를 등록한다.
	 * @param LogWwwMenuConnLogVO
	 * @return String
	 * @throws Exception
	 */
	public abstract void add(LogWwwMenuConnLogVO vo) throws Exception;

}
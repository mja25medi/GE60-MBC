package egovframework.edutrack.modules.log.logintry.service;

import java.util.List;

import egovframework.edutrack.comm.annotation.HrdApiUsrLogin;
import egovframework.edutrack.comm.annotation.HrdApiUsrLogin.Type;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface LogUserLoginTryLogService {

	/**
	 *  로그인 시도 로그 전체 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserLoginTryLogVO> list(
			LogUserLoginTryLogVO vo) throws Exception;

	/**
	 * 로그인 시도 로그 페이징 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserLoginTryLogVO> listPageing(
			LogUserLoginTryLogVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 로그인 시도 로그 페이징 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserLoginTryLogVO> listPageing(
			LogUserLoginTryLogVO vo, int pageIndex, int listScale)
			throws Exception;

	/**
	 * 로그인 시도 로그 페이징 목록을 조회한다.
	 * @param LogUserLoginTryLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserLoginTryLogVO> listPageing(
			LogUserLoginTryLogVO vo, int pageIndex) throws Exception;

	/**
	 * 로그인 시도 로그 정보를 등록한다.
	 * @param LogUserLoginTryLogVO
	 * @return String
	 * @throws Exception
	 */
	@HrdApiUsrLogin(Type.CREATE)
	public abstract void add(LogUserLoginTryLogVO vo) throws Exception;
	
	/**
	 * api용 회원로그인정보 조회
	 * @param LogUserLoginTryLogVO vo
	 * @return  List<EgovMap>
	 */
	public abstract List<EgovMap> selectUserLoginApi(LogUserLoginTryLogVO vo) throws Exception;

}
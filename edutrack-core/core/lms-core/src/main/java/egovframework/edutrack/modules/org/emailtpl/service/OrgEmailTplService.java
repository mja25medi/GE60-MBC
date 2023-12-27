package egovframework.edutrack.modules.org.emailtpl.service;

import java.util.Map;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;

public interface OrgEmailTplService {

	/**
	 * 이메일 템플릿 전체 목록을 조회한다.
	 * @param OrgEmailTplVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgEmailTplVO> list(OrgEmailTplVO vo)
			throws Exception;

	/**
	 * 이메일 템플릿 페이징 목록을 조회한다.
	 * @param OrgEmailTplVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgEmailTplVO> listPageing(
			OrgEmailTplVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 이메일 템플릿 페이징 목록을 조회한다.
	 * @param OrgEmailTplVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgEmailTplVO> listPageing(
			OrgEmailTplVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 이메일 템플릿 페이징 목록을 조회한다.
	 * @param OrgEmailTplVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgEmailTplVO> listPageing(
			OrgEmailTplVO vo, int pageIndex) throws Exception;

	/**
	 * 이메일 템플릿 상세 정보를 조회한다.
	 * @param OrgEmailTplVO
	 * @return OrgEmailTplVO
	 * @throws Exception
	 */
	public abstract OrgEmailTplVO view(OrgEmailTplVO vo) throws Exception;

	/**
	 * 이메일 템플릿 정보를 등록한다.
	 * @param OrgEmailTplVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgEmailTplVO vo) throws Exception;

	/**
	 * 이메일 템플릿 정보를 수정한다.
	 * @param OrgEmailTplVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgEmailTplVO vo) throws Exception;

	/**
	 * 이메일 템플릿 정보를 삭제 한다.
	 * @param OrgEmailTplVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgEmailTplVO vo) throws Exception;

	/**
	 * 이메일 템플릿 정보 전체를 삭제 한다.
	 * @param OrgEmailTplVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeAll(OrgEmailTplVO vo) throws Exception;

	/**
	 * MessageDTO를 받아 메일 Tpl을 적용한다.
	 * @param tplCd
	 * @param map
	 * @param message
	 * @return
	 */
	public abstract void decorator(String orgCd, String tplCd,
			Map<String, Object> argu, LogMsgLogVO message);

}
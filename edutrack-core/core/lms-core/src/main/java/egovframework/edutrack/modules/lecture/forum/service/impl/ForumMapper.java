package egovframework.edutrack.modules.lecture.forum.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.forum.service.ForumVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.List;

@Mapper("forumMapper")
public interface ForumMapper{

    /**
	 * 토론 목록 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return List<ForumVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ForumVO> list(ForumVO vo) throws DataAccessException;

	/**
	 * 토론 정보 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	public ForumVO select(ForumVO vo) throws Exception;
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;

	@SuppressWarnings("unchecked")
	public List<ForumVO> listStd(ForumVO vo) throws Exception;

	/**
	 * 토론 정보 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param iForumVO
	 * @return ProcessResultVO<ForumVO>
	 */
	public int insert(ForumVO vo) throws Exception;

	/**
	 * 토론 정보 수정
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param iForumVO
	 * @return ProcessResultVO<ForumVO>
	 */
	public int update(ForumVO vo) throws Exception;
	
	/**
	 * 토론 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param iForumVO
	 * @return ProcessResultVO<ForumVO>
	 */
	public int delete(ForumVO vo ) throws Exception;
	
	/**
	 * 토론 관련 정보 현황
	 * @param vo
	 * @return
	 */
	public EgovMap selectForumStatus(ForumVO vo) throws Exception;

}
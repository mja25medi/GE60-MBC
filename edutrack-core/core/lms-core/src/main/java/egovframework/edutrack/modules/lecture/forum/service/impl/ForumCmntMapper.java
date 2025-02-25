package egovframework.edutrack.modules.lecture.forum.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.forum.service.ForumCmntVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("forumCmntMapper")
public interface ForumCmntMapper {

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;
	/**
	 * 댓글 목록 전체 수 조회
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return Integer
	 */
	
	public Integer count(ForumCmntVO vo) ;
	/**
	 * 댓글 목록 조회
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return List<ForumCommentVO>
	 */
	
	public List<ForumCmntVO> listPageing(ForumCmntVO vo) ;

	/**
	 * 댓글 정보 저장
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<ForumCommentVO>
	 */
	public int insert(ForumCmntVO vo) ;

	/**
	 * 댓글 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param commentVO
	 * @return ProcessResultVO<?>
	 */
	public int delete(ForumCmntVO vo) ;
}

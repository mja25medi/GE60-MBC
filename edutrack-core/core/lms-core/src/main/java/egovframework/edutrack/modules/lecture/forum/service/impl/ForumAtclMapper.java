package egovframework.edutrack.modules.lecture.forum.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.forum.service.ForumAtclVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("forumAtclMapper")
public interface ForumAtclMapper {

	/**
	 * 토론 게시글 목록 수 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param iForumArticleVO
	 * @return int
	 * @ 
	 */
	public int count(ForumAtclVO vo) ;
	
	/**
	 * 토론 게시글 목록 조회 paging
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param iForumArticleVO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @ List<ForumArticleVO>
	 */
	public List<ForumAtclVO> listPageing(ForumAtclVO vo) ;
	
	/**
	 * 토론 게시글 목록 조회 paging
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param iForumArticleVO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 * @ List<ForumArticleVO>
	 */
	public List<ForumAtclVO> listPageingVer5(ForumAtclVO vo) ;

	/**
	 * 토론 게시글 목록 조회
	 * @author twkim
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return List<ForumArticleVO>
	 */
	public List<ForumAtclVO> list(ForumAtclVO vo) ;

	/**
	 * 토론 게시글 상세 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public ForumAtclVO select(ForumAtclVO vo)  ;

	/**
	 * 토론 게시글 상세 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public int selectKey()  ;

	/**
	 * 토론 게시글 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public int insert(ForumAtclVO vo)  ;

	/**
	 * 토론 게시글 수정
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public int update(ForumAtclVO vo) ;

	/**
	 * 게시글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param iForumArticleVO
	 * @return ProcessResultVO<?>
	 */
	public int delete(ForumAtclVO vo) ;

	/**
	 * 조회수 증가
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param iForumArticleVO
	 * @ void
	 */
	public void hitsup(ForumAtclVO vo)  ;


}

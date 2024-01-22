package egovframework.edutrack.modules.lecture.forum.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.forum.service.ForumJoinUserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("forumJoinUserMapper")
public interface ForumJoinUserMapper {

	/**
	 * 학습자 목록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<ForumJoinUserVO> listPageing(ForumJoinUserVO vo) ;
	
	/**
	 * 학습자 목록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public int count(ForumJoinUserVO vo) ;
	
	/**
	 * 학습자 목록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<ForumJoinUserVO> list(ForumJoinUserVO vo) ;

	/**
	 * 토론 개별 점수 저장
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	public int insert(ForumJoinUserVO vo) ;

	/**
	 * 토론 개별 점수 수정
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	public int update(ForumJoinUserVO vo) ;

	/**
	 * 토론 점수 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 29.
	 * @param vo
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	public int delete(ForumJoinUserVO vo)  ;

}

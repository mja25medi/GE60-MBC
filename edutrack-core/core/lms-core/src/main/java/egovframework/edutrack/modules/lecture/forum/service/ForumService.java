package egovframework.edutrack.modules.lecture.forum.service;


import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface ForumService {

	/**
	 * 토론 목록 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultListVO<ForumVO>
	 */
	public abstract ProcessResultListVO<ForumVO> listForum(ForumVO vo) throws Exception;

	/**
	 * 토론 정보 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	public abstract ProcessResultVO<ForumVO> viewForum(ForumVO vo) throws Exception;

	/**
	 * 토론 정보 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	public abstract ProcessResultVO<ForumVO> addForum(ForumVO vo) throws Exception;

	/**
	 * 토론 정보 수정
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	public abstract ProcessResultVO<ForumVO> editForum(ForumVO vo) throws Exception;

	/**
	 * 토론 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	public abstract ProcessResultVO<ForumVO> deleteForum(ForumVO vo) throws Exception;

	/**
	 * 토론 게시글 목록 조회 paging
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param vo
	 * @param curPage
	 * @return ProcessResultListVO<ForumArticleVO>
	 */
	public abstract ProcessResultListVO<ForumAtclVO> listAtclPageing(
			ForumAtclVO vo) throws Exception;

	/**
	 * 토론 게시글 상세 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param vo
	 * @param hitsup
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public abstract ProcessResultVO<ForumAtclVO> viewAtcl(ForumAtclVO vo,
			boolean hitsup) throws Exception;

	/**
	 * 토론 게시글 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public abstract ProcessResultVO<ForumAtclVO> addAtcl(ForumAtclVO vo) throws Exception;

	/**
	 * 토론 게시글 수정
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	public abstract ProcessResultVO<ForumAtclVO> editAtcl(ForumAtclVO vo) throws Exception;

	/**
	 * 토론 게시글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public abstract ProcessResultVO<?> removeAtcl(ForumAtclVO vo,
			String classUserType) throws Exception;

	/**
	 * 토론 게시글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public abstract ProcessResultVO<?> removeAtcl(ForumAtclVO vo) throws Exception;

	/**
	 * 댓글 목록 조회
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @param curPage
	 * @return ProcessResultListVO<ForumCommentVO>
	 */
	public abstract ProcessResultListVO<ForumCmntVO> listCmntPageing(
			ForumCmntVO vo, int curPage) throws Exception;

	/**
	 * 댓글 정보 저장
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<ForumCommentVO>
	 */
	public abstract ProcessResultVO<ForumCmntVO> addCmnt(ForumCmntVO vo) throws Exception;

	/**
	 * 댓글 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	public abstract ProcessResultVO<?> removeCmnt(ForumCmntVO vo) throws Exception;

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
	public abstract ProcessResultListVO<ForumJoinUserVO> listJoinUserPageing(
			ForumJoinUserVO vo) throws Exception;

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
	public ProcessResultListVO<ForumJoinUserVO> listJoinUser(
			ForumJoinUserVO vo) throws Exception;

	/**
	 * 토론 점수 개별 등록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	public abstract ProcessResultVO<ForumJoinUserVO> addJoinUser(
			ForumJoinUserVO vo) throws Exception;

	/**
	 * 토론 점수 일괄 등록
	 * @author twkim
	 * @date 2013. 10. 29.
	 * @param vo
	 * @param strStdNo
	 * @param strScore
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	public abstract ProcessResultVO<ForumJoinUserVO> addJoinUserAll(
			ForumJoinUserVO vo, String strStdNo, String strScore) throws Exception;

	/**
	 * 토론 정보 목록 조회	(학습자용)
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultListVO<ForumVO>
	 */
	public abstract ProcessResultListVO<ForumVO> listForumStd(ForumVO vo) throws Exception;

}
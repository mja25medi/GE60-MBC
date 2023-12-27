package egovframework.edutrack.modules.lecture.bbs.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.bbs.service.LecBbsCmntVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("lecBbsCmntMapper")
public interface LecBbsCmntMapper {

	/**
	 * 게시물에 첨부된 덧글 목록을 조회한다.
	 * @param commentVO
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<LecBbsCmntVO> list(final LecBbsCmntVO vo) throws DataAccessException;

	/**
	 * 게시물에 첨부된 덧글 목록을 조회한다.
	 * @param commentVO
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<LecBbsCmntVO> listPageing(final LecBbsCmntVO vo) throws DataAccessException;

	/**
	 * 게시물에 첨부된 덧글 목록수 반환
	 * @param commentVO
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public int count(final LecBbsCmntVO vo) throws DataAccessException;
	
	/**
	 * 게시물 덧글의 단일항목을 조회한다.
	 * @param commentVO (atclSn, cmntSn 이 포함된 예제 클래스)
	 * @return 조회결과를 포함한 {@code ProcessResultVO<CommentVO>}
	 * @throws DataAccessException
	 */
	public LecBbsCmntVO select(LecBbsCmntVO vo) throws DataAccessException;
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;

	/**
	 * 게시물에 덧글을 저장한다.
	 * @param QnaAnswerVO
	 * @return cmntSn 주키가 주입된 결과를 담은 {@code ProcessResultVO<CommentVO>}
	 * @throws DataAccessException
	 */
	public int insert(LecBbsCmntVO vo) throws DataAccessException;

	/**
	 * 게시물에 덧글을 수정한다.
	 * @param commentVO
	 * @return 처리결과만 저장한 {@code ProcessResultVO<?>}
	 * @throws DataAccessException
	 */
	public int update(LecBbsCmntVO vo) throws DataAccessException;
	
	/**
	 * 게시물에 덧글을 삭제한다.
	 * @param commentVO
	 * @return 처리결과만 저장한 {@code ProcessResultVO<?>}
	 * @throws DataAccessException
	 */
	public int delete(LecBbsCmntVO vo) throws DataAccessException;

	/**
	 * 게시물에 덧글을 삭제한다.
	 * @param commentVO
	 * @return 처리결과만 저장한 {@code ProcessResultVO<?>}
	 * @throws DataAccessException
	 */
	public int deleteAll(LecBbsCmntVO vo) throws Exception;

	/**
	 * 게시판의 덧글을 삭제한다.
	 * @param commentVO
	 * @return 처리결과만 저장한 {@code ProcessResultVO<?>}
	 * @throws DataAccessException
	 */
	public int deleteForInfo(LecBbsCmntVO vo) throws Exception;


}

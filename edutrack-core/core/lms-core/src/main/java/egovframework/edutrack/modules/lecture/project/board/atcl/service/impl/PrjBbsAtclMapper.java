package egovframework.edutrack.modules.lecture.project.board.atcl.service.impl;

import org.springframework.dao.DataAccessException;

import java.util.List;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("prjBbsAtclMapper")
public interface PrjBbsAtclMapper {
	
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	/**
	 * 팀프로젝트 게시글 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	@SuppressWarnings("unchecked")
	public List<PrjBbsAtclVO> listPageing(PrjBbsAtclVO vo) throws Exception;
	
	/**
	 * 팀프로젝트 게시글 목록수 반환
	 * @param vo
	 * @return ProcessResultListVO
	 */
	@SuppressWarnings("unchecked")
	public int count(PrjBbsAtclVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시글 정보 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public PrjBbsAtclVO select(PrjBbsAtclVO vo) throws Exception;
	
	/**
	 * 팀프로젝트 게시글 정보 등록
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int insert(PrjBbsAtclVO vo) throws Exception;
	
	/**
	 * 팀프로젝트 게시글 정보 수정
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int update(PrjBbsAtclVO vo) throws Exception;

	/**
	 * 팀프로젝트 게시글 정보 삭제
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int delete(PrjBbsAtclVO vo) throws Exception;
	
	/**
	 * 팀프로젝트 게시글 전체 삭제
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int deleteAll(PrjBbsAtclVO vo) throws Exception;

	public int deleteBbsAll(PrjBbsAtclVO vo) throws Exception;
	
	/**
	 * 조회수 1 증가시킨다.
	 * @param vo
	 * @throws DataAccessException
	 */
	public void hitsup(PrjBbsAtclVO vo) throws DataAccessException;
}
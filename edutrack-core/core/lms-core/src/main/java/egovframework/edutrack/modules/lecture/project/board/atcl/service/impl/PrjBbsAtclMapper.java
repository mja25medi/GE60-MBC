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
	public int selectKey()  ;
	/**
	 * 팀프로젝트 게시글 목록 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public List<PrjBbsAtclVO> listPageing(PrjBbsAtclVO vo) ;
	
	/**
	 * 팀프로젝트 게시글 목록수 반환
	 * @param vo
	 * @return ProcessResultListVO
	 */
	
	public int count(PrjBbsAtclVO vo) ;

	/**
	 * 팀프로젝트 게시글 정보 조회
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public PrjBbsAtclVO select(PrjBbsAtclVO vo) ;
	
	/**
	 * 팀프로젝트 게시글 정보 등록
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int insert(PrjBbsAtclVO vo) ;
	
	/**
	 * 팀프로젝트 게시글 정보 수정
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int update(PrjBbsAtclVO vo) ;

	/**
	 * 팀프로젝트 게시글 정보 삭제
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int delete(PrjBbsAtclVO vo) ;
	
	/**
	 * 팀프로젝트 게시글 전체 삭제
	 * @param vo
	 * @return ProcessResultListVO
	 */
	public int deleteAll(PrjBbsAtclVO vo) ;

	public int deleteBbsAll(PrjBbsAtclVO vo) ;
	
	/**
	 * 조회수 1 증가시킨다.
	 * @param vo
	 * @
	 */
	public void hitsup(PrjBbsAtclVO vo) ;
}